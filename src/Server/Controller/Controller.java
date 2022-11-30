package Server.Controller;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import Client.ClientApp;
import Server.Views.Main;

public class Controller extends Thread {
	ClientApp thisClient;

	public Controller(Socket clientSocket) {
		try {
			thisClient = new ClientApp();
//			thisClient.socket = clientSocket;
			OutputStream os = clientSocket.getOutputStream();
//			thisClient.sender = new BufferedWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8));
			InputStream is = clientSocket.getInputStream();
//			thisClient.receiver = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
//			thisClient.port = clientSocket.getPort();
		} catch (IOException e) {

		}
	}
	@Override
	public void run() {
		try {
			while (true) {
				// String header = thisClient.receiver.readLine();
				String header = "";
				if (header == null)
					throw new IOException();

				System.out.println("Header: " + header);
				switch (header) {
					case "filterList": {
						break;
					}
					case "addAccount": {
						break;
					}
					case "updateAccount": {
						break;
					}
					case "removeAccount": {
						break;
					}
					case "lockAccount": {
						break;
					}
					case "historyLogin": {
						break;
					}
					case "listFriend": {
						break;
					}
					case "listLoginTime": {
						break;
					}
					case "listGroupChat": {
						break;
					}
					case "signUp": {
						break;
					}
					case "logIn": {
						break;
					}
					case "forgotPassword": {
						break;
					}
					case "addFriend": {
						break;
					}
					case "unFriend": {
						break;
					}
					case "listFriendOnline": {
						break;
					}
					case "chatWithFriendOnline": {
						break;
					}
					case "chatWithFriendOffline": {
						break;
					}
					case "viewChatHistory": {
						break;
					}
					case "romoveChatHistory": {
						break;
					}
					case "searchStringChatSingle": {
						break;
					}
					case "searchStringChatMultiple": {
						break;
					}
					case "createGroup": {
						break;
					}
					case "changeNameGroup": {
						break;
					}
					case "administator": {
						break;
					}
					case "removeMember": {
						break;
					}
					case "chatGroup": {
						break;
					}
				}
			}

		} catch (IOException e) {
			if (!Main.socketController.s.isClosed() /*&& thisClient.userName != null*/) {

				try {
//					for (ClientApp client : Main.socketController.connectedClient) {
//						if (!client.userName.equals(thisClient.userName)) {
//							client.sender.write("user quit");
//							client.sender.newLine();
//							client.sender.write(thisClient.userName);
//							client.sender.newLine();
//							client.sender.flush();
//						}
//					}

//					for (Room room : Main.socketController.allRooms)
//						room.users.remove(thisClient.userName);

//					thisClient.socket.close();

				} catch (Exception e1) {
					e1.printStackTrace();
				}
				Main.socketController.connectedClient.remove(thisClient);
//				Main.mainScreen.updateClientTable();
			}
		}
	}
}
