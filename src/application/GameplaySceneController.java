package application;


public class GameplaySceneController {
	
	String input;
	boolean songOver = false;
	
	SoundGenerator sg = new SoundGenerator();
		
	public void pressA() {
		System.out.println("A was pressed");
		sg.playSound("6C",100);
		
	}
	
	public void pressS() {
		System.out.println("S was pressed");
		sg.playSound("6D",100);

	}
	
	public void pressD() {
		System.out.println("D was pressed");
		sg.playSound("6E",100);

	}
}
