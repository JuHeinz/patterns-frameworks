package application;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
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

	
	 String lastInput;
	
			@Override
			public void handle(KeyEvent event) {

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
				System.out.println("You played " + lastInput);

			}
			
		String getLastInput() {
			synchronized (this){
				return lastInput;

			}
		}
	
	}

	

	


