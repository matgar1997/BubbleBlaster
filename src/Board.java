public class Board implements Shifting{
	
	private Bubble [][] myBoard = new Bubble[15][15];
	
	public Board(int level){
		for(int r = 0; r < 15; r++)
			for(int c = 0; c < 15; c++){
				int i = (int) (Math.random() * (level - 1) + 1);
				this.myBoard[r][c] = new Bubble(i,r,c);
			}
	}
	
	public void setSurroundingSimilarToTrue(int row, int col, int correctColor){
		if(row >= 0 && row < this.myBoard.length && col >= 0 && col < this.myBoard.length)
				if(this.myBoard[row][col].getColorValue() == correctColor && this.myBoard[row][col].getStatus() != true){
					this.myBoard[row][col].setStatusTrue();
					this.myBoard[row][col].setColorValue(0);
					//System.out.println(row);
					setSurroundingSimilarToTrue(row - 1, col, correctColor);
					setSurroundingSimilarToTrue(row + 1, col, correctColor);
					setSurroundingSimilarToTrue(row, col - 1, correctColor);
					setSurroundingSimilarToTrue(row, col + 1, correctColor);
				}			
	}
	
	public boolean checkIsLevelOver(){
		for(int r = 0; r < 15; r++)
			for(int c = 0; c < 15; c++)
				if(this.myBoard[r][c].getStatus() == false)
				{
					System.out.print("Game Not Over");
					return false;
				}
		System.out.print("Game Over");
		return true;
	}
	
	@Override
	public void shiftRowRight(int r) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void shiftColDown(int c) {	
		int prior;
		for(int i = 0; i < 15; i++){
			prior = this.myBoard[i][c].getColorValue();
			if(this.myBoard[i + 1][c].getColorValue() == 0)
				this.myBoard[i + 1][c].setColorValue(prior);
		}
	}
	
	public Bubble getBubble(int r, int c){
		return this.myBoard[r][c];
	}
}
