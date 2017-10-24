import java.util.ArrayList;

public class AutomataRunner {

	private ArrayList<State> currentStates;
	
	public AutomataRunner(){
		
		currentStates = new ArrayList<State>();
		currentStates.add(getStartingState());
		
	}
	
	
	/*
	 * 
	 * This methods receives a symbol and simulates the automata transition based on it.
	 * It returns true or false based on whether this symbol produces a transition or not.
	 */
	public boolean consumeNextCharacter(String symbol){
		boolean isLegalTransition = true;
		
		ArrayList<State> temp = getNextStates(symbol);
		if(!temp.isEmpty())
			currentStates = temp;
		else
			isLegalTransition = false;
			
		return isLegalTransition;
	}
	
	/*
	 * For every state that is included in ArrayList<State> currentStates
	 * searches the next states based on the transition that is produced by the input symbol.
	 */
	private ArrayList<State> getNextStates(String symbol){
		ArrayList<State> newStates = new ArrayList<State>();
		
		for(State s:currentStates)
		{
			s.getNextState(symbol);
		}
		
		return newStates;
	}
	
	/*
	 * 
	 * This method searches and returns the starting state.
	 * 
	 */
	private State getStartingState(){
		
		for(State s:currentStates)
			if(s.isStarting())
				return s;
		
		return null;
	}
}
