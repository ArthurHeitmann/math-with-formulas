package variableCalculations;

/**
 * The RationalFunction is made of two SimpleFunctions for the numerator and denominator.
 * 
 * @author Arthur
 * @see VarMathSFunc
 * @see VarMathRFunc
 * @see SimpleFunc
 * @see VarNumber
 * @see ExponentVar
 * 
 */
public class RationalFunction {
	private SimpleFunc numerator;
	private SimpleFunc denominator;

	/**
	 * Creates a RationalFunction with given functions for the numerator and denominator.
	 * 
	 * @param nFunc Numerator function
	 * @param dFunc Denominator functions
	 */
	public RationalFunction(SimpleFunc nFunc, SimpleFunc dFunc) {
		numerator = nFunc;
		denominator = dFunc;
	}

	/**
	 * Creates a RationalFunction. The parameter will be the numerator and a new SimpleFunction with the value 1 will be assigned to the denominator.
	 * 
	 * @param func Nominator function
	 */
	public RationalFunction(SimpleFunc func) {
		numerator = func;
		denominator = new SimpleFunc(new VarNumber(1));
	}

	/**
	 * Creates a RationalFunction with the values 0/1 (no zero division)
	 */
	public RationalFunction() {
		numerator = new SimpleFunc(new VarNumber(0));
		denominator = new SimpleFunc(new VarNumber(1));
	}

	/**
	 * 
	 * @return Returns the numerator function.
	 */
	public SimpleFunc getNumerator() {
		return numerator;
	}

	/**
	 * Sets the numerator of the RationalFunction.
	 * 
	 * @param numerator Numerator of the RationalFunction
	 */
	public void setNumerator(SimpleFunc numerator) {
		this.numerator = numerator;
	}

	/**
	 * 
	 * @return Denominator of the RationalFunction
	 */
	public SimpleFunc getDenominator() {
		return denominator;
	}

	/**
	 * Sets the denominator of the function.
	 * 
	 * @param denominator Denominator of the RationalFunction
	 */
	public void setDenominator(SimpleFunc denominator) {
		this.denominator = denominator;
	}
}
