package es1.es1_11;


/*da chiedere al professore*/

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