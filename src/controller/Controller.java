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
		lexiconSheet = new LexiconSheet(user.getName());

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
		return lexiconSheet.getNextChinese();
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
		if (step == Frame.START) {
			int totalCorrect = lexiconSheet.getTotalCorrect();
			int totalWords = lexiconSheet.getTotalNum();
			double accurancy = Caculator.calCorrectness(totalWords,
					totalCorrect);
			Result result = new Result("本词库的统计情况", totalWords, totalCorrect,
					totalWords - totalCorrect, accurancy);
		} else if (step == Frame.END) {
			int reciteCorrect = lexiconSheet.getCorrectNum();
			int reciteWords = lexiconSheet.getReciteNum();
			double accurancy = Caculator.calCorrectness(reciteWords,
					reciteCorrect);
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

	public void changeView(int step, int type) {

	}

	public void setLexicon(String lexicon) {
		lexiconSheet.lexiconSelect(lexicon);
	}
}
