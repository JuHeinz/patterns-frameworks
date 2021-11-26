package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class GraphicsTask extends Application implements Runnable{
@Override
	

	public void start(Stage primaryStage) {
		try {
			//JAVAFX STUFF
			//Erstellt die JavaFX Scene
			FXMLLoader loader = new FXMLLoader(getClass().getResource("GameplayScene.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			//Set Title
			primaryStage.setTitle("PatternHero!");
			//Set Icon
			Image icon = new Image("icon.png");
			primaryStage.getIcons().add(icon);
			
			primaryStage.setScene(scene);
			primaryStage.show();
			
			
			//Sobald in der Scene ein KeyInput reinkommt, wird der UIP aufgerufen.
			scene.setOnKeyPressed(new UserInputHandler());			
			
			
			
		
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

@Override
//RUN must be implemented cause this is a Runnable
public void run() {
	//Lauch must be implemented cause of JAVAFX
	start(new Stage());
	Application.launch();
	
}
}
