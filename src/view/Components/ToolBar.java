package view.Components;

import java.awt.SystemColor;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.border.Border;

import view.Listeners.ToolBarHandler;
import view.TextEditor.TextEditor;

public class ToolBar extends JToolBar{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1271942709108424356L;

	public static JButton btnOpenFile = new JButton();
	public static JButton btnSaveFile = new JButton();
	public static JButton btnStart = new JButton();
	public static JButton btnStop = new JButton();
	public static JButton btnResume = new JButton();
	public static JButton btnStepOver = new JButton();
	
	public ToolBar(int width, int height, TabPane tabbedPane, TextEditor textEditor, LogTab[] log, Register register, RAM ram){
		ToolBarHandler handler = new ToolBarHandler(tabbedPane, textEditor, log, register, ram);
		final int TOOLBAR_BUTTON_MARGIN = 5;
		Border emptyBorder = BorderFactory.createEmptyBorder(TOOLBAR_BUTTON_MARGIN,
											 				 TOOLBAR_BUTTON_MARGIN,
											 				 TOOLBAR_BUTTON_MARGIN,
											 				 TOOLBAR_BUTTON_MARGIN);
		setFloatable(false);
		setBackground(SystemColor.window);
		setBounds(0, 0, width-16, height);
		
		handler.initButtons(btnSaveFile, btnStart, btnStop, btnResume, btnStepOver);
		
		btnStop.setEnabled(false);
		btnResume.setEnabled(false);
		btnStepOver.setEnabled(false);
		
		btnOpenFile.setOpaque(true);
		btnSaveFile.setOpaque(true);
		btnStart.setOpaque(true);
		btnStop.setOpaque(true);
		btnResume.setOpaque(true);
		btnStepOver.setOpaque(true);
		
		btnOpenFile.setName("OpenFile");
		btnSaveFile.setName("SaveFile");
		btnStart.setName("Start");
		btnStop.setName("Stop");
		btnResume.setName("Resume");
		btnStepOver.setName("StepOver");
		
		btnOpenFile.addActionListener(handler);
		btnSaveFile.addActionListener(handler);
		btnStart.addActionListener(handler);
		btnStop.addActionListener(handler);
		btnResume.addActionListener(handler);
		btnStepOver.addActionListener(handler);
		
		add(btnOpenFile);
		add(btnSaveFile);
		add(btnStart);
		add(btnStop);
		add(btnResume);
		add(btnStepOver);
		
		btnOpenFile.setBackground(SystemColor.window);
		btnSaveFile.setBackground(SystemColor.window);
		btnStart.setBackground(SystemColor.window);
		btnStop.setBackground(SystemColor.window);
		btnResume.setBackground(SystemColor.window);
		btnStepOver.setBackground(SystemColor.window);
		
		btnOpenFile.setToolTipText("파일 열기  ^Ctrl-F");
		btnSaveFile.setToolTipText("파일 저장  ^Ctrl-S");
		btnStart.setToolTipText("문법검사  ^Ctrl-G");
		btnStop.setToolTipText("종료&리셋  ^Ctrl-T");
		btnResume.setToolTipText("실행시작&계속   F5");
		btnStepOver.setToolTipText("한문장실행  F10");
		
		btnOpenFile.setBorder(emptyBorder); 
		btnSaveFile.setBorder(emptyBorder);
		btnStart.setBorder(emptyBorder);   
		btnStop.setBorder(emptyBorder);
		btnResume.setBorder(emptyBorder); 
		btnResume.setBorder(emptyBorder); 
		btnStepOver.setBorder(emptyBorder);
		
		btnOpenFile.setIcon(new ImageIcon(ToolBar.class.getResource("/openfile.png")));
		btnOpenFile.setDisabledIcon(new ImageIcon(ToolBar.class.getResource("/openfile-disabled.png")));
		btnSaveFile.setIcon(new ImageIcon(ToolBar.class.getResource("/savefile.png")));
		btnSaveFile.setDisabledIcon(new ImageIcon(ToolBar.class.getResource("/savefile -disabled.png")));
		btnStart.setIcon(new ImageIcon(ToolBar.class.getResource("/check.png")));
		btnStart.setDisabledIcon(new ImageIcon(ToolBar.class.getResource("/check-disabled.png")));
		btnStop.setIcon(new ImageIcon(ToolBar.class.getResource("/term.png")));
		btnStop.setDisabledIcon(new ImageIcon(ToolBar.class.getResource("/term-disabled.png")));
		btnResume.setIcon(new ImageIcon(ToolBar.class.getResource("/resume.png")));
		btnResume.setDisabledIcon(new ImageIcon(ToolBar.class.getResource("/resume-disabled.png")));
		btnStepOver.setIcon(new ImageIcon(ToolBar.class.getResource("/stepnext.png")));
		btnStepOver.setDisabledIcon(new ImageIcon(ToolBar.class.getResource("/stepnext-disabled.png")));
	}
}
