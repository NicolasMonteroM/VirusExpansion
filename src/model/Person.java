package model;

import processing.core.PApplet;

public abstract class Person implements Runnable {

	protected int posX, posY, size, speed, dirX, dirY;
	protected PApplet app;

	public Person(PApplet app) {
		this.app = app;
		this.posX = (int) app.random(10, app.width - 10);
		this.posY = (int) app.random(10, app.height - 10);
		this.size = 7;
		this.speed = (int) app.random(1, 4);
		this.dirX = 1;
		this.dirY = 1;

	}

	public void drawPerson() {

	}

	/**
	 * Person's movement
	 */

	public void movement() {

		posX += getSpeed() * dirX;
		posY += getSpeed() * dirY;

		if (posX >= app.width - getSize() && posX > getSize() || posX < getSize()) {
			dirX *= -1;
		}

		if (posY >= app.height - getSize() && posY > getSize() || posY < getSize()) {
			dirY *= -1;
		}
	}

	/**
	 * Run method overrided from Runnable interface
	 */

	@Override
	public void run() {

		movement();

	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public PApplet getApp() {
		return app;
	}

	public void setApp(PApplet app) {
		this.app = app;
	}

	public int getDirX() {
		return dirX;
	}

	public void setDirX(int dirX) {
		this.dirX = dirX;
	}

	public int getDirY() {
		return dirY;
	}

	public void setDirY(int dirY) {
		this.dirY = dirY;
	}

}
