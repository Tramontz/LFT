package es1.es1_7;


	import junit.framework.TestCase;
	import org.junit.Test;

public class ThirdATest extends TestCase{

	ThirdA thirdA;
    @Override
    public void setUp() throws Exception {
        super.setUp();
        thirdA = new ThirdA();
    }
	    @Test
    public void testNoThirdA() throws Exception {
        assertFalse(thirdA.scan("bbbababab"));
        assertFalse(thirdA.scan("b"));
        assertFalse(thirdA.scan("bbbaaaaaaaaaaaaa"));
    }
    @Test
    public void testThirdA() throws Exception {
        assertTrue(thirdA.scan("abbbbbb"));
        assertTrue(thirdA.scan("a"));
        assertTrue(thirdA.scan("ba"));
        assertTrue(thirdA.scan("bbabbbbbbbb"));
    }
}