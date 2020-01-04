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
    public void testScanNoThreeZero() throws Exception {
	    assertFalse(labTurnMod.scan("0011110"));
	    assertFalse(labTurnMod.scan("01010144ABC"));
	    assertFalse(labTurnMod.scan("102145ArOMA"));
    }
   @Test
    public void testscantT3() throws Exception {
	   assertTrue(labTurnMod.scan("65 4321 Rossi"));	
	   assertTrue(labTurnMod.scan("654321 Rossi"));
        }
   @Test
    public void testscantT2() throws Exception {
	   assertTrue(labTurnMod.scan("123456 Bianchi"));
	   assertTrue(labTurnMod.scan("1234 56Bianchi"));
	   assertTrue(labTurnMod.scan("123456De Gasperi"));
    }
}