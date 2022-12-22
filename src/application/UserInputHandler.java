package application;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

/**
 * Is activated after a key input comes in. Reads the key input and stores it.
 * (important for Gameplay Loop). @see PatternHeroGame
 *
 * @author julia
 */
public class UserInputHandler implements EventHandler<KeyEvent> {
    private String lastInput;


    @Override
    /**
     * Reads last pressed key
     */
    public void handle(KeyEvent event) {
        lastInput = event.getText();
        System.out.println("You pressed " + lastInput);
    }

    //Getters and Setters
    public String getLastInput() {
        synchronized (this) {
            return lastInput;

        }
    }

    public void setLastInput(String input) {
        synchronized (this) {
            this.lastInput = input;
        }
    }


}
