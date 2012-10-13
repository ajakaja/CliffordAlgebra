package clifford;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Collection;
import java.util.Scanner;
import java.util.TreeMap;

import clifford.commands.*;

import algebra.Algebra;


import command.CommandProcessor;

public class Clifford {

	private TreeMap<String, Algebra> algebras = new TreeMap<String, Algebra>();
	
	private CommandProcessor cp;
	
	public Algebra lastUsed = null;


	private void init(){
		addCommands();
	}

	private void loadFiles(String[] args){
		if(args.length != 0){
			for(int i = 0; i < args.length; i++){
				try{
					File f = new File(args[i]); 
					Scanner s = new Scanner(new FileReader(f));
					while(s.hasNextLine()){
						String command = s.nextLine();
						cp.addInput(command);
					}
					s.close();

				} catch (FileNotFoundException e) {
					System.out.println("Could not find a file with the first argument");
				} 
			}
		}
	}

	private void addCommands(){
		cp = new CommandProcessor(System.in, System.out, System.err);
		cp.addCommand( new MakeAlgebraCommand(this), "new");
		cp.addCommand(new ShowCommand(this), "show", "see");
		cp.addCommand(new NegateCommand(this), "negate", "neg");
		cp.addCommand(new RenameCommand(this), "rename", "rn");
		cp.addCommand(new ReorderCommand(this), "swap", "reorder");
		cp.addCommand(new SpacetimeCommand(this), "spacetime");
		cp.addCommand(new ListAlgebrasCommand(this), "list");
		cp.addCommand(new HelpCommand(), "?", "help");


	}
	
	public void addAlgebra(Algebra alg){
		algebras.put(alg.getName(), alg);
		lastUsed = alg;
	}
	
	public Algebra getAlgebra(String name){
		Algebra a = algebras.get(name);
		return a;
	}
	
	public Collection<Algebra> getAlgebras(){
		return algebras.values();
	}
	

	
	public static void main(String[] args) {


		Clifford c = (new Clifford());
		c.init();
		c.loadFiles(args);

	}
}
