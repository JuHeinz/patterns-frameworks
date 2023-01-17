package music;


import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;

import java.util.concurrent.atomic.AtomicReference;

/**
 *    Gets called every time a MidiEvent is processed/played.
 */
public class PHReceiver implements Receiver {

    private final AtomicReference<String> lastInput;
    private int currentNote;
    private int noteOnAmount;
    private String key;

    public PHReceiver(AtomicReference<String> lastInput) {
        this.lastInput = lastInput;
    }


    /**
     * This message gets sent TO this class from the Sequencer
     * This only reads the first midi track in the file, which is okay as long as that track is for piano.
     */
    @Override
    public void send(MidiMessage message, long timeStamp) {
        //If the Status is NOTE_ON (144), process the note
        if (message.getStatus() == 144) {
            currentNote = message.getMessage()[1];
            noteOnAmount++;
            translateNoteToKey(currentNote);
            compareNoteToKey();

        }
    }

    @Override
    public void close() {

    }

    /**
     * Takes the currently played note (int between 0 and 127) determines what key needs to be pressed for it to be counted as a correct input.
     * Since most songs are between 108 and 21 (piano range), the grouping is as follows
     * A = 0 - 44
     * S = 45 - 67
     * D = 68 - 90
     * F = 91 - 127
     *
     * @param currentNote note that was last played back
     * @return key on keyboard user has to hit
     */
    public String translateNoteToKey(int currentNote) {
        if (currentNote <= 44) {
            key = "A";
        } else if (currentNote > 45 && currentNote <= 67) {
            key = "S";
        } else if (currentNote > 68 && currentNote <= 90) {
            key = "D";
        } else if (currentNote >= 91){
            key = "F";
        }else{
            key = "A";
        }
        System.out.println(noteOnAmount + " | " + currentNote + " > " + key);
        return key;
    }

    public void compareNoteToKey() {
        String input = lastInput.get();
        if (input == null) {
            System.out.println("MISSED!");
        } else if (input.equalsIgnoreCase(key)) {
            System.out.println("CORRECT!");
        } else {
            System.out.println("FALSE!");
        }
        lastInput.set(""); // reset
    }
    public int getNoteOnAmount() {
        return noteOnAmount;
    }
}
