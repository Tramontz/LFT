package es3.es3_1;


/*
Esercizio 3.1. Si scriva un analizzatore sintattico a discesa ricorsiva che parsifichi espressioni
aritmetiche molto semplici, scritte in notazione infissa, e composte soltanto da numeri non negativi
(ovvero sequenze di cifre decimali), operatori di somma e sottrazione + e -, operatori di
moltiplicazione e divisione * e /, simboli di parentesi ( e ).
*/

import java.io.*;

public class Parser {
	private Lexer lex;
	private BufferedReader pbr;
	private Token look;

	public Parser(Lexer lexer, BufferedReader breader) {
		lex = lexer;
		pbr = breader;
		move();
	}

	void move() {
		look = lex.lexical_scan(pbr);
		System.out.println("token = " + look);
	}

	void error(String s) {
		throw new Error("near line " + Lexer.line + ": " + s);
	}

	void match(int tag) {
		if (look.tag == tag) {
			if (look.tag != Tag.EOF)
				move();
		} else
			error("syntax error");
	}

	public void start() {
		if (look.tag == Tag.NUM || look.tag == '(') {
			expr();
			match(Tag.EOF);
		} else
			error("Error in start");
	}

	private void expr() {
		if (look.tag == Tag.EOF) {
			error("Error in expr");
		} else {
			term();
			exprp();
		}
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
		case Tag.EOF:
		case ')':
			break;
		default:
			error("Error in exprp");
		}
	}

	private void term() {
		if (look.tag == '(' || look.tag == Tag.NUM) {
			fact();
			termp();
		} else {
			error("Error in term");
		}
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
		case Tag.EOF:
		case ')':
		case '+':
		case '-':
			break;
		default:
			error("Error in termp");
		}
	}

	private void fact() {
		switch (look.tag) {
		case '(':
			move();
			expr();
			match(')');
			break;
		case Tag.NUM:
			move();
			break;
		default:
			error("Error in fact");
		}
	}

	public static void main(String[] args) {
		Lexer lex = new Lexer();
		String path = "\\Workspaces\\LFT_lab\\src\\es3\\es3_1\\Es3_1.txt"; // il percorso del file da leggere
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			Parser parser = new Parser(lex, br);
			parser.start();
			System.out.println("Input OK");
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
