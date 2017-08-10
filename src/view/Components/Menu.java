package view.Components;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import view.Listeners.MenuHandler;
import view.TextEditor.TextEditor;

public class Menu extends JMenuBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4514368440939505436L;
	JMenu mnFile;
	JMenu mnEdit;
	JMenu mnRun;
	JMenu mnWindow;
	JMenu mnHelp;
	
	MenuHandler handler;

	public Menu(TabPane tabbedPane, TextEditor textEditor, LogTab[] log, Register register, RAM ram) {
		// File
		mnFile = new JMenu("File");
		add(mnFile);
		
		handler = new MenuHandler(tabbedPane, textEditor, log, register, ram);

		JMenuItem mntmNew = new JMenuItem("New");
		mntmNew.addActionListener(handler);
		mnFile.add(mntmNew);
		
		JMenuItem mntmOpen = new JMenuItem("Open");
		mntmOpen.addActionListener(handler);
		mnFile.add(mntmOpen);
		mnFile.addSeparator();
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.addActionListener(handler);
		mnFile.add(mntmSave);
		
		JMenuItem mntmSaveAs = new JMenuItem("Save As");
		mntmSaveAs.addActionListener(handler);
		mnFile.add(mntmSaveAs);
		mnFile.addSeparator();
		
		JMenuItem mntmLogSave = new JMenuItem("Log Save");
		mntmLogSave.addActionListener(handler);
		mnFile.add(mntmLogSave);
		
//		JMenuItem mntmLogSaveAs = new JMenuItem("Log Save As");
//		mntmLogSaveAs.addActionListener(handler);
//		mnFile.add(mntmLogSaveAs);
//		mnFile.addSeparator();
		
		JMenuItem mntmCloseAll = new JMenuItem("Close All");
		mntmCloseAll.addActionListener(handler);
		mnFile.add(mntmCloseAll);

	}
}
