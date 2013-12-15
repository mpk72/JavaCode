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
		
		Container cp = getContentPane();
		//cp.add(new JButton("click me"), BorderLayout.EAST);
		cp.add(disp, BorderLayout.CENTER);
		//cp.add(new JCheckBox("I got up today"), BorderLayout.SOUTH);
		pack();
	}
	
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
