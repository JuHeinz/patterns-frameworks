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
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;


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
    @FXML
    private Button btnViewResults;

    @FXML
    private Button aBtn;
    @FXML
    private Button sBtn;
    @FXML
    private Button dBtn;
    @FXML
    private Button fBtn;
    @FXML
    private Button hBtn;
    @FXML
    private Button jBtn;
    @FXML
    private Button kBtn;
    @FXML
    private Button lBtn;
    @FXML
    private Rectangle aNoteBlock;
    @FXML
    private Rectangle sNoteBlock;
    @FXML
    private Rectangle dNoteBlock;
    @FXML
    private Rectangle fNoteBlock;
    @FXML
    private Rectangle hNoteBlock;
    @FXML
    private Rectangle jNoteBlock;
    @FXML
    private Rectangle kNoteBlock;
    @FXML
    private Rectangle lNoteBlock;

    private boolean isOver;

    /**
     * Displays information needed for start of game. Like songname + title.
     * This information comes from the StartScene
     */
    public void setStartInformation(String songName, String midiFileName, int BPM, int initialLives) {
        this.BPM = BPM;
        lives = initialLives;
        songLabel.setText(songName);
        this.midiFileName = midiFileName;
        sharedLivesDisplay.setText(String.valueOf(initialLives));
    }

    /**
     * Sets text in scene according to how well user hit note, updates live count. Gets called on key press or mouse click.
     */
    public void giveFeedback(String inputKey) {
        if (!getIsOver()){
        long tick = game.ph.sequencerTickPosition;

        int player;
        String key;
        int notePointsAmount;
        String noteFeedbackText;

        if ("HJKLhjkl".contains(inputKey)) {
            player = 2;
            key = Player.mapPlayerKey(inputKey);
        } else {
            player = 1;
            key = inputKey;
        }

        String note = game.ph.nextNote;
        if (key.equalsIgnoreCase(note)) {
            long lag = game.ph.nextTick - tick;
            if (Math.abs(lag) > 200) {
                notePointsAmount = 1;
                noteFeedbackText = "Bad";
            } else if (Math.abs(lag) > 100) {
                notePointsAmount = 10;
                noteFeedbackText = "Okay";
            } else {
                notePointsAmount = 100;
                noteFeedbackText = "PERFECT!";
            }
            System.out.printf("Player %d score: %d (%s) %n", player, notePointsAmount, noteFeedbackText);
        } else {
            lives--;
            notePointsAmount = 0;
            noteFeedbackText = "FALSE!";
        }

        if (player == 1) {
            P1noteFeedback.setText(noteFeedbackText);
            P1PointsDisplay.setText(Integer.toString(Integer.parseInt(P1PointsDisplay.getText()) + notePointsAmount));
        }
        if (player == 2) {
            P2noteFeedback.setText(noteFeedbackText);
            P2PointsDisplay.setText(Integer.toString(Integer.parseInt(P2PointsDisplay.getText()) + notePointsAmount));
        }

        sharedLivesDisplay.setText(String.valueOf(lives));

        if (lives <= 0) {
            stopGame();
        }
        }
    }



    //this gets called from the sequencer every 100ms
    public void updateUI(long sequencerTickPosition, String nextNote, long nextTick) {
        List<Button> allButtons = Arrays.asList(aBtn, sBtn,dBtn,fBtn,hBtn,jBtn,kBtn,lBtn);
        List<Rectangle> allRectangles = Arrays.asList(aNoteBlock, sNoteBlock,dNoteBlock,fNoteBlock,hNoteBlock,jNoteBlock,kNoteBlock,lNoteBlock);

        //Avoid throwing an error on the first note
        if (nextNote == null) {
            nextNote = "A";
        }

        long distance = calculateRelativeDistance(nextTick, sequencerTickPosition);

        //Reset styles
        for (Rectangle r : allRectangles){
            r.setStyle(defaultStyle);
        }

        for (Button b : allButtons){
            b.setStyle(notNowStyle);
        }

        //set style for upcoming blocks and buttons
        switch (nextNote) {
            case "A" -> setStyles(aNoteBlock,hNoteBlock,aBtn,hBtn,distance);
            case "S" -> setStyles(sNoteBlock,jNoteBlock,sBtn,jBtn,distance);
            case "D" -> setStyles(dNoteBlock,kNoteBlock,dBtn,kBtn,distance);
            case "F" -> setStyles(fNoteBlock,lNoteBlock,fBtn,lBtn,distance);
        }


    }

    String defaultStyle = "-fx-fill: #16161A;";
    String upNextStyle = "-fx-fill:white;";
    String nowStyle = "-fx-background:transparent;";
    String notNowStyle = "-fx-background-color: #16161A;";


    public void setStyles(Rectangle noteBlock1,Rectangle noteBlock2, Button b1, Button b2, long distance ){
        if (distance <= 50){
            b1.setStyle(nowStyle);
            b2.setStyle(nowStyle);
        }else {
            b1.setStyle(notNowStyle);
            b2.setStyle(notNowStyle);
        }
        noteBlock1.setStyle(upNextStyle);
        noteBlock2.setStyle(upNextStyle);
    }
    public void stopGame() {
        String overText = "";
        String winner;
        if (lives< 1){
            overText="You both lost because you ran out of lives";
            game.ph.stopSequencer();

        }else if (lives > 1){
            int p1Points = Integer.parseInt((P1PointsDisplay.getText()));
            int p2Points = Integer.parseInt((P1PointsDisplay.getText()));
            if (p1Points > p2Points){
                 winner = "Player1";
            }else if (p2Points == p1Points){
                 winner = "Both";
            }else {
                 winner ="Player2";
            }
            overText= winner + " won!";
        }
        // previously invisible button is shown
        btnViewResults.setVisible(true);
        songLabel.setText(overText);
        setOver(true);
    }


    public long calculateRelativeDistance(long nextTick, long sequencerTickPosition) {
        //simple distance
        long distance = nextTick - sequencerTickPosition;

        if (distance <= 0) {
            return 0;
        } else if (distance >= 100) {
            return 100;
        } else {
            //distance is a value between 1 and 99
            //calculate this so corresponds to pixel positions
            return distance;
        }
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


    public void logHistory() throws IOException {
        Files.write(Paths.get("history.csv"),
                "%s;%s;%s;%s;%s".formatted(
                        LocalDateTime.now(),
                        P1PointsDisplay.getText(),
                        P2PointsDisplay.getText(),
                        game.ph.getFileName(),
                System.lineSeparator()).getBytes(), APPEND, CREATE
        );
    }


    /**
     * Function to switch to Start Screen or Game Over Screen after gameplay
     */
    public void switchToScene(ActionEvent event) throws IOException {
        //stops sequencer
        game.ph.stopSequencer();

        Button pressedButton = (Button) event.getSource();
        String buttonId = pressedButton.getId();

        String fxmlFile;
        if (Objects.equals(buttonId, "btnViewResults")) {
            fxmlFile = "HighScoreScene.fxml";
        } else {
            fxmlFile = "StartScene.fxml";
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


    /**
     * Handles click on button, alternative to keyboard input
     *
     * @param e Event that was called
     */
    public void handleButton(ActionEvent e) {
        giveFeedback(((Button) e.getSource()).getText());
    }

    /**
     * Checks if pressed keyboard button is one of the game keys and processes it from there
     *
     * @param event The event of the key that was pressed
     */
    public void handleKeyboard(KeyEvent event) {
        String key = event.getText();
        if ("asdflkjhASDFLKJH".contains(key))
            giveFeedback(key.toUpperCase());
    }


    public boolean getIsOver() {
        return isOver;
    }

    public void setOver(boolean over) {
        isOver = over;
    }
}
