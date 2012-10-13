package command;
import java.io.PrintStream;


public abstract class Command {

	
	
	protected abstract void process(PrintStream out, PrintStream err, String[] input);
	
	
}
