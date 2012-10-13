package clifford.commands;

import java.io.PrintStream;

import command.Command;

public class HelpCommand extends Command{

	@Override
	protected void process(PrintStream out, PrintStream err, String[] input) {
		out.println("Commands:\n" +
				"'new <metric components>' to make a new algebra. Ex: new 1, 1 makes 2d Euclidean space.\n" +
				"'list' to list all algebras.\n" +
				"'negate <vector>' negates a basis vector.\n" +
				"'rename <algebra>|<vector 1> <vector 2>' changes the name of an algebra or of basis vectors.\n" +
				"'reorder <list of vectors>' changes the places of of two or more vectors.\n" +
				"'show <algebra>' displays an algebra's vectors and multiplication table.");
				
		
		
	}

}
