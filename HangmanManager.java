import java.util.ArrayList;
import java.util.HashMap;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Collections;

public class HangmanManager {

	private HangManPanel hmPanel;
	private int wordLen, dif, lives, maxLives, letter = 0;
	private Dictionary dict;
	private String word;
	private boolean[] progress;
	private HangmanGame game;

	public HangmanManager(Dictionary dict, HangmanGame game){
		this.dict = dict;
		this.game = game;
	}

	public void prepForRound(int wordLen, int numGuesses, int dif){
		this.wordLen = wordLen;
		this.maxLives = numGuesses;
		this.lives = numGuesses;
		this.dif = dif;
		letter = 0;

		word = dict.getWord(wordLen,dif);

		progress = new boolean[wordLen];
		for(int i = 0;i < wordLen;i++){
			progress[i] = false;
		}

		//hmPanel.refresh();
		game.startGame();
	}

	public void startGame(){
		game.startGame();
	}

	public void endGame(){
		game.endGame();
	}

	public String getWord(){
		char[] charWord = word.toCharArray();
		char[] newWord = new char[wordLen*2];
		for(int i = 0;i < wordLen;i++){
			if(progress[i] == false && (!checkLose())){
				newWord[2*i] = '_';
			}
			else{
				newWord[2*i] = charWord[i];
			}
			newWord[2*i+1] = ' ';
		}

		return new String(newWord);
	}

	public boolean checkWin(){
		if(checkLose()) return false;
		boolean win = true;
		for(int i = 0;i < wordLen;i++){
			if(progress[i] == false){
				win = false;
			}
		}
		return win;
	}

	public boolean checkLose(){
		return (lives <= 0);
	}

	public void findLetter(String letter){
		if(checkWin()) return;
		char letterChar = letter.toLowerCase().charAt(0);
		boolean wrongLetter = true;
		for(int i = 0;i < wordLen;i++){		
			if(letterChar == word.toLowerCase().charAt(i)){
				progress[i] = true;
				wrongLetter = false;
			}
		}
		if(wrongLetter) loseLife();
		hmPanel.refresh();
	}

	public int getMaxLives(){
		return maxLives;
	}

	public int getLives(){
		return lives;
	}

	public void loseLife(){
		if(checkWin() || checkLose()) return;
		if(lives<=0) return;
		lives--;
		hmPanel.refresh();
	}

	public void setPanel(HangManPanel hmPanel){
		this.hmPanel = hmPanel;
	}

	public int getMaxLen(){
        return dict.getMaxLen();
    }

    public int getMinLen(){
        return dict.getMinLen();
    }


}