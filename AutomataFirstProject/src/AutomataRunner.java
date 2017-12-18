import java.util.ArrayList;

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
			currentStates = newStates;
		else		
			isLegalTransition = false;
		
		printIfLegal(isLegalTransition);

		return isLegalTransition;
	}
	
	private void printStartingStates()
	{
		String temp ="";
		if(currentStates.size()==1)
			temp = "Starting state is: "+currentStates.get(0).getName();
		else
		{
			temp = "Starting states are: ";
			for(int i=0;i<currentStates.size()-1;i++)
				temp += currentStates.get(i).getName()+", ";
			temp += currentStates.get(currentStates.size()-1).getName();
		}
		LogRecord.addLine(temp);
	}
	private void printIfLegal(boolean isLegalTransition){
		String temp ="";
		if(isLegalTransition)
		{
			if(currentStates.size()>1)
			{
				temp = "It's a legal transition. The automata is currently on the below states:";
				LogRecord.addLine(temp);
				temp = "";
				int i;
				for(i=0; i<currentStates.size()-1;i++)
					temp += currentStates.get(i).getName()+", ";
				temp += currentStates.get(i).getName();
				LogRecord.addLine(temp);
			}
			else
			{
				LogRecord.addLine("It's a legal transition. The automata is currently on the below state:");
				LogRecord.addLine(currentStates.get(0).getName());
			}	
		}
		else
		{
			LogRecord.addLine("It's not a legal transition.");
			LogRecord.addLine("Automata crashed.");
		}
	}

	/*
	 * 
	 */
	private ArrayList<State> getNextStates(ArrayList<State> beforeTransitionStates, String symbol){
		ArrayList<State> newStates = new ArrayList<State>();

		for(State s : beforeTransitionStates)		
			Utility.addAllWithoutDuplicates(newStates, s.getNextState(symbol));		

		for(int i=0;i<newStates.size();i++)
			Utility.addAllWithoutDuplicates(newStates,newStates.get(i).getNextNullTransitionState());
			
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
