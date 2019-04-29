// Card Game controller BCS345
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Controller {
	int[] validNumbers = new int[4];

    @FXML
    private Button bRefresh; // refresh function
    @FXML
    private Button bVerify; // verify function
    @FXML
    private ImageView imgCards; 
    @FXML
    void enter(ActionEvent event) {
    	cards();
    }
    
    public Controller(){
    }
    public void cards() {
		
		boolean[] randomCards = new boolean[52];

		// Randomly select 4 unique cards
		int count = 0;
		
		while (count < 4) {// Displays 4 cards selected
			int card = (int) (Math.random() * 4) + 1;// Ignore
			
			if (randomCards[card] = !randomCards[card]) 
			{
				String[] cards = { "hearts", "clubs", "diamonds", "spades" };
				
				String name = cards[(int) (Math.random() * cards.length)];
				
				randomCards[card] = true;
				
				Image image = new Image("png/" + card + "from" + name + ".png");
				imgCards.setImage(image);
				
				System.out.println(++card);// Test
				
				int value = card % 13;
				
				validNumbers[count] = (value == 0) ? 13 : value;
				count++;
			}
		}
	}
}