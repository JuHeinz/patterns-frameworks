package application;

import music.PHSequencer;

import java.util.concurrent.atomic.AtomicReference;

/**
 * This is the game loop. Every play through of a song happens in this class.
 *
 * @author julia
 */
public class PatternHeroGame implements Runnable {
    public PHSequencer ph;

    // Get / Set game Settings
    private Settings settings = new Settings();
    private int lives;
    private int BPM;
    private String midiFileName;

    AtomicReference<String> lastInput;

    UserInputHandler uih;

    String userInput = null;
    private int missedNotes = 0;

    // Constructor, inserts uip.
    public PatternHeroGame(int BPM, int lives, String midiFileName, AtomicReference<String> lastInput) {
        this.BPM = BPM;
        this.lives = lives;
        this.midiFileName = midiFileName;
        this.lastInput = lastInput;
        ph = new PHSequencer(midiFileName, BPM, lastInput);
    }

    /**
     * Starts the gameplay of a song, starts its own thread.
     */
    public void run() {

        //Start sequencer, this plays the song
        ph.startSequencer();
        System.out.println("Song over");

    }



}


	


