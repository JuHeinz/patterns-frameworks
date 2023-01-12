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

    private int BPM = 20;

    private static String theme = "pastel-theme.css";



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

    public static String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }
}

