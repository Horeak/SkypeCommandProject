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

	public static SkypeChatCommand[] commands = new SkypeChatCommand[]{ new CommandCleverBot(), new CommandPandoraBots(), new MathCommand(), new GoogleCommand() };

	//TODO Fix program using wrong chat when sending from phone. Wrong chat instace? Related to time away? If user that is creator of the chat changes? Loop through all chats and check them?
	private static HashMap<Chat, SkypeChatCommand[]> ChatCommandStore = new HashMap<>();

	public static void addCommandsForChat( Chat chat ) throws Exception {
		ChatCommandStore.put(chat, commands.clone());
		System.out.println("Commands has been added for: \"" + chat.getWindowTitle() + "\"");

		SkypeLogWindow.buttonArea.addButton("Chat: " + chat.getWindowTitle(), SkypeLogWindow.buttonArea.getActionForChatButton(chat));
	}

	public static HashMap<String, SkypeChatCommand> getCommandsForChat( Chat chat ) {

		for (Map.Entry<Chat, SkypeChatCommand[]> ent : ChatCommandStore.entrySet()) {
			if (areChatsEqual(ent.getKey(), chat)) {
				HashMap<String, SkypeChatCommand> map = new HashMap<>();

				for (SkypeChatCommand command : ent.getValue()) {
					map.put(command.commandPrefix().toLowerCase(), command);
				}

				return map;
			}
		}
		return null;
	}

	public static Chat[] getChats() {
		Chat[] chats = new Chat[ ChatCommandStore.size() ];

		int i = 0;
		for (Map.Entry<Chat, SkypeChatCommand[]> ent : ChatCommandStore.entrySet()) {
			chats[ i ] = ent.getKey();

			i += 1;
		}

		return chats;
	}

	public static boolean areChatsEqual( Chat chat1, Chat chat2 ) {
		//TODO Find a good min value where it cant randomly be 2 similar chats
		int finalValue = 0, minEqualValue = 3;

		try {

			if (chat1.getWindowTitle().equalsIgnoreCase(chat2.getWindowTitle())) finalValue += 1;
			if (chat1.getAdder() != null && chat2.getAdder() != null && chat1.getAdder().equals(chat2.getAdder()))
				finalValue += 1;
			if (Arrays.equals(chat1.getAllMembers(), chat2.getAllMembers())) finalValue += 1;
			if (chat1.getId().equals(chat2.getId())) finalValue += 1;
			if (Arrays.equals(chat1.getAllChatMessages(), chat2.getAllChatMessages())) finalValue += 1;
			if (Arrays.equals(chat1.getAllPosters(), chat2.getAllPosters())) finalValue += 1;
			if (chat1.getStatus().equals(chat2.getStatus())) finalValue += 1;
			if (Arrays.equals(chat1.getRecentChatMessages(), chat2.getRecentChatMessages())) finalValue += 1;


		} catch (Exception e) {
			e.printStackTrace();
		}

		return finalValue >= minEqualValue;
	}

	public static boolean hasChatBeenValidated( Chat chat ) {
		return getCommandsForChat(chat) != null;
	}


	public static SkypeChatCommand getCommand( String text, Chat chat ) {
		String[] o = text.split(" ");
		return getCommandsForChat(chat).get(o[ 0 ].toLowerCase());
	}

	public static SkypeSubCommand getSubCommandFromCommand( String text, SkypeChatCommand command ) {
		String[] o = text.split(" ");

		if (o.length > 1) {
			for (SkypeSubCommand subCommand : command.subCommands) {
				if (subCommand.commandPrefix().equalsIgnoreCase(o[ 1 ])) {
					return subCommand;

				}
			}
		}

		return null;
	}

	public static void excecuteCommand( ChatMessage message, int type, ChatMessage.Type messageType ) throws Exception {
		SkypeMessagingModes mode = type == 0 ? SkypeMessagingModes.RECEIVED : SkypeMessagingModes.SENT;
		String text = message.getContent();

		SkypeChatCommand command = getCommand(text, message.getChat());

		if (command != null) {
			SkypeSubCommand subCommand = getSubCommandFromCommand(text, command);

			if (subCommand != null) {
				String[] j = getArgsFromText(text, 2);

				if (subCommand.enabled && subCommand.canExcecute(message, j, mode, messageType, command)) {
					subCommand.commandExcecuted(message, j, mode, messageType, command);
				} else if (!subCommand.enabled) {
					message.getChat().send(MessageFormatter.getStandardPrefix() + "That subCommand has been disabled for this chat!");
				} else {
					message.getChat().send(MessageFormatter.getStandardPrefix() + "Could not execute subcommand: " + subCommand.commandPrefix());
				}

			} else {
				String[] j = getArgsFromText(text, 1);

				if (command.isEnabled() && command.canExcecute(message, j, mode, messageType)) {
					command.commandExcecuted(message, j, mode, messageType);
				} else if (!command.isEnabled()) {
					message.getChat().send(MessageFormatter.getStandardPrefix() + "That command has been disabled for this chat!");
				} else {
					message.getChat().send(MessageFormatter.getStandardPrefix() + "Could not execute command: " + message.getContent());
				}
			}
		}
	}


	private static String[] getArgsFromText( String text, int pos ) {
		String[] k = text.split(" ");
		String[] j = new String[]{};

		if (k.length > pos) {
			j = new String[ k.length - pos ];
			System.arraycopy(k, pos, j, 0, k.length - pos);
		}

		return j;
	}
}


