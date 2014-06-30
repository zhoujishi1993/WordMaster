package model_test;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import model.*;

public class TXTIOTest {

	private static IO io;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		io = new TXTIO();
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testReadInLexicon() {
		List<String> expResult = new ArrayList<String>();
		String[] str = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k",
				"l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w",
				"x", "y", "z" };
		expResult.addAll(Arrays.asList(str));
		assertEquals(expResult, io.readInLexicon());
	}

	@Test
	public void testNewUserLexicon() throws Exception {
		String username = "default";
		io.newUserLexicon(username);
		File dir = new File(username);
		assertNotNull(dir);
		assertTrue(dir.exists());
		assertTrue(dir.isDirectory());
		List<String> expResult = new ArrayList<String>();
		String[] str = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k",
				"l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w",
				"x", "y", "z" };
		expResult.addAll(Arrays.asList(str));
		List<String> ret = new ArrayList<String>();
		File[] file = dir.listFiles();
		for(File f: file) {
			ret.add(f.getName());
		}
		assertEquals(expResult, ret);
	}
	
	@Test
	public void testNewUserLexiconUserExisted() throws Exception {
		String username = "default";
		io.newUserLexicon(username);
		File dir = new File(username);
		assertNotNull(dir);
		assertTrue(dir.exists());
		assertTrue(dir.isDirectory());
		List<String> expResult = new ArrayList<String>();
		String[] str = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k",
				"l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w",
				"x", "y", "z" };
		expResult.addAll(Arrays.asList(str));
		List<String> ret = new ArrayList<String>();
		File[] file = dir.listFiles();
		for(File f: file) {
			ret.add(f.getName());
		}
		assertEquals(expResult, ret);
	}
	
	@Test
	public void testReadInWords() {
		String username = "default";
		String lexicon = "a";
		List<Word> words = io.readInWords(username, lexicon);
		String[] expResult = new String[]{"abandon", "adjustable", "alone", "axle"};
		int[] index = new int[]{0, 122, 250, 561};
		for(int i=0; i<index.length; i++) {
			String result = words.get(index[i]).getEnglish(); 
			assertEquals(expResult[i], result);
		}
	}

	@Test
	public void testReadInWordsNotExist() {
		String username = "XXXXX";
		String lexicon = "a";
		assertFalse(new File(username).exists() || new File(lexicon).exists());
		List<Word> words = io.readInWords(username, lexicon);
		assertEquals(0, words.size());
	}
	
	@Test
	public void testWriteLexiconNotExist() {
		String username = "test";
		List<Word> list = new ArrayList<Word>();
		list.add(new Word("test", "test", false, false, false));
		MyLexicon ml = new MyLexicon(list, "test");
		io.writeLexicon(ml, username);
		File file = new File("test/test");
		assertNotNull(file);
		assertFalse(file.exists());
	}
	
	@Test
	public void testWriteLexiconExist() {
		String username = "test";
		if(!(new File(username).exists()))
			new File(username).mkdir();
		List<Word> list = new ArrayList<Word>();
		list.add(new Word("test", "test", false, false, false));
		MyLexicon ml = new MyLexicon(list, "test");
		io.writeLexicon(ml, username);
		File file = new File("test/test");
		assertNotNull(file);
		assertTrue(file.exists());
		String expResult = "test test false false false";
		String str = "";
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"GBK"));
			String line;
			while((line=reader.readLine())!=null) {
				str += line;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		assertEquals(expResult, str);
	}
	
}
