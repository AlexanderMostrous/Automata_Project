import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class LogFileChooser {

	public LogFileChooser(EndingMechanism caller){

		caller.dispose();

		//TODO remember to remove the path below
		final JFileChooser fc = new JFileChooser("C:\\Users\\alexandros\\Dropbox\\Xeimerino\\Computing and Automata Theory");
		
		FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
		fc.setFileFilter(filter);
		
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
			BufferedWriter txtOutput;
			try 
			{
				txtOutput = new BufferedWriter(new FileWriter(file));
				for(String line:LogRecord.getLogRecord())
					txtOutput.write(line);
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}
}
