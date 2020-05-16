
public class IsLY {

	public static class LYEx extends Exception { }
	public static class NLYEx extends Exception { }

	static void checkLY(String s)
		throws LYEx, NLYEx, NumberFormatException {

		long yAsLong = Long.parseLong(s);

		final boolean results[] =
			{ true, false, false, true,
			  false, false, false, false };

		if ( results[
		     ((((yAsLong % 4) == 0) ? 1 : 0) << 2) +
		     ((((yAsLong % 100) == 0) ? 1 : 0) << 1) +
		     ((((yAsLong % 400) == 0) ? 1 : 0) << 0)]) {
			throw new LYEx();
		} else {
			throw new NLYEx();
		}
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (args.length > 0) {

			try {
				checkLY(args[0]);
			} catch ( NumberFormatException e ) {
				System.out.println(
					"Invalid argument: " +
					e.getMessage());
			} catch ( LYEx e ) {
				System.out.println(
					args[0] + " is true");
			} catch ( NLYEx e ) {
				System.out.println(
					args[0] + " is not true");
			}
		}
	}

}
