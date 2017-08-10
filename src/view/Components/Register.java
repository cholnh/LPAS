package view.Components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class Register extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1092381154001513535L;
	
	JLabel reg_int[];
	JLabel reg_float[];
	Color cyan = new Color(211, 242, 252);
	Color green = new Color(189, 255, 189);
	
	public void setIntRegText(int idx, String text)
	{
		if (!reg_int[idx].getText().equals(text))
			reg_int[idx].setForeground(Color.red);
		else reg_int[idx].setForeground(Color.black);
		reg_int[idx].setText(text);
	}
	
	public void setFloatRegText(int idx, String text)
	{
		if (!reg_float[idx].getText().equals(text))
			reg_float[idx].setForeground(Color.red);
		else reg_float[idx].setForeground(Color.black);
		reg_float[idx].setText(text);
	}
	
	public void resetRegister()
	{
		for(int i = 0; i < reg_int.length; i++)
		{
			reg_int[i].setText("0");
		}
		for(int i = 0; i < reg_float.length; i++)
		{
			reg_float[i].setText("0.0");
		}
	}
	
	public void selectRegisterIndex(String word)
	{
		clearSelectedRegisterIndex();
		char register_type = word.charAt(0);
		int register_idx = (int) word.charAt(1) - 49;
		if(register_type == 'r')
		{
			reg_int[register_idx].setForeground(Color.red);
		}
		else if(register_type == 'f')
		{
			reg_float[register_idx].setForeground(Color.red);
		}
		
	}
	
	public void clearSelectedRegisterIndex()
	{
		for(int i = 0; i < reg_int.length; i++)
		{
			reg_int[i].setForeground(Color.black);
		}
		for(int i = 0; i < reg_float.length; i++)
		{
			reg_float[i].setForeground(Color.black);
		}
	}

	public Register()
	{
		setBackground(Color.WHITE);
		setLayout(null);
		
		JPanel regLblPanel = new JPanel();
		regLblPanel.setBounds(0, 0, 400, 25);
		add(regLblPanel);
		regLblPanel.setLayout(new GridLayout(1, 8, 0, 0));
		
		reg_int = new JLabel[8];
		reg_float= new JLabel[4];
		
		JLabel lbl_reg1 = new JLabel("r1");
		lbl_reg1.setBackground(Color.WHITE);
		lbl_reg1.setFont(new Font("굴림", Font.BOLD, 12));
		lbl_reg1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_reg1.setOpaque(true);
		regLblPanel.add(lbl_reg1);
		
		JLabel lbl_reg2 = new JLabel("r2");
		lbl_reg2.setBackground(Color.WHITE);
		lbl_reg2.setFont(new Font("굴림", Font.BOLD, 12));
		lbl_reg2.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_reg2.setOpaque(true);
		regLblPanel.add(lbl_reg2);
		
		JLabel lbl_reg3 = new JLabel("r3");
		lbl_reg3.setBackground(Color.WHITE);
		lbl_reg3.setFont(new Font("굴림", Font.BOLD, 12));
		lbl_reg3.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_reg3.setOpaque(true);
		regLblPanel.add(lbl_reg3);
		
		JLabel lbl_reg4 = new JLabel("r4");
		lbl_reg4.setBackground(Color.WHITE);
		lbl_reg4.setFont(new Font("굴림", Font.BOLD, 12));
		lbl_reg4.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_reg4.setOpaque(true);
		regLblPanel.add(lbl_reg4);
		
		JLabel lbl_reg5 = new JLabel("r5");
		lbl_reg5.setBackground(Color.WHITE);
		lbl_reg5.setFont(new Font("굴림", Font.BOLD, 12));
		lbl_reg5.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_reg5.setOpaque(true);
		regLblPanel.add(lbl_reg5);
		
		JLabel lbl_reg6 = new JLabel("r6");
		lbl_reg6.setBackground(Color.WHITE);
		lbl_reg6.setFont(new Font("굴림", Font.BOLD, 12));
		lbl_reg6.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_reg6.setOpaque(true);
		regLblPanel.add(lbl_reg6);
		
		JLabel lbl_reg7 = new JLabel("r7");
		lbl_reg7.setBackground(Color.WHITE);
		lbl_reg7.setFont(new Font("굴림", Font.BOLD, 12));
		lbl_reg7.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_reg7.setOpaque(true);
		regLblPanel.add(lbl_reg7);
		
		JLabel lbl_reg8 = new JLabel("r8");
		lbl_reg8.setBackground(Color.WHITE);
		lbl_reg8.setFont(new Font("굴림", Font.BOLD, 12));
		lbl_reg8.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_reg8.setOpaque(true);
		regLblPanel.add(lbl_reg8);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 26, 400, 2);
		separator.setPreferredSize(new Dimension(100, 20));
		separator.setMinimumSize(new Dimension(100, 20));
		add(separator);
		
		JPanel regValuePanel = new JPanel();
		regValuePanel.setBounds(0, 28, 400, 25);
		add(regValuePanel);
		regValuePanel.setLayout(new GridLayout(1, 8, 0, 0));
		Border border = new LineBorder(Color.white, 1);

		reg_int[0] = new JLabel("0");
		reg_int[0].setOpaque(true);
		reg_int[0].setBorder(border);
		reg_int[0].setHorizontalAlignment(SwingConstants.CENTER);
		reg_int[0].setFont(new Font("굴림", Font.BOLD, 12));
		reg_int[0].setBackground(cyan);
		regValuePanel.add(reg_int[0]);
		
		reg_int[1] = new JLabel("0");
		reg_int[1].setOpaque(true);
		reg_int[1].setBorder(border);
		reg_int[1].setHorizontalAlignment(SwingConstants.CENTER);
		reg_int[1].setFont(new Font("굴림", Font.BOLD, 12));
		reg_int[1].setBackground(cyan);
		regValuePanel.add(reg_int[1]);
		
		reg_int[2] = new JLabel("0");
		reg_int[2].setOpaque(true);
		reg_int[2].setBorder(border);
		reg_int[2].setHorizontalAlignment(SwingConstants.CENTER);
		reg_int[2].setFont(new Font("굴림", Font.BOLD, 12));
		reg_int[2].setBackground(cyan);
		regValuePanel.add(reg_int[2]);
		
		reg_int[3] = new JLabel("0");
		reg_int[3].setOpaque(true);
		reg_int[3].setBorder(border);
		reg_int[3].setHorizontalAlignment(SwingConstants.CENTER);
		reg_int[3].setFont(new Font("굴림", Font.BOLD, 12));
		reg_int[3].setBackground(cyan);
		regValuePanel.add(reg_int[3]);
		
		reg_int[4] = new JLabel("0");
		reg_int[4].setOpaque(true);
		reg_int[4].setBorder(border);
		reg_int[4].setHorizontalAlignment(SwingConstants.CENTER);
		reg_int[4].setFont(new Font("굴림", Font.BOLD, 12));
		reg_int[4].setBackground(cyan);
		regValuePanel.add(reg_int[4]);
		
		reg_int[5] = new JLabel("0");
		reg_int[5].setOpaque(true);
		reg_int[5].setBorder(border);
		reg_int[5].setHorizontalAlignment(SwingConstants.CENTER);
		reg_int[5].setFont(new Font("굴림", Font.BOLD, 12));
		reg_int[5].setBackground(cyan);
		regValuePanel.add(reg_int[5]);
		
		reg_int[6] = new JLabel("0");
		reg_int[6].setOpaque(true);
		reg_int[6].setBorder(border);
		reg_int[6].setHorizontalAlignment(SwingConstants.CENTER);
		reg_int[6].setFont(new Font("굴림", Font.BOLD, 12));
		reg_int[6].setBackground(cyan);
		regValuePanel.add(reg_int[6]);
		
		reg_int[7] = new JLabel("0");
		reg_int[7].setOpaque(true);
		reg_int[7].setBorder(border);
		reg_int[7].setHorizontalAlignment(SwingConstants.CENTER);
		reg_int[7].setFont(new Font("굴림", Font.BOLD, 12));
		reg_int[7].setBackground(cyan);
		regValuePanel.add(reg_int[7]);
		
		JPanel regLblFloatPanel = new JPanel();
		regLblFloatPanel.setBounds(0, 54, 400, 25);
		add(regLblFloatPanel);
		regLblFloatPanel.setLayout(new GridLayout(1, 8, 0, 0));
		
		JLabel lbl_float1 = new JLabel("f1");
		lbl_float1.setOpaque(true);
		lbl_float1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_float1.setFont(new Font("굴림", Font.BOLD, 12));
		lbl_float1.setBackground(Color.WHITE);
		regLblFloatPanel.add(lbl_float1);
		
		JLabel lbl_float2 = new JLabel("f2");
		lbl_float2.setOpaque(true);
		lbl_float2.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_float2.setFont(new Font("굴림", Font.BOLD, 12));
		lbl_float2.setBackground(Color.WHITE);
		regLblFloatPanel.add(lbl_float2);
		
		JLabel lbl_float3 = new JLabel("f3");
		lbl_float3.setOpaque(true);
		lbl_float3.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_float3.setFont(new Font("굴림", Font.BOLD, 12));
		lbl_float3.setBackground(Color.WHITE);
		regLblFloatPanel.add(lbl_float3);
		
		JLabel lbl_float4 = new JLabel("f4");
		lbl_float4.setOpaque(true);
		lbl_float4.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_float4.setFont(new Font("굴림", Font.BOLD, 12));
		lbl_float4.setBackground(Color.WHITE);
		regLblFloatPanel.add(lbl_float4);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setPreferredSize(new Dimension(100, 20));
		separator_1.setMinimumSize(new Dimension(100, 20));
		separator_1.setBounds(0, 80, 400, 2);
		add(separator_1);
		
		JPanel floatValuePanel = new JPanel();
		floatValuePanel.setBounds(0, 82, 400, 25);
		add(floatValuePanel);
		floatValuePanel.setLayout(new GridLayout(1, 8, 0, 0));
		
		reg_float[0] = new JLabel("0.0");
		reg_float[0].setOpaque(true);
		reg_float[0].setBorder(border);
		reg_float[0].setHorizontalAlignment(SwingConstants.CENTER);
		reg_float[0].setFont(new Font("굴림", Font.BOLD, 12));
		reg_float[0].setBackground(green);
		floatValuePanel.add(reg_float[0]);
		
		reg_float[1] = new JLabel("0.0");
		reg_float[1].setOpaque(true);
		reg_float[1].setBorder(border);
		reg_float[1].setHorizontalAlignment(SwingConstants.CENTER);
		reg_float[1].setFont(new Font("굴림", Font.BOLD, 12));
		reg_float[1].setBackground(green);
		floatValuePanel.add(reg_float[1]);
		
		reg_float[2] = new JLabel("0.0");
		reg_float[2].setOpaque(true);
		reg_float[2].setBorder(border);
		reg_float[2].setHorizontalAlignment(SwingConstants.CENTER);
		reg_float[2].setFont(new Font("굴림", Font.BOLD, 12));
		reg_float[2].setBackground(green);
		floatValuePanel.add(reg_float[2]);
		
		reg_float[3] = new JLabel("0.0");
		reg_float[3].setOpaque(true);
		reg_float[3].setBorder(border);
		reg_float[3].setHorizontalAlignment(SwingConstants.CENTER);
		reg_float[3].setFont(new Font("굴림", Font.BOLD, 12));
		reg_float[3].setBackground(green);
		floatValuePanel.add(reg_float[3]);		
	}
}
