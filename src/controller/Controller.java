package controller;

import java.util.List;

import model.*;

/**
 * @type Controller.java
 * @author zhoujishi
 * @version
 */
public class Controller {
	private LexiconSheet lexiconSheet;
	private Frame mainFrame;
	private User user;
	private int step;
	private Result result;

	public static void main(String args[]) {
		// Shall get the lexicon
		Controller con = new Controller();
	}

	public Controller() {
		mainFrame = new Frame();
		user = new User("default");
		lexiconSheet = new LexiconSheet();

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
		}else{
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
	public Result getResult(int step) {
		if (step == 4) {
			int totalCorrect = lexiconSheet.getTotalCorrect();
			int recitedWords = lexiconSheet.getTotalRecitedNum();
			double accurancy = Caculator.calCorrectness(recitedWords,
					totalCorrect);
			result = new Result(lexiconSheet.getLexiconName(),lexiconSheet.getTotalNum(),
					recitedWords, totalCorrect, recitedWords - totalCorrect,
					accurancy);
			return result;
		} else if (step == 5) {
			int reciteCorrect = lexiconSheet.getCorrectNum();
			int reciteWords = lexiconSheet.getReciteNum();
			double accurancy = Caculator.calCorrectness(reciteWords,
					reciteCorrect);
		    result = new Result(lexiconSheet.getLexiconName(),lexiconSheet.getTotalNum(),
					reciteWords, reciteCorrect, reciteWords - reciteCorrect,
					accurancy);
			return result;
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
			lexiconSheet.setStartPos(type);
		} else if (type == 0 && word != null && !word.equals("")) {
			lexiconSheet.setStartPos(word);
		}
	}

	public boolean setReciteNum(int reciteNum) {
		return lexiconSheet.setReciteNum(reciteNum);
	}

}
