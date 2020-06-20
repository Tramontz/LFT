package es1.es1_8;

/*
 * Progettare e implementare un DFA con alfabeto {a, b} che riconosca il linguaggio
delle stringhe tali che a occorre almeno una volta in una delle ultime tre posizioni della stringa.
Come nell’esercizio 1.7, il DFA deve accettare anche stringhe che contengono meno di tre simboli
(ma almeno uno dei simboli deve essere a).
 */

public class LastThirdA {
	public static boolean scan(String str) {
		int state = 0;
		int i = 0;
		while (state >= 0 && i < str.length()) {
			final char ch = str.charAt(i++);
			switch (state) {
			case 0:
				if (ch == 'a')
					state = 1;
				else if (ch == 'b')
					state = 0;
				else
					state = -1;
				break;
			case 1:
				if (ch == 'a')
					state = 2;
				else if (ch == 'b')
					state = 4;
				else
					state = -1;
				break;
			case 2:
				if (ch == 'a')
					state = 3;
				else if (ch == 'b')
					state = 6;
				else
					state = -1;
				break;
			case 3:
				if (ch == 'a')
					state = 3;
				else if (ch == 'b')
					state = 2;
				else
					state = -1;
				break;
			case 4:
				if (ch == 'a')
					state = 3;
				else if (ch == 'b')
					state = 5;
				else
					state = -1;
				break;
			case 5:
				if (ch == 'a')
					state = 3;
				else if (ch == 'b')
					state = 0;
				else
					state = -1;
				break;
			case 6:
				if (ch == 'a')
					state = 3;
				else if (ch == 'b')
					state = 0;
				else
					state = -1;
				break;
			}
		}
		return (state == 1 || state == 2 || state == 3 || state == 4 || state == 5 || state == 6);
	}

	public static void main(String[] args) {
		System.out.println(scan(args[0]) ? "OK" : "NOPE");
	}
}