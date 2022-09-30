import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringJoiner;

/**
 * The class Automaton inherits from Rule and Generation, and creates either totalistic or elementary rules.
 * 
 * @author MuhammadAli
 *
 */
public abstract class Automaton {
	private Rule rule;
	private ArrayList<Generation> generations;
	/**
	 * falseSymbol is initialized with '0', and represents false.
	 * 
	 */
	public char falseSymbol = '0';
	/**
	 * trueSymbol is initialized with '1', and represents true.
	 * 
	 */
	public char trueSymbol = '1';

	protected Automaton(int ruleNum, Generation initial) throws RuleNumException {
		rule = createRule(ruleNum);
		generations = new ArrayList<Generation>();
		generations.add(initial);
	}

	protected Automaton(String filename) throws IOException, RuleNumException/* CellularAutomatonNotFoundException*/{

		//read in lines from file, split line 2 into true and false symbol
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String firstLine = br.readLine();
		rule = createRule(Integer.parseInt(firstLine.trim()));
		String secondLine = br.readLine();
		String[] partsOfLine = secondLine.split(" ");
		falseSymbol = partsOfLine[0].charAt(0);
		trueSymbol = partsOfLine[1].trim().charAt(0);
		String thirdLine = br.readLine().trim();
		//Create array, then generation, then add to generations
		generations = new ArrayList<Generation>();
		boolean[] genParam = new boolean[thirdLine.length()];

		for(int i = 0; i < thirdLine.length(); ++i) {
			if (thirdLine.charAt(i) == falseSymbol)
				genParam[i] = false;
			else
				genParam[i] = true;
		}
		br.close();
		Generation genToPass = new Generation(genParam);
		generations.add(genToPass);

	}
	/**
	 * createAutomaton decides whether to create a a totalistic or elementary automaton based on the given CellularAutomaton.
	 * 
	 * @param ca Tells the method to create either an elementary or totalistic rule. 
	 * @param ruleNum Will be passed to ElementaryAutomaton or TotalisticAutomaton for their constructor.
	 * @param initial Generation to be passed to elementary or totalistic automaton constructor.
	 * @return Automaton of either type elementary or totalistic
	 * @throws RuleNumException if ruleNum is out of bounds for given CellularAutomaton ca.
	 */
	public static Automaton createAutomaton(CellularAutomaton ca, int ruleNum, Generation initial) throws RuleNumException {
		if(ca == null)
			return null;
		if(CellularAutomaton.ECA == ca) {
			Automaton resultE = new ElementaryAutomaton(ruleNum, initial);
			return resultE;
		}
		else {
			Automaton resultT =  new TotalisticAutomaton(ruleNum, initial);
			return resultT;
		}
	}
	
	/**
	 * This method evolves the generations by the given number of steps.
	 * 
	 * @param numSteps gives the number of steps to evolve the generation by.
	 * @return the number of steps evolved.
	 */
	public int evolve(int numSteps) {
		if (numSteps <= 0) {
			return 0;
		}
		else {
			int i = 0;
			while (i < numSteps) {
				generations.add(rule.evolve(getCurrentGeneration()));
				++i;
			}
			return numSteps;
		}
	}
	
	/**
	 * This method returns the generation at the given step number. 
	 * 
	 * @param stepNum The index of the generation to get. 
	 * @return The generation at the give step number.
	 */
	public Generation getGeneration(int stepNum) {
		if (generations.size() < stepNum) {
			for (int i = generations.size(); i <= stepNum; ++i) {
				generations.add(rule.evolve(getCurrentGeneration()));
			}
		
		}
		Generation result = generations.get(stepNum);
		return result;
	}
	
	/**
	 * This will return the latest generation. 
	 * 
	 * @return the most recent generation.
	 */
	public Generation getCurrentGeneration() {
		return generations.get(generations.size()-1);
	}
	
	/**
	 * This will get the rule Number of the Automaton.
	 * 
	 * @return the rule number of the Automaton.
	 */
	public int getRuleNum() {
		int ruleNumber = rule.getRuleNum();
		return ruleNumber;
	}
	
	/**
	 * Gives the total number of evolutions made in the automaton.
	 * 
	 * @return the total number of evolutions made in the automaton.
	 */
	public int getTotalSteps() {
		return generations.size() -1;
	}
	
	/**
	 * Saves an evolution to a file.
	 * 
	 * @param filename Gives the name of the file.
	 * @throws IOException necessary for FileWriter.
	 */
	public void saveEvolution(String filename) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
		bw.write(toString());
		bw.close();
	}
	
	/**
	 * This returns a string representation for all generations.
	 * 
	 * @return String representation of all generations.
	 */
	@Override
	public String toString() {
		StringJoiner joiner = new StringJoiner(System.lineSeparator());
		for(int i = 0; i < generations.size(); ++i) {
			 joiner.add(getGeneration(i).getStates(falseSymbol, trueSymbol));
		}
		return joiner.toString();
	}
	
	/**
	 * Makes a ruleTable of the rule, either totalistic or elementary.
	 * 
	 * @return String with two lines, depending on whether elementary or totalistic was called. 
	 */
	public String ruleTableString() {
		return rule.ruleTableString(falseSymbol, trueSymbol);
	}
	

	protected abstract Rule createRule(int ruleNum) throws RuleNumException; 


}
