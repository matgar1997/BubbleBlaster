import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Gui implements ActionListener{
	
	private JFrame myGameFrame;
	private JPanel myGamePanel;
	private JFrame myDetailFrame;
	private JPanel myDetailPanel;
	
	private JLabel myScoreLabel;
	private JLabel myScoreNeededLabel;
	
	private JButton [][] myButtons = new JButton[15][15]; // 15,15
	
	private Board myBoard;
	private GameStats myGameStats;
	
	public Gui(){
		//TODO work on frame placement later
		this.instantiateAll();
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		this.myGameFrame.setSize(d.width / 2, d.height / 2);
		this.myDetailFrame.setSize(d.width / 4, d.height / 4);
		this.myDetailFrame.setVisible(true);
		this.myGameFrame.setVisible(true);
		this.myDetailFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.myGameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.myGameFrame.setLocationRelativeTo(null);
		this.myDetailFrame.setLocation((d.width / 2) + 200, (d.height / 2) + 50);
		
		for(int r = 0; r < 15; r++)
			for(int c = 0; c < 15; c++)
				this.myGamePanel.add(this.myButtons[r][c]);
		this.myGameFrame.add(this.myGamePanel);
		this.myGameStats = new GameStats();
		this.myBoard = new Board(this.myGameStats.getLevel());
		
		
		paintBoard();
	}
	
	public void newLevel(){
		this.myGameStats.nextLevel();
		this.myBoard = new Board(this.myGameStats.getLevel());
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
			{
				this.myButtons[r][c] = new JButton(); 
				this.myButtons[r][c].addActionListener(this);
			}
	}
	
	public void paintBoard(){
		for(int r = 0; r < 15; r++)
			for(int c = 0; c < 15; c++){
				if(this.myBoard.getBubble(r, c).getColorValue() == 0){
					this.myButtons[r][c].setBackground(Color.WHITE);
					this.myButtons[r][c].setEnabled(false);
				}
				if(this.myBoard.getBubble(r, c).getColorValue() == 1)
					this.myButtons[r][c].setBackground(Color.BLUE);
				else if(this.myBoard.getBubble(r, c).getColorValue() == 2)
					this.myButtons[r][c].setBackground(Color.GREEN);
				else if(this.myBoard.getBubble(r, c).getColorValue() == 3)
					this.myButtons[r][c].setBackground(Color.YELLOW);
				else if(this.myBoard.getBubble(r, c).getColorValue() == 4)
					this.myButtons[r][c].setBackground(Color.RED);
			}
				
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for(int r = 0; r < 15; r++)
			for(int c = 0; c < 15; c++)
				if(e.getSource() == this.myButtons[r][c]){
					//System.out.println(this.myBoard.getBubble(r, c));
					this.myBoard.setSurroundingSimilarToTrue(r, c, this.myBoard.getBubble(r, c).getColorValue());
					//paintBoard();
					this.myBoard.shiftEveryThing();
					paintBoard();
					if(this.myBoard.checkIsLevelOver() == true){
						this.newLevel();
						resetBoard();
					}
				}
	}
	
	public void resetBoard(){
		for(int r = 0; r < 15; r++)
			for(int c = 0; c < 15; c++){
				this.myButtons[r][c].setEnabled(true);
				this.myBoard.getBubble(r, c).setStatusFalse();		
			}
		paintBoard();
		
	}
}
