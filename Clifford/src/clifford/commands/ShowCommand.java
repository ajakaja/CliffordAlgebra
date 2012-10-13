package clifford.commands;

import java.io.PrintStream;

import algebra.Algebra;

import clifford.Clifford;

public class ShowCommand extends AlgebraCommand {



	public ShowCommand(Clifford c) {
		super(c);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void processAlgebra(PrintStream out, PrintStream err,
			String[] input, Algebra a) {
		out.println(a.toString());
		if(input.length > 1){
			for(int i = 1; i < input.length; i++){
				Algebra b = control.getAlgebra(input[i]);
				if(b != null) out.println(b.toString());
			}
		}
		
	}

}
