import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class State {
	private String name;
	private boolean isFinal;
	private boolean isStarting;
	private ArrayList<Transition> transitions;

	public State(String stateName, boolean isStarting){

		name = stateName;
		this.isStarting = isStarting;
	}	

	/*
	 * 
	 * Receives a string which will be a single character.
	 * It finds the state or states that this character
	 * leads the automata to.
	 * 
	 * It returns the list with the state(s).
	 * 
	 */
	public ArrayList<State> getNextState(String newCharacter){

		ArrayList<State> nextStates = new ArrayList<State>();

		/*
		 * Inspects all transitions. If the input character
		 * is equal to the transition character, then this
		 * destination State is added to the list.
		 */
		for(int i=0; i<this.transitions.size();i++)		
			if(transitions.get(i).getTransitionSymbol().equals(newCharacter))			
				nextStates.add(transitions.get(i).getDestinationState());

		if(!nextStates.isEmpty())
			nextStates.addAll(this.getAllNextStatesBasedOnNullTransitions());

		/*
		 * remove duplicates
		 */
		Set<State> hs = new HashSet<>();
		hs.addAll(nextStates);
		nextStates.clear();
		nextStates.addAll(hs);
		
		return nextStates;
	}
	
	
	public ArrayList<State> getAllNextStatesBasedOnNullTransitions()
	{
		ArrayList<State> states = new ArrayList<State>();
		
		
		
		return states;
	}

	/*
	 * 
	 * Creates a new Transition object
	 * and adds it to the transition list. 
	 */
	public void addNewTransition(String symbol,State state)
	{
		Transition newTransition = new Transition(this, state, symbol);
		this.transitions.add(newTransition);
	}

	public ArrayList<Transition> getTransitions() {
		return transitions;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isFinal() {
		return isFinal;
	}
	public void setFinal(boolean isFinal) {
		this.isFinal = isFinal;
	}
	public boolean isStarting() {
		return isStarting;
	}
	public void setStarting(boolean isStarting) {
		this.isStarting = isStarting;
	}





}
