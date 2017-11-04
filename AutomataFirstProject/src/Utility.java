import java.util.ArrayList;

public class Utility {

	public static void addAllWithoutDuplicates(ArrayList<Object> target, ArrayList<Object> source)
	{
		for(Object o: source)
			if(!target.contains(o))
				target.add(o);
	}
}
