package Commands.CustomCommands.PandoraBots;

import Commands.SkypeChatCommand;
import Commands.SkypeSubCommand;
import Utils.SkypeMessagingModes;
import com.google.code.chatterbotapi.ChatterBotType;
import com.skype.ChatMessage;

/**
 * Created by hhg on 13.10.2015.
 */
class SubCommand_changeBotID extends SkypeSubCommand {

	@Override
	public void commandExcecuted( ChatMessage message, String[] args, SkypeMessagingModes mode, ChatMessage.Type messageType, SkypeChatCommand command ) throws Exception {
		CommandPandoraBots bots = null;

		if (command instanceof CommandPandoraBots) {
			bots = (CommandPandoraBots) command;
		}

		if (bots != null) {
			if (args[ 0 ].equalsIgnoreCase("reset")) {
				bots.pandoraBots = bots.factory.create(ChatterBotType.PANDORABOTS, CommandPandoraBots.DEFAULT_BOT_ID);
				bots.pandoraBotsSession = bots.pandoraBots.createSession();

				bots.botID = CommandPandoraBots.DEFAULT_BOT_ID;

				message.getChat().send("[PandoraBots] Bot id has been reset.");
				return;
			}

			if (args[ 0 ] != null && args[ 0 ].length() == 16) {
				bots.pandoraBots = bots.factory.create(ChatterBotType.PANDORABOTS, args[ 0 ]);
				bots.pandoraBotsSession = bots.pandoraBots.createSession();

				bots.botID = args[ 0 ];

				message.getChat().send("[PandoraBots] Changed bot id to: " + args[ 0 ]);
			} else {
				message.getChat().send("[PandoraBots] Bot id is invalid! Check from the PandoraBots website");
			}
		}

	}

	@Override
	public boolean canExcecute( ChatMessage message, String[] args, SkypeMessagingModes mode, ChatMessage.Type messageType, SkypeChatCommand command ) throws Exception {
		return messageType == ChatMessage.Type.SAID;
	}

	@Override
	public String commandPrefix() {
		return "-changeBotID";
	}

}
