package variableCalculations.fx;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import variableCalculations.SimpleFunc;
import variableCalculations.VarMathSFunc;

/**
 * A row of GUI input elements for the user to define a function.
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
public class FuncFxRow {
	private ArrayList<VarNumberFx> varNumberFxs = new ArrayList<>();
	/** Main visual element */
	private HBox row = new HBox(5);
	private Label rowLabel = new Label();
	private HBox varNumsHB = new HBox(5);
	private Button addVarNumBtn = new Button("+");
	private char funcName;
	private int varNumCounter = 1;

	public FuncFxRow(char funcName) {
		this.funcName = funcName;
		setup();
	}

	/**
	 * Called once from the constructor. Positions all the elements, a bit of styling and adds a listener to a button.
	 */
	private void setup() {
		addVarNumBtn.setId("addVarNumBtn");
		addVarNumBtn.setOnAction(e -> {
			varNumberFxs.add((new VarNumberFx(true)));
			varNumsHB.getChildren().add(varNumberFxs.get(varNumberFxs.size() - 1).getRow());
			varNumCounter++;
		});
		varNumberFxs.add((new VarNumberFx(false)));
		varNumsHB.getChildren().add((varNumberFxs.get(varNumberFxs.size() - 1).getRow()));

		addVarNumBtn.setTranslateY(-2);
		rowLabel.setText(Character.toString(funcName) + " = ");
		row.getChildren().addAll(rowLabel, varNumsHB, addVarNumBtn);
		row.setPadding(new Insets(18, 0, 0, 0));
	}

	/**
	 * @return The function the user entered.
	 */
	public SimpleFunc getFunc() {
		SimpleFunc out = new SimpleFunc();
		try {
			for (int i = 0; i < varNumCounter; i++) {
				out.addVarNum(varNumberFxs.get(i).getVarNumber());
				if (varNumberFxs.get(i).operationBool && varNumberFxs.get(i).multiplyOperator()) {
					out.replaceLast2(VarMathSFunc.mult(varNumberFxs.get(i).getVarNumber(), varNumberFxs.get(i - 1).getVarNumber()));
				}
			}
			out.delRedundant();
		} catch (NullPointerException e) {
			FuncsFX.console.addRow("Input must not be empty", "#ff0000", true);
		} catch (NumberFormatException e) {
			FuncsFX.console.addRow("In Fractions only Numbers and one \".\" are allowed", "#ff0000", true);
		} catch (IllegalArgumentException e) {
			FuncsFX.console.addRow("Only small letters are allowed as variable names!", "#ff0000", true);
		} catch (Exception e) {
			FuncsFX.console.addRow("[INFO] An error occurred. See console for mor information.", "#2c57cc", true);
			e.printStackTrace();
		}

		return out;
	}

	/**
	 * @return HBox: constrains all necessary visual elements.
	 */
	public HBox getRow() {
		return row;
	}
}
