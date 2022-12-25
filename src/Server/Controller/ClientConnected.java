package Server.Controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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

//	function gửi message cho client đã đăng nhập
//	Nhớ check id != null (id == null tức là client connected nhưng chưa login nên chưa có id)
//	Lặp qua connectedClient, sử dụng hàm getID() để lấy id
}
