package highscore;

public class GameRecord {


    private final String date;
    private final int score;
    private final String songName;

    public GameRecord(String date, int score, String songName) {
        this.score = score;
        this.date = date;
        this.songName = songName;
    }

    public Integer getScore() {
        return score;
    }

    public String getSongName() {
        return songName;
    }

    public String getDate() {
        return date;
    }


}
