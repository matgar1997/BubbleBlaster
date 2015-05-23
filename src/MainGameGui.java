import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MainGameGui implements ActionListener {

	private JFrame myGameFrame;
	private JPanel myLayoutPanel;
	private JPanel myDataPanel;
	private JPanel myGamePanel;
	private JLabel myScoreLabel = new JLabel();
	private JLabel myLevelLabel = new JLabel();
	private JLabel myScoreNeededLabel = new JLabel();
	private JLabel myClickCounterLabel = new JLabel();

	private JButton[][] myButtons = new JButton[15][15]; // 15,15

	private Board myBoard;
	
	private int clicks = 0;
	
	private boolean unlocked = false;

	public MainGameGui(Board board) {
		this.myBoard = board;
		
		this.myGameFrame = new JFrame("Bubble Breaker");
		this.myGamePanel = new JPanel(new GridLayout(15, 15));
		this.myDataPanel = new JPanel(new GridLayout(1,4));
		this.myLayoutPanel = new JPanel(new BorderLayout());
		for (int r = 0; r < 15; r++)
			for (int c = 0; c < 15; c++) {
				this.myButtons[r][c] = new JButton();
				this.myButtons[r][c].addActionListener(this);
			}
		this.myDataPanel.add(this.myLevelLabel);
		this.myDataPanel.add(this.myScoreLabel);
		this.myDataPanel.add(this.myScoreNeededLabel);
		this.myDataPanel.add(this.myClickCounterLabel);

		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		this.myGameFrame.setSize((d.width / 2) + d.width / 5, (d.height / 2) + d.height / 5);
		this.myGameFrame.setVisible(true);
		this.myGameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.myGameFrame.setLocationRelativeTo(null);

		for (int r = 0; r < 15; r++)
			for (int c = 0; c < 15; c++)
				this.myGamePanel.add(this.myButtons[r][c]);
		
		this.myLayoutPanel.add(this.myGamePanel, BorderLayout.CENTER);
		this.myLayoutPanel.add(this.myDataPanel, BorderLayout.SOUTH);
		this.myGameFrame.add(this.myLayoutPanel);
		this.paintBoardAndData();
	}

	public void paintBoardAndData() {
		for (int r = 0; r < 15; r++)
			for (int c = 0; c < 15; c++) {
				if (this.myBoard.getBubble(r, c).getColor() == 0){
					this.myButtons[r][c].setBackground(Color.WHITE);
					this.myButtons[r][c].setEnabled(false);
				}

				else if (this.myBoard.getBubble(r, c).getColor() == 1)
					this.myButtons[r][c].setBackground(Color.BLUE);

				else if (this.myBoard.getBubble(r, c).getColor() == 2)
					this.myButtons[r][c].setBackground(Color.GREEN);

				else if (this.myBoard.getBubble(r, c).getColor() == 3)
					this.myButtons[r][c].setBackground(Color.YELLOW);

				else if (this.myBoard.getBubble(r, c).getColor() == 4)
					this.myButtons[r][c].setBackground(Color.RED);

				else if (this.myBoard.getBubble(r, c).getColor() == 5)
					this.myButtons[r][c].setBackground(Color.ORANGE);
				
				else if(this.myBoard.getBubble(r, c).getColor() == 6)
					this.myButtons[r][c].setBackground(Color.CYAN);
				
				else if(this.myBoard.getBubble(r, c).getColor() == 7)
					this.myButtons[r][c].setBackground(Color.PINK);
				
				else if(this.myBoard.getBubble(r, c).getColor() == 8)
					this.myButtons[r][c].setBackground(Color.MAGENTA);
				
				else{
					this.myButtons[r][c].setBackground(Color.GRAY);
				}
				/**
				if (this.myBoard.getBubble(r, c).getStatus() == false)
					this.myButtons[r][c].setEnabled(true);
				if (this.myBoard.getBubble(r, c).getStatus() == true)
					this.myButtons[r][c].setEnabled(false);
					*/
			}
		this.setLevelText();
		this.setScoreText();
		this.setScoreNeededText();
		this.setClickCounterText();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		clicks++;
		for (int r = 0; r < 15; r++)
			for (int c = 0; c < 15; c++)
				if (e.getSource() == this.myButtons[r][c])
					this.myBoard.recursionCall(r, c,
							this.myBoard.getBubble(r, c).getColor());
		
		//if next level was unlocked
		if (this.myBoard.isLevelOver() == true || this.myBoard.getScore() >= this.myBoard.getScoreNeeded()) {
			this.myGameFrame.dispose();
			this.myBoard.nextLevel();
			unlocked = true;
		}
		
		//if level is not over
		else if(this.myBoard.isLevelOver() == false || clicks <= 5){
			//TODO FIX THIS
			this.myBoard.shiftBoardDown();
			this.myBoard.shiftBoardRight();
			this.paintBoardAndData();
			//System.out.println(this.myBoard);
		}
		
		if(clicks == 6 && unlocked == false){
			int reply = JOptionPane.showConfirmDialog(null,
					"Would you like to play again", "You Ended on Level "+this.myBoard.getLevel(), JOptionPane.YES_NO_OPTION);
			if(reply == JOptionPane.NO_OPTION){
				this.myGameFrame.dispose();
			}
			else if(reply == JOptionPane.YES_OPTION){
				this.myGameFrame.dispose();
				this.myBoard.restart();
			}
				
		}
	}
	public void setScoreText(){
		this.myLevelLabel.setText("Current Level: " + this.myBoard.getLevel());
	}
	
	public void setLevelText(){
		this.myScoreLabel.setText("Current Score: " + this.myBoard.getScore());
	}
	
	public void setScoreNeededText(){
		this.myScoreNeededLabel.setText("Score Needed for Level "+(this.myBoard.getLevel() + 1) + ": "+this.myBoard.getScoreNeeded());
	}
	
	public void setClickCounterText(){
		this.myClickCounterLabel.setText("Moves Left: ("+this.clicks+"/6)");
	}
}
