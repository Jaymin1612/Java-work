
public class startsWithMinus implements ITextFormatter{
	public void printString(String input){
		System.out.println(input.replaceAll("\\-", "").toLowerCase());
	}
}
