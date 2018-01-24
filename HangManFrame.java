import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JFrame;
//Yo, make a draw hangman shape and then its own panel. Got nothing to lose, right?
//Probably ideal to look up how to code a grid layout
//Once you get the look done, see if you can add it all in an array list, ask George for help if need be

public class HangManFrame extends JFrame {
	public HangManFrame(HangmanManager manager){
    	super("HangMan Game");
        this.setResizable(false);
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        final HangManPanel hangMan = new HangManPanel(manager);
        final ButtonLayout buttons = new ButtonLayout(manager);

        this.add(hangMan, BorderLayout.CENTER);
        this.add(buttons, BorderLayout.SOUTH);
        this.pack();
        setSize(750, 600);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

    }


}
