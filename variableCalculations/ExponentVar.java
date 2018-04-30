package variableCalculations;

import java.util.ArrayList;

import fractionsSimple.Fraction;

/**
 * The ExponentVar is being used for the usage of variables and corresponding exponents (Further information also in VarNumber). I. e. x^3*y^2a^1
 * 
 * @author Arthur
 * @see VarMathSFunc
 * @see VarMathRFunc
 * @see RationalFunction
 * @see SimpleFunc
 * @see VarNumber
 *
 */
public class ExponentVar {

	public ArrayList<Character> vars = new ArrayList<Character>();
	public ArrayList<Fraction> expoValue = new ArrayList<Fraction>();

	/**
	 * Creates an empty ExponentVar
	 */
	public ExponentVar() {
	}

	/**
	 * Creates an ExponentVar with one defined variable and exponent
	 * 
	 * @param var Variable
	 * @param expoValue Exponent as Double
	 */
	public ExponentVar(char var, Double expoValue) {
		this.vars.add(var);
		this.expoValue.add(new Fraction(expoValue, 1, true));
	}

	/**
	 * Creates an ExponentVar with one defined variable and exponent
	 * 
	 * @param var Variable
	 * @param expoValue Exponent as a fraction
	 */
	public ExponentVar(char var, Fraction expoValue) {
		this.vars.add(var);
		this.expoValue.add(expoValue);
	}

	/**
	 * Creates an ExponentVar from the lists in the parameters.
	 * 
	 * @param vars Variable list
	 * @param expoValues Exponent list
	 */
	public ExponentVar(ArrayList<Character> vars, ArrayList<Fraction> expoValues) {
		this.vars = vars;
		expoValue = expoValues;
	}

	/**
	 * 
	 * @return A String with all the informations of the Object. Can be used for debugging.
	 */
	public String VarsAsString() {
		String out = "";
		for (char var : vars) {
			out += var;
		}
		return out;
	}

	/**
	 * Adds one variable with an exponent to the lists of this object.
	 * 
	 * @param var Variable name/letter
	 * @param expoValue Exponent as fraction
	 */
	public void add(char var, Fraction expoValue) {
		vars.add(var);
		this.expoValue.add(expoValue);
	}

	/**
	 * Adds one variable with an exponent to the lists of this object.
	 * 
	 * @param var Variable name/letter
	 * @param expoValue Exponent as Double
	 */
	public void add(char var, Double num) {
		vars.add(var);
		expoValue.add(new Fraction(num, 1, true));
	}

	/**
	 * 
	 * @return List will all the variable of this object.
	 */
	public ArrayList<Character> getVars() {
		return vars;
	}

	/**
	 * 
	 * @return List will all the fractions of the exponents of this object.
	 */
	public ArrayList<Fraction> getExpoValue() {
		return expoValue;
	}

	/**
	 * Replaces the current list of the exponents list
	 * 
	 * @param expoValue Exponent values
	 */
	public void setExpoValue(ArrayList<Fraction> expoValue) {
		this.expoValue = expoValue;
	}

	/**
	 * Replaces the current list of the variables list
	 * 
	 * @param vars Variable names list
	 */
	public void setVars(ArrayList<Character> vars) {
		this.vars = vars;
	}

	/**
	 * 
	 * @param arg0 The ExponentVar to compare with
	 * @return true: If both lists of the ExponentVars are the same else: false
	 */
	public boolean equals(ExponentVar arg0) {
		if (arg0.getExpoValue().equals(expoValue) && arg0.getVars().equals(vars))
			return true;
		return false;
	}
}
