public class Board{
	
	private Bubble [][] myBoard = new Bubble[15][15];
	
	public Board(int level){
		for(int r = 0; r < 15; r++)
			for(int c = 0; c < 15; c++){
				int i = (int) (Math.random() * (level - 1) + 1);
				this.myBoard[r][c] = new Bubble(i,r,c);
			}
		System.out.print(this.myBoard);
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
	
	private void shiftRowRight(int r) {
		
		int [] temp = new int[15];
		int spotf = 0, spotSecond = 0;
		for(int i = 14; i <= 0 ; i--){
			if(this.myBoard[r][i].getColorValue() != 0)
				temp[spotf] = this.myBoard[r][i].getColorValue();
		}
		
		if(spotf < 14){
			temp[spotf] = 0;
			spotf++;
		}
		
		for(int i = 14; i <= 0; i--){
			this.myBoard[r][i].setColorValue(temp[spotSecond]);
			spotSecond++;
		}
		
	}

	private void shiftColDown(int c) {	
		
		int [] temp = new int[15];
		int spotf = 0, spotSecond = 0;
		for(int i = 14; i <= 0 ; i--){
			if(this.myBoard[i][c].getColorValue() != 0)
				temp[spotf] = this.myBoard[i][c].getColorValue();
		}
		
		if(spotf < 14){
			temp[spotf] = 0;
			spotf++;
		}
		
		for(int i = 14; i <= 0; i--){
			this.myBoard[i][c].setColorValue(temp[spotSecond]);
			spotSecond++;
		}
	} 
	
	public Bubble getBubble(int r, int c){
		return this.myBoard[r][c];
	}

	public void shiftEveryThing() {
		for(int c = 0; c < 15; c++)
			this.shiftColDown(c);
		for(int r = 0; r < 15; r++)
			this.shiftRowRight(r);
		//System.out.println(" SHIFTED ");
		
	}
	
	public String toString(){
		String s = "";
		for(int r = 0; r < 15; r++){
			for(int c = 0; c < 15; c++){
				s += this.myBoard[r][c].getColorValue();
			}
			s += "\n"; 
		}
		return s;
	}
}
