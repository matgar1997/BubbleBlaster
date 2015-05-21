public class Bubble {

	private int myColor;
	private int myRow, myCol;
	private boolean myStatus;

	public Bubble(int color, int row, int col) {
		this.myColor = color;
		this.myRow = row;
		this.myCol = col;
		this.myStatus = false;
	}

	public int getColor() {
		return this.myColor;
	}

	public int getRow() {
		return this.myRow;
	}

	public int getCol() {
		return this.myCol;
	}

	public boolean getStatus() {
		return this.myStatus;
	}

	public void setColor(int color) {
		this.myColor = color;
	}

	public void setRow(int row) {
		this.myRow = row;
	}

	public void setCol(int col) {
		this.myCol = col;
	}

	public void setStatus(boolean status) {
		this.myStatus = status;
	}

	public String toString() {
		return "("+this.myRow+", "+this.myCol+")("+this.myColor+")";
	}
}
