package Q1;

public class Berry extends Fruit{
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
