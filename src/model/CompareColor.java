package model;

import java.util.Comparator;

public class CompareColor implements Comparator<Indicator> {

	@Override
	public int compare(Indicator o1, Indicator o2) {

		return o2.getColor() - o1.getColor();
	}

}
