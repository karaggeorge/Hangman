import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.*;

import javax.swing.JFrame;
//Yo, make a draw hangman shape and then its own panel. Got nothing to lose, right?
//Probably ideal to look up how to code a grid layout
//Once you get the look done, see if you can add it all in an array list, ask George for help if need be

public class StartScreenFrame extends JFrame {
	public StartScreenFrame(HangmanManager manager){
    	super("HangMan Game");
    	this.setResizable(false);
    	

        final StartPanel startPanel = new StartPanel(manager);

        this.add(startPanel, BorderLayout.CENTER);
        this.pack();
        //setSize(750, 600);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

    }


}
