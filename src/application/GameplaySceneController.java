package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * Handles incoming requests from the model zu change the view.
 *
 * @author julia
 */


public class GameplaySceneController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    int lives = 100;

    @FXML
    private Text noteFeedback;
    @FXML
    private Text liveCounter;
    @FXML
    private Text songLabel;

    @FXML
    private Rectangle testNoteBlock;

    /**
     * Displays information needed for start of game. Like songname + title.
     * This information comes from the StartScene
     */
    public void setStartInformation(String songInfo){
        songLabel.setText(songInfo);

    }


    /**
     * @param e Sets text in scene according to how well user hit note, updates live count.
     */
    public void giveFeedback(ActionEvent e) {
        String noteFeedbackText;
        // create random number, just temp until actually connected to user inputs
        int min = 0; // Minimum value of range
        int max = 10; // Maximum value of range
        int random_int = (int) Math.floor(Math.random() * (max - min + 1) + min);


        if (random_int <= 3) {
            noteFeedbackText = "Bad";
            lives = lives - 1;
        } else if (random_int > 3 && random_int < 6) {
            noteFeedbackText = "OK";
        } else {
            noteFeedbackText = "PERFECT!";
        }
        noteFeedback.setText(noteFeedbackText);
        liveCounter.setText(String.valueOf(lives));
        rainNoteBlock();
    }

    private double y;

    public void rainNoteBlock() {
        testNoteBlock.setY(y = y + 25);
        //if note block out of screen delete it
    }

    public void createNoteBlock(String nextKey){
        Rectangle noteBlock = new Rectangle();

        switch (nextKey){
            //TODO Input in corresponding pane, according to key

            case "A":
                break;
            case "S":
                break;
            case "D":
                break;
            case "F":
                break;
        }
    }
    public void switchToStartScene(ActionEvent event) throws IOException {

        //Load Gameplay Scene
        root = FXMLLoader.load(getClass().getResource("StartScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        //set CSS
        String css = this.getClass().getResource(Settings.getTheme()).toExternalForm();
        scene.getStylesheets().add(css);

        stage.setScene(scene);
        stage.show();
    }

}
