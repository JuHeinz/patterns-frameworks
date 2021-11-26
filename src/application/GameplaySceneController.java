package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Handles incoming requests from the model zu change the view. 
 * @author julia
 *
 */
public class GameplaySceneController {
	/**
	 * Apply the .pressed CSS class to the button that has been pressed
	 */
//	@FXML
//	Button aBtn;
//	
//	@FXML
//	Button sBtn;
//	
//	@FXML
//	Button dBtn;
	
	//style, when a Button was pressed 
	public void setPressed(Button button) {
		button.getStyleClass().add("pressed");
	}
	
	//style, when the note was missed

	public void setMissed(Button button) {
		button.getStyleClass().add("pressed");
	}
		
	//style, when the note was hit
	public void setHit(Button button) {
		button.getStyleClass().add("pressed");
	}
}
