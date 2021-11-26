package application;



import songs.Songs;
/**
 * This is the Gameloop.
 * @author julia
 *
 */
public class PatternHeroGame implements Runnable{

	boolean gameStatus = true;
	NoteRainer noteRainer = new NoteRainer();
	GameplaySceneController controller = new GameplaySceneController();
	String[][] song;
	UserInputHandler uip = null;
	
	//Constructor, inserts uip.
	public PatternHeroGame(UserInputHandler uip) {
		this.uip = uip;
	}
	
	
	
	/**
	 * runs the Game.
	 */
	public void run() {
		song = Songs.maryHadA;

		while (gameStatus) {
			

			for (int i = 0; i < song.length; i++) {

				// WHAT NOTE SHOULD BE PRESSED RIGHT NOW?
				String note = noteRainer.rainNote();
				System.out.println(">>> " + i + "| Up Next: " + note);
				
				//GAME WAITS FOR INPUT
				// In dieser Zeit fällt die Note vom Himmel herunter.
				try {
					Thread.sleep(4000);
				} catch (InterruptedException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
								
				
				//DOES LAST INPUT MATCH THAT NOTE?
				//Jetzt wird der letzte Input und die eingegebene Note abgeglichen.
				String userInput  = null;
				try {
					userInput = uip.getLastInput();
				} catch (Exception e1) {
					e1.printStackTrace();
				} 

								
				//If yes, play note
				if(userInput == note) {
					System.out.println("!!!!!!!!!!!!!!!!!!CORRECT!!!!!!!!!!!!!!!!");
					
				}
				else {
					System.out.println("MISSED :(");
				}

				// Wait some time before next Loop can be made.
				try {
					Thread.sleep(6000);
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
