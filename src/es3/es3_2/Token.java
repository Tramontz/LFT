/*  Laboratorio Linguaggi Formali e Traduttori 17/18  
 *
 *  Autore: Stracci Maicol
 *  
 *  Matricola: 821115 
 *  
 *  Esercizio: Esercizio 3.2
 *  
 *  Descrizione: Classe Token
 *               Analizzatore sintattico di un file in input
 *
 *  Output previsto: Stampa elenco di Token analizzati dal file in input. 
 *               
 */
package Esercizio3_2;

public class Token {

    public final int tag;

    public Token(int t) {
        tag = t;
    }

    public String toString() {
        return "<" + tag + ">";
    }
    public static final Token not = new Token('!'),
            lpt = new Token('('),
            rpt = new Token(')'),
            plus = new Token('+'),
            minus = new Token('-'),
            mult = new Token('*'),
            div = new Token('/'),
            semicolon = new Token(';'),
            assign = new Token('=');
}
