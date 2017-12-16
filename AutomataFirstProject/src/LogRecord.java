import java.util.ArrayList;

public class LogRecord {

	private static ArrayList<String> recordStored = new ArrayList<String>();
	
	public static void addLine(String line)
	{
		recordStored.add(line);
	}
	
	public static ArrayList<String> getLogRecord()
	{
		return recordStored;
	}
}
