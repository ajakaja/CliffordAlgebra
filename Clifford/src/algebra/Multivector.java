package algebra;

public class Multivector {
	
	double[] components;
	
	Algebra alg;

	public Multivector(Algebra a){
		alg = a;
		components = new double[a.dim];
		for(int i = 0; i < a.dim; i++){
			components[i] = 0;
		}
	}
	
	public Multivector(Algebra a, Element e){
		alg = a;
		components = new double[a.dim];
		for(int i = 0; i < a.dim; i++){
			components[i] = (a.elements[i].equals(e)) ? 1 : 0;
		}
	}
	
	public Multivector(Algebra a, double[] comp){
		alg = a;
		components = new double[a.dim];
		for(int i = 0; i < comp.length; i++){
			components[i] = comp[i];
		}
		for(int i = comp.length; i < a.dim; i++){
			components[i] = 0;
		}
	}
	
	
	public Multivector multiply(Multivector other){		
		Multivector ret = new Multivector(alg);
		for(int i = 0; i < alg.dim; i++){
			for(int j = 0; j < alg.dim; j++){
				if(this.components[i] == 0 || other.components[j] == 0) continue;
				Multivector part = alg.table[i][j];
				ret = ret.addScale(part, this.components[i]*other.components[j]);
				
			}
		}
		return ret;
	}
	
	
	private Multivector addScale(Multivector other, double scale){

		double[] cs = new double[alg.dim];
		for(int i = 0; i < cs.length; i++){
			cs[i] = components[i] + scale* other.components[i];
		}
		
		return new Multivector(alg, cs);
	}
	
	
	public Multivector dot(Multivector other){
		return ((this.multiply(other).add(other.multiply(this))).scale(0.5));

		
	}
	
	public Multivector wedge(Multivector other){
		return ((this.multiply(other).subtract(other.multiply(this))).scale(0.5));

	}
	
	public Multivector add(Multivector other){
		return addScale(other, 1);
	}
	
	
	
	public Multivector subtract(Multivector other){

		return addScale(other, -1);
	}
	
	public Multivector negate(){
		return scale(-1);
	}

	public Multivector scale(double s){
		double[] cs = new double[alg.dim];
		for(int i = 0; i < cs.length; i++){
			cs[i] = s * components[i];
		}
		
		return new Multivector(alg, cs);
	}
	
	
	boolean isZero(){
		for(double i : components){
			if(i != 0) return false;
		}
		return true;
	}
	
	public String toString(){
		String s = "";
		for(int i = 0; i < components.length; i++){
			if(components[i] != 0){
				
				if(s.length() != 0){ s += "+"; }
				if(components[i] == 1){
					s = s + alg.elements[i].name;
				}else if(components[i] == -1){
					s = s + "-" + alg.elements[i].name;
				}else{
					s = s+ Math.round(components[i]*100)/100 + "*"+ alg.elements[i].name;
				}
			}
		}
		if(s.equals("")) s = "0";
		return s;
		
	}

	
	
}
