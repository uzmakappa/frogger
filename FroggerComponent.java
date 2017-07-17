package HW7;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;

public class FroggerComponent extends JComponent {
	
	/**
	 * create a Component that holds the frogger items to be drawn
	 * @param carLength = length of car in frogger
	 * @param frogHeight = height of frog in frogger
	 */
	public FroggerComponent(int carLength, int frogHeight) {
		this.carLength = carLength;
		this.frogHeight = frogHeight;
		this.carX = 0;
		this.carY = frogHeight;
		this.frogX = 5*carLength/2 - frogHeight/2;
		this.frogY = 2*frogHeight;
	}

	
	
	/** call on the frogger's paint method to paint the game items at the given locations */
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		Rectangle2D.Double car = new Rectangle2D.Double(carX, carY, carLength, frogHeight);
		Ellipse2D.Double frog = new Ellipse2D.Double(frogX, frogY, frogHeight, frogHeight);
		g2.setColor(Color.GRAY);
		g2.fill(car);
		g2.setColor(Color.GREEN);
		g2.fill(frog);
	}


	
	/** get length of car */
	public int getCarLength() {
		return carLength;
	}

	
	/** get height of frog */
	public int getFrogHeight() {
		return frogHeight;
	}

	
	/** get x coordinate of top-left corner of car */
	public int getCarX() {
		return carX;
	}

	
	/** get y coordinate of top-left corner of car */
	public int getCarY() {
		return carY;
	}


	/** get y coordinate of top-left corner of frog */
	public int getFrogY() {
		return frogY;
	}

	
	/**
	 * set x coordinate of top-left corner of car
	 * @param newX
	 */
	public void setCarX(int newX)
	{
		carX = newX;
	}

	
	/**
	 * set y coordinate of top-left corner of car
	 * @param newY
	 */
	public void setCarY(int newY)
	{
		carY = newY;
	}
	

	
	/**
	 * move frog left, right, up, or down
	 * @param direction
	 */
	public void moveFrog(int direction) {
		switch (direction) {
		case LEFT:  
			if (frogX > 0) frogX -= frogHeight; 
			break;
		case RIGHT: 
			if ((frogX+frogHeight) < 5*carLength) frogX += frogHeight; 
			break;
		case UP:    
			if (frogY > 0) frogY -= frogHeight;
			break;
		case DOWN:  
			if ((frogY+frogHeight) < 3*frogHeight) frogY += frogHeight; 
			break;
		}
	}

	
	
	/** Detects whether the car and the frog collide */
	public boolean impact() {
		int minX = carX - frogHeight;
		int maxX = carX + carLength;
		int minY = carY;
		int maxY = carY + frogHeight;

		return (minX <= frogX && frogX <= maxX && minY <= frogY && frogY < maxY);
	}

	private int carLength;
	private int frogHeight;
	private int carX;
	private int carY;
	private int frogX;
	private int frogY;

	public static final int LEFT = 0;
	public static final int RIGHT = 1;
	public static final int UP = 2;
	public static final int DOWN = 3;


}
