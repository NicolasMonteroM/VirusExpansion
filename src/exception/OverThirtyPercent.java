package exception;

public class OverThirtyPercent extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8153420509722347318L;

	public OverThirtyPercent() {
		super("Over 30% of the population has been infected");
	}

}
