package Server.Controller;

import java.io.IOException;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
					String data = api.Filter("searchByName", pk.getData(), 0).toString();
					thisClient.sendString(new Packet("filterList", data, "").toString());
					break;
				}
				case "showAll": {
					String data = api.Filter("showAllInf", "", 0).toString();
					//System.out.println(data);
					thisClient.sendString(new Packet("showAll", data, "").toString());
					break;
				}
				case "orderName":{
					String data = api.Filter("orderbyName", "ds", Integer.parseInt(pk.getData())).toString();
					thisClient.sendString(new Packet("orderName", data, "").toString());
					break;
				}
				case "orderCreateDate":{
					String data = api.Filter("orderbyCreateDay", "ds", Integer.parseInt(pk.getData())).toString();
					thisClient.sendString(new Packet("orderCreateDate", data, "").toString());
					break;
				}
				case "showDetail": {
					String replace1 = pk.getData().substring(0, pk.getData().length());
					List<String> myList = new ArrayList<String>(Arrays.asList(replace1.split(", ")));
					String un = myList.get(0);
					String fn = myList.get(1);
					String ad = myList.get(2);
					String gm = myList.get(3);
					String data = api.showDetailAccount(un,fn,ad,gm).toString();
					thisClient.sendString(new Packet("showDetail", data, "").toString());
					break;
				}
				case "addAccount": {
					String replace1 = pk.getData().substring(0, pk.getData().length());
					List<String> myList = new ArrayList<String>(Arrays.asList(replace1.split(", ")));
					String id = myList.get(0);
					String un = myList.get(1);
					String fn = myList.get(2);
					String pw = myList.get(3);
					String ad = myList.get(4);
					String dob = myList.get(5);
					String gd = myList.get(6);
					String gm = myList.get(7);
					api.addAccountSc(id,un,fn,pw,ad,dob,gd,gm);
					thisClient.sendString(new Packet("addAccount", "Thêm thành công", "").toString());
					break;
				}
				case "updateAccount": {
					String replace1 = pk.getData().substring(0, pk.getData().length());
					List<String> myList = new ArrayList<String>(Arrays.asList(replace1.split(", ")));
					String id = myList.get(0);
					String un = myList.get(1);
					String fn = myList.get(2);
					String dob = myList.get(3);
					String gd = myList.get(4);
					String ad = myList.get(5);
					String gm = myList.get(6);
					String data = api.updateUser(id,un,fn,dob,gd,ad,gm).toString();
					thisClient.sendString(new Packet("updateAccount", data, "").toString());
					break;
				}
				case "removeAccount": {
					String replace1 = pk.getData().substring(0, pk.getData().length());
					List<String> myList = new ArrayList<String>(Arrays.asList(replace1.split(", ")));
					String un = myList.get(0);
					String fn = myList.get(1);
					String ad = myList.get(2);
					String gm = myList.get(3);
					api.deleteAccount(un, fn, ad, gm);
					thisClient.sendString(new Packet("removeAccount", "", "").toString());
					break;
				}
				case "lockAccount": {
					String replace1 = pk.getData().substring(0, pk.getData().length());
					List<String> myList = new ArrayList<String>(Arrays.asList(replace1.split(", ")));
					String un = myList.get(0);
					String fn = myList.get(1);
					String ad = myList.get(2);
					String gm = myList.get(3);
					String ac = myList.get(4);
					api.blockAccount(un,fn,ad,gm,ac);
					thisClient.sendString(new Packet("lockAccount", "", "").toString());
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

	private String ParseInt(String data) {
		// TODO Auto-generated method stub
		return null;
	}
}
