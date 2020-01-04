package es1.es1_9;

public class Fabio {
	public static boolean scan(String str) {
		int state = 0;
		for (char ch : str.toCharArray()) {
			switch (state) { 
			case 0:
				if(ch=='F')
					state= 1;
				else
					state= 5;
					break;
			case 1:
				if(ch=='A')
					state= 2;
				else
					state= 9;
					break;
			case 2:
				if(ch=='B')
					state= 3;
				else
					state= 10;
					break;
			case 3:
				if(ch=='I')
					state= 4;
				else
					state= 12;
					break;
			case 4:
					state= 4;

			case 5:
				if(ch=='A')
					state= 6;
				else
					state= 9;
					break;		
			case 6:
				if(ch=='B')
					state= 7;
				else
					state= 12;
					break;
			case 7:
				if(ch=='I')
					state= 8;
				else
					state= -1;
					break;
			case 8:
				if(ch=='O')
					state= 8;
				else
					state= -1;
					break;
			case 9:
				if(ch=='B')
					state= 10;
				else
					state= -1;
					break;	
			case 10:
				if(ch=='I')
					state= 11;
				else
					state= -1;
					break;	
			case 11:
				if(ch=='O')
					state= 11;
				else
					state= -1;
					break;	
			case 12:
				if(ch=='I')
					state= 13;
				else
					state= -1;
					break;	
			case 13:
				if(ch=='O')
					state= 13;
				else
					state= -1;
					break;
			case 14:
				if(ch=='O')
					state= 14;
				else
					state= -1;
					break;
			}
		}
		return (state == 4 || state == 8 || state == 11 || state == 13 || state == 14) ? true : false;
	}

	public static void main(String[] args) {
		System.out.println(scan(args[0]) ? "OK" : "NOPE");
	}
}