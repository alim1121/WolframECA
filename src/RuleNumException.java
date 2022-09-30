
/**
 * This class will check if the given integer is outside valid range.
 * 
 * @author MuhammadAli, RafalJabrzemski
 *
 */
public class RuleNumException extends Exception {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor that returns message when object is initialized. 
	 * 
	 * @param min lower end of the range
	 * @param max high end of the range.
	 */
	public RuleNumException(int min, int max) {
		super("ruleNum is outside the range [" + min + ", " + max + "].");
	}
	

}
