import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;



public class ButtonLayout extends javax.swing.JPanel {
//	private final JButton A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z;
	final ArrayList<JButton> letterButtons = new ArrayList<JButton>();
	final String keys = "QWERTYUIOPASDFGHJKLZXCVBNM";
	
	
	public ButtonLayout(HangmanManager manager)  {	
		
		final ArrayList<String>Test = new ArrayList<String>();
		
		
		this.setBackground(java.awt.Color.GRAY);
		JPanel controls1 = new JPanel();
		JPanel controls2 = new JPanel();
		JPanel controls3 = new JPanel();
		JPanel overPanel = new JPanel();
		overPanel.setLayout(new GridLayout(4, 0));
		controls1.setLayout(new GridLayout(1, 10));
		controls2.setLayout(new GridLayout(1, 8));
		controls3.setLayout(new GridLayout(1, 7));
		
		char[] keyChars = keys.toCharArray();
		for(int i = 0;i < keys.length();i++){
			JButton tmpButton = new JButton(Character.toString(keyChars[i]));
			letterButtons.add(tmpButton);
			tmpButton.setMnemonic(KeyEvent.getExtendedKeyCodeForChar(tmpButton.getText().toUpperCase().charAt(0)));
			tmpButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					manager.findLetter(tmpButton.getText());
					if(!manager.checkWin() && !manager.checkLose()){
						tmpButton.setEnabled(false);
					}
				}
			});
		}
		 
		 final JButton returnBtn = new JButton("Restart");
		 //returnBtn.setMnemonic(KeyEvent.getExtendedKeyCodeForChar('R'));

		 returnBtn.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent event) {
				manager.endGame();
			 }
		 }
		 );

		 int i;
		 for(i = 0;i < 10;i++){
		 	controls1.add(letterButtons.get(i));
		 }
		 for(;i < 19;i++){
		 	controls2.add(letterButtons.get(i));
		 }
		 for(;i < 26;i++){
		 	controls3.add(letterButtons.get(i));
		 }
		
		 
		overPanel.add(controls1);
		overPanel.add(controls2);
		overPanel.add(controls3);
		overPanel.add(returnBtn);
		
		this.add(overPanel);
		
	}

	public void printID(JButton btn){
		System.out.println(btn.getText());
	}
	
}
