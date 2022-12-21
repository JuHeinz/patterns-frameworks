package music;

import javax.sound.midi.*;
import java.io.*;

/**
 * Plays a song and outputs the notes in sync with playback
 */
public class PHSequencer {
    Sequencer sequencer;
    Sequence sequence;
    File song;

    int BPM;
    private int currentNote;

    public int getCurrentNote() {
        return currentNote;
    }

    public PHSequencer(String song, int BPM) {
        //Set up sequencer by getting corresponding Javax classes and methods
        try {
            // The Sequencer is the class that plays the music
            this.sequencer = MidiSystem.getSequencer();
            // A sequence stores one or more tracks. Tracks are made up of MidiEvents
            // Set the mode of the sequence. We divide each note in quarters (PPQ), with the resolution, we indicate that one tick is one quarter note long
            this.sequence = new Sequence(Sequence.PPQ, 1);
            this.song = new File(song);
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
        } catch (MidiUnavailableException | InterruptedException | IOException | InvalidMidiDataException e) {
            throw new RuntimeException(e);
        }
    }

    // A receiver listens on the sequence and does something every time a MidiEvent is processed/played
    Receiver receiver = new Receiver() {
        public void send(MidiMessage message, long timeStamp) {
            //If the Status is NOTE_ON (144), output the note
            if (message.getStatus() == 144) {
                currentNote = message.getMessage()[1];
            }
        }

        @Override
        public void close() {
            System.out.println("closed receiver");
        }
    };
}
