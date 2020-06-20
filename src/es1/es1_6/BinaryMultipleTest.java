package es1.es1_6;

import junit.framework.TestCase;
import org.junit.Test;

public class BinaryMultipleTest extends TestCase{

    @Override
    public void setUp() throws Exception {
        super.setUp();
    }
    @Test
    public void testScanBinaryMultiple() throws Exception {
    	assertTrue(BinaryMultiple.scan("0"));
        assertTrue(BinaryMultiple.scan("110"));
        assertTrue(BinaryMultiple.scan("1001"));
        assertTrue(BinaryMultiple.scan("0110"));
        assertTrue(BinaryMultiple.scan("11"));
        assertTrue(BinaryMultiple.scan("0001111"));
        assertTrue(BinaryMultiple.scan("11011011000")); //(1752)
        assertTrue(BinaryMultiple.scan("0000000000000000000000000000000000000000000110"));
        assertFalse(BinaryMultiple.scan("10"));
        assertFalse(BinaryMultiple.scan("111"));
        assertFalse(BinaryMultiple.scan("00000000000000101100"));
        assertFalse(BinaryMultiple.scan("1000000110000"));//4144
    }
}