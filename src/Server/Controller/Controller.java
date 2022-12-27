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
					// System.out.println(data);
					thisClient.sendString(new Packet("showAll", data, "").toString());
					break;
				}
				case "orderName": {
					String data = api.Filter("orderbyName", "ds", Integer.parseInt(pk.getData())).toString();
					thisClient.sendString(new Packet("orderName", data, "").toString());
					break;
				}
				case "orderCreateDate": {
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
					String data = api.showDetailAccount(un, fn, ad, gm).toString();
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
					api.addAccountSc(id, un, fn, pw, ad, dob, gd, gm);
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
					String data = api.updateUser(id, un, fn, dob, gd, ad, gm).toString();
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
					api.blockAccount(un, fn, ad, gm, ac);
					thisClient.sendString(new Packet("lockAccount", "", "").toString());
					break;
				}
				case "saveHistoryLogin": {
					String idUser = pk.getData();
					api.saveTimeLogin(idUser);
					thisClient.sendString(new Packet("saveHistoryLogin", "savedHistoryLogin", "").toString());
					break;
				}
				case "getListFriend": {
					String idUser = pk.getData();
					String dataString =api.seeListFriend(idUser).toString();
					thisClient.sendString(new Packet("getListFriend", dataString, "").toString());
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
					String[] data = pk.getData().split("`");
					String user = api.signup(data[0], data[1], data[2]);
					thisClient.sendString(new Packet("signUp", user, "").toString());
					break;
				}
				case "logIn": {
					String[] req = pk.getData().split("`");
					ArrayList<String> data = api.login(req[0], req[1]);
					if (!data.isEmpty()) {
						System.out.println("aa: " + data);
						ClientConnected.getInstance().setID(thisClient, data.get(data.size() - 1));
						thisClient.sendString(new Packet("logIn", data.toString(), "").toString());
					} else {
						thisClient.sendString(new Packet("logIn", "Username or password is wrong!", "").toString());
					}
					break;
				}
				case "forgotPassword": {
					String username = pk.getData();
					boolean check = api.forgotPassword(username);
					if (check) {
						thisClient.sendString(new Packet("forgotPassword", "success", "").toString());
					} else {
						thisClient.sendString(new Packet("forgotPassword", "fail", "").toString());
					}
					break;
				}
				case "addFriend": {
					String replace1 = pk.getData().substring(0, pk.getData().length());
					List<String> myList = new ArrayList<String>(Arrays.asList(replace1.split(", ")));
					String idUserRequest = myList.get(0);
					String idUserSentRequest = myList.get(1);
					api.addFriend(idUserRequest, idUserSentRequest);
					thisClient.sendString(new Packet("addFriend", "add friend successfully", "").toString());
					break;
				}
				case "unFriend": {
					String replace1 = pk.getData().substring(0, pk.getData().length());
					List<String> myList = new ArrayList<String>(Arrays.asList(replace1.split(", ")));
					String idUser = myList.get(0);
					String idFriendDel = myList.get(1);
					api.deleteFriend(idUser, idFriendDel);
					thisClient.sendString(new Packet("unFriend", "unfriend successfully", "").toString());
					break;
				}
				case "acceptRequestFriend": {
					String replace1 = pk.getData().substring(0, pk.getData().length());
					List<String> myList = new ArrayList<String>(Arrays.asList(replace1.split(", ")));
					String idUser = myList.get(0);
					String idNewFriend = myList.get(1);
					api.acceptFriend(idUser, idNewFriend);
					thisClient.sendString(new Packet("acceptRequestFriend", "accept request add friend successfully", "").toString());
					break;
				}
				case "rejectRequestFriend": {
					String replace1 = pk.getData().substring(0, pk.getData().length());
					List<String> myList = new ArrayList<String>(Arrays.asList(replace1.split(", ")));
					String idUser = myList.get(0);
					String idFriendReject = myList.get(1);
					api.rejectFriend(idUser, idFriendReject);
					thisClient.sendString(new Packet("rejectRequestFriend", "reject request add friend successfully", "").toString());
					break;
				}
				case "setStatus":{
					String idUser = pk.getData();
					api.setActive(idUser);
					thisClient.sendString(new Packet("setStatus", "set new status successfully", "").toString());
					break;
				}
				case "listFriendOnline": {
					String idUser = pk.getData();
					String dataString = api.getListFriendOnline(idUser).toString();
					thisClient.sendString(new Packet("listFriendOnline", dataString, "").toString());
					break;
				}
				case "chatWithFriendOnline": {
					String replace1 = pk.getData().substring(0, pk.getData().length());
					List<String> myList = new ArrayList<String>(Arrays.asList(replace1.split(", ")));
					String idSender = myList.get(0);
					String nameSender = myList.get(1);
					String idReceiver = myList.get(2);
					String message = myList.get(3);
					api.sendMessageU2U( idSender,  nameSender,  idReceiver,  message);
					thisClient.sendString(new Packet("chatWithFriendOnline", "chat with friend successfully", "").toString());
					break;
				}
				case "chatWithFriendOffline": {
					String replace1 = pk.getData().substring(0, pk.getData().length());
					List<String> myList = new ArrayList<String>(Arrays.asList(replace1.split(", ")));
					String idSender = myList.get(0);
					String nameSender = myList.get(1);
					String idReceiver = myList.get(2);
					String message = myList.get(3);
					api.sendMessageU2UOffline( idSender,  nameSender,  idReceiver,  message);
					thisClient.sendString(new Packet("chatWithFriendOnline", "chat with friend successfully", "").toString());
					break;
				}
				case "viewChatHistoryWithFriend": {
					String replace1 = pk.getData().substring(0, pk.getData().length());
					List<String> myList = new ArrayList<String>(Arrays.asList(replace1.split(", ")));
					String idUser1 = myList.get(0);
					String idUser2 = myList.get(1);
					String dataString = api.showMessageU2U(idUser1, idUser2).toString();;
					thisClient.sendString(new Packet("viewChatHistoryWithFriend", dataString, "").toString());
					break;
				}
				case "removeChatWithFriendHistory": {
					String replace1 = pk.getData().substring(0, pk.getData().length());
					List<String> myList = new ArrayList<String>(Arrays.asList(replace1.split(", ")));
					String idSender = myList.get(0);
					String idReceiver = myList.get(1);
					api.removeMessageU2U( idSender,  idReceiver);
					thisClient.sendString(new Packet("removeChatWithFriendHistory", "remove message successfully", "").toString());
					
					break;
				}
				case "searchMessageOfFriendChat": {
					String replace1 = pk.getData().substring(0, pk.getData().length());
					List<String> myList = new ArrayList<String>(Arrays.asList(replace1.split(", ")));
					String idSender = myList.get(0);
					String idReceiver = myList.get(1);
					String keyword = myList.get(2);
					String dataString = api.findMessage(idSender, idReceiver, keyword).toString();
					thisClient.sendString(new Packet("searchMessageOfFriendChat", dataString, "").toString());
					break;
				}
				case "showListGr": {
					String data = pk.getData();
					String idSender = pk.getAuthor();
					String res = api.returnListNameGr(data).toString();
					thisClient.sendString(new Packet("showListGr", res, idSender).toString());

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
					ArrayList<String> createGr = new ArrayList<String>();

					createGr = api.createGroup(data, idSender);
					String res;
					if (createGr.size() <= 1) {
						res = createGr.get(0);

						thisClient.sendString(new Packet("createGroup", res, "").toString());
					} else {
						res = createGr.get(0);
						String listUserGr = createGr.get(1);
						String nameGr = data.split("], ")[0].replace("[", "");

						thisClient.sendString(new Packet("createGroup", res, "").toString());
						ClientConnected.getInstance().sendMessage(listUserGr, nameGr, "createGroup");
					}
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

					String nameGr = data.split("`, ")[0].replace("[", "");
					String dataIdGrUser = "[" + data.split("`, ")[1].replace("[", "");

					String res = api.updateAdmin(dataIdGrUser).toString();
					if (res.replace("[", "").replace("]", "").equals("Update thành công")) {
						thisClient.sendString(new Packet("administator", res.toString(), "").toString());
						String idUserNotify = dataIdGrUser.split(", ")[1].replace("[", "").replace("]", "");
						ClientConnected.getInstance().sendMessage(idUserNotify, nameGr, "addAdmin");
					} else {
						thisClient.sendString(new Packet("administator", res.toString(), "").toString());
					}
					break;
				}
				case "removeMember": {
					String data = pk.getData();

					String nameGr = data.split("`, ")[0].replace("[", "");
					String dataIdGrUser = "[" + data.split("`, ")[1].replace("[", "");

					String res = api.removeMember(dataIdGrUser).toString();
					if (res.replace("[", "").replace("]", "").equals("Xóa thành công")) {
						thisClient.sendString(new Packet("removeMember", res.toString(), "").toString());
						String idUserNotify = dataIdGrUser.split(", ")[1].replace("[", "").replace("]", "");
						ClientConnected.getInstance().sendMessage(idUserNotify, nameGr, "removeMember");
					} else {
						thisClient.sendString(new Packet("removeMember", res.toString(), "").toString());
					}

					break;
				}
				case "addMemberGroup": {
					String data = pk.getData();
					String idSender = pk.getAuthor();

					String nameGr = data.split("`, ")[0].replace("[", "");
					String dataIdGrUser = "[" + data.split("`, ")[1].replace("[", "");

					String res = api.addMemberGroup(dataIdGrUser, idSender).toString();
					if (res.split(", ").length > 1) {
						thisClient.sendString(new Packet("addMemberGroup", res.toString(), "").toString());
						String idUserNotify = res.split(", ")[2].replace("[", "").replace("]", "");
						ClientConnected.getInstance().sendMessage(idUserNotify, nameGr, "addMember");
					} else {
						thisClient.sendString(new Packet("addMemberGroup", res.toString(), "").toString());
					}
					break;
				}
//				case "sendNotifyCreateGr": {
//					String data = pk.getData();
//
//					thisClient.sendString(new Packet("sendNotifyCreateGr", data, "").toString());
//
//					break;
//				}
				case "chatGroup": {
					String replace1 = pk.getData().substring(0, pk.getData().length());
					List<String> myList = new ArrayList<String>(Arrays.asList(replace1.split(", ")));
					String idSender = myList.get(0);
					String nameSender = myList.get(1);
					String idRoom = myList.get(2);
					String message = myList.get(3);
					api.sendMessageRoom( idSender,  nameSender,  idRoom,  message);
					thisClient.sendString(new Packet("chatGroup", "chat with group successfully", "").toString());
					
					break;
				}
				case "resetPassword": {
					String username = pk.getData();
					boolean check = api.forgotPassword(username);
					if (check) {
						thisClient.sendString(new Packet("resetPassword", "success", "").toString());
					} else {
						thisClient.sendString(new Packet("resetPassword", "fail", "").toString());
					}
					break;
				}
				default: {
					thisClient.sendString("Not found");
					break;
				}
				}
			}

		} catch (IOException e) {
			ServerApp.mainScreen.reConnect();
		}
	}
}
