package Server.Views;

import Server.Controller.AccountController;
import Server.Controller.InterfaceAPI;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InterfaceAPI activite = new InterfaceAPI();
		// activite.createRoom("Room1","5072578c-4be0-44f7-b8c9-4b29d7f7ab1b");
//		activite.createAccount("Khuong", "123", "HONGTAN", "14/2/200", false, "135 bis tphcm", "hongtan@Gmail.com");
		// activite.addMemberRoom("d90a9525-23c8-420b-a9b4-7017917a5d87",
		// "dd04a51c-6380-4058-a4b4-d84c59f85774");
		System.out.println("connect db");
//		RoomController a = new RoomController();
//		a.read();
		AccountController b = new AccountController();
		b.read();
	}

}
