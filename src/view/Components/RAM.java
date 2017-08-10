package view.Components;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class RAM extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7111197438354586748L;
	private JPanel boxes[];
	private JLabel memorys_name[];
	private JLabel memorys[];
	private TabPane tab;
	int row = 0, col = 0;
	Color cyan = new Color(211, 242, 252);
	Color green = new Color(189, 255, 189);

	class ClickListener extends MouseAdapter {
		@Override
		public void mouseExited(MouseEvent e) {
			tab.getCurrentEditor().clearLineColor();
		}

		@Override
		public void mouseEntered(MouseEvent me) {
			if (me.getSource() instanceof JLabel) {
				
				JLabel label = (JLabel) me.getSource();
				String src = label.getText();
				if (src.contains("int") || src.contains("float")) {
					String varId = src.split(" ")[1];
					if (varId.contains("["))
						varId = varId.substring(0, varId.indexOf('['));
					tab.getCurrentEditor().setOnRamMouserOver(varId);
				}
			}
		}
	}
	
	public void selectRamIndex(String word)
	{
		clearSelectedRamIndex();
		for(int i = 0; i < memorys_name.length; i++)
		{
			if(memorys_name[i].getText().contains(word))
			{
				boxes[i].setBorder(new LineBorder(Color.RED, 3, true));
			}
		}
	}
	
	public void clearSelectedRamIndex()
	{
		for(int i = 0; i < memorys_name.length; i++)
		{
			boxes[i].setBorder(new LineBorder(Color.DARK_GRAY, 1, true));
		}
	}

	public void setRamText(int idx, String text, String name) {
		if (name.contains("int")) {
			memorys_name[idx].setBackground(cyan);
		} else if (name.contains("float")) {
			memorys_name[idx].setBackground(green);
		}
		boxes[idx].setVisible(true);

		memorys_name[idx].setText(name);
		memorys_name[idx].setVisible(true);

		if (!memorys[idx].getText().equals(text))
			memorys[idx].setForeground(Color.red);
		else memorys[idx].setForeground(Color.black);
		memorys[idx].setText(text);
		memorys[idx].setVisible(true);
	}

	public void resetRAM() {
		for (int i = 0; i < memorys.length; i++) {
			boxes[i].setVisible(false);
			boxes[i].setBackground(Color.WHITE);

			memorys_name[i].setText("");
			memorys_name[i].setVisible(false);

			memorys[i].setText("0");
			memorys[i].setVisible(false);
		}
	}

	public RAM(TabPane tab) {
		this.tab = tab;
		setBackground(Color.WHITE);
		setLayout(null);
		setOpaque(true);

		JLabel lblNewLabel = new JLabel("Memory");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setBounds(0, 0, 400, 15);
		add(lblNewLabel);

		JPanel regLblPanel = new JPanel();
		regLblPanel.setBackground(Color.WHITE);
		regLblPanel.setBounds(0, 15, 400, 267);
		add(regLblPanel);
		regLblPanel.setLayout(new GridLayout(8, 4, 10, 10));
		regLblPanel.setOpaque(true);

		boxes = new JPanel[32];
		memorys_name = new JLabel[32];
		memorys = new JLabel[32];

		final ClickListener clickListener = new ClickListener();

		for (int i = 0; i < memorys.length; i++) {
			boxes[i] = new JPanel();
			boxes[i].setBorder(new LineBorder(Color.DARK_GRAY, 1, true));
			boxes[i].setBackground(Color.WHITE);
			boxes[i].setLayout(new GridLayout(2, 1, 0, 0));
			boxes[i].setOpaque(true);
			boxes[i].setVisible(true);

			memorys_name[i] = new JLabel("" + i, SwingConstants.LEFT);
			memorys_name[i].setBackground(Color.WHITE);
			memorys_name[i].setFont(new Font("굴림", Font.BOLD, 10));
			memorys_name[i].setHorizontalAlignment(SwingConstants.LEFT);
			memorys_name[i].setOpaque(true);
			memorys_name[i].setVisible(false);

			memorys[i] = new JLabel("0", SwingConstants.RIGHT);
			memorys[i].setBackground(Color.WHITE);
			memorys[i].setFont(new Font("굴림", Font.BOLD, 12));
			memorys[i].setHorizontalAlignment(SwingConstants.RIGHT);
			memorys[i].setOpaque(true);
			memorys[i].setVisible(false);

			boxes[i].addMouseListener(clickListener);
			memorys_name[i].addMouseListener(clickListener);
			memorys[i].addMouseListener(clickListener);

			boxes[i].add(memorys_name[i]);
			boxes[i].add(memorys[i]);
			regLblPanel.add(boxes[i]);
		}
	}
}
