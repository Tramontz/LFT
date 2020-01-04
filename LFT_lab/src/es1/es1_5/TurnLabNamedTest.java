package es1.es1_5;

import junit.framework.TestCase;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class TurnLabNamedTest extends TestCase{
	TurnLabNamed turnLabNamed;
    @Override
    public void setUp() throws Exception {
        super.setUp();
        turnLabNamed = new TurnLabNamed();
    }
	    @Test
    public void testScanNoThreeZero() throws Exception {
	    assertFalse(turnLabNamed.scan("0011110"));
	    assertFalse(turnLabNamed.scan("01010144ABC"));
	    assertFalse(turnLabNamed.scan("102145ArOMA"));
    }
   @Test
    public void testscantT3() throws Exception {
	   assertTrue(turnLabNamed.scan("Rossi65 4321"));	
	   assertTrue(turnLabNamed.scan("Ros si 654321"));
        }
   @Test
    public void testscantT2() throws Exception {
	   assertTrue(turnLabNamed.scan("Bianchi123456"));
	   assertTrue(turnLabNamed.scan("Bianchi 1234 56"));
	   assertTrue(turnLabNamed.scan("De Gasperi123456"));
    }
}

