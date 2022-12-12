package Client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Client.Views.User;
import Entity.Packet;

public class Controller {
	private static Controller sgt;
	private TCP_Client client;
	private String hostName;
	private int port;
	private Boolean running;
	private User userframe;
	
	private String data;

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

				System.out.println("Header client receive: " + header);
				switch (header) {
				case "filterList": {
					data = pk.getData();
					String replace1 = data.substring(1, data.lastIndexOf("]"));
					List<String> myList = new ArrayList<String>(Arrays.asList(replace1.split(", ")));
					ClientApp.MNUserList.showInfor(myList);
					break;
				}
				case "showAll": {
					data = pk.getData();
					String replace1 = data.substring(1, data.lastIndexOf("]"));
					List<String> myList = new ArrayList<String>(Arrays.asList(replace1.split(", ")));
					System.out.println("List packet display:" + myList);
					ClientApp.MNUserList.showInfor(myList);
					break;
				}
				case "orderName":{
					data = pk.getData();
					String replace1 = data.substring(1, data.lastIndexOf("]"));
					List<String> myList = new ArrayList<String>(Arrays.asList(replace1.split(", ")));
					ClientApp.MNUserList.showInfor(myList);
					break;
				}
				case "orderCreateDate":{
					data = pk.getData();
					String replace1 = data.substring(1, data.lastIndexOf("]"));
					List<String> myList = new ArrayList<String>(Arrays.asList(replace1.split(", ")));
					ClientApp.MNUserList.showInfor(myList);
					break;
				}
				case "showDetail": {
					data = pk.getData();
					String replace1 = data.substring(1, data.lastIndexOf("]"));
					List<String> myList = new ArrayList<String>(Arrays.asList(replace1.split(", ")));

					String id = myList.get(0);
					String active = myList.get(1);
					String usname = myList.get(2);
					String fname = myList.get(3);
					String addr = myList.get(4);
					String dob = myList.get(5);
					String gender = myList.get(6);
					String email = myList.get(7);
					
					String tmpKo = data.substring(1, data.lastIndexOf("]"));
					String tmp = tmpKo.substring(tmpKo.indexOf("[", tmpKo.indexOf("[")) + 1, tmpKo.lastIndexOf("]"));
					String tmp1 = tmp.substring(tmp.indexOf("[") + 1, tmp.length());
					String listFriend = tmp1.substring(tmp1.indexOf("[") + 1, tmp1.length());
					
					String historyLg = tmp1.substring(0, tmp1.indexOf("]"));
					
					userframe = new User(id, usname, fname, addr, dob, gender, email);
					userframe.setVisible(true);
					userframe.showInformation(active, usname, fname, addr, dob, gender, email, listFriend, historyLg);
				}
				case "addAccount": {
					data = pk.getData();
					break;
				}
				case "updateAccount": {
					data = pk.getData();
					break;
				}
				case "removeAccount": {
					data = pk.getData();
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
