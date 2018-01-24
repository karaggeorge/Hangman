import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;

import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.event.ChangeEvent;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.Hashtable;

public class StartPanel extends JPanel
{
	private JButton start;
	private JSlider lives, length;
	private int numLives, wordLength;
	private JPanel sliders, difficulty, rButtons, startButtonPanel;
	private JLabel livesLabel, lengthLabel;
	private ButtonGroup options;
	private JRadioButton veasy, easy, normal, hard, vhard;
	private HangmanManager manager;
	private int minLives = 4, maxLives = 16;

	public StartPanel(HangmanManager manager)
	{
		super();

		this.manager = manager;

		this.setLayout( new BorderLayout() );

		difficulty = new JPanel();
		rButtons = new JPanel();
		rButtons.setLayout( new GridLayout(5,0) );

		options = new ButtonGroup();

		veasy = new JRadioButton("VERY EASY");
		easy = new JRadioButton("EASY");
		normal = new JRadioButton("NORMAL");
		hard = new JRadioButton("HARD");
		vhard = new JRadioButton("VERY HARD");

		options.add(veasy);
		options.add(easy);
		options.add(normal);
		options.add(hard);
		options.add(vhard);

		rButtons.add(veasy);
		rButtons.add(easy);
		rButtons.add(normal);
		rButtons.add(hard);
		rButtons.add(vhard);
		difficulty.add(rButtons, BorderLayout.CENTER);

		veasy.setSelected(true);

		this.add(difficulty, BorderLayout.NORTH);

		sliders = new JPanel();

		sliders.setLayout( new GridLayout(4,0) );

		lives = new JSlider(minLives,maxLives);
		length = new JSlider(manager.getMinLen(),manager.getMaxLen());

		lives.setMajorTickSpacing(1);
		length.setMajorTickSpacing(5);

		lives.setPaintTicks(true);
		length.setPaintTicks(true);

		Hashtable livesLabels = new Hashtable();
		for(int i = minLives;i <= maxLives;i+=2){
			livesLabels.put( new Integer( i ), new JLabel( Integer.toString(i) ) );
		}
		lives.setLabelTable( livesLabels );
		lives.setPaintLabels(true);

		Hashtable lengthLabels = new Hashtable();
		for(int i = manager.getMinLen();i <= manager.getMaxLen();i+=5){
			lengthLabels.put( new Integer( i ), new JLabel( Integer.toString(i) ) );
		}
		length.setLabelTable( lengthLabels );
		length.setPaintLabels(true);

		livesLabel = new JLabel("Guesses: " + lives.getValue());
		lengthLabel = new JLabel("Word Length: " + length.getValue());

		lives.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e){
				livesLabel.setText("Guesses: " + lives.getValue());
			}
		});

		length.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e){
				lengthLabel.setText("Word Length: " + length.getValue());
			}
		});

		sliders.add(lengthLabel);
		sliders.add(length);
		sliders.add(livesLabel);
		sliders.add(lives);


		this.add(sliders, BorderLayout.CENTER);

		startButtonPanel = new JPanel(new BorderLayout());
		start = new JButton("START");
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				manager.prepForRound(length.getValue(),lives.getValue(),getDifficulty());
			}
		}
		);
		startButtonPanel.add(start, BorderLayout.CENTER);
		startButtonPanel.add(new JLabel("   "), BorderLayout.NORTH);
		startButtonPanel.add(new JLabel("      "), BorderLayout.WEST);
		startButtonPanel.add(new JLabel("      "), BorderLayout.EAST);
		startButtonPanel.add(new JLabel("   "), BorderLayout.SOUTH);


		this.add(startButtonPanel, BorderLayout.SOUTH);
		this.add(new JLabel("   "), BorderLayout.WEST);
		this.add(new JLabel("   "), BorderLayout.EAST);

	}

	public int getDifficulty(){
		if(vhard.isSelected()) return 5;
		else if(hard.isSelected()) return 4;
		else if(normal.isSelected()) return 3;
		else if(easy.isSelected()) return 2;
		else return 1;
	}

}