import java.util.ArrayList;

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
	 * If the list is empty, it means that there is no next state
	 * with the use of this specific character.
	 * So it returns null instead.
	 * 
	 */
	public ArrayList<State> getNextState(String newCharacter){

		ArrayList<State> nextStates = new ArrayList<State>();

		for(int i=0; i<transitionSymbols.size();i++)		
			if(transitionSymbols.get(i).equals(newCharacter))			
				nextStates.add(goToStates.get(i));

		return nextStates;

	}


	/*
	 * 
	 * goToStates list and transitionSymbols list are keeped aligned.
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
