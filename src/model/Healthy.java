package model;

import processing.core.PApplet;

public class Healthy extends Person {

	public Healthy(PApplet app) {
		super(app);

	}

	@Override
	public void drawPerson() {
		
		app.noStroke();
		app.fill(102, 242, 129);
		app.ellipse(posX, posY, size, size);

	}
}
