package Q1;

/**
 * The Berry class extends from Fruit, used to model a Berry.
 *
 * @author Po Yat Ching David UID:3035372098
 */
public class Berry extends Fruit{
	/**
	 * Instantiates a new Berry.
	 *
	 * @param weight the weight
	 */
	public Berry(double weight) {
		super(weight);
	}
	public String toString() {
		return "one " + weight + "g berry";
	}

	public void prepare() {
		deseed();
	}
		
}
