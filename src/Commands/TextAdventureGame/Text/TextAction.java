package Commands.TextAdventureGame.Text;

import Commands.TextAdventureGame.Player;

public abstract class TextAction {
	public abstract void performAction( Player player, String text) throws Exception;
	public boolean canPerform(Player player, String text) throws Exception{return text.equalsIgnoreCase(getName());}

	public abstract String getName();

	public boolean canOverrideEvent(){return false;}
}
