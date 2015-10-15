package Main;

import Commands.SkypeChatCommand;
import Misc.WrapLayout;
import Utils.ChatUtils;
import com.skype.Chat;
import com.skype.SkypeException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonArea {

	public static JPanel panel = new JPanel();
	public static JScrollPane scrollPane = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

	public static Dimension buttonSize = new Dimension(120,30);

	public ButtonArea(){
		scrollPane.setPreferredSize(new Dimension(160, 450));
		panel.setLayout(new WrapLayout());

		addButtons();
	}

	public JScrollPane getScrollPane(){
		return scrollPane;
	}

	public void addButton(String t, ActionListener listener){
		JButton button = new JButton(t);

		button.setPreferredSize(buttonSize);
		button.addActionListener(listener);

		panel.add(button);
		scrollPane.validate();
	}

	public void addButtons() {
		try {
			for (Chat chat : ChatUtils.getChats()) {
				addButton("Chat: " + chat.getWindowTitle(), getActionForChatButton(chat));
			}
		}catch (Exception e){
			e.printStackTrace();
		}

		scrollPane.validate();
	}



	public ActionListener getActionForChatButton(Chat chat){
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					JFrame frame = new JFrame("Settings: " + chat.getWindowTitle());
					frame.setPreferredSize(new Dimension(280, 300));

					JPanel panel = new JPanel();
					panel.setLayout(new WrapLayout());

					JScrollPane scrollPane = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
					for(SkypeChatCommand chatCommand : ChatUtils.getCommandsForChat(chat)){
						JButton button = new JButton(chatCommand.commandPrefix() + (chatCommand.nonStandardSetting() == null ? ": " + chatCommand.isEnabled() : ""));

						button.setPreferredSize(new Dimension(200, 30));

						if(chatCommand.nonStandardSetting() != null) {
							button.addActionListener(chatCommand.nonStandardSetting());
						}else{
							button.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									chatCommand.setEnabled(!chatCommand.isEnabled());
									button.setText(chatCommand.commandPrefix() + ": " + chatCommand.isEnabled());
								}
							});

						}

						panel.add(button);
					}

					frame.add(scrollPane, BorderLayout.CENTER);

					frame.pack();
					frame.setVisible(true);
					frame.setResizable(false);
					frame.setLocationRelativeTo(null);

				} catch (SkypeException e1) {
					e1.printStackTrace();
				}

			}
		};
	}

}
