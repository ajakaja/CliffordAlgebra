package command;
import java.io.PrintStream;


public class PreviousCommand extends Command {

	CommandProcessor cp;
	public PreviousCommand(CommandProcessor commandProcessor) {
		cp = commandProcessor;
	}

	@Override
	protected void process(PrintStream out, PrintStream err, String[] input) {
		
		if(input.length == 1){
			cp.runPrevious(0);
		}else{
			try{
				int i = Integer.parseInt(input[1]);
				cp.runPrevious(i);
			}catch (NumberFormatException e){
				err.print(input[1] + " is not a number.");
			}
		}
		
	}

}
