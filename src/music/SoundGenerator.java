package music;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;


/**
 * Creates sounds thanks to the javax Midi library
 *
 * @author julia
 */
public class SoundGenerator {


    /**
     * Outputs a sound, e.g. buzzer for wrong user input
     *
     * @param note,     the note that should be played
     * @param duration, how long the note should be held
     */
    public void playSound(int note, int duration) {
        try {
            // Create and open a synthesizer
            Synthesizer midiSynth = MidiSystem.getSynthesizer();
            midiSynth.open();

            // get Channels
            MidiChannel[] mChannels = midiSynth.getChannels();
            MidiChannel instrument = mChannels[14];

            // Play Note
            instrument.noteOn(note, 80);

            //For this duration
            Thread.sleep(duration);

            // Turn off the note & close the synth
            instrument.noteOff(note, 80);
            midiSynth.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
