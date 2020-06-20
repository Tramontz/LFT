package es1.es1_8;

/*
Esempi di stringhe accettate: “abb”, “bbaba”, “baaaaaaa”, “aaaaaaa”, “a”, “ba”, “bba”,
“aa”, “bbbababab”
Esempi di stringhe non accettate: “abbbbbb”, “bbabbbbbbbb”, “b”
*/

import junit.framework.TestCase;
import org.junit.Test;

public class LastThirdATest extends TestCase {

	@Override
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void testThirdA() throws Exception {
		assertTrue(LastThirdA.scan("abb"));
		assertTrue(LastThirdA.scan("bbaba"));
		assertTrue(LastThirdA.scan("baaaaaaa"));
		assertTrue(LastThirdA.scan("aaaaaaa"));
		assertTrue(LastThirdA.scan("a"));
		assertTrue(LastThirdA.scan("ba"));
		assertTrue(LastThirdA.scan("bba"));
		assertTrue(LastThirdA.scan("aa"));
		assertTrue(LastThirdA.scan("bbbababab"));
		assertFalse(LastThirdA.scan("abbbbbb"));
		assertFalse(LastThirdA.scan("b"));
		assertFalse(LastThirdA.scan("bbabbbbbbbb"));
		assertFalse(LastThirdA.scan("bbabbbbabbbabbb"));
	}
}