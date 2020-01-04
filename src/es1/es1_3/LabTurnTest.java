package es1.es1_3;


import junit.framework.TestCase;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

	/**
	 * Esercizio 1.2
	 */
public class LabTurnTest extends TestCase{
    LabTurn labTurn;
    @Override
    public void setUp() throws Exception {
        super.setUp();
        labTurn = new LabTurn();
    }
	    @Test
    public void testScanNoThreeZero() throws Exception {
	    assertFalse(labTurn.scan("0011110"));
	    assertFalse(labTurn.scan("01010144ABC"));
	    assertFalse(labTurn.scan("102145ArOMA"));
	    assertFalse(labTurn.scan("6543 21Ros si"));
    }
   @Test
    public void testscantT3() throws Exception {
	   assertTrue(labTurn.scan("654321Rossi"));	    
        }
    @Test
    public void testscantT2() throws Exception {
    	assertTrue(labTurn.scan("123456Bianchi"));
    }
}