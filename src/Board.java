public class Board {

	private Bubble[][] myBoard = new Bubble[15][15];
	final private int [] SCORE_NEEDED = {225,325,450,575,700};
	private int myLevel;
	private int myScore = 0;
	private int myScoreNeeded;

	public Board(int level, int score, int scoreNeeded) {
		this.myLevel = level;
		this.myScore = score;
		this.myScoreNeeded = scoreNeeded;
		
		for (int r = 0; r < 15; r++)
			for (int c = 0; c < 15; c++) {
				int i = (int) (Math.random() * (this.myLevel) + 1);
				this.myBoard[r][c] = new Bubble(i, r, c);
			}
	}

	public void recursionCall(int row, int col, int correctColor) {
		if (row >= 0 && row < this.myBoard.length && col >= 0
				&& col < this.myBoard.length)
			if (this.myBoard[row][col].getColor() == correctColor
					&& this.myBoard[row][col].getStatus() != true) {
				this.myBoard[row][col].setStatus(true);
				this.myBoard[row][col].setColor(0);
				this.myScore++;
				recursionCall(row - 1, col, correctColor);
				recursionCall(row + 1, col, correctColor);
				recursionCall(row, col - 1, correctColor);
				recursionCall(row, col + 1, correctColor);
			}
	}

	public void shiftBoardDown() {

		int currentC = 0;
		
		while(currentC < 15){
			Bubble [] temp = new Bubble [15];
			int hits = 14;
			
			//change <= to >=
			for(int i = 14; i >= 0; i--){
				
				if(this.myBoard[i][currentC].getColor() != 0){
					temp[hits] = new Bubble(this.myBoard[i][currentC].getColor(), hits, currentC);
					hits--;
				}
			}
			
			while(hits >= 0){
				temp[hits] = new Bubble(0, hits, currentC);
				hits--;
			}
			for(int r = 0; r < 15; r++){
				this.myBoard[r][currentC] = temp[r];
			}
			currentC++;
		}
		
	}

	public void shiftBoardRight() {
		
		int currentR = 0;
		
		while(currentR < 15){
			Bubble [] temp = new Bubble [15];
			int hits = 14;
			
			for(int i = 14; i >= 0; i--){
				if(this.myBoard[currentR][i].getColor() != 0){
					temp[hits] = new Bubble(this.myBoard[currentR][i].getColor(), currentR, hits);
					hits--;
				}
			}
			
			while(hits >= 0){
				temp[hits] = new Bubble(0, currentR, hits);
				hits--;
			}
			for(int c = 0; c < 15; c++){
				this.myBoard[currentR][c] = temp[c];
			}
			currentR++;
		}
		
	}

	public boolean isLevelOver() {
		for (int r = 0; r < 15; r++)
			for (int c = 0; c < 15; c++)
				if (this.myBoard[r][c].getStatus() == false)
					return false;
		return true;
	}

	public int getLevel() {
		return this.myLevel;
	}

	public int getScore() {
		return this.myScore;
	}
	
	public int getScoreNeeded(){
		return this.SCORE_NEEDED[this.getLevel() - 1];
	}

	public void nextLevel() {
		this.myLevel++;
		Board b = new Board(this.getLevel(), this.getScore(), this.getScoreNeeded());
		new MainGameGui(b);
	}

	public Bubble getBubble(int row, int col) {
		return this.myBoard[row][col];
	}

	public void setBubble(int row, int col, Bubble bubble) {
		this.myBoard[row][col] = bubble;
	}
	
	public void restart(){
		Board b = new Board(1, 0, 225);
		new MainGameGui(b);
	}
	
	public String toString(){
		String s = "";
		
		for (int r = 0; r < 15; r++){
			s += "[";
			for (int c = 0; c < 15; c++){
				s += this.myBoard[r][c] + ", ";
			}
			s += "]\n";
		}  		
		return s;
	}
}
