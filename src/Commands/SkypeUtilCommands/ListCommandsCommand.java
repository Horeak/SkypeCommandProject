package Commands.SkypeUtilCommands;

import Commands.SkypeChatCommand;
import Utils.ChatUtils;
import Utils.SkypeMessagingModes;
import com.skype.ChatMessage;

public class ListCommandsCommand extends SkypeChatCommand {
	@Override
	public void commandExcecuted(ChatMessage message, String[] args, SkypeMessagingModes mode, ChatMessage.Type messageType) throws Exception {

		StringBuilder builder = new StringBuilder();
		builder.append("Available commands for this chat: \n");

		for (SkypeChatCommand command : ChatUtils.getCommandsForChatArr(message.getChat())) {
			if (command.listCommand() && command.isEnabled()) {
				builder.append(command.commandPrefix() + " - Useage: " + command.getUsage() + "\n");
			}
		}

		ChatUtils.sendMessage(message.getChat(), builder.toString());
	}

	@Override
	public boolean canExcecute(ChatMessage message, String[] args, SkypeMessagingModes mode, ChatMessage.Type messageType) throws Exception {
		return messageType == ChatMessage.Type.SAID;
	}

	@Override
	public String commandPrefix() {
		return "ListCommands";
	}

	@Override
	public String getUsage() {
		return "ListCommands";
	}

	public boolean listCommand() {
		return false;
	}
}
