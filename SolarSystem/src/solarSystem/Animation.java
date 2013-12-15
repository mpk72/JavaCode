package solarSystem;

import javax.swing.*;

import java.awt.*;
import java.util.*;

/**
 * Animation is called from class GUI, and runs an animation in a single JPanel.
 * @author Matt
 *
 */
public class Animation extends JPanel{

	public final double lengthScale = 11;  // (pixels/meter) scales graphics

	public final int WIDTH = 800;
	public final int HEIGHT = 800;

	public Animation(){
		super();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setAlignmentX(Component.LEFT_ALIGNMENT);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));

	}

	private void plotPoints(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;

		g2d.setColor(Color.BLACK);

		Dimension size = getSize();

		int xOrigin = size.width/2;
		int yOrigin = size.height/2;
	
		Set<PointMass> s = Collections.synchronizedSet(PointMass.allBodies);
		synchronized(s){
			for (PointMass p:s) {
				double x = p.getXPos();
				double y = p.getYPos();
				int xx = (int)(x*lengthScale) + xOrigin;
				int yy = (int)(y*lengthScale) + yOrigin;
				int diameter = (int)(2*p.getRadius()*lengthScale);
				g2d.fillOval(xx, yy, diameter, diameter);
			}
		}
	}

	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		plotPoints(g);
	}

}
