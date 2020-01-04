/*  Laboratorio Linguaggi Formali e Traduttori 17/18  
 *
 *  Autore: Stracci Maicol
 *  
 *  Matricola: 821115 
 *  
 *  Esercizio: Esercizio 5
 *  
 *  Descrizione: Classe Translator
 *               Traduttore per programmi scritti in linguaggio "P".
 *
 *  Output previsto: File output.j utilizzabile dal programma Jasmin per la 
 *                   transormazione in un file .class eseguibile dalla JVM.
 *
 */
package Esercizio5;

import java.io.*;

public class Translator {

    private Lexer lex;
    private BufferedReader pbr;
    private Token look;

    SymbolTable st = new SymbolTable();
    CodeGenerator code = new CodeGenerator();
    int count = 0;

    public Translator(Lexer l, BufferedReader br) {
        lex = l;
        pbr = br;
        move();
    }

    void move() {
        look = lex.lexical_scan(pbr);
        System.err.println("token = " + look);
    }

    void error(String s) {
        throw new Error("near line " + lex.line + ": " + s);
    }

    void match(int t) {
        if (look.tag == t) {
            if (look.tag != Tag.EOF) {
                move();
            }
        } else {
            error("syntax error");
        }
    }

    public void prog() {
        if (look.tag == Tag.ID || look.tag == Tag.PRINT || look.tag == Tag.FOR || look.tag == Tag.READ || look.tag == Tag.IF || look.tag == Tag.BEGIN) {
            int lnext_prog = code.newLabel();
            statlist(lnext_prog);
            code.emitLabel(lnext_prog);
            match(Tag.EOF);
            try {
                code.toJasmin();
            } catch (java.io.IOException e) {
                System.out.println("IO error\n");
            };
        } else {
            System.out.println("Errore prog");
        }
    }

    public void stat(int lnext) {
        if (look.tag == Tag.ID || look.tag == Tag.PRINT || look.tag == Tag.FOR || look.tag == Tag.READ || look.tag == Tag.IF || look.tag == Tag.BEGIN) {
            switch (look.tag) {

                case Tag.PRINT:
                    match(Tag.PRINT);
                    match('(');
                    expr();
                    code.emit(OpCode.invokestatic, 1);
                    match(')');
                    break;
                case Tag.READ:
                    match(Tag.READ);
                    match('(');
                    if (look.tag == Tag.ID) {
                        int read_id_addr = st.lookupAddress(((Word) look).lexeme);
                        if (read_id_addr == -1) {
                            read_id_addr = count;
                            st.insert(((Word) look).lexeme, count++);
                        }
                        match(Tag.ID);
                        match(')');
                        code.emit(OpCode.invokestatic, 0);
                        code.emit(OpCode.istore, read_id_addr);
                    } else {
                        error("Error in grammar (stat) after read( with " + look);
                    }
                    break;
                case Tag.ID:
                    int read_id_addr = st.lookupAddress(((Word) look).lexeme);
                    Word ID = (Word) look;
                    match(Tag.ID);
                    match('=');
                    expr();
                    if (read_id_addr == -1) {
                        read_id_addr = count;
                        st.insert(ID.lexeme, count++);
                    }
                    code.emit(OpCode.istore, read_id_addr);
                    break;
                case Tag.FOR:
                    match(Tag.FOR);
                    match('(');
                    //Legge id e lo salva
                    int read_id_addrf = st.lookupAddress(((Word) look).lexeme);
                    Word Id = (Word) look;
                    match(Tag.ID);
                    match('=');
                    expr();
                    if (read_id_addrf == -1) {
                        read_id_addrf = count;
                        st.insert(Id.lexeme, count++);
                    }
                    code.emit(OpCode.istore, read_id_addrf);

                    match(';');

                    int lprev = code.newLabel();
                    int ltrue1 = code.newLabel();
                    lnext = code.newLabel();

                    code.emit(OpCode.iload, read_id_addrf);
                    code.emitLabel(lprev);
                    //Controllo fra id e espressione
                    b_expr(ltrue1, lnext);
                    //Se espressione falsa salta a lnext
                    code.emit(OpCode.GOto, lnext);
                    //Se espressione vera fai di seguito
                    code.emitLabel(ltrue1);
                    match(')');
                    match(Tag.DO);
                    stat(lnext);
                    //aumento di uno il valore di ID
                    code.emit(OpCode.iload, read_id_addrf);
                    code.emit(OpCode.ldc, 1);
                    code.emit(OpCode.iadd);
                    code.emit(OpCode.istore, read_id_addrf);
                    code.emit(OpCode.GOto, lprev); // Torno in cima per ricontrollare l' espressione

                    code.emitLabel(lnext); 

                    break;
                case Tag.IF:
                    match(Tag.IF);

                    int ltrue = code.newLabel();
                    lnext = code.newLabel();
                    b_expr(ltrue, lnext);       // Se b_expr == TRUE salta a ltrue
                    code.emit(OpCode.GOto, lnext); // se b_expr == FALSE salta a lfalse 
                    code.emitLabel(ltrue);
                    match(Tag.THEN);
                    stat(lnext);
                    if (look == Word.elsetok) {
                        int lfalse = lnext;
                        lnext = code.newLabel();
                        code.emit(OpCode.GOto, lnext);
                        match(Tag.ELSE);

                        code.emitLabel(lfalse);
                        stat(lnext);
                        code.emitLabel(lnext);
                    } else {
                        code.emitLabel(lnext);
                    }
                    break;
                case Tag.BEGIN:
                    match(Tag.BEGIN);
                    statlist(lnext);
                    match(Tag.END);
                    break;

            }
        } else {
            System.out.println("Errore stat");
        }
    }

    private void statlist(int lnext) {

        if (look.tag == Tag.ID || look.tag == Tag.PRINT || look.tag == Tag.FOR || look.tag == Tag.READ || look.tag == Tag.IF || look.tag == Tag.BEGIN) {

            stat(lnext);
            statlist_p(lnext);
            lnext = code.newLabel();
            code.emitLabel(lnext);

        } else {
            System.out.println("Errore statlist");
        }
    }

    private void statlist_p(int lnext) {
        if (look.tag == ';' || look.tag == Tag.EOF || look.tag == Tag.END) {

            if (look.tag == ';') {
                match(';');
                lnext = code.newLabel();
                code.emitLabel(lnext);
                stat(lnext);
                statlist_p(lnext);

            }
        } else {
            System.out.println("Errore statlist_p");
        }
    }

    private void expr() {
        if (look.tag == '(' || look.tag == Tag.NUM || look.tag == Tag.ID) {
            term();
            exprp();
        } else {
            System.out.println("Errore expr");
        }
    }

    private void b_expr(int ltrue, int lfalse) {
        if (look.tag == '(' || look.tag == Tag.NUM || look.tag == Tag.ID) {
            expr();
            if (look == Word.eq) {
                match(Tag.RELOP);
                expr();
                code.emit(OpCode.if_icmpeq, ltrue);

            }
            if (look == Word.ne) {
                match(Tag.RELOP);
                expr();
                code.emit(OpCode.if_icmpne, ltrue);

            }
            if (look == Word.ge) {
                match(Tag.RELOP);
                expr();
                code.emit(OpCode.if_icmpge, ltrue);

            }
            if (look == Word.le) {
                match(Tag.RELOP);
                expr();
                code.emit(OpCode.if_icmple, ltrue);

            }
            if (look == Word.gt) {
                match(Tag.RELOP);
                expr();
                code.emit(OpCode.if_icmpgt, ltrue);

            }
            if (look == Word.lt) {
                match(Tag.RELOP);
                expr();
                code.emit(OpCode.if_icmplt, ltrue);

            }
        } else {
            System.out.println("Errore b_expr");
        }
    }

    private void exprp() {
        if (look.tag == '+' || look.tag == '-' || look.tag == ')' || look.tag == Tag.THEN || look.tag == Tag.ELSE || look.tag == Tag.END || look.tag == Tag.EOF || look.tag == Tag.RELOP || look.tag == ';') {

            switch (look.tag) {
                case '+':
                    match('+');
                    term();
                    code.emit(OpCode.iadd);
                    exprp();
                    break;
                case '-':
                    match('-');
                    term();
                    code.emit(OpCode.isub);
                    exprp();
                    break;

            }
        } else {
            System.out.println("Errore expr");
        }
    }

    private void term() {
        if (look.tag == '(' || look.tag == Tag.NUM || look.tag == Tag.ID) {

            fact();
            termp();
        } else {
            System.out.println("Errore term");
        }
    }

    private void termp() {
        if (look.tag == '/' || look.tag == '*' || look.tag == '+' || look.tag == '-' || look.tag == ')' || look.tag == Tag.THEN || look.tag == Tag.ELSE || look.tag == Tag.END || look.tag == Tag.EOF || look.tag == Tag.RELOP || look.tag == ';') {

            switch (look.tag) {
                case '*':
                    match('*');
                    term();
                    code.emit(OpCode.imul);
                    exprp();
                    break;
                case '/':
                    match('/');
                    term();
                    code.emit(OpCode.idiv);
                    exprp();
                    break;
            }
        } else {
            System.out.println("Errore termp");
        }
    }

    private void fact() {
        if (look.tag == '(' || look.tag == Tag.NUM || look.tag == Tag.ID) {
            if (look.tag == '(') {
                match('(');
                expr();
                match(')');

            } else if (look.tag == Tag.NUM) {
                NumberTok x = (NumberTok) look;
                int num = x.n;
                code.emit(OpCode.ldc, num);
                move();
            } else if (look.tag == Tag.ID) {
                int read_id_addr = st.lookupAddress(((Word) look).lexeme);
                match(Tag.ID);
                code.emit(OpCode.iload, read_id_addr);
            }
        } else {
            System.out.println("Errore fact");
        }
    }

    public static void main(String[] args) {
        Lexer lex = new Lexer();

        String path = "C:\\Users\\maico\\Desktop\\LFT\\LFT\\Esercizio5\\Test.pas"; // il percorso del file da leggere
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            Translator translator = new Translator(lex, br);
            translator.prog();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
