package Misc;

import Main.SkypeLogWindow;

import java.io.IOException;
import java.io.OutputStream;

public class ConsoleOutputStream extends OutputStream {

	@Override
	public void write(int b) throws IOException {
		SkypeLogWindow.stream.write(b);
	}
}