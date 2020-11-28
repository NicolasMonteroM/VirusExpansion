package controller;

import model.Logic;
import processing.core.PApplet;

public class Controller {
	
	Logic logic;
	PApplet app;

	public Controller(PApplet app) {
		this.app = app;
		logic = new Logic(app);
		
	}
	
	public void startSimulation() {
		logic.initializeSimulation();
		
	}
	
	public void drawSimulation() {
		logic.drawIndicators();
		logic.drawPerson();
		logic.checkContact();
		logic.instructions();
		
	}
	
	public void controllerInteractions(char c) {
		logic.sort(c);
		
	}

}
