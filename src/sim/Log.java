package sim;

import java.awt.Color;
import java.util.ArrayList;

import view.Control;
import view.Components.LogTab;

public class Log {
	// private int logCount = 0;
	private ArrayList<String> logMap = new ArrayList<>();
	private ArrayList<String> errorMap = new ArrayList<>();
	private String logBuffer = "";

	private LogTab[] logpanels;

	private final int TYPE_LOG = 1;
	private final int TYPE_ERROR = 2;
	private final int TYPE_OUTPUT = 3;

	public void pasteLogsPanel(LogTab[] logpanels) {
		this.logpanels = logpanels;
	}

	// log
	public void setLogBuf(String logMessage) {
		logBuffer += logMessage;
	}

	public void resetLogBuf() {
		logBuffer = "";
	}
	void alignLogTab() {
		StringBuffer buf = new StringBuffer(logBuffer);
		int len = logBuffer.length();
		for (int i = len; i < 40; i++) buf.append(' ');
//		if (len <= 24&&logBuffer.indexOf("\\t")==-1) logBuffer += "\t";
//		if (len > 24 && len <= 32) logBuffer += "\t";
//		if (len >= 33 && len <= 36) logBuffer += "\t";
		logBuffer = buf.toString();
	}
	void flushLog() {
		if (logBuffer.length() == 0)
			return;
		//sendMessage(logpanels[0], logBuffer + "\n", TYPE_LOG);
		//logpanels[0].appendToPane(logBuffer, Color.BLACK);
		logMap.add(logBuffer);
		logBuffer = "";
	}
	public void clearLog() {
		//logBuffer = "";
		logMap.clear();
		errorMap.clear();
		outStream.setLength(0);
	}

	public void printLog() {
		//long endtime, starttime = System.currentTimeMillis(); 
		StringBuffer logchunk = new StringBuffer();
		for (int i = 0; i < logMap.size(); i++) {
			if (!logMap.get(i).trim().equals("")) {
				logchunk.append(logMap.get(i));
				logchunk.append("\n");
			}
			if (i % 1000 == 999) {
				logpanels[0].appendToPane(logchunk.toString(), Color.BLACK);
				logchunk.setLength(0);
			}
		}
		logpanels[0].appendToPane(logchunk.toString(), Color.BLACK);
		logMap.clear();
		//endtime = System.currentTimeMillis(); 
		//logpanels[0].appendToPane("\n로그출력 : "+(endtime-starttime) + "밀리초\n\n\n\n", Color.BLACK);
	}
	public void printErr() {
		for (int i = 0; i < errorMap.size(); i++) {
			if (!errorMap.get(i).trim().equals("")) {
				System.err.println("err : " + errorMap.get(i));
				Control.getInstance().setLogText("[err] " + errorMap.get(i) + "\n");
			}
		}
	}

	public boolean hasError() {
		return !errorMap.isEmpty();
	}

	public ArrayList<String> serializeErr() {
		return errorMap;
//		ArrayList<String> out = new ArrayList<String>();
//		for (int i = 0; i < errorMap.size(); i++) {
//			if (!errorMap.get(i).equals("")) {
//				out.add(errorMap.get(i));
//			}
//		}
//		return out;
	}

	// error
	void setError(String errmsg) {
		errmsg = errmsg.trim() + "\n";
		if (errmsg.equals(""))
			return;
		Control.getInstance().setLogText(errmsg);
		errorMap.add(errmsg);
		setLogBuf(errmsg);
		sendMessage(logpanels[1], errmsg, TYPE_ERROR);
	}

	StringBuffer outStream = new StringBuffer();

	// output
	void setOutput(String out) {
		if (out.equals(""))
			return;
		if (out.charAt(0) == '\"')
			out = out.substring(1, out.length() - 1);
		while (out.indexOf("\\n") != -1) {
			int index = out.indexOf("\\n");
			outStream.append(out.substring(0, index));
			sendMessage(logpanels[2], outStream.toString(), TYPE_OUTPUT);
			outStream.setLength(0);
			out = out.substring(index + 2);
		}
		if (out.length() > 0)
			outStream.append(out);
	}

	public void printOutput() {
		if (outStream.length() > 0) {
			sendMessage(logpanels[2], outStream.toString(), TYPE_OUTPUT);
			outStream.setLength(0);
		}
	}

	public void sendMessage(LogTab logtab, String text, int type) {
		//System.out.print(""); // IO작업으로 스레드 동기화.
		//Thread thread = new Thread(new RunOnUiThread(logtab, text, type));
		//thread.start();
//		SwingUtilities.invokeLater(new Runnable() {
//			public void run() {
				switch (type) {
				case TYPE_LOG:
					logtab.appendToPane(text, Color.BLACK);
					//logtab.requestLog(0);
					break;
				case TYPE_ERROR:
					logtab.appendToPane(text, Color.RED);
					//logtab.requestLog(1);
					break;
				case TYPE_OUTPUT:
					logtab.appendToPane(text + "\n", Color.BLUE);
					//logtab.requestLog(2);
					break;
				default:
					logtab.appendToPane(text, Color.BLACK);
					//logtab.requestLog(0);
					break;
				}
			//}
//		});
	}

	/*class RunOnUiThread implements Runnable {
		String text;
		LogTab logtab;
		int type;

		public RunOnUiThread(LogTab logtab, String text, int type) {
			this.logtab = logtab;
			this.text = text;
			this.type = type;
		}

		@Override
		public void run() {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					switch (type) {
					case TYPE_LOG:
						logtab.appendToPane(text, Color.BLACK);
						logtab.requestLog(0);
						break;
					case TYPE_ERROR:
						logtab.appendToPane(text, Color.RED);
						logtab.requestLog(1);
						break;
					case TYPE_OUTPUT:
						logtab.appendToPane(text + "\n", Color.BLUE);
						logtab.requestLog(2);
						break;
					default:
						logtab.appendToPane(text, Color.BLACK);
						logtab.requestLog(0);
						break;
					}
				}
			});
		}

	}*/
}
