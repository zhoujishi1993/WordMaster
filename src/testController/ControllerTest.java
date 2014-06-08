package testController;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ControllerTest {
	private static Controller con;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		con = new Controller();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testNewInputWord() {
		boolean judge = con.newInputWord("word");
		assertTrue(judge);
	}

	@Test
	public void testShowNextWord() {
		String nextWord = con.showNextWord();
		assertEquals(nextWord, "Next");
	}

	@Test
	public void testGetResult() {
		ResultStub result1 = con.getResult(4);
		assertEquals(result1.getName(), "lexiconName");
		assertEquals(result1.getTotal(), 100);
		assertEquals(result1.getRecited(), 100);
		assertEquals(result1.getCorrect(), 10);
		assertEquals(result1.getWrong(), 90);
		assertEquals(result1.getAccurancy(), 0.1, 0);

		ResultStub result2 = con.getResult(5);
		assertEquals(result2.getName(), "lexiconName");
		assertEquals(result2.getTotal(), 100);
		assertEquals(result2.getRecited(), 10);
		assertEquals(result2.getCorrect(), 1);
		assertEquals(result2.getAccurancy(), 0.1, 0);

	}

	@Test
	public void testGetStartList() {
		List<String> startList = con.getStartList("prefix");
		assertEquals(startList.get(0), "startList");
	}

	@Test
	public void testGetLexiconList() {
		List<String> lexiconList = con.getLexiconList();
		assertEquals(lexiconList.get(0), "lexiconList");
	}

	@Test
	public void testChangeView() {
		con.changeView(3);
		assertEquals(con.getFrame().getView(), 3, 0);
	}

	@Test
	public void testSetLexicon() {
		con.setLexicon("lexicon");
		assertEquals(con.getLexionSheet().getLexiconSelect(), "lexicon");
	}

	@Test
	public void testSetStartPosition() {
		con.setStartPosition(1, null);
		assertEquals(con.getLexionSheet().getIntStartPosition(),1);
		con.setStartPosition(0, "abcd");
		assertEquals(con.getLexionSheet().getWord(),"abcd");
	}

	@Test
	public void testSetReciteNum() {
		con.setReciteNum(20);
		assertEquals(con.getLexionSheet().getReciteNums(),20);
	}

}
