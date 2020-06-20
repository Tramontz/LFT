package es1.es1_10;
//Esempi di stringhe accettate: “/****/”, “/*a*a*/”, “/*a/**/”, “/**a///a/a**/”, “/**/”, “/*/*/”
//Esempi di stringhe non accettate: “/*/”, “/**/***/”

import junit.framework.TestCase;
import org.junit.Test;

public class CommentsTest extends TestCase{

    @Override
    public void setUp() throws Exception {
        super.setUp();
    }
	    @Test
    public void testComments() throws Exception {
	    	assertTrue(Comments.scan("/****/"));
	    	assertTrue(Comments.scan("/*a*a*/"));
	    	assertTrue(Comments.scan("/*a/**/"));
	    	assertTrue(Comments.scan("/**a///a/a**/"));
	    	assertTrue(Comments.scan("/**/"));
	    	assertTrue(Comments.scan("/*/*/"));
	    	assertFalse(Comments.scan("/*/"));
	    	assertFalse(Comments.scan("/**/***/"));
	    	assertFalse(Comments.scan("*/"));
	    	assertFalse(Comments.scan("bbabbbbabbbabbb"));
    }
}