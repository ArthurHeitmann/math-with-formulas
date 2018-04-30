package variableCalculations.fx;

import javafx.animation.ScaleTransition;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class WindowConsole {
	private VBox consoleVB = new VBox(4);

	public WindowConsole() {
		addRow("Program started");
		/*consoleVB.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		consoleVB.setOpacity(.5);*/
	}

	public void addRow(String messasge) {
		addRow(messasge, "#000000", false);
	}

	public void addRow(String messasge, String color, boolean bold) {
		String weight;
		if (bold)
			weight = "bold";
		else
			weight = "300";

		Text line = new Text(messasge);
		line.setFill(Color.web(color));
		line.setStyle("-fx-font-weight: " + weight);
		consoleVB.getChildren().add(0, line);
		ScaleTransition transition = new ScaleTransition();
		transition.setDelay(Duration.seconds(7));
		transition.setToY(0);
		transition.setNode(line);
		transition.play();
		transition.setOnFinished(e -> consoleVB.getChildren().remove(line));
		consoleVB.setTranslateY(-10);
	}

	public void addRow(String messasge, String color, String weight) {
	}

	public VBox getConsoleHB() {
		return consoleVB;
	}
}
