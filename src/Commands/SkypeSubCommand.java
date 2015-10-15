package Commands;
/*
* Project: Random Java Creations
* Package: Commands
* Created: 26.06.2015
*/

import Utils.SkypeMessagingModes;
import com.skype.ChatMessage;

public abstract class SkypeSubCommand {

	public boolean enabled = true;

	public abstract void commandExcecuted(ChatMessage message, String clearString, SkypeMessagingModes mode, ChatMessage.Type messageType, SkypeChatCommand command) throws Exception;
	public abstract boolean canExcecute(ChatMessage message, String clearString, SkypeMessagingModes mode, ChatMessage.Type messageType, SkypeChatCommand command) throws Exception;

	public abstract String commandPrefix();
}
