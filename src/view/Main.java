package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;
import javax.swing.text.BadLocationException;

import view.Components.Group;
import view.Components.LogTab;
import view.Components.Menu;
import view.Components.RAM;
import view.Components.Register;
import view.Components.TabPane;
import view.Components.ToolBar;
import view.TextEditor.TextEditor;

public class Main {

	private JFrame frmLpas;
	private Register register;
	private RAM ram;
	private TabPane tabbedPane;
	private JTabbedPane logTabPane;
	private LogTab Logs[];
	
	private Group gleft;
	private Group gright_reg;
	//private Group gright_output;
	
	private ToolBar toolbar;
	private Menu menu;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frmLpas.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws BadLocationException 
	 */
	private void initialize(){
		frmLpas = new JFrame();
		frmLpas.setTitle("LPA Simulator @ KGU");
		frmLpas.setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/logo.png")));
		frmLpas.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				//TextEditor.setBounds(0, 0, frame.getSize().width - 50, frame.getSize().height - 50);
			}
		});
		frmLpas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLpas.setBounds(0, 0, 1280, 768);
		frmLpas.getContentPane().setBackground(Color.WHITE);
		frmLpas.getContentPane().setLayout(null);
		
		// 왼쪽 텍스트 에디터 부분
		gleft = new Group("Code", 800, 700);
		gleft.setBounds(10, 40, 800, 442);
		frmLpas.getContentPane().add(gleft);
		gleft.setLayout(null);
		
		// 아래 로그부분
		//gright_output = new Group("Output", 1240, 206);
		//gright_output.setSize(1240, 206);
		//gright_output.setLocation(10, 492);
		//frame.getContentPane().add(gright_output);
		
		logTabPane = new JTabbedPane();
		logTabPane.setBounds(10, 492, 1240, 206);
		frmLpas.getContentPane().add(logTabPane);
		
		Logs = new LogTab[3];
		Logs[0] = new LogTab(logTabPane); // 그냥 로그
		logTabPane.addTab("Logs", Logs[0].getScrollPane());
		Logs[1] = new LogTab(logTabPane); // 에러 로그
		logTabPane.addTab("Error", Logs[1].getScrollPane());
		Logs[2] = new LogTab(logTabPane); // 아웃풋 로그
		logTabPane.addTab("OutPut", Logs[2].getScrollPane());
		
		// 에디터 부분
		tabbedPane = new TabPane(Logs);
		tabbedPane.setBounds(2, 40, gleft.getSize().width - 4, gleft.getSize().height - 42);
		gleft.add(tabbedPane);
		
		gright_reg = new Group("Storage View", 430, 100);
		gright_reg.setSize(430, 442);
		gright_reg.setLocation(822, 40);
		frmLpas.getContentPane().add(gright_reg);
		
		register = new Register();
		register.setBounds(12, 40, 406, 112);
		gright_reg.add(register);
		
		ram = new RAM(tabbedPane);
		ram.setBounds(12, 156, 406, 280);
		gright_reg.add(ram);
		
		tabbedPane.init(register, ram);
		TextEditor TextEditor = new TextEditor("New", tabbedPane, register, ram);
		TextEditor.setText("program\n\t...\nend");
		TextEditor.setBounds(2, 40, gleft.getSize().width - 4, gleft.getSize().height - 42);
		tabbedPane.addTab("New", TextEditor);
		TextEditor.setLayout(new BoxLayout(TextEditor, BoxLayout.X_AXIS));
		
		menu = new Menu(tabbedPane, TextEditor, Logs, register, ram);
		frmLpas.setJMenuBar(menu);
		menu.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F10"), "none");
		toolbar = new ToolBar(frmLpas.getWidth(), 30, tabbedPane, TextEditor, Logs, register, ram);
		frmLpas.getContentPane().add(toolbar);
		
		frmLpas.addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				TextEditor.requestFocusToTextEditor();
			}
		});
		Control.getInstance().init(frmLpas, register, ram);
	}
}
