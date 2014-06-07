package model;

/**
 * Result
 * @author fxiangyi@gmail.com
 * @version 2.0
 */

public class Result {
	
	private String name;
	private int total;
	private int correct;
	private int wrong;
	private double accurancy;
	
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
	 * @param accurancy
	 * 			accurancy
	 */
	public Result(String name, int total, int correct, int wrong,
			double accurancy) {
		super();
		this.name = name;
		this.total = total;
		this.correct = correct;
		this.wrong = wrong;
		this.accurancy = accurancy;
	}
	
	/**
	 * get name
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * get total
	 * @return total number
	 */
	public int getTotal() {
		return total;
	}
	
	/**
	 * get correct
	 * @return correct number
	 */
	public int getCorrect() {
		return correct;
	}
	
	
	/**
	 * get wrong
	 * @return wrong number
	 */
	public int getWrong() {
		return wrong;
	}
	
	/**
	 * get accurancy
	 * @return accurancy
	 */
	public double getAccurancy() {
		return accurancy;
	}
	
	@Override
	public String toString() {
		return "result [name=" + name + ", total=" + total + ", correct="
				+ correct + ", wrong=" + wrong + ", accurancy=" + accurancy
				+ "]";
	}

}
