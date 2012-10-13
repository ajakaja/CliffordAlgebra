package command;

import java.io.PrintStream;

public class QuitCommand extends Command{

	@Override
	protected void process(PrintStream out, PrintStream err, String[] input) {
		System.exit(0);
		
	}

}
