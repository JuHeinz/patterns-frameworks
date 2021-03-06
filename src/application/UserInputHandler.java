package application;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

/**
 * Is activated after a keyinput comes in. Reads the Keyinput and stores it
 * (important for Gameplay Loop). @see PatternHeroGame
 * 
 * @author julia
 *
 */
public class UserInputHandler implements EventHandler<KeyEvent> {
	private String lastInput;
	private String lastNote;


	@Override
	/**
	 * Reads last pressed key and stores it. Also stores corresponsing note. 
	 */
	public void handle(KeyEvent event) {

		switch (event.getCode()) {
		case A:
			lastNote = "4C";

			break;
		case S:
			lastNote = "4D";

			break;
		case D:
			lastNote = "4E";
			break;
		default:
			break;
		}
		lastInput = event.getText();
		System.out.println("You played " + lastInput);
		
	}
	
	//Getters and Setters
	String getLastInput() {
		synchronized (this) {
			return lastInput;

		}
	}

	String getLastNote() {
		synchronized (this) {
			return lastNote;
		}
	}
	
	
	void setLastInput(String input) {
		synchronized (this) {
			this.lastInput = input;
		}}
		


}
