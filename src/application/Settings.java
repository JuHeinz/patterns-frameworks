package application;

/**
 * Stores the settings a user has made.
 *
 * @author julia
 */
public class Settings {
    //Default values
    private String song = "swallows.mid";
    private int lives = 100;

    private int BPM = 70;




    //Setters and Getters
    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
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
