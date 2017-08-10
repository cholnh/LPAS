package view.Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
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

public class MenuHandler implements ActionListener {

	TabPane tabbedPane;
	TextEditor textEditor;
	LogTab[] logs;
	FileManager fm;
	Register register;
	RAM ram;

	HashMap<String, File> tabFile;

	public MenuHandler(TabPane tabbedPane, TextEditor textEditor, LogTab[] logs, Register register, RAM ram) {
		this.tabbedPane = tabbedPane;
		this.textEditor = textEditor;
		this.logs = logs;
		this.register = register;
		this.ram = ram;
		fm = new FileManager();
		tabFile = tabbedPane.getTabFile();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		final JFileChooser fc = new JFileChooser();
		
		// 기본 경로
		fc.setCurrentDirectory(new File (System.getProperty("user.home") + System.getProperty("file.separator")+ "Desktop"));
		//fc.setCurrentDirectory(new File (System.getProperty("user.dir")); // 현재 디렉토리
		// 확장자 필터
		fc.setFileFilter(new FileNameExtensionFilter("Pseudo Assembler File (*.pa)", "pa"));

		String title = null;
		TextEditor newTextEditor = null;
		String source = e.getActionCommand();
		Sim engine = Sim.GetInstance();
		switch (source) {
		case "New":
			tabbedPane.addTextEditor("New", tabbedPane);
			break;
		case "Open":
			int returnVal = fc.showOpenDialog(newTextEditor);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				if(!(file.getName().endsWith(".pa"))) return;
				
				String text = fm.openFile(file);
				newTextEditor = tabbedPane.addTextEditor(file.getName(), tabbedPane);
				newTextEditor.setText(text);
				tabFile.put(file.getName(), file);
			}
			break;
		case "Save":
			title = tabbedPane.getCurrentEditor().getThisTitle();
			if (title.equals("New")) // 새 저장.
			{
				TextEditor curEditor = tabbedPane.getCurrentEditor();
				returnVal = fc.showSaveDialog(curEditor);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
//					if (file.exists())
//						file.delete();
					file = fm.saveFile(file, curEditor.getText());
					//tabbedPane.getCurrentEditor().setThisTitle(file.getName());
					tabbedPane.setTitle(file.getName());
					tabFile.put(title, file);
				}
			} else // 그냥 저장.
			{
				TextEditor curEditor = tabbedPane.getCurrentEditor();
				File file = new File(tabFile.get(title).getAbsolutePath());
//				if (file.exists())
//					file.delete();
				file = fm.saveFile(file, curEditor.getText());
				//tabbedPane.getCurrentEditor().setThisTitle(file.getName());
				tabbedPane.setTitle(file.getName());
			}
			break;
		case "Save As":
			title = tabbedPane.getCurrentEditor().getThisTitle();
			TextEditor curEditor = tabbedPane.getCurrentEditor();
			returnVal = fc.showSaveDialog(curEditor);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
//				if (file.exists())
//					file.delete();
				file = fm.saveFile(file, curEditor.getText());
				//tabbedPane.getCurrentEditor().setThisTitle(file.getName());
				tabbedPane.setTitle(file.getName());
				tabFile.put(file.getName(), file);
			}
			break;
		case "Log Save":
			title = tabbedPane.getCurrentEditor().getThisTitle();
			File file = tabFile.get(title);

			if (title.equals("New")) {
				JOptionPane.showMessageDialog(null, "파일을 우선 저장해주세요.");
				break;
			}
			
			engine.printStat();
			String logPath = file.getAbsolutePath().replace(file.getName(), "") + file.getName().replace(".pa", "") + ".log.txt";
			file = new File(logPath, "");
//			if (file.exists())
//				file.delete();
			String sep = "\n\n==============   Output   =====================\n";
			fm.saveFile(file, logs[0].getText()+sep+logs[2].getText()+engine.printStat());
			break;
//		case "Log Save As":
//			title = tabbedPane.getCurrentEditor().getThisTitle();
//			file = tabFile.get(title);
//			if (title.equals("New")) {
//				JOptionPane.showMessageDialog(null, "파일을 우선 저장해주세요.");
//				break;
//			}
//			engine.printStat();
//			if (file == null) // 새 저장.
//			{
//				returnVal = fc.showSaveDialog(logs[0]);
//				if (returnVal == JFileChooser.APPROVE_OPTION) {
//					File selectedFile = fc.getSelectedFile();
//					if (selectedFile.exists())
//						selectedFile.delete();
//					String sep2 = "\n\n==============   Output   =====================\n";
//					fm.saveFile(selectedFile, logs[0].getText()+sep2+logs[2].getText()+engine.printStat());
//				}
//			}
//			break;
		case "Close All":
			tabbedPane.removeAll();
			List <TextEditor> Editors = tabbedPane.getEditors();
			Editors.clear();
			break;
		default:
			break;
		}
	}

	public void resetUiandLog(Log simLog) {
		simLog.clearLog();
		register.resetRegister();
		ram.resetRAM();
		logs[0].setText("");
		logs[1].setText("");
		logs[2].setText("");
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
			
			if (types[i] == true)
			{
				ram.setRamText(i, String.valueOf((int) mems[i]), tooltipStrArr[i]);
			}
			else
				ram.setRamText(i, String.format("%.4f", mems[i]), tooltipStrArr[i]);
		}

	}
}
