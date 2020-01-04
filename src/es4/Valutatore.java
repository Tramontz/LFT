/*  Laboratorio Linguaggi Formali e Traduttori 17/18  
 *
 *  Autore: Stracci Maicol
 *  
 *  Matricola: 821115 
 *  
 *  Esercizio: Esercizio 4
 *  
 *  Descrizione: Classe Valutatore
 *               Valutatore di espressioni semplici
 *
 *  Output previsto: Stampa il risultato dell' espressione ricevuta da file in input. 
 *               
 */
package Esercizio4;

import java.io.*;

public class Valutatore {

    private Lexer lex;
    private BufferedReader pbr;
    private Token look;

    public Valutatore(Lexer l, BufferedReader br) {
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
        int expr_val = 0;       // Risultato numerico della espressione 

        expr_val = expr();
        match(Tag.EOF);

        System.out.println("Risultato della espressione: " + expr_val);

    }

    private int expr() {
        int term_val, exprp_val = 0;
        if (look.tag == '(' || look.tag == Tag.NUM) {
            term_val = term();
            exprp_val = exprp(term_val);
            
        } else {
            System.out.println("Errore in Expr. Simbolo: " + look);
        }
        return exprp_val;
    }

    private int exprp(int exprp_i) {
        int term_val, exprp_val = 0;
        if (look.tag == '+' || look.tag == '-') {
            switch (look.tag) {
                case '+':
                    match('+');
                    term_val = term();
                    exprp_val = exprp(exprp_i + term_val);
                    break;
                case '-':
                    match('-');
                    term_val = term();
                    exprp_val = exprp(exprp_i - term_val);
                    break;

            }
            
        } else if (look.tag == Tag.EOF || look.tag == ')') {
            exprp_val = exprp_i;
        } else {
            System.out.println("Errore in exprp. Simbolo: " + look);
        }
        return exprp_val;
    }

    private int term() {
        int termp_i, term_val = 0;
        if (look.tag == '(' || look.tag == Tag.NUM) {
            termp_i = fact();
            term_val = termp(termp_i);
            
        } else {
            System.out.println("Errore in term. Simbolo: " + look);
        }
        return term_val;
    }

    private int termp(int termp_i) {
        int fact_val, termp_val = 0, termp1_i;
        if (look.tag == '*' || look.tag == '/') {
            switch (look.tag) {
                case '*':
                    match('*');
                    fact_val = fact();
                    termp_val = termp(termp_i * fact_val);
                    break;
                case '/':
                    match('/');
                    fact_val = fact();
                    termp_val = termp(termp_i / fact_val);
                    break;
            }
           
        } else if (look.tag == '+' || look.tag == '-' || look.tag == '(' || look.tag == Tag.NUM || look.tag == Tag.EOF || look.tag == ')') {
            termp_val = termp_i;
        } else {
            System.out.println("Errore in termp. Simbolo: " + look);
        }
        return termp_val;
    }

    private int fact() {
        int fact_val = 0;
        if (look.tag == Tag.NUM || look.tag == '(') {
            if (look.tag == Tag.NUM) {
                NumberTok x = (NumberTok) look; // Sapendo che sto leggendo un NumberTok faccio un cast su look cosi da poter utilizzare 
                fact_val = x.n;                 // la variabile x della classe NumberTok, la quale contiene il valore numerico del numero in input.
                move();

            } else if (look.tag == '(') {
                match('(');
                fact_val = expr();
                match(')');
            }
           
        } else {
            System.out.println("Errore in fact. Simbolo: " + look);
        }
         return fact_val;    // Ritorno il valore numerico del numero singolo o della espressione fra parentesi
    }

    public static void main(String[] args) {
        Lexer lex = new Lexer();
        String path = "C:\\Users\\maico\\Desktop\\LFT\\LFT\\Esercizio4\\Test.txt"; // il percorso del file da leggere
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            Valutatore valutatore = new Valutatore(lex, br);
            valutatore.start();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
