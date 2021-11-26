package application;

import java.util.List;


/**
 * Returns the next note of the song.
 * @author julia
 * @see PatternHeroGame
 *
 */
public class NoteRainer {
	List<String> song;

	public NoteRainer(List<String> song) {
		this.song = song;
	}

	/**
	 * Return the next note. 
	 * @return A single note.
	 */
	int index = 0;

	public String rainNote() {
		String note = song.get(index);
		index++;
		return note;
	}

}
