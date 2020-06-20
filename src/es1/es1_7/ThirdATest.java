package es1.es1_7;

import junit.framework.TestCase;
import org.junit.Test;

public class ThirdATest extends TestCase {

	@Override
	public void setUp() throws Exception {
		super.setUp();

	}

	@Test
	public void testThirdA() throws Exception {
		 assertTrue(ThirdA.scan("abbbbbb"));
		 assertTrue(ThirdA.scan("abb"));
		 assertTrue(ThirdA.scan("bbaba"));
		 assertTrue(ThirdA.scan("baaaaaaa"));
		 assertTrue(ThirdA.scan("aaaaaaa"));
		 assertTrue(ThirdA.scan("a"));
		 assertTrue(ThirdA.scan("ba"));
		 assertTrue(ThirdA.scan("bba"));
		 assertTrue(ThirdA.scan("aa"));
		 assertTrue(ThirdA.scan("bbabbbbbbbb"));
		 assertFalse(ThirdA.scan("bbbababab"));
		 assertFalse(ThirdA.scan("b"));
		 assertFalse(ThirdA.scan("bbbaaaaaaa"));
	}
}