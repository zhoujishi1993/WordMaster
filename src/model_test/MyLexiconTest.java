package model_test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.*;

public class MyLexiconTest {

	private static MyLexicon ml;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		List<Word> list = new ArrayList<Word>();
		for(int i=0; i<5; i++) {
			String word = "test"+i;
			list.add(new Word(word, word, false, false, false));
		}
		ml = new MyLexicon(list, "test");
	}

	@Test
	public void testStartList() {
		String prefix = "test";
		List<String> re = ml.startList(prefix);
		List<String> exp = new ArrayList<String>();
		for(int i=0; i<5; i++) {
			exp.add("test"+i);
		}
		assertEquals(exp, re);
	}
	
	@Test
	public void testStartListNotExist() {
		String prefix = "XX";
		List<String> re = ml.startList(prefix);
		assertEquals(0, re.size());
	}
	
	@Test
	public void testStartListNull() {
		String prefix = null;
		List<String> re = ml.startList(prefix);
		assertEquals(0, re.size());
	}

	@Test
	public void testSetReciteNumZero() {
		ml.setStartPos(1);
		assertTrue(ml.setReciteNum(0));
		assertEquals(0, ml.getRecitedNum());
	}
	
	@Test
	public void testSetReciteNumNormal() {
		ml.setStartPos(1);
		assertTrue(ml.setReciteNum(4));
		assertEquals(4, ml.getRecitedNum());
	}
	
	@Test
	public void testSetReciteNumMax() {
		ml.setStartPos(1);
		assertTrue(ml.setReciteNum(5));
		assertEquals(5, ml.getRecitedNum());
	}
	
	@Test
	public void testSetReciteNumOutOfBound() {
		ml.setStartPos(1);
		assertFalse(ml.setReciteNum(6));
		assertEquals(5, ml.getRecitedNum());
	}
	
	@Test
	public void testSetReciteNumMoreBigger() {
		ml.setStartPos(1);
		assertFalse(ml.setReciteNum(10));
		assertEquals(5, ml.getRecitedNum());
	}
	
	@Test
	public void testSetStartPosStringType1() {
		ml.setStartPos(1);
		assertFalse(ml.setReciteNum(6));
		assertEquals(5, ml.getRecitedNum());
		assertEquals("test0", ml.getNext());
	}
	
	@Test
	public void testSetStartPosStringType2() {
		ml.setStartPos(2);
		assertFalse(ml.setReciteNum(6));
		assertEquals(5, ml.getRecitedNum());
		assertEquals("test0", ml.getNext());
	}
	
	@Test
	public void testSetStartPosStringType3() {
		ml.setStartPos("test2");
		assertFalse(ml.setReciteNum(6));
		assertEquals(3, ml.getRecitedNum());
		assertEquals("test2", ml.getNext());
	}
	
	@Test
	public void testGetNext() {
		ml.setStartPos(1);
		assertTrue(ml.setReciteNum(3));
		assertEquals("test0", ml.getNext());
		assertEquals("test1", ml.getNext());
		assertEquals("test2", ml.getNext());
		assertNull(ml.getNext());
	}
	
	@Test
	public void testJudgement() {
		ml.setStartPos(1);
		assertTrue(ml.setReciteNum(4));
		assertEquals("test0", ml.getNext());
		assertTrue(ml.judgement("test0"));
		assertEquals("test1", ml.getNext());
		assertFalse(ml.judgement("XXX"));
		assertEquals("test2", ml.getNext());
		assertFalse(ml.judgement(""));
		assertEquals("test3", ml.getNext());
		assertFalse(ml.judgement(null));
		assertNull(ml.getNext());
	}
	
	@Test
	public void testGetCorrectNum() {
		assertEquals(0, ml.getCorrectNum());
		ml.setStartPos(1);
		assertTrue(ml.setReciteNum(5));
		ml.getNext();
		ml.judgement("test0");
		assertEquals(1, ml.getCorrectNum());
		ml.getNext();
		ml.judgement("");
		assertEquals(1, ml.getCorrectNum());
		ml.getNext();
		ml.judgement("test2");
		assertEquals(2, ml.getCorrectNum());
		ml.getNext();
		ml.judgement("test3");
		assertEquals(3, ml.getCorrectNum());
	}
	
	@Test
	public void testGetTotalNum() {
		assertEquals(5, ml.getTotalNum());
	}
	
	public void testGetTotalRecitedNum() {
		assertEquals(0, ml.getTotalRecitedNum());
		ml.setStartPos(1);
		assertTrue(ml.setReciteNum(5));
		ml.getNext();
		ml.judgement("test0");
		assertEquals(1, ml.getTotalRecitedNum());
		ml.getNext();
		ml.judgement("");
		assertEquals(2, ml.getTotalRecitedNum());
		ml.getNext();
		ml.judgement("test2");
		assertEquals(3, ml.getTotalRecitedNum());
		ml.getNext();
		ml.judgement("test3");
		assertEquals(4, ml.getTotalRecitedNum());
	}
	
	@Test
	public void testGetTotalCorrect() {
		assertEquals(0, ml.getTotalCorrect());
		ml.setStartPos(1);
		ml.setReciteNum(5);
		ml.getNext();
		ml.judgement("test0");
		assertEquals(1, ml.getTotalCorrect());
		ml.getNext();
		ml.judgement("");
		assertEquals(1, ml.getTotalCorrect());
		ml.getNext();
		ml.judgement("test2");
		assertEquals(2, ml.getTotalCorrect());
		ml.getNext();
		ml.judgement("test3");
		assertEquals(3, ml.getTotalCorrect());
	}
	
	@Test
	public void testSetStartWord() {
		ml.setStartPos(1);
		ml.setReciteNum(2);
		assertEquals("test0", ml.getNext());
		assertEquals("test1", ml.getNext());
		assertNull(ml.getNext());
		ml.setStartWord();
		ml.setStartPos(2);
		ml.setReciteNum(2);
		assertEquals("test2", ml.getNext());
		assertEquals("test3", ml.getNext());
		assertNull(ml.getNext());
		ml.setStartWord();
		ml.setStartPos(2);
		ml.setReciteNum(2);
		assertEquals(1, ml.getRecitedNum());
		assertEquals("test4", ml.getNext());
		assertEquals(1, ml.getRecitedNum());
		assertEquals(5, ml.getTotalRecitedNum());
		ml.setStartWord();
		ml.setReciteNum(5);
	}
	
	@Test
	public void testFlow() {
		int cal = 0;
		assertEquals(0, ml.getCorrectNum());
		assertEquals(5, ml.getTotalNum());
		assertEquals(0, ml.getTotalCorrect());
		ml.setStartPos(1);	//from start
		assertTrue(ml.setReciteNum(4));
		assertEquals(4, ml.getRecitedNum());
		for(int i=0; i<4; i++) {
			assertEquals("test"+i, ml.getNext());
			assertTrue(ml.judgement("test"+i));
		}
		assertNull(ml.getNext());
		assertEquals(4, ml.getCorrectNum());
		assertEquals(5, ml.getTotalNum());
		assertEquals(4, ml.getTotalCorrect());
		assertEquals(4, ml.getRecitedNum());
	}

}
