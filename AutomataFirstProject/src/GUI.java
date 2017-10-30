import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUI extends JFrame implements ActionListener{

	
	private JButton btn = new JButton("ok");
	private JTextField tf = new JTextField("");
	private AutomataRunner myAR;
	
	public GUI(ArrayList<State> states){
		
		myAR = new AutomataRunner(states);
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.gridx = 0;
		gbc.insets = new Insets(15, 10, 15, 10);
		gbc.gridy = 0;
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panel.add(tf, gbc);
		gbc.gridy = 1;
		panel.add(btn,gbc);

		
		btn.addActionListener(this);
		
		this.setContentPane(panel);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setTitle("Method Input?");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	@Override
	public void actionPerformed(ActionEvent e) {


		if(e.getActionCommand().equals("ok"))
		{
			myAR.consumeNextCharacter(tf.getText());
			tf.setText("");
		}
		
	}
}
