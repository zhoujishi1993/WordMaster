package model;

import java.util.Iterator;
import java.util.List;

/**
 * LexiconSheet
 * @author fxiangyi@gmail.com
 * @version 2.0
 */

public class LexiconSheet {
	
	private MyLexicon myLexicon;
	private List<String> LexiconList;
	private IO IO;
	
	private String username;
	
	/**
	 * constructor
	 * @param myLexicon
	 * @param lexiconList
	 * @param iO
	 */
	
	public LexiconSheet() {
		IO = new TXTIO();
		username = "default";
	}
	
	public LexiconSheet(MyLexicon myLexicon, List<String> lexiconList,
			model.IO iO) {
		super();
		this.myLexicon = myLexicon;
		LexiconList = lexiconList;
		IO = iO;
	}
	
	public void lexiconSelect(String Lexicon) {
		myLexicon = new MyLexicon(IO.readInWords(username, Lexicon), Lexicon);
		LexiconList = IO.readInLexicon();
	}
	
	
	public List<String> getLexiconList() {
		return IO.readInLexicon();
	}
	
	public String getNextChinese() {
		return myLexicon.getNext();
	}
	
	/**
	 * startList
	 * @param prefix
	 * @return
	 */
	public List<String> startList(String prefix) {
		return myLexicon.startList(prefix);
	}
	
	/**
	 * judgement
	 * @param input
	 * @return
	 */
	public boolean judgement(String input) {
		return myLexicon.judgement(input);
	}
	
	/**
	 * setReciteNum
	 * @param reciteNum
	 * @return
	 */
	public boolean setReciteNum(int reciteNum) {
		return myLexicon.setReciteNum(reciteNum);
	}
	
	/**
	 * getCorrectNum
	 * @return
	 */
	public int getCorrectNum() {
		return myLexicon.getCorrectNum();
	}
	
	/**
	 * getReciteNum
	 * @return
	 */
	public int getReciteNum() {
		return myLexicon.getRecitedNum();
	}
	
	/**
	 * getTotalNum
	 * @return
	 */
	public int getTotalNum() {
		return myLexicon.getTotalNum();
	}
	

	/**
	 * setStartPos
	 * @param type
	 * @return 
	 */
	public boolean setStartPos(int type) {
		return myLexicon.setStartPos(type);
		
	}
	
	/**
	 * setStartPos
	 * @param word
	 * @return 
	 */
	public boolean setStartPos(String word) {
		return myLexicon.setStartPos(word);
		 
	}


	
	/**
	 * getTotalCorrect
	 * @return
	 */
	public int getTotalCorrect() {
		return myLexicon.getTotalCorrect();
	}


	
	/**
	 * getLexiconName
	 * @return
	 */
	public String getLexiconName() {
		return this.myLexicon.getName();
	}
	
	/**
	 * setStartWord
	 */
	public void setStartWord() {
		this.myLexicon.setStartWord();
		IO.writeLexicon(myLexicon, username);
	}
	
	public int getTotalRecitedNum() {
		return this.myLexicon.getTotalRecitedNum();
	}

	
}
