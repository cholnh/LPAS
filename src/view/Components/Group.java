package view.Components;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Group extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7746297149908084846L;
	
	public void setLocation(int x, int y, int width, int height)
	{
		this.setLocation(x, y, width, height);
	}

	public Group(String title, int x, int y) {
		setBorder(new LineBorder(Color.DARK_GRAY, 1, true));
		setBounds(10, 10, x, y);
		setBackground(Color.WHITE);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(1, 1, super.getSize().width-2, 36);
		add(panel);
		panel.setLayout(null);
		
		JLabel lbl_title = new JLabel("<dynamic>");
		lbl_title.setBounds(10, 12, 141, 14);
		lbl_title.setFont(new Font("나눔고딕", Font.BOLD, 12));
		lbl_title.setText(title);
		panel.add(lbl_title);
		super.setVisible(true);
		// setContentPane(cp);
		// setTitle("Text Editor Demo");
		// setDefaultCloseOperation(EXIT_ON_CLOSE);
		// pack();
		// setLocationRelativeTo(null);
	}
	
}