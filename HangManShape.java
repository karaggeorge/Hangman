import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.*;
import java.awt.*;
import java.util.*;
import java.util.List;


public class HangManShape {
	
	private int x, y, width, height;
	private boolean win,lose;
	private List<Shape> parts = new ArrayList<Shape> ();
	
	public HangManShape(int x, int y, int width, int height) {
		
		win = false;
		lose = false;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		design();
		
	}

	public void setLose(boolean lose){
		this.lose = lose;
	}

	public void setWin(boolean win){
		this.win = win;
	}
	
	public void design(){
		parts.add(new Line2D.Double(x,y+height,x+width/3,y+height));   //Base
		parts.add(new Line2D.Double(x+width/6,y+height,x+width/6,y+height/6));  //Basse
		parts.add(new Line2D.Double(x+width/6,y+height/6,x+2*width/3,y+height/6)); //Base
		parts.add(new Line2D.Double(x+2*width/3,y+height/6,x+2*width/3,y+height/6+height/12)); //Rope
		parts.add(new Ellipse2D.Double(x+2*width/3-width/24,y+height/6+height/12,width/12,width/12)); //Head
		parts.add(new Line2D.Double(x+2*width/3,y+height/6+height/12+width/12,x+2*width/3,y+2*height/3));  //Torso
		parts.add(new Line2D.Double(x+2*width/3,y+height/3+width/24,x+2*width/3-width/12,y+height/3+width/24+height/12)); //Left Hand
		parts.add(new Line2D.Double(x+2*width/3,y+height/3+width/24,x+2*width/3+width/12,y+height/3+width/24+height/12)); //Right Hand
		parts.add(new Line2D.Double(x+2*width/3,y+2*height/3,x+2*width/3-width/24,y+2*height/3+height/6)); //Left Leg
		parts.add(new Line2D.Double(x+2*width/3,y+2*height/3,x+2*width/3+width/24,y+2*height/3+height/6)); //Right Leg
		parts.add(new Ellipse2D.Double(x+2*width/3-width/24+width/48,y+height/6+height/12+width/48,3,3)); //Right Eye
		parts.add(new Ellipse2D.Double(x+2*width/3-width/24+3*width/48,y+height/6+height/12+width/48,3,3)); //Left Eye
		parts.add(new Line2D.Double(x+2*width/3,y+height/6+height/12+width/48,x+2*width/3,y+height/6+height/12+width/24)); //Nose
		parts.add(new Line2D.Double(x+2*width/3-width/24+width/48,y+height/6+height/12+3*width/48,x+2*width/3-width/24+3*width/48,y+height/6+height/12+3*width/48)); //Mouth
		parts.add(new Line2D.Double(x+2*width/3-width/24,y+2*height/3+height/6,x+2*width/3-width/24-10,y+2*height/3+height/6)); //Left Shoe
		parts.add(new Line2D.Double(x+2*width/3+width/24,y+2*height/3+height/6,x+2*width/3+width/24+10,y+2*height/3+height/6)); //Right Shoe


	}

	public void draw(Graphics2D g, double perc) {
		g.setColor(Color.black);
		
		int length = (int)(parts.size()*perc);
		if(length>=parts.size()){
			length = parts.size();
			setLose(true);
		}

		for(int i = 0;i < length;i++){
			g.draw(parts.get(i));
		}

		if(win){
			g.setFont(new Font("Serif",Font.PLAIN,30));
			g.drawString("You Win!",x+width/3,y+40);
		}
		else if(lose){
			g.setFont(new Font("Serif",Font.PLAIN,30));
			g.drawString("You Lose!",x+width/3,y+40);
		}

	}
}
