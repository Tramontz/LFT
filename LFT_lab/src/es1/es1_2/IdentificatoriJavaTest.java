package es1.es1_2;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by Mauro on 25/02/2015.
 */

public class IdentificatoriJavaTest extends TestCase {
	IdentificatoriJava ij;

	@Override
	public void setUp() throws Exception {
		super.setUp();
		ij = new IdentificatoriJava();
	}

	@Test
	public void testScan() throws Exception {
		assertTrue(IdentificatoriJava.scan("__abic12"));
		assertFalse(IdentificatoriJava.scan("_"));
		assertFalse(IdentificatoriJava.scan("abdasdas213123"));
		assertTrue(IdentificatoriJava.scan("__"));
	}
}