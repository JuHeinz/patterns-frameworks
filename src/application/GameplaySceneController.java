package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.beans.property.SimpleLongProperty;


public class GameplaySceneController {
    private PatternHeroGame game;
    private int lives;
    private String midiFileName;
    private int BPM;

    @FXML
    private Text P1noteFeedback;

    @FXML
    private Text P2noteFeedback;

    @FXML
    private Text songLabel;
    @FXML
    private Text sharedLivesDisplay;

    @FXML
    private Text P1PointsDisplay;

    @FXML
    private Text P2PointsDisplay;

    static SimpleLongProperty observableTick = new SimpleLongProperty();

    /**
     * Displays information needed for start of game. Like songname + title.
     * This information comes from the StartScene
     */
    public void setStartInformation(String songName, String midiFileName, int BPM, int initialLives){
        this.BPM = BPM;
        lives = initialLives;
        songLabel.setText(songName);
        this.midiFileName = midiFileName;
        sharedLivesDisplay.setText(String.valueOf(initialLives));
    }

    /**
     *Sets text in scene according to how well user hit note, updates live count. Gets called on key press or mouse click.
     */
    public void giveFeedback(String key) {

        //TODO move this function call so it is updated every few miliseconds, not on key press
        markNoteBlock(game.ph.nextNote);

        long tick = game.ph.sequencerTickPosition;

        String noteFeedbackText;

        String note = game.ph.nextNote;
        if (key.equalsIgnoreCase(note)) {
            long lag = game.ph.nextTick - tick;
            if (Math.abs(lag) > 200) {
                noteFeedbackText = "Bad";
            } else if (Math.abs(lag) > 100) {
                noteFeedbackText = "Okay";
            } else {
                noteFeedbackText = "PERFECT!";
            }
            System.out.printf("Score: %d (%s) %n", lag, noteFeedbackText);
        } else {
            noteFeedbackText = "FALSE!"; lives--;
        }
        P1noteFeedback.setText(noteFeedbackText);
        sharedLivesDisplay.setText(String.valueOf(lives));
    }

    public void updateUI(long tick) {
        System.out.println("tick " + tick); /* update UI here */ 
    }

    public void startGame() {

        //If user did not make speed selection, speed is 90 (=regular)
        if (BPM == 0) {
            BPM = 90;
        }

        //If user did not make difficulty selection, lives is 50 (=regular)
        if (lives == 0) {
            lives = 50;
        }

        //Instantiate and declare a new game and run it in its own thread
        game = new PatternHeroGame(BPM, lives, midiFileName, this);
        Thread gameLogicThread = new Thread(game);
        gameLogicThread.start();
    }

    /**
     * Function to stop playback and go back to main menu on "quit" button press
     */
    public void switchToStartScene(ActionEvent event) throws IOException {
        //stops sequencer
        game.ph.stopSequencer();


        //Load Gameplay Scene
        Parent root = FXMLLoader.load(getClass().getResource("StartScene.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);

        //set CSS
        String css = this.getClass().getResource("/themes/classic-theme.css").toExternalForm();
        scene.getStylesheets().add(css);

        stage.setScene(scene);
        stage.show();
    }


    /**
     * Handles click on button, alternative to keyboard input
     * @param e
     */
    public void handleButton(ActionEvent e) {
        giveFeedback(((Button)e.getSource()).getText());
    }

    /**
     * Handles keyboard input
     * @param event The event of the key that was pressed
     */
    public void handleKeyboard(KeyEvent event) {
        String key = event.getText();
        if ("asdflkjhASDFLKJH".contains(key))
            giveFeedback(key.toUpperCase());
    }

    @FXML
    private Rectangle aNoteBlock;

    @FXML
    private Rectangle sNoteBlock;

    @FXML
    private Rectangle dNoteBlock;

    @FXML
    private Rectangle fNoteBlock;



    /**
     * Marks what note to press next on screen
     * @param nextNote next note user has to press
     */
    public void markNoteBlock(String nextNote){
        String defaultStyle = "-fx-fill: black;";
        String upNextStyle = "-fx-fill:white;";

        //reset style for all blocks
        aNoteBlock.setStyle(defaultStyle);
        sNoteBlock.setStyle(defaultStyle);
        dNoteBlock.setStyle(defaultStyle);
        fNoteBlock.setStyle(defaultStyle);

        //set style for upcoming block
        switch (nextNote){
            case "A":
                aNoteBlock.setStyle(upNextStyle);
                break;
           case "S":
               sNoteBlock.setStyle(upNextStyle);
               break;
           case "D":
               dNoteBlock.setStyle(upNextStyle);
               break;
           case "F":
               fNoteBlock.setStyle(upNextStyle);
               break;
        }



    }


}
