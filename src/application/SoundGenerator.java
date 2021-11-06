package application;

import java.util.Arrays;
import java.util.List;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;

public class SoundGenerator {
	
	private static List<String> notes = Arrays.asList("C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B");

	


	void playSound(String note, int duration) {
		try {
			// Create and Open a Snythesizer
			Synthesizer midiSynth = MidiSystem.getSynthesizer();
			midiSynth.open();
			
			//get Channels
			MidiChannel[] mChannels = midiSynth.getChannels();
			
			
			//Play Note
			mChannels[10].noteOn(noteTranslator(note), 80);
			//For this duration
			Thread.sleep( duration );
			//Turn off the note
			mChannels[10].noteOff(noteTranslator(note), 80);
			midiSynth.close();		}
		catch (Exception e){
			throw new RuntimeException(e);
		}

	}
	
	//Takes musical note and translates it to MIDI-Number
	public int noteTranslator(String note) {
		int octave = Integer.parseInt(note.substring(0, 1));
		return notes.indexOf(note.substring(1)) + 12 * octave + 12;
	}

}
