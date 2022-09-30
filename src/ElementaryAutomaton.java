import java.io.IOException;

/**
 * Extending from Automaton, this class creates a elementary automaton, creates a rule with elementary rule.
 * @author Muhammad Ali
 *
 */
public class ElementaryAutomaton extends Automaton{

	protected ElementaryAutomaton(int ruleNum, Generation initial) throws RuleNumException {
		super(ruleNum, initial);
	}
	
	protected ElementaryAutomaton(String filename) throws IOException, RuleNumException {
		super(filename);
	}

	@Override
	protected Rule createRule(int ruleNum) throws RuleNumException {
		Rule elemResult = new ElementaryRule(ruleNum);
		
		return elemResult;
	}
	
	

}
