package application;

/**
 * Stores the settings a user has made. Is invoked in @PatternHeroGame
 *
 * @author julia
 */
public class Settings {
    //Default values
    private String song = "queen.mid";
    private int difficulty = 1;
    private int lives = 100;

    private int BPM = 60;

    //Setters and Getters
    public String getSong() {
        return song;
    }


    public void setSong(String song) {
        this.song = song;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getBPM() {
        return BPM;
    }

    public void setBPM(int BPM) {
        this.BPM = BPM;
    }
}
