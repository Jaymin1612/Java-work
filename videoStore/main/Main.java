package main;

import data.Data;
import ui.UI;

class Main {
	public static void main(String[] args) {
		UI ui = null;

		if (args.length > 0) {
			if ("GUI".equalsIgnoreCase(args[0])) {
				ui = new ui.PopupUI();
			} else if ("TEXT".equalsIgnoreCase(args[0])) {
				ui = new ui.TextUI();
			} else {
				System.out.println("Argument must be GUI or TEXT");
				System.exit(1);
			}
		} else {
			if (Math.random() <= 0.5) {
				ui = new ui.TextUI();
			} else {
				ui = new ui.PopupUI();
			}
		}
		Control control = new Control(Data.newInventory(), ui);
		control.run();
	}
}
