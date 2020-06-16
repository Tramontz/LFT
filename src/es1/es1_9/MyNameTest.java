package es1.es1_9;


import junit.framework.TestCase;
import org.junit.Test;

public class MyNameTest extends TestCase{

	MyName comments;
    @Override
    public void setUp() throws Exception {
        super.setUp();
        comments = new MyName();
    }
	    @Test
    public void testFabio() throws Exception {
	    assertTrue(comments.scan("FABIO","ZABIO"));
	    assertFalse(comments.scan("FABIO","WDKOR"));
	    assertTrue(comments.scan("FABIO","F*BIO"));
	    assertTrue(comments.scan("FABIO","FACIO"));
	    assertTrue(comments.scan("FABIO","FABRO"));
		assertTrue(comments.scan("FABIO","FABIS"));
        assertFalse(comments.scan("FABIO","AMANDA"));
        assertFalse(comments.scan("FABIO","FFFABIO"));
        assertFalse(comments.scan("FABIO","FABIOLA"));
        assertFalse(comments.scan("FABIO","FXBIS"));
    }
}