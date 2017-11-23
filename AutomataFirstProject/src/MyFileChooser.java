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
		JFileChooser fc = new JFileChooser();
		//In response to a button click:
		int returnVal;

		FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
		fc.setFileFilter(filter);
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		do
		{
			returnVal = fc.showOpenDialog(null);
			
		}while(returnVal != JFileChooser.APPROVE_OPTION);
		
		directory = fc.getSelectedFile().getAbsolutePath();	
	}

	public String getDirectory()
	{
		return directory;
	}


}
