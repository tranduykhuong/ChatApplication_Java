package Server.Controller;

import java.io.IOException;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import Entity.Packet;
import Server.ServerApp;
import Server.Models.ClientSocket;

public class Controller extends Thread {
	ClientSocket thisClient;
	InterfaceAPI api = new InterfaceAPI();

	public Controller(Socket clientSocket) {
		try {
			thisClient = new ClientSocket(clientSocket);
			ClientConnected.getInstance().addClientConnect(thisClient);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		System.out.println("Server connect client");
		try {
			while (true) {
				String mess = thisClient.readString();
				System.out.println("Server receive: " + mess);

				if (mess == null)
					break;

				if (mess.equals("Disconnect")) {
					ServerApp.mainScreen
							.addClientStatus(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy"))
									+ " : Client with port " + thisClient.getPort() + " disconnected");
					ClientConnected.getInstance().removeClientConnect(thisClient);
					thisClient.disconnect();
					break;
				}
				Packet pk = new Packet(mess);
				String header = pk.getHeader();

				if (header == null)
					throw new IOException();

				System.out.println("Header server receive: " + header);
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
					String dataString = api.showHistoryLogin().toString();
					thisClient.sendString(new Packet("listLoginTime", dataString, "").toString());
					break;
				}
				case "listGroupChat": {
					String dataString = api.showGroupChatList().toString();
					thisClient.sendString(new Packet("listGroupChat", dataString, "").toString());
					break;
				}
				case "showMemberList": {
					String data = pk.getData();
					String dataString = api.showMemberGroup(data).toString();
					thisClient.sendString(new Packet("showMemberList", dataString, "").toString());
					break;
				}
				case "showAdminList": {
					String data = pk.getData();
					String dataString = api.showAdminGroup(data).toString();
					thisClient.sendString(new Packet("showAdminList", dataString, "").toString());
					break;
				}
				case "sortByGroupName": {
					String dataString = api.showGroupChatListToSort().toString();
					thisClient.sendString(new Packet("sortByGroupName", dataString, "").toString());
					break;
				}
				case "sortByCreateDate": {
					String dataString = api.showGroupChatListByCreateDate().toString();
					thisClient.sendString(new Packet("sortByCreateDate", dataString, "").toString());
					break;
				}
				case "signUp": {
					break;
				}
				case "logIn": {
					thisClient.sendString(new Packet("logIn", "Phan hoi ne", "DK").toString());
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
				default: {
					thisClient.sendString("Not found");
					break;
				}
				}
			}

		} catch (IOException e) {
//			if (!Main.socketController.s.isClosed() /* && thisClient.userName != null */) {
//
//				try {
//					// for (ClientApp client : Main.socketController.connectedClient) {
//					// if (!client.userName.equals(thisClient.userName)) {
//					// client.sender.write("user quit");
//					// client.sender.newLine();
//					// client.sender.write(thisClient.userName);
//					// client.sender.newLine();
//					// client.sender.flush();
//					// }
//					// }
//
//					// for (Room room : Main.socketController.allRooms)
//					// room.users.remove(thisClient.userName);
//
//					// thisClient.socket.close();
//
//				} catch (Exception e1) {
//					e1.printStackTrace();
//				}
//				Main.socketController.connectedClient.remove(thisClient);
//				// Main.mainScreen.updateClientTable();
//			}
		}
	}
}
