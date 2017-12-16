import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

	public static void main(String[] args) throws IOException {

				
		MyFileChooser fc = new MyFileChooser();
		
		if(!fc.getDirectory().equals("END"))
		{
			FormatTester ft = new FormatTester(fc.getDirectory());
			if(ft.isInCorrectFormat())
			{
				AutomataBuilder ab = new AutomataBuilder(ft.getTextLines());
				new GUI(ab.getStates());
			}
			else
				System.out.println("Wrong Format");
		}
		else
			System.out.println("System ended by user before start.");
	}

}
