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
import javax.swing.JPanel;

public class MainGameGui implements ActionListener {

	private JFrame myGameFrame;
	private JPanel myLayoutPanel;
	private JPanel myDataPanel;
	private JPanel myGamePanel;
	private JLabel myScoreLabel = new JLabel();
	private JLabel myLevelLabel = new JLabel();

	private JButton[][] myButtons = new JButton[15][15]; // 15,15

	private Board myBoard;

	public MainGameGui(Board board) {
		this.myBoard = board;
		
		this.myGameFrame = new JFrame("Bubble Breaker");
		this.myGamePanel = new JPanel(new GridLayout(15, 15));
		this.myDataPanel = new JPanel(new GridLayout(1,2));
		this.myLayoutPanel = new JPanel(new BorderLayout());
		for (int r = 0; r < 15; r++)
			for (int c = 0; c < 15; c++) {
				this.myButtons[r][c] = new JButton();
				this.myButtons[r][c].addActionListener(this);
			}
		this.myDataPanel.add(this.myLevelLabel);
		this.myDataPanel.add(this.myScoreLabel);

		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		this.myGameFrame.setSize(d.width / 2, d.height / 2);
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
				if (this.myBoard.getBubble(r, c).getColor() == 0)
					this.myButtons[r][c].setBackground(Color.WHITE);

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

				if (this.myBoard.getBubble(r, c).getStatus() == false)
					this.myButtons[r][c].setEnabled(true);
				if (this.myBoard.getBubble(r, c).getStatus() == true)
					this.myButtons[r][c].setEnabled(false);
			}
		this.setLevelText();
		this.setScoreText();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for (int r = 0; r < 15; r++)
			for (int c = 0; c < 15; c++)
				if (e.getSource() == this.myButtons[r][c])
					this.myBoard.recursionCall(r, c,
							this.myBoard.getBubble(r, c).getColor());
	
		if (this.myBoard.isLevelOver() == true) {
			this.myGameFrame.dispose();
			this.myBoard.nextLevel();
		}
		
		else if(this.myBoard.isLevelOver() == false){
			//TODO FIX THIS
			this.myBoard.shiftBoardDown();
			this.myBoard.shiftBoardRight();
			this.paintBoardAndData();
		}
	}
	public void setScoreText(){
		this.myLevelLabel.setText("Current Level: " + this.myBoard.getLevel());
	}
	
	public void setLevelText(){
		this.myScoreLabel.setText("Current Score: " + this.myBoard.getScore());
	}
}
