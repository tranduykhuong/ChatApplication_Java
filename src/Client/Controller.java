package Client;

import Client.Views.AddGroupScreen;
import Client.Views.ChatApplicationScreen;
import Client.Views.GroupChatScreen;
import Entity.Packet;

public class Controller {
	private static Controller sgt;
	private TCP_Client client;
	private String hostName;
	private int port;
	private Boolean running;

	private String data;
	private AddGroupScreen addGrScreen = new AddGroupScreen();
	private ChatApplicationScreen chatAppScreen = new ChatApplicationScreen();
	private GroupChatScreen grChat = new GroupChatScreen();

	private Controller() {
		client = new TCP_Client();
		running = false;
	}

	public static void setTis(Controller sgt) {
		Controller.sgt = sgt;
	}

	public static Controller getInstance() {
		if (sgt == null) {
			sgt = new Controller();
		}
		return sgt;
	}

	public boolean connect(String hostname, int port) {
		this.hostName = hostname;
		this.port = port;
		if (client.ConnectToServer(hostname, port)) {
			running = true;
			return running;
		}
		return false;
	}

	public void startListen() {
		new Thread(() -> {
			while (true) {
				if (running == false)
					break;
				var msg = client.readString();
				System.out.println("Client receive: " + msg);

				if (msg == null || msg.equals("Server closed!")) {
					disconnect();
					break;
				}

				Packet pk = new Packet(msg);
				String header = pk.getHeader();

				System.out.println("Header client recive: " + header);
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
				case "showListGr": {
					data = pk.getData();

					chatAppScreen.destroyChatAppForm();
					chatAppScreen.setVisible(true);
					chatAppScreen.showListNameGr(data);
					break;
				}
				case "showListFriend": {
					data = pk.getData();

					addGrScreen.destroyAddGroupScreen();
					addGrScreen.setVisible(true);
					addGrScreen.showlistFriend(data);
					break;
				}
				case "createGroup": {
					data = pk.getData();

					addGrScreen.notifyCreateGroup(data);
					chatAppScreen.destroyChatAppForm();
					chatAppScreen.refeshDataForm();
					break;
				}
				case "showListMemberRoom": {
					data = pk.getData();

					String res = data.substring(data.indexOf("[") + 1, data.length() - 1);
					String listNameMember = res.split("]], ")[0].replace("[", "").replace("]", "");
					String listIDMemberAdmin = res.split("]], ")[1];
					String idGrSelected = res.split("]], ")[2].replace("[", "").replace("]", "");
					String isAdmin = res.split("]], ")[3].replace("[", "").replace("]", "");
					String idUser = res.split("]], ")[4].replace("[", "").replace("]", "");
					String nameGr = res.split("]], ")[5];

					String listIdAdmin = listIDMemberAdmin.split("], ")[0].replace("[", "").replace("]", "");
					String listIdMember = listIDMemberAdmin.split("], ")[1].replace("[", "").replace("]", "");

					grChat.destroyGroupChatScreen();
					grChat.setVisible(true);
					grChat.setTitle(nameGr);
					grChat.showInfoGroupChat(listNameMember, idGrSelected, isAdmin, listIdMember, nameGr, listIdAdmin,
							idUser);
					break;
				}
				case "changeNameGroup": {
					data = pk.getData();

					grChat.checkChangeNameGr(data, chatAppScreen.getDataControlListMemberRoom());
					chatAppScreen.destroyChatAppForm();
//					Bug trùng tên
					chatAppScreen.refeshDataForm();
					break;
				}
				case "administator": {
					data = pk.getData();

					grChat.checkUpdateAdmin(data);
					break;
				}
				case "removeMember": {
					data = pk.getData();

//					còn 1 bug ở chỗ xóa này nếu nó bấm sang một gr khác (merge xong fix tiếp)
					grChat.checkDeleteMember(data, chatAppScreen.getDataControlListMemberRoom());
					break;
				}
				case "addMemberGroup": {
					data = pk.getData();

					grChat.checkAddMember(data, chatAppScreen.getDataControlListMemberRoom());
					break;
				}
				case "chatGroup": {
					break;
				}
				default: {
					break;
				}
				}
			}
		}).start();
	}

	public void sendTextMessage(String content) {
		client.sendString(content);
	}

	public void reconnect() {
		client.DisconnectToServer();
		client.ConnectToServer(hostName, port);
	}

	public void disconnect() {
		running = false;
		client.DisconnectToServer();
	}

	public boolean login(String username, String password) {
		client.sendString("1\t" + username + "\t" + password);
		return false;
	}

	public boolean register(String username, String password) {
		client.sendString("0\t" + username + "\t" + password);
		System.out.println("send");
		return client.readString().equals("1");
	}

}
