
public class Bubble {
	
	private int myColor;
	private int myRow, myCol;
	private boolean myCanBePressed;
	
	public Bubble(int color, int row, int col){
		this.myColor = color;
		this.myRow = row;
		this.myCol = col;
		this.myCanBePressed = false;
	}
	
	public void flipStatus(){
		this.myCanBePressed = !this.myCanBePressed;
	}
	
	public int getColorValue(){
		return this.myColor;
	}
	
	public int getRow(){
		return this.myRow;
	}
	
	public int getCol(){
		return this.myCol;
	}
	
	public String toString(){
		return "my Color: "+this.myColor+" my Status: "+this.myCanBePressed+" r: "+this.myRow+" c: "+this.myCol;
	}
}
