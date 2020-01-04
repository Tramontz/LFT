/*  Laboratorio Linguaggi Formali e Traduttori 17/18  
 *
 *  Autore: Stracci Maicol
 *  
 *  Matricola: 821115 
 *  
 *  Esercizio: Esercizio 4
 *  
 *  Descrizione: Classe Token
 *               Valutatore di espressioni semplici
 *
 *  Output previsto: Stampa il risultato dell' espressione ricevuta da file in input. 
 *               
 */
package Esercizio4;


public class Token {
    public final int tag;
    public Token(int t) { tag = t; }
    public String toString() {return "<" + tag + ">";}
    public static final Token
        not = new Token('!'),
        lpt = new Token('('),
        rpt = new Token(')'),
        plus = new Token('+'),
        minus = new Token('-'),
        mult = new Token('*'),
        div = new Token('/'),
        semicolon = new Token(';'),
        assign = new Token('=');
}