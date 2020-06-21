package es2_3;

public class NumberTok extends Token {
	int n = 0;
	public NumberTok(int s) {
		super(Tag.NUM); 
		n = s;
	}
	public String toString() {
		return "<" + Tag.NUM + ", " + n + ">"; 
	}
}
