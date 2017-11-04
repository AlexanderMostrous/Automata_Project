import java.util.ArrayList;

public class Utility {

	public static void addAllWithoutDuplicates(ArrayList<State> target, ArrayList<State> source)
	{
		for(State s: source)
			if(!target.contains(s))
				target.add(s);
	}
}
