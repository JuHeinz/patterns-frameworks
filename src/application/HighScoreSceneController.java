package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class HighScoreSceneController {
    @FXML private Text textP1Avg;
    @FXML private Text textP2Avg;


    double p1Average;
    double p2Average;




    public HighScoreSceneController(){
        separateLists(readCSV());
        p1Average = calculateAverage(player1Games);
        p2Average = calculateAverage(player2Games);
        p1Top5 = findTop5(player1Games);
        p2Top5 = findTop5(player2Games);
    }

    @FXML
    private void initialize()
    {
        displayInformation(p1Average, p2Average, p1Top5, p2Top5);
    }

    private ArrayList<String> readCSV(){
        ArrayList<String> gamesList  = new ArrayList<>();
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

    ArrayList<String[]> player1Games = new ArrayList<>();
    ArrayList<String[]> player2Games = new ArrayList<>();
    ArrayList<GameRecord> p1Top5;
    ArrayList<GameRecord> p2Top5;
    /**
     * Takes logs for each game and turns them into logs for each game but per player
     * @param gamesList list of games previously read from CSV
     */
    private void separateLists(ArrayList<String> gamesList){


        for (String gameAsString : gamesList){

                //Make string of an entry into an array (separate string by comma)
                String[] gameAsArray = gameAsString.split(";");
                //Index now: 0:Date 1:P1Score 2:P2Score 3:song


                //Separate entries for p1 and p2
                //make two new arrays built as follows: 0: Date, 1: Score, 2: song name
                String[] p1game = {gameAsArray[0], gameAsArray[1], gameAsArray[3]};
                String[] p2game = {gameAsArray[0], gameAsArray[2], gameAsArray[3]};
                player1Games.add(p1game);
                player2Games.add(p2game);
        }

    }

    /**
     * @param list either player1Games or player2Games
     * @return average score of player's games
     */
    private double calculateAverage(ArrayList<String[]> list){
        int gameAmount = list.size();
        int sum = 0;
        //Take all the scores from the games and add them together
        for (String[] game : list){
            sum += Integer.parseInt(game[1]);
        }
        return (float)sum/gameAmount;
    }

    /**
     * @param list either player1Games or player2Games
     * @return List of the top 5 games for the player
     */
    private ArrayList<GameRecord> findTop5(ArrayList<String[]> list){
        //make a new Object for each element in array
        List<GameRecord> grList = new ArrayList<>();

        for (String[] game : list){
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
        for (int i = 0; i < num; i++){
            GameRecord gr = grList.get(i);
            top5.add(gr);
        }
        return top5;
    }

    public void displayInformation(double p1Average, double p2Average, ArrayList<GameRecord> p1Top5, ArrayList<GameRecord> p2Top5){

        if (p1Top5.size() == 0 || p2Top5.size() == 0){
            System.out.println("No records to log. Go play some games!");
        }else {

        System.out.println("--------HIGH SCORES---------");
        System.out.println("PLAYER 1");
        for (GameRecord r : p1Top5){
            System.out.println(r.getScore() + "! Song: " +r.getSongName() + " Date: " +r.getDate() );
        }
        System.out.println("PLAYER 2");
        for (GameRecord r : p2Top5){
            System.out.println(r.getScore() + "! Song: " +r.getSongName() + " Date: " +r.getDate() );
        }
        System.out.println("--------AVERAGES---------");
        System.out.println("Player 1:" + p1Average);
        System.out.println("Player 2:" + p2Average);

        //TODO This throws NPE, saying that those fields are null even though they are defined
        //There is possibly an issue with the loading of this scene?
        textP1Avg.setText((Double.toString(p1Average)));
        textP2Avg.setText((Double.toString(p2Average)));
        }
    }

    public void switchToStartScene(ActionEvent event) throws IOException {

        //Load Scene
        Parent root = FXMLLoader.load(getClass().getResource("StartScene.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);

        //set CSS
        String css = this.getClass().getResource("/themes/classic-theme.css").toExternalForm();
        scene.getStylesheets().add(css);

        stage.setScene(scene);
        stage.show();
    }

}
