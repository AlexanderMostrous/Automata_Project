import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class AutomataRunner {

	private ArrayList<State> currentStates, allStates;

	public AutomataRunner(ArrayList<State> allStates){

		this.allStates=allStates;
		currentStates = new ArrayList<State>();
		State stst = getStartingState();
		//TODO TO FIX! Exactly after starting state might be a null transition. This must be considered a possibility. 
		currentStates.add(stst);
		System.out.println("Starting state is: "+stst.getName());
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
		{
			currentStates = temp;
			//Removing duplicates
			Set<State> hs = new HashSet<>();
			hs.addAll(currentStates);
			currentStates.clear();
			currentStates.addAll(hs);
		}
		else
		{
			System.out.println("List is empty.");
			isLegalTransition = false;
		}
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
				System.out.println();
			}
			else
			{
				System.out.println("It's a legal transition. The automata is currently on the below state:");
				System.out.println(currentStates.get(0).getName());
			}	
		}
		else
			System.out.println("It's not a legal transition.");
	}

	/*
	 * 
	 */
	private ArrayList<State> getNextStates(String symbol){
		ArrayList<State> newStates = new ArrayList<State>();

		for(State s:currentStates)
		{
			newStates.addAll(s.getNextState(symbol));
		}

		for(int i=0;i<currentStates.size();i++)
		{
			//TODO Debug
			currentStates.addAll(currentStates.get(i).getNextNulltrasitionState());
			
			//Removing duplicates
			Set<State> hs = new HashSet<>();
			hs.addAll(currentStates);
			currentStates.clear();
			currentStates.addAll(hs);
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
