package songs;

import java.util.List;
import application.SoundGenerator;
/**
 * A Test class, that automatically plays songs without keyboard input.
 * @author julia
 *
 */

public class MusciBox {
	
	
	SoundGenerator sg = new SoundGenerator();

	Songs song = new Songs();
	
	void playSong(){
		List<String> playThis = Songs.removeNull(Songs.maryHadA);
		
		for(String note : playThis ) {
				sg.playSound(note, 130);
			}
	}
	
}
