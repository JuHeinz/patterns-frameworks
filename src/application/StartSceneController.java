package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.stage.Stage;

import java.io.IOException;


public class StartSceneController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    Spinner<Integer> bpmInput;

    @FXML
    Spinner<Integer> livesInput;

    @FXML
    Button btnStartSong1;
    @FXML
    Button btnStartSong2;
    @FXML
    Button btnStartSong3;

    public void switchToGameplayScene(ActionEvent event) throws IOException {


        //Load Gameplay Scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GameplayScene.fxml"));
        root = loader.load();

        // Insert settings into GameplayScene via instance of GameplaySceneController
        GameplaySceneController gameplaySceneController = loader.getController();

        //Choose which song name to display accordning to selected button
        gameplaySceneController.setStartInformation("Testsong");

        //Set Stage
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        //set CSS
        String css = this.getClass().getResource(Settings.getTheme()).toExternalForm();
        scene.getStylesheets().add(css);

        stage.setScene(scene);
        stage.show();
    }
}
