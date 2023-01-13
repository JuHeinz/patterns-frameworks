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
import java.util.concurrent.atomic.AtomicReference;


public class StartSceneController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    private String theme;
    private String songName;
    private String midiFileName;
    private int lives;
    private int BPM;

    static AtomicReference<String> lastInput = new AtomicReference<>("");

    static UserInputHandler userInputHandler = new UserInputHandler(lastInput);

    public void listenToButton(ActionEvent event) throws IOException {
        Button pressedButton = (Button) event.getSource();
        String buttonId = pressedButton.getId();
        //If theme Button, switchTheme()
        if (Objects.equals(buttonId, "btnThemePastel") | Objects.equals(buttonId, "btnThemeNeon") | Objects.equals(buttonId, "btnThemeClassic")){
            this.theme = switchTheme(buttonId);
        }
        //If start Button, startGame()
        else if (Objects.equals(buttonId, "btnStartSong1") | Objects.equals(buttonId, "btnStartSong2") | Objects.equals(buttonId, "btnStartSong3")) {
            startGame(event);
        }
        //If speed Button, change difficulty
        else if (Objects.equals(buttonId, "btnSpeedSlow") | Objects.equals(buttonId, "btnSpeedMedium") | Objects.equals(buttonId, "btnSpeedFast")) {
            setBPM(buttonId);
        }
        //If difficulty(lives) Button, change difficulty
        else if (Objects.equals(buttonId, "btnDifficultyEasy") | Objects.equals(buttonId, "btnDifficultyMedium") | Objects.equals(buttonId, "btnDifficultyHard")) {
            setLives(buttonId);
        }

    }

    public void startGame(ActionEvent event) throws IOException {

        setSongInformationFromButton(event);

        //If user did not make speed selection, speed is 90 (=regular)
        if (BPM == 0){
            this.BPM = 90;
        }

        //If user did not make difficulty selection, lives is 50 (=regular)
        if (lives == 0){
            this.lives = 50;
        }

        //Instantiate and declare a new game and run it in its own thread
        PatternHeroGame game = new PatternHeroGame(BPM, lives, midiFileName,userInputHandler,lastInput);
        Thread gameLogicThread = new Thread(game);
        gameLogicThread.start();

        //Have JavaFX switch to Gameplay Scene
        switchToGameplayScene(event);
    }

    public void switchToGameplayScene(ActionEvent event) throws IOException {

        //Load Gameplay Scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GameplayScene.fxml"));
        root = loader.load();

        // Insert settings into GameplayScene via instance of GameplaySceneController
        GameplaySceneController gameplaySceneController = loader.getController();

        //Choose which song name to display according to selected button
        gameplaySceneController.setStartInformation(songName, BPM, lives);

        //Set Stage
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        //set CSS
        //If user did not make any selection, classic theme is used
        if(theme == null){
            theme = this.getClass().getResource("/themes/classic-theme.css").toExternalForm();
        }
        scene.getStylesheets().add(theme);

        stage.setScene(scene);
        stage.show();
    }

    private String switchTheme(String buttonID){
        String themeName;
        //Set theme name depending on what button was pressed
        if (Objects.equals(buttonID, "btnThemeClassic")){
            themeName = "classic-theme.css";
        } else if (Objects.equals(buttonID, "btnThemeNeon")) {
            themeName = "neon-theme.css";
        }else{
            themeName = "pastel-theme.css";
        }
        System.out.println("Theme set to:" + themeName);

        //Return theme to be used in GameplayScene when it is loaded
        return this.getClass().getResource("/themes/"+ themeName).toExternalForm();
    }

    //Set midi-file and Song Name for chosen Button
    private void setSongInformationFromButton(ActionEvent event){
        Button pressedButton = (Button) event.getSource();
        String buttonId = pressedButton.getId();
        switch (buttonId) {
            case "btnStartSong1" -> {
                midiFileName = "bluemtns.mid";
                songName = "The Blue Alsatian Mountains - S. Adams (1844–1913)";
            }
            case "btnStartSong2" -> {
                midiFileName = "swallows.mid";
                songName = "When the Swallows Homeward Fly (Agathe) - F. Abt (1819–1885)";
            }
            case "btnStartSong3" -> {
                midiFileName = "Vocalise1.mid";
                songName = "Vocalise No 1 - F. Abt (1819–1885)";
            }
        }
    }

    private void setBPM(String buttonID){
        switch (buttonID){
            case "btnSpeedSlow" -> this.BPM = 55;
            case "btnSpeedMedium" -> this.BPM = 90;
            case "btnSpeedFast" -> this.BPM = 100;
        }
    }

    private void setLives(String buttonID){
        switch (buttonID){
            case "btnDifficultyEasy" -> this.lives = 100;
            case "btnDifficultyMedium" -> this.lives = 50;
            case "btnDifficultyHard" -> this.lives = 25;
        }    }

}
