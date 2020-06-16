package es3.es3_2;

public class NumberTok extends Token {
	public int lexeme;

	public NumberTok(int s) {
		super(256);
		lexeme = s;
	}

	public String toString() {
		return "<" + tag + ", " + lexeme + ">";
	}
}
