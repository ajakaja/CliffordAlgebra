package clifford.commands;

import java.io.PrintStream;

import algebra.Algebra;

import clifford.Clifford;
import clifford.UnknownNameException;


public class NegateCommand extends AlgebraCommand {



	public NegateCommand(Clifford c) {
		super(c);
		// TODO Auto-generated constructor stub
	}


	@Override
	protected void processAlgebra(PrintStream out, PrintStream err,
			String[] input, Algebra a) {
		
		if(input.length == 1){
			err.println("No vector provided.");
			return;
		}
		String name = input[1];
		try{
			a.helper.negate(name);
			out.println("Warning: that only negates basis vectors and will not negate any vectors you have made with them.");
		}catch(UnknownNameException e){
			err.println("No vector called " + name + " in " + a.getName() + ".");
		}
		
	}

}
