package fractionsSimple;

public class FractionsCalc {
	/**
	 * This method shortens the given fraction so that it is as simple as possible (i. e. (2/10) --> (1/5) </br>
	 * or (-1/-2) --> (1/2)).
	 * 
	 * @param frac The fraction that will be shorten.
	 * @return The shorten fraction which was given.
	 * 
	 * <h2>Explanation of code segments:</h2>
	 * <ul>
	 * <li>1. Check whether the input fraction consists out of decimal values like 1.5</br>
	 * If so than it will be extended with 10Ex (x is the amount of decimal places of the longest number (n. or d.))</li>
	 * <li>2. Loop that starts counting down and looks whether i is a divisor of both the num. and the denom.</br>
	 * If so then both n. and d. will be divided by that value.</li>
	 * <li>3. If both n. and d. are negative they will be set positive (because dividing both with -1)</li>
	 * </ul>
	 */
	public static Fraction shorten(Fraction frac) {
		if (!isInt(frac)) {																			//1.
			int decimalPlaces = CountDecPlaces(maxDecimalCount(frac));
			frac = mult(frac, new Fraction(decimalPlaces * Math.pow(10, decimalPlaces)));
		}
		for (int i = (int) Math.min(frac.getNumerator(), frac.getDenominator()); i > 1; i--) {		//2.
			if (frac.getNumerator() % i == 0 && frac.getDenominator() % i == 0) {
				double numerator = frac.getNumerator();
				double denom = frac.getDenominator();
				frac.setNumerator(numerator / i);
				frac.setDenominator(denom / i);
			}
		}

		if (frac.getNumerator() < 0 && frac.getDenominator() < 0)									//3.
			frac = mult(frac, new Fraction(-1, -1, false));

		return frac;
	}

	/**
	 * Adds up two fractions, makes the denominators equal and shortens the output.
	 * 
	 * @param frac1
	 * @param frac2
	 * @return
	 */
	public static Fraction add(Fraction frac1, Fraction frac2) {
		Fraction tmpFrac;
		if (frac1.getDenominator() != frac2.getDenominator()) {
			double newDenominator = frac1.getDenominator() * frac2.getDenominator();
			double numerator1 = frac1.getNumerator() * frac2.getDenominator();
			double numerator2 = frac2.getNumerator() * frac1.getDenominator();
			tmpFrac = new Fraction(numerator1 + numerator2, newDenominator, false);
		} else {
			tmpFrac = new Fraction(frac1.getNumerator() + frac2.getNumerator(), frac1.getDenominator(), false);
		}
		return shorten(tmpFrac);
	}

	/**
	 * Adds up a fraction with a double number, for which will then automatically the denom will be set to 1 and then shorten.
	 * 
	 * @param frac1
	 * @param summand
	 * @return Result of the operation.
	 */
	public static Fraction add(Fraction frac1, double summand) {
		Fraction frac2 = new Fraction(summand, 1, true);
		Fraction tmpFrac;
		return add(frac1, frac2);
	}

	/**
	 * Subtracts two fractions by multiplying the second one with -1 and then adding them up.
	 * 
	 * @param frac1 Fraction 1
	 * @param frac2 Fraction 2 (will be multiplied with -1)
	 * @return Result of the operation.
	 */
	public static Fraction subtr(Fraction frac1, Fraction frac2) {
		return add(frac1, mult(frac2, -1));
	}

	/**
	 * 
	 * @param frac1 Fraction 1
	 * @param num Number which will be converted to a fraction
	 * @return Result of the operation.
	 */
	public static Fraction subtr(Fraction frac1, double num) {
		return add(frac1, -1 * num);
	}

	/**
	 * Multiplies two fractions.
	 * 
	 * @param frac1 Factor 1
	 * @param frac2 Factor 2
	 * @return The product of the operation.
	 */
	public static Fraction mult(Fraction frac1, Fraction frac2) {
		Fraction result;
		double numerator = frac1.getNumerator() * frac2.getNumerator();
		double denominator = frac1.getDenominator() * frac2.getDenominator();
		result = new Fraction(numerator, denominator, false);
		return shorten(result);
	}

	/**
	 * Multiplies two fractions.
	 * 
	 * @param frac1 Factor 1 as Fraction
	 * @param frac2 Factor 2 as Double
	 * @return The product of the operation.
	 */
	public static Fraction mult(Fraction frac1, double factor) {
		Fraction result;
		double numerator = frac1.getNumerator() * factor;
		double denominator = frac1.getDenominator();
		result = new Fraction(numerator, denominator, false);
		return shorten(result);
	}

	/**
	 * Divides two fractions by multiplying the first with the second as a reciprocal.
	 * 
	 * @param frac1 Fraction 1
	 * @param frac2 Fraction 2
	 * @return The quotient of the operation.
	 */
	public static Fraction divide(Fraction frac1, Fraction frac2) {
		return mult(frac1, reciprocal(frac2));
	}

	/**
	 * Divides two fractions by multiplying the first with the second as a reciprocal.
	 * 
	 * @param frac1 Fraction 1 as Fraction
	 * @param frac2 Fraction 2 as Double
	 * @return The quotient of the operation.
	 */
	public static Fraction divide(Fraction frac1, double num) {
		return mult(new Fraction(1, num, false), frac1);
	}

	/**
	 * Builds the reciprocal (numerator and denominator are switched) of the given fraction.
	 * 
	 * @param frac
	 * @return reciprocal
	 */
	private static Fraction reciprocal(Fraction frac) {
		double tmpDenom = frac.getDenominator();
		frac.setDenominator(frac.getNumerator());
		frac.setNumerator(tmpDenom);
		return frac;
	}

	/**
	 * Determines whether the given fraction consists out of Integers or Doubles
	 * 
	 * @param frac
	 * @return true: only Integer values; false: has Double values
	 */
	private static boolean isInt(Fraction frac) {
		if ((frac.getNumerator() - (int) frac.getNumerator()) == 0 && (frac.getDenominator() - (int) frac.getDenominator()) == 0) {
			return true;
		}

		return false;
	}

	/**
	 * 
	 * @param fraction Fraction with double values
	 * @return The amount of decimal places / the numbers after the dot.
	 */
	private static int maxDecimalCount(Fraction fraction) {
		int numeratorDecPlaces = CountDecPlaces(fraction.getNumerator());
		int denominatorDecPlaces = CountDecPlaces(fraction.getDenominator());
		if (numeratorDecPlaces != denominatorDecPlaces) {							//test whether numerator and denominator have same dec. length
			return numeratorDecPlaces > denominatorDecPlaces ? numeratorDecPlaces : denominatorDecPlaces;
		} else {
			return numeratorDecPlaces;
		}
	}

	/**
	 * 
	 * @param num as Double
	 * @return The amount of decimal places of the given number.
	 */
	private static int CountDecPlaces(double num) {
		String text = Double.toString(Math.abs(num));
		int integerPlaces = text.indexOf('.');
		int decimalPlaces = text.length() - integerPlaces - 1;
		return decimalPlaces;
	}
}
