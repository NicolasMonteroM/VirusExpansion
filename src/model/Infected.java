package model;

import processing.core.PApplet;

public class Infected extends Person{

	public Infected(PApplet app) {
		super(app);

	}

	@Override
	public void drawPerson() {
		
		app.noStroke();
		app.fill(245, 84, 105);
		app.ellipse(posX, posY, size, size);

	}

}
