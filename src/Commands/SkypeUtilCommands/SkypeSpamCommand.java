package Commands.SkypeUtilCommands;

import Commands.SkypeChatCommand;
import Commands.SkypeSubCommand;
import Utils.SkypeMessagingModes;
import com.skype.ChatMessage;

public class SkypeSpamCommand extends SkypeChatCommand{

		protected boolean enabled = false;

		public boolean isEnabled() {
			return enabled;
		}

		public void setEnabled( boolean enabled ) {
			for (SkypeSubCommand sub : subCommands) {
				sub.enabled = enabled;
			}

			this.enabled = enabled;
		}

	@Override
	public void commandExcecuted( ChatMessage message, String[] args, SkypeMessagingModes mode, ChatMessage.Type messageType ) throws Exception {
		int amount = Integer.parseInt(args[0]);
		boolean sep = Boolean.parseBoolean(args[1]);

		int tt = args.length;
		String t = "";

		for(int i = 2; i < tt; i++){
			t += args[i] + " ";
		}

		if(sep){
			for(int i = 0; i < amount; i++){
				message.getChat().send(t);
			}
		}else{
			String g = "";

			for(int i = 0; i < amount; i++){
				g += t;
			}

			message.getChat().send(g);
		}


	}

	@Override
	public boolean canExcecute( ChatMessage message, String[] args, SkypeMessagingModes mode, ChatMessage.Type messageType ) throws Exception {
		return messageType == ChatMessage.Type.SAID && args.length >= 2;
	}

	@Override
	public String commandPrefix() {
		return "spam";
	}

	@Override
	public String getUsage() {
		return "Spam <amount> <true/false(combine or seperate messages> <message>";
	}
}
