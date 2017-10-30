import java.util.ArrayList;

public class AutomataRunner {

	private ArrayList<State> currentStates, allStates;

	public AutomataRunner(ArrayList<State> allStates){

		this.allStates=allStates;
		currentStates = new ArrayList<State>();
		State stst = getStartingState();
		currentStates.add(stst);
		System.out.println("Starting state is: "+stst.getName());
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
		{
			System.out.println("List is empty.");
			isLegalTransition = false;
		}

		//TODO Remove this line. For debug purpose only.
		printIfLegal(isLegalTransition);

		return isLegalTransition;
	}
	private void printIfLegal(boolean isLegalTransition){
		if(isLegalTransition)
		{
			if(currentStates.size()>1)
			{
				System.out.println("It's a legal transition. The automata is currently on the below states:");
				int i;
				for(i=0; i<currentStates.size()-1;i++)
					System.out.print(currentStates.get(i).getName()+", ");
				System.out.print(currentStates.get(i).getName());
			}
			else
			{
				System.out.println("It's a legal transition. The automata is currently on the below state:");
				System.out.print(currentStates.get(0).getName());
			}	
		}
		else
			System.out.println("It's not a legal transition.");
	}

	/*
	 * For every state that is included in ArrayList<State> currentStates
	 * searches the next states based on the transition that is produced by the input symbol.
	 */
	private ArrayList<State> getNextStates(String symbol){
		ArrayList<State> newStates = new ArrayList<State>();

		for(State s:currentStates)
		{
			newStates.addAll(s.getNextState(symbol));
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
