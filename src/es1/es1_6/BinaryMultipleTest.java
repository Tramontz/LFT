package es1.es1_6;

import junit.framework.TestCase;
import org.junit.Test;

public class BinaryMultipleTest extends TestCase{

	BinaryMultiple binaryMultiple;
    @Override
    public void setUp() throws Exception {
        super.setUp();
        binaryMultiple = new BinaryMultiple();
    }

    @Test
    public void testScanNoThreeZero() throws Exception {
        assertFalse(binaryMultiple.scan("0011110"));
        assertFalse(binaryMultiple.scan("010101"));
        assertFalse(binaryMultiple.scan("10214"));
    }
    @Test
    public void testScanThreeZero() throws Exception {
        assertTrue(binaryMultiple.scan("10101"));
        assertTrue(binaryMultiple.scan("110"));
    }
}