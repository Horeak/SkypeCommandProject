package Commands.TextAdventureGame.World;

import Commands.TextAdventureGame.Text.TextAction;

public class WorldType {
	public String desc;
	public TextAction[] actions;

	public WorldType(String name, TextAction[] actions){
		this.desc = name;
		this.actions = actions;
	}

}
