package controller;

import java.util.ArrayList;
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

   
   public static void main(String args[]){
	   //Shall get the lexicon
	   //Controller con = new Controller();
   }
   
   public Controller(Frame mainFrame){
	   this.mainFrame = mainFrame;
	   user = new User("default");
	   lexiconSheet = new LexiconSheet();
   }
   
   /**
 * @method  newInputWord
 * @param word to judge whether it is in the lexicon
 * @return true if the input word is in the lexicon
 */
public boolean newInputWord(String word){
	   return lexiconSheet.judgement(word);
   }
   //get the next Chinese meaning of the word
   /**
 * @method  showNextWord
 * @return  The next Chinese meanings of the list
 */
public String showNextWord(){
	   return lexiconSheet.getNextChinese();
   }
   
   /**
 * @method  getResult
 * @param step The current state of the mainFrame, if the step == Frame.START, then calculate the 
 * total result of the lexicon, if the step == Frame.END, then calculate the current result of this
 * recite
 * @return the accurancy, correct words, wrong words, total words of the result. 
 */
public Result getResult(Status step){
	   if(step == Status.END_TOTAL){
		   int totalCorrect = lexiconSheet.getTotalCorrect();
		   int totalWords = lexiconSheet.getTotalNum();
		   double accurancy = Caculator.calCorrectness(totalWords, totalCorrect);
		   Result result = new Result("本词库的统计情况",	totalWords,totalCorrect,totalWords - totalCorrect,accurancy);
	   }else if (step == Status.END_PART){
		   int reciteCorrect = lexiconSheet.getCorrectNum();
		   int reciteWords = lexiconSheet.getReciteNum();
		   double accurancy = Caculator.calCorrectness(reciteWords, reciteCorrect);
	   }
	return result;
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
	   List<String> a = new ArrayList<String>();
	   a.add("a");
	   a.add("b");
	   return a;
	   //return lexiconSheet.getLexiconList();
   }
   
   public void changeView(Status status){
	   mainFrame.changeView(status);
   }
   
   public void setLexicon(String lexicon){
	   lexiconSheet.lexiconSelect(lexicon);
   }



	public boolean setStartPos(int type, String text) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean setReciteNum(int parseInt) {
		// TODO Auto-generated method stub
		return false;
	}

}
