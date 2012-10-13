package algebra;

import clifford.DuplicateNameException;
import clifford.UnknownNameException;

public class AlgebraHelper {

	Algebra a;
	AlgebraHelper(Algebra alg){
		a = alg;
	}
	
	public void rename(String name){
		a.name = name;
	}
	
	public void renameElement(String oldName, String newName) throws UnknownNameException, DuplicateNameException{
		int index = a.indexOf(oldName);
		if(index == -1) throw new UnknownNameException(new String[]{oldName});
		if(a.names.contains(newName)) throw new DuplicateNameException(newName);
		a.elements[index].name = newName;
	}

	public void negate(String name) throws UnknownNameException{
		int n = a.indexOf(name);
		if(n == -1) throw new UnknownNameException(new String[]{name});
		if(a.elements[n].components.length < 2){ //it's not possible ! //TODO: make it possible for -x to be a basis vector
			System.err.println("not yet possible to negate non-multivectors yet, sorry.");
			return;
		}
		
		
		//for every multivector that includes this element: negate it.
		//swap two of this element's components
		for(int i = 1; i < a.table.length; i++){ // the 0th element is 1 * v which is written in terms of v and would need to be negated again.
			for(int j = 1; j < a.table.length; j++){
				if(i == n){
					a.table[i][j] = a.table[i][j].negate();
				}
				if(j == n){
					a.table[i][j] = a.table[i][j].negate();
				}
			}
		}
		
		Element e = a.elements[n];
		Element temp = e.components[0];
		e.components[0] = e.components[1];
		e.components[1] = temp;
		
	}
	


	public void reorder(String[] in) throws UnknownNameException{
		int[] es = new int[in.length];

		for(int i = 0; i < in.length; i++){
			es[i] = a.indexOf(in[i]);
			if(es[i] == -1) throw new UnknownNameException(new String[]{in[i]});
		}
	
		for(int i = 0; i < in.length-1; i++){
			swap(es[i], es[i+1]);
		}
	}
	private void swap(int i, int j){
		System.out.println("test");
		//swaps elements i and j in the tables.
		
		//swap elements in elements.
		//swap column in table
		//swap row in table
		//swap components in every multivector
		
		Element temp = a.elements[i];
		a.elements[i] = a.elements[j];
		a.elements[j] = temp;
		
		Multivector[] mvs = a.table[i];
		a.table[i] = a.table[j];
		a.table[j] = mvs;
		
		for(int k = 0; k < a.table.length; k++){
			Multivector mv = a.table[k][i];
			a.table[k][i] = a.table[k][j];
			a.table[k][j] = mv;
		}
		
		for(Multivector[] row : a.table){
			for(Multivector mv: row){
				double t = mv.components[i];
				mv.components[i] = mv.components[j];
				mv.components[j] = t;
			}
		}
		for(Multivector mv : a.userMVs){
			double t = mv.components[i];
			mv.components[i] = mv.components[j];
			mv.components[j] = t;
		}
		
		
	}

	public void renameSpacetimeStyle() {
		// TODO Auto-generated method stub
		
	}
	
	
}
