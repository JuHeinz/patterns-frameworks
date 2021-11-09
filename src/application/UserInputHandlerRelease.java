package application;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.KeyEvent;

public class UserInputHandlerRelease implements EventHandler<KeyEvent> {
	
	FXMLLoader loader = new FXMLLoader(getClass().getResource("GameplayScene.fxml"));
	GameplaySceneController controller = new GameplaySceneController(); 

			public void handle(KeyEvent event) {
				System.out.println(event.getCode());

				switch (event.getCode()) {
				case A:
					System.out.println("A Key was released");
					break;
				case S:
					System.out.println("S Key was released");
					break;
				case D:
					System.out.println("D Key was released");
					break;
				default:
					break;
				}				
			}


}
