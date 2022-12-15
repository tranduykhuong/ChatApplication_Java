package Server.Controller;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.bson.Document;

import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Updates;

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

	public void addMemberRoom(String idRoom, String idUser) {
		ArrayList<String> document = new ArrayList<String>();
//		document = (ArrayList<String>) CollectionAccount().find().iterator().next().get("listRoom");
		CollectionAccount().updateOne(eq("id", idUser), combine(Updates.addToSet("listRoom", idRoom)));
	}

	public ArrayList<String> searchListFriend(String id) {
		Document filterDoc = new Document();
		filterDoc.append("id", id);
		MongoCursor<Document> document = CollectionAccount().find(filterDoc).iterator();
		ArrayList<String> listData = new ArrayList<String>();
		try {
			while (document.hasNext()) {
				Document doc = document.next();
				listData.add((String) doc.get("listFriend").toString());
			}
		} finally {
			document.close();
		}
		return listData;
	}

	public ArrayList<String> getFullnameToById(String id) {
		Document filterDoc = new Document();
		filterDoc.append("id", id);
		MongoCursor<Document> document = CollectionAccount().find(filterDoc).iterator();
		ArrayList<String> listData = new ArrayList<String>();
		try {
			while (document.hasNext()) {
				Document doc = document.next();
				listData.add((String) doc.get("fullName").toString());
			}
		} finally {
			document.close();
		}
		return listData;
	}

	public ArrayList<String> getListIdGrToId(String id) {
		Document filterDoc = new Document();
		filterDoc.append("id", id);
		MongoCursor<Document> document = CollectionAccount().find(filterDoc).iterator();
		ArrayList<String> listData = new ArrayList<String>();
		try {
			while (document.hasNext()) {
				Document doc = document.next();
				listData.add((String) doc.get("listRoom").toString());
			}
		} finally {
			document.close();
		}
		return listData;
	}

	public void updateListRoom(String idUser, ArrayList<String> listRoom) {
		CollectionAccount().updateOne(eq("id", idUser), combine(set("listRoom", listRoom)));
		System.out.println("successful");
	}

	public void updateMemberIdInListRoom(String idUser, String idMember) {
		CollectionAccount().updateOne(eq("id", idUser), combine(Updates.addToSet("listRoom", idMember)));
		System.out.println("successful");
	}

	public ArrayList<String> getFullnameIdToByUsername(String username) {
		Document filterDoc = new Document();
		filterDoc.append("userName", username);
		MongoCursor<Document> document = CollectionAccount().find(filterDoc).iterator();
		ArrayList<String> listData = new ArrayList<String>();
		try {
			while (document.hasNext()) {
				Document doc = document.next();
				listData.add((String) doc.get("fullName").toString());
				listData.add((String) doc.get("id").toString());
			}
		} finally {
			document.close();
		}
		return listData;
	}

	public ArrayList<String> getGetListUsername() {
		Document filterDoc = new Document();
		MongoCursor<Document> document = CollectionAccount().find(filterDoc).iterator();
		ArrayList<String> listData = new ArrayList<String>();
		try {
			while (document.hasNext()) {
				Document doc = document.next();
				listData.add((String) doc.get("userName").toString());
			}
		} finally {
			document.close();
		}
		return listData;
	}
}
