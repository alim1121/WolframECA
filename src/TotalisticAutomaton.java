import java.io.IOException;

/**
 * This class extends from Automaton and will create a Totalistic Automaton, which has a rule that evolves based on the sums of its neighborhood.
 * @author Muhammad Ali
 *
 */
public class TotalisticAutomaton extends Automaton{

	
	protected TotalisticAutomaton(int ruleNum, Generation initial) throws RuleNumException {
		super(ruleNum, initial);
	}
	
	protected TotalisticAutomaton(String filename) throws IOException, RuleNumException {
		super(filename);
	}

	@Override
	protected Rule createRule(int ruleNum) throws RuleNumException {
		Rule totResult = new TotalisticRule(ruleNum);

		return totResult;
	}

}
