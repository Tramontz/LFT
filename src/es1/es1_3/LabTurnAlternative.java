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

public class LabTurnAlternative {
/* controllo per numero di matricola PARI */
	private static boolean even(char ch) {
		return (ch%2)==0;
	}
/* cognome intervallo A..k */
	private static boolean ak(char ch){
		return (ch >= 'A' && ch <= 'K') || (ch >= 'a' && ch <= 'k');
	}

/* cognome intervallo L..z */
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
                if(Character.isDigit(ch) && even(ch))
                    state = 1;
                else if (Character.isDigit(ch) && !even(ch))
                    state = 2;
                else
                    state = -1;
                break;
            case 1: 
                if(Character.isDigit(ch) && even(ch))
                    state = 1;
                else if (Character.isDigit(ch) && !even(ch))
                    state = 2;
                else if(ak(ch)) 
                    state = 3;
                else
                    state = -1;
                break;
            case 2: 
                if(Character.isDigit(ch) && even(ch))
                    state = 1;
                else if (Character.isDigit(ch) && !even(ch))
                    state = 2;
                else if(lz(ch)) // L..z
                    state = 4;
                else
                    state = -1;
                break;
            case 3:
                if (Character.isLetter(ch)) // A..z
                    state = 3;
                else
                    state = -1;
                break;
            case 4:	// stato q4
                if (Character.isLetter(ch)) // A..z
                state = 4;
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

