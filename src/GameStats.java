
public class GameStats {

	private int myLevel;
	private int myScore;
	
	public GameStats(){
		this.myLevel = 1;
		this.myScore = 0;
	}
	
	public void nextLevel(){
		this.myLevel++;
	}
	
	public void addToScore(int value){
		this.myScore += value;
	}
	
	public int getLevel(){
		return this.myLevel;
	}
	
	public int getScore(){
		return this.myScore;
	}
}
