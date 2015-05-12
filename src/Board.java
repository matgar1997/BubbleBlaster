
public class Board implements Shifting{
	
	private Bubble [][] myBoard;

	@Override
	public void shiftRowRight(int r) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void shiftColDown(int c) {
		// TODO Auto-generated method stub
		
	}
	
	public Bubble getBubble(int r, int c){
		return this.myBoard[r][c];
	}
}
