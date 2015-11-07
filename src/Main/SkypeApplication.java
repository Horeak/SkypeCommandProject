package Main;/*
* Project: Random Java Creations
* Package: PACKAGE_NAME
* Created: 22.06.2015
*/

import Utils.ChatUtils;
import com.skype.ChatMessage;
import com.skype.ChatMessageAdapter;
import com.skype.Skype;
import com.skype.SkypeException;

import javax.swing.*;

public class SkypeApplication extends ChatMessageAdapter {

	public SkypeApplication() {
		System.out.println("Program has started successfully.");
	}

	public static void main( String[] args ) throws Exception {
		SkypeLogWindow.createAndShowGui();

		System.out.println("Started skype-java integration program with user: " + Skype.getProfile().getFullName());

		Skype.setDebug(false);
		Skype.setDaemon(false);

		SkypeApplication ap = new SkypeApplication();
		Skype.addChatMessageListener(ap);
	}

	public void chatMessageReceived( ChatMessage received ) throws SkypeException {
		System.out.println("Message received: " + received.getContent());
		SwingUtilities.invokeLater(() -> {
			try {
				message(received, 0, received.getType());
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

	}

	public void chatMessageSent( ChatMessage sentChatMessage ) throws SkypeException {
		System.out.println("Message sent: " + sentChatMessage.getContent());
		SwingUtilities.invokeLater(() -> {
			try {
				message(sentChatMessage, 0, sentChatMessage.getType());
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}


	public void message( ChatMessage message, int type, ChatMessage.Type messageType ) throws Exception {
		if (!ChatUtils.hasChatBeenValidated(message.getChat())) {
			System.out.println("Chat has not received commands yet!");
			ChatUtils.addCommandsForChat(message.getChat());
		}

		if (ChatUtils.hasChatBeenValidated(message.getChat())) ChatUtils.excecuteCommand(message, type, messageType);
	}
}
