package controller;

/**
 * @type Caculator.java
 * @author zhoujishi
 * @version
 */
public class Caculator {
/**
 * @method  calCorrectness
 * @param reciteNum
 * @param correct
 * @return
 */
public static double calCorrectness(int reciteNum,int correct){
	   if(reciteNum == 0){
		   throw new ArithmeticException("cannot have a reciteNum with 0");
	   }else{
		   int oneHundredCorrect = (int)((correct / reciteNum) * 100);
		   return oneHundredCorrect / 100;
	   }
   }
   
}
