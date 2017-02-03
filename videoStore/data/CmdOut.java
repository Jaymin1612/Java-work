package data;

import command.Command;

/**
 * Implementation of command to check out a video.
 * @see Data
 */
final class CmdOut implements Command {
	private InventorySet inventory;
	private Record oldvalue;
	private Video video;
	CmdOut(InventorySet inventory, Video video) {
		this.inventory = inventory;
		this.video = video;
	}
	public boolean run() {
		if (oldvalue != null) {
			return false;
		}
		try {
			oldvalue = inventory.checkOut(video);
			inventory.getHistory().add(this);
			return true;
		} catch (ClassCastException e) {
			return false;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}
	public void undo() {
		inventory.replaceEntry(video, oldvalue);
	}
	public void redo() {
		oldvalue = inventory.checkOut(video);
	}
}
