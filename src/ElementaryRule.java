import java.util.Arrays;

/**
 * Elementary Rule creates a rule that evolves based on the arrangement of it's neighborhood states.
 * 
 * @author Muhammad Ali
 *
 */
public class ElementaryRule extends Rule {
	private String binaryRule;

	
	protected ElementaryRule(int ruleNum) throws RuleNumException {
		super(ruleNum);
		if(ruleNum > 255 || ruleNum < 0)
			throw new RuleNumException(0, 255);
		
		
		String rule = Integer.toBinaryString(getRuleNum());
		StringBuilder tempRule = new StringBuilder();
		while(tempRule.length() < 8 - rule.length()) {
			tempRule.append('0');
		}
		tempRule.append(rule);
		binaryRule = tempRule.toString();
	}

	/**
	 * getNeighborhood will return a boolean[] with the index, along with one element to its left and right.
	 * 
	 * @param idx The element at the center of the neighborhood.
	 * @param gen The Generation that the neighborhood will be derived from.
	 * @return boolean[] of length 3 taken from gen around given idx.
	 */
	@Override
	public boolean[] getNeighborhood(int idx, Generation gen) {
		return getNeighborhoodByRadius(idx, 1, gen);
	}


	/**
	 * Evolves the rule and returns the center value, based on the arrangement of booleans in the neighborhood.
	 * 
	 * @param neighborhood boolean[] with three elements, the center of this array will be evolved.
	 * @return boolean that represents the evolution of neighborhood's center element.
	 */
	@Override
	public boolean evolve(boolean[] neighborhood) {
		
		//List the different possibilities, put in array representation
		boolean[] pos1 = {true, true, true};
		boolean[] pos2 = {true, true, false};
		boolean[] pos3 = {true, false, true};
		boolean[] pos4 = {true, false, false};
		boolean[] pos5 = {false, true, true};
		boolean[] pos6 = {false, true, false};
		boolean[] pos7 = {false, false, true};
		boolean[] pos8 = {false, false, false};
		//Put possibilities into array that will be cycled through
		boolean[][] chances = {pos1, pos2, pos3, pos4, pos5, pos6, pos7, pos8};
		
		//Create an array of characters for the binary rule
		char[] binary = new char[binaryRule.length()];
		for (int i = 0; i < binaryRule.length(); ++i) {
			binary[i] = binaryRule.charAt(i);
		}
		
		//Find when chances[] == '1'
		int [] whereChancesIs1 = new int[chances.length];
		int z = 0;
		//Z is just to assign some value to whereChanceis1
		
		boolean truth = false;
		for(int j = 0; j < chances.length; ++j) {
			if(binary[j] == '1') {
				whereChancesIs1[z] = j;
				++z;
				if (truth != true)
					truth = true;
			}
		}
		//This checks for if no 1 occurs in the binary
		if (truth == false)
			return false;
		else {
		//To "trim" the array, and an if statement, if(whereChancesIs1[k] == 0 && k != 0), break
			for (int k = 0; k < whereChancesIs1.length; ++k) {
				if(whereChancesIs1[k] == 0 && k != 0)
					break;
				else if (Arrays.equals(neighborhood, chances[whereChancesIs1[k]]))
					return true;
			}
			return false;
		}
	
	}

	/**
	 * Returns a string of two lines, the first gives the possible cases, and the second giving the binary for the rule.
	 * 
	 * @param falseSymbol The given representation of false.
	 * @param trueSymbol The given representation of true.
	 * @return String table of two lines that displays possible cases and binary of rule.
	 */
	@Override
	public String ruleTableString(char falseSymbol, char trueSymbol) {
		//Ethan Ho assisted with this method
		char tempFalse = '\0';
		char tempTrue = '\1';
		
		String table = "111 110 101 100 011 010 001 000" + System.lineSeparator() + " ";

		for(int i = 0; i < binaryRule.length(); ++i) {
			if (i == binaryRule.length()-1)
				table += binaryRule.charAt(i) + " ";
			else
				table += binaryRule.charAt(i) + "   ";
		}
		
		table = table.replace('0', tempFalse);
		table = table.replace('1', tempTrue);
		
		table = table.replace(tempFalse, falseSymbol);
		table = table.replace(tempTrue, trueSymbol);
	
		return table;
	}
	
}
