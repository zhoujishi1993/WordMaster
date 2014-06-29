package controller;

import java.util.List;

import view.Frame;
import view.Frame.Status;
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

	
	public Controller(Frame mainFrame){
		   this.mainFrame = mainFrame;
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
			mainFrame.changeView(Status.END_PART);
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
	 * @return the accuracy, correct words, wrong words, total words of the
	 *         result.
	 */
	public Result getResult(Status step) {
		if (step == Status.END_TOTAL) {
			int totalCorrect = lexiconSheet.getTotalCorrect();
			int recitedWords = lexiconSheet.getTotalRecitedNum();
			double accurancy = Caculator.calCorrectness(recitedWords,
					totalCorrect);
			result = new Result(lexiconSheet.getLexiconName(),lexiconSheet.getTotalNum(),
					recitedWords, totalCorrect, recitedWords - totalCorrect,
					accurancy);
			return result;
		} else if (step == Status.END_PART) {
			int reciteCorrect = lexiconSheet.getCorrectNum();
			int reciteWords = lexiconSheet.getReciteNum();
			double accurancy = Caculator.calCorrectness(reciteWords,
					reciteCorrect);
		    result = new Result(lexiconSheet.getLexiconName(),lexiconSheet.getTotalNum(),
					reciteWords, reciteCorrect, reciteWords - reciteCorrect,
					accurancy);
			return result;
		}
		return null;
	}
	
	
	   /**
	 * @method  getStartList
	 * @param prefix The prefix of the string
	 * @return The list of string that have such prefix in the lexicon
	 */
	public List<String> getStartList(String prefix){
		   return lexiconSheet.startList(prefix);
	   }
	   
	   /**
	 * @method  getLexiconList
	 * @return 
	 */
	public List<String> getLexiconList(){
		   return lexiconSheet.getLexiconList();
	}



	public void changeView(Status step) {
		mainFrame.changeView(step);
	}

	public void setLexicon(String lexicon) {
		lexiconSheet.lexiconSelect(lexicon);
	}

	public boolean setStartPosition(int type, String word) {
		if (type == 1 || type == 2) {
			return lexiconSheet.setStartPos(type);
		} else if (type == 3 && word != null && !word.equals("")) {
			return lexiconSheet.setStartPos(word);
		}
		return false;
	}

	public boolean setReciteNum(int reciteNum) {
		return lexiconSheet.setReciteNum(reciteNum);
	}

}
