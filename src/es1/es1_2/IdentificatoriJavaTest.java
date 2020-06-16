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
		assertTrue(IdentificatoriJava.scan("x"));
		assertTrue(IdentificatoriJava.scan("flag1"));
		assertTrue(IdentificatoriJava.scan("x_1"));
		assertTrue(IdentificatoriJava.scan("lft_lab"));
		assertTrue(IdentificatoriJava.scan("_temo"));
		assertTrue(IdentificatoriJava.scan("x_1_y_2"));
		assertTrue(IdentificatoriJava.scan("x____"));
		assertTrue(IdentificatoriJava.scan("___5"));
		assertTrue(IdentificatoriJava.scan("abdasdas213123"));
		assertFalse(IdentificatoriJava.scan("_"));
		assertFalse(IdentificatoriJava.scan("___"));
		assertFalse(IdentificatoriJava.scan("5"));
		assertFalse(IdentificatoriJava.scan("221B"));
		assertFalse(IdentificatoriJava.scan("123"));
		assertFalse(IdentificatoriJava.scan("9_to_5"));
	}
}