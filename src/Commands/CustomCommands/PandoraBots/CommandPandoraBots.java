package Commands.CustomCommands.PandoraBots;
/*
* Project: Random Java Creations
* Package: Commands
* Created: 26.06.2015
*/

import Commands.CustomCommands.ChatterBot.ChatterBotCommand;
import Misc.WrapLayout;
import com.google.code.chatterbotapi.ChatterBot;
import com.google.code.chatterbotapi.ChatterBotFactory;
import com.google.code.chatterbotapi.ChatterBotSession;
import com.google.code.chatterbotapi.ChatterBotType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CommandPandoraBots extends ChatterBotCommand {

	public static String DEFAULT_BOT_ID = "b0dafd24ee35a477";
	public ChatterBotFactory factory = new ChatterBotFactory();
	public ChatterBot pandoraBots = null;
	public ChatterBotSession pandoraBotsSession = null;
	public String botID = DEFAULT_BOT_ID;

	public CommandPandoraBots() {
		super();

		subCommands.add(new SubCommand_changeBotID());

		try {

			pandoraBots = factory.create(ChatterBotType.PANDORABOTS, DEFAULT_BOT_ID);
			pandoraBotsSession = pandoraBots.createSession();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public ActionListener nonStandardSetting() {
		return new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent e ) {
				JFrame frame = new JFrame(commandPrefix());
				JButton button = new JButton("Enabled: " + isEnabled());

				frame.setPreferredSize(new Dimension(300, 200));
				JPanel panel = new JPanel();
				panel.setLayout(new WrapLayout());

				button.setPreferredSize(new Dimension(150, 30));
				button.addActionListener(( actionPerformed ) -> {
					setEnabled(!isEnabled());
					button.setText("Enabled: " + isEnabled());
				});

				JLabel label = new JLabel("Bot ID: ");
				JTextField textField = new JTextField(botID);
				textField.setPreferredSize(new Dimension(140, 20));


				label.setLabelFor(textField);
				panel.add(label);

				frame.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed( WindowEvent e ) {
						super.windowClosed(e);

						String temp = textField.getText();

						if (temp != null && temp.length() == 16) {
							botID = temp;
						}
					}
				});

				panel.add(textField, BorderLayout.SOUTH);

				panel.add(button, BorderLayout.CENTER);
				frame.add(panel, BorderLayout.CENTER);

				frame.setLocationRelativeTo(null);
				frame.pack();
				frame.setResizable(false);
				frame.setVisible(true);
			}
		};
	}

	@Override
	public String getBotName() {
		return "PandoraBots";
	}

	@Override
	public ChatterBotSession getBotSession() {
		return pandoraBotsSession;
	}

	@Override
	public ChatterBotSession getNewSession() {
		return pandoraBots.createSession();
	}

	@Override
	public void setSession( ChatterBotSession session ) {
		pandoraBotsSession = session;
	}

	@Override
	public String getUsage() {
		return "PandoraBots <text/subcommand>";
	}

}

