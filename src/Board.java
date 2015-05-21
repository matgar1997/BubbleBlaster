public class Board {

	private Bubble[][] myBoard = new Bubble[15][15];
	private int myLevel;
	private int myScore = 0;

	public Board(int level, int score) {
		this.myLevel = level;
		this.myScore = score;
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

	}

	public void shiftBoardRight() {

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

	public void nextLevel() {
		this.myLevel++;
		Board b = new Board(this.getLevel(), this.getScore());
		new MainGameGui(b);
	}

	public Bubble getBubble(int row, int col) {
		return this.myBoard[row][col];
	}

	public void setBubble(int row, int col, Bubble bubble) {
		this.myBoard[row][col] = bubble;
	}
}
