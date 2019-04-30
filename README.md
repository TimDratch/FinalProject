# FinalProject
package application;
	
import java.util.HashMap;
import java.util.Map;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;


	// Scientific  calculator.
	public class Main extends Application {
	  
	  /** The Constant template. */
	  private static final String[][] template = {
	      { "7", "8", "9", "C" },
	      { "4", "5", "6", "*" },
	      { "1", "2", "3", "-" },
	      { "0", ".", "=", "+" },
	      {"%", "mod", "/", "reci"},
	      {"e", "ln", "log", "sq²"},
	    {"sin", "cos", "tan", "?"}
	  };

	  /** The accelerators. */
	  private final Map<String, Button> accelerators = new HashMap<>();

	  /** The stack value. */
	  private DoubleProperty stackValue = new SimpleDoubleProperty();
	  
	  /** The value. */
	  private DoubleProperty value = new SimpleDoubleProperty();

	  /**
	   * The Enum Op.
	   */
	  private enum Op { /** The noop. */
	 NOOP, /** The add. */
	 ADD, /** The subtract. */
	 SUBTRACT, /** The multiply. */
	 MULTIPLY, /** The divide. */
	 DIVIDE ,/** The percentage. */
	PERCENTAGE, /** The modulus. */
	 MODULUS, /** The reciprocal. */
	 RECIPROCAL, /** The square. */
	 SQUARE, /** The exponent. */
	 EXPONENT, /** The natural log. */
	 NATURAL_LOG, /** The log. */
	 LOG, /** The square root. */
	 SQUARE_ROOT, /** The sine. */
	 SINE, /** The cosine. */
	 COSINE, /** The tangent. */
	 TANGENT}
