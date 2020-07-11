package es2;

/*
Gli identificatori corrispondono all’espressione regolare [a - zA - Z][a - zA - Z0 - 9]
e i numeri corrispondono all’espressione regolare 0|[1 - 9][0 - 9]

L’analizzatore lessicale dovra ignorare tutti i caratteri riconosciuti come “spazi” (incluse le 
tabulazioni e i ritorni a capo), ma dovra segnalare la presenza di caratteri illeciti, quali ad esempio # o @.
*/

import java.io.*;

public class Lexer {
	public static int line = 1;
	private char peek = ' ';

	private void readch(BufferedReader br) {
		try {
			peek = (char) br.read();
		} catch (IOException exc) {
			peek = (char) -1; // ERROR
		}
	}

	public Token lexical_scan(BufferedReader br) {
		while (peek == ' ' || peek == '\t' || peek == '\n' || peek == '\r') {
			if (peek == '\n')
				line++;
			readch(br);
		}
		switch (peek) {
		case '!':
			peek = ' ';
			return Token.not;
		case '(':
			peek = ' ';
			return Token.lpt;
		case ')':
			peek = ' ';
			return Token.rpt;
		case '{':
			peek = ' ';
			return Token.lpg;
		case '}':
			peek = ' ';
			return Token.rpg;
		case '+':
			peek = ' ';
			return Token.plus;
		case '-':
			peek = ' ';
			return Token.minus;
		case '*':
			peek = ' ';
			return Token.mult;
		case '/':
			peek = ' ';
			return Token.div;
		case ';':
			peek = ' ';
			return Token.semicolon;
		// ---------//
		case '&':					//riconosco & come carattere valido solo se rappresenta l'operazione AND (&&)
			readch(br);
			if (peek == '&') {
				peek = ' ';
				return Word.and;
			} else {
				System.err.println("Erroneous character" + " after & : " + peek);
				return null;
			}
		case '|':					//riconosco || come carattere valido solo se rappresenta l'operazione OR (||)
			readch(br);
			if (peek == '|') {
				peek = ' ';
				return Word.or;
			} else {
				System.err.println("Erroneous character" + " after | : " + peek);
				return null;
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
		case '=':
			readch(br);
			if (peek == '=') {
				peek = ' ';
				return Word.eq;
			} else {
				return Token.assign;
			}
		case (char) -1:
			return new Token(Tag.EOF);
		default:
			if (Character.isLetter(peek)) {
				// ... gestire il caso degli identificatori e delle parole chiave //
				String tok = "";
				while (Character.isLetter(peek) || Character.isDigit(peek)) { //riconosco tutta la sequenza dell'identificatore (char || char+digit)
					tok = tok + peek;
					readch(br);
				}
				switch (tok) {
				case "cond":
					// peek = ' ';
					return Word.cond;
				case "when":
					// peek = ' ';
					return Word.when;
				case "then":
					// peek = ' ';
					return Word.then;
				case "else":
					// peek = ' ';
					return Word.elsetok;
				case "while":
					// peek = ' ';
					return Word.whiletok;
				case "do":
					// peek = ' ';
					return Word.dotok;
				case "seq":
					// peek = ' ';
					return Word.seq;
				case "print":
					// peek = ' ';
					return Word.print;
				case "read":
					// peek = ' ';
					return Word.read;
				default:
					// peek = ' ';
					return new Word(257, tok);	//se non viene riconosciuto come un token noto, lo ritengo un identificatore
				}
			} else if (Character.isDigit(peek)) {
				// ... gestire il caso dei numeri ... //
				String num = "";
				while (Character.isDigit(peek)) {
					num = num + peek;
					readch(br);
				}
				return new NumberTok(Integer.parseInt(num));
			} else {
				System.err.println("Erroneous character: " + peek);
				return null;
			}
		}
	}

//--------------//
	public static void main(String[] args) {
		Lexer lex = new Lexer();
		String path = "E:\\Workspaces\\LFT_lab\\src\\es2\\Es2.txt"; // il percorso del file da leggere
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