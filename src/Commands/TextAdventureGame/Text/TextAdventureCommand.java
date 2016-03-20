package Commands.TextAdventureGame.Text;

import Commands.SkypeChatCommand;
import Commands.TextAdventureGame.Player;
import Utils.SkypeMessagingModes;
import com.skype.Chat;
import com.skype.ChatMessage;

import java.util.HashMap;
import java.util.Random;

public class TextAdventureCommand extends SkypeChatCommand {
	public static Random rand = new Random();
	public HashMap<Chat, HashMap<String, Player>> chatHashMapHashMap = new HashMap<>();

	public TextAdventureCommand() {
//		subCommands.add(new SubCommand_clearBot(this));
//		subCommands.add(new SubCommand_botThinkSelf(this));
	}

	@Override
	public void commandExcecuted( ChatMessage message, String[] args, SkypeMessagingModes mode, ChatMessage.Type messageType ) throws Exception {
		String input = "";
		for (String t : args) input += (input.length() > 0 ? " " : "") + t;

		if(chatHashMapHashMap.get(message.getChat()) == null){
			chatHashMapHashMap.put(message.getChat(), new HashMap<String, Player>());
		}

		if(!chatHashMapHashMap.get(message.getChat()).containsKey(message.getSenderDisplayName())){
			if(input.equalsIgnoreCase("start")) {
				chatHashMapHashMap.get(message.getChat()).put(message.getSenderDisplayName(), new Player(message.getSenderDisplayName(), message.getChat()));
				return;
			}else{
				message.getChat().send("[TA]" + message.getSenderDisplayName() + " has not started yet! Type \"TA start\" to start!");
			}
		}
		if(chatHashMapHashMap.get(message.getChat()).get(message.getSenderDisplayName())!= null) {
			if (input.equalsIgnoreCase("restart")) {
				chatHashMapHashMap.get(message.getChat()).put(message.getSenderDisplayName(), new Player(message.getSenderDisplayName(), message.getChat()));
				return;
			}
		}

		if(chatHashMapHashMap.get(message.getChat()).get(message.getSenderDisplayName())!= null){
			if(chatHashMapHashMap.get(message.getChat()).get(message.getSenderDisplayName()).health <= 0){
				message.getChat().send("[TA]" + message.getSenderDisplayName() + " is dead! type \"TA restart\" to restart!");
			}else{
				TextHandler.handleText(chatHashMapHashMap.get(message.getChat()).get(message.getSenderDisplayName()), input, message.getChat());
			}
		}


	}

	@Override
	public boolean canExcecute( ChatMessage message, String[] args, SkypeMessagingModes mode, ChatMessage.Type messageType ) throws Exception {
		return messageType == ChatMessage.Type.SAID;
	}

	@Override
	public String commandPrefix() {
		return "TA";
	}

	@Override
	public String getUsage() {
		return "TA <input>";
	}

}
