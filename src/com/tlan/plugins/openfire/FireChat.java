package com.tlan.plugins.openfire;

import java.util.Scanner;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.packet.Message;

/**
 * Chat prototype using smack as client library. Server side are supported by
 * openfire.
 * 
 * @author promise
 * @date 2009-3-30
 * 
 */
public class FireChat {
	private static Chat chat;

	/* 发送文本 */
	public static Chat sendTextMessage(String user, XMPPConnection connection)
			throws Exception {
		return connection.getChatManager().createChat(user,
				new MessageListener() {
					public void processMessage(Chat chat, Message message) {
						System.out.println("Received message: "
								+ message.getBody());
					}
				});
		// chat.sendMessage("Hello XMPP Message!");
	}

	public static void main(String[] args) {

		String user = "test@ay131227154706800b19z";
		String host = "115.28.20.72";
		int port = 5222;
		String username = "mgm";
		String password = "123456";
		ConnectionConfiguration config = new ConnectionConfiguration(host, port);
		config.setCompressionEnabled(true);
		config.setSASLAuthenticationEnabled(true);

		XMPPConnection connection = new XMPPConnection(config);

		try {
			connection.connect();
			connection.login(username, password);

			chat = sendTextMessage(user, connection);
			chat.sendMessage("Hello XMPP Message!");
			chat.sendMessage("111");

			Thread.sleep(1000000);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.disconnect();
		}
	}

}
