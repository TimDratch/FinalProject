
	 
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