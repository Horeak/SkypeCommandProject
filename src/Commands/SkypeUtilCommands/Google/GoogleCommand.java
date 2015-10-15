package Commands.SkypeUtilCommands.Google;

import Commands.SkypeChatCommand;
import Commands.SkypeSubCommand;
import Utils.CommandFilter;
import Utils.SkypeMessagingModes;
import com.google.gson.Gson;
import com.skype.ChatMessage;

import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLEncoder;

@Deprecated
public class GoogleCommand extends SkypeChatCommand {

	protected boolean enabled = false;

	public boolean isEnabled(){
		return enabled;
	}

	public void setEnabled(boolean enabled){
		for(SkypeSubCommand sub : subCommands){
			sub.enabled = enabled;
		}

		this.enabled = enabled;
	}

	@Override
	public void commandExcecuted(ChatMessage message, String clearString, SkypeMessagingModes mode, ChatMessage.Type messageType) throws Exception {
		String google = "http://ajax.googleapis.com/ajax/services/search/web?v=1.0&q=";
		String search = clearString;
		String charset = "UTF-8";

		URL url = new URL(google + URLEncoder.encode(search, charset));
		Reader reader = new InputStreamReader(url.openStream(), charset);
		GoogleResults results = new Gson().fromJson(reader, GoogleResults.class);

//		// Show title and URL of 1st result.
//		System.out.println(results.getResponseData().getResults().get(0).getTitle());
//		System.out.println(results.getResponseData().getResults().get(0).getUrl());

		if(results.getResponseData().getResults().size() > 0){
			message.getChat().send("[GoogleCommand]First results for search \"" + clearString  + "\" was:" +
					" \n 1) " + results.getResponseData().getResults().get(0).getUrl() +
					" \n 2) " + results.getResponseData().getResults().get(1).getUrl() +
					" \n 3) " + results.getResponseData().getResults().get(2).getUrl());

		}else{
			message.getChat().send("[GoogleCommand]Nothing was found with search word " + clearString);
		}
	}

	@Override
	public boolean canExcecute(ChatMessage message, String clearString, SkypeMessagingModes mode, ChatMessage.Type messageType) throws Exception {
		return messageType == ChatMessage.Type.SAID;
	}

	@Override
	public String commandPrefix() {
		return "Google";
	}

	@Override
	public CommandFilter CommandMessageFilter() {
		return CommandFilter.StartWith;
	}
}
