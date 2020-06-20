package es1.es1_3;

/*
 * Progettare e implementare un DFA che riconosca il linguaggio di stringhe che
contengono un numero di matricola seguito (subito) da un cognome, dove la combinazione di
matricola e cognome corrisponde a studenti del turno 2 o del turno 3 del laboratorio di Linguaggi
Formali e Traduttori. Si ricorda le regole per suddivisione di studenti in turni:
• Turno T1: cognomi la cui iniziale e compresa tra A e K, e il numero di matricola ` e dispari; `
• Turno T2: cognomi la cui iniziale e compresa tra A e K, e il numero di matricola ` e pari; `
• Turno T3: cognomi la cui iniziale e compresa tra L e Z, e il numero di matricola ` e dispari; `
• Turno T4: cognomi la cui iniziale e compresa tra L e Z, e il numero di matricola ` e pari.
 */

public class LabTurn {
	public static boolean scan(String s) {
		int state = 0;
		int i = 0;
		s = s.toUpperCase();
		while (state >= 0 && i < s.length()) {
			final char ch = s.charAt(i++);
			switch (state) {
			case 0:
				if (ch == '0' || ch == '2' || ch == '4' || ch == '6' || ch == '8')
					state = 1;
				else if (ch == '1' || ch == '3' || ch == '5' || ch == '7' || ch == '9')
					state = 2;
				else
					state = -1;
				break;
			case 1:
				if (ch == '0' || ch == '2' || ch == '4' || ch == '6' || ch == '8')
					state = 1;
				else if (ch == '1' || ch == '3' || ch == '5' || ch == '7' || ch == '9')
					state = 2;
				else if (ch >= 'A' && ch <= 'K')
					state = 3;
				else
					state = -1;
				break;
			case 2:
				if (ch == '0' || ch == '2' || ch == '4' || ch == '6' || ch == '8')
					state = 1;
				else if (ch == '1' || ch == '3' || ch == '5' || ch == '7' || ch == '9')
					state = 2;
				else if (ch >= 'L' && ch <= 'Z')
					state = 4;
				else
					state = -1;
				break;
			case 3:
				if (ch >= 'A' && ch <= 'Z')
					state = 3;
				else
					state = -1;
				break;
			case 4:
				if (ch >= 'A' && ch <= 'Z')
					state = 4;
				else
					state = -1;
				break;
			}
		}
		if (state == 3 || state == 4)
			return true;
		else
			return false;
	}

	public static void main(String[] args) {
		System.out.println(scan(args[0]) ? "OK" : "NOPE");
	}
}
