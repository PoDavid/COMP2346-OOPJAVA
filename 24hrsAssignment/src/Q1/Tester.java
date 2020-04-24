package Q1;

import java.util.ArrayList;

public class Tester {
	private final ArrayList<Fruit> fruits = new ArrayList<>();

	public static void main(String[] args) {
		Tester t = new Tester();
		t.add(new Fruit());
		t.add(new Berry(3.7));
		t.add(new Berry(4));
		t.add(new Vitis(5.2));
		t.add(new Berry(5));
		t.add(new Fragaria());
		t.add(new Berry(6));
		t.add(new Vitis(10));
		t.add(new Fragaria());
		t.start();
		
		System.out.println(t.getCount()+" fruits are juiced");

	}
	public void add(Fruit fruit){
		fruits.add(fruit);
	}
	public int getCount(){
		if (fruits.size()!=0){
			return fruits.get(0).getCount();
		}
		return 0;
	}
	public void start() {
		for (Fruit fruit : fruits){
			fruit.prepare();
		}
	}
}
