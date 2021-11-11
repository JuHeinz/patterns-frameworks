package application;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
/**
 * This class acts as a controler between the View (JavaFX) and the Model (GameplaySceneController)
 * It receives inputs from JAVAFX and triggers the code for the rest of the model
 */
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
/**
 * Is activated after a keyinput comes in. Reads the Keyinput and stores it (important for Gameplay Loop).
 *  * @see PatternHeroGame

 * @author julia
 *
 */
public class UserInputHandler implements EventHandler<KeyEvent> {
	

	FXMLLoader loader = new FXMLLoader(getClass().getResource("GameplayScene.fxml"));
	
	SoundGenerator sg = new SoundGenerator();

	
	static String lastInput = "4E";
	
			@Override
			public void handle(KeyEvent event) {
				System.out.println(event.getCode());

				switch (event.getCode()) {
				case A:
					lastInput = "4C";


					break;
				case S:
					lastInput = "4D";


					break;
				case D:
					lastInput = "4E";
					break;
				default:
					break;
				}				
			}

		String getlastInput() {
			return lastInput;
		}
	
	}

	

	


