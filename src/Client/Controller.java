package Client;

import Entity.Packet;

public class Controller {
	private static Controller sgt;
	private TCP_Client client;
	private String hostName;
	private int port;
	private Boolean running;

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
