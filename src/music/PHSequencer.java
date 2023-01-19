package music;


import javax.sound.midi.*;
import java.io.*;
import java.util.ArrayList;
import application.GameplaySceneController;

/**
 * Plays a song and outputs upcoming notes
 */
public class PHSequencer {
    private final GameplaySceneController parent;
    Sequencer sequencer;
    Sequence sequence;
    File song;
    String fileName;
    public volatile long sequencerTickPosition;
    int BPM;
    NoteToKeyTranslator noteToKeyTranslator;
    public volatile String nextNote;
    public volatile long nextTick;

    public PHSequencer(String fileName, int BPM, GameplaySceneController parent) {
        //Set up sequencer by getting corresponding Javax classes and methods
        try {
            this.parent = parent;
            // The Sequencer is the class that plays the music
            this.sequencer = MidiSystem.getSequencer();
            // A sequence stores one or more tracks. Tracks are made up of MidiEvents
            // Set the mode of the sequence. We divide each note in quarters (PPQ), with the resolution, we indicate that one tick is one quarter note long
            this.sequence = new Sequence(Sequence.PPQ, 1);
            this.fileName = fileName;
            this.song = new File("midi_files/" + fileName);
            this.BPM = BPM;
            this.noteToKeyTranslator = new NoteToKeyTranslator();

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
            sequencer.setTempoInBPM(BPM);

            //read input from midi file
            InputStream is = new BufferedInputStream(new FileInputStream(song));
            //put it into the sequencer as a sequence
            sequencer.setSequence(is);

            //PREPROCESS MIDI FILE
            ArrayList<MidiEvent> noteOnEvents = extractNoteOnEvents(sequencer.getSequence());

            //start sequencer, music plays
            sequencer.start();
            System.out.println("Playing: " + fileName);

            //Check every 100 ms if the sequencer is still running, if not, close it.
            //Also check current tickPosition of sequencer playback
            int i = 0;
            while (sequencer.isRunning()) {
                //Find current tick position of sequencer playback
                sequencerTickPosition = sequencer.getTickPosition();
                //If the tick of the sequencer is smaller than the currently anticipated note's tick, output upcoming tick.
                if (sequencerTickPosition < noteOnEvents.get(i).getTick()) {
                    MidiEvent noteOnEvent = noteOnEvents.get(i);
                    int note = noteOnEvent.getMessage().getMessage()[1];
                    String key = noteToKeyTranslator.translate(note);
                    System.out.println("At tick " + noteOnEvents.get(i).getTick() + " press key: " + key);
                    nextTick = noteOnEvents.get(i).getTick();
                    nextNote = key;
                } else {
                    //if the position in the sequencer is larger than the next anticipated note, meaning if the note was passed, select the next anticipated note
                    //but only so long not all notes have been played
                    if (i < noteOnEvents.size() - 1) {
                        i++;
                    }
                }
                parent.updateUI(sequencerTickPosition);
                //Repeat after 100 ms
                Thread.sleep(100);
            }

            sequencer.close();
            System.out.println("Playback ended");
            parent.logHistory();

        } catch (MidiUnavailableException | InterruptedException | IOException | InvalidMidiDataException e) {
            throw new RuntimeException(e);
        }
    }

    public void stopSequencer(){
        sequencer.close();
        System.out.println("Sequencer closed");
    }

    /**
     * Takes the sequence's events and returns only those that are of type NOTE_ON
     *
     * @param sequence The sequence (song) about to be played
     * @return List of NOTE_ON events
     */
    public ArrayList<MidiEvent> extractNoteOnEvents(Sequence sequence) {

        ArrayList<MidiEvent> noteOnEvents = new ArrayList<>();

        //Only consider one track of the midi file, this is different for each file since some tracks may be mute, on test files, track 1 is ok.
        Track[] tracks = sequence.getTracks();
        Track track = tracks[1];

        //track.size() = number of events in track
        for (int i = 0; i < track.size() - 1; i++) {
            //.get() gets the event at the specified index. Index refers to the total number of events in track, not ticks.
            MidiEvent event = track.get(i);
            //An event contains a message and a tick. The message has a status.
            //If the status is 144 (NOTE_ON)
            if (event.getMessage().getStatus() == 144) {
                //add the event to the above list
                noteOnEvents.add(event);
            }
        }

        return noteOnEvents;

    }

}
