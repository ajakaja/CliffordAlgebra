package clifford.commands;

import java.io.PrintStream;

import clifford.Clifford;

import algebra.Algebra;


import command.Command;

public class MakeAlgebraCommand extends Command{

	Clifford control;

	public MakeAlgebraCommand(Clifford c) {
		control = c;
	}

	@Override
	protected void process(PrintStream out, PrintStream err, String[] input) {
		// TODO Auto-generated method stub

		//signature of metric is in input

		if(input.length <=2){
			err.println("Nothing worth making an algebra out of.");
		}

		int[] metric = new int[input.length -1];

		try{
			for(int i = 1; i < input.length; i++){
				metric[i-1] = Integer.parseInt(input[i]);
			}
			Algebra a = new Algebra(metric);
			control.addAlgebra(a);
			out.println("Made new algebra " + a.getName() + ".");

		}catch(NumberFormatException e){
			err.println("Bad input for metric.");
		}
	}

}
