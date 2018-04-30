package variableCalculations.fx;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import variableCalculations.RationalFunction;
import variableCalculations.SimpleFunc;
import variableCalculations.VarMathSFunc;
import variableCalculations.VarNumber;

/**
 * This class is being used to get input from the user and returns a RationalFunction. The user can switch between a single line input and and a
 * fraction as an input method. In the text fields the user can enter function names/letters and then either add, subtract or multiply them.
 * Multiplication is being over + and -. If this part is not the first in the total row than the user can do one of the following operations with the
 * previous part: +, -, * or /.
 * 
 * @author Arthur
 * @see FuncCalcInpPart
 * @see FuncCalcInputFx
 * @see FuncsFxRow
 * @see FuncsFX
 * @see RatfuncDisplayOutFx
 * @see VarNumberFx
 * @see WindowConsole
 */
public class FuncCalcInpPart {
	private TextField numerator = new TextField();
	private TextField denominator = new TextField();
	private Line fracLine = new Line(0, 0, 100, 0);
	private VBox fractionVB = new VBox(3);
	private HBox hBox = new HBox(5);
	private Button fracSwithBtn = new Button("▼");
	private boolean singleLine = true;
	private boolean operation;
	private TextField operationField = new TextField("+");
	private String az = "qwertzuiopasdfghjklyxcvbnm";
	private String operators = "+-*";

	/**
	 * Constructs a new GUI element that can return a RationalFuntion from the user input.
	 * 
	 * @param operation True: This part is not the first and has to be calculated with the previous part. False: First part in the row
	 */
	public FuncCalcInpPart(boolean operation) {
		this.operation = operation;
		setupVerifications();
		fracSwithBtn.setId("btnType1");
		fractionVB.getChildren().add(numerator);
		fracSwithBtn.setScaleY(0.7);
		if (operation) {
			hBox.getChildren().addAll(operationField, fractionVB, fracSwithBtn);
			operationField.setPrefSize(25, 25);
			operationField.setId("operationF");
		} else {
			hBox.getChildren().addAll(fractionVB, fracSwithBtn);
		}
		numerator.setPrefWidth(100);
		denominator.setPrefWidth(100);
		fracSwithBtn.setOnAction(e -> {
			if (singleLine) {
				fractionVB.getChildren().addAll(fracLine, denominator);
				fractionVB.setPadding(new Insets(-20, 0, 0, 0));
				fracSwithBtn.setText("▲");
				singleLine = false;
			} else {
				fractionVB.getChildren().remove(1);
				fractionVB.getChildren().remove(1);
				fractionVB.setPadding(new Insets(0, 0, 0, 0));
				fracSwithBtn.setText("▼");
				singleLine = true;
			}
		});
	}

	/**
	 * Sets some listeners up for the input text fields to tell the user if something wrong has been entered. Only looks at the last letter in the
	 * text fields
	 */
	private void setupVerifications() {
		numerator.textProperty().addListener((obsV, oV, nV) -> {
			if (!nV.isEmpty()) {
				char newChar = nV.charAt(nV.length() - 1);
				if (newChar == '/') {
					FuncsFX.console.addRow("For division please use fractions or the division simbol \"/\" between two input fields", "#2c57cc", false);
				} else if (!az.contains(Character.toString(newChar)) && !operators.contains(Character.toString(newChar))) {
					FuncsFX.console.addRow("Only letters from defined functions are allowed!\n\tFor the usage of numbers use functions with just the factor", "#ff0000", true);
				}
			}
		});
		denominator.textProperty().addListener((obsV, oV, nV) -> {
			if (!nV.isEmpty()) {
				char newChar = nV.charAt(nV.length() - 1);
				if (newChar == '/') {
					FuncsFX.console.addRow("For division please use fractions or the division simbol \"/\" between two input fields", "#2c57cc", false);
				} else if (!az.contains(Character.toString(newChar)) && !operators.contains(Character.toString(newChar))) {
					FuncsFX.console.addRow("Only letters from defined functions are allowed!\n\tFor the usage of numbers use functions with just the factor", "#ff0000", true);
				}
			}
		});
		if (operation) {
			operationField.textProperty().addListener((obsV, oV, nV) -> {
				if (!nV.isEmpty()) {
					if (nV.length() > 1) {
						FuncsFX.console.addRow("Only one operator per field!", "#ff0000", false);
						operationField.setText(Character.toString(nV.charAt(0)));
					}
					if (nV.charAt(0) != '+' && nV.charAt(0) != '-' && nV.charAt(0) != '*' && nV.charAt(0) != '/') {
						FuncsFX.console.addRow("Only the following operators are allowed: +, -, *, /", "#ff0000", true);
					}
				}
			});
		}
	}

	/**
	 * Code structure:
	 * <ol>
	 * <li>IF: text fields are empty</li>
	 * <li>IF: numerator contains only one function (IF: this function is inverted/negative) ELSE: parse the string</li>
	 * <li>IF: display type is fraction(not single line) --> Same procedure as in 1.</li>
	 * </ol>
	 * 
	 * @return A RationalFunction calculated from the function names/letters the user has entered.
	 * @throws NullPointerException If the input is empty
	 * 
	 *
	 */
	public RationalFunction getRatFunction() throws NullPointerException {
		if (this.numerator.getText().isEmpty() || (!singleLine && this.denominator.getText().isEmpty()))			//1.
			throw new NullPointerException();

		RationalFunction out = new RationalFunction();
		SimpleFunc numerator = null;
		SimpleFunc denom = null;
		try {
			if (this.numerator.getText().length() == 1)																//2.
				numerator = FuncsFX.getFunc(this.numerator.getText().charAt(0));
			else if (this.numerator.getText().length() == 2 && this.numerator.getText().charAt(0) == '-')
				numerator = VarMathSFunc.mult(FuncsFX.getFunc(this.numerator.getText().charAt(1)), new SimpleFunc(new VarNumber(-1)));
			else
				numerator = stringToFunc(this.numerator.getText());
			if (!singleLine) {
				if (this.denominator.getText().length() == 1)
					denom = FuncsFX.getFunc(this.denominator.getText().charAt(0));
				else if (this.denominator.getText().length() == 2 && this.denominator.getText().charAt(0) == '-')
					denom = VarMathSFunc.mult(FuncsFX.getFunc(this.denominator.getText().charAt(1)), new SimpleFunc(new VarNumber(-1)));
				else
					denom = stringToFunc(this.denominator.getText());
			}
		} catch (NumberFormatException e) {
			FuncsFX.console.addRow("Invalid input in numerator or denominator!", "#ff0000", true);
		}
		out.setNumerator(numerator);
		out.setDenominator(singleLine ? new SimpleFunc(new VarNumber(1)) : denom);

		return out;
	}

	/**
	 * Code structure: --> See markers in code
	 * 
	 * @param str String that will be converted to a SimpleFunc
	 * @return The parsed String as a function
	 * @throws NumberFormatException If the input is invalid
	 */
	private SimpleFunc stringToFunc(String str) throws NumberFormatException {
		SimpleFunc out = new SimpleFunc();
		int amountMultSymbols = 0;
		if (!validateFuncInp(str))
			throw new NumberFormatException();
		for (char c : str.toCharArray()) {
			if (c == '*')
				amountMultSymbols++;
		}
		int startPos = 0;
		for (int i = 0; i < amountMultSymbols; i++) {					// 1. first do multiplication
			int nextPos = str.indexOf('*', startPos);
			int nextPosTmp = nextPos;
			int followingMults = 1;
			while (true) {												//determine the amount of multiplications in a row before a + or -
				if (nextPosTmp + 2 < str.length() - 1)
					if (str.charAt(nextPos + 2) == '*') {
						followingMults++;
						nextPosTmp = str.indexOf('*', nextPosTmp + 1);
					} else
						break;
				else
					break;
			}

			SimpleFunc tmpFunc = FuncsFX.getFunc(str.charAt(nextPos - 1));
			for (int j = 0; j < followingMults; j++) {
				tmpFunc = VarMathSFunc.mult(tmpFunc, FuncsFX.getFunc(str.charAt(nextPos + j * 2 + 1)));
				i++;
			}

			if (nextPos > 1 && str.charAt(nextPos - 2) == '-')							// 2. Handle minus at the beginning
				tmpFunc = VarMathSFunc.mult(tmpFunc, new SimpleFunc(new VarNumber(-1)));

			out.addVarNum(tmpFunc);

			startPos = nextPos + 1;
			i += followingMults - 1;
		}

		boolean minusStart = false;
		if (str.charAt(0) == '-')
			minusStart = true;
		for (int i = 0; i < str.length(); i += 2) {										//3. Adding the rest of the functions surrounded with + and -
			if (i == 0) {
				if (minusStart) {
					i++;
					if (str.charAt(2) != '*')
						out.addVarNum(VarMathSFunc.mult(FuncsFX.getFunc(str.charAt(1)), new SimpleFunc(new VarNumber(-1))));
				} else {
					if (str.charAt(1) != '*')
						out.addVarNum(FuncsFX.getFunc(str.charAt(0)));
				}
				continue;
			}

			if (i == str.length() - 1) {
				char lastOperator = str.charAt(str.length() - 2);
				if (lastOperator == '*')
					break;
				if (lastOperator == '+')
					out.addVarNum(FuncsFX.getFunc(str.charAt(i)));
				else
					out.addVarNum(VarMathSFunc.mult(FuncsFX.getFunc(str.charAt(i)), new SimpleFunc(new VarNumber(-1))));
				break;
			}

			if (str.charAt(i - 1) == '*' || str.charAt(i + 1) == '*')
				continue;

			if (str.charAt(i - 1) == '+')
				out.addVarNum(FuncsFX.getFunc(str.charAt(i)));
			else
				out.addVarNum(VarMathSFunc.mult(FuncsFX.getFunc(str.charAt(i)), new SimpleFunc(new VarNumber(-1))));

		}
		out.delRedundant();
		return out;
	}

	/**
	 * Every char that has an even position has to be a function name/letter; all uneven have to be operators.
	 * 
	 * @param funcStr Function to be validated
	 * @return True: the String can be parsed without problems. </br>
	 * Else: Invalid user input
	 */
	private boolean validateFuncInp(String funcStr) {
		StringBuilder sb = new StringBuilder(funcStr);
		if (funcStr.charAt(0) == '-') {
			sb.deleteCharAt(0);
		}

		for (int i = 0; i < sb.length(); i++) {
			if (i % 2 == 0 && !az.contains(sb.subSequence(i, i + 1).toString()))
				return false;
			if (i % 2 == 1 && !operators.contains(sb.subSequence(i, i + 1).toString()))
				return false;
		}

		return true;
	}

	/**
	 * @return The mathematical operation symbol.
	 */
	public char getOperation() {
		return operationField.getText().charAt(0);
	}

	/**
	 * @return HBox: contains all visual elements
	 */
	public HBox getHBox() {
		return hBox;
	}
}
