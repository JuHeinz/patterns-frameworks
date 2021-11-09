package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class GameplaySceneController {
	
	@FXML
	 Button sBtn;
	
	@FXML
	 Button aBtn;
	
	@FXML
	 Button dBtn;
	
	
	String input;
	boolean songOver = false;
	
	SoundGenerator sg = new SoundGenerator();
		
	public void pressA() {
		sg.playSound("4C",100);
//		aBtn.getStyleClass().add("pressed");

		
	}
	
	public void pressS() {
		sg.playSound("4D",100);
//		sBtn.getStyleClass().add("pressed");


	}
	
	public void pressD() {
		sg.playSound("4E",100);
//		dBtn.getStyleClass().add("pressed");


	}
}
