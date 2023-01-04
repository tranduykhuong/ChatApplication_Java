package Client;

import java.awt.Dimension;
import java.awt.Toolkit;

import Client.Views.ConnectionScreen;

public class ClientApp {
	public static ConnectionScreen connectionScreen;

	public static void main(String[] args) {
		connectionScreen = new ConnectionScreen();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		connectionScreen.setLocation(dim.width/2- connectionScreen.getSize().width/2, dim.height/2 - connectionScreen.getSize().height/2);
		connectionScreen.setVisible(true);
	}
}