package application;

import music.PHSequencer;

import java.util.concurrent.atomic.AtomicReference;


public class PatternHeroGame implements Runnable {
    public PHSequencer ph;

    AtomicReference<String> lastInput;

    public PatternHeroGame(int BPM, int lives, String midiFileName, AtomicReference<String> lastInput) {
        // Get / Set game Settings
        this.lastInput = lastInput;
        ph = new PHSequencer(midiFileName, BPM, lastInput);
    }

    /**
     * Starts the playback of a song in its own thread.
     */
    public void run() {
        //Start sequencer, this plays the song
        ph.startSequencer();
        System.out.println("Song over");

    }



}


	


