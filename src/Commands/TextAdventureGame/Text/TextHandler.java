package Commands.TextAdventureGame.Text;

import Commands.TextAdventureGame.Event;
import Commands.TextAdventureGame.Item;
import Commands.TextAdventureGame.Player;
import com.skype.Chat;

public class TextHandler {

	public static TextAction[] textActions = new TextAction[]{
			new TextAction(){
				@Override
				public void performAction( Player player, String text ) throws Exception{
					player.chat.send("[TA]" + player.name + " has " + player.health + "/" + player.maxHealth + " health");
				}

				@Override
				public String getName() {
					return "Health";
				}

				public boolean canOverrideEvent(){return true;}
			},

			new TextAction(){
				@Override
				public void performAction( Player player, String text ) throws Exception{
					if(!text.equalsIgnoreCase("inventory")){
						String g = text.replace("inventory ", "");
						int t = 0;

						for(Item it : player.items){
							if(it.name.equalsIgnoreCase(g)){
								t += 1;
							}
						}

						player.print("Found " + t + " \"" + g + "\" in inventory.");
					}else{
						player.print(player.name + " has " + player.items.size() + " items in their inventory.");
					}
				}

				public boolean canPerform(Player player, String text) throws Exception{return text.toLowerCase().startsWith(getName().toLowerCase());}

				@Override
				public String getName() {
					return "inventory";
				}

				public boolean canOverrideEvent(){return true;}
			},

	};

	public static void handleText(Player player, String text, Chat chat) throws Exception{
		if(player.event != null){
			if(player.event.input(player, text, chat)){
				return;
			}
		}

		for(TextAction te : textActions){
			if(player.event == null || te.canOverrideEvent())
			if(te.canPerform(player, text)){
				te.performAction(player, text);
				return;
			}
		}

		chat.send("[TA]Invalid input!");

		if(player.event != null)
		chat.send("[TA]" + player.name + " is in " + player.world.worldType.desc + ". What do you do? (" + Event.getActions(player.world.worldType.actions) + ")");
	}

}
