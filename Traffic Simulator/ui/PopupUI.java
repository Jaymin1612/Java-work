package ui;

import javax.swing.JOptionPane;

public final class PopupUI implements UI {
	@Override
	public void displayMessage(String message) {
		JOptionPane.showMessageDialog(null,message);
	}

	@Override
	public void displayError(String message) {
		JOptionPane.showMessageDialog(null,message,"Error",JOptionPane.ERROR_MESSAGE);
	}

	@Override
	public void processMenu(UIMenu menu) {
		StringBuilder b = new StringBuilder();
		b.append(menu.getHeading());
		b.append("\n");
		b.append("Enter choice by number:");
		b.append("\n");

		for (int i = 1; i < menu.size(); i++) {
			b.append("  " + i + ". " + menu.getPrompt(i));
			b.append("\n");
		}

		String response = JOptionPane.showInputDialog(b.toString());
		if (response == null) {
			response = "";
		}
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
		
		String[] result = new String[form.size()];
		     
		for (int x = 0; x < form.size(); x++){
			String response = JOptionPane.showInputDialog("Enter " + form.getPrompt(x));
		    result[x] = new String(response);
		}
		return result;
	}
}
