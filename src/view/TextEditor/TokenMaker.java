package view.TextEditor;

import javax.swing.text.Segment;

import org.fife.ui.rsyntaxtextarea.AbstractTokenMaker;
import org.fife.ui.rsyntaxtextarea.RSyntaxUtilities;
import org.fife.ui.rsyntaxtextarea.Token;
import org.fife.ui.rsyntaxtextarea.TokenMap;

public class TokenMaker extends AbstractTokenMaker {
	
	@Override
	public TokenMap getWordsToHighlight() {
		TokenMap tokenMap = new TokenMap();

		tokenMap.put("program", Token.MARKUP_PROCESSING_INSTRUCTION);
		tokenMap.put("end", Token.MARKUP_PROCESSING_INSTRUCTION);
		tokenMap.put("if", Token.DATA_TYPE);
		tokenMap.put("goto", Token.DATA_TYPE);
		
		tokenMap.put("int", Token.DATA_TYPE);
		tokenMap.put("float", Token.DATA_TYPE);
		
		tokenMap.put("r1", Token.VARIABLE);
		tokenMap.put("r2", Token.VARIABLE);
		tokenMap.put("r3", Token.VARIABLE);
		tokenMap.put("r4", Token.VARIABLE);
		tokenMap.put("r5", Token.VARIABLE);
		tokenMap.put("r6", Token.VARIABLE);
		tokenMap.put("r7", Token.VARIABLE);
		tokenMap.put("r8", Token.VARIABLE);
		tokenMap.put("f1", Token.VARIABLE);
		tokenMap.put("f2", Token.VARIABLE);
		tokenMap.put("f3", Token.VARIABLE);
		tokenMap.put("f4", Token.VARIABLE);
		
		tokenMap.put("&", Token.OPERATOR);
		tokenMap.put(":=", Token.OPERATOR);
		tokenMap.put("*", Token.OPERATOR);
		tokenMap.put("+", Token.OPERATOR);
		tokenMap.put("-", Token.OPERATOR);
		tokenMap.put("/", Token.OPERATOR);
		tokenMap.put("%", Token.OPERATOR);
		tokenMap.put("<", Token.OPERATOR);
		tokenMap.put("<=", Token.OPERATOR);
		tokenMap.put(">", Token.OPERATOR);
		tokenMap.put(">=", Token.OPERATOR);
		tokenMap.put("==", Token.OPERATOR);
		tokenMap.put("!=", Token.OPERATOR);

		tokenMap.put("(", Token.SEPARATOR);
		tokenMap.put(")", Token.SEPARATOR);
		tokenMap.put("[", Token.SEPARATOR);
		tokenMap.put("]", Token.SEPARATOR);
		
		tokenMap.put("--", Token.COMMENT_EOL);
	
		//tokenMap.put("[a-zA-Z][a-zA-Z0-9]*", Token.IDENTIFIER);
		return tokenMap;
	}
	
	@Override
	public void addToken(Segment segment, int start, int end, int tokenType, int startOffset) {
	   // This assumes all keywords, etc. were parsed as "identifiers."
	   if (tokenType==Token.IDENTIFIER) {
	      int value = wordsToHighlight.get(segment, start, end);
	      if (value != -1) {
	         tokenType = value;
	      }
	   }
	   super.addToken(segment, start, end, tokenType, startOffset);
	}
	
	@Override
	public Token getTokenList(Segment text, int startTokenType, int startOffset) {

		resetTokenList();

		char[] array = text.array;
		int offset = text.offset;
		int count = text.count;
		int end = offset + count;

		// Token starting offsets are always of the form:
		// 'startOffset + (currentTokenStart-offset)', but since startOffset and
		// offset are constant, tokens' starting positions become:
		// 'newStartOffset+currentTokenStart'.
		int newStartOffset = startOffset - offset;

		int currentTokenStart = offset;
		int currentTokenType = startTokenType;
		
		final String operators = "+-*/%!=<>^&|?:";
		final String separators = "()[]{}";
		final String separators2 = ".,;";	// Characters you don't want syntax highlighted but separate identifiers.
		boolean numContainsExponent = false;
		@SuppressWarnings("unused")
		final String hexCharacters = "0123456789ABCDEFabcdef";
		final String numberEndChars = "FfLl"; // Characters used to specify literal number types.
		boolean numContainsEndCharacter = false;

		for (int i = offset; i < end; i++) {

			char c = array[i];

			switch (currentTokenType) {

			case Token.NULL:

				currentTokenStart = i; // Starting a new token here.

				switch (c) {

				case ' ':
				case '\t':
					currentTokenType = Token.WHITESPACE;
					break;

				case '"':
					currentTokenType = Token.LITERAL_STRING_DOUBLE_QUOTE;
					break;

				case '#':
					currentTokenType = Token.COMMENT_EOL;
					break;

				default:
					if (RSyntaxUtilities.isDigit(c)) {
						currentTokenType = Token.LITERAL_NUMBER_DECIMAL_INT;
						break;
					} else if (RSyntaxUtilities.isLetter(c) || c == '/' || c == '_') {
						currentTokenType = Token.IDENTIFIER;
						break;
					}

					// Anything not currently handled - mark as an identifier
					currentTokenType = Token.IDENTIFIER;
					break;

				} // End of switch (c).

				break;

			case Token.WHITESPACE:

				switch (c) {

				case ' ':
				case '\t':
					break; // Still whitespace.

				case '"':
					addToken(text, currentTokenStart, i - 1, Token.WHITESPACE, newStartOffset + currentTokenStart);
					currentTokenStart = i;
					currentTokenType = Token.LITERAL_STRING_DOUBLE_QUOTE;
					break;

				case '#':
					addToken(text, currentTokenStart, i - 1, Token.WHITESPACE, newStartOffset + currentTokenStart);
					currentTokenStart = i;
					currentTokenType = Token.COMMENT_EOL;
					break;

				default: // Add the whitespace token and start anew.

					addToken(text, currentTokenStart, i - 1, Token.WHITESPACE, newStartOffset + currentTokenStart);
					currentTokenStart = i;

					if (RSyntaxUtilities.isDigit(c)) {
						currentTokenType = Token.LITERAL_NUMBER_DECIMAL_INT;
						break;
					} else if (RSyntaxUtilities.isLetter(c) || c == '/' || c == '_') {
						currentTokenType = Token.IDENTIFIER;
						break;
					}

					// Anything not currently handled - mark as identifier
					currentTokenType = Token.IDENTIFIER;

				} // End of switch (c).

				break;

			default: // Should never happen
			case Token.IDENTIFIER:

				switch (c) {

				case ' ':
				case '\t':
					addToken(text, currentTokenStart, i - 1, Token.IDENTIFIER, newStartOffset + currentTokenStart);
					currentTokenStart = i;
					currentTokenType = Token.WHITESPACE;
					break;

				case '"':
					addToken(text, currentTokenStart, i - 1, Token.IDENTIFIER, newStartOffset + currentTokenStart);
					currentTokenStart = i;
					currentTokenType = Token.LITERAL_STRING_DOUBLE_QUOTE;
					break;

				default:
					if (RSyntaxUtilities.isLetterOrDigit(c) || c == '/' || c == '_') {
						break; // Still an identifier of some type.
					}
					// Otherwise, we're still an identifier (?).

				} // End of switch (c).

				break;

			case Token.LITERAL_NUMBER_DECIMAL_INT:
				switch (c) {

				case ' ':
				case '\t':
					addToken(text, currentTokenStart, i - 1, Token.LITERAL_NUMBER_DECIMAL_INT,
							newStartOffset + currentTokenStart);
					currentTokenStart = i;
					currentTokenType = Token.WHITESPACE;
					break;

				case '"':
					addToken(text, currentTokenStart, i - 1, Token.LITERAL_NUMBER_DECIMAL_INT,
							newStartOffset + currentTokenStart);
					currentTokenStart = i;
					currentTokenType = Token.LITERAL_STRING_DOUBLE_QUOTE;
					break;

				default:

					if (RSyntaxUtilities.isDigit(c)) {
						break; // Still a literal number.
					}else if (c=='e') {	// Exponent.
						if (numContainsExponent==false) {
							numContainsExponent = true;
						}
						else {
							currentTokenType = Token.ERROR_NUMBER_FORMAT;
						}
						break;
					}
					else if (c=='.') { // Decimal point.
						if (numContainsExponent==true) {
							currentTokenType = Token.ERROR_NUMBER_FORMAT;
						}
						else {
							currentTokenType = Token.LITERAL_NUMBER_FLOAT;
						}
						break;
					}
					int indexOf = numberEndChars.indexOf(c);
					if (indexOf>-1) {	// Numbers can end in 'f','F','l','L', etc.
						if (numContainsEndCharacter==true) {
							currentTokenType = Token.ERROR_NUMBER_FORMAT;
						}
						else {
							numContainsEndCharacter = true;
						}
						break;
					}
					indexOf = operators.indexOf(c);
					if (indexOf>-1) {
						addToken(text, currentTokenStart,i-1, Token.LITERAL_NUMBER_DECIMAL_INT, newStartOffset+currentTokenStart);
						currentTokenStart = i;
						currentTokenType = Token.OPERATOR;
						break;
					}
					indexOf = separators.indexOf(c);
					if (indexOf>-1) {
						addToken(text, currentTokenStart,i-1, Token.LITERAL_NUMBER_DECIMAL_INT, newStartOffset+currentTokenStart);
						addToken(text, i,i, Token.SEPARATOR, newStartOffset+i);
						currentTokenType = Token.NULL;
						break;
					}
					indexOf = separators2.indexOf(c);
					if (indexOf>-1) {
						addToken(text, currentTokenStart,i-1, Token.LITERAL_NUMBER_DECIMAL_INT, newStartOffset+currentTokenStart);
						addToken(text, i,i, Token.IDENTIFIER, newStartOffset+i);
						currentTokenType = Token.NULL;
						break;
					}

					// Otherwise, remember this was a number and start over.
					addToken(text, currentTokenStart, i - 1, Token.LITERAL_NUMBER_DECIMAL_INT,
							newStartOffset + currentTokenStart);
					i--;
					currentTokenType = Token.NULL;

				} // End of switch (c).

				break;
			case Token.COMMENT_EOL:
				i = end - 1;
				addToken(text, currentTokenStart, i, currentTokenType, newStartOffset + currentTokenStart);
				// We need to set token type to null so at the bottom we don't
				// add one more token.
				currentTokenType = Token.NULL;
				break;

			case Token.LITERAL_STRING_DOUBLE_QUOTE:
				if (c == '"') {
					addToken(text, currentTokenStart, i, Token.LITERAL_STRING_DOUBLE_QUOTE,
							newStartOffset + currentTokenStart);
					currentTokenType = Token.NULL;
				}
				break;

			} // End of switch (currentTokenType).

		} // End of for (int i=offset; i<end; i++).

		switch (currentTokenType) {

		// Remember what token type to begin the next line with.
		case Token.LITERAL_STRING_DOUBLE_QUOTE:
			addToken(text, currentTokenStart, end - 1, currentTokenType, newStartOffset + currentTokenStart);
			break;

		// Do nothing if everything was okay.
		case Token.NULL:
			addNullToken();
			break;

		// All other token types don't continue to the next line...
		default:
			addToken(text, currentTokenStart, end - 1, currentTokenType, newStartOffset + currentTokenStart);
			addNullToken();
		}

		// Return the first token in our linked list.
		return firstToken;

	}
}
