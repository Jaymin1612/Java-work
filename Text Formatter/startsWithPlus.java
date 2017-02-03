
public class startsWithPlus implements ITextFormatter{
	public void printString(String input){
		System.out.println(input.replaceAll("\\+", "").toUpperCase());
	}

}
