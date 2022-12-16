package Client;

import Client.Views.ConnectionScreen;
import Client.Views.ManageUsersList;

public class ClientApp {
	public static ConnectionScreen connectionScreen;
	public static ManageUsersList MNUserList;
	
	public static void main(String[] args) {
		connectionScreen = new ConnectionScreen();
		MNUserList = new ManageUsersList();
		connectionScreen.setVisible(true);
	}
}