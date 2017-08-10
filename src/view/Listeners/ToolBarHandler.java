package view.Listeners;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import sim.Log;
import sim.Sim;
//import sim.SimEngine;
import view.Components.LogTab;
import view.Components.RAM;
import view.Components.Register;
import view.Components.TabPane;
import view.TextEditor.TextEditor;
import view.Util.FileManager;

public class ToolBarHandler implements ActionListener {

	final int STATE_CHECK = 0;
	final int STATE_READY = 1;
	final int STATE_STOP = 2;
	final int STATE_END = 3;

	TabPane tabbedPane;
	TextEditor textEditor;
	LogTab[] logs;
	FileManager fm;
	Register register;
	RAM ram;

	HashMap<String, File> tabFile;

	JButton btnSave;
	JButton btnStart;
	JButton btnStop;
	JButton btnResume;
	JButton btnStepOver;

	public ToolBarHandler(TabPane tabbedPane, TextEditor textEditor, LogTab[] logs, Register register, RAM ram) {
		this.tabbedPane = tabbedPane;
		this.textEditor = textEditor;
		this.logs = logs;
		this.register = register;
		this.ram = ram;
		fm = new FileManager();
		
		tabFile = tabbedPane.getTabFile();

	}

	public void initButtons(JButton save, JButton start, JButton stop, JButton resume, JButton stepover) {
		this.btnSave = save;
		this.btnStart = start;
		this.btnStop = stop;
		this.btnResume = resume;
		this.btnStepOver = stepover;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		/*
		 * btnNewFile.setName("NewFile"); btnLoadFile.setName("SaveFile");
		 * btnPlay.setName("Play"); btnPause.setName("Pause");
		 * btnStop.setName("Stop"); btnStepInto.setName("StepInto");
		 * btnRestart.setName("Restart");
		 */
		
		String source = ((JComponent) e.getSource()).getName();
		Sim engine = Sim.GetInstance();
		Log simLog = Sim.GetInstance().getLog();

		switch (source) {
		case "OpenFile":
			doOpenFile();
			break;
		case "SaveFile":
			doSaveFile();
			break;
		case "Start":// Grammar
			doGrammerCheck(engine, simLog);
			break;
		case "Stop":
			doTerminate(engine, simLog);
			break;
		case "Resume":
			doResume();
			break;
		case "StepOver":
			doStepOver();
			break;
		default:
			break;
		}
	}

	@SuppressWarnings("unused")
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

	/*
	 * final int STATE_MODIFIED = 0; // CHECK상태로 이동, Clear UI, Engine OXXX final
	 * int STATE_GRAMMER_SUCESS = 1; // Ready 상태 XOXX final int
	 * STATE_GRAMMER_FAILED = 2; // CHECK 상태유지 OXXX final int STATE_RV_SUCESS =
	 * 3; // STOP상태로 이동 XOOO final int STATE_RV_FAILED = 4; // CHECK 상태로 이동 OXXX
	 * final int STATE_RV_END = 5; // END 상태로 이동 XXXO final int STATE_TEMINATED
	 * = 6; // READY 상태로 이동 XOXX
	 */
	private void doOpenFile()
	{
		final JFileChooser fc = new JFileChooser();
		// 기본 경로
		fc.setCurrentDirectory(new File (System.getProperty("user.home") + System.getProperty("file.separator")+ "Desktop"));
		// 확장자 필터
		fc.setFileFilter(new FileNameExtensionFilter("Pseudo Assembler File (*.pa)", "pa"));
		
		TextEditor newTextEditor = null;
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
		fc.setFileFilter(new FileNameExtensionFilter("Text File (*.txt)", "txt"));
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
				file = fm.saveFile(file, curEditor.getText());
				for(int i = 0; i < tabbedPane.getTabCount(); i++) {
				    if(curEditor == tabbedPane.getComponentAt(i)) {
				        tabbedPane.setTitleAt(i, file.getName());
				        break;
				    }
				}
				tabbedPane.setTitle(file.getName());
				//tabbedPane.getCurrentEditor().setThisTitle(file.getName());
				tabFile.put(file.getName(), file);
				btnSave.setEnabled(false);
			}
		} else // 그냥 저장.
		{
			TextEditor curEditor = tabbedPane.getCurrentEditor();
			File file = new File(tabFile.get(title).getAbsolutePath());
			if (file.exists())
				file.delete();
			file = fm.saveFile(file, curEditor.getText());
			//tabbedPane.getCurrentEditor().setThisTitle(file.getName());
			tabbedPane.setTitle(file.getName());
			btnSave.setEnabled(false);
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
	Color myRed = new Color(255, 230, 230);
	private void doGrammerCheck(Sim engine, Log simLog) {
//		File errfile = new File("log.txt");
//		PrintStream printStream = null;
//		try {
//			printStream = new PrintStream(new FileOutputStream(errfile));
//			System.setErr(printStream);
//		} catch (FileNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}

		resetUiandLog(simLog);

		TextEditor editor = (TextEditor) tabbedPane.getSelectedComponent();
		String text = editor.getText();
		engine.pasteLogsPanel(logs);
		// 구문 검사
		engine.runGrammarCheck(text);
		if (simLog.hasError()) {
			ArrayList<String> errMsg = simLog.serializeErr();
			for (int i = 0; i < errMsg.size(); i++) {
				String errln_full = errMsg.get(i);
				System.out.println(errln_full);
				int errln = Integer.parseInt(errln_full.split(":")[0].split("line ")[1]) - 1;
				tabbedPane.getCurrentEditor().setLineColor(errln, myRed);
			}
			tabbedPane.getCurrentEditor().setFocusLine(0);
			return;
		} else {
			// 에러가 없다면.
			doUiState(STATE_READY, engine, simLog);
			doSaveFile();
		}
//		// 구문에 에러가 있다면.
//		if (!simLog.getErrMap().isEmpty()) {
//			doUiState(STATE_CHECK, engine, simLog);
//			ArrayList<String> errlns = readFile(errfile);
//			for (int i = 0; i < errlns.size(); i++) {
//				String errln_full = errlns.get(i);
//				int errln = Integer.parseInt(errln_full.split(":")[0].split("line ")[1]) - 1;
//				tabbedPane.getCurrentEditor().setLineColor(errln, Color.RED);
//				tabbedPane.getCurrentEditor().setFocusLine(0);
//			}
//			return;
//		} else {
//			// 에러가 없다면.
//			doUiState(STATE_READY, engine, simLog);
//
//		}
//		if (printStream != null)
//			printStream.close();
	}

	private void doResume() {
		Sim engine = Sim.GetInstance();
		Log simLog = Sim.GetInstance().getLog();
		int[] noBreakpt;
		int srcln = 0;
		String thisLine;

		noBreakpt = tabbedPane.getCurrentEditor().getBreakPoints();
		srcln = engine.runWithBreakpoints(noBreakpt) - 1;
		tabbedPane.getCurrentEditor().setFocusLine(srcln);
		thisLine = tabbedPane.getCurrentEditor().getTextLine(srcln, 3);
		doUiState(STATE_CHECK, engine, simLog);
		
//		File errfile = new File("log.txt");
//		PrintStream printStream = null;
//		try {
//			printStream = new PrintStream(new FileOutputStream(errfile));
//			System.setErr(printStream);
//		} catch (FileNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		
		if (simLog.hasError()) {
			ArrayList<String> errMsg = simLog.serializeErr();
			for (int i = 0; i < errMsg.size(); i++) {
				String errln_full = errMsg.get(i);
				System.out.println(errln_full);
				int errln = Integer.parseInt(errln_full.split(":")[0].split("line ")[1]) - 1;
				tabbedPane.getCurrentEditor().setLineColor(errln, myRed);
			}
			tabbedPane.getCurrentEditor().setFocusLine(0);
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
		Log simLog = Sim.GetInstance().getLog();
		int srcln = 0;
		String thisLine;

		srcln = engine.stepover() - 1;
		tabbedPane.getCurrentEditor().setFocusLine(srcln);
		thisLine = tabbedPane.getCurrentEditor().getTextLine(srcln, 3);
		doUiState(STATE_CHECK, engine, simLog);
		
//		File errfile = new File("log.txt");
//		PrintStream printStream = null;
//		try {
//			printStream = new PrintStream(new FileOutputStream(errfile));
//			System.setErr(printStream);
//		} catch (FileNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		
		if (simLog.hasError()) {
			ArrayList<String> errMsg = simLog.serializeErr();
			for (int i = 0; i < errMsg.size(); i++) {
				String errln_full = errMsg.get(i);
				System.out.println(errln_full);
				int errln = Integer.parseInt(errln_full.split(":")[0].split("line ")[1]) - 1;
				tabbedPane.getCurrentEditor().setLineColor(errln, myRed);
			}
			tabbedPane.getCurrentEditor().setFocusLine(0);
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
			btnStart.setEnabled(true);
			btnResume.setEnabled(false);
			btnStepOver.setEnabled(false);
			btnStop.setEnabled(false);
			break;
		case STATE_READY:
			btnStart.setEnabled(false);
			btnResume.setEnabled(true);
			btnStepOver.setEnabled(false);
			btnStop.setEnabled(false);
			break;
		case STATE_STOP:
			btnStart.setEnabled(false);
			btnResume.setEnabled(true);
			btnStepOver.setEnabled(true);
			btnStop.setEnabled(true);
			refreshUI(engine, simLog);
			break;
		case STATE_END:
			btnStart.setEnabled(false);
			btnResume.setEnabled(false);
			btnStepOver.setEnabled(false);
			btnStop.setEnabled(true);
			refreshUI(engine, simLog);
			break;
		default:
			break;
		}
	}

	private void refreshUI(Sim engine, Log simLog) {
		// resetUiandLog(simLog);
		setRegNmemUi(engine);
		simLog.printErr();
		simLog.printOutput();
		simLog.printLog();
	}

//	private void resetAll(SimEngine engine, Log simLog) {
//		engine.resetEngine();
//		simLog = Sim.GetInstance().getLog();
//		resetUiandLog(simLog);
//		btnStart.setEnabled(true);
//		tabbedPane.getCurrentEditor().setFocusLine(0);
//		tabbedPane.getCurrentEditor().resetBreakPoints();
//	}

	public void resetUiandLog(Log simLog) {
		tabbedPane.getCurrentEditor().clearLineColor();
		tabbedPane.getCurrentEditor().setFocusLine(0);
		simLog.clearLog();
		register.resetRegister();
		ram.resetRAM();
		logs[0].setText("");
		logs[1].setText("");
		logs[2].setText("");
		btnStop.setEnabled(false);
		btnResume.setEnabled(false);
		btnStepOver.setEnabled(false);
	}

	public void setRegNmemUi(Sim engine) {
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
}
