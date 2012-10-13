package clifford;

@SuppressWarnings("serial")
public class UnknownNameException extends Exception {
	public UnknownNameException(String... names){
		unknowns = names;
	}
	public String[] unknowns;
}
