import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class LogFileChooser {

	public LogFileChooser(EndingMechanism caller){

		caller.dispose();//Destruction of caller frame.

		final JFileChooser fc = new JFileChooser();
		
		FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
		fc.setFileFilter(filter);
		
		int counter=3, returnVal;
		do
		{
			returnVal = fc.showSaveDialog(null);
			counter--;
		}//counter is there for not being annoying.
		while(returnVal != JFileChooser.APPROVE_OPTION && counter>0);
		
		if (counter>0) {
			File file = fc.getSelectedFile(); //get File selected by user
			BufferedWriter txtOutput;
			try 
			{
				txtOutput = new BufferedWriter(new FileWriter(file));
				for(String line:LogRecord.getLogRecord())
				{
					txtOutput.write(line);
					txtOutput.newLine();
				}
				txtOutput.close();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}
}
