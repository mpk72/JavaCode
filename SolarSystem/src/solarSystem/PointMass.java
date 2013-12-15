package solarSystem;

import java.util.*;
import java.lang.Math;

public class PointMass {

	private double xPos; // (m) position along x axis
	private double yPos; // (m) position along the y axis

	private double xVel; // (m/s) velocity along the x axis
	private double yVel; // (m/s) velocity along the y axis

	private double xPos_next; // (m) position along x axis
	private double yPos_next; // (m) position along the y axis

	private double xVel_next; // (m/s) velocity along the x axis
	private double yVel_next; // (m/s) velocity along the y axis

	private double mass; // (kg) mass of the particle
	private double radius; // (m) radius of the particle (for collisions)

	public final static double systemRadius = 85.0;

	private final static double density = 70; // (kg/m^3) particle density

	private int ID; // Identification Number

	// Gravitational Constant
	public final double G = 1; // (m^3/(kg*s^2)

	/**
	 * A data structure that keeps track of all of the point masses in the
	 * simulation.
	 */
	public static HashSet<PointMass> allBodies = new HashSet<PointMass>();

	/*
	 * Keep track of how many point masses are in the system. This is used to
	 * name each new particle as it is created.
	 */
	private static int nBodies = 0;

	/**
	 * Constructor: creates a new PointMass object with random stats
	 */
	PointMass() {
		
		double r = systemRadius*Math.random();
		double th = Math.PI - 2*Math.PI*Math.random();
		
		double v = 0.5*Math.random();
		double phi = Math.PI - 2*Math.PI*Math.random();
		
		double bigMass = 30;
		
		xPos = r*Math.cos(th);
		yPos = r*Math.sin(th);
		xVel = v*Math.cos(phi);
		yVel = v*Math.sin(phi);
		mass = 0.5 + bigMass*Math.random();
		
		if (mass>0.8*bigMass){
			// make a few planets among asteroids
			mass = mass*(1.0 + 10.0*Math.random());
			xVel = 2*xVel;
			yVel = 2*yVel;
		}
		
		radius = calculateRadius();

		this.ID = nBodies;
		nBodies++;
		allBodies.add(this);
		
	}

	/*
	 * This is just a manipulation of the equation for volume of a sphere
	 */
	private double calculateRadius() {
		return Math.pow((mass * 3.0) / (Math.PI * density * 4.0), 1.0 / 3.0);
	}

	/**
	 * Run the system dynamics forward by one time step
	 * 
	 * @param h
	 *            time step for integration
	 */
	public static void runPhysics(double h) {

		/*
		 * Figure out where each body would move based on the current
		 * configuration of the system.
		 */
		for (PointMass p : allBodies) {
			p.calculateNextState(h);
		}

		// Now move all of the point masses at once
		for (PointMass p : allBodies) {
			p.updateState();
		}

	}

	/**
	 * Print the number of bodies in the system and the print the position of
	 * each one.
	 */
	public static void printSystemInfo() {
		System.out.println("Number of bodies: " + nBodies);
		for (PointMass p : allBodies) {
			System.out.println("   M" + p.ID + " ("
					+ String.format("%6.4f", p.xPos) + ", "
					+ String.format("%6.4f", p.yPos) + ")");
		}
	}

	/**
	 * Get the force acting on the current PointMass from another point mass. If
	 * the masses are coincident, returns null instead of div by 0 error.
	 */
	private Double[] getForce(PointMass p) {

		// Find the distance between the objects:
		double dx = p.xPos - this.xPos;
		double dy = p.yPos - this.yPos;
		double r2 = dx * dx + dy * dy; // Distance squared
		double r = Math.sqrt(r2); // Distance

		// Check to figure out collision stuff
		if (r < radius + p.radius) { // Then a collision occurred!

		}

		// Check that there are no divide by zero things...
		if (r2 == 0.0)
			return null;

		// Get the magnitude of the force (inverse-square law)
		double F = G * this.mass * p.mass / r2;

		// Now give the force a direction
		Double[] Force = new Double[2];
		Force[0] = F * dx / r;
		Force[1] = F * dy / r;

		// Check to figure out collision stuff
		if (r < radius + p.radius) { // Then a collision occurred!
			// Turn off forces...
			Force[0] = 0.0;
			Force[1] = 0.0;
		} 
			return Force;
	}

	/**
	 * Get the sum of all forces acting on this point mass
	 */
	public Double[] getForce() {

		Double[] Force = new Double[2]; // sum of all forces
		Force[0] = 0.0;
		Force[1] = 0.0;

		Double[] F = new Double[2]; // individual force
		for (PointMass p : allBodies) {
			if (p != this) {
				F = getForce(p);
				Force[0] = Force[0] + F[0];
				Force[1] = Force[1] + F[1];
			}
		}

		return Force;
	}

	/**
	 * Use Euler integration with a time step of h to get the next state
	 */
	public void calculateNextState(double h) {
		xPos_next = xPos + h * xVel;
		yPos_next = yPos + h * yVel;

		Double[] Force = getForce(); // sum of all forces on point mass
		xVel_next = xVel + h * Force[0] / mass;
		yVel_next = yVel + h * Force[1] / mass;
	}

	
	/**
	 * Update the state of the point mass. This is used after the next state for
	 * every mass in the system has been calculated.
	 */
	public void updateState() {
		
		double r2 = xPos_next*xPos_next + yPos_next*yPos_next;
		if (r2 > systemRadius*systemRadius){
			// Then the particle will leave the system - wrap to other side
			xPos = -xPos;
			yPos = - yPos;
		} else {
			// Particle did not leave the system - normal update
			xPos = xPos_next;
			yPos = yPos_next;
		}
		
		xVel = xVel_next;
		yVel = yVel_next;
		
	}

	/**
	 * get the horizontal position of point mass
	 */
	public double getXPos() {
		return xPos;
	}

	/**
	 * get the vertical position of each point
	 */
	public double getYPos() {
		return yPos;
	}

	/**
	 * get the radius of the particle
	 */
	public double getRadius() {
		return radius;
	}

}
