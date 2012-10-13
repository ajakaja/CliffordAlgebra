package clifford.commands;

import java.io.PrintStream;
import java.util.Arrays;

import algebra.Algebra;

import clifford.Clifford;
import clifford.UnknownNameException;


public class ReorderCommand extends AlgebraCommand{

	public ReorderCommand(Clifford c) {
		super(c);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void processAlgebra(PrintStream out, PrintStream err,
			String[] input, Algebra a) {
		
		if(input.length < 3){
			err.println("Not enough arguments");
			return;
		}
		String[] in = new String[input.length - 1];
		for(int i = 1; i < input.length; i++){
			in[i-1] = input[i];
		}
		try{
			a.helper.reorder(in);
		}catch(UnknownNameException e){
			if(e.unknowns.length == 1) err.println("Invalid vector name: " + e.unknowns[0]);
			else err.println("Invalid vector names: " + Arrays.toString(e.unknowns));
		}
		
	}


}
