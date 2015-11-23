package Commands.SkypeUtilCommands;

import Commands.SkypeChatCommand;
import Utils.ChatUtils;
import Utils.SkypeMessagingModes;
import com.skype.ChatMessage;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class MathCommand extends SkypeChatCommand {
	@Override
	public void commandExcecuted( ChatMessage message, String[] args, SkypeMessagingModes mode, ChatMessage.Type messageType ) throws Exception {
		String input = "";
		for (String t : args) input = input + t;

		ScriptEngineManager mgr = new ScriptEngineManager();
		ScriptEngine engine = mgr.getEngineByName("JavaScript");

		String foo = input.replace("x", "*");
		ChatUtils.sendMessage(message.getChat(), "[MathAnswer] " + input + " = " + engine.eval(foo).toString());
	}

	@Override
	public boolean canExcecute( ChatMessage message, String[] args, SkypeMessagingModes mode, ChatMessage.Type messageType ) throws Exception {
		return messageType == ChatMessage.Type.SAID;
	}

	@Override
	public String commandPrefix() {
		return "Math";
	}

	@Override
	public String getUsage() {
		return "Math <question>";
	}

}
