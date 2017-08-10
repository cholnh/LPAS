// Generated from PAGrammar.g4 by ANTLR 4.5.3

	// 파서 클래스 헤더 
	package parser;
	import java.util.HashMap;
	import java.util.ArrayList;
	import sim.Instruction;
	import sim.Sim.InstType;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class PAGrammarParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, NEWLINE=26, RREG=27, FREG=28, FLOAT=29, INT=30, LABEL=31, GOTO=32, 
		ID=33, WS=34, STRING_LITERAL=35, LINE_COMMENT=36;
	public static final int
		RULE_prog = 0, RULE_stmts = 1, RULE_stmt = 2, RULE_arithstmt = 3, RULE_ifstmt = 4, 
		RULE_arrstmt = 5, RULE_loadstorestmt = 6, RULE_asgnstmt = 7, RULE_iostmt = 8, 
		RULE_aexpr = 9, RULE_bexpr = 10, RULE_reg = 11, RULE_factr = 12, RULE_factf = 13;
	public static final String[] ruleNames = {
		"prog", "stmts", "stmt", "arithstmt", "ifstmt", "arrstmt", "loadstorestmt", 
		"asgnstmt", "iostmt", "aexpr", "bexpr", "reg", "factr", "factf"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'program'", "'end'", "':'", "':='", "'if'", "'('", "')'", "'int'", 
		"'float'", "'['", "']'", "'&'", "'*'", "'print'", "'input'", "'+'", "'-'", 
		"'/'", "'%'", "'<'", "'=='", "'<='", "'>='", "'!='", "'>'", null, null, 
		null, null, null, null, "'goto'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, "NEWLINE", "RREG", "FREG", "FLOAT", "INT", "LABEL", "GOTO", 
		"ID", "WS", "STRING_LITERAL", "LINE_COMMENT"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "PAGrammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


		// 파서 클래스 멤버 선언부
		// 생성된 코드부
		public ArrayList<Instruction> lines = new ArrayList<Instruction>();
		// 변수 이름 선언부
		public static HashMap<String, Integer> labels = new HashMap<>();
		// 한 줄 코드를 넣는 변수
		String aLine = null;
		int lineno = 0;
		String curLabel;
		public static InstType curType=InstType.none;

	public PAGrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgContext extends ParserRuleContext {
		public Token x;
		public List<TerminalNode> NEWLINE() { return getTokens(PAGrammarParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(PAGrammarParser.NEWLINE, i);
		}
		public StmtsContext stmts() {
			return getRuleContext(StmtsContext.class,0);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PAGrammarListener ) ((PAGrammarListener)listener).enterProg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PAGrammarListener ) ((PAGrammarListener)listener).exitProg(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(31);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE) {
				{
				{
				setState(28);
				match(NEWLINE);
				}
				}
				setState(33);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(34);
			match(T__0);
			setState(35);
			match(NEWLINE);
			{
			setState(36);
			stmts();
			}
			setState(37);
			((ProgContext)_localctx).x = match(T__1);
			 
					Instruction result = new Instruction(InstType.endp);
					result.lineno = lineno;
					result.srcln = ((ProgContext)_localctx).x.getLine();
					lines.add(result);
				
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StmtsContext extends ParserRuleContext {
		public Token NEWLINE;
		public Token t;
		public StmtContext stmt;
		public List<TerminalNode> NEWLINE() { return getTokens(PAGrammarParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(PAGrammarParser.NEWLINE, i);
		}
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public List<TerminalNode> LINE_COMMENT() { return getTokens(PAGrammarParser.LINE_COMMENT); }
		public TerminalNode LINE_COMMENT(int i) {
			return getToken(PAGrammarParser.LINE_COMMENT, i);
		}
		public List<TerminalNode> LABEL() { return getTokens(PAGrammarParser.LABEL); }
		public TerminalNode LABEL(int i) {
			return getToken(PAGrammarParser.LABEL, i);
		}
		public StmtsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmts; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PAGrammarListener ) ((PAGrammarListener)listener).enterStmts(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PAGrammarListener ) ((PAGrammarListener)listener).exitStmts(this);
		}
	}

	public final StmtsContext stmts() throws RecognitionException {
		StmtsContext _localctx = new StmtsContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_stmts);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(57); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(57);
				switch (_input.LA(1)) {
				case NEWLINE:
				case LINE_COMMENT:
					{
					setState(41);
					_la = _input.LA(1);
					if (_la==LINE_COMMENT) {
						{
						setState(40);
						match(LINE_COMMENT);
						}
					}

					setState(43);
					((StmtsContext)_localctx).NEWLINE = match(NEWLINE);
					}
					break;
				case T__4:
				case T__7:
				case T__8:
				case T__12:
				case T__13:
				case T__14:
				case RREG:
				case FREG:
				case LABEL:
				case GOTO:
				case ID:
					{
					curLabel=null; int srcln = 0; 
					setState(48);
					_la = _input.LA(1);
					if (_la==LABEL) {
						{
						setState(45);
						((StmtsContext)_localctx).t = match(LABEL);
						 curLabel = (((StmtsContext)_localctx).t!=null?((StmtsContext)_localctx).t.getText():null); 
						setState(47);
						match(T__2);
						}
					}

					setState(50);
					((StmtsContext)_localctx).stmt = stmt();
					setState(52);
					_la = _input.LA(1);
					if (_la==LINE_COMMENT) {
						{
						setState(51);
						match(LINE_COMMENT);
						}
					}

					setState(54);
					((StmtsContext)_localctx).NEWLINE = match(NEWLINE);

								Instruction result = ((StmtsContext)_localctx).stmt.result;
								if (result == null) break;
								lines.add(result);
								result.label = curLabel;
								labels.put(curLabel, lineno);
								result.lineno = lineno++; 
								result.srcln = ((StmtsContext)_localctx).NEWLINE.getLine();
							
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(59); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__7) | (1L << T__8) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << NEWLINE) | (1L << RREG) | (1L << FREG) | (1L << LABEL) | (1L << GOTO) | (1L << ID) | (1L << LINE_COMMENT))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StmtContext extends ParserRuleContext {
		public Instruction result;
		public ArithstmtContext arithstmt;
		public IostmtContext iostmt;
		public IfstmtContext ifstmt;
		public ArrstmtContext arrstmt;
		public AsgnstmtContext asgnstmt;
		public LoadstorestmtContext loadstorestmt;
		public Token t;
		public Token v;
		public ArithstmtContext arithstmt() {
			return getRuleContext(ArithstmtContext.class,0);
		}
		public IostmtContext iostmt() {
			return getRuleContext(IostmtContext.class,0);
		}
		public IfstmtContext ifstmt() {
			return getRuleContext(IfstmtContext.class,0);
		}
		public ArrstmtContext arrstmt() {
			return getRuleContext(ArrstmtContext.class,0);
		}
		public AsgnstmtContext asgnstmt() {
			return getRuleContext(AsgnstmtContext.class,0);
		}
		public LoadstorestmtContext loadstorestmt() {
			return getRuleContext(LoadstorestmtContext.class,0);
		}
		public TerminalNode LABEL() { return getToken(PAGrammarParser.LABEL, 0); }
		public TerminalNode RREG() { return getToken(PAGrammarParser.RREG, 0); }
		public TerminalNode FREG() { return getToken(PAGrammarParser.FREG, 0); }
		public StmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PAGrammarListener ) ((PAGrammarListener)listener).enterStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PAGrammarListener ) ((PAGrammarListener)listener).exitStmt(this);
		}
	}

	public final StmtContext stmt() throws RecognitionException {
		StmtContext _localctx = new StmtContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_stmt);
		try {
			setState(90);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(61);
				((StmtContext)_localctx).arithstmt = arithstmt();
				 ((StmtContext)_localctx).result =  ((StmtContext)_localctx).arithstmt.result; 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(64);
				((StmtContext)_localctx).iostmt = iostmt();
				 ((StmtContext)_localctx).result =  ((StmtContext)_localctx).iostmt.result; 
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(67);
				((StmtContext)_localctx).ifstmt = ifstmt();
				 ((StmtContext)_localctx).result =  ((StmtContext)_localctx).ifstmt.result; 
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(70);
				((StmtContext)_localctx).arrstmt = arrstmt();
				 ((StmtContext)_localctx).result =  ((StmtContext)_localctx).arrstmt.result; 
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(73);
				((StmtContext)_localctx).asgnstmt = asgnstmt();
				 ((StmtContext)_localctx).result =  ((StmtContext)_localctx).asgnstmt.result; 
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(76);
				((StmtContext)_localctx).loadstorestmt = loadstorestmt();
				 ((StmtContext)_localctx).result =  ((StmtContext)_localctx).loadstorestmt.result; 
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(79);
				match(GOTO);
				setState(80);
				((StmtContext)_localctx).t = match(LABEL);

						curType = InstType.gotoL;
						((StmtContext)_localctx).result =  new Instruction(InstType.gotoL, (((StmtContext)_localctx).t!=null?((StmtContext)_localctx).t.getText():null));
					
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(82);
				((StmtContext)_localctx).t = match(RREG);
				setState(83);
				match(T__3);
				setState(84);
				((StmtContext)_localctx).v = match(FREG);

						curType = InstType.cvtf2i;
						((StmtContext)_localctx).result =  new Instruction(InstType.cvtf2i, (((StmtContext)_localctx).t!=null?((StmtContext)_localctx).t.getText():null), (((StmtContext)_localctx).v!=null?((StmtContext)_localctx).v.getText():null));
					
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(86);
				((StmtContext)_localctx).t = match(FREG);
				setState(87);
				match(T__3);
				setState(88);
				((StmtContext)_localctx).v = match(RREG);

						curType = InstType.cvti2f;
						((StmtContext)_localctx).result =  new Instruction(InstType.cvti2f, (((StmtContext)_localctx).t!=null?((StmtContext)_localctx).t.getText():null), (((StmtContext)_localctx).v!=null?((StmtContext)_localctx).v.getText():null));
					
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArithstmtContext extends ParserRuleContext {
		public Instruction result;
		public Token v;
		public AexprContext aexpr;
		public AexprContext aexpr() {
			return getRuleContext(AexprContext.class,0);
		}
		public TerminalNode RREG() { return getToken(PAGrammarParser.RREG, 0); }
		public TerminalNode FREG() { return getToken(PAGrammarParser.FREG, 0); }
		public ArithstmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arithstmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PAGrammarListener ) ((PAGrammarListener)listener).enterArithstmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PAGrammarListener ) ((PAGrammarListener)listener).exitArithstmt(this);
		}
	}

	public final ArithstmtContext arithstmt() throws RecognitionException {
		ArithstmtContext _localctx = new ArithstmtContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_arithstmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92);
			((ArithstmtContext)_localctx).v = _input.LT(1);
			_la = _input.LA(1);
			if ( !(_la==RREG || _la==FREG) ) {
				((ArithstmtContext)_localctx).v = (Token)_errHandler.recoverInline(this);
			} else {
				consume();
			}
			setState(93);
			match(T__3);
			setState(94);
			((ArithstmtContext)_localctx).aexpr = aexpr();

						// expr에서 arithi, arithf, ifi, iff 중 하나의 명령문 생성하여 result에 돌려줌
						((ArithstmtContext)_localctx).result =  ((ArithstmtContext)_localctx).aexpr.result;  // 44번줄
						if (_localctx.result.type == InstType.arithi ||
						    _localctx.result.type == InstType.arithf) {
							// Instruction 객체 b는 instType과 피연산자 두개를 regs[2]에 넣어 돌려줌
							_localctx.result.lvalue = (((ArithstmtContext)_localctx).v!=null?((ArithstmtContext)_localctx).v.getText():null);
						} 
					
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IfstmtContext extends ParserRuleContext {
		public Instruction result;
		public BexprContext bexpr;
		public Token t;
		public BexprContext bexpr() {
			return getRuleContext(BexprContext.class,0);
		}
		public TerminalNode GOTO() { return getToken(PAGrammarParser.GOTO, 0); }
		public TerminalNode LABEL() { return getToken(PAGrammarParser.LABEL, 0); }
		public IfstmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifstmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PAGrammarListener ) ((PAGrammarListener)listener).enterIfstmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PAGrammarListener ) ((PAGrammarListener)listener).exitIfstmt(this);
		}
	}

	public final IfstmtContext ifstmt() throws RecognitionException {
		IfstmtContext _localctx = new IfstmtContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_ifstmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(97);
			match(T__4);
			setState(98);
			match(T__5);
			setState(99);
			((IfstmtContext)_localctx).bexpr = bexpr();
			setState(100);
			match(T__6);
			setState(101);
			match(GOTO);
			setState(102);
			((IfstmtContext)_localctx).t = match(LABEL);
			 
						((IfstmtContext)_localctx).result =  ((IfstmtContext)_localctx).bexpr.result;
						((IfstmtContext)_localctx).bexpr.result.gotoL = (((IfstmtContext)_localctx).t!=null?((IfstmtContext)_localctx).t.getText():null);
					
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArrstmtContext extends ParserRuleContext {
		public Instruction result;
		public Token t;
		public Token v;
		public Token c;
		public Token a;
		public TerminalNode ID() { return getToken(PAGrammarParser.ID, 0); }
		public TerminalNode INT() { return getToken(PAGrammarParser.INT, 0); }
		public TerminalNode RREG() { return getToken(PAGrammarParser.RREG, 0); }
		public ArrstmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrstmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PAGrammarListener ) ((PAGrammarListener)listener).enterArrstmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PAGrammarListener ) ((PAGrammarListener)listener).exitArrstmt(this);
		}
	}

	public final ArrstmtContext arrstmt() throws RecognitionException {
		ArrstmtContext _localctx = new ArrstmtContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_arrstmt);
		int _la;
		try {
			setState(116);
			switch (_input.LA(1)) {
			case T__7:
			case T__8:
				enterOuterAlt(_localctx, 1);
				{
				setState(105);
				((ArrstmtContext)_localctx).t = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==T__7 || _la==T__8) ) {
					((ArrstmtContext)_localctx).t = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(106);
				((ArrstmtContext)_localctx).v = match(ID);
				setState(107);
				match(T__9);
				setState(108);
				((ArrstmtContext)_localctx).c = match(INT);
				setState(109);
				match(T__10);

							if ((((ArrstmtContext)_localctx).t!=null?((ArrstmtContext)_localctx).t.getText():null).equals("int")) {
								curType = InstType.arri;
								((ArrstmtContext)_localctx).result =  new Instruction(InstType.arri);
							} else {
								curType = InstType.arrf;
								((ArrstmtContext)_localctx).result =  new Instruction(InstType.arrf);
							}
							_localctx.result.id = (((ArrstmtContext)_localctx).v!=null?((ArrstmtContext)_localctx).v.getText():null);
							_localctx.result.ival = Integer.parseInt((((ArrstmtContext)_localctx).c!=null?((ArrstmtContext)_localctx).c.getText():null));
						
				}
				break;
			case RREG:
				enterOuterAlt(_localctx, 2);
				{
				setState(111);
				((ArrstmtContext)_localctx).a = match(RREG);
				setState(112);
				match(T__3);
				setState(113);
				match(T__11);
				setState(114);
				((ArrstmtContext)_localctx).v = match(ID);

							curType = InstType.getaddr;
							((ArrstmtContext)_localctx).result =  new Instruction(InstType.getaddr, (((ArrstmtContext)_localctx).a!=null?((ArrstmtContext)_localctx).a.getText():null));
							_localctx.result.id = (((ArrstmtContext)_localctx).v!=null?((ArrstmtContext)_localctx).v.getText():null);
						
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LoadstorestmtContext extends ParserRuleContext {
		public Instruction result;
		public Token a;
		public Token t;
		public Token v;
		public Token b;
		public TerminalNode ID() { return getToken(PAGrammarParser.ID, 0); }
		public List<TerminalNode> RREG() { return getTokens(PAGrammarParser.RREG); }
		public TerminalNode RREG(int i) {
			return getToken(PAGrammarParser.RREG, i);
		}
		public TerminalNode FREG() { return getToken(PAGrammarParser.FREG, 0); }
		public LoadstorestmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_loadstorestmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PAGrammarListener ) ((PAGrammarListener)listener).enterLoadstorestmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PAGrammarListener ) ((PAGrammarListener)listener).exitLoadstorestmt(this);
		}
	}

	public final LoadstorestmtContext loadstorestmt() throws RecognitionException {
		LoadstorestmtContext _localctx = new LoadstorestmtContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_loadstorestmt);
		int _la;
		try {
			setState(146);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(118);
				((LoadstorestmtContext)_localctx).a = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==RREG || _la==FREG) ) {
					((LoadstorestmtContext)_localctx).a = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(119);
				match(T__3);
				setState(120);
				((LoadstorestmtContext)_localctx).t = match(ID);

							if (((LoadstorestmtContext)_localctx).a.getType() == RREG) {
								curType = InstType.loadi;
								((LoadstorestmtContext)_localctx).result =  new Instruction(InstType.loadi, (((LoadstorestmtContext)_localctx).a!=null?((LoadstorestmtContext)_localctx).a.getText():null));
							} else {
								curType = InstType.loadf; 
								((LoadstorestmtContext)_localctx).result =  new Instruction(InstType.loadf, (((LoadstorestmtContext)_localctx).a!=null?((LoadstorestmtContext)_localctx).a.getText():null));
							}
							_localctx.result.id = (((LoadstorestmtContext)_localctx).t!=null?((LoadstorestmtContext)_localctx).t.getText():null);
						
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(122);
				((LoadstorestmtContext)_localctx).t = match(ID);
				setState(123);
				match(T__3);
				setState(124);
				((LoadstorestmtContext)_localctx).v = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==RREG || _la==FREG) ) {
					((LoadstorestmtContext)_localctx).v = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}

							if (((LoadstorestmtContext)_localctx).v.getType() == RREG) {
								curType = InstType.storei;
								((LoadstorestmtContext)_localctx).result =  new Instruction(InstType.storei, (((LoadstorestmtContext)_localctx).v!=null?((LoadstorestmtContext)_localctx).v.getText():null));
							} else { 
								curType = InstType.storef;
								((LoadstorestmtContext)_localctx).result =  new Instruction(InstType.storef, (((LoadstorestmtContext)_localctx).v!=null?((LoadstorestmtContext)_localctx).v.getText():null));
							}
							_localctx.result.id = (((LoadstorestmtContext)_localctx).t!=null?((LoadstorestmtContext)_localctx).t.getText():null);
						
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(126);
				match(T__12);
				setState(127);
				((LoadstorestmtContext)_localctx).a = match(RREG);
				setState(128);
				match(T__3);
				setState(129);
				((LoadstorestmtContext)_localctx).b = match(RREG);

							curType = InstType.storeai;
							((LoadstorestmtContext)_localctx).result =  new Instruction(InstType.storeai, (((LoadstorestmtContext)_localctx).a!=null?((LoadstorestmtContext)_localctx).a.getText():null), (((LoadstorestmtContext)_localctx).b!=null?((LoadstorestmtContext)_localctx).b.getText():null));
						
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(131);
				match(T__12);
				setState(132);
				((LoadstorestmtContext)_localctx).a = match(RREG);
				setState(133);
				match(T__3);
				setState(134);
				((LoadstorestmtContext)_localctx).b = match(FREG);

							curType = InstType.storeaf;
							((LoadstorestmtContext)_localctx).result =  new Instruction(InstType.storeaf, (((LoadstorestmtContext)_localctx).a!=null?((LoadstorestmtContext)_localctx).a.getText():null), (((LoadstorestmtContext)_localctx).b!=null?((LoadstorestmtContext)_localctx).b.getText():null));
						
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(136);
				((LoadstorestmtContext)_localctx).a = match(RREG);
				setState(137);
				match(T__3);
				setState(138);
				match(T__12);
				setState(139);
				((LoadstorestmtContext)_localctx).b = match(RREG);

							curType = InstType.loadai;
							((LoadstorestmtContext)_localctx).result =  new Instruction(InstType.loadai, (((LoadstorestmtContext)_localctx).a!=null?((LoadstorestmtContext)_localctx).a.getText():null), (((LoadstorestmtContext)_localctx).b!=null?((LoadstorestmtContext)_localctx).b.getText():null));
						
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(141);
				((LoadstorestmtContext)_localctx).a = match(FREG);
				setState(142);
				match(T__3);
				setState(143);
				match(T__12);
				setState(144);
				((LoadstorestmtContext)_localctx).b = match(RREG);

							curType = InstType.loadaf;
							((LoadstorestmtContext)_localctx).result =  new Instruction(InstType.loadaf, (((LoadstorestmtContext)_localctx).a!=null?((LoadstorestmtContext)_localctx).a.getText():null), (((LoadstorestmtContext)_localctx).b!=null?((LoadstorestmtContext)_localctx).b.getText():null));
						
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AsgnstmtContext extends ParserRuleContext {
		public Instruction result;
		public Token t;
		public Token v;
		public List<TerminalNode> RREG() { return getTokens(PAGrammarParser.RREG); }
		public TerminalNode RREG(int i) {
			return getToken(PAGrammarParser.RREG, i);
		}
		public TerminalNode INT() { return getToken(PAGrammarParser.INT, 0); }
		public List<TerminalNode> FREG() { return getTokens(PAGrammarParser.FREG); }
		public TerminalNode FREG(int i) {
			return getToken(PAGrammarParser.FREG, i);
		}
		public TerminalNode FLOAT() { return getToken(PAGrammarParser.FLOAT, 0); }
		public TerminalNode ID() { return getToken(PAGrammarParser.ID, 0); }
		public AsgnstmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_asgnstmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PAGrammarListener ) ((PAGrammarListener)listener).enterAsgnstmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PAGrammarListener ) ((PAGrammarListener)listener).exitAsgnstmt(this);
		}
	}

	public final AsgnstmtContext asgnstmt() throws RecognitionException {
		AsgnstmtContext _localctx = new AsgnstmtContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_asgnstmt);
		int _la;
		try {
			setState(160);
			switch (_input.LA(1)) {
			case RREG:
				enterOuterAlt(_localctx, 1);
				{
				setState(148);
				((AsgnstmtContext)_localctx).t = match(RREG);
				setState(149);
				match(T__3);
				setState(150);
				((AsgnstmtContext)_localctx).v = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==RREG || _la==INT) ) {
					((AsgnstmtContext)_localctx).v = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}

							curType = InstType.asgni;
							if (((AsgnstmtContext)_localctx).v.getType() == RREG) {
								((AsgnstmtContext)_localctx).result =  new Instruction(InstType.asgni, (((AsgnstmtContext)_localctx).t!=null?((AsgnstmtContext)_localctx).t.getText():null), (((AsgnstmtContext)_localctx).v!=null?((AsgnstmtContext)_localctx).v.getText():null));
							} else {
								((AsgnstmtContext)_localctx).result =  new Instruction(InstType.asgni, (((AsgnstmtContext)_localctx).t!=null?((AsgnstmtContext)_localctx).t.getText():null), null);
								_localctx.result.ival = Integer.parseInt((((AsgnstmtContext)_localctx).v!=null?((AsgnstmtContext)_localctx).v.getText():null));
							}
						
				}
				break;
			case FREG:
				enterOuterAlt(_localctx, 2);
				{
				setState(152);
				((AsgnstmtContext)_localctx).t = match(FREG);
				setState(153);
				match(T__3);
				setState(154);
				((AsgnstmtContext)_localctx).v = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==FREG || _la==FLOAT) ) {
					((AsgnstmtContext)_localctx).v = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}

							curType = InstType.asgnf;
							if (((AsgnstmtContext)_localctx).v.getType() == FREG) {
								((AsgnstmtContext)_localctx).result =  new Instruction(InstType.asgnf, (((AsgnstmtContext)_localctx).t!=null?((AsgnstmtContext)_localctx).t.getText():null), (((AsgnstmtContext)_localctx).v!=null?((AsgnstmtContext)_localctx).v.getText():null));
							} else {
								((AsgnstmtContext)_localctx).result =  new Instruction(InstType.asgnf, (((AsgnstmtContext)_localctx).t!=null?((AsgnstmtContext)_localctx).t.getText():null), null);
								_localctx.result.fval = Float.parseFloat((((AsgnstmtContext)_localctx).v!=null?((AsgnstmtContext)_localctx).v.getText():null));
							}
						
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 3);
				{
				setState(156);
				((AsgnstmtContext)_localctx).t = match(ID);
				setState(157);
				match(T__3);
				setState(158);
				((AsgnstmtContext)_localctx).v = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==FLOAT || _la==INT) ) {
					((AsgnstmtContext)_localctx).v = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}

							if (((AsgnstmtContext)_localctx).v.getType() == INT) {
								curType = InstType.initi;
								((AsgnstmtContext)_localctx).result =  new Instruction(InstType.initi);
								_localctx.result.ival = Integer.parseInt((((AsgnstmtContext)_localctx).v!=null?((AsgnstmtContext)_localctx).v.getText():null));
							}
							else {
								curType = InstType.initf;
								((AsgnstmtContext)_localctx).result =  new Instruction(InstType.initf);
								_localctx.result.fval = Float.parseFloat((((AsgnstmtContext)_localctx).v!=null?((AsgnstmtContext)_localctx).v.getText():null));
							}
							_localctx.result.id = (((AsgnstmtContext)_localctx).t!=null?((AsgnstmtContext)_localctx).t.getText():null);
						
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IostmtContext extends ParserRuleContext {
		public Instruction result;
		public Token t;
		public Token x;
		public TerminalNode STRING_LITERAL() { return getToken(PAGrammarParser.STRING_LITERAL, 0); }
		public TerminalNode RREG() { return getToken(PAGrammarParser.RREG, 0); }
		public TerminalNode FREG() { return getToken(PAGrammarParser.FREG, 0); }
		public IostmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_iostmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PAGrammarListener ) ((PAGrammarListener)listener).enterIostmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PAGrammarListener ) ((PAGrammarListener)listener).exitIostmt(this);
		}
	}

	public final IostmtContext iostmt() throws RecognitionException {
		IostmtContext _localctx = new IostmtContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_iostmt);
		int _la;
		try {
			setState(175);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(162);
				match(T__13);
				setState(163);
				((IostmtContext)_localctx).t = match(STRING_LITERAL);

							curType = InstType.prints;
							((IostmtContext)_localctx).result =  new Instruction(InstType.prints);
							_localctx.result.msg = (((IostmtContext)_localctx).t!=null?((IostmtContext)_localctx).t.getText():null);
						
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(165);
				match(T__13);
				setState(166);
				((IostmtContext)_localctx).t = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==RREG || _la==FREG) ) {
					((IostmtContext)_localctx).t = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}

							if (((IostmtContext)_localctx).t.getType() == RREG) {
								curType = InstType.printi;
								((IostmtContext)_localctx).result =  new Instruction(InstType.printi, (((IostmtContext)_localctx).t!=null?((IostmtContext)_localctx).t.getText():null));
							} else { 
								((IostmtContext)_localctx).result =  new Instruction(InstType.printf, (((IostmtContext)_localctx).t!=null?((IostmtContext)_localctx).t.getText():null));
								curType = InstType.printf;
							}
						
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(168);
				match(T__14);
				setState(169);
				((IostmtContext)_localctx).t = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==RREG || _la==FREG) ) {
					((IostmtContext)_localctx).t = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}

							if (((IostmtContext)_localctx).t.getType() == RREG) {
								curType = InstType.inputi;
								((IostmtContext)_localctx).result =  new Instruction(InstType.inputi, (((IostmtContext)_localctx).t!=null?((IostmtContext)_localctx).t.getText():null));
							} else {
								curType = InstType.inputf;
								((IostmtContext)_localctx).result =  new Instruction(InstType.inputf, (((IostmtContext)_localctx).t!=null?((IostmtContext)_localctx).t.getText():null));
							}
						
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(171);
				match(T__14);
				setState(172);
				((IostmtContext)_localctx).x = match(STRING_LITERAL);
				setState(173);
				((IostmtContext)_localctx).t = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==RREG || _la==FREG) ) {
					((IostmtContext)_localctx).t = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}

							if (((IostmtContext)_localctx).t.getType() == RREG) {
								curType = InstType.inputi;
								((IostmtContext)_localctx).result =  new Instruction(InstType.inputi, (((IostmtContext)_localctx).t!=null?((IostmtContext)_localctx).t.getText():null));
							} else {
								curType = InstType.inputf;
								((IostmtContext)_localctx).result =  new Instruction(InstType.inputf, (((IostmtContext)_localctx).t!=null?((IostmtContext)_localctx).t.getText():null));
							}
							_localctx.result.msg = (((IostmtContext)_localctx).x!=null?((IostmtContext)_localctx).x.getText():null);
						
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AexprContext extends ParserRuleContext {
		public Instruction result;
		public Token a;
		public Token e;
		public Token b;
		public List<TerminalNode> RREG() { return getTokens(PAGrammarParser.RREG); }
		public TerminalNode RREG(int i) {
			return getToken(PAGrammarParser.RREG, i);
		}
		public List<TerminalNode> FREG() { return getTokens(PAGrammarParser.FREG); }
		public TerminalNode FREG(int i) {
			return getToken(PAGrammarParser.FREG, i);
		}
		public TerminalNode INT() { return getToken(PAGrammarParser.INT, 0); }
		public TerminalNode FLOAT() { return getToken(PAGrammarParser.FLOAT, 0); }
		public AexprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aexpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PAGrammarListener ) ((PAGrammarListener)listener).enterAexpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PAGrammarListener ) ((PAGrammarListener)listener).exitAexpr(this);
		}
	}

	public final AexprContext aexpr() throws RecognitionException {
		AexprContext _localctx = new AexprContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_aexpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			 
					((AexprContext)_localctx).result =  new Instruction(InstType.arithi);
					String opnd2=null; 
				
			setState(186);
			switch (_input.LA(1)) {
			case RREG:
				{
				setState(178);
				((AexprContext)_localctx).a = match(RREG);
				setState(179);
				((AexprContext)_localctx).e = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__12) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18))) != 0)) ) {
					((AexprContext)_localctx).e = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(180);
				((AexprContext)_localctx).b = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==RREG || _la==INT) ) {
					((AexprContext)_localctx).b = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}

							curType = InstType.arithi;
							((AexprContext)_localctx).result =  new Instruction(InstType.arithi);
						  	if (((AexprContext)_localctx).b.getType() == RREG)
								opnd2 = (((AexprContext)_localctx).b!=null?((AexprContext)_localctx).b.getText():null);			  
						  	else {
						  		opnd2 = null;
								_localctx.result.ival = Integer.parseInt((((AexprContext)_localctx).b!=null?((AexprContext)_localctx).b.getText():null));
							}
						
				}
				break;
			case FREG:
				{
				setState(182);
				((AexprContext)_localctx).a = match(FREG);
				setState(183);
				((AexprContext)_localctx).e = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__12) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18))) != 0)) ) {
					((AexprContext)_localctx).e = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(184);
				((AexprContext)_localctx).b = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==FREG || _la==FLOAT) ) {
					((AexprContext)_localctx).b = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}

							curType = InstType.arithf;
							((AexprContext)_localctx).result =  new Instruction(InstType.arithf);
						  	if (((AexprContext)_localctx).b.getType() == FREG)
								opnd2 = (((AexprContext)_localctx).b!=null?((AexprContext)_localctx).b.getText():null);			  
						  	else {
							  	opnd2 = null;
								_localctx.result.fval = Float.parseFloat((((AexprContext)_localctx).b!=null?((AexprContext)_localctx).b.getText():null));
							}
						
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			 
					_localctx.result.optr = (((AexprContext)_localctx).e!=null?((AexprContext)_localctx).e.getText():null);
					_localctx.result.setRegs((((AexprContext)_localctx).a!=null?((AexprContext)_localctx).a.getText():null), opnd2);
				
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BexprContext extends ParserRuleContext {
		public Instruction result;
		public Token a;
		public Token e;
		public Token b;
		public List<TerminalNode> RREG() { return getTokens(PAGrammarParser.RREG); }
		public TerminalNode RREG(int i) {
			return getToken(PAGrammarParser.RREG, i);
		}
		public List<TerminalNode> FREG() { return getTokens(PAGrammarParser.FREG); }
		public TerminalNode FREG(int i) {
			return getToken(PAGrammarParser.FREG, i);
		}
		public TerminalNode INT() { return getToken(PAGrammarParser.INT, 0); }
		public TerminalNode FLOAT() { return getToken(PAGrammarParser.FLOAT, 0); }
		public BexprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bexpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PAGrammarListener ) ((PAGrammarListener)listener).enterBexpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PAGrammarListener ) ((PAGrammarListener)listener).exitBexpr(this);
		}
	}

	public final BexprContext bexpr() throws RecognitionException {
		BexprContext _localctx = new BexprContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_bexpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			 
					((BexprContext)_localctx).result =  new Instruction(InstType.ifi);
					String opnd2=null; 
				
			setState(199);
			switch (_input.LA(1)) {
			case RREG:
				{
				setState(191);
				((BexprContext)_localctx).a = match(RREG);
				setState(192);
				((BexprContext)_localctx).e = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24))) != 0)) ) {
					((BexprContext)_localctx).e = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(193);
				((BexprContext)_localctx).b = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==RREG || _la==INT) ) {
					((BexprContext)_localctx).b = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}

							curType = InstType.ifi;
							((BexprContext)_localctx).result =  new Instruction(InstType.ifi);
						  	if (((BexprContext)_localctx).b.getType() == RREG)
								opnd2 = (((BexprContext)_localctx).b!=null?((BexprContext)_localctx).b.getText():null);			  
							else {
							  	  opnd2 = null;
								  _localctx.result.ival = Integer.parseInt((((BexprContext)_localctx).b!=null?((BexprContext)_localctx).b.getText():null));
							}
						
				}
				break;
			case FREG:
				{
				setState(195);
				((BexprContext)_localctx).a = match(FREG);
				setState(196);
				((BexprContext)_localctx).e = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24))) != 0)) ) {
					((BexprContext)_localctx).e = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(197);
				((BexprContext)_localctx).b = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==FREG || _la==FLOAT) ) {
					((BexprContext)_localctx).b = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}

							curType = InstType.iff;
							((BexprContext)_localctx).result =  new Instruction(InstType.iff);
						  	if (((BexprContext)_localctx).b.getType() == RREG)
								opnd2 = (((BexprContext)_localctx).b!=null?((BexprContext)_localctx).b.getText():null);			  
						  	else {
							  	opnd2 = null;
								_localctx.result.fval = Float.parseFloat((((BexprContext)_localctx).b!=null?((BexprContext)_localctx).b.getText():null));
							}
						
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			 
					_localctx.result.optr = (((BexprContext)_localctx).e!=null?((BexprContext)_localctx).e.getText():null);
					_localctx.result.setRegs((((BexprContext)_localctx).a!=null?((BexprContext)_localctx).a.getText():null), opnd2);
				
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RegContext extends ParserRuleContext {
		public String str;
		public Token a;
		public TerminalNode RREG() { return getToken(PAGrammarParser.RREG, 0); }
		public TerminalNode FREG() { return getToken(PAGrammarParser.FREG, 0); }
		public RegContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_reg; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PAGrammarListener ) ((PAGrammarListener)listener).enterReg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PAGrammarListener ) ((PAGrammarListener)listener).exitReg(this);
		}
	}

	public final RegContext reg() throws RecognitionException {
		RegContext _localctx = new RegContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_reg);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(203);
			((RegContext)_localctx).a = _input.LT(1);
			_la = _input.LA(1);
			if ( !(_la==RREG || _la==FREG) ) {
				((RegContext)_localctx).a = (Token)_errHandler.recoverInline(this);
			} else {
				consume();
			}
			 ((RegContext)_localctx).str =  (((RegContext)_localctx).a!=null?((RegContext)_localctx).a.getText():null); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FactrContext extends ParserRuleContext {
		public String str;
		public Token a;
		public TerminalNode RREG() { return getToken(PAGrammarParser.RREG, 0); }
		public TerminalNode INT() { return getToken(PAGrammarParser.INT, 0); }
		public FactrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_factr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PAGrammarListener ) ((PAGrammarListener)listener).enterFactr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PAGrammarListener ) ((PAGrammarListener)listener).exitFactr(this);
		}
	}

	public final FactrContext factr() throws RecognitionException {
		FactrContext _localctx = new FactrContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_factr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(206);
			((FactrContext)_localctx).a = _input.LT(1);
			_la = _input.LA(1);
			if ( !(_la==RREG || _la==INT) ) {
				((FactrContext)_localctx).a = (Token)_errHandler.recoverInline(this);
			} else {
				consume();
			}
			 ((FactrContext)_localctx).str =  (((FactrContext)_localctx).a!=null?((FactrContext)_localctx).a.getText():null); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FactfContext extends ParserRuleContext {
		public String str;
		public Token a;
		public TerminalNode FREG() { return getToken(PAGrammarParser.FREG, 0); }
		public TerminalNode FLOAT() { return getToken(PAGrammarParser.FLOAT, 0); }
		public FactfContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_factf; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PAGrammarListener ) ((PAGrammarListener)listener).enterFactf(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PAGrammarListener ) ((PAGrammarListener)listener).exitFactf(this);
		}
	}

	public final FactfContext factf() throws RecognitionException {
		FactfContext _localctx = new FactfContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_factf);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(209);
			((FactfContext)_localctx).a = _input.LT(1);
			_la = _input.LA(1);
			if ( !(_la==FREG || _la==FLOAT) ) {
				((FactfContext)_localctx).a = (Token)_errHandler.recoverInline(this);
			} else {
				consume();
			}
			 ((FactfContext)_localctx).str =  (((FactfContext)_localctx).a!=null?((FactfContext)_localctx).a.getText():null); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3&\u00d7\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\3\2\7\2 \n\2\f\2\16\2#\13\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\3\5\3,\n\3\3\3\3\3\3\3\3\3\3\3\5\3\63\n\3\3\3"+
		"\3\3\5\3\67\n\3\3\3\3\3\3\3\6\3<\n\3\r\3\16\3=\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\5\4]\n\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7w\n\7\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\b\u0095\n\b\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u00a3\n\t\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n\u00b2\n\n\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\5\13\u00bd\n\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\5\f\u00ca\n\f\3\f\3\f\3\r\3\r\3\r\3\16\3\16\3\16\3\17\3\17"+
		"\3\17\3\17\2\2\20\2\4\6\b\n\f\16\20\22\24\26\30\32\34\2\t\3\2\35\36\3"+
		"\2\n\13\4\2\35\35  \3\2\36\37\3\2\37 \4\2\17\17\22\25\3\2\26\33\u00e3"+
		"\2!\3\2\2\2\4;\3\2\2\2\6\\\3\2\2\2\b^\3\2\2\2\nc\3\2\2\2\fv\3\2\2\2\16"+
		"\u0094\3\2\2\2\20\u00a2\3\2\2\2\22\u00b1\3\2\2\2\24\u00b3\3\2\2\2\26\u00c0"+
		"\3\2\2\2\30\u00cd\3\2\2\2\32\u00d0\3\2\2\2\34\u00d3\3\2\2\2\36 \7\34\2"+
		"\2\37\36\3\2\2\2 #\3\2\2\2!\37\3\2\2\2!\"\3\2\2\2\"$\3\2\2\2#!\3\2\2\2"+
		"$%\7\3\2\2%&\7\34\2\2&\'\5\4\3\2\'(\7\4\2\2()\b\2\1\2)\3\3\2\2\2*,\7&"+
		"\2\2+*\3\2\2\2+,\3\2\2\2,-\3\2\2\2-<\7\34\2\2.\62\b\3\1\2/\60\7!\2\2\60"+
		"\61\b\3\1\2\61\63\7\5\2\2\62/\3\2\2\2\62\63\3\2\2\2\63\64\3\2\2\2\64\66"+
		"\5\6\4\2\65\67\7&\2\2\66\65\3\2\2\2\66\67\3\2\2\2\678\3\2\2\289\7\34\2"+
		"\29:\b\3\1\2:<\3\2\2\2;+\3\2\2\2;.\3\2\2\2<=\3\2\2\2=;\3\2\2\2=>\3\2\2"+
		"\2>\5\3\2\2\2?@\5\b\5\2@A\b\4\1\2A]\3\2\2\2BC\5\22\n\2CD\b\4\1\2D]\3\2"+
		"\2\2EF\5\n\6\2FG\b\4\1\2G]\3\2\2\2HI\5\f\7\2IJ\b\4\1\2J]\3\2\2\2KL\5\20"+
		"\t\2LM\b\4\1\2M]\3\2\2\2NO\5\16\b\2OP\b\4\1\2P]\3\2\2\2QR\7\"\2\2RS\7"+
		"!\2\2S]\b\4\1\2TU\7\35\2\2UV\7\6\2\2VW\7\36\2\2W]\b\4\1\2XY\7\36\2\2Y"+
		"Z\7\6\2\2Z[\7\35\2\2[]\b\4\1\2\\?\3\2\2\2\\B\3\2\2\2\\E\3\2\2\2\\H\3\2"+
		"\2\2\\K\3\2\2\2\\N\3\2\2\2\\Q\3\2\2\2\\T\3\2\2\2\\X\3\2\2\2]\7\3\2\2\2"+
		"^_\t\2\2\2_`\7\6\2\2`a\5\24\13\2ab\b\5\1\2b\t\3\2\2\2cd\7\7\2\2de\7\b"+
		"\2\2ef\5\26\f\2fg\7\t\2\2gh\7\"\2\2hi\7!\2\2ij\b\6\1\2j\13\3\2\2\2kl\t"+
		"\3\2\2lm\7#\2\2mn\7\f\2\2no\7 \2\2op\7\r\2\2pw\b\7\1\2qr\7\35\2\2rs\7"+
		"\6\2\2st\7\16\2\2tu\7#\2\2uw\b\7\1\2vk\3\2\2\2vq\3\2\2\2w\r\3\2\2\2xy"+
		"\t\2\2\2yz\7\6\2\2z{\7#\2\2{\u0095\b\b\1\2|}\7#\2\2}~\7\6\2\2~\177\t\2"+
		"\2\2\177\u0095\b\b\1\2\u0080\u0081\7\17\2\2\u0081\u0082\7\35\2\2\u0082"+
		"\u0083\7\6\2\2\u0083\u0084\7\35\2\2\u0084\u0095\b\b\1\2\u0085\u0086\7"+
		"\17\2\2\u0086\u0087\7\35\2\2\u0087\u0088\7\6\2\2\u0088\u0089\7\36\2\2"+
		"\u0089\u0095\b\b\1\2\u008a\u008b\7\35\2\2\u008b\u008c\7\6\2\2\u008c\u008d"+
		"\7\17\2\2\u008d\u008e\7\35\2\2\u008e\u0095\b\b\1\2\u008f\u0090\7\36\2"+
		"\2\u0090\u0091\7\6\2\2\u0091\u0092\7\17\2\2\u0092\u0093\7\35\2\2\u0093"+
		"\u0095\b\b\1\2\u0094x\3\2\2\2\u0094|\3\2\2\2\u0094\u0080\3\2\2\2\u0094"+
		"\u0085\3\2\2\2\u0094\u008a\3\2\2\2\u0094\u008f\3\2\2\2\u0095\17\3\2\2"+
		"\2\u0096\u0097\7\35\2\2\u0097\u0098\7\6\2\2\u0098\u0099\t\4\2\2\u0099"+
		"\u00a3\b\t\1\2\u009a\u009b\7\36\2\2\u009b\u009c\7\6\2\2\u009c\u009d\t"+
		"\5\2\2\u009d\u00a3\b\t\1\2\u009e\u009f\7#\2\2\u009f\u00a0\7\6\2\2\u00a0"+
		"\u00a1\t\6\2\2\u00a1\u00a3\b\t\1\2\u00a2\u0096\3\2\2\2\u00a2\u009a\3\2"+
		"\2\2\u00a2\u009e\3\2\2\2\u00a3\21\3\2\2\2\u00a4\u00a5\7\20\2\2\u00a5\u00a6"+
		"\7%\2\2\u00a6\u00b2\b\n\1\2\u00a7\u00a8\7\20\2\2\u00a8\u00a9\t\2\2\2\u00a9"+
		"\u00b2\b\n\1\2\u00aa\u00ab\7\21\2\2\u00ab\u00ac\t\2\2\2\u00ac\u00b2\b"+
		"\n\1\2\u00ad\u00ae\7\21\2\2\u00ae\u00af\7%\2\2\u00af\u00b0\t\2\2\2\u00b0"+
		"\u00b2\b\n\1\2\u00b1\u00a4\3\2\2\2\u00b1\u00a7\3\2\2\2\u00b1\u00aa\3\2"+
		"\2\2\u00b1\u00ad\3\2\2\2\u00b2\23\3\2\2\2\u00b3\u00bc\b\13\1\2\u00b4\u00b5"+
		"\7\35\2\2\u00b5\u00b6\t\7\2\2\u00b6\u00b7\t\4\2\2\u00b7\u00bd\b\13\1\2"+
		"\u00b8\u00b9\7\36\2\2\u00b9\u00ba\t\7\2\2\u00ba\u00bb\t\5\2\2\u00bb\u00bd"+
		"\b\13\1\2\u00bc\u00b4\3\2\2\2\u00bc\u00b8\3\2\2\2\u00bd\u00be\3\2\2\2"+
		"\u00be\u00bf\b\13\1\2\u00bf\25\3\2\2\2\u00c0\u00c9\b\f\1\2\u00c1\u00c2"+
		"\7\35\2\2\u00c2\u00c3\t\b\2\2\u00c3\u00c4\t\4\2\2\u00c4\u00ca\b\f\1\2"+
		"\u00c5\u00c6\7\36\2\2\u00c6\u00c7\t\b\2\2\u00c7\u00c8\t\5\2\2\u00c8\u00ca"+
		"\b\f\1\2\u00c9\u00c1\3\2\2\2\u00c9\u00c5\3\2\2\2\u00ca\u00cb\3\2\2\2\u00cb"+
		"\u00cc\b\f\1\2\u00cc\27\3\2\2\2\u00cd\u00ce\t\2\2\2\u00ce\u00cf\b\r\1"+
		"\2\u00cf\31\3\2\2\2\u00d0\u00d1\t\4\2\2\u00d1\u00d2\b\16\1\2\u00d2\33"+
		"\3\2\2\2\u00d3\u00d4\t\5\2\2\u00d4\u00d5\b\17\1\2\u00d5\35\3\2\2\2\17"+
		"!+\62\66;=\\v\u0094\u00a2\u00b1\u00bc\u00c9";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}