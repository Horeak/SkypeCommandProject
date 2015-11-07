package Misc;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConsolePrintStream extends PrintStream {

	public JEditorPane textArea;

	public ConsolePrintStream( JEditorPane textArea ) {
		super(new ConsoleOutputStream(), true);

		this.textArea = textArea;
	}

	@Override
	public void print( String s ) {
		super.print(s);
		try {
			final String text = s + "\n";

			SwingUtilities.invokeLater(() -> {
				try {
					if (textArea != null) {
						DateFormat dateFormat = new SimpleDateFormat("[HH:mm:ss]");
						textArea.getDocument().insertString(textArea.getDocument().getLength(), dateFormat.format(new Date()) + text, null);
					}
				} catch (BadLocationException e) {
					e.printStackTrace();
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}