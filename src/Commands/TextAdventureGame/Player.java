package Commands.TextAdventureGame;

import Commands.TextAdventureGame.Text.TextAction;
import Commands.TextAdventureGame.World.World;
import com.skype.Chat;

import java.util.ArrayList;

public class Player {

	public Event event;
	public World world;
	public Chat chat;
	public String name;
	public double health;
	public static double maxHealth = 20.0;

	public ArrayList<Item> items = new ArrayList<>();


	public Player(String name, Chat chat) throws Exception{
		this.name = name;
		this.chat = chat;
		this.health = maxHealth;

		this.world = new World();
		event = new Event(world.worldType.actions, name + " spawned at " + world.desc + ". What do you do? (" + Event.getActions(world.worldType.actions) + ")", chat);
	}

	public void kill(String text) throws Exception{
		health = 0;
		chat.send("[TA]" + text);
	}

	public void print(String text) throws  Exception{
		chat.send("[TA]" + text);
	}

}
