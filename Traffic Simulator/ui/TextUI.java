package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Scanner;

public final class TextUI implements UI {
	final BufferedReader in;
	final PrintStream out;

	public TextUI() {
		this.in = new BufferedReader(new InputStreamReader(System.in));
		this.out = System.out;
	}

	@Override
	public void displayMessage(String message) {
		out.println(message);
	}

	@Override
	public void displayError(String message) {
		out.println(message);
	}

	private String getResponse() {
		String result;
		try {
			result = in.readLine();
		} catch (IOException e) {
			throw new UIError(); // re-throw UIError
		}
		if (result == null) {
			throw new UIError(); // input closed
		}
		return result;
	}

	@Override
	public void processMenu(UIMenu menu) {
		out.println(menu.getHeading());
		out.println("Enter choice by number:");

		for (int i = 1; i < menu.size(); i++) {
			out.println("  " + i + ". " + menu.getPrompt(i));
		}

		String response = getResponse();
		int selection;
		try {
			selection = Integer.parseInt(response, 10);
			if ((selection < 0) || (selection >= menu.size()))
				selection = 0;
		} catch (NumberFormatException e) {
			selection = 0;
		}

		menu.runAction(selection);
	}

	@Override
	public String[] processForm(UIForm form) {
		Scanner scan = new Scanner(System.in);
		String[] result = new String[form.size()];
		     
		for (int x = 0; x < form.size(); x++){
			System.out.print("Enter " + form.getPrompt(x) + ": ");
		    String response = scan.next();
		       
		    if (response == null) {
		    	response = "0";
		    }
		    result[x] = new String(response);
		}
		scan.close();
		return result;
	}
}
