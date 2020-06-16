package es1.es1_4;



/*
 * Modificare l’automa dell’esercizio precedente in modo che riconosca le combinazioni di matricola e cognome di studenti del turno 2 o del turno 3 del laboratorio, dove il numero
di matricola e il cognome possono essere separati da una sequenza di spazi, e possono essere
precedute e/o seguite da sequenze eventualmente vuote di spazi. Per esempio, l’automa deve
accettare la stringa “654321 Rossi” e “ 123456 Bianchi ” (dove, nel secondo esempio, ci
sono spazi prima del primo carattere e dopo l’ultimo carattere), ma non “1234 56Bianchi” e
“123456Bia nchi”. Per questo esercizio, i cognomi composti (con un numero arbitrario di parti) possono essere accettati: per esempio, la stringa “123456De Gasperi” deve essere accettato.
Modificare l’implementazione Java dell’automa di conseguenza.
 */


//parte di de gasperi non torna

public class LabTurnMod {
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
            case 0: // stato iniziale q0
                if (Character.isDigit(ch) && even(ch))
                    state = 1;
                else if (Character.isDigit(ch) && !even(ch))
                    state = 2;
                else if(Character.isWhitespace(ch))
                    state = 0;
                else
                    state = -1;
                break;
            case 1: // stato q1
                if (Character.isDigit(ch) && even(ch))
                    state = 1;
                else if (Character.isDigit(ch) && !even(ch))
                    state = 2;
                else if (Character.isWhitespace(ch))
                    state = 3;
                else if (ak(ch)) // A..k
                    state = 5;
                else
                    state = -1;
                break;
            case 2: // stato q2
                if (Character.isDigit(ch) && even(ch))
                    state = 1;
                else if (Character.isDigit(ch) && !even(ch))
                    state = 2;
                else if (Character.isWhitespace(ch))
                    state = 4;
                else if (lz(ch)) // L..z
                    state = 5;
                else
                    state = -1;
                break;
            case 3: // stato q3
                if (Character.isWhitespace(ch))
                    state = 3;
                if (ak(ch)) // A..k
                    state = 5;
                else
                    state = -1;
                break;
            case 4: // stato q4
                if (Character.isWhitespace(ch))
                    state = 4;
                else if (lz(ch)) // L..z
                    state = 5;
                else
                    state = -1;
                break;
            case 5: // stato q5
                if (Character.isLetter(ch) || Character.isWhitespace(ch))
                    state = 5;
                else
                    state = -1;
                break;
			}
		}
		return state == 5;
	}

	public static void main(String[] args) {
		System.out.println(scan(args[0]) ? "OK" : "NOPE");
	}
}
