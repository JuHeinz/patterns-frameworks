package application;

import music.PHSequencer;


public class PatternHeroGame implements Runnable {
    public PHSequencer ph;

    public PatternHeroGame(int BPM, int lives, String midiFileName, GameplaySceneController parent) {
        // Get / Set game Settings
        ph = new PHSequencer(midiFileName, BPM, parent);
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


	


