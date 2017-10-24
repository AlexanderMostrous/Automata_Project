import java.util.ArrayList;

public class AutomataRunner {

	private ArrayList<State> currentStates, allStates;
	
	public AutomataRunner(ArrayList<State> allStates){
		
		this.allStates=allStates;
		currentStates = new ArrayList<State>();
		currentStates.add(getStartingState());
		
	}
	
	/*
	 * 
	 * This methods receives a symbol and simulates the automata transition based on it.
	 * It returns true or false based on whether this symbol produces a transition or not.
	 */
	//TODO DEBUG 3
	public boolean consumeNextCharacter(String symbol){
		boolean isLegalTransition = true;
		
		ArrayList<State> temp = getNextStates(symbol);
		if(!temp.isEmpty())
			currentStates = temp;
		else
			isLegalTransition = false;
			
		//TODO Remove those lines. For debug purpose only.
		if(isLegalTransition)
		{
			System.out.println("It's a legal transition.");
			for(State s:currentStates)
				System.out.print(s.getName()+", ");
		}
		else
			System.out.println("It's not a legal transition.");

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
		
		for(State s:allStates)
			if(s.isStarting())
				return s;
		
		return null;
	}
}
