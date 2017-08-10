// Generated from PAGrammar.g4 by ANTLR 4.5.3

	// �ļ� Ŭ���� ��� 
	package parser;
	import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link PAGrammarParser}.
 */
public interface PAGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link PAGrammarParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(PAGrammarParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link PAGrammarParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(PAGrammarParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by {@link PAGrammarParser#stmts}.
	 * @param ctx the parse tree
	 */
	void enterStmts(PAGrammarParser.StmtsContext ctx);
	/**
	 * Exit a parse tree produced by {@link PAGrammarParser#stmts}.
	 * @param ctx the parse tree
	 */
	void exitStmts(PAGrammarParser.StmtsContext ctx);
	/**
	 * Enter a parse tree produced by {@link PAGrammarParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterStmt(PAGrammarParser.StmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link PAGrammarParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitStmt(PAGrammarParser.StmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link PAGrammarParser#arithstmt}.
	 * @param ctx the parse tree
	 */
	void enterArithstmt(PAGrammarParser.ArithstmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link PAGrammarParser#arithstmt}.
	 * @param ctx the parse tree
	 */
	void exitArithstmt(PAGrammarParser.ArithstmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link PAGrammarParser#ifstmt}.
	 * @param ctx the parse tree
	 */
	void enterIfstmt(PAGrammarParser.IfstmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link PAGrammarParser#ifstmt}.
	 * @param ctx the parse tree
	 */
	void exitIfstmt(PAGrammarParser.IfstmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link PAGrammarParser#arrstmt}.
	 * @param ctx the parse tree
	 */
	void enterArrstmt(PAGrammarParser.ArrstmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link PAGrammarParser#arrstmt}.
	 * @param ctx the parse tree
	 */
	void exitArrstmt(PAGrammarParser.ArrstmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link PAGrammarParser#loadstorestmt}.
	 * @param ctx the parse tree
	 */
	void enterLoadstorestmt(PAGrammarParser.LoadstorestmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link PAGrammarParser#loadstorestmt}.
	 * @param ctx the parse tree
	 */
	void exitLoadstorestmt(PAGrammarParser.LoadstorestmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link PAGrammarParser#asgnstmt}.
	 * @param ctx the parse tree
	 */
	void enterAsgnstmt(PAGrammarParser.AsgnstmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link PAGrammarParser#asgnstmt}.
	 * @param ctx the parse tree
	 */
	void exitAsgnstmt(PAGrammarParser.AsgnstmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link PAGrammarParser#iostmt}.
	 * @param ctx the parse tree
	 */
	void enterIostmt(PAGrammarParser.IostmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link PAGrammarParser#iostmt}.
	 * @param ctx the parse tree
	 */
	void exitIostmt(PAGrammarParser.IostmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link PAGrammarParser#aexpr}.
	 * @param ctx the parse tree
	 */
	void enterAexpr(PAGrammarParser.AexprContext ctx);
	/**
	 * Exit a parse tree produced by {@link PAGrammarParser#aexpr}.
	 * @param ctx the parse tree
	 */
	void exitAexpr(PAGrammarParser.AexprContext ctx);
	/**
	 * Enter a parse tree produced by {@link PAGrammarParser#bexpr}.
	 * @param ctx the parse tree
	 */
	void enterBexpr(PAGrammarParser.BexprContext ctx);
	/**
	 * Exit a parse tree produced by {@link PAGrammarParser#bexpr}.
	 * @param ctx the parse tree
	 */
	void exitBexpr(PAGrammarParser.BexprContext ctx);
	/**
	 * Enter a parse tree produced by {@link PAGrammarParser#reg}.
	 * @param ctx the parse tree
	 */
	void enterReg(PAGrammarParser.RegContext ctx);
	/**
	 * Exit a parse tree produced by {@link PAGrammarParser#reg}.
	 * @param ctx the parse tree
	 */
	void exitReg(PAGrammarParser.RegContext ctx);
	/**
	 * Enter a parse tree produced by {@link PAGrammarParser#factr}.
	 * @param ctx the parse tree
	 */
	void enterFactr(PAGrammarParser.FactrContext ctx);
	/**
	 * Exit a parse tree produced by {@link PAGrammarParser#factr}.
	 * @param ctx the parse tree
	 */
	void exitFactr(PAGrammarParser.FactrContext ctx);
	/**
	 * Enter a parse tree produced by {@link PAGrammarParser#factf}.
	 * @param ctx the parse tree
	 */
	void enterFactf(PAGrammarParser.FactfContext ctx);
	/**
	 * Exit a parse tree produced by {@link PAGrammarParser#factf}.
	 * @param ctx the parse tree
	 */
	void exitFactf(PAGrammarParser.FactfContext ctx);
}