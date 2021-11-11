package application;
	

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	
	//TODO: In Threads aufteilen.
	//THREAD 1:
	//JAVAFX, 
	
	//THREAD 2:
	// Model, Gameplay
	
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
			
			PatternHeroGame game = new PatternHeroGame();
			game.run();
			
			//Sobald in der Scene ein KeyInput reinkommt, wird der UIP aufgerufen.
			scene.setOnKeyPressed(new UserInputHandler());			
			
		
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
