package highscore;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Math.round;
import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;


public class HighScoreSceneController {
    double p1Average;
    double p2Average;
    ArrayList<String[]> player1Games = new ArrayList<>();
    ArrayList<String[]> player2Games = new ArrayList<>();
    ArrayList<GameRecord> p1Top5;
    ArrayList<GameRecord> p2Top5;
    @FXML
    private Text textP1Avg;
    @FXML
    private Text textP2Avg;
    //Date
    @FXML
    private Text txtP1D1;
    @FXML
    private Text txtP1D2;
    @FXML
    private Text txtP1D3;
    @FXML
    private Text txtP1D4;
    @FXML
    private Text txtP1D5;
    @FXML
    private Text txtP2D1;
    @FXML
    private Text txtP2D2;
    @FXML
    private Text txtP2D3;
    @FXML
    private Text txtP2D4;
    @FXML
    private Text txtP2D5;
    //Song
    @FXML
    private Text txtP1S1;
    @FXML
    private Text txtP1S2;
    @FXML
    private Text txtP1S3;
    @FXML
    private Text txtP1S4;
    @FXML
    private Text txtP1S5;
    @FXML
    private Text txtP2S1;
    @FXML
    private Text txtP2S2;
    @FXML
    private Text txtP2S3;
    @FXML
    private Text txtP2S4;
    @FXML
    private Text txtP2S5;
    //Points
    @FXML
    private Text txtP1P1;
    @FXML
    private Text txtP1P2;
    @FXML
    private Text txtP1P3;
    @FXML
    private Text txtP1P4;
    @FXML
    private Text txtP1P5;
    @FXML
    private Text txtP2P1;
    @FXML
    private Text txtP2P2;
    @FXML
    private Text txtP2P3;
    @FXML
    private Text txtP2P4;
    @FXML
    private Text txtP2P5;
    public HighScoreSceneController() {
        separateLists(readCSV());
        p1Average = calculateAverage(player1Games);
        p2Average = calculateAverage(player2Games);
        p1Top5 = findTop5(player1Games);
        p2Top5 = findTop5(player2Games);
    }

    @FXML
    private void initialize() {
        displayInformation(p1Average, p2Average, p1Top5, p2Top5);
    }

    public void log(LocalDateTime date, String p1Points, String p2Points, String songName) throws IOException {
        Files.write(Paths.get("history.csv"),
                "%s;%s;%s;%s;%s".formatted(
                        date,
                        p1Points,
                        p2Points,
                        songName,
                        System.lineSeparator()).getBytes(), APPEND, CREATE
        );
    }

    private ArrayList<String> readCSV() {
        ArrayList<String> gamesList = new ArrayList<>();
        // Create a file object and assign it to our file (must be in project folder)
        File file = new File("history.csv");

        // Declare BufferedReader & FileReader in argument of try-block. This way, they are automatically closed.
        try (FileReader fr = new FileReader(file);
             BufferedReader br = new BufferedReader(fr)) {
            String line;
            while ((line = br.readLine()) != null && line.length() > 0) {
                gamesList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return gamesList;
    }

    /**
     * Takes logs for each game and turns them into logs for each game but per player
     *
     * @param gamesList list of games previously read from CSV
     */
    private void separateLists(ArrayList<String> gamesList) {


        for (String gameAsString : gamesList) {

            //Make string of an entry into an array (separate string by comma)
            String[] gameAsArray = gameAsString.split(";");
            //Index now: 0:Date 1:P1Score 2:P2Score 3:song

            //Trim date String
            String date = gameAsArray[0].substring(0, 10);


            //Separate entries for p1 and p2
            //make two new arrays built as follows: 0: Date, 1: Score, 2: song name
            String[] p1game = {date, gameAsArray[1], gameAsArray[3]};
            String[] p2game = {date, gameAsArray[2], gameAsArray[3]};
            player1Games.add(p1game);
            player2Games.add(p2game);
        }

    }

    /**
     * @param list either player1Games or player2Games
     * @return average score of player's games
     */
    private double calculateAverage(ArrayList<String[]> list) {
        int gameAmount = list.size();
        int sum = 0;
        //Take all the scores from the games and add them together
        for (String[] game : list) {
            sum += Integer.parseInt(game[1]);
        }
        float num = (float) sum / gameAmount;
        return round(num);
    }

    /**
     * @param list either player1Games or player2Games
     * @return List of the top 5 games for the player
     */
    private ArrayList<GameRecord> findTop5(ArrayList<String[]> list) {
        //make a new Object for each element in array
        List<GameRecord> grList = new ArrayList<>();

        for (String[] game : list) {
            GameRecord record = new GameRecord((game[0]), Integer.parseInt(game[1]), game[2]);
            grList.add(record);
        }

        grList.sort((o1, o2) -> {
            // compare two instance of GameRecord by score
            return o2.getScore().compareTo(o1.getScore());
        });

        ArrayList<GameRecord> top5 = new ArrayList<>();
        //If there's less than 5 records get all there are, otherwise get 5
        int num = Math.min(grList.size(), 5);
        for (int i = 0; i < num; i++) {
            GameRecord gr = grList.get(i);
            top5.add(gr);
        }
        return top5;
    }

    public void displayInformation(double p1Average, double p2Average, ArrayList<GameRecord> p1Top5, ArrayList<GameRecord> p2Top5) {

        //Display Averages
        textP1Avg.setText((Double.toString(p1Average)));
        textP2Avg.setText((Double.toString(p2Average)));

        //Display Scores
        List<Text> allP1DateTexts = Arrays.asList(txtP1D1, txtP1D2, txtP1D3, txtP1D4, txtP1D5);
        List<Text> allP1SongTexts = Arrays.asList(txtP1S1, txtP1S2, txtP1S3, txtP1S4, txtP1S5);
        List<Text> allP1PointsTexts = Arrays.asList(txtP1P1, txtP1P2, txtP1P3, txtP1P4, txtP1P5);
        List<Text> allP2DateTexts = Arrays.asList(txtP2D1, txtP2D2, txtP2D3, txtP2D4, txtP2D5);
        List<Text> allP2SongTexts = Arrays.asList(txtP2S1, txtP2S2, txtP2S3, txtP2S4, txtP2S5);
        List<Text> allP2PointsTexts = Arrays.asList(txtP2P1, txtP2P2, txtP2P3, txtP2P4, txtP2P5);

        for (int i = 0; i < p1Top5.size(); i++) {
            allP1DateTexts.get(i).setText(p1Top5.get(i).getDate());
            allP1SongTexts.get(i).setText(p1Top5.get(i).getSongName());
            allP1PointsTexts.get(i).setText(String.valueOf(p1Top5.get(i).getScore()));
        }
        for (int i = 0; i < p2Top5.size(); i++) {
            allP2DateTexts.get(i).setText(p2Top5.get(i).getDate());
            allP2SongTexts.get(i).setText(p2Top5.get(i).getSongName());
            allP2PointsTexts.get(i).setText(String.valueOf(p2Top5.get(i).getScore()));
        }

    }


    public void switchToStartScene(ActionEvent event) throws IOException {

        //Load Scene
        Parent root = FXMLLoader.load(getClass().getResource("/application/StartScene.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);

        //set CSS
        String css = this.getClass().getResource("/themes/classic-theme.css").toExternalForm();
        scene.getStylesheets().add(css);

        stage.setScene(scene);
        stage.show();
    }

}
