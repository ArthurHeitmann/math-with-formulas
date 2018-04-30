package variableCalculations.fx;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import variableCalculations.SimpleFunc;

/**
 * This Class creates a window. The user can define several functions with variables and than do calculations on them.
 * 
 * @see FuncCalcInpPart
 * @see FuncCalcInputFx
 * @see FuncsFxRow
 * @see FuncsFX
 * @see RatfuncDisplayOutFx
 * @see VarNumberFx
 * @see WindowConsole
 */
public class FuncsFX {
	static VBox mainLayout = new VBox(5);
	static String az = "fghijklmnopqrstuvwxyzabcde";
	static int curNamePos = 0;
	static VBox inputs;
	static ArrayList<HBox> inpRows;
	static ArrayList<FuncFxRow> fxRows = new ArrayList<>();
	static Stage window;
	static Text midText = new Text("Calculations");
	public static WindowConsole console;
	static AnchorPane root;
	public static RatfuncDisplayOutFx outDisplay;
	public static HBox outHB;

	/**
	 * Creates the main window.
	 */
	public static void open() {
		window = new Stage();
		window.setTitle("Functions with maths");

		Text title = new Text("Enter your formula below");
		inputs = new VBox(5);
		inpRows = new ArrayList<>();
		Button addInp = new Button("+");
		VBox calcVbox = new VBox(5);
		HBox outCalcInp = new HBox(5);
		FuncCalcInputFx outCalcInpField = new FuncCalcInputFx();
		outHB = new HBox(5);
		root = new AnchorPane();
		Button calcBtn = new Button("Calculate");

		title.setId("title");
		addInp.setId("btnType1");
		calcBtn.setId("btnType1");
		makeInpRow();
		calcVbox.setPadding(new Insets(45, 0, 0, 0));
		outCalcInp.setPadding(new Insets(20, 0, 0, 0));

		addInp.setOnAction(e -> makeInpRow());
		calcBtn.setOnAction(e -> outCalcInpField.calculateFuncs());
		Text outP1_2[] = { new Text("S = "), new Text("S = ") };
		Text outCalculated = new Text("Enter inputs first");
		outCalculated.setTranslateY(20);
		outP1_2[1].setTranslateY(20);
		outCalcInp.getChildren().addAll(outP1_2[0], outCalcInpField.getInpRowHB(), calcBtn);
		outHB.setPadding(new Insets(15, 0, 0, 0));
		outHB.getChildren().addAll(outP1_2[1], outCalculated);
		calcVbox.getChildren().addAll(midText, outCalcInp, outHB);
		console = new WindowConsole();
		AnchorPane.setTopAnchor(console.getConsoleHB(), 15.0);
		AnchorPane.setRightAnchor(console.getConsoleHB(), 5.0);

		mainLayout.getChildren().addAll(title, inputs, addInp, calcVbox);
		mainLayout.setPadding(new Insets(15));
		mainLayout.setPrefSize(1000, 500);
		root.getChildren().addAll(mainLayout, console.getConsoleHB());

		Scene scene = new Scene(root);
		scene.getStylesheets().add(FuncsFX.class.getResource("style.css").toExternalForm());
		window.setScene(scene);
		window.show();
	}

	/**
	 * @param funcName The name/letter of a function
	 * @return The function a user has created
	 */
	public static SimpleFunc getFunc(char funcName) {
		return fxRows.get(az.indexOf(funcName)).getFunc();
	}

	/**
	 * Generates a new row to define a function.
	 * 
	 * @return HBox of that row
	 */
	private static HBox makeInpRow() {
		if (curNamePos < 11) {
			if (curNamePos > 2)
				window.setHeight(window.getHeight() + 68);
			Text funcName = new Text(Character.toString(az.charAt(curNamePos)) + " = ");
			TextField inpField = new TextField();
			fxRows.add(new FuncFxRow(az.charAt(curNamePos)));
			HBox inpRow = fxRows.get(fxRows.size() - 1).getRow();
			inpField.setPrefWidth(400);
			curNamePos++;
			inpRows.add(inpRow);
			inputs.getChildren().add(inpRow);

			return inpRow;
		}
		console.addRow("Maximum number of functions reached!", "#ff0000", true);
		return new HBox();
	}

}
