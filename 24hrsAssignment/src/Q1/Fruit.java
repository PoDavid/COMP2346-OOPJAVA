package Q1;

public class Fruit {
	protected double weight;
	static int count=0;
	public Fruit() {
		count++;
	}
	public Fruit(double weight){
		this.weight = weight;
		count++;
	}
	public String toString() {
		return "a fruit";
	}
	public int getCount(){
		return count;
	}
	public void deseed() {
		System.out.print("Seed from ");
		System.out.print(this);
		System.out.println(" removed");
	}
	public void prepare() {
		System.out.print("Cleaned ");
		System.out.println(this);
	}

}
