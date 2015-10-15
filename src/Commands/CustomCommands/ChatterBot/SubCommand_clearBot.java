package Commands.CustomCommands.ChatterBot;

import Commands.SkypeChatCommand;
import Commands.SkypeSubCommand;
import Utils.SkypeMessagingModes;
import com.skype.ChatMessage;

/**
 * Created by hhg on 13.10.2015.
 */
class SubCommand_clearBot extends SkypeSubCommand {

	ChatterBotCommand command;
	public SubCommand_clearBot(ChatterBotCommand command){
		this.command = command;
	}

	@Override
	public void commandExcecuted(ChatMessage message, String clearString, SkypeMessagingModes mode, ChatMessage.Type messageType, SkypeChatCommand commandc) throws Exception {
		command.setSession(command.getNewSession());
		message.getChat().send("["+command.getBotName()+"] Cleared session.");
	}

	@Override
	public boolean canExcecute(ChatMessage message, String clearString, SkypeMessagingModes mode, ChatMessage.Type messageType, SkypeChatCommand commandc) throws Exception {
		return messageType == ChatMessage.Type.SAID;
	}

	@Override
	public String commandPrefix() {
		return "-clear";
	}

}
