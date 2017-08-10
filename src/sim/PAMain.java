package sim;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import parser.PAGrammarLexer;
import parser.PAGrammarParser;

public class PAMain {
	static SimEngine engine = SimEngine.engine;
	public static void main(String... args) throws IOException {
		File file = new File("test1.pa");
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		ANTLRInputStream input = null;
		input = new ANTLRInputStream(fis);
		PAGrammarLexer lexer = new PAGrammarLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		PAGrammarParser parser = new PAGrammarParser(tokens);
//		parser.removeErrorListeners();
//		parser.addErrorListener(ThrowingErrorListener.INSTANCE);
//		try {
		parser.prog();
//		 } catch (ParseCancellationException e) {
//		 System.out.println("Parse Error : " + e.getMessage());
//		 //e.printStackTrace();
//		 throw new RuntimeException();
//		 }
		
		for (Instruction in : parser.lines) {
			if (in == null)
				continue;
			InstData inst = new InstData(in.type);
			inst.init(in);
			engine.instArray.add(inst);
		}
		runWithBreakpoints();
		
	}
	static void runWithBreakpoints() {
//		Log log = engine.getLog();
//		
//		InstData inst = null;
//		int where=-1;
//		Scanner scan = new Scanner(System.in);
//		int tmp[] = new int[10];
//		int cc = 0;
//		System.out.print("브레이크 포인트 설정...(종료 0)");
//		while (true) {
//			tmp[cc] = scan.nextInt();
//			if (tmp[cc] == 0) break;
//			cc++;
//		}
//		//scan.nextLine();
//		int[] bps = new int[cc];
//		for (int i=0; i < cc; i++)
//			bps[i] = engine.getLineNum(tmp[i]);
//		while (true) {
//			//inst = engine.getNextInst(); // instArray.get(pc++);
//			//log.printLog();	// 브레이크 포인트 까지 로그 출력
//			where = runto(bps);
//			if (where == -1)
//				break;			
//			log.setLogBuf("Stop At [" + where + "]\n    -->");
//			engine.getCurInst().print();
//			log.flushLog(); // 로그 라인 개행
//			log.setLogBuf("\t-- Enter to continue --\n"); 
//			log.flushLog(); // 로그 라인 개행
//			
//			scan.nextLine();	
//		}
//		log.setLogBuf("Program End...\n");
//		log.flushLog();
//		
//		// log 모은거 전부 출력
//		
//	}
//	//
//	static int runto(int[] breakpts) {
//		Log log = engine.getLog();
//		InstData inst = null;
//		while (true) {
//			inst = engine.getNextInst(); // instArray.get(pc++);
//			if (inst.type == Sim.InstType.endp)
//				return -1;
//			inst.print();
//			inst.eval();
//			log.flushLog();
//			if (engine.checkBps(breakpts)!=-1){
//				break;
//			}
//		}
//		return 0;
	}
}