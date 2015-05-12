
public class Bubble {
	
	private int myColor;
	private boolean myCanBePressed;
	
	public Bubble(int color){
		this.myColor = color;
		this.myCanBePressed = false;
	}
	
	public void flipStatus(){
		this.myCanBePressed = !this.myCanBePressed;
	}
	
	public int getColorValue(){
		return this.myColor;
	}
	
	public String toString(){
		return "my Color: "+this.myColor+" my Status: "+this.myCanBePressed;
	}
}
