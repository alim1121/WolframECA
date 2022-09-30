/**
 * This class accepts command line arguments and gives evolutions for ElementaryCAs and TotalisticCAs.
 * @author Muhammad Ali
 *
 */
public class Application {

	private static final int NUM_EXPECTED_ARGS = 6;

	private static final int IDX_CA = 0;
	private static final int IDX_RULE_NUM = 1;
	private static final int IDX_FALSE_SYMBOL = 2;
	private static final int IDX_TRUE_SYMBOL = 3;
	private static final int IDX_INITIAL_GENERATION = 4;
	private static final int IDX_NUM_EVOLVE = 5;

	private static final String ARG_NAMES = "ca rule-num false-symbol true-symbol initial-generation num-evolutions";
	
	// Source and class file names must match, so Application can be hard-coded
	private static final String USAGE_FMT_STRING_CLASS = "Usage: java Application " + ARG_NAMES;

	// The jar file may be renamed, so this will be varied
	private static final String USAGE_FMT_STRING_JAR = "Usage: java -jar %s " + ARG_NAMES;

	private String[] appArgs;
	
	/**
	 * Creates an Application and checks if the correct number of arguments were passed.
	 * 
	 * @param args The arguments received from the command line.
	 */
	public Application(String[] args) {
		validateNumArgs(args);
	}

	private void validateNumArgs(String[] args) {
		// TODO: Validate the number of arguments and throw an exception
		// if they do not match the expected amount.
		if(args.length != NUM_EXPECTED_ARGS)
			throwRuntimeExceptionWithUsageMessage();
		else
			appArgs = args;
	}

	private void throwRuntimeExceptionWithUsageMessage() {
		// Implementation provided
		if (runningAsJar()) {
			// Get the path to the executing file
			String path = Application.class
					.getProtectionDomain()
					.getCodeSource()
					.getLocation()
					.getPath();
			// Only take path after last slash (to get file name).
			// A hard-coded slash is fine since Java URLs use /
			String file = path.substring(path.lastIndexOf("/") + 1);
			throw new RuntimeException(String.format(USAGE_FMT_STRING_JAR, file));
		} else {
			throw new RuntimeException(String.format(USAGE_FMT_STRING_CLASS));
		}
	}

	private boolean runningAsJar() {
		// Implementation provided
		return Application.class
				.getResource("Application.class")
				.toString()
				.startsWith("jar");
	}

	private void parseArgs(String[] args) {
		// TODO: Parse each of the six arguments, construct the appropriate 
		// Automaton, and print out the full evolution to System.out. 
		// Refer to the README for details on exception handling.
		try {
			CellularAutomaton ca = CellularAutomaton.parse(args[0]);
			int ruleNum = Integer.parseInt(args[1]);
			char falseSymbol = args[2].charAt(0);
			char trueSymbol = args[3].charAt(0);
			String initial = args[4];
			int numSteps = Integer.parseInt(args[5]);
			
			//Now begin constructing with the given variables
			Generation genZero = new Generation(initial, trueSymbol);
			Automaton automaton = Automaton.createAutomaton(ca, ruleNum, genZero);
			
			//Now print all evolutions to with System.out.print() for numSteps duration, including genZero;
			//Helped by Abe A.
			automaton.evolve(numSteps);
			String printString = automaton.toString();
			printString = printString.replace('0', falseSymbol);
			printString = printString.replace('1', trueSymbol);
			System.out.println(printString);
			
			
		}catch(Exception e) {
			//This is where the errors will be printed.
			throw new RuntimeException(e.getMessage());
		}
		
		
	}

	/**
	 * run() will parse the arguments from the input, and print out the full evolution.
	 * 
	 */
	public void run() {
		// TODO: Call the parseArgs method using the previously 
		// given arguments.
		parseArgs(appArgs);
	}

	/**
	 * The main method will create an Application and run it, printing out any errors.
	 * @param args is series of Strings from command line given by the user.
	 */
	public static void main(String[] args) {
		// TODO: Construct and run an Application using the 
		// supplied main method arguments. Refer to the README
		// for details on RuntimeException handling.
		try {
			Application application = new Application(args);
			application.run();
			
		}catch(Exception e) {
			System.err.println(e.getMessage());
		}
		
		
	}
}
