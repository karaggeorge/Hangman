import javax.swing.*;
import java.awt.event.WindowEvent;

public class HangmanGame {

	private HangmanManager manager;
	private HangManFrame hmFrame;
	private StartScreenFrame ssFrame;

	public HangmanGame(){
		manager = new HangmanManager(new Dictionary("dictionary.txt"), (this) );
		ssFrame = new StartScreenFrame(manager);
	}

	public void startGame(){
		ssFrame.dispatchEvent(new WindowEvent(ssFrame, WindowEvent.WINDOW_CLOSING));
		hmFrame = new HangManFrame(manager);
	}

	public void endGame(){
		hmFrame.dispatchEvent(new WindowEvent(hmFrame, WindowEvent.WINDOW_CLOSING));
		ssFrame = new StartScreenFrame(manager);
	}

	public static void main(String[] args){

    	try {
	            // Set System L&F
	        UIManager.setLookAndFeel(
	            UIManager.getSystemLookAndFeelClassName());
	    } 
	    catch (UnsupportedLookAndFeelException e) {
	       // handle exception
	    }
	    catch (ClassNotFoundException e) {
	       // handle exception
	    }
	    catch (InstantiationException e) {
	       // handle exception
	    }
	    catch (IllegalAccessException e) {
	       // handle exception
	    }

		new HangmanGame();
	}

}