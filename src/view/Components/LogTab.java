package view.Components;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

public class LogTab extends JTextPane{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2125817374386818020L;

	private JScrollPane sp;
	JTabbedPane ancestor;
	
	public JScrollPane getScrollPane()
	{
		return sp;
	}

	public LogTab(JTabbedPane ancestor)
	{
		this.ancestor = ancestor;
		setBounds(2, 40, ancestor.getSize().width - 4, ancestor.getSize().height - 42);
		sp = new JScrollPane(this);
		sp.setBounds(2, 40, ancestor.getSize().width - 4, ancestor.getSize().height - 42);
		sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		addFocusListener(new FocusListener() {
	        @Override
	        public void focusLost(FocusEvent e) {
	            setEditable(true);
	        }
	        @Override
	        public void focusGained(FocusEvent e) {
	            setEditable(false);
	        }
	    });
	}
	
	public void requestLog(int idx)
	{
		ancestor.setSelectedIndex(idx);
	}
	
	public void appendToPane(String msg, Color c)
    {
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);

        aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Lucida Console");
        aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);

        int len = getDocument().getLength();
        setCaretPosition(len);
        setCharacterAttributes(aset, false);
        replaceSelection(msg);
    }
}
