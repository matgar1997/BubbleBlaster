public class Bubble {

	private int myColor;
	private int myRow, myCol;
	private boolean myCanBePressed;

	public Bubble(int color, int row, int col) {
		this.myColor = color;
		this.myRow = row;
		this.myCol = col;
		this.myCanBePressed = false;
	}

	public void setStatusTrue() {
		this.myCanBePressed = true;
	}

	public void setStatusFalse() {
		this.myCanBePressed = false;
	}

	public int getColorValue() {
		return this.myColor;
	}

	public void setColorValue(int i) {
		this.myColor = i;
	}

	public int getRow() {
		return this.myRow;
	}

	public boolean getStatus() {
		return this.myCanBePressed;
	}

	public int getCol() {
		return this.myCol;
	}

	public String toString() {
		return "my Color: " + this.myColor + " my Status: "
				+ this.myCanBePressed + " r: " + this.myRow + " c: "
				+ this.myCol;
	}
}
