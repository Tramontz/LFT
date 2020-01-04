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

    TreZeri treZeri;
    @Override
    public void setUp() throws Exception {
        super.setUp();
        treZeri = new TreZeri();
    }

    @Test
    public void testScanNoThreeZero() throws Exception {
        assertFalse(treZeri.scan("0011110"));
        assertFalse(treZeri.scan("010101"));
        assertFalse(treZeri.scan("10214"));
    }
    @Test
    public void testScanThreeZero() throws Exception {
        assertTrue(treZeri.scan("000111"));
    }
}