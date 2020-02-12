import java.io.*;
import java.text.DecimalFormat;

public class PostfixReader {
	public boolean is_operator(String a){
		return ((a.equals("+")) || (a.equals("-")) || (a.equals("*")) || (a.equals("/")) | (a.equals("^")));
	}
	public String[] split_expression(String input){
		String[] output = new String[256];
		int j=0;
		String operand = null;
		char[]input_array = input.toCharArray();
		for(int i=0;i<input_array.length;i+=1){
			if(input_array[i]>='0'&&input_array[i]<='9') {
				if(i+1!=input_array.length&&input_array[i+1]=='.'){
					if(operand!=null)
						operand = operand + input_array[i] +".";
					else
						operand = input_array[i] +".";
					i+=1;
				}
				else if(operand==null)
					operand= String.valueOf(input_array[i]);
				else
					operand = operand + input_array[i];
			}
			else if(!(input_array[i]>='0'&&input_array[i]<='9')){
				if(i!=0&&input_array[i]=='-'&&(!(input_array[i-1]>='0'&&input_array[i-1]<='9'))){
					operand="-";
				}
				else if(operand==null){
					output[j]= String.valueOf(input_array[i]);
					j+=1;
				}
				else{
					output[j]=operand;
					operand=null;
					output[j+1] = String.valueOf(input_array[i]);
					j+=2;
				}
			}
		}
		return output;
	}

	public String calculator(String operator, String operand_a, String operand_b){
		double answer=0;
		double a = Double.parseDouble(operand_a);
		double b = Double.parseDouble(operand_b);
		switch(operator){
			case"^":
				double sum = a;
				for(int i=0;i<b-1;i++)
					sum*=a;
				answer = sum;
				break;
			case"*":
				answer = a*b;
				break;
			case"/":
				answer = a/b;
				break;
			case"+":
				answer = a+b;
				break;
			case"-":
				answer = a-b;
				break;

		}
		DecimalFormat df = new DecimalFormat("###.#");
  		return String.valueOf(df.format(answer));
	}
	public static void main(String[] args) {
		PostfixReader myAnswer = new PostfixReader();
		myAnswer.doConversion();
	}

	public void doConversion() {
		// TODO: read Postfix from input using readPostfix(), then convert it to infix and
		// print it out
		String[]  input;
		String[] output;
		boolean error=false;
		Stack stack = new Stack();
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
						System.out.println("Error: Invalid postfix");
						error=true;
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
		if (!error) {
			System.out.print("Infix: ");
			output = split_expression(infix);
			for (int i = 0; i < output.length && output[i] != null; i += 1) {
				System.out.print(output[i] + " ");
			}
			System.out.println();
			evalInfix(infix);
		}
	}

	public void evalInfix(String infix) {
		// TODO: evaluate the infix representation of the input arithmetic expression, 
		// and then print the result of the evaluation of the expression on the next 
		// line.
		//System.out.print(infix);
		Stack operand_stack = new Stack();
		Stack operator_stack = new Stack();
		String operator, operand_a, operand_b, result;
		String[] splitted_infix = split_expression(infix);
		for(int i=0;i<splitted_infix.length&&splitted_infix[i]!=null;i+=1){
			if(is_operator(String.valueOf(splitted_infix[i]))){
				operator_stack.push(splitted_infix[i]);
			}
			else if(splitted_infix[i].equals(")")){
				operator=operator_stack.pop();
				operand_b=operand_stack.pop();
				operand_a=operand_stack.pop();
				result = calculator(operator,operand_a,operand_b);
				operand_stack.push(result);
			}
			else if(!splitted_infix[i].equals("(")){
				operand_stack.push(splitted_infix[i]);
			}
		}
		System.out.println("Result: "+ operand_stack.pop());
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