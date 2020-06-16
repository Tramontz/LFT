package es1.es1_5;

/*
 * Progettare e implementare un DFA che, come in Esercizio 1.3, riconosca il linguaggio di stringhe che contengono matricola e cognome di studenti del turno 2 o del turno 3 del
laboratorio, ma in cui il cognome precede il numero di matricola (in altre parole, le posizioni del
cognome e matricola sono scambiate rispetto all’Esercizio 1.3). Assicurarsi che il DFA sia minimo
 */

public class TurnLabNamed {
/* PARI */
	private static boolean even(char ch) {
		return (ch%2)==0;
	}
/* A..k */
	private static boolean ak(char ch){
		return (ch >= 'A' && ch <= 'K') || (ch >= 'a' && ch <= 'k');
	}

/* L..z */
	private static boolean lz(char ch) {
		return (ch >= 'L' && ch <= 'Z') || (ch >= 'l' && ch <= 'z');
	}

	public static boolean scan(String s) {
		int state = 0;
		int i = 0;
		while (state >= 0 && i < s.length()) {
			final char ch = s.charAt(i++);
			switch (state) {
        	case 0:
                if (ak(ch))
                    state = 1;
                else if (lz(ch))
                    state = 2;
                else
                    state = -1;
                break;
            case 1:
                if(Character.isDigit(ch) && even(ch))
                    state = 4;
                else if (Character.isDigit(ch) && !even(ch))
                    state = 5;
                else if (Character.isLetter(ch))
                    state = 1;
                else
                    state = -1;
                break;
            case 2:
                if (Character.isDigit(ch) && !even(ch))
                    state = 3;
                else if (Character.isDigit(ch) && even(ch))
                    state = 6;
                else if (Character.isLetter(ch))
                    state = 2;
                else
                    state = -1;
                break;
            case 3:         // turno 3
                if (Character.isDigit(ch) && !even(ch))
                    state = 3;
                else if (Character.isDigit(ch) && even(ch))
                    state = 6;
                else
                    state = -1;
                break;
            case 4:         // turno 2
                if (Character.isDigit(ch) && even(ch))
                    state = 4;
                else if (Character.isDigit(ch) && !even(ch))
                    state = 5;
                else
                    state = -1;
                break;
            case 5:
                if (Character.isDigit(ch) && !even(ch))
                    state = 5;
                else if (Character.isDigit(ch) && even(ch))
                    state = 4;
                else
                    state = -1;
                break;
            case 6:
                if (Character.isDigit(ch) && even(ch))
                    state = 6;
                else if (Character.isDigit(ch) && !even(ch))
                    state = 3;
                else
                    state = -1;
                break;
        }
    }
    return ((state == 3) || (state == 4));
}


	public static void main(String[] args) {
		System.out.println(scan(args[0]) ? "OK" : "NOPE");
	}	
}