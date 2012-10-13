package clifford;

@SuppressWarnings("serial")
public class DuplicateNameException extends Exception {

	public DuplicateNameException(String s){
		duplicate = s;
	}
	public String duplicate;
}
