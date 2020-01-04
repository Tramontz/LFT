/*  Laboratorio Linguaggi Formali e Traduttori 17/18  
 *
 *  Autore: Stracci Maicol
 *  
 *  Matricola: 821115 
 *  
 *  Esercizio: Esercizio 5
 *  
 *  Descrizione: Classe ExpressionTranslator
 *               Traduttore per programmi scritti in linguaggio "P".
 *
 *  Output previsto: File output.j utilizzabile dal programma Jasmin per la 
 *                   transormazione in un file .class eseguibile dalla JVM.
 *
 */
package Esercizio5;

import java.io.*;

public class ExpressionTranslator {

    private Lexer lex;
    private BufferedReader pbr;
    private Token look;

    CodeGenerator code = new CodeGenerator();

    public ExpressionTranslator(Lexer l, BufferedReader br) {
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

        match(Tag.PRINT);
        match('(');
        expr();
        code.emit(OpCode.invokestatic, 1);
        match(')');
        match(Tag.EOF);
        try {
            code.toJasmin();
        } catch (java.io.IOException e) {
            System.out.println("IO error\n");
        };

    }


    private void exprp() {
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
    }
    
    private void expr(){
        term();
        exprp();
    }
    
    private void term() {
        
        fact();
        termp();
    }
    
    private void termp() {
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
    }
    private void fact(){
        if(look.tag == '('){
            match('(');
            expr();
            match(')');
            
        }
        else if(look.tag == Tag.NUM){
            NumberTok x = (NumberTok)look; 
            int num = x.n;
            code.emit(OpCode.ldc,num);
            move();
        }
        else error("errore.");
    }
    public static void main(String[] args) {
        Lexer lex = new Lexer();

        String path = "C:\\Users\\maico\\Desktop\\LFT\\LFT\\Esercizio5\\Test.pas"; // il percorso del file da leggere
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            ExpressionTranslator translator = new ExpressionTranslator(lex, br); 
            translator.prog();
            br.close();
        } catch (IOException e) {e.printStackTrace();}    
    }    
}
