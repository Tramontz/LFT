package es1.es1_4;

import junit.framework.TestCase;

import org.junit.Test;

public class LabTurnModTest extends TestCase {

	@Override
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void testScanTurnComposedName() throws Exception {
		assertTrue(LabTurnMod.scan("654321Rossi"));
		assertTrue(LabTurnMod.scan("654321 Rossi"));
		assertTrue(LabTurnMod.scan(" 654321 Rossi"));
		assertTrue(LabTurnMod.scan("123456Bianchi"));
		assertTrue(LabTurnMod.scan("123456 Bianchi"));
		assertTrue(LabTurnMod.scan(" 123456 Bianchi"));
		assertTrue(LabTurnMod.scan("123456De Gasperi"));
		assertFalse(LabTurnMod.scan("65 4321 Rossi"));
		assertFalse(LabTurnMod.scan("1234 56Bianchi"));
		assertFalse(LabTurnMod.scan("123456Bia nchi"));
	}
}