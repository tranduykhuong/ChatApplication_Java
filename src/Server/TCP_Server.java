package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import Server.Models.ClientSocket;

public class TCP_Server {
	private int serverPort;
	private ServerSocket serverSocket;
	public List<ClientSocket> connectedClient;
//	public List<Room> allRooms;

	public boolean openSocket(int port) {
		if (serverSocket != null) {
			closeSocket();
		}
		try {
			serverSocket = new ServerSocket(port);
			connectedClient = new ArrayList<ClientSocket>();
//			allRooms = new ArrayList<Room>();

			new Thread(() -> {
				try {
					do {
						System.out.println("Waiting for client");

						Socket clientSocket = serverSocket.accept();

//						ClientCommunicateThread clientCommunicator = new ClientCommunicateThread(clientSocket);
//						clientCommunicator.start();

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
			for (ClientSocket client : connectedClient)
				client.disconnect();
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		serverSocket = null;
	}
}
