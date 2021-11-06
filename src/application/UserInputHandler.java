package application;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.KeyEvent;

public class UserInputHandler implements EventHandler<KeyEvent> {

	FXMLLoader loader = new FXMLLoader(getClass().getResource("GameplayScene.fxml"));
	GameplaySceneController controller = new GameplaySceneController(); 

			@Override
			public void handle(KeyEvent event) {
				System.out.println(event.getCode());

				switch (event.getCode()) {
				case A:
					controller.pressA();
					break;
				case S:
					controller.pressS();
					break;
				case D:
					controller.pressD();
					break;
				default:
					break;
				}				
			}

	
	}

	

	


