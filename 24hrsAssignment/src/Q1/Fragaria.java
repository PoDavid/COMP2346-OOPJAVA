package Q1;

public class Fragaria extends Fruit{
	public Fragaria() {
		super();
	}
	public String toString() {
		return "a fragaria";
	}
	public void prepare() {
		System.out.print("Caps and stems removed from ");
		System.out.println(this);
	}
	
}
