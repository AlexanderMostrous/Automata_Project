import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUI extends JFrame implements ActionListener, KeyListener{

	
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
		btn.addKeyListener(this);
		
		
		this.setContentPane(panel);
		//The next 2 lines of code makes the OK button focused from the beginning.
		this.getRootPane().setDefaultButton(btn);
		btn.requestFocusInWindow();
		
		this.pack();
		this.setVisible(true);		
		this.setSize(450, 200);
		this.setResizable(true);
		this.setLocationRelativeTo(null);
		this.setTitle("Input Characters 1 by 1 and press OK ");
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
	@Override
	public void keyPressed(KeyEvent e) {
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER)
		{
			myAR.consumeNextCharacter(tf.getText());
			tf.setText("");
		}
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		
	}
}
