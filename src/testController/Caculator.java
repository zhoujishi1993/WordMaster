package testController;

/**
 * @type Caculator.java
 * @author zhoujishi
 * @version  this is the same class with controller.Caculator for test
 */
public class Caculator {
	/**
	 * @method calCorrectness
	 * @param reciteNum
	 * @param correct
	 * @return
	 */
	public static double calCorrectness(int reciteNum, int correct) {
		if (reciteNum == 0) {
			return 0;
		} else if (reciteNum < 0 || correct < 0) {
			throw new ArithmeticException("cannot have negative parameters");
		} else if (correct > reciteNum) {
            throw new ArithmeticException("cannot have a correct numer larger than reciteNum");
		} else {
			// This will make the accuracy of 0.01
			int oneHundredCorrect = (int) ((correct * 1.0 / reciteNum) * 100);
			return oneHundredCorrect / 100.0;
		}
	}

}
