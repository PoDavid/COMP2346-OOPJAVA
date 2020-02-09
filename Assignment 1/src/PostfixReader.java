import java.io.*;

public class PostfixReader {


	public static void main(String[] args) {
		PostfixReader myAnswer = new PostfixReader();
		myAnswer.doConversion();
	}

	public void doConversion() {
		// TODO: read Postfix from input using readPostfix(), then convert it to infix and
		// print it out
	}

	public void evalInfix(String infix) {
		// TODO: evaluate the infix representation of the input arithmetic expression, 
		// and then print the result of the evaluation of the expression on the next 
		// line.
	}

	public String[] readPostfix() {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String inputLine;
		try {
			System.out.print("Input Postfix: ");
			inputLine = input.readLine();
			return inputLine.split(" ");
		} catch (IOException e) {
			System.err.println("Input ERROR.");
		}

		// return empty array if error occurs
		return new String[] {};
	}

}

class Stack {
	// TODO: implement Stack in this class
	private String[] stack;
	private int size;
	public Stack(){
		stack=new String[256];
		size=-1;
	}
	public void push(String input){
		size+=1;
		stack[size]=input;
	}
	public String pop(){
		if (size > -1) {
			String output;
			output = stack[size];
			size -= 1;
			return output;
		}
		return null;
	}
}