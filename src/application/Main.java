package application;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Start-Klasse.
 * @author julia
 *
 */
public class Main extends Application {
	



	/**
	 * start() Method is inherited from JavaFX Application class
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			//JAVAFX STUFF
			//Create JavaFX Scene
			FXMLLoader loader = new FXMLLoader(getClass().getResource("StartScene.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);

			//set CSS
			String css = this.getClass().getResource("/themes/classic-theme.css").toExternalForm();
			scene.getStylesheets().add(css);

			//Set Title
			primaryStage.setTitle("PatternHero!");

			//Set Icon
			Image icon = new Image("icon.png");
			primaryStage.getIcons().add(icon);
			
			primaryStage.setScene(scene);
			primaryStage.show();
			


		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		

		//GRAPHICS THREAD (JavaFX creates a Thread automatically)
		launch(args);
		
	}
}
