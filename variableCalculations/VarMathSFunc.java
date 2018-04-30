package variableCalculations;

import java.util.ArrayList;
import java.util.List;

import fractionsSimple.Fraction;
import fractionsSimple.FractionsCalc;

/**
 * This class contains only static methods for calculations with simple functions.
 * 
 * @author Arthur
 * @see RationalFunction
 * @see SimpleFunc
 * @see VarNumber
 * @see ExponentVar
 * @see VarMathRFunc
 */
public class VarMathSFunc {

	/**
	 * This method adds all the values of the VarNumbers from the given list.
	 * <h1>The method does not verify that all the ExponentVars are the same. You need to verify that first.</h1>
	 * 
	 * @param list A list of values with the same variable(s)
	 * @return The sum of all the values of the list example: [input] List = {1x; 4x; -2x} --> [output] VarNumber = {3x}
	 */
	public static VarNumber sumVarNum(List<VarNumber> list) {
		String var = list.get(0).ExponenetToString();
		Fraction tmpFrac = new Fraction(0, 1, false);
		for (VarNumber varNumber : list) {
			tmpFrac = FractionsCalc.add(tmpFrac, varNumber.getValue());
		}
		VarNumber out = new VarNumber(tmpFrac, list.get(0).getExponentVar());
		return out;
	}

	/**
	 * Subtracts two function from each other. func2 is being multiplied with -1 and then just added.
	 * 
	 * @param func1 Function 1
	 * @param func2 Function 2
	 * @return The result of the minus operation
	 */
	public static SimpleFunc subtr(SimpleFunc func1, SimpleFunc func2) {
		return add(func1, mult(func2, -1));
	}

	/**
	 * Adds up two functions.
	 * 
	 * @param func1
	 * @param func2
	 * @return The sum of both functions
	 */
	public static SimpleFunc add(SimpleFunc func1, SimpleFunc func2) {
		List<VarNumber> list1 = func1.getFuncParts();
		list1.addAll(func2.getFuncParts());
		func1.setFuncParts(list1);
		func1.delRedundant();
		;

		return func1;
	}

	/**
	 * Multiplies two functions with each other.
	 * 
	 * @param func1
	 * @param func2
	 * @return The product of both functions.
	 */
	public static SimpleFunc mult(SimpleFunc func1, SimpleFunc func2) {
		List<VarNumber> list1 = func1.getFuncParts();
		List<VarNumber> list2 = func2.getFuncParts();
		List<VarNumber> out = new ArrayList<VarNumber>();
		for (VarNumber element1 : list1) {
			for (VarNumber element2 : list2) {
				out.add(mult(element1, element2));
			}
		}
		func1.setFuncParts(out);
		func1.delRedundant();
		return func1;
	}

	/**
	 * Multiplies two VarNumbers with each other.
	 * 
	 * @param num1 VarNumber 1
	 * @param num2 VarNumber 2
	 * @return The product of both VarNumbers.
	 */
	public static VarNumber mult(VarNumber num1, VarNumber num2) {
		VarNumber out;
		Fraction value = FractionsCalc.mult(num1.getValue(), num2.getValue());
		ArrayList<Character> allVars = new ArrayList<>();
		ArrayList<Fraction> allExpoValues = new ArrayList<>();
		ArrayList<Character> tmpVars = num1.getExponentVar().getVars();
		ArrayList<Fraction> tmpFrac = num1.getExponentVar().getExpoValue();
		tmpVars.addAll(num2.getExponentVar().getVars());
		tmpFrac.addAll(num2.getExponentVar().getExpoValue());

		if (num1.hasVariable() && num2.hasVariable()) {
			for (int i = 0; i < tmpVars.size(); i++) {
				if (allVars.contains(tmpVars.get(i))) {
					int index = allVars.indexOf(tmpVars.get(i));
					allExpoValues.set(index, FractionsCalc.add(allExpoValues.get(index), tmpFrac.get(i)));
				} else {
					allVars.add(tmpVars.get(i));
					allExpoValues.add(tmpFrac.get(i));
				}
			}
			out = new VarNumber(value, allVars, allExpoValues);
		} else if (num1.hasVariable() || num2.hasVariable()) {
			out = new VarNumber(value, tmpVars.get(0), tmpFrac.get(0));
		} else {
			out = new VarNumber(value);
		}

		return out;
	}

	/**
	 * Multiplies a function with a factor.
	 * 
	 * @param func2 Function
	 * @param num Factor
	 * @return Product of the function and the factor.
	 */
	private static SimpleFunc mult(SimpleFunc func2, double num) {
		return mult(func2, new SimpleFunc(new VarNumber(num)));
	}

}
