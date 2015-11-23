package Commands.SkypeUtilCommands;

import Commands.SkypeChatCommand;
import Utils.ChatUtils;
import Utils.SkypeMessagingModes;
import com.skype.ChatMessage;

//This is not used as a command but only a toggleable feature
public class InvalidCommand extends SkypeChatCommand {
	@Override
	public void commandExcecuted(ChatMessage message, String[] args, SkypeMessagingModes mode, ChatMessage.Type messageType) throws Exception {
		String t = "Invalid command! type \"ListCommands\" to see all active commands!";

		if (message.getChat().getAllMembers().length <= 2 && messageType == ChatMessage.Type.SAID && !message.getContent().equalsIgnoreCase(t)) {
			ChatUtils.sendMessage(message.getChat(), t);
		}
	}

	@Override
	public boolean canExcecute(ChatMessage message, String[] args, SkypeMessagingModes mode, ChatMessage.Type messageType) throws Exception {
		return messageType == ChatMessage.Type.SAID;
	}

	@Override
	public String commandPrefix() {
		return "InvalidCommand";
	}

	@Override
	public String getUsage() {
		return null;
	}

	@Override
	public boolean listCommand() {
		return false;
	}
}
