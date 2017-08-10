grammar PAGrammar; 
@parser::header {
	// �ļ� Ŭ���� ��� 
	import java.util.HashMap;
	import java.util.ArrayList;
}

@parser::members {
	// �ļ� Ŭ���� ��� �����
	// ������ �ڵ��
	ArrayList<Instruction> lines = new ArrayList<Instruction>();
	// ���� �̸� �����
	static HashMap<String, Integer> labels = new HashMap<>();
	// �� �� �ڵ带 �ִ� ����
	String aLine = null;
	int lineno = 0;
	String curLabel;
}

prog:	'program' NEWLINE
	( stmts ) 'end' 
	{ 
		lines.add(new Instruction(InstData.InstType.end)); 
	}
	;
stmts 
	: 
	( 
		('\t')? LINE_COMMENT? NEWLINE 
		|
		{curLabel=null; int srcln = 0; } 
		( t=LABEL { curLabel = $t.text; } ':' )? '\t'  
		stmt LINE_COMMENT? NEWLINE
		{
			Instruction result = $stmt.result;
			lines.add(result);
			result.label = curLabel;
			labels.put(curLabel, lineno);
			result.lineno = lineno++; 
			result.srcln = $NEWLINE.getLine();
		}
	)+ 
	;
stmt returns [ Instruction result ]
	: 
	arithstmt { $result = $arithstmt.result; }
	| iostmt { $result = $iostmt.result; }
	| ifstmt { $result = $ifstmt.result; }
	| arrstmt { $result = $arrstmt.result; }
	| asgnstmt { $result = $asgnstmt.result; }
	| loadstorestmt { $result = $loadstorestmt.result; }
	| 'goto' t=LABEL
	{
		$result = new Instruction(InstData.InstType.gotoL, $t.text);
	}
	| t = RREG ':=' v=FREG
	{
		$result = new Instruction(InstData.InstType.cvtf2i, $t.text, $v.text);
	}
	| t = FREG ':=' v=RREG
	{
		$result = new Instruction(InstData.InstType.cvti2f, $t.text, $v.text);
	}
	;

arithstmt returns  [Instruction result] 
	:	
		v=(RREG | FREG) ':=' expr
		{
			// expr���� arithi, arithf, ifi, iff �� �ϳ��� ��ɹ� �����Ͽ� result�� ������
			$result = $expr.result;  // 44����
			if ($result.type == InstData.InstType.arithi ||
			    $result.type == InstData.InstType.arithf) {
				// Instruction ��ü b�� instType�� �ǿ����� �ΰ��� regs[2]�� �־� ������
				$result.lvalue = $v.text;
			} else {
				System.out.println("\n�������� ������ �� ����. ��ġ : " + $v.getLine());
				throw new RuntimeException();
			}
		}
		;

ifstmt returns  [Instruction result] 
	:	
		'if' '(' expr ')' GOTO t=LABEL
		{ 
			$result = $expr.result;
			if ($result.type == InstData.InstType.arithi ||
			    $result.type == InstData.InstType.arithf) {
				System.out.println("\n������ ���Ǻο� �� �� ����. ��ġ : " + $t.getLine());
				throw new RuntimeException();
			}
			$expr.result.gotoL = $t.text;
		}
		;	
arrstmt returns  [Instruction result] 
	:	t=('int' | 'float') v=ID '[' c=INT ']' 
		{
			if ($t.text.equals("int"))
				$result = new Instruction(InstData.InstType.arri);
			else 
				$result = new Instruction(InstData.InstType.arrf);
			$result.id = $v.text;
			$result.ival = Integer.parseInt($c.text);
		}
		| a=RREG ':=' '&' v=ID  		// getaddr
		{
			$result = new Instruction(InstData.InstType.getaddr, $a.text);
			$result.id = $v.text;
		}
		;

loadstorestmt returns  [Instruction result] 
		: a = (RREG | FREG) ':=' t=ID
		{
			if ($a.getType() == RREG)
				$result = new Instruction(InstData.InstType.loadi, $a.text);
			else 
				$result = new Instruction(InstData.InstType.loadf, $a.text);
			$result.id = $t.text;
		}
		| t = ID ':=' v=(RREG | FREG)
		{
			if ($v.getType() == RREG)
				$result = new Instruction(InstData.InstType.storei, $v.text);
			else 
				$result = new Instruction(InstData.InstType.storef, $v.text);
			$result.id = $t.text;
		}
		| '*' a=RREG ':=' b=RREG		// storeai
		{
			$result = new Instruction(InstData.InstType.storeai, $a.text, $b.text);
		}
		| '*' a=RREG ':=' b=FREG		// storeaf
		{
			$result = new Instruction(InstData.InstType.storeaf, $a.text, $b.text);
		}
		| a=RREG ':=' '*' b=RREG		// storeai
		{
			$result = new Instruction(InstData.InstType.loadai, $a.text, $b.text);
		}
		| a=FREG ':=' '*' b=RREG		// storeaf
		{
			$result = new Instruction(InstData.InstType.loadaf, $a.text, $b.text);
		}
		;

asgnstmt returns  [Instruction result] 
		: t = RREG ':=' v=(RREG | INT)
		{
			if ($v.getType() == RREG) {
				$result = new Instruction(InstData.InstType.asgni, $t.text, $v.text);
			} else {
				$result = new Instruction(InstData.InstType.asgni, $t.text, null);
				$result.ival = Integer.parseInt($v.text);
			}
		}
		| t = FREG ':=' v=(FREG | FLOAT)
		{
			if ($v.getType() == FREG) {
				$result = new Instruction(InstData.InstType.asgnf, $t.text, $v.text);
			} else {
				$result = new Instruction(InstData.InstType.asgnf, $t.text, null);
				$result.fval = Float.parseFloat($v.text);
			}
		}
		| t = ID ':=' v=(INT|FLOAT)
		{
			if ($v.getType() == INT) {
				$result = new Instruction(InstData.InstType.initi);
				$result.ival = Integer.parseInt($v.text);
			}
			else {
				$result = new Instruction(InstData.InstType.initf);
				$result.fval = Float.parseFloat($v.text);
			}
			$result.id = $t.text;
		}
	;

iostmt returns  [Instruction result] 
	:
		'print' t=STRING_LITERAL
		{
			$result = new Instruction(InstData.InstType.prints);
			$result.msg = $t.text;
		}
		| 'print' t=(RREG | FREG)
		{
			if ($t.getType() == RREG)
				$result = new Instruction(InstData.InstType.printi, $t.text);
			else 
				$result = new Instruction(InstData.InstType.printf, $t.text);
		}
		| 'input' t = (RREG | FREG)
		{
			if ($t.getType() == RREG)
				$result = new Instruction(InstData.InstType.inputi, $t.text);
			else
				$result = new Instruction(InstData.InstType.inputf, $t.text);
		}
	;
expr returns [Instruction result] 
	: 
	{ 
		String opnd2=null; 
	}
	( 
		a = RREG e=('+'|'-'|'*'|'/'|'%') b = ( RREG | INT )
		{
			$result = new Instruction(InstData.InstType.arithi);
		  	if ($b.getType() == RREG)
				opnd2 = $b.text;			  
		  	else {
		  		opnd2 = null;
				$result.ival = Integer.parseInt($b.text);
			}
		}
		| a=FREG e=('+'|'-'|'*'|'/'|'%') b=(FREG | FLOAT)
		{
			$result = new Instruction(InstData.InstType.arithf);
		  	if ($b.getType() == FREG)
				opnd2 = $b.text;			  
		  	else {
			  	opnd2 = null;
				$result.fval = Float.parseFloat($b.text);
			}
		}
		| a = RREG e=('<'|'=='|'<='|'>='|'!='|'>') b=( RREG | INT)
		{
			$result = new Instruction(InstData.InstType.ifi);
		  	if ($b.getType() == RREG)
				opnd2 = $b.text;			  
			else {
			  	  opnd2 = null;
				  $result.ival = Integer.parseInt($b.text);
			}
		}
		| a=FREG e=('<'|'=='|'<='|'>='|'!='|'>')  b=(FREG | FLOAT)
		{
			$result = new Instruction(InstData.InstType.iff);
		  	if ($b.getType() == RREG)
				opnd2 = $b.text;			  
		  	else {
			  	opnd2 = null;
				$result.fval = Float.parseFloat($b.text);
			}
		}
	) 
	{ 
		$result.optr = $e.text;
		$result.setRegs($a.text, opnd2);
	}
	; 
reg returns [String str]
	: a = (RREG | FREG)
	{ $str = $a.text; }
	;
factr returns [String str]
	: a = (RREG | INT)
	{ $str = $a.text; }
	;
factf returns [String str]
	: a = (FREG | FLOAT)
	{ $str = $a.text; }
	;
NEWLINE : [\n\r]+ ;
RREG : 'r'[0-8] ;
FREG : 'f'[0-4];
FLOAT: [0-9]+'.'[0-9]*;
INT : [0-9]+  ;
ID : [a-z];
LABEL: 'L'[1-9];
GOTO: 'goto' ;
WS : (' ' |'\t')+ {skip();};
STRING_LITERAL
    :  '"' ( ESCAPE_SEQUENCE | ~('\\'|'"') )* '"'
    ;

fragment
ESCAPE_SEQUENCE
    :   '\\' ('b'|'t'|'n'|'f'|'r'|'\"'|'\''|'\\')
    ;
LINE_COMMENT
    : '--' ~('\n'|'\r')*
    {   
        skip();
    }
    ;