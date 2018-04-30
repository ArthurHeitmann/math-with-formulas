package fractionsSimple.fx;

import fractionsSimple.Fraction;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.Start;

/**
 * This class is being used to open a new Window. It will then generate the necessary GUI to calculate two fractions and show them to the user.
 * 
 * @author Arthur
 * @see FractionFX
 * @see ParseInput
 */
public class FractionWindow {
	static Fraction outFrac;
	static FractionFX outFracfx;

	/**
	 * Shows a window with all the necessary GUI elements to calculate two fractions (with +, -, * or /) and than output the fraction as a fraction
	 * and as a decimal number.
	 * 
	 * @return This stage can be applied to the currently running Application thread.
	 */
	public static Stage display() {
		Stage window = new Stage();
		window.setTitle("Simple fraction calculations");

		FractionFX frac1fx = new FractionFX();
		FractionFX frac2fx = new FractionFX();
		outFracfx = new FractionFX();
		outFracfx.unclickable();
		outFracfx.setLineLength(31);

		VBox inp1 = frac1fx.getVBox();
		VBox inp2 = frac2fx.getVBox();
		VBox outFracVBox = outFracfx.getVBox();

		TextField calcMeth = new TextField("+");
		TextField decimalOut = new TextField();
		decimalOut.setDisable(true);
		calcMeth.setPrefWidth(25);
		calcMeth.setId("calcMeth");
		Label inpHeader = new Label("Enter two fractions and an operator (+, -, * or /):");
		Label outHeader = new Label("Output");
		Label decimalLabel = new Label("As Decimal: ");
		decimalLabel.setPadding(new Insets(5, 0, 0, 0));
		HBox decimals = new HBox(10);
		decimals.getChildren().addAll(decimalLabel, decimalOut);

		Button calcBtn = new Button("Calculate");
		calcBtn.setOnAction(e -> {
			try {
				outFrac = ParseInput.calcFrac(frac1fx.getFraction(), frac2fx.getFraction(), calcMeth.getText());
				outFracfx.setNumerator(Integer.toString((int) outFrac.getNumerator()));
				outFracfx.setDenominator(Integer.toString((int) outFrac.getDenominator()));
				decimalOut.setText(Double.toString(outFrac.getNumerator() / outFrac.getDenominator()));
			} catch (NumberFormatException e1) {
				System.out.println("Invalid input! \n Only number and \".\"'s are allowed \n");
			}
		});

		HBox inputLayout = new HBox(10);
		inputLayout.getChildren().addAll(inp1, calcMeth, inp2);

		VBox mainLayout = new VBox(10);
		mainLayout.getChildren().addAll(inpHeader, inputLayout, calcBtn, outHeader, outFracVBox, decimals);
		mainLayout.setPadding(new Insets(20));

		Scene scene = new Scene(mainLayout);
		scene.getStylesheets().add(Start.class.getResource("style.css").toExternalForm());

		window.setScene(scene);
		window.setMinHeight(100);
		window.setMinWidth(100);
		window.setMaxHeight(900);
		window.setMaxWidth(1200);
		window.show();
		return window;

	}
}
