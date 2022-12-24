package Client;

import Client.Views.ConnectionScreen;
import Client.Views.LoadingScreen;

public class ClientApp {
	public static ConnectionScreen connectionScreen;

	public static void main(String[] args) {
		connectionScreen = new ConnectionScreen();
		connectionScreen.setVisible(true);
	}
}