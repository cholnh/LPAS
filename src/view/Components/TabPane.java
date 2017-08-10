package view.Components;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import view.TextEditor.TextEditor;

public class TabPane extends JTabbedPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4684669658406204863L;
	
	List <TextEditor> Editors = new ArrayList<TextEditor>();
	List <CloseButtonTab> TabInfos = new ArrayList<CloseButtonTab>();
	HashMap<String, File> tabFile = new HashMap<String, File>();
	public HashMap<String, File> getTabFile()
	{
		return this.tabFile;
	}
	//List <String> Logs = new ArrayList<String>();
	/*
	 * Todo List.
	 * Logs : Run시마다 생성되는 log들을 저장.
	 */
	
	private TextEditor CurrentEditor;
	private LogTab[] log;
	
	private Register register;
	private RAM ram;
	public List <TextEditor> getEditors(){
		return Editors;
	}
	
	public List <CloseButtonTab> getButtonTab(){
		return TabInfos;
	}
	
	public void init(Register register, RAM ram)
	{
		this.register = register;
		this.ram = ram;
	}
	
	public TabPane(LogTab[] log)
	{
		//this.register = register;
		//this.ram = ram;
		this.log = log;
		ChangeListener l = new ChangeListener() {

		    Component lastSelected = getSelectedComponent();
		    @Override
		    public void stateChanged(ChangeEvent e) {
		        if (lastSelected != getSelectedComponent()) {
		            lastSelected = getSelectedComponent();
		            try{
			            CurrentEditor = Editors.get(getSelectedIndex());
			            
						CurrentEditor.requestFocus();
						CurrentEditor.requestFocusToTextEditor();
		            }
		            catch(Exception ex)
		            {
		            	
		            }
		        }

		    }
		};
		addChangeListener(l);
	}
	public TextEditor getCurrentEditor()
	{
		return CurrentEditor;
	}
	
	public LogTab[] getLogTabs()
	{
		return this.log;
	}
	
	public void setCurrentEditor(TextEditor textEditor)
	{
		this.CurrentEditor = textEditor;
	}
	
	public void removedTab(TextEditor editor)
	{
		int idx = editor.getIndex();
		//this.getSelectedIndex()
		Editors.remove(idx);
		TabInfos.remove(idx);
		for(TextEditor edit : Editors) {
			if(idx >= edit.getIndex())
				continue;
			else
				edit.setIndex(edit.getIndex()-1);
		}
		//Logs.remove(idx);
	}

	@Override
	public void addTab(String title, Component component) {
		super.addTab(title, component);
		int count = this.getTabCount() - 1;
		CloseButtonTab bTab = new CloseButtonTab(component, title);
		setTabComponentAt(count, bTab);
		Editors.add((TextEditor) component);
		TabInfos.add((bTab));
		setSelectedIndex(Editors.size()-1);
		CurrentEditor = (TextEditor) component;
		CurrentEditor.setIndex(Editors.size()-1);
		CurrentEditor.requestFocusToTextEditor();
		CurrentEditor.setThisTitle(title);
	}
	
	public void setTitle(String title)
	{
		for(int i = 0; i < Editors.size(); i++)
		{
			if(Editors.get(i).equals(CurrentEditor))
			{
				TabInfos.get(i).setTitle(title);
				break;
			}
		}
	}
	
	public TextEditor addTextEditor(String title, Component frame)
	{
		TextEditor newEditor = new TextEditor(title, this, register, ram);
		newEditor.setBounds(2, 40, frame.getSize().width - 4, frame.getSize().height - 42);
		newEditor.setText("program\n\t...\nend");
		addTab(title, newEditor);
		newEditor.setLayout(new BoxLayout(newEditor, BoxLayout.X_AXIS));
		
		return newEditor;
	}

	/* Button */
	public class CloseButtonTab extends JPanel {
		/**
		 * 
		 */
		private JLabel jLabel;
		private static final long serialVersionUID = -4612046795463786762L;
		@SuppressWarnings("unused")
		private Component tab;

		public CloseButtonTab(final Component tab, String title) {
			this.tab = tab; // 바인딩 때문에 줄 삭제 금지.
			setOpaque(false);
			FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER, 3, 3);
			setLayout(flowLayout);
			jLabel = new JLabel(title);
			add(jLabel);
			JButton button = new JButton(new ImageIcon(TabPane.class.getResource("/closeTab.png")));
			button.setMargin(new Insets(0, 0, 0, 0));
			button.addMouseListener(new CloseListener(tab));
			button.setOpaque(false);
			button.setContentAreaFilled(false);
			button.setBorderPainted(false);
			add(button);
		}
		
		public void setTitle(String title)
		{
			jLabel.setText(title);
		}
	}

	/* ClickListener */
	public class CloseListener implements MouseListener {
		private Component tab;

		public CloseListener(Component tab) {
			this.tab = tab;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if (e.getSource() instanceof JButton) {
				JButton clickedButton = (JButton) e.getSource();
				TabPane tabbedPane = (TabPane) clickedButton.getParent().getParent().getParent();
				tabbedPane.remove(tab);
				tabbedPane.removedTab((TextEditor) tab);
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			if (e.getSource() instanceof JButton) {
				JButton clickedButton = (JButton) e.getSource();
				clickedButton.setIcon(new ImageIcon(TabPane.class.getResource("/closeTab-over.png")));
				//closeTab-over
			}
		}

		@Override
		public void mouseExited(MouseEvent e) {
			if (e.getSource() instanceof JButton) {
				JButton clickedButton = (JButton) e.getSource();
				clickedButton.setIcon(new ImageIcon(TabPane.class.getResource("/closeTab.png")));
			}
		}
	}
}
