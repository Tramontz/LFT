package es1.es1_3;

import junit.framework.TestCase;

import org.junit.Test;

/**
Per esempio, “123456Bianchi” e “654321Rossi” sono stringhe del linguaggio, mentre
“654321Bianchi” e “123456Rossi” no. Nel contesto di questo esercizio, un numero di matricola non ha un numero prestabilito di cifre 
(ma deve essere composto di almeno una cifra). Un cognome corrisponde a una sequenza di lettere, e deve essere composto di almeno una lettera.
Quindi l’automa deve accettare le stringhe “2Bianchi” e “122B” ma non “654322” e “Rossi”.
Assicurarsi che il DFA sia minimo.
 */
public class LabTurnTest extends TestCase {
	@Override
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void testScanT3() throws Exception {
		assertTrue(LabTurn.scan("123456Bianchi"));
		assertTrue(LabTurn.scan("654321Rossi"));
		assertTrue(LabTurn.scan("1R"));
		assertTrue(LabTurn.scan("2Bianchi"));
		assertTrue(LabTurn.scan("122B"));
		assertTrue(LabTurn.scan("122B"));
		assertTrue(LabTurn.scan("2Bianchi"));
		assertFalse(LabTurn.scan("654321Bianchi"));
		assertFalse(LabTurn.scan("123456"));
		assertFalse(LabTurn.scan("Bianchi"));
		assertFalse(LabTurn.scan("123456Rossi"));
		assertFalse(LabTurn.scan("654321Bianchi"));
		assertFalse(LabTurn.scan("123456"));
		assertFalse(LabTurn.scan("Bianchi"));
	}
	
	@Test
	public void testScanAlternativeT3() throws Exception {
		assertTrue(LabTurnAlternative.scan("123456Bianchi"));
		assertTrue(LabTurnAlternative.scan("654321Rossi"));
		assertTrue(LabTurnAlternative.scan("1R"));
		assertTrue(LabTurnAlternative.scan("2Bianchi"));
		assertTrue(LabTurnAlternative.scan("122B"));
		assertTrue(LabTurnAlternative.scan("122B"));
		assertTrue(LabTurnAlternative.scan("2Bianchi"));
		assertFalse(LabTurnAlternative.scan("654321Bianchi"));
		assertFalse(LabTurnAlternative.scan("123456"));
		assertFalse(LabTurnAlternative.scan("Bianchi"));
		assertFalse(LabTurnAlternative.scan("123456Rossi"));
		assertFalse(LabTurnAlternative.scan("654321Bianchi"));
		assertFalse(LabTurnAlternative.scan("123456"));
		assertFalse(LabTurnAlternative.scan("Bianchi"));
	}
}