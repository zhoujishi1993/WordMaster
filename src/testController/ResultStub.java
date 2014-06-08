package testController;

/**
 * @type ResultStub.java
 * @author zhoujishi
 * @version 1.0
 * @description this is a stub class for result
 */
public class ResultStub {
	private String name;
	private int total;
	private int recited;
	private int correct;
	private int wrong;
	private double accurancy;
	
	public ResultStub(String name, int total, int recited, int correct,
			int wrong, double accurancy) {
		super();
		this.name = name;
		this.total = total;
		this.recited = recited;
		this.correct = correct;
		this.wrong = wrong;
		this.accurancy = accurancy;
	}

	public String getName() {
		return name;
	}

	public int getTotal() {
		return total;
	}

	public int getRecited() {
		return recited;
	}

	public int getCorrect() {
		return correct;
	}

	public int getWrong() {
		return wrong;
	}

	public double getAccurancy() {
		return accurancy;
	}
	
	
	
	
	
}
