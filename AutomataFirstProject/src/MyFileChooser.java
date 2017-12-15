import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MyFileChooser extends JFrame {

	private String directory;

	public MyFileChooser(){


		//Create a file chooser
		//TODO Remember to Remove-Helpful Starting Path for Debugging
		JFileChooser fc = new JFileChooser("C:\\Users\\alexandros\\Dropbox\\Xeimerino\\Computing and Automata Theory");
		//In response to a button click:
		int returnVal;
		
		FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
		fc.setFileFilter(filter);
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int counter = 3;
		do
		{
			returnVal = fc.showOpenDialog(null);
			counter--;
			
		}//counter is there for not being annoying.
		while(returnVal != JFileChooser.APPROVE_OPTION && counter>0);
		
		if(counter != 0)
			directory = fc.getSelectedFile().getAbsolutePath();	
		else
			directory = "END";
	}

	public String getDirectory()
	{
		return directory;
	}


}
