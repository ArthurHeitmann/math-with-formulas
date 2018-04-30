package variableCalculations.fx;

import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import variableCalculations.RationalFunction;
import variableCalculations.VarMathRFunc;

/**
 * This class is being used to calculate all of the users inputs into one RationalFunction.
 * 
 * @author Arthur
 * @see FuncCalcInpPart
 * @see FuncCalcInputFx
 * @see FuncsFxRow
 * @see FuncsFX
 * @see RatfuncDisplayOutFx
 * @see VarNumberFx
 * @see WindowConsole
 *
 */
public class FuncCalcInputFx {
	private HBox inpRowHB = new HBox(5);
	private HBox inputsHB = new HBox(4);
	private Button addInputBtn = new Button("+");
	private ArrayList<FuncCalcInpPart> funcCalcInpParts = new ArrayList<>();

	public FuncCalcInputFx() {
		addInputPart(false);
		inpRowHB.getChildren().addAll(inputsHB, addInputBtn);
		addInputBtn.setOnAction(e -> addInputPart(true));
		addInputBtn.setId("btnType1");
	}

	public void addInputPart(boolean operation) {
		funcCalcInpParts.add(new FuncCalcInpPart(operation));
		inputsHB.getChildren().addAll(funcCalcInpParts.get(funcCalcInpParts.size() - 1).getHBox());
	}

	public void calculateFuncs() {
		RationalFunction tmpFunc = null;
		try {
			tmpFunc = funcCalcInpParts.get(0).getRatFunction();
			for (int i = 1; i < funcCalcInpParts.size(); i++) {
				switch (funcCalcInpParts.get(i).getOperation()) {
					case '*':
						tmpFunc = VarMathRFunc.mult(tmpFunc, funcCalcInpParts.get(i).getRatFunction());
						break;
					case '/':
						tmpFunc = VarMathRFunc.divide(tmpFunc, funcCalcInpParts.get(i).getRatFunction());
						break;
					case '+':
						tmpFunc = VarMathRFunc.add(tmpFunc, funcCalcInpParts.get(i).getRatFunction());
						break;
					case '-':
						tmpFunc = VarMathRFunc.subtr(tmpFunc, funcCalcInpParts.get(i).getRatFunction());
						break;
					/*default:
						FuncsFX.console.addRow("Invalid operator entered only the folloeing are allowed: +, -, *, /", "#ff0000", true);*/
				}

			}
			if (FuncsFX.outDisplay == null) {
				FuncsFX.outDisplay = new RatfuncDisplayOutFx(tmpFunc);
				FuncsFX.outHB.getChildren().remove(1);
				FuncsFX.outHB.getChildren().add(FuncsFX.outDisplay.getHBox());
			} else
				FuncsFX.outDisplay.setFunc(tmpFunc);
		} catch (NullPointerException e) {
			FuncsFX.console.addRow("Input must not be empty!", "#ff0000", true);
		}
	}

	public HBox getInpRowHB() {
		return inpRowHB;
	}
}
