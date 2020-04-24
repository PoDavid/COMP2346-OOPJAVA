package Q1;

/**
 * The Vitis class extends from Fruit, used to model a Vitis.
 *
 * @author Po Yat Ching David UID:3035372098
 */
public class Vitis extends Fruit{
	/**
	 * Instantiates a new Vitis.
	 *
	 * @param weight the weight
	 */
	public Vitis(double weight) {
		super(weight);
	}
	public String toString() {
		return "one " + this.weight + "g vitis";
	}
	public void prepare() {
		deseed();
	}
}
