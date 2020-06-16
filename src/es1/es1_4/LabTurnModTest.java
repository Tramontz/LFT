package es1.es1_4;

import junit.framework.TestCase;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

	/**
	 * Esercizio 1.2
	 */
public class LabTurnModTest extends TestCase{
    LabTurnMod labTurnMod;
    @Override
    public void setUp() throws Exception {
        super.setUp();
        labTurnMod = new LabTurnMod();
    }
   @Test
    public void testscantT3() throws Exception {
	   assertTrue(labTurnMod.scan("654321Rossi"));
	   assertTrue(labTurnMod.scan("654321 Rossi"));
	   assertTrue(labTurnMod.scan(" 654321 Rossi"));
	   assertFalse(labTurnMod.scan("65 4321 Rossi"));	  
	   }
   @Test
    public void testscantT2() throws Exception {
	   assertTrue(labTurnMod.scan("123456Bianchi"));
	   assertTrue(labTurnMod.scan("123456 Bianchi"));
	   assertTrue(labTurnMod.scan(" 123456 Bianchi"));
	   assertTrue(labTurnMod.scan("123456De Gasperi"));
	   assertFalse(labTurnMod.scan("1234 56Bianchi"));
    }
}