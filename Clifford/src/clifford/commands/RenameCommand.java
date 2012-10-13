package clifford.commands;

import java.io.PrintStream;

import algebra.Algebra;

import clifford.Clifford;
import clifford.DuplicateNameException;
import clifford.UnknownNameException;


public class RenameCommand extends AlgebraCommand{

	public RenameCommand(Clifford c) {
		super(c);
	}

	@Override
	protected void processAlgebra(PrintStream out, PrintStream err,
			String[] input, Algebra a) {
		if(input.length == 2){
			String name = input[1];
			if(name.length() == 0){
				err.println("Invalid name.");
			}
			if(!(control.getAlgebra(name) == null)){
				err.println("The name '" + name + "' is already taken by an algebra.");
				return;
			}
			out.println("Renamed " + a.getName() + " to " + name + ".");

			a.helper.rename(name);
		}else{
			for(int i = 1; i < input.length; i+= 2){
				if(i+1 < input.length){ //each pair gets renamed first <- second
					try{
						a.helper.renameElement(input[i], input[i+1]);
						out.println("Renamed " + input[i] + " to " + input[i+1] + ".");
					}catch(DuplicateNameException e){
						err.println("Name " + e.duplicate + " is already taken.");
					} catch (UnknownNameException e) {
						err.println("No vector called " + e.unknowns[0] + " in algebra " + a.getName() + ".");
					}
				}
			}
			
		}
		
	}


}
