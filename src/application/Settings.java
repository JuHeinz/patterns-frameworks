package application;

import java.util.List;
import songs.Songs;

/**
 * Stores the settings a user has made. Is invoked in @PatternHeroGame
 * @author julia
 *
 */
public class Settings {
	//Default values
	private List<String> song = Songs.removeNull(Songs.maryHadA);
	private int difficulty = 1;
	private int lives = 100;
	
	//Setters and Getters
	public List<String> getSong() {
		return song;
	}
	public void setSong(List<String> song) {
		this.song = song;
	}
	public int getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(int difficultiy) {
		this.difficulty = difficultiy;
	}
	public int getLives() {
		return lives;
	}
	public void setLives(int lives) {
		this.lives = lives;
	}

}
