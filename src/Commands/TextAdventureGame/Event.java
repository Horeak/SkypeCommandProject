package Commands.TextAdventureGame;

import Commands.TextAdventureGame.Text.TextAction;
import Commands.TextAdventureGame.Text.TextHandler;
import com.skype.Chat;

public class Event {
	public TextAction[] actions;
	public String message;

		public Event(TextAction[] actions, String message, Chat chat) throws Exception{
			this.actions = actions;
			this.message = message;
			chat.send("[TA]" + message);
		}


		public boolean input(Player player, String text, Chat chat) throws Exception{
			for(TextAction te : actions){
				if(te.canPerform(player, text)){
					te.performAction(player, text);
					return true;
				}
			}
			player.event = this;
			return false;
		}

	public static String getActions(TextAction[] actions){
		String inputs = "";

		for(TextAction te : actions){
			if(inputs.length() > 0){
				inputs += "/";
			}

			inputs += te.getName();
		}

		return inputs;
	}
}
