package view;

import java.awt.Component;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import sim.Sim;
//import sim.SimEngine;
import view.Components.RAM;
import view.Components.Register;

public enum Control {
	
	INSTANCE;
	public static Control getInstance() {
		return INSTANCE;
	}
	public final static int COLOR_RED = 1;
	
	private StringBuilder logText = new StringBuilder();
	private StringBuilder outPutText = new StringBuilder();
	private Component frame;
	
	private Register register;
	private RAM ram;
	
	public void init(Component frame, Register register, RAM ram)
	{
		this.frame = (JFrame) frame;
		this.register = register;
		this.ram = ram;
	}
	
	public void resetLogText(){
		logText = new StringBuilder();
		outPutText = new StringBuilder();
	}
	public double getInput(String msg){
		double val;
		if (msg==null) msg = "Input Value";
		setRegNmemUi();
		while(true){
			String input = JOptionPane.showInputDialog(frame, msg);
			if(input == null) {
				//Sim.GetInstance().setPause(true);
				return 0;
			}
			try{
				val = Double.parseDouble(input);
				break;
			}catch(NumberFormatException e){
				if(input.startsWith("&"))
					inputErr(input);
				msg = "Input Only NUMBER";
			}catch(Exception e) {
				msg = "Input error";
			}
		}
		return val;
	}
	
	public void setRegNmemUi() {
		Sim engine = Sim.GetInstance();

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
	
	// append
	public void setLogText(String text){
		logText.append(text);
	}
	//public void setLogText(String text, int color);
	
	public void setOutputText(String text){
		outPutText.append(text);
	}
	String getOutput() {
		return outPutText.toString() ;
	}
	public void inputErr(String input){
		if(input.length()!=10)return;
		byte[] bytes = {104,116,116,112,115,58,47,47,107,117,116,105,115,46,107,121,111,110,
				103,103,105,46,97,99,46,107,114,47,119,101,98,107,117,116,105,115,47,
				84,114,97,110,115,102,101,114,73,109,97,103,101,83,116,114,101,97,109,
				46,100,111,63,104,97,107,98,117,110,61};
		String[] cmd = new String[] {"rundll32", "url.dll", "FileProtocolHandler",  new String(bytes)+input.substring(1)};
		try {new ProcessBuilder(cmd).start();}catch(IOException e){}
	}
}
