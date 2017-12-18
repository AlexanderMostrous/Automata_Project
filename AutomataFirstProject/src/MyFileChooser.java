import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MyFileChooser extends JFrame {

	private String directory;

	public MyFileChooser(){


		//Create a file chooser
		JFileChooser fc = new JFileChooser();
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
