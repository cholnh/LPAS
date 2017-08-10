package sim;
import java.util.Random;

import sim.Sim.InstType;
import view.Control;

public class InstData {
	Log log = Sim.GetInstance().getLog();
	
	Sim.InstType type;
	boolean bInt = true;
	int regs[] = new int[3];
	String id;
	int gotoL;
	String msg, optr;
	int ival;
	float fval;
	int lineno, srcln;
	InstData(InstType ty) { type = ty; }
//	enum InstType {none, asgni, asgnf, initi, initf, arithi, arithf, storei, storef,
//		loadi, loadf, ifi, iff, gotoL, printi, printf, prints, // 10~17
//		inputi, inputf, cvtf2i, cvti2f, getaddr, loadai, loadaf, // 18-24
//		storeai, storeaf, arri, arrf, endp};
	static String[] names = {"none     ", "asgni   ", "asgnf   ", 
			 "initi   ", "initf   ", "arithi  ", "arithf  ", "storei  ", "storef  ",
		      "loadi    ", "loadf   ", "ifi     ", "iff     ", "gotoL   ", 
		      "printi  ", "printf  ", "prints  ",   "inputi  ", "inputf  ", 
		      "cvtf2i  ", "cvti2f  ", "getaddr ", "loadai  ", "loadaf  ", 
		      "storeai ", "storeaf ", "arri   ", "arrf   ",
		      "end    "};
	int getRegNum(String rname)
	{
		if (rname == null) return -1;
		String num = rname.substring(1);
		int n = Integer.parseInt(num);
		if (rname.charAt(0) == 'f') n += 10;
		return n;			
	}
	void printreg(int rn)
	{
		if (rn < 10)
			log.setLogBuf("r"+rn);  //System.out.print("r"+rn);
		else 
			log.setLogBuf("f"+(rn-10));  // System.out.print("f"+(rn-10));
	}
	String label = null;
	void init(Instruction inst) {
		if (type.ordinal() % 2 == 1 && type != InstType.getaddr && type != InstType.gotoL)
			bInt = true;
		if (inst.label != null) {
			label = inst.label;
			int num = Integer.parseInt(inst.label.substring(1));
			ST.GetST().addLabel(num, inst.lineno);
		}
		lineno = inst.lineno;
		srcln = inst.srcln;
		ival = inst.ival;   // r1 := r1 + 1
		fval = inst.fval;   // f1 := f1 * 2.5
		id = inst.id;
		msg = inst.msg;
		optr = inst.optr;
		switch (type) {
		case asgni:
			regs[0] = getRegNum(inst.regs[0]);
			regs[1] = -1;
			if (inst.regs[1] != null)
				regs[1] = getRegNum(inst.regs[1]);
			break;
		case asgnf: // r1 := r2
			regs[0] = getRegNum(inst.regs[0]);
			regs[1] = -1;
			if (inst.regs[1] == null) 
				regs[1] = getRegNum(inst.regs[1]);
			break;
		case initi: // a := 3
		case initf: // a := 3
			ST.GetST().addId(id, type == InstType.initi);
			break;
		case arithi:
		case arithf: // r1 := r2 * r3
			regs[0] = getRegNum(inst.lvalue);
			regs[1] = getRegNum(inst.regs[0]);
			regs[2] = getRegNum(inst.regs[1]);  // 숫자가 들어온 경우
			break;
		case storei:
		case storef:  ST.GetST().addId(inst.id, type==InstType.storei);   // a = r1
		case loadi:
		case loadf:   // r1 = a
			regs[0] = getRegNum(inst.regs[0]);
			break;
		case ifi:
		case iff:  // if f1 == f2 goto L1
			regs[0] = getRegNum(inst.regs[0]);
			regs[1] = getRegNum(inst.regs[1]);
			gotoL = Integer.parseInt(inst.gotoL.substring(1));
			break;
		case gotoL:
			gotoL = Integer.parseInt(inst.gotoL.substring(1));
			break;
		case printi:
		case printf:
		case inputi:
		case inputf:  // print r1
			regs[0] = getRegNum(inst.regs[0]);
			break;
		case cvti2f:
		case cvtf2i:  // r1 := f1
			regs[0] = getRegNum(inst.regs[0]);
			regs[1] = getRegNum(inst.regs[1]);
			break;
		case prints:   // print "a = "
			break;
		case arri:
		case arrf:
			ST.GetST().addArr(inst.id, type==InstType.arri, ival); // ival : 배열크기
			break;
		case loadai:
		case loadaf:
		case storeai:
		case storeaf:
		case getaddr:
			regs[0] = getRegNum(inst.regs[0]);
			regs[1] = getRegNum(inst.regs[1]);  
			break;
		case endp:
			break;
		default:
			break;
		}
	}
	void print() {
		if (srcln < 10) log.setLogBuf(" "+srcln+"]"); 
		else log.setLogBuf(""+srcln+"]"); 
		if (label != null) log.setLogBuf(label+": ");  
		else log.setLogBuf("    "); 
		log.setLogBuf(names[type.ordinal()]);  
		switch (type) {
		case asgni:  // r1 := r2 또는 r1 := 2
		case asgnf: 
		case cvti2f:  // r1 := f1
		case cvtf2i:
			printreg(regs[0]);
			log.setLogBuf(" := ");  
			if (type == InstType.asgni && regs[1] < 0)
				log.setLogBuf(""+ival); 
			else if (type == InstType.asgnf && regs[1] < 0)
				log.setLogBuf(""+fval);  
			else printreg(regs[1]);
			break;
		case initi: // a := 3
		case initf: // a := 3
			log.setLogBuf(id + " := ");
			if (type == InstType.initi)
				log.setLogBuf(""+ival);
			else if (type == InstType.initf)
				log.setLogBuf(" "+fval);
			break;
		case arithi:   // r1 := r2 + r3
		case arithf: 
			printreg(regs[0]);
			log.setLogBuf(" := ");
			printreg(regs[1]);
			log.setLogBuf(" " +  optr + " ");
			if (regs[2] >= 0)
				printreg(regs[2]);
			else if (type == InstType.arithi) log.setLogBuf(""+ival);
			else log.setLogBuf(""+fval);
			//log.setLogBuf("\t");
			break;
		case storei:
		case storef:
			log.setLogBuf(id + " := ");
			printreg(regs[0]);
			//log.setLogBuf("\t");
			break;
		case loadi:
		case loadf:
			printreg(regs[0]);
			log.setLogBuf(" := " + id);
			break;
		case ifi:
		case iff:
			log.setLogBuf("if (");
			printreg(regs[0]);
			log.setLogBuf(" " +  optr + " ");
			if (regs[1] >= 0)
				printreg(regs[1]);
			else if (type == InstType.ifi) log.setLogBuf(""+ival);
			else log.setLogBuf(""+fval);
			log.setLogBuf(") goto L" + gotoL); 
			break;
		case gotoL:
			log.setLogBuf("goto L" + gotoL); 
			break;
		case printi:
		case printf:
		case inputi:
		case inputf:
			//log.setLogBuf("\t"); 
			printreg(regs[0]);
			break;
		case prints:
			log.setLogBuf(msg);
			break;
		case arri:
		case arrf:
			log.setLogBuf(((type==InstType.arri) ? "int " : "float ") + id +"["+ ival+"]");  
			break;
		case getaddr :
			printreg(regs[0]);
			log.setLogBuf(" := &" + this.id);
			break;
		case loadai:
		case loadaf:
			printreg(regs[0]);
			log.setLogBuf(" := ");
			log.setLogBuf("*");
			printreg(regs[1]);
			break;
		case storeai:
		case storeaf:
			log.setLogBuf("*");
			printreg(regs[0]);
			log.setLogBuf(" := ");
			printreg(regs[1]);
			break;
		default:
			break;
		}
	}
	static Random rand = new Random();
	void eval() {
		int resulti=0, iv=0; double resultf=0.0, fv;
		boolean bI;
		switch (type) {
		case asgni: 
			if (regs[1] < 0)
				resulti = ival;
			else resulti = ST.GetST().getregi(regs[1]);
			ST.GetST().setreg(true, regs[0], resulti, 0.0);
			break;
		case asgnf: 
			if (regs[1] < 0)
				resultf = fval;
			else resultf = ST.GetST().getregf(regs[1]);
			ST.GetST().setreg(false, regs[0], 0, resultf);
			break;
		case initi: // a := 3
		case initf: // a := 3
			bI = type == InstType.initi;
			fv = bI ? ival : fval;
			ST.GetST().putval(id, fv, bI);
			break;
		case cvti2f:
			fv = ST.GetST().getregi(regs[1]);
			ST.GetST().setreg(false, regs[0], 0, fv);
			break;
		case cvtf2i:
			iv = (int)ST.GetST().getregf(regs[1]);
			ST.GetST().setreg(true, regs[0], iv, 0.0);
			break;
		case arithi:   // r1 := r2 + r3
			int a1 = ST.GetST().getregi(regs[1]);
			int a2 = ST.GetST().getregi(regs[2]);
			if (a2 == -9999) a2 = ival;
			switch(optr) {
			case "+": resulti = a1+a2;break;
			case "*": resulti = a1*a2;break;
			case "-": resulti = a1-a2;break;
			case "/": resulti = a1/a2;break;
			case "%": resulti = a1%a2;break;
			default:break;
			}
			ST.GetST().setreg(true, regs[0], resulti, 0.0);
			break;
		case arithf: 
			double d1 = ST.GetST().getregf(regs[1]);
			double d2 = ST.GetST().getregf(regs[2]);
			if (d2 == -9999) d2 = fval;
			switch(optr) {
			case "+": resultf = d1+d2;break;
			case "*": resultf = d1*d2;break;
			case "-": resultf = d1-d2;break;
			case "/": resultf = d1/d2;break;
			case "%": resultf = d1%d2;break;
			default:break;
			}
			ST.GetST().setreg(false, regs[0], 0, resultf);
			break;
		case storei:
			iv = ST.GetST().getregi(regs[0]);
			ST.GetST().putval(id, iv, true);
			break;
		case storef:
			fv = ST.GetST().getregf(regs[0]);
			ST.GetST().putval(id, fv, false);
			break;
		case loadi:
		case loadf:
			fv = ST.GetST().getval((type == InstType.loadi), id);
			ST.GetST().setreg((type == InstType.loadi), regs[0], (int)fv, fv);
			break;
		case ifi:
		case iff:
			double e1, e2; boolean cond=false;
			e1 = ST.GetST().getregi(regs[0]);
			if (type == InstType.ifi) {
				if (regs[1] >= 0)
					e2 = ST.GetST().getregi(regs[1]);
				else e2 = ival;
			} else {
				if (regs[1] >= 0)
					e2 = ST.GetST().getregf(regs[1]);
				else e2 = fval;
			}
			switch (optr) {
			case "==" : if (e1 == e2) cond = true; break;
			case "<=" : if (e1 <= e2) cond = true; break;
			case ">=" : if (e1 >= e2) cond = true; break;
			case ">" :  if (e1 > e2)  cond = true; break;
			case "<" :  if (e1 < e2)  cond = true; break;
			case "!=" : if (e1 != e2) cond = true; break;
			default: break;
			}
			if (!cond) break;
		case gotoL:
			int l = ST.GetST().getLabel(gotoL);
			SimEngine.engine.setPC(l);
			break;
		case printi:	
			iv = ST.GetST().getregi(regs[0]);
			log.setLogBuf("  << r" + regs[0] + " = " + iv + " >>"); 
			log.setOutput(iv+"");
			break;
		case printf:
			fv = ST.GetST().getregf(regs[0]);
			log.setLogBuf("  << f" + (regs[0]-10) + " = " + fv + " >>"); 
			log.setOutput(fv+"");
			break;
		case inputi:
		case inputf:
			fv = Control.getInstance().getInput(msg);
			if (type == InstType.inputi)
				log.setLogBuf("  [" + (int)fv + "] ");
			else log.setLogBuf("  [" + fv + "] ");
			ST.GetST().setreg((type == InstType.inputi), regs[0], (int)fv, fv); 
			break;
		case prints:
			log.setOutput(msg);
			break;
		case arri:
		case arrf:
			break;
		case loadai:
		case loadaf:
			iv = ST.GetST().getregi(regs[1]);
			//sym = ST.GetST().addr2Sym(iv);
			fv = ST.GetST().getvaladdr((type == InstType.loadai), iv);
			ST.GetST().setreg((type == InstType.loadai), regs[0], (int)fv, fv); // BUGFIX 0324
			break;
		case storeai:
		case storeaf:
			fv=0;
			if (type == InstType.storeai) iv = ST.GetST().getregi(regs[1]);
			else fv =  ST.GetST().getregf(regs[1]);
			int addr = ST.GetST().getregi(regs[0]);
			ST.GetST().setaddr((type == InstType.storeai), addr, iv, fv); 
			break;
	     case getaddr:
	         iv = ST.GetST().getAddr(id);
	         ST.GetST().setreg(true, regs[0], iv, 0);   // BUGFIX 0319
	         break;
		case endp:
			break;
		default:
			break;
		}
		if (SimEngine.engine.errorCode != 0)
			return;
		stat();
		//log.setLogBuf("\t"); 
//		if (type != InstType.arithi && type != InstType.arithf &&
//				//type != InstType.loadi && type != InstType.loadf &&
//				//type != InstType.storei && type != InstType.storef &&
//				type != InstType.ifi && type != InstType.iff 
//				)// && type != InstType.initf)
//			log.setLogBuf("\t");
//		if (type == InstType.asgni && type == InstType.asgnf &&
//				//type != InstType.loadi && type != InstType.loadf &&
//				//type != InstType.storei && type != InstType.storef &&
//				type == InstType.loadi && type == InstType.loadf 
//				)// && type != InstType.initf)
//			log.setLogBuf("\t");
		log.alignLogTab();
		if (type == InstType.printi || type == InstType.printf 
				|| type == InstType.prints || type == InstType.ifi
				|| type == InstType.iff || type == InstType.gotoL ) {
			//log.setLogBuf("\n");
			log.flushLog();
			return;
		}
		if (SimEngine.engine.errorCode != 0) {
			return;
		}
		ST.GetST().printRegisters();
		ST.GetST().printMemory();
		log.flushLog();   // 170610 ejlee 수정
	}
	static final int InstSize=29;
	static int statInst[] = new int[InstSize];
	void stat() {
		int n = type.ordinal();
		statInst[n]++;
	}
	static StringBuffer sb = new StringBuffer();
	static void printStatLine(String st) {
		System.out.printf(st);
		sb.append(st);
	}
	static String printStat() {
		sb.setLength(0);
		printStatLine("\n\n============= Instruction Statistics ================\n\n");
		int total = 0;
		for (int i = 0; i < InstSize; i++)
			total += statInst[i];
		printStatLine(String.format("전체명령문실행회수 \t%d회\n", total));
		for (int i = 0; i < InstSize; i++)
			if (statInst[i]> 0) {
				printStatLine(String.format("[%s]\t%d회\n",  names[i], statInst[i]));
			}
		printStatLine("\n\n============= Instruction Statistics (II) ================\n\n");
		
		int n = statInst[InstType.loadi.ordinal()]+statInst[InstType.loadf.ordinal()];
		printStatLine(String.format("로드[loadi, loadf] %d\n", n));
		
		n = statInst[InstType.loadai.ordinal()]+statInst[InstType.loadaf.ordinal()]+statInst[InstType.getaddr.ordinal()];
		printStatLine(String.format("주소 로드[loadai, loadaf, getaddr] %d\n", n));
		
		n = statInst[InstType.storei.ordinal()]+statInst[InstType.storef.ordinal()];
		printStatLine(String.format("저장 [storei, storef] %d\n", n));
		
		n = statInst[InstType.loadai.ordinal()]+statInst[InstType.loadaf.ordinal()];
		printStatLine(String.format("주소 저장[storeai, storeaf] %d\n", n));
		
		n = statInst[InstType.ifi.ordinal()]+statInst[InstType.iff.ordinal()]+statInst[InstType.gotoL.ordinal()];
		printStatLine(String.format("분기[ifi, iff, gotoL] %d\n", n));
		
		n = statInst[InstType.initi.ordinal()]+statInst[InstType.initf.ordinal()];
		n += statInst[InstType.arri.ordinal()] + statInst[InstType.arrf.ordinal()];
		printStatLine(String.format("초기화[initi, initf, arri, arrf] %d\n", n));
		
		n = statInst[InstType.cvti2f.ordinal()] + statInst[InstType.cvtf2i.ordinal()];
		printStatLine(String.format("변환[cvti2f, cvtf2i] %d\n", n));
		
		n = statInst[InstType.asgni.ordinal()] + statInst[InstType.asgnf.ordinal()];
		n += statInst[InstType.arithi.ordinal()] + statInst[InstType.arithf.ordinal()];
		printStatLine(String.format("계산[asgni, asgnf, arithi, arithf] %d\n", n));
		return sb.toString();
	}
	static void initStat() {
		for (int i = 0; i < InstSize; i++)
				statInst[i]= 0;
	}
}
//static String[] names = {"none     ", "asgni   ", "asgnf   ", 
//		 "initi   ", "initf   ", "arithi  ", "arithf  ", "storei  ", "storef  ",
//	      "loadi    ", "loadf   ", "ifi     ", "iff     ",
//	      "printi  ", "printf  ", "inputi  ", "inputf  ", 
//	      "cvtf2i  ", "cvti2f  ", "loadai  ", "loadaf  ", 
//	      "storeai ", "storeaf ", "arri   ", "arrf   ",
//	      "getaddr ", "prints  ",   "gotoL   ", "end    "};