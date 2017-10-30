import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormatTester {

	private String filePath;
	private ArrayList<String> textLines = new ArrayList<String>();
	
	/*
	 * Constructor. Input is a string which will be used as
	 * the path/location of the .txt file.
	 */
	public FormatTester(String filePath){

		this.filePath = filePath;
		readTextFile();
	}

	/*
	 * 
	 * This method returns if the txt file is in correct format.
	 * It detects every possible mistake in format, but NOT inconsistencies
	 * the numbers/lines etc.
	 * 
	 * Checks every line one by one and instantly aborts, returning false,
	 * in case of wrong format. 
	 * 
	 */
	public boolean isInCorrectFormat(){
		int i = 0;
		Pattern pattern;
		Matcher matcher;

		//1st Line: Number of states
		pattern = Pattern.compile("([0-9])+");
		matcher = pattern.matcher(textLines.get(i));
		if(!matcher.matches())
			return false;
		matcher.reset();
		i++;

		//2nd Line: Name of the starting state
		pattern = Pattern.compile("([0-9A-Za-z])+");
		matcher = pattern.matcher(textLines.get(i));
		if(!matcher.matches())
			return false;
		matcher.reset();
		i++;

		//3d Line: Number of final states
		pattern = Pattern.compile("([0-9])+");
		matcher = pattern.matcher(textLines.get(i));
		if(!matcher.matches())
			return false;
		matcher.reset();
		i++;

		//4thLine: Names of final states
		pattern = Pattern.compile("([0-9A-Za-z])+((\\s+)([0-9A-Za-z])+)*");
		matcher = pattern.matcher(textLines.get(i));
		if(!matcher.matches())
			return false;
		matcher.reset();
		i++;

		//5th Line: Number of transitions
		pattern = Pattern.compile("([0-9])+");
		matcher = pattern.matcher(textLines.get(i));
		if(!matcher.matches())
			return false;
		matcher.reset();
		i++;
		
		/*
		 * All transitions
		 * The transition symbols, based on which, next state will be selected, are consisted strictly by a-z and 0-9 characters. NOT capitals.
		 */
		pattern = Pattern.compile("([0-9A-Za-z])+(\\s+)(([0-9a-z])|(#))(\\s+)([0-9A-Za-z])+");
		while(i<textLines.size())
		{
			matcher = pattern.matcher(textLines.get(i));
			if(!matcher.matches())
				return false;
			matcher.reset();
			i++;
		}

		return true;
	}

	/*
	 * 
	 * It reads a whole .txt file, line by line. 
	 * The .txt file path is held in the filePath attribute.
	 * Each line is stored inside an ArrayList<String> object.
	 * 
	 */
	private void readTextFile(){
		try 
		{
			BufferedReader in = new BufferedReader(new FileReader(filePath));
			String line;
			while((line = in.readLine()) != null)
			{
				//TODO Add a full-blank-space-line cleaner.
				textLines.add(line.trim());
			}
			in.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}

	}

	public ArrayList<String> getTextLines() {
		return textLines;
	}

}
