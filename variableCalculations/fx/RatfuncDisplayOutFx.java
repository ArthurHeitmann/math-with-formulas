package variableCalculations.fx;

import java.util.ArrayList;

import fractionsSimple.Fraction;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import variableCalculations.ExponentVar;
import variableCalculations.RationalFunction;
import variableCalculations.SimpleFunc;
import variableCalculations.VarNumber;

public class RatfuncDisplayOutFx {
	private Pane numerator = new Pane();
	private Pane denominator = new Pane();
	private Line fracLine = new Line(0, 0, 200, 0);
	private Text minusIndicator = new Text("-");
	private VBox fractionVB = new VBox(6);
	private HBox hBox = new HBox(5);
	private RationalFunction func;
	private boolean numeratorFrac;
	private boolean denomFrac;
	private double numeratorLength = 0;
	private double denomLength = 0;

	private enum FracPos {
		NUMERATOR, DENOMINATOR
	}

	public RatfuncDisplayOutFx(RationalFunction func) {
		this.func = func;
		fractionVB.getChildren().addAll(numerator, fracLine, denominator);
		hBox.getChildren().addAll(minusIndicator, fractionVB);
		applyFuncVisuals();
		minusIndicator.setVisible(false);
	}

	private void applyFuncVisuals() {
		HBox cHB = applyFunc(func.getNumerator(), FracPos.NUMERATOR);
		HBox dHB = applyFunc(func.getDenominator(), FracPos.DENOMINATOR);
		double length = 0;
		numerator.getChildren().add(cHB);
		denominator.getChildren().add(dHB);
		if (numeratorFrac)
			numerator.setTranslateY(10);
		else
			numerator.setTranslateY(0);
		if (denomFrac)
			denominator.setTranslateY(13);
		else
			denominator.setTranslateY(0);
		handleMinusIndi();
		minusIndicator.setStyle("-fx-font-size: 200%");
		minusIndicator.setTranslateY(20);
		fracLine.setEndX(Math.max(numeratorLength - 30, denomLength - 30));

	}

	private HBox applyFunc(SimpleFunc func, FracPos fracPos) {
		ArrayList<StringBuilder> texts = new ArrayList<>();
		HBox out = new HBox(5);
		boolean edit = false;
		boolean edit2 = false;
		for (VarNumber vn : func.getFuncParts()) {
			if (vn.getValueAsDec() < 0) {
				out.getChildren().add(new Text(" - "));
				edit = true;
			} else
				out.getChildren().add(new Text(" + "));
			if (fracPos == FracPos.NUMERATOR)
				numeratorLength += out.getChildren().get(0).getBoundsInLocal().getWidth() + 5;
			else
				denomLength += out.getChildren().get(0).getBoundsInLocal().getWidth() + 5;
			VBox vNvalue = generateFraction(vn.getValue(), false, fracPos);
			if (edit) {
				StringBuilder textC = new StringBuilder(((Text) vNvalue.getChildren().get(0)).getText());
				StringBuilder textD = null;
				if (vNvalue.getChildren().size() == 3)
					textD = new StringBuilder(((Text) vNvalue.getChildren().get(2)).getText());
				if (textC.charAt(0) == '-') {
					textC.deleteCharAt(0);
					((Text) vNvalue.getChildren().get(0)).setText(textC.toString());
				} else {
					textD.deleteCharAt(0);
					((Text) vNvalue.getChildren().get(2)).setText(textD.toString());
				}
			}
			if (fracPos == FracPos.NUMERATOR)
				numeratorLength += vNvalue.getBoundsInLocal().getWidth() + 5;
			else
				denomLength += vNvalue.getBoundsInLocal().getWidth() + 5;
			if (vNvalue.getChildren().size() == 3) {
				vNvalue.setTranslateY(-12);
				if (fracPos == FracPos.NUMERATOR)
					numeratorFrac = true;
				else
					denomFrac = true;

			}

			out.getChildren().addAll(vNvalue, generateExpoVar(vn.getExponentVar(), fracPos));
		}

		return out;
	}

	private HBox generateExpoVar(ExponentVar expoVar, FracPos pos) {
		HBox out = new HBox(5);
		for (int i = 0; i < expoVar.getExpoValue().size(); i++) {
			Text tmpTxt = new Text(Character.toString(expoVar.getVars().get(i)));
			out.getChildren().add(tmpTxt);
			tmpTxt.setStyle("-fx-font-size: 115%");
			VBox expoVB = generateFraction(expoVar.getExpoValue().get(i), true, pos);

			if (pos == FracPos.NUMERATOR)
				numeratorLength += tmpTxt.getBoundsInLocal().getWidth() + 5;
			else
				denomLength += tmpTxt.getBoundsInLocal().getWidth() + 5;

			if (expoVB.getChildren().size() == 3) {
				expoVB.setTranslateY(-11);
			}

			out.getChildren().add(expoVB);
		}

		return out;
	}

	private VBox generateFraction(Fraction frac, boolean exponent, FracPos pos) {
		Text n = new Text(Double.toString(frac.getNumerator()));
		Text d = new Text(Double.toString(frac.getDenominator()));
		Line fracLine = new Line(0, 0, 0, 0);
		VBox out = new VBox(3);
		if (n.getText().endsWith(".0")) {
			StringBuilder sb = new StringBuilder(n.getText());
			sb.delete(sb.length() - 2, sb.length());
			n.setText(sb.toString());
		}
		if (d.getText().endsWith(".0")) {
			StringBuilder sb = new StringBuilder(d.getText());
			sb.delete(sb.length() - 2, sb.length());
			d.setText(sb.toString());
		}
		double length = Math.max(n.getBoundsInLocal().getWidth(), d.getBoundsInLocal().getWidth());
		if (exponent) {
			n.setStyle("-fx-font-size: 75%");
			d.setStyle("-fx-font-size: 75%");
			fracLine.setEndX(length * .75);
		} else {
			n.setStyle("-fx-font-size: 105%");
			d.setStyle("-fx-font-size: 105%");
			fracLine.setEndX(length * 1.05);
		}
		if (d.getText().equals("1"))
			out.getChildren().add(n);
		else if (n.getText().equals("0.0") || d.getText().equals("0"))
			out.getChildren().add(new Text("0"));
		else
			out.getChildren().addAll(n, fracLine, d);
		if (pos == FracPos.NUMERATOR)
			numeratorLength += fracLine.getEndX() + 3;
		else
			denomLength += fracLine.getEndX() + 3;
		return out;
	}

	private void handleMinusIndi() {
		char operatorC = ((Text) ((HBox) numerator.getChildren().get(0)).getChildren().get(0)).getText().charAt(1);
		char operatorD = ((Text) ((HBox) denominator.getChildren().get(0)).getChildren().get(0)).getText().charAt(1);
		if (operatorC == '+' ^ operatorD == '+')
			minusIndicator.setVisible(true);
		((HBox) numerator.getChildren().get(0)).getChildren().remove(0);
		((HBox) denominator.getChildren().get(0)).getChildren().remove(0);
	}

	public void setFunc(RationalFunction func) {
		this.func = func;
		numeratorLength = 0;
		denomLength = 0;
		numeratorFrac = false;
		denomFrac = false;
		minusIndicator.setVisible(false);
		numerator.getChildren().clear();
		denominator.getChildren().clear();
		applyFuncVisuals();
	}

	public HBox getHBox() {
		return hBox;
	}
}
