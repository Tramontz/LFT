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
    public void testScanBinaryMultiple() throws Exception {
        assertTrue(binaryMultiple.scan("110"));
        assertTrue(binaryMultiple.scan("1001"));
        assertTrue(binaryMultiple.scan("0110"));
        assertFalse(binaryMultiple.scan("0001111"));
        assertFalse(binaryMultiple.scan("10"));
        assertFalse(binaryMultiple.scan("111"));
    }
}