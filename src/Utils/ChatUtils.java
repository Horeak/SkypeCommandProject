package Utils;

import Commands.CustomCommands.Cleverbot.CommandCleverBot;
import Commands.CustomCommands.PandoraBots.CommandPandoraBots;
import Commands.SkypeChatCommand;
import Commands.SkypeSubCommand;
import Commands.SkypeUtilCommands.Google.GoogleCommand;
import Commands.SkypeUtilCommands.MathCommand;
import Main.SkypeLogWindow;
import com.skype.Chat;
import com.skype.ChatMessage;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ChatUtils {

	public static SkypeChatCommand[] commands = new SkypeChatCommand[]{
			new CommandCleverBot(), new CommandPandoraBots(),
			new MathCommand(),      new GoogleCommand()};

	//TODO Fix program using wrong chat when sending from phone. Wrong chat instace? Related to time away? If user that is creator of the chat changes? Loop through all chats and check them?
	private static HashMap<Chat, SkypeChatCommand[]> ChatCommandStore = new HashMap<>();

	public static void addCommandsForChat(Chat chat) throws Exception{
		ChatCommandStore.put(chat, commands.clone());
		System.out.println("Commands has been added for: \"" + chat.getWindowTitle() + "\"");

		SkypeLogWindow.buttonArea.addButton("Chat: " + chat.getWindowTitle(), SkypeLogWindow.buttonArea.getActionForChatButton(chat));
	}

	//TODO Make sure this works correctly
	public static SkypeChatCommand[] getCommandsForChat(Chat chat){

		for(Map.Entry<Chat, SkypeChatCommand[]> ent : ChatCommandStore.entrySet()){
			if(areChatsEqual(ent.getKey(), chat)){
				return ent.getValue();
			}
		}
		return null;
	}

	public static Chat[] getChats(){
		Chat[] chats = new Chat[ChatCommandStore.size()];

		int i = 0;
		for(Map.Entry<Chat, SkypeChatCommand[]> ent : ChatCommandStore.entrySet()){
			chats[i] = ent.getKey();

			i += 1;
		}

		return chats;
	}

	public static boolean areChatsEqual(Chat chat1, Chat chat2){
		//TODO Find a good min value where it cant randomly be 2 similar chats
		int finalValue = 0, minEqualValue = 2;

		try {

			if(chat1.getWindowTitle().equalsIgnoreCase(chat2.getWindowTitle())) finalValue += 1;
			if(chat1.getAdder().equals(chat2.getAdder())) finalValue += 1;
			if(Arrays.equals(chat1.getAllMembers(), chat2.getAllMembers())) finalValue += 1;
			if(chat1.getId().equals(chat2.getId())) finalValue += 1;
			if(Arrays.equals(chat1.getAllChatMessages(), chat2.getAllChatMessages())) finalValue += 1;
			if(Arrays.equals(chat1.getAllPosters(), chat2.getAllPosters())) finalValue += 1;
			if(chat1.getStatus().equals(chat2.getStatus())) finalValue += 1;
			if(Arrays.equals(chat1.getRecentChatMessages(), chat2.getRecentChatMessages())) finalValue += 1;


		}catch(Exception e){
			e.printStackTrace();
		}

		return finalValue >= minEqualValue;
	}
	public static boolean hasChatBeenValidated(Chat chat){
		return getCommandsForChat(chat) != null;
	}


	//TODO Optimize
	public static void excecuteCommand(ChatMessage message, int type, ChatMessage.Type messageType) throws Exception{

		SkypeMessagingModes mode = type == 0 ? SkypeMessagingModes.RECEIVED : SkypeMessagingModes.SENT;

		for(SkypeChatCommand command : getCommandsForChat(message.getChat())){
			if(command.CommandMessageFilter() == CommandFilter.StartWith && message.getContent().toLowerCase().startsWith(command.commandPrefix().toLowerCase())
					|| command.CommandMessageFilter() == CommandFilter.Contains && message.getContent().toLowerCase().contains(command.commandPrefix().toLowerCase())
					|| command.CommandMessageFilter() == CommandFilter.Equals && message.getContent().toLowerCase().equals(command.commandPrefix().toLowerCase())){

				String t = message.getContent().length() > command.commandPrefix().length() ? message.getContent().substring(command.commandPrefix().length() + 1, message.getContent().length()) : message.getContent().replace(command.commandPrefix(), "");

				if(!command.subCommands.isEmpty()){
					for(SkypeSubCommand subCommand : command.subCommands){
						String[] g = t.split(" ");

						if(subCommand.commandPrefix().toLowerCase().equals(g[0].toLowerCase())){
							String tt = null;

							if(t.length() > subCommand.commandPrefix().length() + 1){
								tt = t.substring(subCommand.commandPrefix().length() + 1, t.length());
							}

							if(subCommand.canExcecute(message, tt, mode, messageType, command) && subCommand.enabled) {
								subCommand.commandExcecuted(message, tt, mode, messageType, command);
								return;

							}else{
								message.getChat().send(MessageFormatter.getStandardPrefix() + "Could not execute subcommand: " + t);
							}

						}
					}

				}

				if(command.canExcecute(message, t, mode, messageType) && command.isEnabled()){
					command.commandExcecuted(message, t, mode, messageType);
					return;
				}else if(!command.isEnabled()){
					message.getChat().send(MessageFormatter.getStandardPrefix() + "That command has been disabled for this chat!");
				}else{
					message.getChat().send(MessageFormatter.getStandardPrefix() + "Could not execute command: " + message.getContent());
				}

			}
		}
	}
}
