import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;

public class LogFileChooser {

	private String directory;

	public LogFileChooser(){



		final JFileChooser fc = new JFileChooser();
		 //parent component to JFileChooser
		
		int counter=3, returnVal;
		do
		{
			returnVal = fc.showSaveDialog(null);
			counter--;
			
		}//counter is there for not being annoying.
		while(returnVal != JFileChooser.APPROVE_OPTION && counter>0);
		
		//TODO Need to complete this JFileChooser.
		if (counter>0) {
			File file = fc.getSelectedFile(); //get File selected by user
			
			try 
			{
				BufferedWriter o = new BufferedWriter(new FileWriter(file));
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}
}
