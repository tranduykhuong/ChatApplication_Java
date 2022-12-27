package Server.Controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import Entity.Packet;
import Server.ServerApp;
import Server.Models.ClientSocket;

public class ClientConnected {
	private static ClientConnected sgt;
	private List<ClientSocket> connectedClient;

	private ClientConnected() {
		connectedClient = new ArrayList<ClientSocket>();
	}

	public static ClientConnected getInstance() {
		if (sgt == null) {
			sgt = new ClientConnected();
		}
		return sgt;
	}

	public List<ClientSocket> getClientConnected() {
		return connectedClient;
	}

	public void addClientConnect(ClientSocket cli) {
		connectedClient.add(cli);
		ServerApp.mainScreen.addClientStatus(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy"))
				+ " : Client with port " + cli.getPort() + " connected");
	}

	public void removeClientConnect(ClientSocket cli) {
		for (ClientSocket e : connectedClient) {
			if (e.getPort() == cli.getPort()) {
				connectedClient.remove(cli);
				System.out.println("xoa");
				return;
			}
		}
	}

	public void setID(ClientSocket cli, String id) {
		for (ClientSocket e : connectedClient) {
			if (e.getPort() == cli.getPort()) {
				e.setID(id);
				return;
			}
		}
	}

	public void sendMessage(String listIdUser, String nameGr, String type) {
		if (type.equals("createGroup")) {
			for (ClientSocket e : connectedClient) {
				if (e.getID() != null) {
					for (int i = 0; i < listIdUser.split(", ").length; i++) {
						if (e.getID().equals(listIdUser.split(", ")[i].replace("[", "").replace("]", ""))) {
							e.sendString(new Packet("sendNotifyCreateGr",
									"Bạn đã trở thành thành viên của nhóm " + nameGr, "").toString());
						}
					}
				}
			}
		} else if (type.equals("addAdmin")) {
			for (ClientSocket e : connectedClient) {
				if (e.getID() != null) {
					if (e.getID().equals(listIdUser)) {
						e.sendString(new Packet("sendNotifyAdminGr", "Bạn đã trở thành admin của nhóm " + nameGr, "")
								.toString());
					}
				}
			}
		} else if (type.equals("removeMember")) {
			for (ClientSocket e : connectedClient) {
				if (e.getID() != null) {
					if (e.getID().equals(listIdUser)) {
						e.sendString(
								new Packet("sendNotifyDeleteGr", "Bạn đã bị xóa khỏi nhóm " + nameGr, "").toString());
					}
				}
			}
		} else if (type.equals("addMember")) {
			for (ClientSocket e : connectedClient) {
				if (e.getID() != null) {
					if (e.getID().equals(listIdUser)) {
						e.sendString(
								new Packet("sendNotifyAddGr", "Bạn đã được thêm vào nhóm " + nameGr, "").toString());
					}
				}
			}
		}
	}

//	function gửi message cho client đã đăng nhập
//	Nhớ check id != null (id == null tức là client connected nhưng chưa login nên chưa có id)
//	Lặp qua connectedClient, sử dụng hàm getID() để lấy id
}
