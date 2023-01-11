package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;



/**
 * Handles incoming requests from the model zu change the view.
 *
 * @author julia
 */


public class GameplaySceneController {

    int lives = 100;

    @FXML
    private Text noteFeedback;
    @FXML
    private Text liveCounter;

    @FXML
    private Rectangle testNoteBlock;

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
}
