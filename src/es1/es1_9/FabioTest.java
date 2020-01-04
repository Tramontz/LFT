package es1.es1_9;


import junit.framework.TestCase;
import org.junit.Test;

public class FabioTest extends TestCase{

	Fabio comments;
    @Override
    public void setUp() throws Exception {
        super.setUp();
        comments = new Fabio();
    }
	    @Test
    public void testNoThirdA() throws Exception {
        assertFalse(comments.scan("AMANDA"));
        assertFalse(comments.scan("FFFABIO"));
        assertFalse(comments.scan("FABIOLA"));
        assertFalse(comments.scan("FXBIS"));
    }
    @Test
    public void testThirdA() throws Exception {
        assertTrue(comments.scan("FACIO"));
        assertTrue(comments.scan("ZABIO"));
        assertTrue(comments.scan("FABIS"));
    }
}