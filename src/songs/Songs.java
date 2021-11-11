package songs;

import java.util.ArrayList;
import java.util.List;

public class Songs {
	
	/**
	 * A song is stored as an Array of String Arrays,
	 * first index is C, second D, third E
	 */

	//Mary had a little lamb
	public static String[][] maryHadA = {
			{null, 		null, 		"4E", 		null, 		null},
			{null, 		"4D", 		null, 		null, 		null},
			{"4C", 		null, 		null, 		null, 		null},
			{null, 		"4D", 		null, 		null, 		null},
			{null, 		null, 		"4E", 		null, 		null},
			{null, 		null, 		"4E", 		null, 		null},
			{null, 		null, 		"4E", 		null, 		null},
			{null, 		"4D", 		null, 		null, 		null},
			{null, 		"4D", 		null, 		null, 		null},
			{null, 		"4D", 		null, 		null, 		null},
			{null, 		null, 		"4E", 		null, 		null},
			{null, 		null, 		"4E", 		null, 		null},
			{null, 		null, 		"4E", 		null, 		null},
			{null, 		null, 		"4E", 		null, 		null},
			{null, 		"4D", 		null, 		null, 		null},
			{"4C", 		null, 		null, 		null, 		null},
			{null, 		"4D", 		null, 		null, 		null},
			{null, 		null, 		"4E", 		null, 		null},
			{null, 		null, 		"4E", 		null, 		null},
			{null, 		null, 		"4E", 		null, 		null},
			{null, 		null, 		"4E", 		null, 		null},
			{null, 		"4D", 		null, 		null, 		null},
			{null, 		null, 		"4E", 		null, 		null},
			{null, 		"4D", 		null, 		null, 		null},
			{"4C", 		null, 		null, 		null, 		null},

		};
	
	
	/**
	 * Transforms song as seen above in a way that removes all nulls
	 * @param song that is to be transformed
	 * @return song with null values removed
	 */
	
	public static List<String> removeNull(String[][] song) {
		List<String> newSong = new ArrayList<>();
		
		for(String[] line : song ) {
			
			for (int i = 0; i < line.length; i++){
			if(line[i] != null) {
				newSong.add(line[i]);
			}else {
				continue;
			}
			}
		}

		return newSong;
		
	}
	
}

