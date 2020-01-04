package es2;

import java.io.*; 
import java.util.*;

public class Lexer {

	public static int line = 1;
	private char peek = ' ';
    String identificatore = "";
    String Numero = "";
    boolean status = true;
    
	private void readch(BufferedReader br) {
		try {
			peek = (char) br.read();
		} catch (IOException exc) {
			peek = (char) -1; // ERROR
		}
	}

	public Token lexical_scan(BufferedReader br) {
		while (peek == ' ' || peek == '\t' || peek == '\n'  || peek == '\r') {
			if (peek == '\n') line++;
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
                            + " after & : "  + peek );
                    return null;
                }
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
                peek = ' ';
                return Token.assign;

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
            case '_': //Id puo' contenere  '_' ma non alla prima posizione, se identificatore e' vuoto allora lancio un errore
                if (identificatore == "") {
                    System.err.println("Erroneous character" + " after = : " + peek);
                    return null;
                } else {
                    identificatore = identificatore + peek; // altrimenti aggiungo underscore al nome dell' identificatore
                }          
            case (char)-1:
                return new Token(Tag.EOF);

            default:
                if (Character.isLetter(peek)) {

                    switch (identificatore) { // Cerco se identificatore corrisponde ad una delle word
                        case "cond":
                            identificatore = ""; // In caso positivo pulisco la variabile sulla quale tengo l' identificatore
                            return Word.cond;  // e ritorno la word corrispondente   
                            
                        case "when":
                            identificatore = "";
                            return Word.when;
                            
                        case "then":
                            identificatore = "";
                            return Word.then;

                        case "else":
                            identificatore = "";
                            return Word.elsetok;

                        case "while":
                            identificatore = "";
                            return Word.whiletok;
                            
                        case "do":
                            identificatore = "";
                            return Word.dotok;
                            
                        case "seq":
                            identificatore = "";
                            return Word.seq;

                        case "print":
                            identificatore = "";
                            return Word.print;

                        case "read":
                            identificatore = "";
                            return Word.read;
                    }
                    if (Character.isDigit(peek) && identificatore != "") { //L' identificatore puo' contenere numeri ma solo dopo almeno 1 lettera, quindi l' identificatore non deve essere vuoto
                    	identificatore = identificatore + peek;
                    	readch(br);
                    	while (Character.isDigit(peek) || Character.isLetter(peek) || peek == '_') { // "|| peek == '_'" perche' l' identificatore puo' contenere underscore al suo interno
                    		identificatore = identificatore + peek;
                    		readch(br);
                    	}
                    }
                    	String x = identificatore; // Salvo l' identificatore generato, lo azzero e ritorno una nuova Word
                    	identificatore = "";
                    	return new Word(Tag.ID, x);
                } else if (Character.isDigit(peek)) {
                while (Character.isDigit(peek)) {
                    Numero = Numero + peek;
                    readch(br);
                }
                String x = Numero;
                Numero = "";
                return new NumberTok(Integer.parseInt(x)); // Da stringa a numero intero e creo un nuovo NumberTok) {

	// ... gestire il caso dei numeri ... //

                } else {
                        System.err.println("Erroneous character: " 
                                + peek );
                        return null;
                }
         }
	}
		
    public static void main(String[] args) {
        Lexer lex = new Lexer();
        String path = "...path..."; // il percorso del file da leggere
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            Token tok;
            do {
                tok = lex.lexical_scan(br);
                System.out.println("Scan: " + tok);
            } while (tok.tag != Tag.EOF);
            br.close();
        } catch (IOException e) {e.printStackTrace();}    
    }
}