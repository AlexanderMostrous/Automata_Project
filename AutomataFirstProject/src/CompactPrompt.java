import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CompactPrompt extends JFrame implements ActionListener{
	
	private JLabel lbl = new JLabel("Wrong txt file Format!!");
	private JButton btn = new JButton("OK");
	
	/*
	 * This class prompts a JFrame in case that the input txt file is wrong.
	 */
	public CompactPrompt(){
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.gridx = 0;
		gbc.insets = new Insets(15, 10, 15, 10);
		gbc.gridy = 0;
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panel.add(lbl, gbc);
		gbc.gridy = 1;
		panel.add(btn,gbc);

		btn.addActionListener(this);
		
		this.setContentPane(panel);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setTitle("Wrong Format Prompt Window");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btn))
			this.dispose();
	}

}
