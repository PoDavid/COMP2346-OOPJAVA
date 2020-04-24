package Q1;

/**
 * The Fruit class, used to model a Fruit.
 * @author Po Yat Ching David UID:3035372098
 */
public class Fruit {
	/**
	 * The Weight of the fruit.
	 */
	protected double weight;
	/**
	 * The Total number of fruit created.
	 */
	static int count=0;

	/**
	 * Instantiates a new Fruit.
	 */
	public Fruit() {
		count++;
	}

	/**
	 * Instantiates a new Fruit.
	 *
	 * @param weight the weight
	 */
	public Fruit(double weight){
		this.weight = weight;
		count++;
	}
	public String toString() {
		return "a fruit";
	}

	/**
	 * Get the total number of fruit.
	 *
	 * @return the total number of fruit int
	 */
	public int getCount(){
		return count;
	}

	/**
	 * Deseed the fruit.
	 */
	public void deseed() {
		System.out.print("Seed from ");
		System.out.print(this);
		System.out.println(" removed");
	}

	/**
	 * Prepare the fruit.
	 */
	public void prepare() {
		System.out.print("Cleaned ");
		System.out.println(this);
	}

}
