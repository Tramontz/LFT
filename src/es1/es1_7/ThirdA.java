package es1.es1_7;

/*
 * Progettare e implementare un DFA con alfabeto {a, b} che riconosca il linguaggio
delle stringhe tali che 'a' occorre almeno una volta in una delle prime tre posizioni della stringa.
Il DFA deve accettare anche stringhe che contengono meno di tre simboli (ma almeno uno dei
simboli deve essere a).
Esempi di stringhe accettate: �abb�, �abbbbbb�, �bbaba�, �baaaaaaa�, �aaaaaaa�, �a�, �ba�,
�bba�, �aa�, �bbabbbbbbbb�
Esempi di stringhe non accettate: �bbbababab�, �b�
 */

public class ThirdA {
	public static boolean scan(String str) {
		int state = 0;
		for (char ch : str.toCharArray()) {
			switch (state) { 
			case 0:
				if(ch=='a')
					state= 4;
				else if(ch=='b')
					state=1;
				else
					state=-1;
					break;
			case 1:
				if(ch=='a')
					state= 4;
				else if(ch=='b')
					state=2;
				else
					state=-2;
					break;
			case 2:
				if(ch=='a')
					state= 4;
				else if(ch=='b')
					state=3;
				else
					state=-1;
					break;
			case 3: 
				if(ch=='a'||ch=='b')
					state= 3;
				else
					state=-1;
					break;
			case 4: 
				if(ch=='a'||ch=='b')
					state= 4;
				else
					state=-1;
					break;
			}
		}
		return state == 4;
	}

	public static void main(String[] args) {
		System.out.println(scan(args[0]) ? "OK" : "NOPE");
	}
}