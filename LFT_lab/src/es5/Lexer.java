/*  Laboratorio Linguaggi Formali e Traduttori 17/18  
 *
 *  Autore: Stracci Maicol
 *  
 *  Matricola: 821115 
 *  
 *  Esercizio: Esercizio 5
 *  
 *  Descrizione: Classe Lexer
 *               Traduttore per programmi scritti in linguaggio "P".
 *
 *  Output previsto: File output.j utilizzabile dal programma Jasmin per la 
 *                   transormazione in un file .class eseguibile dalla JVM.
 *
 */
package Esercizio5;

import java.io.*;
import java.util.*;

public class Lexer {

    public static int line = 1;
    private char peek = ' ';
    String identificatore = "";
    String Numero = "";

    private void readch(BufferedReader br) {
        try {
            peek = (char) br.read();
        } catch (IOException exc) {
            peek = (char) -1; // ERROR
        }
    }

    public Token lexical_scan(BufferedReader br) {

        while (peek == ' ' || peek == '\t' || peek == '\n' || peek == '\r') {
            if (peek == '\n') {
                line++;
            }
            readch(br);
        }

        if (peek == '/') {
            boolean status = true;
            readch(br);
            if (peek == '*') {
                readch(br);
                while (status) {

                    if (peek == '*') {
                        readch(br);
                        if (peek == '/') {
                            status = false;
                            peek = ' ';
                        } else if (peek == (char) -1) {
                            status = false;
                            new Token(Tag.EOF);
                        }

                    } else {
                        readch(br);
                        if (peek == (char) -1) {
                            status = false;
                        }
                    }
                }

                while (peek == ' ' || peek == '\t' || peek == '\n' || peek == '\r') {
                    if (peek == '\n') {
                        line++;
                    }
                    readch(br);
                }
            } else if (peek == '/') {
                readch(br);
                while (status) {
                    if (peek == '\n') {
                        status = false;
                        peek = ' ';
                    } else if (peek == (char) -1) {
                        status = false;
                        new Token(Tag.EOF);
                    }
                    readch(br);
                }
            } else {

                return Token.div;
            }

        }

        switch (peek) {
            case '!':
                peek = ' ';
                return Token.not;

            // ... gestire i casi di +, -, *, /, ;, (, ), {, } ... //
            case '+':
                peek = ' ';
                return Token.plus;
            case '-':
                peek = ' ';
                return Token.minus;
            case '*':
                peek = ' ';
                return Token.mult;
            case ';':
                peek = ' ';
                return Token.semicolon;
            case '(':
                peek = ' ';
                return Token.lpt;
            case ')':
                peek = ' ';
                return Token.rpt;
            case '&':
                readch(br);
                if (peek == '&') {
                    peek = ' ';
                    return Word.and;
                } else {
                    System.err.println("Erroneous character"
                            + " after & : " + peek);
                    return null;
                }

            // ... gestire i casi di ||, <, >, <=, >=, ==, <> ... //
            case '|':
                readch(br);
                if (peek == '|') {
                    peek = ' ';
                    return Word.or;
                } else {
                    System.err.println("Erroneous character"
                            + " after | : " + peek);
                    return null;
                }
            case '=':
                readch(br);
                if (peek == '=') {
                    peek = ' ';
                    return Word.eq;
                } else {
                    return Token.assign;
                }

            case '<':
                readch(br);
                if (peek == '=') {
                    peek = ' ';
                    return Word.le;
                } else if (peek == '>') {
                    peek = ' ';
                    return Word.ne;
                } else {
                    return Word.lt;
                }
            case '>':
                readch(br);
                if (peek == '=') {
                    peek = ' ';
                    return Word.ge;
                } else {
                    return Word.gt;
                }
            case '_':
                if (identificatore == "") {
                    System.err.println("Erroneous character"
                            + " after = : " + peek);
                    return null;
                } else {
                    identificatore = identificatore + peek;

                }
            case (char) -1:
                return new Token(Tag.EOF);

            default:
                if (Character.isLetter(peek)) {
                    while (Character.isLetter(peek)) {
                        identificatore = identificatore + peek;
                        readch(br);
                    }
                    switch (identificatore) {
                        case "if":
                            identificatore = "";
                            return Word.iftok;

                        case "then":
                            identificatore = "";
                            return Word.then;

                        case "else":
                            identificatore = "";
                            return Word.elsetok;

                        case "for":
                            identificatore = "";
                            return Word.fortok;

                        case "do":
                            identificatore = "";
                            return Word.dotok;

                        case "print":
                            identificatore = "";
                            return Word.print;

                        case "read":
                            identificatore = "";
                            return Word.read;

                        case "begin":
                            identificatore = "";
                            return Word.begin;

                        case "end":
                            identificatore = "";
                            return Word.end;
                    }
                    if ((Character.isDigit(peek) && identificatore != "") || peek == '_') {
                        identificatore = identificatore + peek;
                        readch(br);
                        while (Character.isDigit(peek) || Character.isLetter(peek) || peek == '_') {
                            identificatore = identificatore + peek;
                            readch(br);
                        }

                    }
                    String x = identificatore;
                    identificatore = "";
                    return new Word(Tag.ID, x);

                } else if (Character.isDigit(peek)) {
                    while (Character.isDigit(peek)) {
                        Numero = Numero + peek;
                        readch(br);
                    }
                    String x = Numero;
                    Numero = "";
                    return new NumberTok(Integer.parseInt(x));

                } else {
                    System.err.println("Erroneous character: "
                            + peek);
                    return null;
                }
        }

    }

    public static void main(String[] args) {
        Lexer lex = new Lexer();
        String path = "C:\\Users\\Maicol\\Desktop\\Programmazione\\LFT\\Esercizio2\\Test.txt"; // il percorso del file da leggere
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            Token tok;
            do {
                tok = lex.lexical_scan(br);
                System.out.println("Scan: " + tok);
            } while (tok.tag != Tag.EOF);
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
