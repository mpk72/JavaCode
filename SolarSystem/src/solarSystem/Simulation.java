package solarSystem;

import javax.swing.JFrame;

public class Simulation {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
			
		/*
		 * THREAD ONE  --  PHYSICS ENGINE
		 */
		Physics p = new Physics(60);
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
