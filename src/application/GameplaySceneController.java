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
import java.util.concurrent.atomic.AtomicReference;


/**
 * Handles incoming requests from the model zu change the view.
 *
 * @author julia
 */


public class GameplaySceneController {
    private PatternHeroGame game;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private int lives;
    private String midiFileName;
    private int BPM;

    @FXML
    private Text noteFeedback;
    @FXML
    private Text liveCounter;
    @FXML
    private Text songLabel;

    @FXML
    private Text upcomingKeyIndicator;
    @FXML
    private Rectangle testNoteBlock;

    @FXML
    private Text bpmDisplay;
    static AtomicReference<String> lastInput = new AtomicReference<>("");

    /**
     * Displays information needed for start of game. Like songname + title.
     * This information comes from the StartScene
     */
    public void setStartInformation(String songName, String midiFileName, int BPM, int initialLives){
        this.BPM = BPM;
        lives = initialLives;
        songLabel.setText(songName);
        this.midiFileName = midiFileName;
        bpmDisplay.setText(String.valueOf(BPM));
        liveCounter.setText(String.valueOf(initialLives));
    }

    public void displayUpcomingKey(String key){
        upcomingKeyIndicator.setText(key);
    }

    /**
     *Sets text in scene according to how well user hit note, updates live count.
     */
    public void giveFeedback(String key) {

        long tick = game.ph.sequencerTickPosition;

        String noteFeedbackText;

        String note = game.ph.nextNote;
        if (key.equalsIgnoreCase(note)) {
            long lag = game.ph.nextTick - tick;
            if (Math.abs(lag) > 100) {
                noteFeedbackText = "Bad";
                lives--;
            } else if (Math.abs(lag) > 10) {
                noteFeedbackText = "OK";
            } else {
                noteFeedbackText = "PERFECT!";
            }
            System.out.printf("Score: %d (%s) %n", lag, noteFeedbackText);
        } else {
            noteFeedbackText = "FALSE!"; lives--;
        }
        noteFeedback.setText(noteFeedbackText);
        liveCounter.setText(String.valueOf(lives));
        lastInput.set(key);
    }

    private double y;

    public void rainNoteBlock() {
        testNoteBlock.setY(y = y + 25);
        //if note block out of screen delete it
    }

    public void createNoteBlock(String nextKey){
        Rectangle noteBlock = new Rectangle();


    }

    public void startGame() throws IOException {

        //If user did not make speed selection, speed is 90 (=regular)
        if (BPM == 0) {
            BPM = 90;
        }

        //If user did not make difficulty selection, lives is 50 (=regular)
        if (lives == 0) {
            lives = 50;
        }

        //Instantiate and declare a new game and run it in its own thread
        game = new PatternHeroGame(BPM, lives, midiFileName, lastInput);
        Thread gameLogicThread = new Thread(game);
        gameLogicThread.start();
    }

    public void switchToStartScene(ActionEvent event) throws IOException {

        //Load Gameplay Scene
        root = FXMLLoader.load(getClass().getResource("StartScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        //set CSS
        String css = this.getClass().getResource("/themes/classic-theme.css").toExternalForm();
        scene.getStylesheets().add(css);


        //This line used to be in main, i don't know where to put it now so that it works
        //TODO fix
        //scene.setOnKeyPressed(userInputHandler.handle());

        stage.setScene(scene);
        stage.show();
    }

    //USER INPUT
    //On key input on the scene,

    public void handleButton(ActionEvent e) {
        giveFeedback(((Button)e.getSource()).getText());
    }

    public void handleKeyboard(KeyEvent event) {
        String key = event.getText();
        if ("asdflkjhASDFLKJH".contains(key))
            giveFeedback(key.toUpperCase());
    }
}
