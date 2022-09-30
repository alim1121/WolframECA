import java.util.Arrays;


/**
 * The Generation class represents a row of cells in a 2-State Cellular Automaton.
 * @author Muhammad Ali
 *
 */
public class Generation {
	//Must make immutable
	//Think of a generation as a row on the image
	//This class is the exact same code used in my project1
	private boolean[] cellStates;
	
	/**
	 * Creates a Generation with the given states. 
	 * 
	 * @param states Boolean values that represent a row of cells in a 2-State Cellular Automaton initially.
	 */
	public Generation(boolean... states) {
		//cellStates = new boolean[states.length];
		if(states == null || states.length == 0) {
			//may need to edit for array of null elements
			cellStates = new boolean[1];
			cellStates[0] = false;
		}
		else {
			cellStates = new boolean[states.length];
			for (int i = 0; i < states.length; ++i) {
				cellStates[i] = states[i];
			}
		}
	}
	
	/**
	 * Creates a Generation with a given String of states, along with a trueSymbol, to convert it into a boolean[] of cellStates.
	 * 
	 * @param states represents values of a row of cells. 
	 * @param trueSymbol Represents true, will be used on String to convert it into booleans.
	 */
	public Generation(String states, char trueSymbol) {
		//First, convert states into array
		if ( states == null) {
			cellStates = new boolean[1];
			cellStates[0] = false;
		}
		else if (states == "") {
			cellStates = new boolean[1];
			cellStates[0] = false;
		}
			
		else {
			cellStates = new boolean[states.length()];
			char[] statesChars = new char[states.length()];
			for (int i = 0; i < states.length(); ++i) {
				statesChars[i] = states.charAt(i);
			}
		
			for(int i =0; i < statesChars.length; ++i) {
				if(statesChars[i] == trueSymbol)
					cellStates[i] = true;
				else
					cellStates[i] = false;
			}
		}
	}
	
	/**
	 * Returns the state of an index in the generation.
	 * @param idx The index of the state desired. 
	 * @return Boolean value of state at the given index.
	 */
	public boolean getState(int idx) {
		return cellStates[idx];
	}
	
	/**
	 * Returns the states of the generation.
	 * @return Copy of generation, to ensure that generation stays immutable.
	 */
	public boolean[] getStates() { 
		boolean[] copy = Arrays.copyOf(cellStates, cellStates.length);
		return copy;
	}
	
	/**
	 * Returns the states of the generation, but translates the boolean values into the given falseSymbol and trueSymbol.
	 * 
	 * @param falseSymbol The character used to represent false.
	 * @param trueSymbol The character used to represent true.
	 * @return String of generation, with true and false replaced with trueSymbol and falseSymbol, respectively.
	 */
	public String getStates(char falseSymbol, char trueSymbol) {
		String stateString = "";
		for (int i = 0; i < cellStates.length; ++i) {
			if(cellStates[i] == true)
				stateString += trueSymbol;
			else
				stateString += falseSymbol;
		}
		
		return stateString;
	}
	
	/**
	 * Gives the number of elements in the generation.
	 * 
	 * @return The number of elements in the generation.
	 */
	public int size() {
		return cellStates.length;
	}
	

}