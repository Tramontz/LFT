package es1.es1_5;

import junit.framework.TestCase;
import org.junit.Test;

public class TurnLabNamedTest extends TestCase {

	@Override
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void testScanNamed() throws Exception {
		assertTrue(TurnLabNamed.scan("Rossi654321"));
		assertTrue(TurnLabNamed.scan("Bianchi123456"));
		assertFalse(TurnLabNamed.scan("Rossi65 4321"));
		assertFalse(TurnLabNamed.scan("Ros si 654321"));
		assertFalse(TurnLabNamed.scan("Bianchi 1234 56"));
		assertFalse(TurnLabNamed.scan("De Gasperi123456"));
	}

}
