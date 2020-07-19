package es1.es1_9;

/*
 * Progettare e implementare un DFA che riconosca il linguaggio di stringhe che
contengono il tuo nome e tutte le stringhe ottenute dopo la sostituzione di un carattere del nome
con un altro qualsiasi. Ad esempio, nel caso di uno studente che si chiama Paolo, il DFA deve
accettare la stringa “Paolo” (cioe il nome scritto correttamente), ma anche le stringhe “ ` Pjolo”,
“caolo”, “Pa%lo”, “Paola” e “Parlo” (il nome dopo la sostituzione di un carattere), ma non
“Eva”, “Perro”, “Pietro” oppure “P*o*o”
 */

/*Il DFA apposito per FABIO prevedeva una serie di stati dopo ogni lettera, che in caso fosse giusta passassero ad uno stato successivo, se fosse stata
 una lettera diversa dall'originale, con una serie successiva di stati controllava che le lettere in sequenza fossero quelle del nome.
ho provato ad immaginarne uno più 'sofisticato', valido per qualunque nome.
 */

public class MyName {
	public static boolean scan(String name) {
		name = name.toUpperCase();
		int state = 0;
		int i = 0;
		while (state >= 0 && i < name.length()) {
			final char ch = name.charAt(i++);
			switch (state) {
			case 0:
				if (ch == 'F')
					state = 1;
				else if (ch != 'F')
					state = 6;
				break;
			case 1:
				if (ch == 'A')
					state = 2;
				else if (ch != 'A')
					state = 7;
				break;
			case 2:
				if (ch == 'B')
					state = 3;
				else if (ch != 'B')
					state = 8;
				break;
			case 3:
				if (ch == 'I')
					state = 4;
				else if (ch != 'I')
					state = 9;	
				break;
			case 4:
					state = 5;
				break;
			case 5:
				state= -1;
			case 6:
				if (ch == 'A')
					state = 7;
				else 
					state= -1;
				break;
			case 7:
				if (ch == 'B')
					state = 8;
				else 
					state= -1;
				break;
			case 8:
				if (ch == 'I')
					state = 9;
				else 
					state= -1;
				break;
			case 9:
				if (ch == 'O')
					state = 5;
				else 
					state= -1;
				break;				
			}
		}
		if (state == 5)	return true;
		else return false;
	}

	public static void main(String[] args) {
		System.out.println(scan(args[0]) ? "OK" : "NOPE");
	}
}