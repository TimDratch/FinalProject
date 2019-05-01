
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

	 	