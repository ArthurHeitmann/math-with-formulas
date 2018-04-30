package fractionsSimple.fx;

import fractionsSimple.Fraction;
import fractionsSimple.FractionsCalc;

/**
 * This class is being used to calculate two fractions given from a GUI application.
 * 
 * @author Arthur
 * @see FractionFX
 * @see FractionWindow
 */
public class ParseInput {
	/**
	 * Calculates two fractions and returns them.
	 * 
	 * @param frac1 Fraction 1
	 * @param frac2 Fraction 2
	 * @param operator The operation (+, -, *, /)
	 * @return
	 */
	public static Fraction calcFrac(Fraction frac1, Fraction frac2, String operator) {
		Fraction fraction;
		switch (operator) {
			case "+":
				fraction = FractionsCalc.add(frac1, frac2);
				break;
			case "-":
				fraction = FractionsCalc.subtr(frac1, frac2);
				break;
			case "*":
				fraction = FractionsCalc.mult(frac1, frac2);
				break;
			case "/":
				fraction = FractionsCalc.divide(frac1, frac2);
				break;
			default:
				fraction = new Fraction(0);
				System.out.println("Invalid operator!");
		}
		return fraction;
	}
}
