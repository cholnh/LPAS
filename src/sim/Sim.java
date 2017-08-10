package sim;

import view.Components.LogTab;

public interface Sim {
	enum InstType {none, asgni, asgnf, initi, initf, arithi, arithf, storei, storef,
		loadi, loadf, ifi, iff, gotoL, printi, printf, prints, // 10~17
		inputi, inputf, cvtf2i, cvti2f, getaddr, loadai, loadaf, // 18-24
		storeai, storeaf, arri, arrf, endp};
	
	//static SimEngine engine = new SimEngine(); 
	public static Sim GetInstance() {
		return SimEngine.engine; 
	}
	
	public void runGrammarCheck(String text);
	
	public int stepover();
	//public int runto(int[] bplines);
	public int runWithBreakpoints(int[] bplines);
	
	public int[] getIRegs();
	public double[] getFRegs();
	public double[] getMemories();
	public boolean[] getMemoryTypeArr();
	public String[] getMemoryTipArr();

	public void resetParserEngine();
	public void resetEngine();
	
	public Log getLog();
	public void pasteLogsPanel(LogTab[] logpanels);

	public String printStat();
	final int UndefinedIdentifier = 11;
	final int TypeMismatch = 12;
	final int DuplicatedId = 13;
	final int WrongAddress = 14;
	final int WrongLabelNum = 15;
	final int RegisterIndex = 16;
	final int SemanticError = 17;
	final int InfiniteLoop = 18;
	final int TokenError = 19;
	final int UnknownError = 20;
	// "after parser-semantic error", "infinite loop", "unknown error"
}