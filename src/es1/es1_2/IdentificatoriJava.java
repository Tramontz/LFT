package es1.es1_2;

/**
 * Progettare e implementare un DFA che riconosca il linguaggio degli identificatori
 * in un linguaggio in stile Java: un identificatore e' una sequenza non vuota di
 * lettere, numeri, ed il simbolo di "underscore" _ che non comincia con un numero e
 * che non puo' essere composto solo dal simbolo _.
 */

public class IdentificatoriJava {
	public static boolean scan(String s) {
		int state = 0;
		int i = 0;
		while (state >= 0 && i < s.length()) {
			final char ch = s.charAt(i++);
			switch (state) {
			case 0:
                if (ch == '_')
                    state = 1;
                else if (Character.isLetter(ch))
                    state = 2;
                else
                    state = -1;
                break;
            case 1:
                if (ch == '_')
                    state = 1;
                else if (Character.isLetter(ch) || Character.isDigit(ch))
                    state = 2;
                else
                    state = -1;
                break;
            case 2:
                if (Character.isLetter(ch) || Character.isDigit(ch) || ch == '_')
                    state = 2;
                else
                    state = -1;
                break;
			}
		}
		return state == 2;
	}
	
	public static void main(String[] args) {
		System.out.println(scan(args[0]) ? "OK" : "NOPE");
	}
}