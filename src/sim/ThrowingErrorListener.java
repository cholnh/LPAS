package sim;
//import org.antlr.v4.codegen.model.Recognizer;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.misc.ParseCancellationException;

import parser.PAGrammarParser;

public class ThrowingErrorListener extends BaseErrorListener {

   public static final ThrowingErrorListener INSTANCE = new ThrowingErrorListener();
 
   @Override
   public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e)
      throws ParseCancellationException {
	   	 String errMsg = "line " + line + ":" + charPositionInLine + " " + msg;
	   	 
	   	 SimEngine engine = (SimEngine)(Sim.GetInstance());
	   	 Log log = engine.getLog();
	   	 //InstData inst = Sim.GetInstance().getCurInst();
	   	 log.setError("\n"+errMsg+"\n\t" + engine.getTypeHint(PAGrammarParser.curType));
      }
}