/*  Laboratorio Linguaggi Formali e Traduttori 17/18  
 *
 *  Autore: Stracci Maicol
 *  
 *  Matricola: 821115 
 *  
 *  Esercizio: Esercizio 5
 *  
 *  Descrizione: Classe NumberTok
 *               Traduttore per programmi scritti in linguaggio "P".
 *
 *  Output previsto: File output.j utilizzabile dal programma Jasmin per la 
 *                   transormazione in un file .class eseguibile dalla JVM.
 *
 */
package Esercizio5;

public class NumberTok extends Token {
    int n = 0;
    public NumberTok(int s) { super(Tag.NUM); n = s; }
    public String toString() { return "<" + Tag.NUM + ", " + n + ">"; }
    
}
