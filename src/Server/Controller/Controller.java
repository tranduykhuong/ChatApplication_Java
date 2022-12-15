package Server.Controller;

import java.io.IOException;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
					break;
				}
				case "listGroupChat": {
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
				case "showListGr": {
					String data = pk.getData();
					String res = api.returnListNameGr(data).toString();
					thisClient.sendString(new Packet("showListGr", res, "").toString());
					break;
				}
				case "showListFriend": {
					String data = pk.getData();
					String res = api.searchListFriend(data).toString();
					thisClient.sendString(new Packet("showListFriend", res, "").toString());
					break;
				}
				case "createGroup": {
					String data = pk.getData();
					String idSender = pk.getAuthor();
					String res = api.createGroup(data, idSender).toString();
					thisClient.sendString(new Packet("createGroup", res, "").toString());
					break;
				}
				case "showListMemberRoom": {
					String data = pk.getData();
					String idSender = pk.getAuthor();
					String res = api.listIdMemGr(data, idSender).toString();
					thisClient.sendString(new Packet("showListMemberRoom", res.toString(), "").toString());
					break;
				}
				case "changeNameGroup": {
					String data = pk.getData();

					String res = api.changeNameGr(data).toString();
					thisClient.sendString(new Packet("changeNameGroup", res.toString(), "").toString());
					break;
				}
				case "administator": {
					String data = pk.getData();

					String res = api.updateAdmin(data).toString();
					thisClient.sendString(new Packet("administator", res.toString(), "").toString());
					break;
				}
				case "removeMember": {
					String data = pk.getData();

					String res = api.removeMember(data).toString();
					thisClient.sendString(new Packet("removeMember", res.toString(), "").toString());
					break;
				}
				case "addMemberGroup": {
					String data = pk.getData();
					String res = api.addMemberGroup(data).toString();
					thisClient.sendString(new Packet("addMemberGroup", res.toString(), "").toString());
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
