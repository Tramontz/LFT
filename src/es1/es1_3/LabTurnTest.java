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
    public void testscantT3() throws Exception {
	   assertTrue(labTurn.scan("654321Rossi"));
	   assertTrue(labTurn.scan("1R"));
	   assertFalse(labTurn.scan("654321Bianchi"));
	   assertFalse(labTurn.scan("123456"));
	   assertFalse(labTurn.scan("Bianchi"));
	   assertFalse(labTurn.scan("123456Rossi"));
        }
    @Test
    public void testscantT2() throws Exception {
    	assertTrue(labTurn.scan("123456Bianchi"));
    	assertTrue(labTurn.scan("122B"));
    	assertTrue(labTurn.scan("2Bianchi"));
 	   	assertFalse(labTurn.scan("654321Bianchi"));
 	    assertFalse(labTurn.scan("123456"));
 	    assertFalse(labTurn.scan("Bianchi"));
    }
}