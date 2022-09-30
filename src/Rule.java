/**
 * The Rule class will be used to generate either a totalistic or elementary rule, using the given ruleNum. 
 * @author Muhammad Ali
 *
 */
public abstract class Rule {
	private int ruleNum;
	
	protected Rule(int ruleNum) {
		this.ruleNum = ruleNum;
		
	}
	
	/**
	 * Gives the rule number passed to the constructor.
	 * @return the rule number passed to the constructor.
	 */
	public int getRuleNum() {
		return ruleNum;
	}
	
	/**
	 * Gives the neighborhood of the generation around the given index.
	 * @param idx The center of the neighborhood desired.
	 * @param gen The generation from which the neighborhood will be derived from.
	 * @return A boolean[] of cellStates from generation around idx.
	 */
	public abstract boolean[] getNeighborhood(int idx, Generation gen);
	
	/**
	 * Evolves the neighborhood, changing  if used by ElementaryRule or TotalisticRule.
	 * 
	 * @param neighborhood The neighborhood around the index to be evolved.
	 * @return The center of the neighborhood's state after being evolved.
	 */
	public abstract boolean evolve(boolean[] neighborhood);
	
	/**
	 * Creates a table, with the top line being the possibilities/possible sums, depending on if used by ElementaryRule or TotalisticRule.
	 * 
	 * @param falseSymbol represents false, will replace false in the binary form of the rule.
	 * @param trueSymbol represents true, will replace true in the binary form of the rule.
	 * @return table, with the top line being the possibilities/possible sums, depending on if used by ElementaryRule or TotalisticRule.
	 */
	public abstract String ruleTableString(char falseSymbol, char trueSymbol);
	
	/**
	 * Gets the surrounding cells around the index in a Generation, going to the left and right as far as the radius tells.
	 * 
	 * @param idx The center of the neighborhood desired.  
	 * @param radius The desired amount of cells to include on either side of the index.
	 * @param gen The generation that the neighborhood will be derived from.
	 * @return boolean[] of cellStates for idx and the radius around it
	 */
	public static boolean[] getNeighborhoodByRadius(int idx, int radius, Generation gen) {
		boolean[] result = new boolean[radius*2 +1]; 
		int index;
		for(int i = idx -radius, j = 0; i <= idx + radius; ++i, ++j) {
			index = Math.floorMod(idx - radius + j , gen.size());
			result[j] = gen.getState(index);
		}
		return result;
		
		//Helped by Aaron Coker and Nicholas May
	}
	
	/**
	 * Evolves every state of the given Generation.
	 * 
	 * @param gen The Generation to be evolved.
	 * @return The evolved Generation of gen.
	 */
	public Generation evolve(Generation gen) {
		int sizeNeeded = gen.size();//Use this to determine size of new array and when to stop cycling through loop
		boolean[] states = new boolean[sizeNeeded];
		int i = 0;
		while (i <= sizeNeeded -1) {
			//get (i-1,i,i+1) of the current gen and run it through the evolve method that passed already
			states[i] = evolve(getNeighborhood(i, gen));
			++i;
		}
		
		Generation evolution = new Generation(states);
		return evolution;
		
	}

}
