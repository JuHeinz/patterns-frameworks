package application;

import java.util.Arrays;
import java.util.List;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;


/**
 * Creates sounds thanks to the javax Midi library
 * @author julia
 *
 */
public class SoundGenerator {

	private static List<String> notes = Arrays.asList("C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B");
	
	/**
	 * Outputs a sound
	 * @param note, the note that should be played
	 * @param duration, how long the note should be held
	 */
	public void playSound(String note, int duration) {
		try {
			// Create and Open a Snythesizer
			Synthesizer midiSynth = MidiSystem.getSynthesizer();
			midiSynth.open();

			// get Channels
			MidiChannel[] mChannels = midiSynth.getChannels();
			MidiChannel  instrument =  mChannels[14]; 
			// Play Note
			instrument.noteOn(noteTranslator(note), 80);

			 //For this duration
			Thread.sleep(700);

			// Turn off the not & close the snyth
			instrument.noteOff(noteTranslator(note), 80);
			midiSynth.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
	
	public void stopSound(String note){
		
	}

	/**
	 * Makes musical note and translates it to MIDI-Number
	 * @param The normal musical note, with the octave preceding it. eg. 4A
	 * @return the midi-Code for that note
	 */
	public int noteTranslator(String note) {
		if( note != null) {
		int octave = Integer.parseInt(note.substring(0, 1)); // Muscial Notes are inputed with an Octave in front of
																// them, this extracts that
		return notes.indexOf(note.substring(1)) + 12 * octave + 12; // This gets the position of the second part of the
																	// note (the letter) and calculates the correct
																	// midi-ID
		}
		else {
			return 0;
		}
	}

}
