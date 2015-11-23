package Commands.CustomCommands.Cleverbot;
/*
* Project: Random Java Creations
* Package: Commands
* Created: 26.06.2015
*/

import Commands.CustomCommands.ChatterBot.ChatterBotCommand;
import com.google.code.chatterbotapi.ChatterBot;
import com.google.code.chatterbotapi.ChatterBotFactory;
import com.google.code.chatterbotapi.ChatterBotSession;
import com.google.code.chatterbotapi.ChatterBotType;


public class CommandCleverBot extends ChatterBotCommand {

	public ChatterBotFactory factory = new ChatterBotFactory();

	public ChatterBot cleverBot = null;
	public ChatterBotSession cleverBotSession = null;

	public CommandCleverBot() {
		super();

		try {
			cleverBot = factory.create(ChatterBotType.CLEVERBOT);
			cleverBotSession = cleverBot.createSession();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getBotName() {
		return "CleverBot";
	}

	@Override
	public ChatterBotSession getBotSession() {
		return cleverBotSession;
	}

	@Override
	public ChatterBotSession getNewSession() {
		return cleverBot.createSession();
	}

	@Override
	public void setSession( ChatterBotSession session ) {
		cleverBotSession = session;
	}

	@Override
	public String getUsage() {
		return "CleverBot <text/subcommand>";
	}
}