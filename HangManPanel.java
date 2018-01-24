import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.*;
import java.awt.*;

public class HangManPanel extends javax.swing.JPanel {
	private HangManShape hangshape;
	private HangmanManager manager;
	private static final int HANG_WIDTH = 75, PANEL_WIDTH = 700, PANEL_HEIGHT=400, HANG_HEIGHT = 150, X = 100, Y = 100;
	//private  ShipShape shape; //CarShape is polymorphically  declared of interface type MoveableShape. 
		   
		public HangManPanel(HangmanManager manager) {
			   super();	   
			   this.manager = manager;
			   manager.setPanel((this));
			   this.setBackground(java.awt.Color.white);
			   this.setPreferredSize(new java.awt.Dimension(PANEL_WIDTH, PANEL_HEIGHT));
			   this.setSize(new java.awt.Dimension(PANEL_WIDTH, PANEL_HEIGHT));
	           
			   hangshape = new HangManShape(50,0,PANEL_WIDTH-100,PANEL_HEIGHT-50);
		}

		public void paintComponent(Graphics g) {
			  super.paintComponent(g);
			  Graphics2D brush = (Graphics2D) g;
			  brush.setFont(new Font("Serif",Font.PLAIN,20));
			  brush.drawString("Lives Remaining = " + manager.getLives(), 5, 20);
			  brush.setFont(new Font("Serif",Font.PLAIN,30));
			  brush.drawString(manager.getWord(), 70, PANEL_HEIGHT);

			  hangshape.setWin(manager.checkWin());
			  hangshape.draw(brush,getLifePerc());
			  
			  
		}

		private double  getLifePerc(){
			double perc = manager.getMaxLives() - manager.getLives();
			perc = (double)(perc/(double)manager.getMaxLives());

			return perc;
		}

		public void refresh(){
			repaint();
		}
	}
