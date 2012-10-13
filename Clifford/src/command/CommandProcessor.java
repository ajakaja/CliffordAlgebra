package command;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class CommandProcessor{
	private PrintStream out;
	private Scanner in;
	private PrintStream err;
	
	private HashMap<String, Command> commands = new HashMap<String, Command>();

	private ArrayList<String[]> previous = new ArrayList<String[]>();
	
	boolean running;
	
	
	public CommandProcessor(){
		this(System.in, System.out, System.err);
		
	}
	
	
	public CommandProcessor(InputStream input, PrintStream output, PrintStream error){
		in = new Scanner(input);
		out = output;
		err = error;
		PreviousCommand pc = new PreviousCommand(this);
		QuitCommand q = new QuitCommand();
		addCommand(pc, "p");
		addCommand(q, "q");
		
		Runnable run = getRunnable();
		new Thread(run).start();
	
	}
	

	private Runnable getRunnable(){
		return new Runnable(){
			@Override
			public void run() {
				running = true;
				while(running && in != null){
					String s = in.nextLine();
					parse(s.split(" "));
				}
			}
		};
	}
	
	
	public void addInput(String input){
		if(input != null && input.length() != 0){
			parse(input.split(" "));
		}
	}
	
	public void addInput(String[] input){
		if(input != null && input.length > 0){
			parse(input);
		}
	}

	
	private void parse(String[] input){
		Command c ;
		synchronized(this){
			c = commands.get(input[0]);
		}
		if(c == null){
			err.println("Unrecognized command: " + input[0] + ".");
		}else{
			if(!(c instanceof PreviousCommand)){
				previous.add(input);
			}
			c.process(out, err, input);
		}
		
	}


	void runPrevious(int i) {
		if(i >= previous.size()){
			err.println("No history that far back."); 
			return;
		}
		String[] s = previous.get(previous.size()-1-i);
		parse(s);
	}
	
	public void addCommand(Command c, String... comm){
		synchronized(this){
			for(String s: comm){
				commands.put(s.toLowerCase(),  c);
			}
		}
	}
	
	
	public static void main(String[] args){
		(new CommandProcessor()).addCommand(new EchoCommand(), "echo");
	}
	
	public void close(){
		running = false;
	}
}
