package sim;
public class SymUnit {
	String name = null;
	int addr;
	boolean bInt;
	int size;
	public String toString() {
		String out = name;
		if (size > 1) out += "["+size+"]";
		out += addr+"번지, ";
		if (bInt) out += "int";
		else out += "float";
		return out;
	}
}
