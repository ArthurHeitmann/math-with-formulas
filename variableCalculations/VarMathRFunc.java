package variableCalculations;

/**
 * This class contains only static functions for calculations with rational functions.
 * 
 * @author Arthur
 * @see VarMathSFunc
 * @see RationalFunction
 * @see SimpleFunc
 * @see VarNumber
 * @see ExponentVar
 */
public class VarMathRFunc {

	/**
	 * 
	 * Adds up two functions. If both denominators are not the same both functions will be extended so that the denominators are the same.
	 * 
	 * @param func1 Function 1
	 * @param func2 Function 2
	 * @return The sum of both functions
	 */
	public static RationalFunction add(RationalFunction func1, RationalFunction func2) {
		if (func1.getDenominator().equals(func2.getDenominator())) {
			func1.getNumerator().addVarNum(func2.getNumerator());
			func1.getNumerator().delRedundant();
			return func1;
		}
		func1.setNumerator(VarMathSFunc.mult(func1.getNumerator(), func2.getDenominator()));
		func1.setDenominator(VarMathSFunc.mult(func1.getDenominator(), func2.getDenominator()));
		func2.setNumerator(VarMathSFunc.mult(func2.getNumerator(), func1.getDenominator()));

		func1.setNumerator(VarMathSFunc.add(func1.getNumerator(), func2.getNumerator()));

		func1.getNumerator().delRedundant();
		func1.getDenominator().delRedundant();
		return func1;
	}

	/**
	 * Subtracts two functions from each other.
	 * 
	 * @param func1 Rational function 1
	 * @param func2 Rational function 2
	 * @return The result of the operation.
	 */
	public static RationalFunction subtr(RationalFunction func1, RationalFunction func2) {
		return add(func1, mult(func2, new RationalFunction(new SimpleFunc(new VarNumber(-1)))));
	}

	/**
	 * Multiplies two rational functions with each other.
	 * 
	 * @param func1 Rational function 1
	 * @param func2 Rational function 2
	 * @return The product of both functions.
	 */
	public static RationalFunction mult(RationalFunction func1, RationalFunction func2) {
		SimpleFunc numerator = VarMathSFunc.mult(func1.getNumerator(), func2.getNumerator());
		SimpleFunc denom = VarMathSFunc.mult(func1.getDenominator(), func2.getDenominator());

		return new RationalFunction(numerator, denom);
	}

	/**
	 * Divides the functions by multiplying with the reciprocal of the second function.
	 * 
	 * @param func1 Rational function 1
	 * @param func2 Rational function 2
	 * @return The quotient of both functions.
	 */
	public static RationalFunction divide(RationalFunction func1, RationalFunction func2) {
		RationalFunction tmpFunc = new RationalFunction(func2.getDenominator(), func2.getNumerator());
		return mult(func1, tmpFunc);
	}
}
