import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class State {
	private String name;
	private boolean isFinal;
	private boolean isStarting;
	private ArrayList<State> goToStates;
	private ArrayList<String> transitionSymbols;

	public State(String stateName, boolean isStarting){

		name = stateName;
		goToStates = new ArrayList<State>();
		transitionSymbols = new ArrayList<String>();
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

		for(int i=0; i<transitionSymbols.size();i++)		
			if(transitionSymbols.get(i).equals(newCharacter))			
				nextStates.add(goToStates.get(i));

		if(!nextStates.isEmpty())
			nextStates.addAll(this.getMyStandardNextStates());

		/*
		 * remove duplicates
		 */
		Set<State> hs = new HashSet<>();
		hs.addAll(nextStates);
		nextStates.clear();
		nextStates.addAll(hs);
		
		return nextStates;
	}

	/*
	 * Description inside method.
	 */
	public ArrayList<State> getMyStandardNextStates()
	{
		ArrayList<State> nextStates = new ArrayList<State>();
		
		
		/*
		 * First we add in ArrayList<State> nextStates all next states
		 * based on å transitions.
		 * 
		 */
		for(int i=0;i<this.transitionSymbols.size();i++)
			if(this.transitionSymbols.get(i).equals("å"))
				nextStates.add(this.goToStates.get(i));
		
		int i=0;
		/*
		 * If ArrayList<State> nextStates is now empty, then the while loop will not
		 * be executed once, because there are no å transitions.
		 * Else the loop will execute by iterating throughout the whole list
		 * while adding next states based on å transitions.
		 * 
		 */
		while(i<nextStates.size())
		{
			for(int j=0;j<nextStates.get(i).transitionSymbols.size();j++)
				if(nextStates.get(i).transitionSymbols.get(j).equals("å"))
					if(!nextStates.contains(nextStates.get(i).goToStates.get(j)))
						nextStates.add(nextStates.get(i).goToStates.get(j));
			i++;
		}
		
		return nextStates;
		
	}
	
	/*
	 * 
	 * goToStates list and transitionSymbols list are kept aligned.
	 * Each one's i-th item is related with the other's. 
	 */
	public void addNewTransition(String symbol,State state)
	{
		addGoToState(state);
		addTransitionSymbol(symbol);
	}

	public ArrayList<State> getGoToStates() {
		return goToStates;
	}
	private void addTransitionSymbol(String symbol){
		this.transitionSymbols.add(symbol);
	}
	public ArrayList<String> getTransitionSymbols() {
		return transitionSymbols;
	}
	private void addGoToState(State aState) {
		this.goToStates.add(aState);
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
