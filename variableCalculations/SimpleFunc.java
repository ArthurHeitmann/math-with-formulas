package variableCalculations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * The SimpleFunc is list of VarNumbers. It can be used for all mathematical operations except for division (for that use RationalFuntion).
 * 
 * @author Arthur
 * @see RationalFunction
 * @see VarNumber
 * @see ExponentVar
 * @see VarMathSFunc
 * @see VarMathRFunc
 */
public class SimpleFunc {
	private List<VarNumber> funcParts = new ArrayList<VarNumber>();

	/**
	 * Constructs a function using a given list varNumbers.
	 * 
	 * @param list A predefined list of VarNumbers
	 */
	public SimpleFunc(List<VarNumber> list) {
		funcParts = list;
		delRedundant();
	}

	/**
	 * Constructs a function using one VarNumber which will be added to the funcParts list
	 * 
	 * @param varNumber
	 */
	public SimpleFunc(VarNumber varNumber) {
		funcParts.add(varNumber);
	}

	/**
	 * Constructs an empty function for later usage.
	 */
	public SimpleFunc() {
	}

	/**
	 * The last two elements of the list, if it has at least 2 elements, will be replaced by the given vNum. </br>
	 * Used when i. e. multiplying funcParts[4] with funcParts[5] --> multiplied funcParts[4]
	 * 
	 * @param newVNum The VarNumber that will replace the last two elements of the list if it has at least 2 elements.
	 */
	public void replaceLast2(VarNumber newVNum) {
		if (funcParts.size() > 1) {
			funcParts.remove(funcParts.size() - 1);
			funcParts.remove(funcParts.size() - 1);
		}
		funcParts.add(newVNum);
	}

	/**
	 * Prints out the function. For debugging only intended
	 */
	public void prinSelf() {
		String out = "";
		for (VarNumber varNumber : funcParts) {
			out += " + " + varNumber.getValueAsDec() + varNumber.ExponenetToString();
		}
		System.out.println(out);
	}

	/**
	 * 
	 * @return The list of all the VarNumber out of which the function consists.
	 */
	public List<VarNumber> getFuncParts() {
		return funcParts;
	}

	/**
	 * Replaces the current list with with the one given.
	 * 
	 * @param funcParts List with VarNumbers
	 */
	public void setFuncParts(List<VarNumber> funcParts) {
		this.funcParts = funcParts;
	}

	/**
	 * Adds another function to the current
	 * 
	 * @param func Function
	 */
	public void addVarNum(SimpleFunc func) {
		funcParts.addAll(func.getFuncParts());

	}

	/**
	 * Adds a VarNumber to the current list of this function.
	 * 
	 * @param varNumber
	 */
	public void addVarNum(VarNumber varNumber) {
		funcParts.add(varNumber);
	}

	/**
	 * Merges VarNumbers with the same ExponentVar
	 */
	public void delRedundant() {
		List<VarNumber> tmpList = funcParts;
		HashMap<String, List<VarNumber>> varNumbCategorys = new HashMap<String, List<VarNumber>>();	//categorize parts by their variable (like x) 
		for (VarNumber varNumber : tmpList) {
			String key = varNumber.ExponenetToString();
			if (varNumbCategorys.containsKey(key)) {
				varNumbCategorys.get(key).add(varNumber);
			} else {
				varNumbCategorys.put(key, new ArrayList<VarNumber>());
				varNumbCategorys.get(key).add(varNumber);
			}
		}
		tmpList.clear();
		for (String varExpo : varNumbCategorys.keySet()) {													//overwrite list with combined terms
			List<VarNumber> workList = varNumbCategorys.get(varExpo);
			VarNumber shortenVarTest = VarMathSFunc.sumVarNum(workList);
			if (shortenVarTest.getValueAsDec() != 0.0)														//removes parts with no value (like 0.0 * x)
				tmpList.add(shortenVarTest);
		}
		funcParts = tmpList;
	}

}
