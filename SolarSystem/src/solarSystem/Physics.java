package solarSystem;

/**
 * This class manages the thread that is running the physics in the background.
 * It initializes the simulation and 
 * @author Matt
 *
 */
public class Physics extends Thread{

	private final double TIMESTEP = 0.01;   
	
	/**
	 * Construct the physics simulation with nBodies. 
	 * @param nBodies must be a positive integer.
	 */
	Physics(int nBodies){
		for(int i=0; i<nBodies; i++){
			new PointMass();
		}
	}
	
	/**
	 * Here is where the actual work of the simulation is done
	 */
	public void run() {
		while (true){  // Assumes that application aborts when GUI closes
			PointMass.runPhysics(TIMESTEP);
			try {
				Thread.sleep((long)(1000*TIMESTEP));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}			
		
		
	}

}
