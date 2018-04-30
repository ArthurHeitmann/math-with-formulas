package variableCalculations;

import java.util.ArrayList;

import fractionsSimple.Fraction;

/**
 * 
 * The "Variable Number" is one of the most basic parts for a formula or any sort of mathematical function. It is build out of the value and a list of
 * variables with exponents (see ExponentVar). </br>
 * Here is one example: 2x^3
 * <ul>
 * <li>2: Stands for the value; this can also be a fraction</li>
 * <li>x^3: Stands for "ExponentVar"; can also be something like x^2*y^3</li>
 * </ul>
 * An exponent with a fraction can be used for roots, i. e. the square root of x equals x^(1/2)
 * 
 * @author Arthur
 * @see ExponentVar
 * @see RationalFunction
 * @see SimpleFunc
 * @see VarMathSFunc
 * @see VarMathRFunc
 */
public class VarNumber {
	private Fraction value;
	private ExponentVar exponentVar;

	/**
	 * Constructs a VarNumber with just the value as a fraction.
	 * 
	 * @param value
	 */
	public VarNumber(Fraction value) {
		this.value = value;
		this.exponentVar = new ExponentVar();
	}

	/**
	 * Constructs a VarNumber with just the value as a Double.
	 * 
	 * @param value
	 */
	public VarNumber(double value) {
		this.value = new Fraction(value, 1, true);
		this.exponentVar = new ExponentVar();
	}

	/**
	 * Constructs a VarNumber with its value as a fraction, one variable and a exponent to the variable.
	 * 
	 * @param value
	 * @param variable
	 * @param expo
	 */
	public VarNumber(Fraction value, char variable, Fraction expo) {
		this.value = value;
		this.exponentVar = new ExponentVar(variable, expo);
	}

	/**
	 * Constructs a VarNumber with its value as a Double, one variable and a exponent to the variable.
	 * 
	 * @param value
	 * @param variable
	 * @param expo
	 */
	public VarNumber(double value, char variable, Fraction expo) {
		this.value = new Fraction(value, 1, true);
		this.exponentVar = new ExponentVar(variable, expo);
	}

	/**
	 * Constructs a VarNumber with its value as a fraction, one variable and a exponent (Double) to the variable.
	 * 
	 * @param value
	 * @param variable
	 * @param expo
	 */
	public VarNumber(Fraction value, char variable, double expo) {
		this.value = value;
		this.exponentVar = new ExponentVar(variable, expo);
	}

	/**
	 * Constructs a VarNumber with its value as a Double, one variable and a exponent (Double) to the variable.
	 * 
	 * @param value
	 * @param variable
	 * @param expo
	 */
	public VarNumber(double value, char variable, double expo) {
		this.value = new Fraction(value, 1, true);
		this.exponentVar = new ExponentVar(variable, expo);
	}

	/**
	 * Constructs a VarNumber with its value as a fraction and a given ExponentVar.
	 * 
	 * @param value
	 * @param exponentVar ExponentVar; should already be initialized
	 */
	public VarNumber(Fraction value, ExponentVar exponentVar) {
		this.value = value;
		this.exponentVar = exponentVar;
	}

	/**
	 * Constructs a VarNumber with its value as a fraction and a list of variables and exponents. This list is being used to create a new ExponentVar.
	 * 
	 * @param value
	 * @param vars
	 * @param expoValues
	 */
	public VarNumber(Fraction value, ArrayList<Character> vars, ArrayList<Fraction> expoValues) {
		this.value = value;
		exponentVar = new ExponentVar(vars, expoValues);
	}

	/**
	 * Constructs an empty VarNumber. The value equals (0/1) == 0
	 * 
	 */
	public VarNumber() {
		value = new Fraction(0, 1, false);
		exponentVar = new ExponentVar();
	}

	/**
	 * 
	 * @return The value of the VarNumber.
	 */
	public Fraction getValue() {
		return value;
	}

	/**
	 * Sets the value of the VarNumber.
	 * 
	 * @param varValue
	 */
	public void setValue(Fraction varValue) {
		this.value = varValue;
	}

	/**
	 * 
	 * @return true: the value of the VarNumber is positive; false: the value is negative
	 */
	public boolean positive() {
		if (value.getNumerator() / value.getDenominator() >= 0)
			return true;
		return false;
	}

	/**
	 * Returns a String that has all the informations of the ExponentVar. </br>
	 * Can be used for indexing or debugging
	 * 
	 * @return
	 */
	public String ExponenetToString() {
		String out = "";
		for (int i = 0; i < exponentVar.vars.size(); i++) {
			out += exponentVar.vars.get(i) + "^" + exponentVar.expoValue.get(i).getValueAsDec();
		}
		return out;
	}

	/**
	 * 
	 * @return true: The VarNumber has at least one variable; false: no variable
	 */
	public boolean hasVariable() {
		return exponentVar.vars.size() >= 0 ? true : false;
	}

	/**
	 * 
	 * @return Returns the ExponentVar of the VarNumber
	 */
	public ExponentVar getExponentVar() {
		return exponentVar;
	}

	/**
	 * 
	 * @return The decimal value of the value property.
	 */
	public double getValueAsDec() {
		return value.getNumerator() / value.getDenominator();
	}

}
