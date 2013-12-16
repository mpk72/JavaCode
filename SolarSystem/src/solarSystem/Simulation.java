package solarSystem;

import javax.swing.JFrame;

/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~ *
 * ENTRY POINT FOR APPLICATION *
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~ *
 * 
 * This class creates a thread for the background physics and another thread
 * to run the GUI. It then runs both threads.
 * 
 * @author Matt
 *
 */
public class Simulation {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
			
		/*
		 * THREAD ONE  --  PHYSICS ENGINE
		 */
		int nParticles = 60;  // Number of point masses in simulation
		Physics p = new Physics(nParticles);
		p.start();
		
		/*
		 * THREAD TWO  --  GUI + ANIMATION
		 */		
		GUI gui = new GUI("Test");
		gui.setVisible(true);
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		new Thread(gui).start();
			
	}

}
