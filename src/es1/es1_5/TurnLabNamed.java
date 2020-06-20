package es1.es1_5;

/*
 * Progettare e implementare un DFA che, come in Esercizio 1.3, riconosca il linguaggio di stringhe che contengono matricola e cognome di studenti del turno 2 o del turno 3 del
laboratorio, ma in cui il cognome precede il numero di matricola (in altre parole, le posizioni del
cognome e matricola sono scambiate rispetto all�Esercizio 1.3). Assicurarsi che il DFA sia minimo
 */

public class TurnLabNamed {
	public static boolean scan(String s) {
		int state = 0;
		int i = 0;
		s = s.toUpperCase();
		while (state >= 0 && i < s.length()) {
			final char ch = s.charAt(i++);
			switch (state) {
			case 0:
				if (ch >= 'A' && ch <= 'K')
					state = 1;
				else if (ch >= 'L' && ch <= 'Z')
					state = 2;
				else
					state = -1;
				break;
			case 1:
				if (ch >= 'A' && ch <= 'Z' || ch == '1' || ch == '3' || ch == '5' || ch == '7' || ch == '9')
					state = 1;
				else if (ch == '0' || ch == '2' || ch == '4' || ch == '6' || ch == '8')
					state = 3;
				else
					state = -1;
				break;
			case 2:
				if (ch >= 'A' && ch <= 'Z' || ch == '0' || ch == '2' || ch == '4' || ch == '6' || ch == '8')
					state = 2;
				else if (ch == '1' || ch == '3' || ch == '5' || ch == '7' || ch == '9')
					state = 4;
				else
					state = -1;
				break;
			case 3:
				if (ch == '0' || ch == '2' || ch == '4' || ch == '6' || ch == '8')
					state = 3;
				else if (ch == '1' || ch == '3' || ch == '5' || ch == '7' || ch == '9')
					state = 1;
				else
					state = -1;
				break;
			case 4:
				if (ch == '1' || ch == '3' || ch == '5' || ch == '7' || ch == '9')
					state = 4;
				else if (ch == '0' || ch == '2' || ch == '4' || ch == '6' || ch == '8')
					state = 2;
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
