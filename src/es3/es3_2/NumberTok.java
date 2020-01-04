/*  Laboratorio Linguaggi Formali e Traduttori 17/18  
 *
 *  Autore: Stracci Maicol
 *  
 *  Matricola: 821115 
 *  
 *  Esercizio: Esercizio 3.2
 *  
 *  Descrizione: Classe NumberTok
 *               Analizzatore sintattico di un file in input
 *
 *  Output previsto: Stampa elenco di Token analizzati dal file in input. 
 *               
 */
package Esercizio3_2;

public class NumberTok extends Token {

    int n = 0;

    public NumberTok(int s) {
        super(Tag.NUM);
        n = s;
    }

    public String toString() {
        return "<" + Tag.NUM + ", " + n + ">";
    }

}
