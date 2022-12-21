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

    UserInputHandler userInputHandler = null;

    String userInput = null;
    private int missedNotes = 0;
    private boolean gameIsRunning = true;

    // Constructor, inserts uip.
    public PatternHeroGame(UserInputHandler uip) {
        this.userInputHandler = uip;
    }

    /**
     * Starts the gameplay of a song, starts its own thread.
     */
    public void run() {

        //Start sequencer, this plays the song
        PHSequencer ph = new PHSequencer(song, settings.getBPM());
        ph.startSequencer();
        // TODO mit last input abgleichen

        //TODO get song length aka amount of note_on events

        System.out.println("Song over. Score: " + (song.length() - missedNotes) + "/" + song.length() + " => "
                + (missedNotes / song.length()) + "%");
        gameIsRunning = false;

    }
}


	


