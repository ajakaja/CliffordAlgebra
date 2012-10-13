package algebra;

public class Scalar extends Multivector {

	private Scalar(Algebra a, double[] components) {
		super(a, components);
	}
	
	private Scalar(Algebra a, Element e){
		super(a, e);
	}
	
	public Scalar(Algebra a, double r){
		super(a, new double[]{r});
	}

}
