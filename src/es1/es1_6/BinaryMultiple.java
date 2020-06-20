package es1.es1_6;

/*
 * Progettare e implementare un DFA che riconosca il linguaggio dei numeri binari
(stringhe di 0 e 1) il cui valore e multiplo di 3. Per esempio, “ ` 110”, “1001” e “0” sono stringhe del linguaggio (rappresentano rispettivamente i numeri 6, 9 e 0), mentre “10” e “111” no
(rappresentano rispettivamente i numeri 2 e 7). Eventuale sequenze di 0 iniziali devono essere
trattate: ad esempio, il DFA deve accettare le stringhe “0110” e “0001111”.
Suggerimento: usare tre stati per rappresentare il resto della divisione per 3 del numero.

 */

public class BinaryMultiple {
	public static boolean scan(String s) {
		int state = 0;
		int i = 0;
		while (state >= 0 && i < s.length()) {
			final char ch = s.charAt(i++);
			switch (state) {

			case 0:
				if (ch == '0')
					state = 0;
				else if (ch == '1')
					state = 1;
				else
					state = -1;
				break;
			case 1:
				if (ch == '0')
					state = 2;
				else
					state = 0;
				break;

			case 2:
				if (ch == '0')
					state = 1;
				break;
			}
		}
		return state == 0;
	}

	public static void main(String[] args) {
		System.out.println(scan(args[0]) ? "OK" : "NOPE");
	}
}
