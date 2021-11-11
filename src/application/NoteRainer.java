package application;

import java.util.List;

import songs.Songs;

public class NoteRainer {

	/**
	 * Returns the next note of the song.
	 * 
	 * @param song to be played
	 * 
	 */

	
	List<String> song = Songs.removeNull(Songs.maryHadA);

	

/**
 * 
 * @return A single note.
 */
	int index = 0;

	public String rainNote() {
		String note = song.get(index);
		index++;
		return note;
	}
	

}
