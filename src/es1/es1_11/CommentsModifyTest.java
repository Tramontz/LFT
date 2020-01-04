package es1.es1_11;


import junit.framework.TestCase;
import org.junit.Test;

public class CommentsModifyTest extends TestCase{

	CommentsModify commentsModify;
    @Override
    public void setUp() throws Exception {
        super.setUp();
        commentsModify = new CommentsModify();
    }
	    @Test
    public void testNoThirdA() throws Exception {
        assertFalse(commentsModify.scan("aaa/*/aa"));
        assertFalse(commentsModify.scan("a/**//***a"));
        assertFalse(commentsModify.scan("*aa/*aa"));
        assertFalse(commentsModify.scan("bbabbbbabbbabbb"));
    }
    @Test
    public void testThirdA() throws Exception {
        assertTrue(commentsModify.scan("aaa/****/aa"));
        assertTrue(commentsModify.scan("aa/*a*a*/"));
        assertTrue(commentsModify.scan("aaaa"));
        assertTrue(commentsModify.scan("*/a"));
        assertTrue(commentsModify.scan("/****/"));
        assertTrue(commentsModify.scan("/*aa*/"));
        assertTrue(commentsModify.scan("a/**/***a"));
        assertTrue(commentsModify.scan("a/**/***/a"));
        assertTrue(commentsModify.scan("a/**/aa/***/a"));
    }
}
