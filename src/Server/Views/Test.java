package Server.Views;

import Server.Controller.AccountController;
import Server.Controller.InterfaceAPI;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InterfaceAPI activite = new InterfaceAPI();

		// Off log warning
		Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
		mongoLogger.setLevel(Level.OFF);

		// activite.deleteMemberRoom("a0e27522-8d51-4e8a-8ec8-895fad56330b",
		// "5ef64430-4249-4068-8705-f085fd06a4f4");
		// activite.findMessage("5ef64430-4249-4068-8705-f085fd06a4f4",
		// "dd04a51c-6380-4058-a4b4-d84c59f85774", "asdsad");

		// activite.saveTimeLogin("5ef64430-4249-4068-8705-f085fd06a4f4");
		// activite.setActive("5ef64430-4249-4068-8705-f085fd06a4f4");
		// activite.sendMessageU2U( "dd04a51c-6380-4058-a4b4-d84c59f85774","Nguyen Hong
		// Tan","5ef64430-4249-4068-8705-f085fd06a4f4", "Who are youu ????");
		// activite.createRoom("Team chơi ngu","5ef64430-4249-4068-8705-f085fd06a4f4");
		// activite.addFriend("dd04a51c-6380-4058-a4b4-d84c59f85774", "24322");

		// activite.rejectFriend("5ef64430-4249-4068-8705-f085fd06a4f4",
		// "dd04a51c-6380-4058-a4b4-d84c59f85774");
		activite.acceptFriend("24322", "dd04a51c-6380-4058-a4b4-d84c59f85774");
		// activite.createAccount("Khuong", "123", "HONGTAN", "14/2/200", false, "135
		// bis tphcm", "hongtan@Gmail.com");
		// activite.addMemberRoom("d90a9525-23c8-420b-a9b4-7017917a5d87",
		// "dd04a51c-6380-4058-a4b4-d84c59f85774");

		System.out.println("connect db");
		// RoomController a = new RoomController();
		// a.read();
		// AccountController b = new AccountController();
		// b.read();
		// RoomController a = new RoomController();
		// a.read();
	//	RoomController a = new RoomController();
	//	a.read();
		AccountController b = new AccountController();
		b.read();
	}

}
