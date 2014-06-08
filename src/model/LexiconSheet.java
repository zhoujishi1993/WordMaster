package model;

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

	public List<String> getLexiconList() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getNextChinese() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
}
