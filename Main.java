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

//Scientific  calculator.
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
// The Cur Operator
private Op curOp   = Op.NOOP;

// The Stack Operator
private Op stackOp = Op.NOOP;

// The main method
public static void main(String[] args) { launch(args); }

// Stage
@Override public void start(Stage stage) {
  final TextField screen  = createScreen();
  final TilePane  buttons = createButtons();

  stage.setTitle("Calc"); // Titel
  stage.initStyle(StageStyle.UTILITY);
  stage.setResizable(false);
  stage.setScene(new Scene(createLayout(screen, buttons)));
  stage.show();
}

// Creates the layout.
// screen the screens
//buttons the buttons
// also returns the v box

private VBox createLayout(TextField screen, TilePane buttons) {
  final VBox layout = new VBox(20);
  layout.setAlignment(Pos.CENTER);
  layout.setStyle("-fx-background-color: chocolate; -fx-padding: 20; -fx-font-size: 20;");
  layout.getChildren().setAll(screen, buttons);
  handleAccelerators(layout);
  screen.prefWidthProperty().bind(buttons.widthProperty());
  return layout;
}


// handle accelerators
// lays out the layout

private void handleAccelerators(VBox layout) {
  layout.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
    @Override
    public void handle(KeyEvent keyEvent) {
      Button activated = accelerators.get(keyEvent.getText());
      if (activated != null) {
        activated.fire();
      }
    }
  });
}


 // Creates the screen.

 // return the text field

private TextField createScreen() {
  final TextField screen = new TextField();
  screen.setStyle("-fx-background-color: aquamarine;");
  screen.setAlignment(Pos.CENTER_RIGHT);
  screen.setEditable(false);
  screen.textProperty().bind(Bindings.format("%.0f", value));
  return screen;
}

// Creates the Buttons
// Creates the Pane
private TilePane createButtons() {   // Creates the Button
  TilePane buttons = new TilePane();
  buttons.setVgap(7);
  buttons.setHgap(7);
  buttons.setPrefColumns(template[0].length);
  for (String[] r: template) {
    for (String s: r) {
      buttons.getChildren().add(createButton(s));
    }
  }
  return buttons;
}


	// Creates the button.
//returns the button

private Button createButton(final String s) {
Button button = makeStandardButton(s);

if (s.matches("[0-9]")) {
  makeNumericButton(s, button);
} else {
  final ObjectProperty<Op> triggerOp = determineOperand(s);
  if (triggerOp.get() != Op.NOOP) {
    makeOperandButton(button, triggerOp);
  } else if ("C".equals(s)) {
    makeClearButton(button);
  } else if ("=".equals(s)) {
    makeEqualsButton(button);
  }
}

return button;
}


// Determines operand.
// return the object property

private ObjectProperty<Op> determineOperand(String s) {
final ObjectProperty<Op> triggerOp = new SimpleObjectProperty<>(Op.NOOP);
switch (s) {
  case "+": triggerOp.set(Op.ADD);      break;
  case "-": triggerOp.set(Op.SUBTRACT); break;
  case "*": triggerOp.set(Op.MULTIPLY); break;
  case "/": triggerOp.set(Op.DIVIDE);   break;
  case "%": triggerOp.set(Op.PERCENTAGE); break;
  case "ln": triggerOp.set(Op.NATURAL_LOG); break;
  case "log": triggerOp.set(Op.LOG); break;
  case "e": triggerOp.set(Op.EXPONENT); break;
  case "reci": triggerOp.set(Op.RECIPROCAL); break;
  case "sq²": triggerOp.set(Op.SQUARE); break;
  case "?": triggerOp.set(Op.SQUARE_ROOT); break;
  case "mod": triggerOp.set(Op.MODULUS); break;
  case "sin": triggerOp.set(Op.SINE); break;
  case "cos": triggerOp.set(Op.COSINE); break;
  case "tan": triggerOp.set(Op.TANGENT); break;
}
return triggerOp;
}


// Make operand button.
// triggers the trigger 

private void makeOperandButton(Button button, final ObjectProperty<Op> triggerOp) {
button.setStyle("-fx-base: lightgray;");
button.setOnAction(new EventHandler<ActionEvent>() {
  @Override
  public void handle(ActionEvent actionEvent) {
    curOp = triggerOp.get();
  }
});
}


// Make standard button.
//return the button

private Button makeStandardButton(String s) {
Button button = new Button(s);
button.setStyle("-fx-base: beige;");
accelerators.put(s, button);
button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
return button;
}

//Make numeric button.
private void makeNumericButton(final String s, Button button) {
  button.setOnAction(new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent actionEvent) {
      if (curOp == Op.NOOP) {
        value.set(value.get() * 10 + Double.parseDouble(s));
      } else {
        stackValue.set(value.get());
        value.set(Double.parseDouble(s));
        stackOp = curOp;
        curOp = Op.NOOP;
      }
    }
  });
}

//Create clear button
private void makeClearButton(Button button) {
  button.setStyle("-fx-base: mistyrose;");
  button.setOnAction(new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent actionEvent) {
      value.set(0);
    }
  });
}

//Create equals button
//Add cases for various functions
private void makeEqualsButton(Button button) {
  button.setStyle("-fx-base: ghostwhite;");
  button.setOnAction(new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent actionEvent) {
      switch (stackOp) {
        case ADD: value.set(stackValue.get() + value.get()); break;
        case SUBTRACT: value.set(stackValue.get() - value.get()); break;
        case MULTIPLY: value.set(stackValue.get() * value.get()); break;
        case DIVIDE: value.set(stackValue.get() / value.get()); break;
        case MODULUS: value.set(stackValue.get() % value.get()); break;
        case PERCENTAGE: double p = stackValue.get(); p = value.get(); value.set(value.get() / 100.0); break;
        case NATURAL_LOG: double nl = stackValue.get(); nl = value.get();  value.set(Math.log(value.get())); break;
        case LOG: double l= stackValue.get(); l = value.get();  value.set(Math.log(value.get())*2.303); break;
        case EXPONENT: double e= stackValue.get(); e = value.get();  value.set(value.get() * 2.71828182846); break;
        case RECIPROCAL: double r= stackValue.get(); r = value.get();  value.set(1.0 / value.get()); break;
        case SQUARE: double s= stackValue.get(); s = value.get();  value.set(value.get() * value.get()); break;
        case SQUARE_ROOT: double sr= stackValue.get(); sr = value.get();  value.set(Math.sqrt(value.get())); break;
        case SINE: double si= stackValue.get(); si = value.get();  value.set(Math.sin(Math.toRadians(value.get()))); break;
        case COSINE: double co= stackValue.get(); co = value.get();  value.set(Math.cos(Math.toRadians(value.get()))); break;
        case TANGENT: double ta= stackValue.get(); ta = value.get();  value.set(Math.tan(Math.toRadians(value.get()))); break;
	default:
		break;
      }
    }
  });
}
}