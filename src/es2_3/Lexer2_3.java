package es2_3;

/*
. Estendere il metodo lexical_scan in modo tale che possa trattare la presenza di
commenti nel file di input. I commenti possono essere scritti in due modi:
• commenti delimitati con / * e * /
• commenti che iniziano con // e che terminano con un a capo oppure con EOF.
I commenti devono essere ignorati dal programma per l’analisi lessicale; in altre parole, per le
parti dell’input che contengono commenti, non deve essere generato nessun token. Ad esempio,
consideriamo l’input seguente.
*/

import java.io.*;

public class Lexer2_3 {
	public static int line = 1;
	private char peek = ' ';

	private void readch(BufferedReader br) {
		try {
			peek = (char) br.read();
		} catch (IOException exc) {
			peek = (char) -1; // ERROR
		}
	}

	public Token lexical_scan(BufferedReader br){
		while (peek == ' ' || peek == '\t' || peek == '\n' || peek == '\r') {
			if (peek == '\n')
				line++;
			readch(br);
		}
		while (peek == '/') {
			readch(br);
			if (peek == '*') {
				readch(br);
				while (peek != '/') {
					while (peek != '*') {
						readch(br);
					}
					readch(br);
				}
				readch(br);
				while (peek == ' ' || peek == '\n' || peek == '\t' || peek == '\r') {
					if (peek == '\n')
						line++;
					readch(br);
				}
			} else if (peek == '/') {
				while (peek != '\n' && peek != (char) -1) {
					readch(br);
				}
				readch(br);
				line++;
			} else {
				return Token.div;
			}
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
		case ';':
			peek = ' ';
			return Token.semicolon;
		case '&':
			readch(br);
			if (peek == '&') {
				peek = ' ';
				return Word.and;
			} else {
				System.err.println("Erroneous character" + " after & : " + peek);
				return null;
			}
		case '|':
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
				String tok = "";
				while (Character.isLetter(peek) || Character.isDigit(peek)) {
					tok = tok + peek;
					readch(br);
				}
				switch (tok) {
				case "cond":
					return Word.cond;
				case "when":
					return Word.when;
				case "then":
					return Word.then;
				case "else":
					return Word.elsetok;
				case "while":
					return Word.whiletok;
				case "do":
					return Word.dotok;
				case "seq":
					return Word.seq;
				case "print":
					return Word.print;
				case "read":
					return Word.read;
				default:
					return new Word(257, tok);
				}
			} else if (Character.isDigit(peek)) {
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

	public static void main(String[] args) {
		Lexer2_3 lex = new Lexer2_3();
		String path = "E:\\Workspaces\\LFT_lab\\src\\es2_3\\Es2_3.txt"; // il percorso del file da leggere
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
