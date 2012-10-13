package clifford.commands;

import java.io.PrintStream;

import algebra.Algebra;

import clifford.Clifford;
import command.Command;

/** 
 * commands that just modify or use algebras.
 * Takes care of finding the algebra the command refers to, and then calls 
 * @author Alex
 *
 */
public abstract class AlgebraCommand extends Command{
	
	protected Clifford control;
	public AlgebraCommand(Clifford c){
		control = c;
	}
	
	/**
	 * Extracts the algebra the command refers to from the Clifford class.
	 * If it was referred to by name in the command, erases it from the input.
	 */
	@Override
	protected void process(PrintStream out, PrintStream err, String[] input) {
		Algebra a = null;
		String[] newInput = input;
		if(input.length > 1) {
			a = control.getAlgebra(input[1]);
				if(a != null){
				newInput = new String[input.length-1];
				newInput[0] = input[0];
				for(int i = 1; i < input.length - 1 ; i++){
					newInput[i] = input[i+1];
				}
			}
		}
		if(input.length == 1 || a == null) a = control.lastUsed;
		if(a == null){
			err.println("No algebras in the system.");
			return;
		}
		processAlgebra(out, err, newInput, a);
		control.lastUsed = a;
	}
	
	protected abstract void processAlgebra(PrintStream out, PrintStream err, String[] input, Algebra a);
		

}
