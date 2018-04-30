package variableCalculations.fx;

import fractionsSimple.Fraction;
import fractionsSimple.FractionsCalc;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import variableCalculations.ExponentVar;
import variableCalculations.VarNumber;

public class VarNumberFx {
	private HBox row = new HBox(5);
	private VBox fracVBox = new VBox(5);
	private Line fracLine = new Line(0, 0, 50, 0);
	private TextField numeratorInput = new TextField("1");
	private TextField denominatorInput = new TextField("1");
	private HBox vars = new HBox(3);
	private Button addVarBtn = new Button("+");
	private TextField operationField = new TextField("+");
	public boolean operationBool;
	private int varsCounter = 0;

	public VarNumberFx(boolean operation) {
		VarNumber varNumber = new VarNumber();
		operationBool = operation;
		setup();
	}

	private void setup() {
		numeratorInput.setAlignment(Pos.CENTER);
		denominatorInput.setAlignment(Pos.CENTER);
		numeratorInput.setPrefWidth(50);
		denominatorInput.setPrefWidth(50);

		operationField.setId("operationF");
		numeratorInput.setId("fracInp1");
		denominatorInput.setId("fracInp2");
		addVarBtn.setId("addVarBtn");

		addVarBtn.setOnAction(e -> vars.getChildren().add(makeVarExpo()));
		operationField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.isEmpty()) {
					if (newValue.charAt(newValue.length() - 1) != '+' && newValue.charAt(newValue.length() - 1) != '-' && newValue.charAt(newValue.length() - 1) != '*') {
						FuncsFX.console.addRow("Invalid operator! \n\t Only +, - and * are allowed!", "#ff0000", true);
					}
					if (newValue.length() > 1) {
						FuncsFX.console.addRow("Only one operator!", "#ff0000", true);
					}
				}
			}
		});
		numeratorInput.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.isEmpty()) {
					if (!Character.isDigit(newValue.charAt(newValue.length() - 1)) && (newValue.charAt(newValue.length() - 1)) != '.') {
						FuncsFX.console.addRow("Only numbers and dots (\".\") are allowed", "#ff0000", true);
					} else if ((newValue.charAt(newValue.length() - 1)) == '.' && newValue.indexOf('.') != newValue.length() - 1) {
						FuncsFX.console.addRow("Only one dot \".\" per number!", "#ff0000", true);
					}
				}
			}
		});
		denominatorInput.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.isEmpty()) {
					if (!Character.isDigit(newValue.charAt(newValue.length() - 1)) && (newValue.charAt(newValue.length() - 1)) != '.') {
						FuncsFX.console.addRow("Only numbers and dots (\".\") are allowed", "#ff0000", true);
					} else if ((newValue.charAt(newValue.length() - 1)) == '.' && newValue.indexOf('.') != newValue.length() - 1) {
						FuncsFX.console.addRow("Only one dot \".\" per number!", "#ff0000", true);
					}
				}
			}
		});

		operationField.setPrefSize(25, 25);
		addVarBtn.setPrefSize(18, 25);
		fracVBox.getChildren().addAll(numeratorInput, fracLine, denominatorInput);
		fracVBox.setPadding(new Insets(-18, 0, 0, 7));
		if (operationBool)
			row.getChildren().addAll(operationField, fracVBox, vars, addVarBtn);
		else
			row.getChildren().addAll(fracVBox, vars, addVarBtn);

	}

	private HBox makeVarExpo() {
		TextField var = new TextField();
		TextField expo = new TextField("1");
		HBox expoVar = new HBox(3);
		var.setId("varField");
		expo.setId("expoField");
		var.setPrefSize(18, 25);
		expo.setPrefSize(30, 15);
		expo.setTranslateY(-10);
		expoVar.getChildren().addAll(var, expo);

		var.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.isEmpty()) {
					if (!Character.isLetter(newValue.charAt(newValue.length() - 1))) {
						FuncsFX.console.addRow("Only numbers and dots (\".\") are allowed!", "#ff0000", true);
					}
					if (newValue.length() > 1) {
						var.setText(Character.toString(var.getText().charAt(0)));
						FuncsFX.console.addRow("Only one variable per field!", "#ff0000", true);
					}
				}
			}
		});
		expo.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.isEmpty()) {
					if (!Character.isDigit(newValue.charAt(newValue.length() - 1)) && (newValue.charAt(newValue.length() - 1)) != '.' && (newValue.charAt(newValue.length() - 1)) != '/') {
						FuncsFX.console.addRow("Only numbers and dots (\".\") are allowed!", "#ff0000", true);
					} else if ((newValue.charAt(newValue.length() - 1)) == '.' && newValue.indexOf('.') != newValue.length() - 1) {
						FuncsFX.console.addRow("Only one dot \".\" per number!", "#ff0000", true);
					}
				}
			}
		});
		varsCounter++;
		return expoVar;
	}

	public VarNumber getVarNumber() throws NullPointerException, NumberFormatException, IllegalArgumentException {
		Double c, d;
		ExponentVar exponentVar = new ExponentVar();
		VarNumber out;
		try {																	//Factor value | fraction
			c = Double.parseDouble(numeratorInput.getText());
			d = Double.parseDouble(denominatorInput.getText());
			for (int i = 0; i < varsCounter; i++) {
				char var = ((TextField) ((HBox) vars.getChildren().get(i)).getChildren().get(0)).getText().charAt(0);						//variable
				if (Character.isDigit(var) || Character.isUpperCase(var) | !Character.isLetter(var))
					throw new IllegalArgumentException();
				Fraction expo = parseExpoInput(((TextField) ((HBox) vars.getChildren().get(i)).getChildren().get(1)).getText());
				exponentVar.add(var, expo);
			}

		} catch (NullPointerException e) {
			throw e;
		} catch (NumberFormatException e) {
			throw e;
		}

		if (varsCounter > 0) {
			out = new VarNumber(new Fraction(c, d, true), exponentVar);
		} else {
			out = new VarNumber(new Fraction(c, d, true));
		}

		if (operationField.getText().equals("-")) {
			out.setValue(FractionsCalc.mult(out.getValue(), -1));
		}

		return out;
	}

	private Fraction parseExpoInput(String str) {
		if (str.contains("/")) {
			int slashPos = str.indexOf('/');
			double numerator, denom;
			numerator = Double.parseDouble(str.substring(0, slashPos));
			denom = Double.parseDouble(str.substring(slashPos + 1));

			return new Fraction(numerator, denom, true);
		} else {
			return new Fraction(Double.parseDouble(str), 1, true);
		}
	}

	public boolean multiplyOperator() {
		return operationField.getText().equals("*");
	}

	public HBox getRow() {
		return row;
	}
}
