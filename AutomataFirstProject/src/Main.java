import java.io.IOException;

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
				new CompactPrompt();
		}
		else
			System.out.println("System ended by user before start.");
	}
}
