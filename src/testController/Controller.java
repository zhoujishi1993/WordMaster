package testController;

import java.util.List;

import javax.net.ssl.SSLEngineResult.Status;

import model.Result;
import controller.Caculator;

/**
 * @type Controller.java
 * @author zhoujishi
 * @version !import This class is same with the Controller class in controller
 *          package This class is used for test and the Class name is replaced
 *          with Stub
 * */
public class Controller {
	private LexiconSheetStub lexiconSheet;
	private FrameStub mainFrame;
	private UserStub user;
	private int step;
	private ResultStub result;

	public static void main(String args[]) {
		// Shall get the lexicon
		Controller con = new Controller();
	}

	public Controller() {
		mainFrame = new FrameStub();
		user = new UserStub("default");
		lexiconSheet = new LexiconSheetStub();
	}

	/**
	 * @method newInputWord
	 * @param word
	 *            to judge whether it is in the lexicon
	 * @return true if the input word is in the lexicon
	 */
	public boolean newInputWord(String word) {
		return lexiconSheet.judgement(word);
	}

	// get the next Chinese meaning of the word

	/**
	 * @method showNextWord
	 * @return The next Chinese meanings of the list
	 */
	public String showNextWord() {
		String nextWord = lexiconSheet.getNextChinese();
		if (nextWord != null) {
			return nextWord;
		} else {
			lexiconSheet.setStartWord();
			mainFrame.changeView(5);
			return null;
		}
	}

	/**
	 * @method getResult
	 * @param step
	 *            The current state of the mainFrame, if the step ==
	 *            Frame.START, then calculate the total result of the lexicon,
	 *            if the step == Frame.END, then calculate the current result of
	 *            this recite
	 * @return the accurancy, correct words, wrong words, total words of the
	 *         result.
	 */
	public ResultStub getResult(int step) {
		if (step == 4) {
			int totalCorrect = lexiconSheet.getTotalCorrect();
			int recitedWords = lexiconSheet.getTotalRecitedNum();
			double accurancy = Caculator.calCorrectness(recitedWords,
					totalCorrect);
		    result = new ResultStub(lexiconSheet.getLexiconName(),
					lexiconSheet.getTotalNum(), recitedWords, totalCorrect,
					recitedWords - totalCorrect, accurancy);
			return result;
		} else if (step == 5) {
			int reciteCorrect = lexiconSheet.getCorrectNum();
			int reciteWords = lexiconSheet.getReciteNum();
			double accurancy = Caculator.calCorrectness(reciteWords,
					reciteCorrect);
			result = new ResultStub(lexiconSheet.getLexiconName(),
					lexiconSheet.getTotalNum(), reciteWords, reciteCorrect,
					reciteWords - reciteCorrect, accurancy);
			return result;
		} else {
			return null;
		}
	}

	/**
	 * @method getStartList
	 * @param prefix
	 *            The prefix of the string
	 * @return The list of string that have such prefix in the lexicon
	 */
	public List<String> getStartList(String prefix) {
		return lexiconSheet.startList(prefix);
	}

	/**
	 * @method getLexiconList
	 * @return
	 */
	public List<String> getLexiconList() {
		return lexiconSheet.getLexiconList();
	}

	public void changeView(int step) {
		mainFrame.changeView(step);
	}

	public void setLexicon(String lexicon) {
		lexiconSheet.lexiconSelect(lexicon);
	}

	public void setStartPosition(int type, String word) {
		if (type == 1 || type == 2) {
			lexiconSheet.setStartPosition(type);
		} else if (type == 0 && word != null && !word.equals("")) {
			lexiconSheet.setStartPosition(word);
		}
	}

	public boolean setReciteNum(int reciteNum) {
		return lexiconSheet.setReciteNum(reciteNum);
	}
	
	/**
	 * @method getFrame
	 * @return
	 * @description this function is only used for test to get the mainframe
	 */
	public FrameStub getFrame(){
		return mainFrame;
	}

	/**
	 * @method getLexionSheet
	 * @return
	 * @description this function is only used for test to get the lexiconsheet
	 */
	public LexiconSheetStub getLexionSheet(){
		return lexiconSheet;
	}
}
