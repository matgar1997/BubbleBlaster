import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Gui implements Shifting{
	
	private JFrame myGameFrame;
	private JPanel myGamePanel;
	private JFrame myDetailFrame;
	private JPanel myDetailPanel;
	
	private JLabel myScoreLabel;
	private JLabel myScoreNeededLabel;
	
	private JButton [][] myButtons = new JButton[15][15]; // 15,15
	
	private String [] myColorOptions = {"WHITE", "BLUE", "GREEN", "RED", "YELLOW"};
	
	private Board myBoard;
	
	public Gui(){
		this.instantiateAll();
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		this.myGameFrame.setSize(d.width / 2, d.height / 2);
		this.myGameFrame.setVisible(true);
		this.myGameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.myGameFrame.setLocationRelativeTo(null);
		
		for(int r = 0; r < 15; r++)
			for(int c = 0; c < 15; c++)
				this.myGamePanel.add(this.myButtons[r][c]);
		this.myGameFrame.add(this.myGamePanel);
	}
	
	public void sweepClear(){
		//turns all 0 values to no color
		for(int r = 0; r < 15; r++)
			for(int c = 0; c < 15; c++)
				if(this.myBoard.getBubble(r, c).getColorValue() == 0)
					this.myButtons[r][c].setBackground(Color.WHITE);
	}

	@Override
	public void shiftRowRight(int r) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void shiftColDown(int c) {
		// TODO Auto-generated method stub
		
	}
	
	public void instantiateAll(){ //This should only be called at start in constructor
		this.myGameFrame = new JFrame("Bubble Breaker");
		this.myGamePanel = new JPanel(new GridLayout(15,15));
		this.myDetailFrame = new JFrame();
		this.myDetailPanel = new JPanel();
		
		this.myScoreLabel = new JLabel();
		this.myScoreNeededLabel = new JLabel();
		
		for(int r = 0; r < 15; r++)
			for(int c = 0; c < 15; c++)
				this.myButtons[r][c] = new JButton(); 
	}
}
