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
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * startList
	 * @param prefix regex
	 * @return english started with prefix
	 */
	public List<String> startList(String prefix) {
		List<String> ret = new ArrayList<String>();
		String regex = "^"+prefix;
		Iterator<Word> it = this.words.iterator();
		String temp;
		while(it.hasNext()) {
			temp = it.next().getEnglish();
			if(temp.matches(regex)) {
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
		boolean ret = this.words.get(this.recitePos).getEnglish().equals(input);
		this.words.get(this.recitePos).setRight(ret);
		return ret;
	}
	
	/**
	 * isOutOfBound
	 * @return true or false
	 */
	private boolean isOutOfBound() {
		return this.startPos+this.reciteNum <= this.words.size();
	}
	
	/**
	 * setReciteNum
	 * @param reciteNum number of recite words
	 * @return 
	 * 			true reciteNum = reciteNum
	 * 			false reciteNum = words.size()-this.startPos
	 */
	public boolean setReciteNum(int reciteNum) {
		if(isOutOfBound()) {
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
	 * @return true or
	 * 			false no last recited word
	 */
	public boolean setStartPos(int type) {
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
				cal++;
				if(it.next().isStart() && cal!=this.words.size()) {
					this.startPos = cal;
					//Notice!!
					this.recitePos = cal;
					ret = true;
					break;
				}
			}
			break;
		}
		return ret;
	}
	
	/**
	 * setStartPos
	 * @param word input English
	 * @return true or
	 * 			false: no such word
	 */
	public boolean setStartPos(String word) {
		boolean ret = false;
		int cal = 0;
		Iterator<Word> it = this.words.iterator();
		while(it.hasNext()) {
			if(it.next().getEnglish().equals(word)) {
				this.startPos = cal;
				ret = true;
			}
		}
		return ret;
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
	 */
	public Word getNext() {
		Word ret = this.words.get(this.recitePos);
		ret.setRecited(true);
		if((this.recitePos++)==(this.startPos+this.reciteNum)) {
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
		}
		return ret;
	}
}
