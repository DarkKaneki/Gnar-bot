package xyz.gnarbot.gnar.textadventure.events;

import xyz.gnarbot.gnar.textadventure.Event;
import xyz.gnarbot.gnar.textadventure.TextAdventure;
import xyz.gnarbot.gnar.utils.Note;

/**
 * Created by zacha on 11/1/2016.
 */
public class FirstBagEvent extends Event{

	public FirstBagEvent() {
		super("FirstBag", "First Bag", "Get your first bag");
	}

	@Override
	public String getEventID() {
		return super.getEventID();
	}

	@Override
	public String getEventName() {
		return "First Bag";
	}

	@Override
	public String getEventDescription() {
		return "You found a bag on the floor!";
	}

	@Override
	public boolean hasCompletedEvent() {
		return super.hasCompletedEvent();
	}

	@Override
	public void runEvent(TextAdventure adventure, Note n) {
		super.sendMessage(n, ":bulb: While walking, you find a bag lying on the floor.\n   :warning: What would you like to do?\n  ➜ `Pick it up`\n  ➜ `Leave it`\n  ➜ `Examine it`\n \n :bulb: `Use the _adventure command to select a response! Example: _adventure Pick it up`");
	}

	@Override
	public void parseResponse(TextAdventure adventure, Note n, String response) {
		System.out.println("Got event response");
		if (!(response.equalsIgnoreCase("pick it up") || response.equalsIgnoreCase("leave it") || response.equalsIgnoreCase("examine it"))){
			super.sendMessage(n, "I'm unsure of how to react to that response. Please try again!~");
			return;
		}else{
			if (response.equalsIgnoreCase("leave it")){
				super.sendMessage(n, "    **A voice from the distance calls out to you and says...**\n    *I wouldn't leave it if I were you. It's probably important.*");
			}else if (response.equalsIgnoreCase("pick it up")){
				super.sendMessage(n, "        :asterisk: You pick up the bag.\n         \"*Wow! I can fit quite a bit in here!*\" You think to yourself.\n:bulb: `Use _adventure inventory to view your inventory!`");
				adventure.getResponseFromEvent(this, "completed");
				super.setCompletedEvent(true);
				adventure.logAction("You picked up an item: Backpack!");
			}else{
				super.sendMessage(n, "        :asterisk: You examine the bag from afar, worried that it might attack you. Upon further inspection, it just seems to be a regular old backpack.");
				adventure.logAction("You examined a bag you found on the floor.");
			}
		}
	}
}
