package application;



import songs.Songs;
/**
 * This is the Gameloop.
 * @author julia
 *
 */
public class PatternHeroGame {

	boolean gameStatus = true;
	NoteRainer noteRainer = new NoteRainer();

	String[][] song;
	/**
	 * runs the Game.
	 */

	public void run() {
		song = Songs.maryHadA;

		while (gameStatus) {

			for (int i = 0; i < song.length; i++) {

				// WHAT NOTE SHOULD BE PRESSED RIGHT NOW?
				String note = noteRainer.rainNote();
				System.out.print("Frame " + i + " Note: " + note);
								
				
				//DOES LAST INPUT MATCH THAT NOTE?
				String userInput = UserInputHandler.lastInput; 
				System.out.println(" Last user Input: " + userInput);
				SoundGenerator sg = new SoundGenerator();

								
				//If yes, play note
				if(userInput == note) {
					sg.playSound(note, 100);
				}

				// Wait some time before next Loop can be made.
				try {
					Thread.sleep(600);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			// Game is over
			System.out.println("Song over");
			gameStatus = false;

		}
	}

}
