package application;

import music.PHSequencer;

import java.util.concurrent.atomic.AtomicReference;

/**
 * This is the game loop. Every play through of a song happens in this class.
 *
 * @author julia
 */
public class PatternHeroGame implements Runnable {

    // Get / Set game Settings
    private Settings settings = new Settings();
    private String song = settings.getSong();
    private int lives = settings.getLives();

    AtomicReference<String> lastInput;

    UserInputHandler uih;

    String userInput = null;
    private int missedNotes = 0;

    // Constructor, inserts uip.
    public PatternHeroGame(UserInputHandler uih, AtomicReference<String> lastInput) {
        this.uih = uih;
        this.lastInput = lastInput;
    }

    /**
     * Starts the gameplay of a song, starts its own thread.
     */
    public void run() {

        //Start sequencer, this plays the song
        PHSequencer ph = new PHSequencer(song, settings.getBPM(), lastInput);
        ph.startSequencer();
        System.out.println("Song over");

    }
}


	


