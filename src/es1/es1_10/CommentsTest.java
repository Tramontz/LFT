package es1.es1_10;


import junit.framework.TestCase;
import org.junit.Test;

public class CommentsTest extends TestCase{

	Comments comments;
    @Override
    public void setUp() throws Exception {
        super.setUp();
        comments = new Comments();
    }
	    @Test
    public void testComments() throws Exception {
	    	assertTrue(comments.scan("/****/"));
	    	assertTrue(comments.scan("/*a*a*/"));
	    	assertTrue(comments.scan("/*a/**/"));
	    	assertTrue(comments.scan("/**a///a/a**/"));
	    	assertTrue(comments.scan("/**/"));
	    	assertTrue(comments.scan("/*/*/"));
	    	assertFalse(comments.scan("/*/"));
	    	assertFalse(comments.scan("/**/***/"));
	    	assertFalse(comments.scan("*/"));
	    	assertFalse(comments.scan("bbabbbbabbbabbb"));
    }
}