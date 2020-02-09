import java.io.*;
import java.util.Arrays;

public class PostfixReader {

	private Stack stack;

	public PostfixReader() {
		stack = new Stack();
	}
	public boolean is_operator(String a){
		return ((a.equals("+")) || (a.equals("-")) || (a.equals("*")) || (a.equals("/")) | (a.equals("^")));
	}

	public static void main(String[] args) {
		PostfixReader myAnswer = new PostfixReader();
		myAnswer.doConversion();
	}

	public void doConversion() {
		// TODO: read Postfix from input using readPostfix(), then convert it to infix and
		// print it out
		String[]  input;
		input = readPostfix();
		if (input.length > 0){
			int i = 0;
			while(i < input.length){
				if(!is_operator(input[i]))
					stack.push(input[i]);
				else{
					String a = stack.pop();
					String b = stack.pop();
					if (a == null || b == null){
						System.out.println("Invalid Postfix" + "\n");
						break;
					}
					else {
						stack.push("(" + b + input[i] + a + ")");
					}
				}
				i++;
			}
		}
		String infix = stack.get_infix();
		System.out.print("Infix: ");
		for (char c : infix.toCharArray()) {
    		System.out.print(c + " ");
		}
		evalInfix(infix);
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

	public String get_infix(){
        return stack[0];
	}
}