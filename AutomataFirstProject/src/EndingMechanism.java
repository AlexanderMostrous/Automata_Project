import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class EndingMechanism extends JFrame implements ActionListener{

	private AutomataRunner ar;
	private String endingReason;
	private JLabel resultMessage1, resultMessage2;
	private JButton yesBtn, noBtn;
	public EndingMechanism(String endingReason, GUI callingGUI)
	{
		callingGUI.dispose();
		this.endingReason=endingReason;
		this.ar=null;
		endingProcess();
	}

	public EndingMechanism(String endingReason, GUI callingGUI, AutomataRunner ar)
	{
		callingGUI.dispose();
		this.endingReason=endingReason;
		this.ar=ar;
		endingProcess();
	}

	private void trivialProcess()
	{
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		yesBtn = new JButton("Yes");
		noBtn = new JButton("No");
		
		gbc.gridx = 0;
		gbc.insets = new Insets(15, 10, 15, 10);
		gbc.gridy = 0;
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panel.add(resultMessage1, gbc);
		gbc.gridy++;
		panel.add(resultMessage2,gbc);
		
		gbc.gridy++;
		panel.add(new JLabel("Do you want a Log Record of your automata activity?"),gbc);
		
		gbc.gridy++;
		panel.add(yesBtn,gbc);

		gbc.gridx++;
		panel.add(noBtn,gbc);
		
		yesBtn.addActionListener(this);
		noBtn.addActionListener(this);
		
		
		this.setContentPane(panel);		
		this.pack();
		this.setVisible(true);		
		this.setSize(650, 400);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setTitle("Ending prompt and Log choice. ");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void endingProcess(){

		if(endingReason.equals("crash"))
		{
			this.resultMessage1 = new JLabel("Your automata crashed due to wrong input.");
			this.resultMessage2 = new JLabel("Your input is not a valid string");
		}
		else
		{
			this.resultMessage1 = new JLabel("Your automata terminated by user.");
			if(ar.postTerminationInputEvaluation_isValid())
				this.resultMessage2 = new JLabel("Your input is not a valid string because it didnt terminate on a final state.");
			else
			{
				this.resultMessage2 = new JLabel("Your input is a valid string.");
				if(ar.getFinalStates().size() == 1)
					this.resultMessage2.setText(this.resultMessage2.getText()+" Final state is: "+ar.getFinalStates().get(0).getName()+".");
				else
				{
					this.resultMessage2.setText(this.resultMessage2.getText()+" Final states are: ");
					for(int i=0;i<ar.getFinalStates().size()-1;i++)
						this.resultMessage2.setText(this.resultMessage2.getText()+ar.getFinalStates().get(i).getName()+", ");
					this.resultMessage2.setText(this.resultMessage2.getText()+ar.getFinalStates().get(ar.getFinalStates().size()).getName()+".");
				}
			}
		}
		
		trivialProcess();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource().equals(yesBtn))
		{
			
		}
		else if(e.getSource().equals(noBtn))
			this.dispose();
			
	}
}
