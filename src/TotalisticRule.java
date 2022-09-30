/**
 * THis class creates a Totalistic Rule, which evolves depending on the sum of the states of cells next to it, instead of the arrangement. 
 * @author Muhammad Ali
 *
 */
public class TotalisticRule extends Rule{
	private String binaryRule;
	private String firstRow;
	private String secRow;

	protected TotalisticRule(int ruleNum) throws RuleNumException {
		super(ruleNum);
		if(ruleNum > 63 || ruleNum < 0)
			throw new RuleNumException(0, 63);
		
		String rule = Integer.toBinaryString(getRuleNum());
		StringBuilder tempRule = new StringBuilder();
		while(tempRule.length() < 6 - rule.length()) {
			tempRule.append('0');
		}
		tempRule.append(rule);
		binaryRule = tempRule.toString();
	}
	

	/**
	 * Gives the neighborhood of the generation around the given index.
	 * @param idx The center of the neighborhood desired.
	 * @param gen The generation from which the neighborhood will be derived from.
	 * @return A boolean[] of cellStates from generation around idx.
	 */
	@Override
	public boolean[] getNeighborhood(int idx, Generation gen) {
		return getNeighborhoodByRadius(idx, 2, gen);
	}

	/**
	 * Evolves the neighborhood based upon the number of true cells in the neighborhood.
	 * 
	 * @param neighborhood The neighborhood around the index to be evolved.
	 * @return The center of the neighborhood's state after being evolved.
	 */
	@Override
	public boolean evolve(boolean[] neighborhood) {
		
		//This code counts the number of true in the neighborhood
		int count = 0;
	    for (int i = 0; i < neighborhood.length; ++i) {
	    	if(neighborhood[i] == true)
	    		++count;
	    }

	    //Now that we have the total trues in the neighborhood
	    //We should map it to the ruleTable that we have using the private variables created in the ruleTableString method
	    ruleTableString('f', 't');//These symbols are arbitrary, we just want the first&secondRow variables to be initialized
	    
	    int indexOfCount = firstRow.indexOf(Integer.toString(count));
	    if (secRow.charAt(indexOfCount) == '1')
	    	return true;
	    return false;
	}

	
	/**
	 * Creates a table, with the top line being the possible sums, and the bottom being the binary of the rule, replaced with the given symbols.
	 * 
	 * @param falseSymbol represents false, will replace false in the binary form of the rule.
	 * @param trueSymbol represents true, will replace true in the binary form of the rule.
	 * @return table, String with the top line being the possible sums, and the bottom being the binary of the rule, replaced with the given symbols.
	 */
	@Override
	public String ruleTableString(char falseSymbol, char trueSymbol) {
		//Assisted by Ethan Ho on this method
		char tempFalse = '\0';
		char tempTrue = '\1';
		
		String firstRow = "5 4 3 2 1 0";
		this.firstRow = firstRow;
		firstRow += System.lineSeparator();

		String secRow = "";
		for(int i = 0; i < binaryRule.length(); ++i) {
			if (i == binaryRule.length()-1)
				secRow += binaryRule.charAt(i);
			else
				secRow += binaryRule.charAt(i) + " ";
		}
		//The reason for this variable is so it can be used in evolve
		this.secRow= secRow;
		
		secRow = secRow.replace('0', tempFalse);
		secRow = secRow.replace('1', tempTrue);
		
		secRow = secRow.replace(tempFalse, falseSymbol);
		secRow = secRow.replace(tempTrue, trueSymbol);

		
		return firstRow + secRow;
	}

}
