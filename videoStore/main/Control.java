package main;

import java.util.Iterator;
import command.Command;
import data.Data;
import data.Inventory;
import data.Record;
import data.Video;
import ui.UI;
import ui.UIError;
import ui.UIForm;
import ui.UIFormBuilder;
import ui.UIFormTest;
import ui.UIMenu;
import ui.UIMenuBuilder;

class Control {
	private static final int EXITED = 0;
	private static final int EXIT = 1;
	private static final int START = 2;
	private static final int NUMSTATES = 10;
	private UIMenu[] menus;
	private int state;

	private UIForm getVideoForm;
	private UIFormTest numberTest;
	private UIFormTest stringTest;

	private Inventory inventory;
	private UI ui;

	Control(Inventory inventory, UI ui) {
		this.inventory = inventory;
		this.ui = ui;

		menus = new UIMenu[NUMSTATES];
		state = START;
		addSTART(START);
		addEXIT(EXIT);

		UIFormTest yearTest = input -> {
			try {
				int i = Integer.parseInt(input);
				return i > 1800 && i < 5000;
			} catch (NumberFormatException e) {
				return false;
			}
		};
		numberTest = input -> {
			try {
				Integer.parseInt(input);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}
		};
		stringTest = input -> ! "".equals(input.trim());

		UIFormBuilder f = new UIFormBuilder();
		f.add("Title", stringTest);
		f.add("Year", yearTest);
		f.add("Director", stringTest);
		getVideoForm = f.toUIForm("Enter Video");
	}

	void run() {
		try {
			while (state != EXITED) {
				ui.processMenu(menus[state]);
			}
		} catch (UIError e) {
			ui.displayError("UI closed");
		}
	}

	private void addSTART(int stateNum) {
		UIMenuBuilder m = new UIMenuBuilder();

		m.add("Default",
				() -> ui.displayError("doh!"));
		m.add("Add/Remove copies of a video",
				() -> {
					String[] result1 = ui.processForm(getVideoForm);
					Video v = Data.newVideo(result1[0], Integer.parseInt(result1[1]), result1[2]);

					UIFormBuilder f = new UIFormBuilder();
					f.add("Number of copies to add/remove", numberTest);
					String[] result2 = ui.processForm(f.toUIForm(""));

					Command c = Data.newAddCmd(inventory, v, Integer.parseInt(result2[0]));
					if (! c.run()) {
						ui.displayError("Command failed");
					}
				});
		m.add("Check in a video",
				() -> {
					// TODO
				});
		m.add("Check out a video",
				() -> {
					// TODO
				});
		m.add("Print the inventory",
				() -> ui.displayMessage(inventory.toString()));
		m.add("Clear the inventory",
				() -> {
					if (!Data.newClearCmd(inventory).run()) {
						ui.displayError("Command failed");
					}
				});
		m.add("Undo",
				() -> {
					if (!inventory.undo()) {
						ui.displayError("Command failed");
					}
				});
		m.add("Redo",
				() -> {
					if (!inventory.redo()) {
						ui.displayError("Command failed");
					}
				});
		m.add("Print top ten all time rentals in order",
				() -> {
					// TODO
				});

		m.add("Exit",
				() -> state = EXIT);

		m.add("Initialize with bogus contents",
				() -> {
					Data.newAddCmd(inventory, Data.newVideo("a", 2000, "m"), 1).run();
					Data.newAddCmd(inventory, Data.newVideo("b", 2000, "m"), 2).run();
					Data.newAddCmd(inventory, Data.newVideo("c", 2000, "m"), 3).run();
					Data.newAddCmd(inventory, Data.newVideo("d", 2000, "m"), 4).run();
					Data.newAddCmd(inventory, Data.newVideo("e", 2000, "m"), 5).run();
					Data.newAddCmd(inventory, Data.newVideo("f", 2000, "m"), 6).run();
					Data.newAddCmd(inventory, Data.newVideo("g", 2000, "m"), 7).run();
					Data.newAddCmd(inventory, Data.newVideo("h", 2000, "m"), 8).run();
					Data.newAddCmd(inventory, Data.newVideo("i", 2000, "m"), 9).run();
					Data.newAddCmd(inventory, Data.newVideo("j", 2000, "m"), 10).run();
					Data.newAddCmd(inventory, Data.newVideo("k", 2000, "m"), 11).run();
					Data.newAddCmd(inventory, Data.newVideo("l", 2000, "m"), 12).run();
					Data.newAddCmd(inventory, Data.newVideo("m", 2000, "m"), 13).run();
					Data.newAddCmd(inventory, Data.newVideo("n", 2000, "m"), 14).run();
					Data.newAddCmd(inventory, Data.newVideo("o", 2000, "m"), 15).run();
					Data.newAddCmd(inventory, Data.newVideo("p", 2000, "m"), 16).run();
					Data.newAddCmd(inventory, Data.newVideo("q", 2000, "m"), 17).run();
					Data.newAddCmd(inventory, Data.newVideo("r", 2000, "m"), 18).run();
					Data.newAddCmd(inventory, Data.newVideo("s", 2000, "m"), 19).run();
					Data.newAddCmd(inventory, Data.newVideo("t", 2000, "m"), 20).run();
					Data.newAddCmd(inventory, Data.newVideo("u", 2000, "m"), 21).run();
					Data.newAddCmd(inventory, Data.newVideo("v", 2000, "m"), 22).run();
					Data.newAddCmd(inventory, Data.newVideo("w", 2000, "m"), 23).run();
					Data.newAddCmd(inventory, Data.newVideo("x", 2000, "m"), 24).run();
					Data.newAddCmd(inventory, Data.newVideo("y", 2000, "m"), 25).run();
					Data.newAddCmd(inventory, Data.newVideo("z", 2000, "m"), 26).run();
				});

		menus[stateNum] = m.toUIMenu("Bob's Video");
	}
	private void addEXIT(int stateNum) {
		UIMenuBuilder m = new UIMenuBuilder();

		m.add("Default", () -> {});
		m.add("Yes",
				() -> state = EXITED);
		m.add("No",
				() -> state = START);

		menus[stateNum] = m.toUIMenu("Are you sure you want to exit?");
	}
}
