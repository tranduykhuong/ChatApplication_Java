package Server.Views;
//import org.bson.Document;
//import com.mongodb.client.MongoClient;
//import com.mongodb.client.MongoClients;
//import com.mongodb.cl	ient.MongoCollection;
//import com.mongodb.client.MongoDatabase;
import Server.Controller.*;
import Server.Models.*;
public class Test {
	public static void main(String [] args)
	{

		InterfaceApi activite = new InterfaceApi();
	//	activite.createRoom("Room1","5072578c-4be0-44f7-b8c9-4b29d7f7ab1b");
	//activite.createAccount("TAN", "123", "HONGTAN", "14/2/200", false, "353 bis tphcm", "hongtan@Gmail.com");
		//activite.addMemberRoom("d90a9525-23c8-420b-a9b4-7017917a5d87", "dd04a51c-6380-4058-a4b4-d84c59f85774");
		Room a = new Room();
		a.read();
		Account b = new Account();
		b.read();
	}
}
