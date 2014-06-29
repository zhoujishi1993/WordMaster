package model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Result
 * @author fxiangyi@gmail.com
 * @version 2.0
 */

public final class Result {
	

	private SimpleStringProperty name;
	private SimpleIntegerProperty totalNum;
	private SimpleIntegerProperty recited;
	private SimpleIntegerProperty correct;
	private SimpleIntegerProperty wrong;
	private SimpleDoubleProperty accuracy;

	
	/**
	 * constructor
	 * @param name
	 * 			name of result
	 * @param total
	 * 			total number
	 * @param correct
	 * 			correct number
	 * @param wrong
	 * 			false number
	 * @param accuracy
	 * 			accuracy
	 */

	public Result(String name, int total, int recited, int correct, int wrong,
			double accuracy) {
		super();
		this.name = new SimpleStringProperty(name);
		this.totalNum = new SimpleIntegerProperty(total);
		this.recited = new SimpleIntegerProperty(recited);
		this.correct = new SimpleIntegerProperty(correct);
		this.wrong = new SimpleIntegerProperty(wrong);
		this.accuracy = new SimpleDoubleProperty(accuracy);

	}
	
	/**
	 * get name
	 * @return name
	 */
	public String getName() {
		return name.get();
	}
	
	/**
	 * get totalNum
	 * @return lexicon total number
	 */
	public int getTotalNum() {
		return totalNum.get();
	}
	
	/**
	 * getRecited
	 * @return recited
	 */
	public int getRecited() {
		return recited.get();
	}
	
	
	/**
	 * get correct
	 * @return correct number
	 */
	public int getCorrect() {
		return correct.get();
	}
	
	/**
	 * get wrong
	 * @return wrong number
	 */
	public int getWrong() {
		return wrong.get();
	}
	
	/**
	 * get accuracy
	 * @return accuracy
	 */
	public double getAccuracy() {
		return accuracy.get();
	}

	@Override
	public String toString() {
		return "result [name=" + name + ", total=" + totalNum + ", recited="
				+ recited + ", correct=" + correct + ", wrong=" + wrong
				+ ", accuracy=" + accuracy	+ "]";

	}
	

}
