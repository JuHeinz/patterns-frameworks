package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class OverSceneController {

    /**
     * Switches to a different scene, depending on what button was pressed
     * @param event includes button that was pressed

     */
    public void switchToScene(ActionEvent event) throws IOException {
        Button pressedButton = (Button) event.getSource();
        String buttonId = pressedButton.getId();

        String fxmlFile;
        if (Objects.equals(buttonId, "btnToStart")){
            fxmlFile = "StartScene.fxml";
        }else {
            fxmlFile = "HighscoreScene.fxml";
        }

        //Load Scene
        Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);

        //set CSS
        String css = this.getClass().getResource("/themes/classic-theme.css").toExternalForm();
        scene.getStylesheets().add(css);

        stage.setScene(scene);
        stage.show();
    }


}
