/**
 * This is used to determine whether a String represents an ElementaryCellularAutomaton or TotalisticCellularAutomaton.
 * 
 * @author Muhammad Ali
 *
 */
public enum CellularAutomaton {

	/**
	 * ECA represents elementary cellular automaton.
	 */
	ECA,
	/**
	 * TCA represents totalistic cellular automaton.
	 */
	TCA;
	
	/**
	 * This determines whether a String represents an ElementaryCellularAutomaton or TotalisticCellularAutomaton.
	 * 
	 * @param s is the String that will either be ECA or TCA.
	 * @return ECA or TCA, depending on String. 
	 * @throws CellularAutomatonNotFoundException Thrown if s is neither ECA or TCA.
	 */
	public static CellularAutomaton parse(String s) throws CellularAutomatonNotFoundException {
		if (s.equalsIgnoreCase("ECA")) { 
			return ECA;
		}
		else if (s.equalsIgnoreCase("TCA")) {
			return TCA;
		}
		else {
			throw new CellularAutomatonNotFoundException(s);
		}
	}
}
