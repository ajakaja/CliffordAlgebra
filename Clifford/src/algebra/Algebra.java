package algebra;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import util.Combinatorics;




/**
 * this is a very bad solution. It gets the job done but only for clifford algebras and only those of the type I know about.
 * Maybe someday I will make it much more general.
 * @author Alex
 *
 */
public class Algebra {

	private static String defaultNames = "1vogabcdefhjklmnpqrstuvwxyz";

	Set<String> names = new HashSet<String>();

	String name;

	private static int number = 0;

	int bases;
	int dim;

	Element[] elements;
	Multivector[][] table;
	
	Set<Multivector> userMVs = new HashSet<Multivector>();
	
	
	public AlgebraHelper helper = new AlgebraHelper(this);

	public Algebra(int[] metric){
		name = "algebra"+number;
		number++;
		bases = metric.length;
		dim = (int)Math.pow(2, bases);


		elements = new Element[dim];
		table = new Multivector[dim][dim];

		constructElements();

		constructTable(metric);


	}

	private void constructElements(){
		elements[0] = new Element("1", 0, new Element[]{});
		for(int i = 1; i <= bases; i++){
			Element el = new Element(defaultNames.substring(1,2)+i, 1, new Element[1]);
			el.components[0]=el;
			elements[i] = el;
		}

		int index = 1 + bases;

		for(int grade = 2; grade <= bases-1; grade++){
			int[][] combos = Combinatorics.getSubsets(bases, grade);
			for(int i = 0; i < combos.length; i++){
				int[] combo = combos[i];
				//gets the basis vectors for this combination
				Element[] set = new Element[grade];
				for(int j = 0; j < combo.length; j++){
					set[j] = elements[combo[j]+1]; 
				}
				Element el = new Element(defaultNames.substring(grade, grade+1) + (i+1), grade, set);
				elements[index] = el;
				index ++;

			}

		}
		Element[] max = new Element[bases];
		for(int i = 1; i<= bases; i++){
			max[i-1] = elements[i];
		}
		elements[index] = new Element("i", bases, max);

		for(Element e: elements){
			names.add(e.name);
		}
	}


	private void constructTable(int[] metric){
		table[0][0] = new Scalar(this, 1);
		for(int i = 0; i < dim; i++){
			table[0][i] = new Blade(this, elements[i]);
			table[i][0] = new Blade(this, elements[i]);
		} //identity maps everything to itself

		for(int i = 1; i <= bases; i++){
			for(int j = 1; j <= bases; j++){
				if(i == j) table[i][i] = new Scalar(this, metric[i-1]);
			}
		}

		for(int i = 1; i < dim; i++){
			for(int j = 1; j < dim; j++){
				if(table[i][j] != null) continue;
				table[i][j] = createProduct(elements[i], elements[j]);


			}
		}


		// now have to figure out how to construct the rest...
	}


	
	private Multivector createProduct(Element e1, Element e2){

		ArrayList<Element> comp1 = new ArrayList<Element>(), comp2 = new ArrayList<Element>();
		for(Element e : e1.components) comp1.add(e);
		for(Element e : e2.components) comp2.add(e);

		ArrayList<Element> overlap = new ArrayList<Element>();
		for(Element e : comp1){
			if(comp2.contains(e)) overlap.add(e);
		}
		int total = 1;
		if(overlap.size() != 0){
			//make total = the product of all the contracted elements. -1s for swaps and then the metric component for that element.
			for(Element e : overlap){
				int t = indexOf(e);
				if(table[t][t].isZero()) return new Scalar(this, 0); //if any component is duplicated and squares to 0 the whole thing is zero.


				int i = comp1.indexOf(e);
				if(i != comp1.size()-1){
					swap(comp1, i, comp1.size()-1);
					total *= -1;
				}
				int j = comp2.indexOf(e);
				if(j != 0){
					swap(comp2, j, 0);
					total *= -1;
				}
				comp1.remove(comp1.size()-1);
				comp2.remove(0);
				total *= table[t][t].components[0]; // HACK - this assumes vectors square to scalars and all components are scalars.
			}


		}

		comp1.addAll(comp2); //now that all overlaps have been removed... find what element this corresponds to and get the sign right.


		Element e = findElementFromComponents(comp1);
		//naive version: swap each element till you get the right one.
		for(int i = 0; i < e.components.length; i++){
			int j = comp1.indexOf(e.components[i]);
			if(i != j){
				swap(comp1, i, j);
				total *= -1; //swap each element into place and figure out the sign.
			}

		}
		Multivector mv = new Multivector(this, e);
		return mv.scale(total);
		





	}

	int indexOf(Element e){
		for(int i = 0; i < elements.length; i++){
			if(elements[i].equals(e)) return i;
		}
		return -1;
	}
	
	int indexOf(String elementName){
		for(int i = 0; i < elements.length; i++){
			if(elements[i].name.equals(elementName)) return i;
		}
		return -1;
	}

	private <T> void swap(List<T> l, int i, int j){
		T temp = l.get(i);
		l.set(i, l.get(j));
		l.set(j, temp);
	}


	public Element findElementFromComponents(Collection<Element> comps){
		loop: for(Element e : elements){
			if(comps.size() != e.components.length) continue loop;
			for(Element sub : e.components){
				if(!comps.contains(sub)) continue loop;
			}
			return e;
			
		}
		
		return null;
	}



	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("Algebra ").append(getName()).append(":\n");
		for(Element e : elements){
			sb.append(e.toString()).append("\n");
		}
		sb.append("Multiplication Table: \n");
		for(Multivector[] es : table){
			for(Multivector mv : es){
				String s = String.format("%8s", mv.toString());
				sb.append(s);
			}
			sb.append("\n");
		}

		return sb.toString();
	}

	public String getName(){
		return name;
	}
	

	
}
