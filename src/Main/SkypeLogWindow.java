package Main;

import Misc.ConsolePrintStream;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.io.PrintStream;

public class SkypeLogWindow{

	private static ConsolePrintStream taOutputStream;
	public static PrintStream stream = System.out;

	public static JFrame frame = new JFrame("Skype command program");
	public static ButtonArea buttonArea;

	public static void createAndShowGui() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(600, 500));

		JTextPane textArea = new JTextPane();
		DefaultCaret caret = (DefaultCaret)textArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

		textArea.setEditable(false);
		taOutputStream = new ConsolePrintStream(textArea);

		frame.setLayout(new BorderLayout());

		JScrollPane pane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		frame.add(pane, BorderLayout.CENTER);

		buttonArea = new ButtonArea();
		frame.add(buttonArea.getScrollPane(), BorderLayout.EAST);

		System.setOut(taOutputStream);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(true);
	}
}

