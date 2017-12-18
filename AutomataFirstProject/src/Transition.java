
public class Transition {
	
	private State startingState, destinationState;
	private String transitionSymbol;
	
	public Transition(State start, State destination, String symbol)
	{
		startingState = start;
		destinationState = destination;
		transitionSymbol = symbol;
	}
	
	public boolean isANullTransition()
	{
		if(transitionSymbol.equals("å"))
			return true;
		else
			return false;
	}

	public State getStartingState() {
		return startingState;
	}

	public State getDestinationState() {
		return destinationState;
	}

	public String getTransitionSymbol() {
		return transitionSymbol;
	}
	
	public String toString(){		
		return startingState.getName() + " " + transitionSymbol + " " + destinationState.getName();
	}
}
