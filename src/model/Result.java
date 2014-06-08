package model;

/**
 * Result
 * @author fxiangyi@gmail.com
 * @version 2.0
 */

public class Result {
	
	private String name;
	private int total;
	private int recited;
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
	public Result(String name, int total, int recited, int correct, int wrong,
			double accurancy) {
		super();
		this.name = name;
		this.total = total;
		this.recited = recited;
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
	 * getRecited
	 * @return recited
	 */
	public int getRecited() {
		return recited;
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
		return "Result [name=" + name + ", total=" + total + ", recited="
				+ recited + ", correct=" + correct + ", wrong=" + wrong
				+ ", accurancy=" + accurancy + "]";
	}
	

}
