package es1.es1_5;

import junit.framework.TestCase;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class TurnLabNamedTest extends TestCase{
	TurnLabNamed turnLabNamed;
    @Override
    public void setUp() throws Exception {
        super.setUp();
        turnLabNamed = new TurnLabNamed();
    }
   @Test
    public void testscantT3() throws Exception {
	   assertTrue(turnLabNamed.scan("Rossi654321"));
	   assertFalse(turnLabNamed.scan("Rossi65 4321"));	
	   assertFalse(turnLabNamed.scan("Ros si 654321"));
        }
   @Test
    public void testscantT2() throws Exception {
	   assertTrue(turnLabNamed.scan("Bianchi123456"));
	   assertFalse(turnLabNamed.scan("Bianchi 1234 56"));
	   assertFalse(turnLabNamed.scan("De Gasperi123456"));
    }
}

