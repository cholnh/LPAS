package view.TextEditor;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rtextarea.RUndoManager;

public class RSyntaxText extends RSyntaxTextArea {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4273502063717277144L;

	public RSyntaxText(int i, int j) {
		super(i, j);
	}

	@Override
	protected RUndoManager createUndoManager() {
		RUndoManager undoManager = super.createUndoManager();
		undoManager.setLimit(5);
		return undoManager;
	}
}
