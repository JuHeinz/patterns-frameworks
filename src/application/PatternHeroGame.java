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
    private int lives;
    private int BPM;
    private String midiFileName;

    AtomicReference<String> lastInput;

    UserInputHandler uih;

    String userInput = null;
    private int missedNotes = 0;

    // Constructor, inserts uip.
    public PatternHeroGame( int BPM, int lives, String midiFileName, UserInputHandler uih, AtomicReference<String> lastInput) {
        this.BPM = BPM;
        this.lives = lives;
        this.midiFileName = midiFileName;
        this.uih = uih;
        this.lastInput = lastInput;
    }

    /**
     * Starts the gameplay of a song, starts its own thread.
     */
    public void run() {

        //Start sequencer, this plays the song
        PHSequencer ph = new PHSequencer(midiFileName, BPM, lastInput);
        ph.startSequencer();
        System.out.println("Song over");

    }



}


	


