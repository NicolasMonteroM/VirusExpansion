package exception;

/**
 * 
 * @author nicolasmonteromuriel
 *
 */

public class NewInfection extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2740277434597731695L;

	public NewInfection() {
		super("New person infected");
	}

}
