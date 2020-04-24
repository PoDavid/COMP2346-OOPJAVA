package Q1;

public class Vitis extends Fruit{
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
