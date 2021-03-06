package HW7;

import java.awt.event.*;

import javax.swing.*;

/** This program implements a simple frogger game */
public class FroggerTester
{
	public static void main(String[] args) throws InterruptedException
	{
		final FroggerComponent frogger = new FroggerComponent(200,100);

		JFrame frame = new JFrame();
		frame.add(frogger);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setSize(5*frogger.getCarLength(), 3*frogger.getFrogHeight());
		frame.setVisible(true);
		frogger.repaint();

		// moves car back and forth across JFrame using timer
		ActionListener listener = new ActionListener() {
			public void actionPerformed(ActionEvent event)
			{
				int spd = frogger.getCarLength()/4;
				if(moveRight) {
					frogger.setCarX(frogger.getCarX() + spd);
				}
				else {
					frogger.setCarX(frogger.getCarX() - spd);
				}
				if (frogger.getCarX() == 4*frogger.getCarLength()) {
					moveRight = false;
				}
				else if (frogger.getCarX() == 0) {
					moveRight = true;
				}

				if (frogger.impact()) {
					JOptionPane.showMessageDialog(frame, "Sorry, you lose...");
					frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
				}
				
				frogger.repaint();
			}
		};

		// timer for car motion
		final int delay = 250;
		Timer t = new Timer(delay, listener);
		t.start();

		// allows user to control frog motion using keyboard arrow keys
		frame.addKeyListener(new KeyListener() {

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_LEFT) {
					frogger.moveFrog(LEFT);
					frogger.repaint();
				}

				else if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
					frogger.moveFrog(RIGHT);
					frogger.repaint();
				}

				else if (e.getKeyCode()==KeyEvent.VK_UP) {
					frogger.moveFrog(UP);
					frogger.repaint();
				}

				else if (e.getKeyCode()==KeyEvent.VK_DOWN) {
					frogger.moveFrog(DOWN);
					frogger.repaint();
				}
				
				if (frogger.getFrogY() == 0) {
					JOptionPane.showMessageDialog(frame, "Congratulations, you win!");
					frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub	
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub	
			}
		});
	}

	public static final int LEFT = 0;
	public static final int RIGHT = 1;
	public static final int UP = 2;
	public static final int DOWN = 3;

	public static boolean moveRight = true;
}
