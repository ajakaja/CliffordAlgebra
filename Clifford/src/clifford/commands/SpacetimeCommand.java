package clifford.commands;

import java.io.PrintStream;

import algebra.Algebra;

import clifford.Clifford;


public class SpacetimeCommand extends AlgebraCommand {

	public SpacetimeCommand(Clifford c) {
		super(c);
	}

	@Override
	protected void processAlgebra(PrintStream out, PrintStream err,
			String[] input, Algebra a) {
		a.helper.renameSpacetimeStyle();
		
	}



}
