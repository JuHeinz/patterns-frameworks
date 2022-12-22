package application;

import music.PHSequencer;

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

    UserInputHandler uih;

    String userInput = null;
    private int missedNotes = 0;

    // Constructor, inserts uip.
    public PatternHeroGame(UserInputHandler uih) {
        this.uih = uih;
    }

    /**
     * Starts the gameplay of a song, starts its own thread.
     */
    public void run() {

        //Start sequencer, this plays the song
        PHSequencer ph = new PHSequencer(song, settings.getBPM());
        ph.startSequencer();
        System.out.println("Song over");

    }
}


	


