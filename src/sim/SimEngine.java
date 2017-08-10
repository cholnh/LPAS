package sim;

import java.util.ArrayList;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import parser.PAGrammarLexer;
import parser.PAGrammarParser;
import view.Components.LogTab;

class SimEngine implements Sim {
	Log log = new Log();
	static SimEngine engine = new SimEngine(); 
	public Log getLog() {
		return log;
	}

	public void pasteLogsPanel(LogTab[] logpanels) {
		log.pasteLogsPanel(logpanels);
		;
	}

	private static int pc = 0;

	ThrowingErrorListener tel = new ThrowingErrorListener();
	@Override
	public void runGrammarCheck(String text) {
		resetParserEngine();
		ANTLRInputStream input = null;
		input = new ANTLRInputStream(text);
		PAGrammarParser parser = null;
		try {
		PAGrammarLexer lexer = new PAGrammarLexer(input);
		lexer.addErrorListener(tel);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		parser = new PAGrammarParser(tokens);
		} catch (Exception e1) {
			setErrorCode(Sim.TokenError, e1.toString()+"\n");
			e1.printStackTrace();
			return;
		}
		parser.addErrorListener(tel);

		parser.prog();

		System.out.println();
		//resetEngine();

		try {
		for (Instruction in : parser.lines) {
			if (in == null)
				continue;
			InstData inst = new InstData(in.type);
			inst.init(in);

			engine.instArray.add(inst);
		}
		} catch (Exception e1) {
			setErrorCode(Sim.SemanticError, e1.toString());
			e1.printStackTrace();
		}
	}

	int getPC() {
		return pc;
	}

	void setPC(int pc_) {
		pc = pc_;
	}

	ArrayList<InstData> instArray = new ArrayList<>();

	public void resetParserEngine() {
		engine.instArray.clear();
		ST.GetST().parserReset();
		resetEngine();
	}

	public void resetEngine() {
		setPC(0);
		ST.GetST().reset();
		errorCode = 0;
		log.clearLog();
		InstData.initStat();
	}


	private InstData getNextInst() {
		return instArray.get(pc++);
	}

	@Override
	public int runWithBreakpoints(int[] bplines) {

		int where = -1;
		int[] bps = new int[bplines.length];
		for (int i = 0; i < bplines.length; i++)
			bps[i] = getLineNum(bplines[i]);
		where = runto(bps);
		if (where == -1) {
			System.out.println("end of program");
			InstData inst = instArray.get(instArray.size() - 1);
			return inst.srcln;
		}
		return where;
	}
	private int pcBack = -1;

	public int stepover() {
		InstData inst = null;
		//System.out.println("runto pc: " + pc);
		pcBack = pc;
		inst = engine.getNextInst(); // instArray.get(pc++);
		if (inst.type == Sim.InstType.endp)
			return -1;
		inst.print();
		try {
		inst.eval();
		} catch (Exception e1) {
			setErrorCode(Sim.SemanticError, e1.toString());
			e1.printStackTrace();
		}
		log.flushLog();
		if (errorCode != 0) 
			return getLocation(pcBack);
		else return getCurLocation();
	}

	int runto(int[] bplines) { // 브레이크포인트 설정된 소스 줄번호 배열로 런...
		InstData inst = null;
		int instCount = 0;
		@SuppressWarnings("unused")
		long endtime, starttime = System.currentTimeMillis(); 
		while (true) {
			//System.out.println("runto pc: " + pc);
			pcBack = pc;
			inst = engine.getNextInst(); // instArray.get(pc++);
			if (inst.type == Sim.InstType.endp) {
				endtime = System.currentTimeMillis(); 
//				if (inst.type == Sim.InstType.endp)
//					log.setLogBuf(instCount + ", " + (endtime-starttime) + "밀리초\n\n\n\n");
//				log.flushLog();
				return -1;
			}
			inst.print();
			try {
				inst.eval();
				instCount++;
			if (instCount > 200000) {
				setErrorCode(Sim.InfiniteLoop, "Infinite Loop");
			}
			} catch (Exception e1) {
				setErrorCode(Sim.UnknownError, e1.toString());
				e1.printStackTrace();
			}
			if (errorCode != 0) 
				return getLocation(pcBack);  // 오류 발생 시 현재 실행중이던 문장 줄번호 리턴
			if (engine.checkBps(bplines) != -1) {
				break;
			}
			//log.flushLog();
		}
		return getCurLocation();
	}

	private int checkBps(int[] breakpts) {
		for (int i = 0; i < breakpts.length; i++)
			if (pc == breakpts[i])
				return breakpts[i];
		return -1;
	}

	@Override
	public int[] getIRegs() {
		return ST.GetST().rreg;
	}

	@Override
	public double[] getFRegs() {
		return ST.GetST().freg;
	}

	@Override
	public double[] getMemories() { // 메모리 선언 객체 배열
		return ST.GetST().getMemory();
	}

	private int getLineNum(int srclinenum) { // 소스 줄번호에 대응하는 명령문 번호 pc
		InstData inst = null;
		for (int i = 0; i < instArray.size(); i++) {
			inst = instArray.get(i);
			if (inst.srcln == srclinenum)
				return i;
		}
		return -1;
	}
	
	public String printStat(){
		return InstData.printStat();
	}
//	private InstData getCurInst() {
//	if (pc >= instArray.size() || pc < 0)
//		return null;
//	return instArray.get(pc);
//}
	private int getCurLocation() { // 현재 정지한 위치에 대응하는 소스 줄번호
		InstData inst = null;
		if (pc >= instArray.size() || pc < 0)
			inst = instArray.get(instArray.size() - 1); // at the end
		else inst = instArray.get(pc);
		return inst.srcln;
	}
	private int getLocation(int pcBack) {
		if (pcBack >= instArray.size())
			return -1;
		return instArray.get(pcBack).srcln;
	}

	public boolean[] getMemoryTypeArr() {
		return ST.GetST().getMemoryTypeArr();
	}

	public String[] getMemoryTipArr() {
		return ST.GetST().getMemoryTipArr();
	}

	int errorCode = 0;
	String ErrorMsg[] = { "", "undefined identifier", "type mismatch", "duplicated identifier", 
			"wrong address", "wrong label number", "wrong register index", 
			"after parser-semantic error", "infinite loop", "token recognition error",
			"unknown error" };

	void setErrorCode(int err, String msg) {
		errorCode = err;
		if (pcBack == -1) pcBack = 0;
		InstData inst = instArray.get(pcBack);
		String errMsg = "line " + inst.srcln + ":0 " + ErrorMsg[err - 10] + " " + msg;
		errMsg += "\n\t" + getTypeHint(inst.type);
		Log log = Sim.GetInstance().getLog();
		log.setError(errMsg);
	}

	public String getTypeHint(InstType t) {
		switch (t) {
		case none:
			return "NONE : 알수 없는 명령문";
		case asgni:
			return "정수 지정  - rn := rn 또는 정수값 ";
		case asgnf:
			return "실수 지정  - fn := fn 또는 실수값 ";
		case initi:
			return "정수 초기화 - ID := 정수값";
		case initf:
			return "실수 초기화 - ID := 실수값";
		case arithi:
			return "정수 연산  - rn := 수식, 수식은 (rn 연산 rn) 또는 (rn 연산 정수) ";
		case arithf:
			return "실수 연산  - fn := 수식, 수식은 (fn 연산 fn) 또는 (fn 연산 실수) ";
		case storei:
			return "정수 저장 - ID := rn";
		case storef:
			return "실수 저장 - ID := fn";
		case loadi:
			return "정수 로드 - rn := ID 또는 실수에서 정수로 형변환 - rn := fn";
		case loadf:
			return "실수 로드 - fn := ID 또는 정수에서 실수로 형변환 - fn := rn";
		case ifi:
			return "조건 점프 - if 비교식 goto 레이블, 정수 비교식, 레이블은 Ln"; // ifi
		case iff:
			return "조건 점프 - if 비교식 goto 레이블, 실수 비교식, 레이블은 Ln"; // iff
		case gotoL:
			return "무조건 점프 - goto 레이블, 레이블은 Ln";
		case printi:
		case printf:
		case prints:
			return "출력문 - print (rn 또는 fn 또는 문자열)";
		case inputi:
		case inputf:
			return "입력문 - input rn 또는 fn";
		case cvtf2i:
			return "실수에서 정수로 형변환 - rn := fn";
		case cvti2f:
			return "정수에서 실수로 형변환 - fn := rn";
		case getaddr:
			return "변수 주소 로드 - rn := *ID";
		case loadai:
			return "레지스터의 주소에서 값로드 - rn := *rn";
		case loadaf:
			return "레지스터의 주소에서 값로드 - rn := *rn";
		case storeai:
			return "레지스터의 주소로 정수값 저장 - *rn := rn";
		case storeaf:
			return "레지스터의 주소로 실수값 저장 - *rn := fn";
		case arri:
			return "정수 배열 선언 - int ID[정수]";
		case arrf:
			return "실수 배열 선언 - float ID[정수]";
		case endp:
			return "프로그램 끝";
		default:
			return "잘못된 명령문 타입";
		}
	}

	// public enum InstType {none, asgni, asgnf, initi, initf, arithi, arithf,
	// storei, storef,
	// loadi, loadf, ifi, iff, gotoL, printi, printf, prints,
	// inputi, inputf, cvtf2i, cvti2f, getaddr, loadai, loadaf, storeai,
	// storeaf, arri, arrf, endp};
}
