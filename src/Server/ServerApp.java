package Server;

import java.awt.Dimension;
import java.awt.Toolkit;

import Server.Views.MainScreen;

public class ServerApp {
	public static MainScreen mainScreen;

	public static void main(String[] args) {
		mainScreen = new MainScreen();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		mainScreen.setLocation(dim.width/2- mainScreen.getSize().width/2, dim.height/2 - mainScreen.getSize().height/2);
	}
}
