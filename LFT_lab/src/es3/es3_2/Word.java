/*  Laboratorio Linguaggi Formali e Traduttori 17/18  
 *
 *  Autore: Stracci Maicol
 *  
 *  Matricola: 821115 
 *  
 *  Esercizio: Esercizio 3.2
 *  
 *  Descrizione: Classe Word
 *               Analizzatore sintattico di un file in input
 *
 *  Output previsto: Stampa elenco di Token analizzati dal file in input. 
 *               
 */
package Esercizio3_2;

public class Word extends Token {

    public String lexeme = "";

    public Word(int tag, String s) {
        super(tag);
        lexeme = s;
    }

    public String toString() {
        return "<" + tag + ", " + lexeme + ">";
    }
    public static final Token iftok = new Word(Tag.IF, "if"),
            then = new Word(Tag.THEN, "then"),
            elsetok = new Word(Tag.ELSE, "else"),
            fortok = new Word(Tag.FOR, "for"),
            dotok = new Word(Tag.DO, "do"),
            print = new Word(Tag.PRINT, "print"),
            read = new Word(Tag.READ, "read"),
            begin = new Word(Tag.BEGIN, "begin"),
            end = new Word(Tag.END, "end"),
            or = new Word(Tag.OR, "||"),
            and = new Word(Tag.AND, "&&"),
            lt = new Word(Tag.RELOP, "<"),
            gt = new Word(Tag.RELOP, ">"),
            le = new Word(Tag.RELOP, "<="),
            ge = new Word(Tag.RELOP, ">="),
            eq = new Word(Tag.RELOP, "=="),
            ne = new Word(Tag.RELOP, "<>");
}
