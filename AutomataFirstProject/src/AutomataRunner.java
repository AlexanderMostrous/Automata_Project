import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class AutomataRunner {

	private ArrayList<State> currentStates, allStates;

	public AutomataRunner(ArrayList<State> allStates){

		this.allStates=allStates;
		currentStates = new ArrayList<State>();
		State stst = getStartingState();
		ArrayList<State> startingStates = new ArrayList<>();
		startingStates.add(stst);
		/*
		 * Besides the one starting state that is given as an input in the txt file,
		 * it is probable that this state has null transitions to some other state(s).
		 * So we need to take this into account.
		 */
		for(int i=0;i<startingStates.size();i++)
			Utility.addAllWithoutDuplicates(startingStates,startingStates.get(i).getNextNullTransitionState());

		currentStates = startingStates;
		
		printStartingStates();
	}

	/*
	 * 
	 * This methods receives a symbol and simulates the automata transition based on it.
	 * It returns true or false based on whether this symbol produces a transition or not.
	 */
	public boolean consumeNextCharacter(String symbol){
		boolean isLegalTransition = true;

		ArrayList<State> newStates = getNextStates(currentStates,symbol);
		
		if(!newStates.isEmpty())
		{
			currentStates = newStates;
		}
		else
		{
			System.out.println("List is empty.");
			isLegalTransition = false;
		}
		printIfLegal(isLegalTransition);

		return isLegalTransition;
	}
	
	private void printStartingStates()
	{
		if(currentStates.size()==1)
			System.out.println("Starting state is: "+currentStates.get(0).getName());
		else
		{
			System.out.print("Starting states are: ");
			for(int i=0;i<currentStates.size()-1;i++)
				System.out.print(currentStates.get(i).getName()+", ");
			System.out.println(currentStates.get(currentStates.size()-1).getName());
		}
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
	private ArrayList<State> getNextStates(ArrayList<State> beforeTransitionStates, String symbol){
		ArrayList<State> newStates = new ArrayList<State>();

		for(State s : beforeTransitionStates)
		{
			Utility.addAllWithoutDuplicates(newStates, s.getNextState(symbol));
		}

		for(int i=0;i<newStates.size();i++)
		{
			//TODO Debugged?
			Utility.addAllWithoutDuplicates(newStates,newStates.get(i).getNextNullTransitionState());
			
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
	
	/*
	 * 
	 * This method searches all currentStates and 
	 * returns TRUE if at least ONE of them is a final state
	 * and FALSE if ALL of them are NOT final states.
	 * 
	 */
	public boolean postTerminationInputEvaluation_isValid()
	{
		for(State s:currentStates)
			if(s.isFinal())
				return true;
		return false;
	}
	
	/*
	 * 
	 * Searches and returns all final states in an ArrayList.
	 * 
	 */
	
	public ArrayList<State> getFinalStates(){
		ArrayList<State> temp = new ArrayList<State>();
		for(State s:currentStates)
			if(s.isFinal())
				temp.add(s);
		return temp;
	}
}
