package music;


import javax.sound.midi.*;
import java.io.*;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Plays a song and outputs the notes in sync with playback
 */
public class PHSequencer {
    PHReceiver receiver;
    Sequencer sequencer;
    Sequence sequence;
    File song;

    int BPM;

    public PHSequencer(String song, int BPM, AtomicReference<String> lastInput) {
        //Set up sequencer by getting corresponding Javax classes and methods
        try {
            // The Receiver listens for midi events
            this.receiver = new PHReceiver(lastInput);
            // The Sequencer is the class that plays the music
            this.sequencer = MidiSystem.getSequencer();
            // A sequence stores one or more tracks. Tracks are made up of MidiEvents
            // Set the mode of the sequence. We divide each note in quarters (PPQ), with the resolution, we indicate that one tick is one quarter note long
            this.sequence = new Sequence(Sequence.PPQ, 1);
            this.song = new File("midi_files/"+ song);
            this.BPM = BPM;

        } catch (MidiUnavailableException | InvalidMidiDataException e) {
            throw new RuntimeException(e);
        }

    }

    public void startSequencer() {
        try {
            sequencer.open();
            // Wait for sequencer to open
            Thread.sleep(2000);

            //set speed of song in BPM
            //TODO link to user setting
            sequencer.setTempoInBPM(BPM);

            //read input from midi file
            InputStream is = new BufferedInputStream(new FileInputStream(song));
            //put it into the sequencer as a sequence
            sequencer.setSequence(is);

            //start sequencer, music plays
            sequencer.start();

            //set up transmitter, which will listen on the sequence and do something every time an event is processed
            sequencer.getTransmitter().setReceiver(receiver);

            //Check every 100 ms if the sequencer is still running, if not, close it.
            while (sequencer.isRunning()) {
                Thread.sleep(100);
            }
            //close sequencer
            sequencer.close();

            System.out.println(receiver.getNoteOnAmount());

        } catch (MidiUnavailableException | InterruptedException | IOException | InvalidMidiDataException e) {
            throw new RuntimeException(e);
        }
    }



}
