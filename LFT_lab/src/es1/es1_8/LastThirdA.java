package es1.es1_8;

public class LastThirdA {
	public static boolean scan(String str) {
		int state = 0;
		for (char ch : str.toCharArray()) {
			switch (state) { 
			case 0:
				if(ch=='a')
					state= 7;
				else if(ch=='b')
					state=0;
				else
					state=-1;
					break;
			case 1:
				if(ch=='a')
					state= 7;
				else if(ch=='b')
					state=2;
				else
					state=-2;
					break;
			case 2:
				if(ch=='a')
					state= 5;
				else if(ch=='b')
					state=3;
				else
					state=-1;
					break;
			case 3:
				if(ch=='a')
					state=1;
				else if(ch=='b')
					state=0;
				else
					state=-1;
					break;
			case 4:
				if(ch=='a')
					state=7;
				else if(ch=='b')
					state=6;
				else
					state=-1;
					break;
			case 5:
				if(ch=='a')
					state=4;
				else if(ch=='b')
					state=2;
				else
					state=-1;
					break;
			case 6:
				if(ch=='a')
					state=5;
				else if(ch=='b')
					state=3;
				else
					state=-1;
					break;
			case 7:
				if(ch=='a')
					state=7;
				else if(ch=='b')
					state=6;
				else
					state=-1;
					break;
			}
		}
		return (state ==2 || state == 5 || state == 6 || state == 7)?true : false;
	}

	public static void main(String[] args) {
		System.out.println(scan(args[0]) ? "OK" : "NOPE");
	}
}