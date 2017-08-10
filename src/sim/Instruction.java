package sim;
import sim.Sim.InstType;
public class Instruction {
//	enum InstType {none, asgni, asgnf, initi, initf, arithi, arithf, storei, storef,
//		loadi, loadf, ifi, iff, printi, printf, 
//		inputi, inputf, cvtf2i, cvti2f, prints, getaddr, loada, storeai, storeaf, arri, arrf, end};
		
	boolean bInt = false;
	public int lineno;
	public int srcln;
	public Instruction(InstType ty, String reg1, String reg2)
	{
		type = ty;
		regs[0] = reg1;
		regs[1] = reg2;
	}
	public Instruction(InstType ty, String m)
	{
		type = ty;
		switch (ty) {
		case prints:msg = m; break;
		case gotoL:gotoL = m; break;
		default:
			regs[0] = m; break;
		}
	}
	public Instruction(InstType ty)
	{
		type = ty;
	}
	public void setRegs(String reg1, String reg2)
	{
		regs[0] = reg1;
		regs[1] = reg2;
	}
	@Override
	public String toString() {
		String result = "["+lineno+"] "+InstData.names[type.ordinal()];
		result += "\nregisters : ";
		if (regs[0] != null) result += " [0]=" + regs[0];
		else result += " null, ";
		if (regs[1] != null) result += ", [1]=" + regs[1];
		else result += " null";
		//if (regs[2] != null) result += regs[2];
		result += "\niliteral : " + ival;
		result += "\nfliteral : " + fval;
		result += "\nlabel : ";
		if (label != null) result += label;
		else result += " null, ";
		result += "\ngotoL : ";
		if (gotoL != null) result += gotoL;
		else result += " null, ";
		result += "\n";
		return result;
	}
	public String regs[] = new String[2];
	public int ival;
	public float fval;
	public String lvalue, id, msg, optr, label=null, gotoL=null;
	public String line;
	public InstType type;
	public int registers[] = new int[3];
}