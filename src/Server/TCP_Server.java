package Server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import Server.Controller.ClientConnected;
import Server.Controller.Controller;
import java.util.ArrayList;
import java.util.List;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import Server.Models.ClientSocket;

public class TCP_Server {
	private int serverPort;
	private InetAddress serverIP;
	private ServerSocket serverSocket;

	public boolean openSocket(String ip, int port) {
		if (serverSocket != null) {
			closeSocket();
		}
		try {
			this.serverPort = port;
			this.serverIP = InetAddress.getByName(ip);
			serverSocket = new ServerSocket(serverPort, 1000, serverIP);
			System.out.println("Server is running in port " + port);

			new Thread(() -> {
				try {
					do {
						System.out.println("Waiting for client...");

						Socket clientSocket = serverSocket.accept();

						System.out.println(clientSocket.getPort());
						Controller clientController = new Controller(clientSocket);
						clientController.start();

					} while (serverSocket != null && !serverSocket.isClosed());
				} catch (IOException e) {
					System.out.println("Server or client socket closed");
				}
			}).start();

		} catch (IOException e) {
			return false;
		}
		serverPort = port;
		return true;
	}

	public void closeSocket() {
		try {
			for (ClientSocket client : ClientConnected.getInstance().getClientConnected()) {
				if (client.isConnected()) {
					client.sendString("Server closed!");
				}
			}
			if (serverSocket != null) {
				serverSocket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		serverSocket = null;
	}

	public String getThisIP() {
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
