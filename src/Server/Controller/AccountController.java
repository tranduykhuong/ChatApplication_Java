package Server.Controller;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.bson.Document;

import com.mongodb.client.MongoCursor;

import Server.Models.AccountModel;

public class AccountController extends AccountModel {
	public void create(String id, String userName, String password, String name, String dob, boolean gender,
			String address, String email) {

		ArrayList<String> listFriend = new ArrayList<String>();
		ArrayList<String> listRoom = new ArrayList<String>();
		ArrayList<String> listMessage = new ArrayList<String>();
		ArrayList<String> historyLogin = new ArrayList<String>();
		// boy -> gender :0
		// girl -> gender : 1
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();

		Document document = new Document("id", id).append("userName", userName).append("password", password)
				.append("dob", dob).append("gender", gender).append("address", address).append("email", email)
				.append("listFriend", listFriend).append("listRoom", listRoom).append("listMessage", listMessage)
				.append("createTime", formatter.format(date)).append("active", 1).append("historyLogin", historyLogin);
		CollectionAccount().insertOne(document);
		System.out.println("successful");
	}

	public void read() {

		MongoCursor<Document> document = CollectionAccount().find().iterator();

		try {
			while (document.hasNext()) {
				System.out.println(document.next().toJson());
			}
		} finally {
			document.close();
		}
		System.out.print("Successfull");
	}

	public void addPeopleRoom(String idRoom, String idUser) {
		ArrayList<String> document = new ArrayList<String>();
		document = (ArrayList<String>) CollectionAccount().find().iterator().next().get("listRoom");
		document.add(idRoom);
		CollectionAccount().updateOne(eq("id", idUser), combine(set("listRoom", document)));
	}

	public void update() {
		CollectionAccount().updateOne(eq("_id", "23"), combine(set("name", "23")));
		System.out.println("successful");
	}

	public void delete(String id) {
		CollectionAccount().deleteMany(eq("_id", id));
	}
}
