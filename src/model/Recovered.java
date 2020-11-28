package model;

import processing.core.PApplet;

public class Recovered extends Person {

	public Recovered(PApplet app) {
		super(app);

	}

	@Override
	public void drawPerson() {

		app.noStroke();
		app.fill(63, 232, 255);
		app.ellipse(posX, posY, size, size);

	}

}
