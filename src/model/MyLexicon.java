package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * MyLexicon
 * @author fxiangyi@gmail.com
 * @version 2.0
 */

public class MyLexicon extends Lexicon {

	private int recitePos;
	private int startPos;
	private boolean[] correctness;
	private int reciteNum;
	
	public MyLexicon(List<Word> words, String name) {
		super(words, name);
		this.correctness = new boolean[words.size()];
		for(int i=0; i<this.correctness.length; i++) 
			this.correctness[i] = false;
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * startList
	 * @param prefix regex
	 * @return english started with prefix
	 */
	public List<String> startList(String prefix) {
		List<String> ret = new ArrayList<String>();
		if(prefix==null)
			return ret;
		String regex = prefix;
		Iterator<Word> it = this.words.iterator();
		String temp;
		while(it.hasNext()) {
			temp = it.next().getEnglish();
			if(temp.startsWith(regex)) {
				ret.add(temp);
			}
		}
		return ret;
	}
	
	/**
	 * judgement
	 * @param input input
	 * @return true or false
	 */
	public boolean judgement(String input) {
		int prev = this.recitePos-1;
		boolean ret = this.words.get(prev).getEnglish().equals(input);
		this.words.get(prev).setRight(ret);
		this.correctness[prev] = ret;
		return ret;
	}
	
	/**
	 * isOutOfBound
	 * @return true or false
	 * 			true out of bound
	 */
	private boolean isOutOfBound(int reciteNum) {
		return this.startPos+reciteNum > this.words.size();
	}
	
	/**
	 * setReciteNum
	 * @param reciteNum number of recite words
	 * @return 
	 * 			true reciteNum = reciteNum
	 * 			false reciteNum = words.size()-this.startPos
	 */
	public boolean setReciteNum(int reciteNum) {
		if(!isOutOfBound(reciteNum)) {
			this.reciteNum = reciteNum;
			return true;
		} else {
			this.reciteNum = this.words.size()-this.startPos;
			return false;
		}
	}
	
	/**
	 * getCorrectNum
	 * @return correct num
	 */
	public int getCorrectNum() {
		int cal = 0;
		for(int i=0; i<this.correctness.length; i++) {
			if(this.correctness[i])
				cal++;
		}
		return cal;
	}
	
	/**
	 * getTotalNum
	 * @return total number
	 */
	public int getTotalNum() {
		return this.words.size();
	}
	
	/**
	 * getTotalRecitedNum
	 * @return
	 */
	public int getTotalRecitedNum() {
		Iterator<Word> it = this.words.iterator();
		int cal = 0;
		while(it.hasNext()) {
			if(it.next().isRecited())
				cal++;
		}
		return cal;
	}
	
	/**
	 * getRecitedNum
	 * @return recited number
	 */
	public int getRecitedNum() {
		return this.reciteNum;
	}
	
	/**
	 * setStartPos
	 * @param type
	 * 			2 last recited
	 */
	public void setStartPos(int type) {
		boolean ret = false;
		switch(type) {
		case 1:
			this.startPos = 0;
			//Notice!!
			this.recitePos = 0;
			ret = true;
			break;
		case 2:
			Iterator<Word> it = this.words.iterator();
			int cal = 0;
			while(it.hasNext()) {
				
				if(it.next().isStart() && cal!=this.words.size()) {
					this.startPos = cal;
					//Notice!!
					this.recitePos = cal;
					ret = true;
					break;
				}
				cal++;
			}
			if(!ret) {
				this.startPos = 0;
				//Notice!!
				this.recitePos = 0;
				ret = true;
			}
			break;
		}
		//return ret;
	}
	
	/**
	 * setStartPos
	 * @param word input English\
	 */
	public void setStartPos(String word) {
		boolean ret = false;
		int cal = 0;
		Iterator<Word> it = this.words.iterator();
		while(it.hasNext()) {
			if(it.next().getEnglish().equals(word)) {
				this.startPos = cal;
				this.recitePos = cal;
				ret = true;
			}
			cal++;
		}
		//return ret;
	}
	
	/**
	 * getTotalCorrect
	 * @return total correct number
	 */
	public int getTotalCorrect() {
		int cal = 0;
		Iterator<Word> it = this.words.iterator();
		while(it.hasNext()) {
			if(it.next().isRight())
				cal ++;
		}
		return cal;
	}
	
	/**
	 * getNext
	 * @return next english
	 */
	public String getNext() {
		if(this.recitePos>=this.startPos+this.reciteNum)
			return null;
		Word ret = this.words.get(this.recitePos++);
		ret.setRecited(true);
		/*if((this.recitePos++)==(this.startPos+this.reciteNum)) {
			Iterator<Word> it = this.words.iterator();
			Word temp;
			while(it.hasNext()) {
				temp = it.next();
				if(temp.isStart()) {
					temp.setStart(false);
					break;
				}
			}
			ret.setStart(true);
		}*/
		return ret.getEnglish();
	}
	
	/**
	 * setStartWord
	 */
	public void setStartWord() {
		Iterator<Word> it = this.words.iterator();
		Word temp;
		while(it.hasNext()) {
			temp = it.next();
			if(temp.isStart()) {
				temp.setStart(false);
				break;
			}
		}
		if(this.recitePos >= words.size())
			this.words.get(0).setStart(true);
		else
			this.words.get(this.recitePos).setStart(true);
	}
}
