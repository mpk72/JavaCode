package solarSystem;

import javax.swing.*;

import java.awt.*;

/**
 * GUI is the top level class for handling the GUI for this application. It 
 * calls other classes to handle the content within each JPanel.
 * @author Matt
 *
 */
public class GUI extends JFrame implements Runnable{

	private final double FRAMERATE = 30;
	
	public Animation disp;
	
	public GUI(String t){
		super(t);
		disp = new Animation();
		
		/* For now, there is only a single JPanel in the gui. 
		* More will be added at a future time to give the user control over
		* restarting the application, and changing parameters 
		*/
		Container cp = getContentPane();
		cp.add(disp, BorderLayout.CENTER);
		pack();
	}
	
	/**
	 * This method runs the gui animation. It basically just refreshed the
	 * gui at FRAMERATE (or something close to it).
	 */
	public void run(){

		long h = (long)(1000.0/FRAMERATE);
	
		while (true){   // Assumes that application aborts when GUI closes
			this.repaint();
			try {
				Thread.sleep(h);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}			


	}

}
