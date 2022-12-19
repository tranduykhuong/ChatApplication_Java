package Server.Controller;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

import Client.ClientApp;

public class SocketController {
	public String serverName;
	public int serverPort;
	ServerSocket s;
	public List<ClientApp> connectedClient;
//	public List<Room> allRooms;

	public void OpenSocket(int port) {
		try {
			s = new ServerSocket(port);
			connectedClient = new ArrayList<ClientApp>();
//			allRooms = new ArrayList<Room>();

			new Thread(() -> {
				try {
					do {
						System.out.println("Waiting for client");

						Socket clientSocket = s.accept();

						Controller clientCommunicator = new Controller(clientSocket);
						clientCommunicator.start();

					} while (s != null && !s.isClosed());
				} catch (IOException e) {
					System.out.println("Server or client socket closed");
				}
			}).start();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void CloseSocket() {
		try {
			for (ClientApp client : connectedClient)
//				client.socket.close();
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getThisIP() {
		String ip = "";
		try {
			Socket socket = new Socket();
			socket.connect(new InetSocketAddress("google.com", 80));
			ip = socket.getLocalAddress().getHostAddress();
			socket.close();
		} catch (IOException e) {
			e.getStackTrace();
		}
		return ip;
	}
}
