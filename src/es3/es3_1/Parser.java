/*  Laboratorio Linguaggi Formali e Traduttori 17/18  
 *
 *  Autore: Stracci Maicol
 *  
 *  Matricola: 821115 
 *  
 *  Esercizio: Esercizio 3.1
 *  
 *  Descrizione: Classe Parser
 *               Analizzatore sintattico di un file in input
 *
 *  Output previsto: Stampa elenco di Token analizzati dal file in input. 
 *               
 */
package Esercizio3_1;

import java.io.*;

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

    public void start() {
        if (look.tag == '(' || look.tag == Tag.NUM) {
            expr();
            match(Tag.EOF);
        } else {
            System.out.println("Errore in start.");
        }

    }

    private void expr() {
        if (look.tag == Tag.NUM || look.tag == '(') {
            term();
            exprp();

        } else {
            error("Errore in Expr.");
        }

    }

    private void exprp() {
        if (look.tag == '+' || look.tag == '-' || look.tag == Tag.EOF || look.tag == ')') {
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
        } else {
            System.out.println("Errore in exprp. Hai dimenticato un - o un + ?");
        }
    }

    private void term() {
        if (look.tag == Tag.NUM || look.tag == '(') {
            fact();
            termp();

        } else {
            error("Errore in Term.");
        }

    }

    private void termp() {
        if (look.tag == '*' || look.tag == '/' || look.tag == '+' || look.tag == '-' || look.tag == Tag.EOF || look.tag == ')') {
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
        } else {
            System.out.println("Errore in termp. Hai dimentiato un * o / ?");
        }
    }

    private void fact() {

        if (look.tag == Tag.NUM) {
            move();
        } else {
            if (look.tag != '(') {
                error("Errore in FACT. Mi aspettavo una '(' o un NUM");
            }
            move();
            expr();
            if (look.tag != ')') {
                error("Errore in FACT. Mi aspettavo una ')'");
            } else {
                move();
            }

        }
    }

    public static void main(String[] args) {
        Lexer lex = new Lexer();
        String path = "C:\\Users\\maico\\Desktop\\LFT\\LFT\\Esercizio3\\Esercizio3_1\\Test.txt"; // il percorso del file da leggere
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            Parser parser = new Parser(lex, br);
            parser.start();
            System.out.println("Input OK");
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
