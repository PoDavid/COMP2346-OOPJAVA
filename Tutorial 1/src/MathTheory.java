public class MathTheory{
    public static int power(int base, int exponent) {
        int result = 1;
        while(exponent-->0)
            result *=base;
        return result;
    }
    public static void main(String[] args) {
		int power = MathTheory.power(2,10);
		System.out.print(power);
	}
}
