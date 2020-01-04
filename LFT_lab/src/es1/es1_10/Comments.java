package es1.es1_10;

public class Comments {
	public static boolean scan(String str) {
		int state = 0;
		for (char ch : str.toCharArray()) {
			switch (state) { 
			case 0:
				if(ch=='/')
					state= 1;
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
				state= -1;
				break;
			}
		}
		return state == 4;
	}

	public static void main(String[] args) {
		System.out.println(scan(args[0]) ? "OK" : "NOPE");
	}
}