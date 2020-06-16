package es1.es1_11;

/*
 * Modificare l’automa dell’esercizio precedente in modo che riconosca il linguaggio di stringhe (sull’alfabeto {/, *, a}) che contengono “commenti” delimitati da /* e * /, ma con
la possibilita di avere stringhe prima e dopo come specificato qui di seguito. L’idea ` e che sia `
possibile avere eventualmente commenti (anche multipli) immersi in una sequenza di simboli
dell’alfabeto. Quindi l’unico vincolo e che l’automa deve accettare le stringhe in cui un’occorren- `
za della sequenza /* deve essere seguita (anche non immediatamente) da un’occorrenza della
sequenza * /. Le stringhe del linguaggio possono non avere nessuna occorrenza della sequenza
/* (caso della sequenza di simboli senza commenti). Implementare l’automa seguendo la costruzione vista in Listing 1
 */

public class CommentsModify {
	public static boolean scan(String str) {
		int state = 0;
		for (char ch : str.toCharArray()) {
			switch (state) { 
			case 0:
				if(ch=='/')
					state= 1;
				else if(ch=='a')
					state= 4;
				else if(ch=='*')
					state= 3;
				else
					state= -1;
					break;
			case 1:
				if(ch=='*')
					state= 2;
				else
					state= -1;
					break;
			case 2:
				if(ch=='a' || ch=='/')
					state= 2;
				else if(ch=='*')
					state= 3;
				else
					state= -1;
					break;
			case 3:
				if(ch=='a')
					state= 2;
				else if(ch=='*')
					state= 3;
				else if(ch=='/')
					state= 4;
				else
					state= -1;
					break;
			case 4:
				if(ch=='a')
					state= 4;
				else if(ch=='/')
					state= 1;
				else if(ch=='*')
					state= 5;
				else
					state= -1;
				break;
			case 5:
				if(ch=='/' || ch == 'a')
					state= 4;
				else if(ch=='*')
					state= 5;
				else
					state= -1;
			}
		}
		return state == 4;
	}

	public static void main(String[] args) {
		System.out.println(scan(args[0]) ? "OK" : "NOPE");
	}
}