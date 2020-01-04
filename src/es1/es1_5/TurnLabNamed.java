package es1.es1_5;

public class TurnLabNamed {
	public static Boolean scan(String str) {
		int state = 0;
		int i = 0;
		for (char c : str.toCharArray()) {
			if(Character.isDigit(c)) i++;
			switch (state) { 
			case 0:
				if(Character.isAlphabetic(c) &&c>='L' && c<='Z')
				state=2;
			else if(Character.isAlphabetic(c) && c>='A' && c<='K')
				state=1;
			else if (Character.isSpaceChar(c))
				state=1;
			else 
				state=-1;
				break;
		case 1:
			if(Character.isDigit(c) && (c%2)==0)
				state=3;
			else if(Character.isSpaceChar(c) || Character.isAlphabetic(c))
				state=1;
			else 
				state=-1;
				break;	
		case 3:
			if(Character.isDigit(c) || Character.isSpaceChar(c) && i<=5)
				state=3;
			else
				state=-1;
		case 2:
			if(Character.isDigit(c) && (c%2)!=0)
				state=3;
			else if(Character.isSpaceChar(c) || Character.isAlphabetic(c))
				state=1;
			else 
				state=-1;
				break;	
			}			
		}
		return state==3;
	}
	public static void main(String[] args) {
		System.out.println(scan(args[0]) ? "OK" : "NOPE");
	}
}