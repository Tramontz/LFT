package es5_2;

/*Modificare la grammatica del linguaggio P per permettere l'utilizzo dei connettivi logici
&& (congiunzione), || (disgiunzione) e ! (negazione) in notazione prefissa nelle
espressioni booleane, utilizzando le parentesi tonde per delimitare ogni sottoespressione.
� Segue un esempio di un input che corrisponde alla grammatica con connettivi logici:
(do
(read x)
(cond (|| (< x 10) (&& (> x 20) (!(> x 30))))
 (print (+ x 100))
)
)
� Si scriva uno SDT e si estenda il traduttore con un'implementazione corrispondente.
� Suggerimento: Cercare di utilizzare la valutazione cortocircuitata per le espressioni
booleane, come illustrato nelle lezioni di teoria
*/


/*CALCOLO FIRST/FOLLOW/INSIEME GUIDA
1 PROG->STAT eof
2 STATLIST->STAT STATLIST_P
3 STATLIST_P->STAT STATLIST_P
4 STATLIST_P-> eps
5 STAT->( STAT_P )
6 STAT_P-> id EXPR
7 STAT_P-> cond BEXPR STAT ELSEOPT
8 STAT_P-> while BEXPR STAT
9 STAT_P-> do STATLIST
10 STAT_P-> print EXPRLIST
11 STAT_P-> read id
12 ELSEOPT-> ( else STAT )
13 ELSEOPT-> eps
14 BEXPR-> ( BEXPR_P )
15 BEXPR-> ( BEXPR )
16 BEXPR-> (&&( BEXPR ))
17 BEXPR-> (||( BEXPR ))
18 BEXPR-> (!( BEXPR ))
19 BEXPR_P-> relop EXPR EXPR
20 EXPR-> num 
21 EXPR->id
22 EXPR->( EXPR_P )
23 EXPR_P -> + EXPRLIST
24 EXPR_P -> - EXPR EXPR
25 EXPR_P -> * EXPR_LIST
26 EXPR_P -> / EXPR EXPR
27 EXPR_LIST-> EXPR EXPR_LIST_P
28 EXPR_LIST_P->EXPR EXPR_LIST_P
29 EXPR_LIST_P->eps

*FIRST SET
*
PROG	(
STATLIST	(
STATLIST_P	 (
STAT	(
STAT_P	id cond while do print read
ELSEOPT	(
BEXPR	( && || !
BEXPR_P	relop
EXPR	num id(
EXPR_P	+ - * /
EXPR_LIST	num id (
EXPR_LIST_P	num id (
*
*
*FOLLOW SET
*
PROG	-|
STATLIST	)
STATLIST_P	)
STAT	EOF ( )
STAT_P	)
ELSEOPT	)
BEXPR	()
BEXPR_P	)
EXPR	) numid (
EXPR_P	)
EXPR_LIST	)
EXPR_LIST_P	)

*
*GUIDA
*
1	(
2	(
3	(
4	)
5	(
6	id
7	cond
8	while
9	do
10	print
11	read
12	(
13	)
14	(
15	(
16	&&
17	||
18	!
19	relop
20	num
21	id
22	(
23	+
24	-
25	*
26	/
27	numid(
28	numid(
29	)
*
*/


import java.io.*;

public class Translator {
	private Lexer lex;
	private BufferedReader pbr;
	private Token look;

	SymbolTable st = new SymbolTable();
	CodeGenerator code = new CodeGenerator();
	int count = 0;

	public Translator(Lexer l, BufferedReader br) {
		lex = l;
		pbr = br;
		move();
	}

	void move() {
		look = lex.lexical_scan(pbr);
		System.err.println("token = " + look);
	}

	void error(String s) {
		throw new Error("near line " + Lexer.line + ": " + s);
	}

	void match(int t) {
		if (look.tag == t) {
			if (look.tag != Tag.EOF)
				move();
		} else
			error("syntax error, found: " + look);
	}

	/*
	 * metodo di partenza GUI (
	 */
	public void prog() {
		int lnext_prog = code.newLabel(); // S.next = newlabel()
		if (look.tag == '(') {
			stat(lnext_prog);
			code.emitLabel(lnext_prog);
			if (look.tag == Tag.EOF) {
				match(Tag.EOF);
			}
		} else {
			error("Error in Prog, found: " + look);
		}
		try {
			code.toJasmin();

		} catch (java.io.IOException e) {
			System.out.println("IO error\n");
		}
		;
	}

	/*
	 * GUIDA: '('
	 * 
	 */
	public void stat(int lnext) {
		if (look.tag == '(') {
			match(look.tag);
			stat_p(lnext);
			match(')');
		} else
			error("Error in stat, found: " + look);
	}

	/*
	 * il metodo stat_p() si occupa di guardare le istruzioni chiave della
	 * grammatica in oggetto GUIDA:id cond while do print read ID:=<expr>* COND
	 * <bexpr>, <stat> <elseopt> WHILE <bexpr> <stat> DO(<statlist>) print
	 * <ExprList> read ID
	 * 
	 */

	public void stat_p(int lnext) {

		switch (look.tag) {
		case '=':
			match('=');
			if (look.tag == Tag.ID) {
				int next_id = code.newLabel();
				int id_addr = st.lookupAddress(((Word) look).lexeme); // controlla se l'ID è già assegnato a un
																		// indirizzo
				if (id_addr == -1) {
					id_addr = count; // count = 0
					st.insert(((Word) look).lexeme, count++); // inserisce in un nuovo indirizzo l'ID
				}
				match(Tag.ID);
				expr();
				code.emit(OpCode.istore, id_addr); // CodeGenerator emette l'istruzione assegnata
				code.emitLabel(next_id);
			} else {
				error("Error in grammar (stat) after ID, found " + look);
			}
			break;

		case Tag.PRINT:
			match(Tag.PRINT);
			exprlist();
			code.emit(OpCode.invokestatic, 1); // 1 == invoca la funzione print
			break;

		case Tag.READ:
			int next_read = code.newLabel();
			match(Tag.READ);
			if (look.tag == Tag.ID) {
				int read_id_addr = st.lookupAddress(((Word) look).lexeme); // controlla se l'ID è già assegnato a un
																			// indirizzo
				if (read_id_addr == -1) {
					read_id_addr = count;
					st.insert(((Word) look).lexeme, count++);
				}
				match(Tag.ID);
				code.emit(OpCode.invokestatic, 0); // 0 == invoca la funzione read
				code.emit(OpCode.istore, read_id_addr);
				code.emitLabel(next_read);
			} else {
				error("Error in grammar (stat) after read( with " + look);
			}
			break;
		/*
		 * init_case: lable di partenza per il valutatore, che ci sia 'else' oppure no,
		 * dopo la valutazione il programma andrà a next_case
		 */
		case Tag.COND:
			int init_case = code.newLabel();
			int next_case = code.newLabel();

			match(Tag.COND);
			bexpr(init_case);
			stat(init_case);
			code.emit(OpCode.GOto, next_case);
			code.emitLabel(init_case);
			elseopt(init_case);
			code.emit(OpCode.GOto, next_case);
			code.emitLabel(next_case);
			break;

		case Tag.WHILE:

			match(Tag.WHILE);
			int lnext_stat_while = code.newLabel(); // begin = newlabel() || b.true
			int end_while = code.newLabel(); // B.false
			code.emitLabel(lnext_stat_while);
			bexpr(end_while);
			stat(lnext_stat_while);
			code.emit(OpCode.GOto, lnext_stat_while);
			code.emitLabel(end_while);
			break;

		case Tag.DO:
			match(Tag.DO);
			statlist(lnext);
			break;
		}
	}

	/*
	 * Guida: '('
	 * Guida(eps): ')'
	 */
	public void statlist_p(int lnext) {
		if (look.tag == '(') {
			// match(look.tag);
			stat(lnext);
			statlist_p(lnext);
		} else if (look.tag == ')') {

		} // do notthing, epsilon

		else {
			error("Error in statlist_p, found " + look);
		}
	}

	/*
	 * * Guida: '('
	 */
	public void statlist(int lnext) {
		if (look.tag == '(') { // ;
			stat(lnext);
			statlist_p(lnext);
		} else
			error("Error in statlist, found: " + look);
	}

	/*
	 * gestisce la keyword ELSE GUIDA '(' GUIDA(eps): ')'
	 */
	public void elseopt(int lnext) {
		if (look.tag == '(') {
			match(look.tag);
			if (look.tag == Tag.ELSE) {
				match(look.tag);
				stat(lnext);
				if (look.tag != ')') { // )
					error("Erroneous character after elseopt, expected ) but found: " + look);
				} else
					match(look.tag);
			} else
				error("Error in Else, found: " + look);
		} else if (look.tag == ')') {
			// do notthing, epsilon
		}
	}

	/*
	 * GUIDA '('
	 * 
	 * MODIFICA DELLA GRAMMATICA:
	 * BEXPR-> ( BEXPR_P )
	 * BEXPR-> ( BEXPR )
	 * BEXPR-> (&&( BEXPR ))
	 * BEXPR-> (||( BEXPR ))
	 * BEXPR-> (!( BEXPR ))
	 */
	public void bexpr(int lnext) {
		int next_when = code.newLabel(); // B.false = newlabel()
		if (look.tag == '(') {
			match(look.tag);
			switch (look.tag) {
			case Tag.AND:
				match(Tag.AND);
				bexpr(lnext);
				bexpr(lnext);
				if (look.tag != ')') {
					error("Erroneous character after AND, expected ) but found: " + look);
				} else
					match(look.tag);
				break;
				
			case Tag.OR:
				int next_or = code.newLabel(); //salto dell'OR in caso di falso, per far s� che non vada fuori dal COND ma salti all'OR successivo
				match(Tag.OR);
				bexpr(next_or);
				code.emit(OpCode.GOto, next_when);
				code.emitLabel(next_or);
				bexpr(lnext);
				code.emit(OpCode.GOto, next_when);
				code.emitLabel(next_when);
				if (look.tag != ')') {
					error("Erroneous character after OR, expected ) but found: " + look);
				} else
					match(look.tag);
				break;

			case Tag.NOT:
				match(Tag.NOT);
				bexpr(next_when);
				code.emit(OpCode.GOto, lnext);
				code.emitLabel(next_when);
				if (look.tag != ')') {
					error("Erroneous character after NOT, expected ) but found: " + look);
				} else
					match(look.tag);
				break;

			default:

				bexpr_p(next_when, lnext); // inverto lnext e next_when in modo che la condizione vera sia la prima ad essere analizzata dopo il compare
				if (look.tag != ')') {
					error("Erroneous character after bexpr, expected ) but found: " + look);
				} else {
					match(look.tag);
					code.emit(OpCode.GOto, lnext); // emit('goto' S1.next)
					code.emitLabel(next_when); // emitlabel(B.true)
				}
			}
		} else {
			error("Error in WhenItem, found: " + look);
		}
	}

	public void bexpr_p(int ltrue, int lfalse) {
		if (look.tag == Tag.RELOP) {
			if (look == Word.eq) {
				match(Tag.RELOP);
				expr();
				expr();
				code.emit(OpCode.if_icmpeq, ltrue); // emit('if_icmpep', B.true)
				// code.emit(OpCode.GOto, lfalse); // emit('goto' B.false)
			} else if (look == Word.ne) {
				match(Tag.RELOP);
				expr();
				expr();
				code.emit(OpCode.if_icmpne, ltrue); // emit('if_icmpne', B.true)
				// code.emit(OpCode.GOto, lfalse); // emit('goto' B.false)
			} else if (look == Word.le) {
				match(Tag.RELOP);
				expr();
				expr();
				code.emit(OpCode.if_icmple, ltrue); // emit('if_icmple', B.true)
				// code.emit(OpCode.GOto, lfalse); // emit('goto' B.false)
			} else if (look == Word.ge) {
				match(Tag.RELOP);
				expr();
				expr();
				code.emit(OpCode.if_icmpge, ltrue); // emit('if_icmpge', B.true)
				// code.emit(OpCode.GOto, lfalse); // emit('goto' B.false)
			} else if (look == Word.lt) {
				match(Tag.RELOP);
				expr();
				expr();
				code.emit(OpCode.if_icmplt, ltrue); // emit('if_icmplt', B.true)
				// code.emit(OpCode.GOto, lfalse); // emit('goto' B.false)
			} else if (look == Word.gt) {
				match(Tag.RELOP);
				expr();
				expr();
				code.emit(OpCode.if_icmpgt, ltrue); // emit('if_icmpgt', B.true)
				// code.emit(OpCode.GOto, lfalse); // emit('goto' B.false)
			}
		} else {
			error("Erroneous character in bexpr_p: invalid boolean expression");
		}
	}

	private void expr() {
		switch (look.tag) {
		case Tag.NUM:
			int costant = (((NumberTok) look).lexeme);
			code.emit(OpCode.ldc, costant); // emit(ldc(NUM))
			match(Tag.NUM);
			if (look.tag == '(') {
				error("Error in grammar expr, found: num(");
			}
			break;

		case Tag.ID:
			int id_addr = st.lookupAddress(((Word) look).lexeme);
			code.emit(OpCode.iload, id_addr); // emit(addr(id.lessema))
			match(Tag.ID);
			break;

		case '(':
			match(look.tag);
			expr_p();
			if (look.tag == ')') {
				match(look.tag);
			}
			break;
		}
	}

	private void expr_p() {
		switch (look.tag) {
		case '+':
			match('+');
			expr();
			exprlist();
			code.emit(OpCode.iadd);

			break;

		case '-':
			match('-');
			expr();
			expr();
			code.emit(OpCode.isub);
			break;
		case '*':
			match('*');
			exprlist();
			code.emit(OpCode.imul);
			break;

		case '/':
			match('/');
			expr();
			expr();
			code.emit(OpCode.idiv);
			break;

		default:
			error("Erroneous character in Exprp, found " + look);
		}
	}

	/*
	 * EXPR_LIST→ EXPR EXPR_LIST_P
	 * 
	 * GUIDA: num id (
	 */
	private void exprlist() {
		if (look.tag == '(' || look.tag == Tag.NUM || look.tag == Tag.ID) {
			// match(look.tag);
			expr();
			exprlist_p();
		} else {
			error("Error in exprlist, found " + look);
		}
	}

	/*
	 * EXPR_LIST_P→EXPR EXPR_LIST_P EXPR_LIST_P→ε
	 * 
	 * GUIDA num ID '(' GUIDA(eps): ')'
	 */
	private void exprlist_p() {
		if (look.tag == '(' || look.tag == Tag.NUM || look.tag == Tag.ID) {
			expr();
			exprlist_p();
		}
		if (look.tag == ')') {
			// epsilon, do notthing
		} else {
			error("Error in exprlist_p, found " + look);
		}
	}

	public static void main(String[] args) {
		Lexer lex = new Lexer();
		String absolutePath="E:\\Workspaces\\LFT_lab\\";
		//String path = absolutePath+"src\\es5\\test\\A.lft";
		//String path = absolutePath+"src\\es5\\test\\B.lft";
		//String path = absolutePath+"src\\es5\\test\\TestCond.lft";
		//String path = absolutePath+"src\\es5\\test\\TestCondNoElse.lft";
		//String path = absolutePath+"src\\es5\\test\\TestWhile.lft";
		//String path = absolutePath+"src\\es5\\test\\esempio_semplice.lft";
		//String path = absolutePath+"src\\es5\\test\\euclid.lft";
		//String path = absolutePath+"src\\es5\\test\\factorial.lft";
		String path = absolutePath+"src\\es5\\test\\testBoolean.lft";
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			Translator translator = new Translator(lex, br);
			translator.prog();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
