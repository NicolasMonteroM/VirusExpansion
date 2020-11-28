package model;

import java.util.ArrayList;
import java.util.Collections;

import exception.NewInfection;
import exception.OverThirtyPercent;
import processing.core.PApplet;

/**
 * 
 * @author nicolasmonteromuriel
 *
 */

public class Logic {

	PApplet app;
	private ArrayList<Person> people;
	private ArrayList<Indicator> indicators;
	private String[] info;

	private Indicator healthyIndicator, infectedIndicator, recoveredIndicator;

	private CompareColor colorComparator;

	public Logic(PApplet app) {

		this.app = app;
		people = new ArrayList<Person>();
		indicators = new ArrayList<Indicator>();
		info = app.loadStrings("data/virusInitialValues.txt");

		colorComparator = new CompareColor();
	}

	/**
	 * Program's initial values
	 */

	public void initializeSimulation() {

		/* Creating indicators and adding them to a List */

		healthyIndicator = new Indicator(app, "Healthy", 0, 1);
		infectedIndicator = new Indicator(app, "Infected", 0, 2);
		recoveredIndicator = new Indicator(app, "Recovered", 0, 3);

		indicators.add(healthyIndicator);
		indicators.add(infectedIndicator);
		indicators.add(recoveredIndicator);

		/*
		 * Creating initial people from the .txt archive and adding them to a list
		 */

		for (int i = 0; i < info.length; i++) {

			String[] peopleData = info[i].split(" ");
			String type = peopleData[0];
			int quantity = Integer.parseInt(peopleData[1]);

			for (int j = 0; j < quantity; j++) {

				if (type.equals("healthy" + ":")) {
					people.add(new Healthy(app));

				}

				if (type.equals("infected" + ":")) {
					people.add(new Infected(app));

				}

				if (type.equals("recovered" + ":")) {
					people.add(new Recovered(app));

				}
			}
		}
	}

	/**
	 * Drawing each person from the list. Creating and starting movement threads.
	 */

	public void drawPerson() {

		for (Person p : people) {
			p.drawPerson();

			Thread personMovement = new Thread(p);
			personMovement.start();

		}

		/* Checking for their recovery */

		checkRecoveringTime();
	}

	/**
	 * Drawing each indicator and stating their quantity values.
	 */

	public void drawIndicators() {

		healthyIndicator.setQuantity(checkIndicator(1));
		infectedIndicator.setQuantity(checkIndicator(2));
		recoveredIndicator.setQuantity(checkIndicator(3));

		for (int i = 0; i < indicators.size(); i++) {

			indicators.get(i).drawIndicator(app.width / 2, (20 * i) + 30);

		}

	}

	public void instructions() {

		app.fill(100);
		app.text("Press key 'q' to sort indicators by quantity. Press 'c' to do it by color.", app.width / 2,
				app.height - 20);

	}

	/**
	 * Checking how many people of each type is in the people list.
	 */

	public int checkIndicator(int type) {

		int number = 0;

		for (int i = 0; i < people.size(); i++) {

			switch (type) {

			case 1:
				if (people.get(i) instanceof Healthy) {
					number++;
				}
				break;

			case 2:
				if (people.get(i) instanceof Infected) {
					number++;

					/*
					 * Trying a method that throws an exception when there's more than 30% of people
					 * infected
					 */

					try {
						checkOverThirty(number);
					} catch (OverThirtyPercent e) {

						System.out.println(e.getMessage());
					}

				}
				break;

			case 3:
				if (people.get(i) instanceof Recovered) {
					number++;
				}
				break;

			}
		}

		return number;
	}

	/**
	 * Method that throws an exception when there's more than 30% of people
	 * infected.
	 */

	public void checkOverThirty(int number) throws OverThirtyPercent {

		if (number >= 30) {

			throw new OverThirtyPercent();

		}
	}

	/**
	 * Checking people's nearness in order to create a new infection
	 */

	public void checkContact() {

		for (int i = 0; i < people.size(); i++) {

			for (int j = 0; j < people.size(); j++) {

				if (people.get(i) instanceof Infected && people.get(j) instanceof Healthy) {

					/*
					 * Trying if someone gets infected
					 */

					try {

						infect(people.get(i), people.get(j));

					} catch (NewInfection e) {

						System.out.println(e.getMessage());
						System.out.println("–––––––––");
					}

				}
			}
		}
	}

	public void checkRecoveringTime() {

		for (int i = 0; i < people.size(); i++) {

			recovered(people.get(i));
		}
	}

	public void recovered(Person i) {

		if (i instanceof Infected) {

			if (app.frameCount % 700 == 0) {

				Recovered r = new Recovered(app);

				changeType(r, i);

				people.add(r);
				people.remove(i);

			}
		}
	}

	/**
	 * Creating a new infection
	 */

	public void infect(Person i, Person h) throws NewInfection {

		int infectionProbability = (int) app.random(0, 100);

		int infectedPosX = i.getPosX();
		int infectedPosY = i.getPosY();
		int healthyPosX = h.getPosX();
		int healthyPosY = h.getPosY();

		if (infectionProbability > 90 && PApplet.dist(infectedPosX, infectedPosY, healthyPosX, healthyPosY) < 10) {

			Infected i2 = new Infected(app);

			changeType(i2, h);

			people.add(i2);
			people.remove(h);

			throw new NewInfection();

		}
	}

	public void changeType(Person p1, Person p2) {

		p1.setPosX(p2.getPosX());
		p1.setPosY(p2.getPosY());
		p1.setSpeed(p2.getSpeed());
		p1.setDirX(p2.getDirX());
		p1.setDirY(p2.getDirY());

	}

	/**
	 * Sorting indicators' list by pressing keys
	 */

	public void sort(char c) {

		switch (c) {

		case 'q':

			Collections.sort(indicators);
			break;

		case 'c':

			System.out.println(c);
			Collections.sort(indicators, colorComparator);
			break;

		default:
			break;

		}
	}
}
