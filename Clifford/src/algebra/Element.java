package algebra;

public class Element {

	
	int grade;
	String name;
	Element[] components;
	
	public Element(String n, int g, Element[] c){
		grade = g;
		name = n;
		components = c;
	}
	
	public String toString(){
		String s = grade + "-vector " + name;
		if(components.length != 0){
			s = s + ": " + components[0].name;
			for(int i = 1; i < components.length; i++){
				s += " ^ " +components[i].name;
			}
		}
		return s;
	}
}
