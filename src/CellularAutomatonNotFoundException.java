
/**
 * Exception that is thrown if Cellular Automaton is unknown, meaning it is neither ECA nor TCA.
 * @author Muhammad Ali
 *
 */
public class CellularAutomatonNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Gives a message ending with the unknown automaton type.
	 * 
	 * @param s The unknown Cellular Automaton.
	 */
	public CellularAutomatonNotFoundException(String s) {
		super("Unknown cellular automaton type " + s);
	}
}
