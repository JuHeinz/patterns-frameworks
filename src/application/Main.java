package application;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
/**
 * Start-Klasse.
 * @author julia
 *
 */
public class Main extends Application {
	

	static UserInputHandler userInputHandler = new UserInputHandler();
	
	/**
	 * start() Method is inherited from JavaFX Application class
	 */
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
			
			//USER INPUT
			//Sobald in der Scene ein KeyInput reinkommt, wird der UIP aufgerufen.
			scene.setOnKeyPressed(userInputHandler);			

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		//GAME LOGIC THREAD
		PatternHeroGame game = new PatternHeroGame(userInputHandler);
		Thread gameLogicThread = new Thread(game);
		gameLogicThread.start();
		
		//GRAPHICS THREAD (JavaFX macht automatisch einen Thread)
		launch(args);
		
	}
}
