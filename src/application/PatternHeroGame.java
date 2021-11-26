package application;

import java.util.List;

/**
 * This is the Gameloop. Every playthrough of a song happens in this class. 
 * 
 * @author julia
 *
 */
public class PatternHeroGame implements Runnable {

	// Game Settings
	private Settings settings = new Settings();
	private int speedInMs = setDifficulty();
	private List<String> song = settings.getSong();
	private int lives = settings.getLives();

	// Needed Instaces
	private NoteRainer noteRainer = new NoteRainer(song);
	UserInputHandler userInputHandler = null;
	private SoundGenerator soundMaker = new SoundGenerator();

	// Needed Fields
	String userInput = null;
	private int missedNotes = 0;
	private boolean gameIsRunning = true;

	// Constructor, inserts uip.
	public PatternHeroGame(UserInputHandler uip) {
		this.userInputHandler = uip;
	}

	/**
	 * Starts the gameplay of a song.
	 */
	public void run() {

		while (gameIsRunning) {

			for (int i = 0; i < song.size(); i++) {

				// Check more notes have been missed than the player has lives.
				// If yes, game over.

				if (missedNotes >= lives) {
					gameIsRunning = false;
					break;
				}

				// WHAT NOTE SHOULD BE PRESSED RIGHT NOW?
				String note = noteRainer.rainNote();
				System.out.println(">>> " + i + "| Up Next: " + noteToKey(note));

				// GAME WAITS FOR INPUT
				// In dieser Zeit fällt die Note vom Himmel herunter.
				try {
					Thread.sleep(speedInMs);
				} catch (InterruptedException e2) {
					e2.printStackTrace();
				}

				// DOES LAST INPUT MATCH THAT NOTE?
				// Jetzt wird der letzte Input und die eingegebene Note abgeglichen.
				try {
					userInput = userInputHandler.getLastNote();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				soundMaker.playSound(note, speedInMs);

				// Success
				if (userInput == note) {
					System.out.println("CORRECT!");

				// Failure
				} else {
					// Give Feedback
					System.out.println("MISSED");
					// Play Error sound
					soundMaker.playSound("1E", speedInMs);
					// Increase missed notes count
					missedNotes++;

				}

				// Reset last input, so two of the same notes after each other aren't both
				// correct if just the first note was hit.
				userInputHandler.setLastInput(null);
			}

			// GAME OVER
			System.out.println("Song over. Score: " + (song.size() - missedNotes) + "/" + song.size() + " => "
					+ (missedNotes / song.size()) + "%");
			gameIsRunning = false;

		}
	}

	/**
	 * Takes the difficulty value from the settings file and converts it to the corresponding speedInMs.
	 * @return speedInMS. Is used to set the game of the speed. 
	 */
	private int setDifficulty() {
		this.speedInMs = settings.getDifficulty() * 1000;
		return speedInMs;
	}
	
	/**
	 * Takes the next note that needs to be played in the song and returns the corresponding keyboard key.
	 * @param The next note, that needs to be played in the song.
	 * @return the corresponding Keyboard Key.
	 */
	private String noteToKey(String note) {
		switch (note) {
		case "4C":
			return "A";

		case "4D":
			return "S";

		case "4E":
			return "D";
		default:
			throw new IllegalArgumentException("Unexpected value: " + note);
		}
	}

}
