package es1.es1_8;


import junit.framework.TestCase;
import org.junit.Test;

public class LastThirdATest extends TestCase{

	LastThirdA lastthirdA;
    @Override
    public void setUp() throws Exception {
        super.setUp();
        lastthirdA = new LastThirdA();
    }
	    @Test
    public void testNoThirdA() throws Exception {
        assertFalse(lastthirdA.scan("abbbbbb"));
        assertFalse(lastthirdA.scan("b"));
        assertFalse(lastthirdA.scan("bbabbbbbbbb"));
        assertFalse(lastthirdA.scan("bbabbbbabbbabbb"));
    }
    @Test
    public void testThirdA() throws Exception {
        assertTrue(lastthirdA.scan("bbaba"));
        assertTrue(lastthirdA.scan("a"));
        assertTrue(lastthirdA.scan("ba"));
        assertTrue(lastthirdA.scan("bbbababab"));
    }
}