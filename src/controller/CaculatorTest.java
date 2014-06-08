/**
 * 
 */
package controller;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @type CaculatorTest.java
 * @author zhoujishi
 * @version
 */
public class CaculatorTest {

	/**
	 * Test method for {@link controller.Caculator#calCorrectness(int, int)}.]
	 * First test for normal case which correct and reciteNum is positive and 
	 * reciteNum >= correct
	 */
	@Test
	public void testCalCorrectness() {
		int reciteNum = 20;
		int correct = 5;
		double accuracy = Caculator.calCorrectness(reciteNum, correct);
		assertEquals(0.25, accuracy, 0);
	}
	
	/*
	 * Test method for {@link controller.Caculator#calCorrectness(int, int)}.]
	 * Second test for abnormal case which reciteNum or correct is negative
	 */
	@Test(expected = ArithmeticException.class)
	public void testCalCorrectnessWithNegativeReciteNum(){
		int reciteNum = -1;
		int correct = 5;
		Caculator.calCorrectness(reciteNum, correct);
	}

	@Test(expected = ArithmeticException.class)
	public void testCalCorrectnessWithNegativeCorret(){
	    int reciteNum = 5;
	    int correct = -1;
	    Caculator.calCorrectness(reciteNum, correct);
	}
	
	/**
	 * Test method for {@link controller.Caculator#calCorrectness(int, int)}.]
	 * Then test the case which reciteNum is zero
	 * it shall return an accuracy with 0
	 */
	@Test
	public void testCalCorrectnessWithZeroReciteNum(){
		int reciteNum = 0;
		int correct = 0;
		double accuracy = Caculator.calCorrectness(reciteNum, correct);
		assertEquals(0, accuracy,0);
	}
	
	/*
	 * Test method for {@link controller.Caculator#calCorrectness(int, int)}.]
	 * Finally test the case which correct is larger than reciteNum
	 * it shall throw an arithmetic Exception
	 */
	@Test(expected = ArithmeticException.class)
	public void testCalCorrectnessWithCorrectLargerThenRecite(){
		int reciteNum = 2;
		int correct = 3;
		Caculator.calCorrectness(reciteNum, correct);
	}
}
