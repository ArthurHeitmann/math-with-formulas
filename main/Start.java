package main;

import fractionsSimple.Fraction;
import fractionsSimple.fx.FractionFX;
import javafx.application.Application;
import javafx.stage.Stage;
import variableCalculations.fx.FuncsFX;

public class Start extends Application {
	Fraction outFrac;
	FractionFX outFracfx;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		FuncsFX.open();
	}

}
