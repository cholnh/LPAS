package view.TextEditor;

import java.awt.Color;
import java.awt.Point;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;

import org.fife.ui.rsyntaxtextarea.AbstractTokenMakerFactory;
import org.fife.ui.rsyntaxtextarea.TokenMakerFactory;
import org.fife.ui.rtextarea.Gutter;
import org.fife.ui.rtextarea.GutterIconInfo;
import org.fife.ui.rtextarea.RTextScrollPane;

import sim.Log;
import sim.Sim;
//import sim.SimEngine;
import view.Components.RAM;
import view.Components.Register;
import view.Components.TabPane;
import view.Components.ToolBar;
import view.Util.FileManager;

public class TextEditor extends JPanel {

	/**
	 * 
	 */
	
	final int STATE_CHECK = 0;
	final int STATE_READY = 1;
	final int STATE_STOP = 2;
	final int STATE_END = 3;
	
	private FileManager fm = new FileManager();
	private TabPane tabbedPane;
	private HashMap<String, File> tabFile;
	private TextEditor thisEditor;
	private String thisTitle;
	private int thisIndex = 0;
	private RSyntaxText textArea;
	private RTextScrollPane sp;
	private GutterIconInfo[] bookmarks;
	private static final long serialVersionUID = -7746297149908084846L;

	public HashSet<String> defaultRegister = new HashSet<String>();
	
	private Register register;
	private RAM ram;

	public TextEditor(String title, TabPane ancestor, Register register, RAM ram) {
		this.register = register;
		this.ram = ram;
		this.tabbedPane = ancestor;
		this.tabFile = ancestor.getTabFile();
		thisEditor = this;
		setThisTitle(title);

		this.setToolTipText("");

		textArea = new RSyntaxText(5, 10);
		AbstractTokenMakerFactory atmf = (AbstractTokenMakerFactory) TokenMakerFactory.getDefaultInstance();
		atmf.putMapping("text/PA", new TokenMaker().getClass().getName());
		textArea.setSyntaxEditingStyle("text/PA");
		// textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_NONE); //
		// AbstractTokenMaker
		textArea.setCodeFoldingEnabled(true);
		sp = new RTextScrollPane(textArea);
		Gutter gutter = sp.getGutter();
		gutter.setBookmarkIcon(new ImageIcon(TextEditor.class.getResource("/breakpoint.png")));
		gutter.setBookmarkingEnabled(true);
		gutter.getComponent(2).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					@SuppressWarnings("unused")
					int line = viewToModelLine(textArea, e.getPoint()); // 추가할때마다
																		// 라인을
																		// 알고싶다면
					// System.out.println(line);
					bookmarks = gutter.getBookmarks();
					// for(int i = 0; i < bookmarks.length; i++)
					// System.out.println("offset = " +
					// bookmarks[i].getMarkedOffset()); // 모든 브레이크포인트의 라인을
					// 알고싶다면.
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		// drag & drop
		tabbedPane.setDropTarget(new dragNdrop());
		textArea.setDropTarget(new dragNdrop());
		
		textArea.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				ancestor.setCurrentEditor(thisEditor);
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
			}
		});
		textArea.addCaretListener(new CaretListener(){

			@Override
			public void caretUpdate(CaretEvent e) {
				// TODO Auto-generated method stub
				int start = e.getMark();
				int end = e.getDot();
				if(start > end)
				{
					int tmp = end;
					end = start;
					start = tmp;
				}
				if(start != end)
				{
					String word;
					try {
						word = textArea.getText(start, end-start);
						Iterator<String> i = defaultRegister.iterator();
						boolean isRegister = false;
						while(i.hasNext())
						{
							if(i.next().equals(word)){
								isRegister = true;
								break;
							}
						}
						if(isRegister)
						{
							register.selectRegisterIndex(word);
						}
						else
						{
							boolean isNumeric = word.chars().allMatch( Character::isDigit );
							if(!isNumeric)
								ram.selectRamIndex(word);
						}
					} catch (BadLocationException e1) {
						// do nothing
					}
				}
				else
				{
					ram.clearSelectedRamIndex();
					register.clearSelectedRegisterIndex();
				}
			}
		});
		textArea.addKeyListener(new KeyAdapter(){
			@Override
			public void keyReleased(KeyEvent e) {
				// f5 = 116 f10 = 121
				switch (e.getKeyCode()) {
					case 116:
						if(ToolBar.btnResume.isEnabled())
							doResume();
						break;
					case 121:
						if(ToolBar.btnStepOver.isEnabled())
							doStepOver();
						break;
					default:
						break;
				}
				if (e.isControlDown()) {
					// f = 70 , s = 83, g = 71, t = 84
					Sim engine = Sim.GetInstance();
					Log simLog = engine.getLog();
					switch (e.getKeyCode()) {
					case 70:
						// file open
						doOpenFile();
						break;
					case 83:
						// file save
						if(ToolBar.btnSaveFile.isEnabled())
						{
							ToolBar.btnSaveFile.setEnabled(false);
							doSaveFile();
						}
						break;
					case 71:
						// grammer check;
						if(ToolBar.btnStart.isEnabled())
							doGrammerCheck(engine, simLog);
					case 84:
						// terminate
						if(ToolBar.btnStop.isEnabled())
							doTerminate(engine, simLog);
						break;
					default:
						break;
					}
				}
			}
		});
		textArea.getDocument().addDocumentListener(new DocumentListener(){
			Sim engine = Sim.GetInstance();
			Log simLog = engine.getLog();

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				ToolBar.btnSaveFile.setEnabled(true);
				doUiState(STATE_CHECK, engine, simLog);
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				//System.out.println("insertUpdate");
				ToolBar.btnSaveFile.setEnabled(true);
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				//System.out.println("removeUpdate");
				ToolBar.btnSaveFile.setEnabled(true);
			}
		});
		for(int i = 1; i <= 8; i++)
			defaultRegister.add("r"+i);
		for(int i = 1; i <= 4; i++)
			defaultRegister.add("f"+i);
		super.add(sp);
		super.setVisible(true);

	}
	
	public void setHotKeyListener(KeyAdapter keys)
	{
		textArea.addKeyListener(keys);
	}
	public void setModifiedListener(DocumentListener mod)
	{
		textArea.getDocument().addDocumentListener(mod);
	}
	
	public void setOnRamMouserOver(String id) {
		try {
			int lastoff = textArea.getLastVisibleOffset();
			int lastline = textArea.getLineOfOffset(lastoff);
			for (int i = 0; i <= lastline; i++) {
				int startoff = textArea.getLineStartOffset(i);
				int endoff = textArea.getLineEndOffset(i);
				String ln = textArea.getText(startoff, endoff - startoff);

				if (ln.contains(id + " :=") || ln.contains(":= " + id) || ln.contains(id + "[")
						|| ln.contains("&" + id)) {
					setLineColor(i, Color.YELLOW);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void requestFocusToTextEditor() {
		textArea.requestFocus();
	}

	public int[] getBreakPoints() {
		int[] points;
		Gutter gutter = sp.getGutter();
		bookmarks = gutter.getBookmarks();
		points = new int[bookmarks.length];
		for (int i = 0; i < bookmarks.length; i++) {
			try {
				points[i] = textArea.getLineOfOffset(bookmarks[i].getMarkedOffset()) + 1;
			} catch (BadLocationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// System.out.println("offset = " + a[i].getMarkedOffset()); // 모든
		// 브레이크포인트의 라인을 알고싶다면.
		return points;
	}

	public void resetBreakPoints() {
		Gutter gutter = sp.getGutter();
		bookmarks = gutter.getBookmarks();
		for (int i = 0; i < bookmarks.length; i++) {
			try {
				gutter.toggleBookmark(textArea.getLineOfOffset(bookmarks[i].getMarkedOffset()));
			} catch (BadLocationException e) {
				e.printStackTrace();
			}
		}
	}

	public void setLineColor(int line, Color color) {
		try {
			textArea.addLineHighlight(line, color);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void clearLineColor() {
		textArea.removeAllLineHighlights();
	}

	public void setFocusLine(int line) {
		try {
			textArea.setCaretPosition(textArea.getLineStartOffset(line));
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getTextLine(int line, int len) {
		int stratoff = 0;
		try {
			stratoff = textArea.getLineStartOffset(line);
			return textArea.getText(stratoff, len);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void setText(String text) {
		textArea.setText(text);
	}

	public String getText() {
		return textArea.getText();
	}

	public int getIndex() {
		return thisIndex;
	}

	public void setIndex(int idx) {
		this.thisIndex = idx;
	}

	private int viewToModelLine(RSyntaxText textArea, Point p) throws BadLocationException {
		int offs = textArea.viewToModel(p);
		return offs > -1 ? textArea.getLineOfOffset(offs) : -1;
	}

	public String getThisTitle() {
		return thisTitle;
	}

	public void setThisTitle(String thisTitle) {
		this.thisTitle = thisTitle;
	}
	
	private void doOpenFile()
	{
		final JFileChooser fc = new JFileChooser();
		// 기본 경로
		fc.setCurrentDirectory(new File (System.getProperty("user.home") + System.getProperty("file.separator")+ "Desktop"));
		// 확장자 필터
		fc.setFileFilter(new FileNameExtensionFilter("Pseudo Assembler File (*.pa)", "pa"));
		
		TextEditor newTextEditor = null;
		Sim engine = Sim.GetInstance();
		@SuppressWarnings("unused")
		Log simLog = engine.getLog();
		int returnVal = 0;
		
		returnVal = fc.showOpenDialog(newTextEditor);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			if(!(file.getName().endsWith(".pa") || file.getName().endsWith(".txt"))) return;
			
			String text = fm.openFile(file);
			newTextEditor = tabbedPane.addTextEditor(file.getName(), tabbedPane);
			newTextEditor.setText(text);
			tabbedPane.getCurrentEditor().setThisTitle(file.getName());
			tabFile.put(file.getName(), file);
		}
	}
	private void doSaveFile()
	{
		final JFileChooser fc = new JFileChooser();
		// 기본 경로
		fc.setCurrentDirectory(new File (System.getProperty("user.home") + System.getProperty("file.separator")+ "Desktop"));
		// 확장자 필터
		fc.setFileFilter(new FileNameExtensionFilter("Pseudo Assembler File (*.pa)", "pa"));
		
		String title = null;
		int returnVal = 0;
		title = tabbedPane.getCurrentEditor().getThisTitle();
		if (title.equals("New")) // 새 저장.
		{
			TextEditor curEditor = tabbedPane.getCurrentEditor();
			returnVal = fc.showSaveDialog(curEditor);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				if (file.exists())
					file.delete();
				fm.saveFile(file, curEditor.getText());
				tabbedPane.getCurrentEditor().setThisTitle(file.getName());
				tabFile.put(title, file);
				ToolBar.btnSaveFile.setEnabled(false);
			}
		} else // 그냥 저장.
		{
			TextEditor curEditor = tabbedPane.getCurrentEditor();
			File file = new File(tabFile.get(title).getAbsolutePath());
			if (file.exists())
				file.delete();
			fm.saveFile(file, curEditor.getText());
			tabbedPane.getCurrentEditor().setThisTitle(file.getName());
			ToolBar.btnSaveFile.setEnabled(false);
		}
	}
	private void doTerminate(Sim engine, Log simLog) {
		try {
			engine.resetEngine();
			resetUiandLog(simLog);
			doUiState(STATE_READY, engine, simLog);
			// resetAll(engine, simLog);
		} catch (Exception exception) {
			// 아마 simLog가 Null거나, Sim자체가 Null일경우 밖에 없습니다.
		}
	}

	private void doGrammerCheck(Sim engine, Log simLog) {
		File errfile = new File("log.txt");
		PrintStream printStream = null;
		try {
			printStream = new PrintStream(new FileOutputStream(errfile));
			System.setErr(printStream);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		resetUiandLog(simLog);

		TextEditor editor = (TextEditor) tabbedPane.getSelectedComponent();
		String text = editor.getText();
		engine.pasteLogsPanel(tabbedPane.getLogTabs());
		// 구문 검사
		engine.runGrammarCheck(text);

		// 구문에 에러가 있다면.
		if (simLog.hasError()) {
			doUiState(STATE_CHECK, engine, simLog);
			ArrayList<String> errlns = readFile(errfile);
			for (int i = 0; i < errlns.size(); i++) {
				String errln_full = errlns.get(i);
				int errln = Integer.parseInt(errln_full.split(":")[0].split("line ")[1]) - 1;
				tabbedPane.getCurrentEditor().setLineColor(errln, errPink);
			}
			return;
		} else {
			// 에러가 없다면.
			doUiState(STATE_READY, engine, simLog);

		}
		if (printStream != null)
			printStream.close();
	}
	Color errPink = new Color(255, 230, 230);
	private void doResume() {
		Sim engine = Sim.GetInstance();
		Log simLog = engine.getLog();
		int[] noBreakpt;
		int srcln = 0;
		String thisLine;

		noBreakpt = tabbedPane.getCurrentEditor().getBreakPoints();
		srcln = engine.runWithBreakpoints(noBreakpt) - 1;
		tabbedPane.getCurrentEditor().setFocusLine(srcln);
		thisLine = tabbedPane.getCurrentEditor().getTextLine(srcln, 3);
		doUiState(STATE_CHECK, engine, simLog);
		
		File errfile = new File("log.txt");
		PrintStream printStream = null;
		try {
			printStream = new PrintStream(new FileOutputStream(errfile));
			System.setErr(printStream);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if (simLog.hasError()) {
			ArrayList<String> errMsg = simLog.serializeErr();
			for (int i = 0; i < errMsg.size(); i++) {
				String errln_full = errMsg.get(i);
				System.out.println(errln_full);
				int errln = Integer.parseInt(errln_full.split(":")[0].split("line ")[1]) - 1;
				tabbedPane.getCurrentEditor().setLineColor(errln, errPink);
			}
			return;
		} else {
			if (thisLine != null) {
				if (thisLine.equals("end")) {
					doUiState(STATE_END, engine, simLog);
				} else {
					doUiState(STATE_STOP, engine, simLog);
				}
			}
		}
	}

	private void doStepOver() {
		Sim engine = Sim.GetInstance();
		Log simLog = engine.getLog();
		int srcln = 0;
		String thisLine;

		srcln = engine.stepover() - 1;
		tabbedPane.getCurrentEditor().setFocusLine(srcln);
		thisLine = tabbedPane.getCurrentEditor().getTextLine(srcln, 3);
		doUiState(STATE_CHECK, engine, simLog);
		
		File errfile = new File("log.txt");
		PrintStream printStream = null;
		try {
			printStream = new PrintStream(new FileOutputStream(errfile));
			System.setErr(printStream);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if (simLog.hasError()) {
			ArrayList<String> errMsg = simLog.serializeErr();
			for (int i = 0; i < errMsg.size(); i++) {
				String errln_full = errMsg.get(i);
				System.out.println(errln_full);
				int errln = Integer.parseInt(errln_full.split(":")[0].split("line ")[1]) - 1;
				tabbedPane.getCurrentEditor().setLineColor(errln, errPink);
			}
			return;
		} else {
			if (thisLine != null) {
				if (thisLine.equals("end")) {
					doUiState(STATE_END, engine, simLog);
				} else {
					doUiState(STATE_STOP, engine, simLog);
				}
			}
		}
	}

	private void doUiState(int state, Sim engine, Log simLog) {
		switch (state) {
		case STATE_CHECK: // Document Listener 저장버튼 활성화/비활성화
			ToolBar.btnStart.setEnabled(true);
			ToolBar.btnResume.setEnabled(false);
			ToolBar.btnStepOver.setEnabled(false);
			ToolBar.btnStop.setEnabled(false);
			break;
		case STATE_READY:
			ToolBar.btnStart.setEnabled(false);
			ToolBar.btnResume.setEnabled(true);
			ToolBar.btnStepOver.setEnabled(false);
			ToolBar.btnStop.setEnabled(false);
			break;
		case STATE_STOP:
			ToolBar.btnStart.setEnabled(false);
			ToolBar.btnResume.setEnabled(true);
			ToolBar.btnStepOver.setEnabled(true);
			ToolBar.btnStop.setEnabled(true);
			refreshUI(engine, simLog);
			break;
		case STATE_END:
			ToolBar.btnStart.setEnabled(false);
			ToolBar.btnResume.setEnabled(false);
			ToolBar.btnStepOver.setEnabled(false);
			ToolBar.btnStop.setEnabled(true);
			refreshUI(engine, simLog);
			break;
		default:
			break;
		}
	}

	private void refreshUI(Sim engine, Log simLog) {
		// resetUiandLog(simLog);
		setRegNmemUi(engine);
		//simLog.printErr();
		simLog.printOutput();
	}

//	private void resetAll(SimEngine engine, Log simLog) {
//		engine.resetEngine();
//		simLog = Sim.GetInstance().getLog();
//		resetUiandLog(simLog);
//		ToolBar.btnStart.setEnabled(true);
//		tabbedPane.getCurrentEditor().setFocusLine(0);
//		tabbedPane.getCurrentEditor().resetBreakPoints();
//	}

	public void resetUiandLog(Log simLog) {
		tabbedPane.getCurrentEditor().clearLineColor();
		simLog.clearLog();
		register.resetRegister();
		ram.resetRAM();
		tabbedPane.getLogTabs()[0].setText("");
		tabbedPane.getLogTabs()[1].setText("");
		tabbedPane.getLogTabs()[2].setText("");
		ToolBar.btnStop.setEnabled(false);
		ToolBar.btnResume.setEnabled(false);
		ToolBar.btnStepOver.setEnabled(false);
	}

	public void setRegNmemUi(Sim engine) {
		// resetRegNmemUi();
		engine = Sim.GetInstance();

		int[] iregs = engine.getIRegs();
		double[] fregs = engine.getFRegs();
		double[] mems = engine.getMemories();

		for (int i = 0; i < iregs.length - 1; i++)
			register.setIntRegText(i, "" + iregs[i + 1]);
		for (int i = 0; i < fregs.length - 1; i++)
			register.setFloatRegText(i, "" + fregs[i + 1]);

		boolean[] types = engine.getMemoryTypeArr();
		String[] tooltipStrArr = engine.getMemoryTipArr();
		for (int i = 0; i < mems.length; i++) {

			if (types[i] == true) {
				ram.setRamText(i, String.valueOf((int) mems[i]), tooltipStrArr[i]);
			} else
				ram.setRamText(i, String.format("%.4f", mems[i]), tooltipStrArr[i]);
		}
	}
	private ArrayList<String> readFile(File file) {
		ArrayList<String> result = new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			while ((line = br.readLine()) != null) {
				result.add(line);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	class dragNdrop extends DropTarget{
		private static final long serialVersionUID = -7531080085856537328L;
		
		public synchronized void drop(DropTargetDropEvent evt) {
			try {
				evt.acceptDrop(DnDConstants.ACTION_COPY);
				@SuppressWarnings("unchecked")
				List<File> droppedFiles = (List<File>) evt.getTransferable()
						.getTransferData(DataFlavor.javaFileListFlavor);
				for (File file : droppedFiles) {
					// process files
					
					String text = fm.openFile(file);
					thisEditor = tabbedPane.addTextEditor(file.getName(), tabbedPane);
					thisEditor.setText(text);
					tabbedPane.getCurrentEditor().setThisTitle(file.getName());
					tabFile.put(file.getName(), file);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}