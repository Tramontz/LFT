package es1.es1_3;

public class LabTurn {
	public static boolean scan(String str) {
		int state = 0;
		int i = 0;
		for (char c : str.toCharArray()) {
			i++;
			switch (state) { 
			case 0:
				if (Character.isDigit(c) && i<=5)
					state= 0;
				else if(Character.isDigit(c) && (c%2)!=0)
					state=1;
				else if(Character.isDigit(c) && (c%2)==0)
					state=2;					
				else
					state=-1;
					break;
			case 1:
				if(Character.isAlphabetic(c) &&c>='L' && c<='Z')
					state=3;
				else 
					state=-1;
					break;
			case 2:
				if(Character.isAlphabetic(c) && c>='A' && c<='K')
					state=3;
				else 
					state=-1;
					break;
			case 3: 
				if(Character.isAlphabetic(c))
					state=3;
				else
					state = -1;
					break;	
			}
		}
		return state == 3;
	}

	public static void main(String[] args) {
		System.out.println(scan(args[0]) ? "OK" : "NOPE");
	}
}
