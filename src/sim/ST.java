package sim;
import java.util.ArrayList;
import java.util.HashMap;
class ST {
	SimEngine engine = (SimEngine)(Sim.GetInstance());
	
	private ArrayList<SymUnit> SymTab = new ArrayList<>();
	private double[] Memory = new double[32];
	private HashMap<Integer, Integer> num2pc = new HashMap<>();
	static private ST st = null;
	static ST GetST() {
		if (st != null) return st;
		st = new ST();
		return st;			
	}
	double[] getMemory() {
		double[] arr = new double[top];
		for (int i = 0; i < top; i++)
			arr[i] = Memory[i];
		return arr;
	}
	void parserReset() {
	      top = 0;
	      SymTab.clear();
	      num2pc.clear();
	      reset();
	}
	void reset() {
		for (int i = 0; i< rreg.length; i++)
			rreg[i] = 0;
		for (int i = 0; i< freg.length; i++)
			freg[i] = 0;
		for (int i = 0; i< Memory.length; i++)//top = 0;
			Memory[i] = -1.0;
	}
	SymUnit findSym(String id) {
		for (SymUnit s : SymTab) {
			if (s.name.equals(id))
				return s;
		}
		return null;
	}
	private SymUnit addr2Sym(int a) {
		if (a >= top || a < 0) return null;		
		for (SymUnit s : SymTab) {
			if (a < s.addr+s.size)
				return s;
		}
		return null;
	}
	int getLabel(int num)
	{
		if (num2pc.get(num) == null)
		{
			engine.setErrorCode(Sim.WrongLabelNum, "L"+num);
			return -1;
		}
		int pc = num2pc.get(num);
		return pc;
	}
	void addLabel(int label, int pc)
	{
		num2pc.put(label, pc);
	}
	int top = 0;
	SymUnit addId(String id, boolean bInt)
	{
		if (findSym(id) == null) {
			SymUnit sym = new SymUnit();
			sym.name = id;
			Memory[top] =-1.0;
			sym.addr = top++;
			sym.bInt = bInt;
			sym.size = 1;
			SymTab.add(sym);  // 심볼테이블에 추가
			return sym;
		} else {
			return findSym(id);
		}
	}
	SymUnit addArr(String id, boolean bInt, int size) {
		if (findSym(id) == null) {
			SymUnit sym = new SymUnit();
			sym.name = id;
			sym.addr = top;
			sym.size = size;
			for (int i=0;i<size;i++) {
				Memory[top++] =-1.0;
			}
			sym.bInt = bInt;
			SymTab.add(sym);
			return sym;
		} else {
			return findSym(id);
		}
		
	}
	int getAddr(String id) {
		SymUnit sym = findSym(id);
		if (sym == null) {
			engine.setErrorCode(Sim.UndefinedIdentifier, id);
			return -1; 
		}
		return sym.addr;
	}
//	int getaddri(int addr) {
//		SymUnit sym = addr2Sym(addr);
//		if (sym == null) {
//			engine.setErrorCode(Sim.WrongAddress, "address : " + addr+"");
//			return -1; 
//		}
//		if (!sym.bInt) {
//			engine.setErrorCode(Sim.TypeMismatch, "address : " + addr+"");
//			return -1; 
//		}
//		return (int)Memory[addr];
//	}
	double getvaladdr(boolean bInt, int addr) {
		SymUnit sym = addr2Sym(addr);
		if (sym == null) {
			engine.setErrorCode(Sim.WrongAddress, "address : " + addr+"");
			return -1; 
		}
		if (bInt != sym.bInt) {
			engine.setErrorCode(Sim.TypeMismatch, "address : " + addr+"");
			return -1;
		}
		return Memory[addr];
	}
//	int getival(String id) {
//		SymUnit sym = findSym(id);
//		if (sym == null) {
//			engine.setErrorCode(Sim.UndefinedIdentifier, id);
//			return -1; 
//		} 
//		if (!sym.bInt) {
//			engine.setErrorCode(Sim.TypeMismatch, id);
//			return -1;
//		}
//		return (int)Memory[sym.addr];
//	}
	// loadi, loadf - 이름으로 값 접근
	double getval(boolean bInt, String id) {
		SymUnit sym = findSym(id);
		if (sym == null) {
			engine.setErrorCode(Sim.UndefinedIdentifier, id);
			return -1; 
		} else if (bInt != sym.bInt) {
			engine.setErrorCode(Sim.TypeMismatch, id);
			return -1; 
		}
		return Memory[sym.addr];
	}
	// storeai / storeaf 주소로 값 저장
	int setaddr(boolean bInt, int addr, int val, double dval) {  
		SymUnit sym = addr2Sym(addr);
		if (sym == null) {
			engine.setErrorCode(Sim.WrongAddress, addr+"");
			return -1;
		}
		if (sym.bInt != bInt) {
			engine.setErrorCode(Sim.TypeMismatch, addr+"");
			return -1;
		}
		if (bInt) Memory[addr] = val;
		else Memory[addr] = dval;
		return 0;
	}
	
	// storei, storef에서 이름에 값 저장
	int putval(String id, double val, boolean bint)  
	{
		SymUnit sym = findSym(id);
		if (sym == null) {
			engine.setErrorCode(Sim.UndefinedIdentifier, id);
			return -1;
		} else if (sym.bInt != bint) {
			engine.setErrorCode(Sim.TypeMismatch, id);
			return -1;
		}
		Memory[sym.addr] = val;
		return 0;
	}		
	int rreg[] = new int[9];
	double freg[] = new double[5];
	void setreg(boolean bInt, int rn, int ival, double dval) {
		try {
			if (bInt) rreg[rn] = ival;
			else {
			    freg[rn-10] = dval;
			} 
		} catch (ArrayIndexOutOfBoundsException e) {
			if ((bInt && rn >= 10) || (!bInt && (rn-10) < 0)) 
				engine.setErrorCode(Sim.TypeMismatch, "setreg r"+rn);
			else
				engine.setErrorCode(Sim.RegisterIndex, "setreg "+rn);
			return;
		}
	}
	int getregi(int rn) {
		try {
		if (rn < 0) return -9999;
		return rreg[rn];
		} catch (ArrayIndexOutOfBoundsException e) {
			engine.setErrorCode(Sim.RegisterIndex, "getregi r"+rn);
			return -9999;
		}
	}
	double getregf(int rn) {
		try {
		if (rn < 0) return -9999;
		return freg[rn-10];
		} catch (ArrayIndexOutOfBoundsException e) {
			engine.setErrorCode(Sim.RegisterIndex, "f"+(rn-10));
			return -9999; 
		}
	}
	
	Log log = engine.getLog();
	void printRegisters()
	{
		log.setLogBuf("REGS:"); 
		for (int i = 1; i < rreg.length; i++)
			log.setLogBuf(" " + rreg[i]); 
		log.setLogBuf("/"); 
		for (int i = 1; i < freg.length; i++)
			log.setLogBuf(" "+freg[i]); 
	}
	void printMemory()
	{
		log.setLogBuf("  MEMS:"); // 
		for (SymUnit s : SymTab) {
			log.setLogBuf(" "+s.name + "="); 
			if (s.size>1) log.setLogBuf("["); 
			for (int i = 0; i < s.size; i++) {
				int index = s.addr+i;
				if (Memory[index] == -1)
					log.setLogBuf(" _u"); 
				else if (s.bInt) {
					log.setLogBuf(" "+ (int)Memory[index]); 
				} else
					log.setLogBuf(" "+ Memory[index]); 
			}
			if (s.size>1) log.setLogBuf("]"); 
		}
	}
	boolean[] getMemoryTypeArr()
	{
		boolean bIntArr[] = new boolean[top];
		for (SymUnit s : SymTab) {
			for (int i = 0; i < s.size; i++) {
				int index = s.addr+i;
				if (s.bInt) bIntArr[index] = true;
				else bIntArr[index] = false;
			}
		}
		return bIntArr;
	}
	String[] getMemoryTipArr()
	{
		String idArr[] = new String[top];
		for (SymUnit s : SymTab) {
			for (int i = 0; i < s.size; i++) {
				int index = s.addr+i;
				if (s.bInt) idArr[index] = "int ";
				else idArr[index] = "float ";
				idArr[index] += s.name;
				if (s.size > 1) idArr[index] += "["+i+"]";
			}
		}
		return idArr;
	}	
}