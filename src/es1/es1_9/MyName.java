package es1.es1_9;

/*
 * Progettare e implementare un DFA che riconosca il linguaggio di stringhe che
contengono il tuo nome e tutte le stringhe ottenute dopo la sostituzione di un carattere del nome
con un altro qualsiasi. Ad esempio, nel caso di uno studente che si chiama Paolo, il DFA deve
accettare la stringa “Paolo” (cioe il nome scritto correttamente), ma anche le stringhe “ ` Pjolo”,
“caolo”, “Pa%lo”, “Paola” e “Parlo” (il nome dopo la sostituzione di un carattere), ma non
“Eva”, “Perro”, “Pietro” oppure “P*o*o”
 */

public class MyName {

	public static boolean scan(String name, String prototype) {
		int state = 0;
		int i = 0;
		if (name.length() != prototype.length())
			state = -1;
		while (state >= 0 && i < name.length()) {
			final char ch = name.charAt(i);
			final char pr = prototype.charAt(i);
			switch (state) {
			case 0:
				if (ch == pr)
					state = 0;
				else if (ch != pr)
					state = 1;
				else
					state = -1;
				break;
			case 1:
				if (ch == pr)
					state = 1;
				else
					state = -1;
				break;
			}
			i++;
		}
		if (state == 0 || state == 1)
			return true;
		else
			return false;
	}

	public static void main(String[] args) {
		System.out.println(scan(args[0], args[1]) ? "OK" : "NOPE");
	}
}
