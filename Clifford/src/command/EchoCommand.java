package command;

import java.io.PrintStream;

public class EchoCommand extends Command{

	@Override
	protected void process(PrintStream out, PrintStream err, String[] input) {
		
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i < input.length; i++){
			sb.append(input[i]);
			if(i != input.length-1){
				sb.append(" ");
			}
		}
		
		out.println(sb.toString());
	}

}
