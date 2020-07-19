package es1.es1_9;


import junit.framework.TestCase;
import org.junit.Test;

public class MyNameTest extends TestCase{

    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

	    @Test
    public void testGenericName() throws Exception {
	    assertTrue(GenericName.scan("FABIO","ZABIO"));
	    assertTrue(GenericName.scan("FABIO","F*BIO"));
	    assertTrue(GenericName.scan("FABIO","FACIO"));
	    assertTrue(GenericName.scan("FABIO","FABRO"));
		assertTrue(GenericName.scan("FABIO","FABIS"));
        assertFalse(GenericName.scan("FABIO","AMANDA"));
        assertFalse(GenericName.scan("FABIO","FFFABIO"));
        assertFalse(GenericName.scan("FABIO","FABIOLA"));
        assertFalse(GenericName.scan("FABIO","FXBIS"));
	    assertFalse(GenericName.scan("FABIO","WDKOR"));

    }
	    @Test
	    public void testMyName() throws Exception {
		    assertTrue(MyName.scan("ZABIO"));
		    assertTrue(MyName.scan("F*BIO"));
		    assertTrue(MyName.scan("FACIO"));
		    assertTrue(MyName.scan("FABRO"));
		    assertTrue(MyName.scan("FABIS"));
		    assertFalse(MyName.scan("AMANDA"));
		    assertFalse(MyName.scan("FFFABIO"));
		    assertFalse(MyName.scan("FABIOLA"));
		    assertFalse(MyName.scan("FXBIS"));
		    assertFalse(MyName.scan("WDKOR"));

	    }
}