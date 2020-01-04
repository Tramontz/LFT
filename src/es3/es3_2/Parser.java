/*  Laboratorio Linguaggi Formali e Traduttori 17/18  
 *
 *  Autore: Stracci Maicol
 *  
 *  Matricola: 821115 
 *  
 *  Esercizio: Esercizio 3.2
 *  
 *  Descrizione: Classe Parser
 *               Analizzatore sintattico di un file in input
 *
 *  Output previsto: Stampa elenco di Token analizzati dal file in input. 
 *               
 */
package Esercizio3_2;

import java.io.*;
import javax.swing.text.html.HTML;

public class Parser {

    private Lexer lex;
    private BufferedReader pbr;
    private Token look;

    public Parser(Lexer l, BufferedReader br) {
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

        statlist();
        match(Tag.EOF);

    }

    private void stat() { // NOTA: Secondo la grammatica, prima di END non deve essere presente il ";".
        if (look.tag == Tag.ID || look.tag == Tag.PRINT || look.tag == Tag.READ || look.tag == Tag.IF || look.tag == Tag.FOR || look.tag == Tag.BEGIN) {
            switch (look.tag) {

                case Tag.ID:
                    move();
                    match('=');
                    expr();
                    break;
                case Tag.PRINT:
                    move();
                    match('(');
                    expr();
                    match(')');
                    break;
                case Tag.READ:
                    move();
                    match('(');
                    match(Tag.ID);
                    match(')');
                    break;
                case Tag.IF:
                    move();
                    match('(');
                    bexpr();
                    match(')');
                    if (look.tag == Tag.THEN) {
                        move();
                        stat();
                    }
                    if (look.tag == Tag.ELSE) {
                        move();
                        stat();
                    }
                    break;
                case Tag.FOR:
                    move();
                    match('(');
                    match(Tag.ID);
                    match('=');
                    expr();
                    match(';');
                    bexpr();
                    match(')');
                    match(Tag.DO);
                    stat();
                    break;
                case Tag.BEGIN:
                    move();
                    statlist();
                    match(Tag.END);
                    break;
            }
        } else {
            System.out.println("Errore in Stat. Simbolo:" + look);
        }

    }

    private void statlist() {
        if (look.tag == Tag.ID || look.tag == Tag.PRINT || look.tag == Tag.READ || look.tag == Tag.IF || look.tag == Tag.FOR || look.tag == Tag.BEGIN) {
            stat();
            statlist_p();
        } else {
            System.out.println("Errore in statlist. Simbolo:" + look);
        }
    }

    private void statlist_p() {
        if (look.tag == ';') {
            move();
            stat();
            statlist_p();
        } else if (look.tag == Tag.EOF || look.tag == Tag.END) {
        } else {
            System.out.println("Errore in statlist_p. Simbolo:" + look);
        }

    }

    private void bexpr() {
        if (look.tag == '(' || look.tag == Tag.NUM || look.tag == Tag.ID) {
            expr();
            match(Tag.RELOP);
            expr();
        } else {
            System.out.println("Errore in bexrp. Simbolo:" + look);
        }

    }

    private void expr() {
        if (look.tag == '(' || look.tag == Tag.NUM || look.tag == Tag.ID) {
            term();
            exprp();
        } else {
            System.out.println("Errore in exrp. Simbolo:" + look);
        }

    }

    private void exprp() {
        if (look.tag == '+' || look.tag == '-') {
            switch (look.tag) {
                case '+':
                    move();
                    term();
                    exprp();
                    break;
                case '-':
                    move();
                    term();
                    exprp();
                    break;
            }
        } else if (look.tag == ';' || look.tag == Tag.EOF || look.tag == Tag.END || look.tag == ')' || look.tag == '(' || look.tag == Tag.RELOP || look.tag == Tag.NUM || look.tag == Tag.ID) {
        } else {
            System.out.println("Errore in exprp. Simbolo:" + look);
        }
    }

    private void term() {
        if (look.tag == '(' || look.tag == Tag.NUM || look.tag == Tag.ID) {
            fact();
            termp();
        } else {
            System.out.println("Errore in term. Simbolo:" + look);
        }

    }

    private void termp() {
        if (look.tag == '*' || look.tag == '/') {
            switch (look.tag) {
                case '*':
                    move();
                    fact();
                    termp();
                    break;
                case '/':
                    move();
                    fact();
                    termp();
                    break;

            }
        } else if (look.tag == ';' || look.tag == Tag.EOF || look.tag == Tag.END || look.tag == ')' || look.tag == '(' || look.tag == Tag.NUM || look.tag == Tag.RELOP || look.tag == Tag.ID || look.tag == '+' || look.tag == '-') {
        } else {
            System.out.println("Errore in termp. Simbolo:" + look);
        }
    }

    private void fact() {
        if (look.tag == '(' || look.tag == Tag.NUM || look.tag == Tag.ID) {
            switch (look.tag) {
                case '(':
                    move();
                    expr();
                    match(')');
                    break;
                case Tag.NUM:
                    move();
                    break;
                case Tag.ID:
                    move();
                    break;
            }
        } else {
            System.out.println("Errore in fact. Simbolo:" + look);
        }

    }

    public static void main(String[] args) {
        Lexer lex = new Lexer();
        String path = "C:\\Users\\maico\\Desktop\\LFT\\LFT\\Esercizio3\\Esercizio3_2\\Test.txt"; // il percorso del file da leggere
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            Parser parser = new Parser(lex, br);
            parser.prog();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
