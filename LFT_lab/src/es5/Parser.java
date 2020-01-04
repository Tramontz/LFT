/*  Laboratorio Linguaggi Formali e Traduttori 17/18  
 *
 *  Autore: Stracci Maicol
 *  
 *  Matricola: 821115 
 *  
 *  Esercizio: Esercizio 5
 *  
 *  Descrizione: Classe Parser
 *               Traduttore per programmi scritti in linguaggio "P".
 *
 *  Output previsto: File output.j utilizzabile dal programma Jasmin per la 
 *                   transormazione in un file .class eseguibile dalla JVM.
 *
 */
package Esercizio5;
        
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
	    if (look.tag != Tag.EOF) move();
	} else error("syntax error");
    }

    public void start() {  
	 
	expr();
	match(Tag.EOF);
	
    }

    private void expr() {  
        if(look.tag == 256 || look.tag == 40){
            term();
            if(look.tag == Token.plus.tag || look.tag == Token.minus.tag || look.tag == ')' || look.tag == Tag.EOF){
                exprp();
            }
            else error("Errore in EXPR. Mi aspettavo un '+' o un '-'");
        }
        else error("Errore in EXPR. Mi aspettavo una '(' o un NUM");
       
        
    }

    private void exprp() {
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
    }

    private void term() { 
        if(look.tag == 256 || look.tag == 40){
            fact();
            if(look.tag == Token.mult.tag || look.tag == Token.div.tag || look.tag == Token.plus.tag || look.tag == Token.minus.tag || look.tag == ')' || look.tag == Tag.EOF){
                termp();
            }
            else error("Errore in TERM. Mi aspettavo un '*,/,+,-' o 'EXPR'");
        }
        else error("Errore in TERM. Mi aspettavo una '(' o un NUM");
        
        
        
    
    }
    
    private void termp() { 
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
        
    }
    
    private void fact() { 
        
        if(look.tag == 256){
            move();
        }
        else{
            if (look.tag != '('){
                error("Errore in FACT. Mi aspettavo una '(' o un NUM");
            }
            move();
            expr();
            if (look.tag != ')'){
                error("Errore in FACT. Mi aspettavo una ')'");
            }
            else move();
            
        }
    }
		
    public static void main(String[] args) {
        Lexer lex = new Lexer();
        String path = "C:\\Users\\Maicol\\Desktop\\Programmazione\\LFT\\Esercizio3\\Esercizio31\\test.txt"; // il percorso del file da leggere
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            Parser parser = new Parser(lex, br); 
            parser.start();
            System.out.println("Input OK");
            br.close();
        } catch (IOException e) {e.printStackTrace();}    
    }
}