package testController;

import java.util.ArrayList;
import java.util.List;

/**
 * @type LexiconSheetStub.java
 * @author zhoujishi
 * @version 1.0
 * @description this is a stub class that mock the activation of LexiconSheet class
 */
public class LexiconSheetStub {
    /**
     * @method judgement
     * @return
     * @description it mocks the judgment function in LexiconSheet
     * it always return true
     */
    public boolean judgement(String word){
    	return true;
    }
    
    /**
     * @method getNextChinese
     * @return
     * @description it mocks the getNextChinese function in LexiconSheet
     * it always return "next"
     */
    public String getNextChinese(){
    	return "Next";
    }
    
    /**
     * @method getTotalCorrect
     * @return
     * @description it mocks the getTotalCorrect function in LexiconSheet
     * it always return 10
     */
    public int getTotalCorrect(){
    	return 10;
    }
    
    /**
     * @method getTotalNum
     * @return
     * @description it mocks the getTotalCorrect function in LexiconSheet
     * it always return 100
     */
    public int getTotalNum(){
    	return 100;
    }
    
    /**
     * @method getCorrectNum
     * @return
     * @description it mocks the getCorrectNum function in LexiconSheet
     * it always return 1
     */
    public int getCorrectNum(){
    	return 1;
    }
    
    /**
     * @method getReciteNum
     * @return
     * @description it mocks the getReciteNum function in LexiconSheet 
     * it always return 10
     */
    public int getReciteNum(){
    	return 10;
    }
    
    /**
     * @method getLexiconList
     * @return
     * @description it mocks the getLexiconList function in LexiconSheet
     * it always return a simple ArrayList with one element "lexiconList"
     */
    public List<String> getLexiconList(){
    	ArrayList<String> lexiconList = new ArrayList<String>();
    	lexiconList.add("lexiconList");
    	return lexiconList;

    }
    
    /**
     * @method getStartList
     * @return
     * @description it mocks the getStartList function in LexiconSheet
     */
    public List<String> startList(String prefix){
    	ArrayList<String> startList = new ArrayList<String>();
    	startList.add("startList");
    	return startList;
    }
    
    /** @type boolean
     *  @description it mark the state 
     */
    private String lexicon ;
    /**
     * @method LexiconSelect
     * @description it mocks the LexiconSelect function in LexiconSheet
     * it change the state of lexicon 
     */
    public void lexiconSelect(String lex){
    	lexicon = lex;
    }
    
    /**
     * @method getLexiconSelect
     * @return
     * @description this is a method to get the state of lexiconSheet
     */
    public String getLexiconSelect(){
    	return lexicon;
    }
    
    /** @type int
     *  @description this
     */
    private int startPosition;
    /**
     * @method setStartPosition
     * @param step
     * @description it mocks the setStartPosition(int) function of lexiconSheet
     */
    public void setStartPosition(int step){
    	startPosition = step;
    }
    
    /**
     * @method getIntStartPosition
     * @return
     * @description use to track the startPosition
     */
    public int getIntStartPosition(){
    	return startPosition;
    }
    
    /** @type String
     *  @description use to mark the state
     */
    private String word;
    
    /**
     * @method setStartPosition
     * @param word
     * @description this mocks the setStartPosition(String word) function in LexicionSheet
     */
    public void setStartPosition(String word){
    	this.word = word;
    }
    
    /**
     * @method getWord
     * @return
     * @description use to track the word 
     */
    public String getWord(){
    	return word;
    }
    
    /** @type int
     *  @description this mark the state of reciteNum
     */
    private int reciteNum;
    /**
     * @method setReciteNum
     * @param num
     * @description it mocks the setReciteNum function in LexiconSheet
     */
    public boolean setReciteNum(int num){
    	reciteNum = num;
    	return true;
    }
    
    /**
     * @method getReciteNums
     * @return
     * @description use to track the recite num
     */
    public int getReciteNums(){
    	return reciteNum;
    }
 
    
    /**
     * @method getTotalReciteNum
     * @return
     * @description it mocks the getTotalReciteNum function in LexiconSheet
     */
    public int getTotalRecitedNum(){
    	return 100;
    }
    
    /** @type String
     *  @description this mark the state 
     */
    private String startWord;
    /**
     * @method setStartWord
     * @description it mocks the setStartWord function in LexiconSheet
     */
    public void setStartWord(){
    	word = "startWord";
    }
    
    public String getStartWord(){
    	return startWord;
    }
    
    
    /**
     * @method getLexiconName
     * @return
     * @description it mocks the getLexiconName function in LexiconSheet
     */
    public String getLexiconName(){
        return "lexiconName";
    }
}
