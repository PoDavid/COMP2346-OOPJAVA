/**
 * The type Java doc example.
 */
public class JavaDocExample {

	/**
	 * The entry point of application.
	 *
	 * @param args the input arguments
	 */
	public static void main(String[] args) {
		JavaDocExample app = new JavaDocExample("Hello, World!");
		System.out.print("Hello world length = " + app.getLength());
	}

	/**
	 * The Message.
	 */
	public String message;

	/**
	 * Instantiates a new Java doc example.
	 *
	 * @param message the message
	 */
	public JavaDocExample(String message) {
		this.message = message;
	}

	/**
	 * Gets length.
	 *
	 * @return the length
	 */
	public int getLength() {
	    return message.length();
	}

}
