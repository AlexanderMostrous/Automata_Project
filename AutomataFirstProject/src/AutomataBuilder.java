import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AutomataBuilder {

	private ArrayList<String> textLines = new ArrayList<String>();
	private int numberOfStates,numberOfFinalStates,numberOfTransitions;
	private ArrayList<State> states = new ArrayList<State>();


	public AutomataBuilder(ArrayList<String> textLines){

		this.textLines = textLines;


	}

	public void createAutomata(){

		int i = 0;
		Pattern pattern;
		Matcher matcher;

		//1st Line: Number of states
		pattern = Pattern.compile("([0-9])+");
		matcher = pattern.matcher(textLines.get(i));
		matcher.find();
		numberOfStates = Integer.valueOf(matcher.group());

		matcher.reset();
		i++;


		//2nd Line: Name of the starting state
		pattern = Pattern.compile("([0-9A-Za-z])+");
		matcher = pattern.matcher(textLines.get(i));
		matcher.find();
		states.add(new State(matcher.group(), true));

		matcher.reset();
		i++;


		//3d Line: Number of final states
		pattern = Pattern.compile("([0-9])+");
		matcher = pattern.matcher(textLines.get(i));
		matcher.find();
		numberOfFinalStates = Integer.valueOf(matcher.group());

		matcher.reset();
		i++;


		//4thLine: Names of final states
		pattern = Pattern.compile("([0-9A-Za-z])+((\\s+)([0-9A-Za-z])+)*");
		matcher = pattern.matcher(textLines.get(i));
		ArrayList<String> namesOfFinalStates = new ArrayList<String>();
		String[] temp = textLines.get(i).split(" ");

		for(int j = 0;j<temp.length;j++)
			namesOfFinalStates.add(temp[j]);
		addFinalStates(namesOfFinalStates);

		matcher.reset();
		i++;


		//5th Line: Number of transitions
		pattern = Pattern.compile("([0-9])+");
		matcher = pattern.matcher(textLines.get(i));
		matcher.find();
		numberOfTransitions = Integer.valueOf(matcher.group());
		
		matcher.reset();
		i++;
		
		/*
		 * All transitions
		 * The transition symbols, based on which, next state will be selected, are consisted strictly by a-z and 0-9 characters. NOT capitals.
		 */
		pattern = Pattern.compile("([0-9A-Za-z])+(\\s+)([0-9a-z])(\\s+)([0-9A-Za-z])+");
		String[] tempTransitions; 
		while(i<textLines.size())
		{
			tempTransitions = textLines.get(i).split(" ");
			handleTransitions(tempTransitions);
			i++;
		}
		
	}

	/*
	 * 
	 * Receives a length=3 String table as input.
	 * Iterates through every state stored, trying to find if each state name
	 * belongs to an already created State object. If yes, then it holds a
	 * pointer to this object. If not, then it creates a new State object
	 * and then holds the pointer to it.
	 * 
	 * In the end, it "binds" 2 states with the State.addNewTransition() method.
	 */
	private void handleTransitions(String[] transitions)
	{
		String stateA_name = transitions[0],transitionSymbol = transitions[1],stateB_name = transitions[2];
		State stateA = null, stateB = null;
		boolean bFound = false,aFound = false;
		
		for(int i=0;i<states.size();i++)
			if(states.get(i).getName().equals(stateA_name))
			{
				aFound = true;
				stateA = states.get(i);
				break;
			}
		
		if(!aFound)
		{
			stateA = new State(stateA_name, false);
			states.add(stateA);
		}
		
		for(int i=0;i<states.size();i++)
			if(states.get(i).getName().equals(stateB_name))
			{
				bFound = true;
				stateB = states.get(i);
				break;
			}
		
		if(!bFound)
		{
			stateB = new State(stateB_name, false);
			states.add(stateB);
		}
		
		
		stateA.addNewTransition(transitionSymbol, stateB);
		
	}
	
	/*
	 * This method takes the names of the final states as an input.
	 * The method uses the names as an identifier of new states.
	 * So, if the starting state's name is in the input list,
	 * it means that the starting state is also a final state.
	 * 
	 * Any other name that is in the list is used to create a new state.
	 */
	private void addFinalStates(ArrayList<String> names){

		for(int i = 0; i<names.size();i++)
		{
			//If true, then we just detected our starting state, which is also a final state.
			if(names.get(i).equals(states.get(0).getName()))
				states.get(0).setFinal(true);
			else//If false, then it's some other state, that we need to add it as a new state.
			{
				State aState = new State(names.get(i), false);
				aState.setFinal(true);
				states.add(aState);
			}
		}
	}

	public ArrayList<String> getTextLines() {
		return textLines;
	}


}
