package Commands.SkypeUtilCommands;

import Commands.SkypeChatCommand;
import Utils.CommandFilter;
import Utils.SkypeMessagingModes;
import com.skype.ChatMessage;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class MathCommand extends SkypeChatCommand {
	@Override
	public void commandExcecuted(ChatMessage message, String clearString, SkypeMessagingModes mode, ChatMessage.Type messageType) throws Exception {
		ScriptEngineManager mgr = new ScriptEngineManager();
		ScriptEngine engine = mgr.getEngineByName("JavaScript");

		String foo = clearString.replace("x", "*");

		message.getChat().send("[MathAnswer] " + clearString + " = " + engine.eval(foo).toString());

	}

	@Override
	public boolean canExcecute(ChatMessage message, String clearString, SkypeMessagingModes mode, ChatMessage.Type messageType) throws Exception {
		return messageType == ChatMessage.Type.SAID;
	}

	@Override
	public String commandPrefix() {
		return "Math";
	}

	@Override
	public CommandFilter CommandMessageFilter() {
		return CommandFilter.StartWith;
	}
}
