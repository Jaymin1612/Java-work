import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TextFormatter {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter text:");
		String input = br.readLine();
		ITextFormatter formatter = null;
		switch(input.charAt(0)) {
		  	case '+':
		  		formatter = new startsWithPlus();
				break;
		    case '-':
		    	formatter = new startsWithMinus();
		    	break;
		  	default:
		   		System.out.println(input);
		}
		stringPrinter.print(input, formatter);

	}
	
}