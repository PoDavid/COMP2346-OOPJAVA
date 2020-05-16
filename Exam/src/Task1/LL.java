
public class LL {
	
	private int v;
	protected LL n;
	
	public LL(int v) {
		this.v = v;
		n = null;
	}
	
	public LL(int v, LL n) {
		this.v = v;
		this.n = n;
	}
	
	public int getV() { return v; }

}
