package model;

import java.io.Serializable;

/**
 * Word
 * @author fxiangyi@gmail.com
 * @version 2.0
 */

public class Word implements Serializable {
	
	private String english;
	private String chinese;
	private boolean recited;
	private boolean right;
	private boolean start;
	
	/**
	 * constructor
	 * @param chinese
	 * 			chinese
	 * @param english
	 * 			english
	 * @param recited
	 * 			true or false
	 * 			default: false
	 * @param right
	 * 			true or false
	 * 			default: false
	 * @param start
	 * 			true or false
	 * 			default: false
	 */
	public Word(String english, String chinese, boolean recited, boolean right,
			boolean start) {
		super();
		this.chinese = chinese;
		this.english = english;
		this.recited = recited;
		this.right = right;
		this.start = start;
	}

	/** 
	 * get chinese
	 * @return chinese
	 */
	public String getChinese() {
		return chinese;
	}
	
	/**
	 * get english
	 * @return english
	 */
	public String getEnglish() {
		return english;
	}
		
	/**
	 * is recieted
	 * @return whether the word is recited
	 */
	public boolean isRecited() {
		return recited;
	}
	
	/**
	 * is right
	 * @return whether the word is recited right
	 */
	public boolean isRight() {
		return right;
	}
	
	/**
	 * is start
	 * @return is the word start one
	 */
	public boolean isStart() {
		return start;
	}
	
	/**
	 * setRecited
	 * @param recited default true
	 */
	public void setRecited(boolean recited) {
		this.recited = recited;
	}
	
	/**
	 * setRight
	 * @param right default true
	 */
	public void setRight(boolean right) {
		this.right = right;
	}
	
	/**
	 * setStart
	 * @param start default true
	 */
	public void setStart(boolean start) {
		this.start = start;
	}
	
	@Override
	public String toString() {
		//return this.english+" "+this.chinese+" "+this.recited+" "+this.right+" "+this.start;
		return "<word><english>"+this.english+"</english><chinese>"+this.chinese+"</chinese><recited>"+this.recited+"</recited><right>"+this.right+"</right><start>"+this.start+"</start></word>";
	}
}
