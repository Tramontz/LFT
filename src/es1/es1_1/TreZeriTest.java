package es1.es1_1;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * Esercizio 1.1
 * Copiare il codice in Figura 2, compilarlo e accertarsi che funzioni correttamente
 * testandolo su un insieme significativo di stringhe, per es. 010101, 1100011001, 10214, ecc.
 * Come deve essere modificato il codice per riconoscere il linguaggio complementare, ovvero il
 * linguaggio delle stringhe di 0 e 1 che non contengono 3 zeri consecutivi?
 */
public class TreZeriTest extends TestCase{


    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    @Test
    public void testScanThreeZero() throws Exception {
    	assertTrue(TreZeri.scan("000111"));
    	assertTrue(TreZeri.scan("0000111"));
    	assertTrue(TreZeri.scan("10101001111001000111"));
        assertFalse(TreZeri.scan("0011110"));
        assertFalse(TreZeri.scan("010101"));
        assertFalse(TreZeri.scan("10214"));
    }
    @Test
    public void testScanThreeZeroMod() throws Exception {
    	assertFalse(TreZeriMod.scan("000111"));
    	assertFalse(TreZeriMod.scan("0000111"));
    	assertFalse(TreZeriMod.scan("10101001111001000111"));
    	assertFalse(TreZeriMod.scan("10214"));
    	assertTrue(TreZeriMod.scan("0011110"));
    	assertTrue(TreZeriMod.scan("010101")); 
    	assertTrue(TreZeriMod.scan("10101001111001001101"));
    }
}