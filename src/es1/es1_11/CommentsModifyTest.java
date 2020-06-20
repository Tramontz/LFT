package es1.es1_11;
/*
 Esempi di stringhe accettate: “aaa/**** /aa”, “aa/*a*a* /”, “aaaa”, “/**** /”, “/*aa* /”,
“* /a”, “a/** /***a”, “a/** /*** /a”, “a/** /aa/*** /a”
Esempi di stringhe non accettate: “aaa/* /aa”, “a/**//***a”, “aa/*aa”
 */

import junit.framework.TestCase;
import org.junit.Test;

public class CommentsModifyTest extends TestCase{

    @Override
    public void setUp() throws Exception {
        super.setUp();
    }
	    @Test
    public void testCommentsModify() throws Exception {
        assertFalse(CommentsModify.scan("aaa/*/aa"));
        assertFalse(CommentsModify.scan("a/**//***a"));
        assertFalse(CommentsModify.scan("*aa/*aa"));
        assertFalse(CommentsModify.scan("bbabbbbabbbabbb"));
        assertTrue(CommentsModify.scan("aaa/****/aa"));
        assertTrue(CommentsModify.scan("aa/*a*a*/"));
        assertTrue(CommentsModify.scan("aaaa"));
        assertTrue(CommentsModify.scan("*/a"));
        assertTrue(CommentsModify.scan("/****/"));
        assertTrue(CommentsModify.scan("/*aa*/"));
        assertTrue(CommentsModify.scan("a/**/***a"));
        assertTrue(CommentsModify.scan("a/**/***/a"));
        assertTrue(CommentsModify.scan("a/**/aa/***/a"));
    }

}
