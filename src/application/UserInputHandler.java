package application;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

import java.util.concurrent.atomic.AtomicReference;
/**
 * Is activated after a key input comes in. Reads the key input and stores it.
 * (important for Gameplay Loop). @see PatternHeroGame
 *
 * @author julia
 */
public class UserInputHandler implements EventHandler<KeyEvent> {
    private final AtomicReference<String> lastInput;

    public UserInputHandler(AtomicReference<String> lastInput) {
        this.lastInput = lastInput;
    }


    @Override
    /*
     * Reads last pressed key
     */
    public void handle(KeyEvent event) {
        lastInput.set(event.getText());
        System.out.println("You pressed " + lastInput);
    }

    //Getters and Setters
    public String getLastInput() {
        synchronized (this) {
            return lastInput.get();

        }
    }

    public void setLastInput(String input) {
        synchronized (this) {
            this.lastInput.set(input);
        }
    }


}
