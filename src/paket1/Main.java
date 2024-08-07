package paket1;

import java.io.IOException;

import javax.swing.JFrame;

import org.json.simple.parser.ParseException;

public class Main {
	static Frame frame;
	public static void main(String[] args) throws IOException, ParseException {
		frame = new Frame();

	}
	
	public static JFrame getFrame() {
		return frame;
	}

}
