package clifford.commands;

import java.io.PrintStream;

import algebra.Algebra;

import clifford.Clifford;

import command.Command;

public class ListAlgebrasCommand extends Command {

	
	private Clifford control;
	public ListAlgebrasCommand(Clifford c) {
		control = c;
	}

	@Override
	protected void process(PrintStream out, PrintStream err, String[] input) {
		for(Algebra a: control.getAlgebras()){
			out.println(a.getName());
		}
		
	}

}
