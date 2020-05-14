package Q1;

/**
 * The Fragaria class extends from Fruit, used to model a Fragaria.
 *
 * @author Po Yat Ching David UID:3035372098
 */
public class Fragaria extends Fruit{
	/**
	 * Instantiates a new Fragaria.
	 */
	public Fragaria() {
	}
	public String toString() {
		return "a fragaria";
	}
	public void prepare() {
		System.out.print("Caps and stems removed from ");
		System.out.println(this);
	}
	
}
