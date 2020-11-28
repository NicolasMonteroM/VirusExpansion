package view;

import controller.Controller;
import processing.core.PApplet;

/**
 * 
 * @author nicolasmonteromuriel
 *
 */

public class Main extends PApplet {
	
	Controller controller;

	public static void main(String[] args) {
		PApplet.main(Main.class.getName());

	}

	public void settings() {
		size(600, 600);

	}

	public void setup() {
		controller = new Controller(this);
		controller.startSimulation();

	}

	public void draw() {
		background(20);
		controller.drawSimulation();

	}

	public void keyPressed() {
		controller.controllerInteractions(key);
		
	}

}
