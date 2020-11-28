package model;

import processing.core.PApplet;

public class Indicator implements Comparable<Indicator> {

	protected int quantity, color;
	protected String type;
	protected PApplet app;

	public Indicator(PApplet app, String type, int quantity, int color) {
		this.app = app;
		this.quantity = quantity;
		this.color = color;
		this.type = type;
	}

	/**
	 * Natural sorting method by comparing indicators' quantities
	 */

	@Override
	public int compareTo(Indicator o) {

		return o.quantity - quantity;
	}

	public void drawIndicator(int posX, int posY) {

		switch (color) {

		case 1:
			app.fill(102, 242, 129);
			break;

		case 2:
			app.fill(245, 84, 105);
			break;

		case 3:
			app.fill(63, 232, 255);
			break;

		}

		app.textAlign(app.CENTER);
		app.text(type + ": " + quantity, posX, posY);

	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}
}
