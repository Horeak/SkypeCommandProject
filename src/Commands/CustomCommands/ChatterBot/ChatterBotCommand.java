package Commands.CustomCommands.ChatterBot;
/*
* Project: Random Java Creations
* Package: Commands.CustomCommands
* Created: 27.06.2015
*/

import Commands.SkypeChatCommand;
import Utils.CommandFilter;
import Utils.SkypeMessagingModes;
import com.google.code.chatterbotapi.ChatterBotSession;
import com.skype.ChatMessage;
import com.skype.ChatMessage.Type;

//Standard functions for the bot commands
public abstract class ChatterBotCommand extends SkypeChatCommand {

	public ChatterBotCommand()
	{
		subCommands.add(new SubCommand_clearBot(this));
		subCommands.add(new SubCommand_botThinkSelf(this));
	}

	public abstract String getBotName();
	public abstract ChatterBotSession getBotSession();
	public abstract ChatterBotSession getNewSession();

	public abstract void setSession(ChatterBotSession session);

	@Override
	public void commandExcecuted(ChatMessage message, String clearString, SkypeMessagingModes mode, ChatMessage.Type messageType) throws Exception {

		System.out.println(" - " + getBotName() + " is thinking about: " + clearString);
		String reply = getBotSession().think(clearString);

		if (reply != null && !reply.isEmpty()) {
			if(message.getChat()!= null) {

				System.out.println(" - " +  getBotName() + " replied: " + reply);
				message.getChat().send("[" + getBotName()+ " Says] " + reply);

			}else{
				System.out.println("ERROR: Null chat");
			}
		}
	}

	@Override
	public boolean canExcecute(ChatMessage message, String clearString, SkypeMessagingModes mode, ChatMessage.Type messageType) throws Exception {
		return getBotSession() != null && messageType == Type.SAID;
	}

	@Override
	public String commandPrefix() {
		return getBotName();
	}

	@Override
	public CommandFilter CommandMessageFilter() {
		return CommandFilter.StartWith;
	}
}

