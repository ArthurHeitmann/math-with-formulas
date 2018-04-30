package fractionsSimple.fx;

import fractionsSimple.Fraction;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * This class can be used for GUI applications to get a fraction input or output a fraction.
 * 
 * @author Arthur
 * @see FractionWindow
 * @see ParseInput
 */
public class FractionFX {
	TextField numerator = new TextField();
	TextField denominator = new TextField();
	VBox layout = new VBox();
	Label line = new Label("─────────────");

	/**
	 * <p>
	 * Constructs an object with default values.
	 * </p>
	 * <p>
	 * Used to get input from the user
	 * </p>
	 */
	public FractionFX() {
		setup();
	}

	/**
	 * Constructs the object with already given values. These are then applied to the TextFields.
	 * 
	 * @param numerator The numerator that will show up for the user in an box;
	 * @param denominator The denominator that will show up for the user in an box;
	 */
	public FractionFX(double denominator, double numerator) {
		setup();
		this.numerator.setText(Double.toString(numerator));
		this.denominator.setText(Double.toString(denominator));
	}

	/**
	 * <p>
	 * Combines the initialization steps which need to be done by both constructors
	 * </p>
	 * <ul>
	 * <li>Adds the parts to the main VBox</li>
	 * <li>Centers text in the TextFields</li>
	 * <li>Sets up the line between both input fields</li>
	 * </ul>
	 */
	private void setup() {
		layout.getChildren().addAll(numerator, line, denominator);
		numerator.setAlignment(Pos.CENTER);
		denominator.setAlignment(Pos.CENTER);
		numerator.setPrefWidth(line.getWidth());
		denominator.setPrefWidth(line.getWidth());
		line.setMaxHeight(5);

	}

	/**
	 * makes all the text fields fields uneditable by the user. Used for outputting a fraction.
	 */
	public void unclickable() {
		numerator.setDisable(true);
		denominator.setDisable(true);
		line.setDisable(true);
	}

	/**
	 * Sets the length of the line in the fraction. The line is Text which then consists of multiple - symbolds
	 * 
	 * @param count The amount of - symbols
	 */
	public void setLineLength(int count) {
		String lineCont = "";
		for (int i = 0; i < count; i++) {
			lineCont += "─";
		}
		line.setText(lineCont);
	}

	public void setNumerator(String numerator) {
		this.numerator.setText(numerator);
	}

	public void setDenominator(String denominator) {
		this.denominator.setText(denominator);
	}

	public Fraction getFraction() {
		return new Fraction(Double.parseDouble(numerator.getText()), Double.parseDouble(denominator.getText()), true);
	}

	public double getNumerator() {
		return Double.parseDouble((numerator.getText()));
	}

	public double getDenominator() {
		return Double.parseDouble((denominator.getText()));
	}

	/**
	 * 
	 * @return The VBox that holds the fraction with the GUI elements
	 */
	public VBox getVBox() {
		return layout;
	}

}
