import java.util.ArrayList;

public class State {
	private String name;
	private boolean isFinal;
	private boolean isStarting;
	private ArrayList<State> goToStates;
	private ArrayList<String> transitionSymbols;
	
	public State(String stateName, boolean isStarting, boolean isFinal){
		
		name = stateName;
		goToStates = new ArrayList<State>();
		transitionSymbols = new ArrayList<String>();
		this.isStarting = isStarting;
		this.isFinal = isFinal;
		
	}	
	
	public ArrayList<State> getNextState(String newCharacter){
		
		ArrayList<State> nextStates = new ArrayList<State>();
		
		for(int i=0; i<transitionSymbols.size();i++)		
			if(transitionSymbols.get(i).equals(newCharacter))			
				nextStates.add(goToStates.get(i));
		
		if(!nextStates.isEmpty())
			return nextStates;
		else
			return null;//If null, then the whole automata will eventually crush, due to wrong input.
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
