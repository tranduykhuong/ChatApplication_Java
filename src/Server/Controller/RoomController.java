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

import Server.Models.RoomModel;

public class RoomController extends RoomModel {
	public void create(String name, String idRoom, String idUser, ArrayList<String> _listMembers) {

		ArrayList<String> listMembers = new ArrayList<String>();
		listMembers = _listMembers;
		ArrayList<String> listAdmins = new ArrayList<String>();
		listAdmins.add(idUser);
		ArrayList<String> listMessage = new ArrayList<String>();

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();

		Document document = new Document("id", idRoom).append("listMember", listMembers)
				.append("listAdmins", listAdmins).append("listMessage", listMessage)
				.append("createTime", formatter.format(date)).append("listMessage", listMessage).append("name", name);
		CollectionRoom().insertOne(document);
		System.out.println("successful");
	}

	public void read() {
		MongoCursor<Document> document = CollectionRoom().find().iterator();
		try {
			while (document.hasNext()) {
				System.out.println(document.next().toJson());
			}
		} finally {
			document.close();
		}
	}

	public void addPeopleRoom(String idUser, String idRoom) {
		ArrayList<String> document = new ArrayList<String>();
		document = (ArrayList<String>) CollectionRoom().find().iterator().next().get("listMember");
		document.add(idUser);
		CollectionRoom().updateMany(eq("id", idRoom), combine(set("listMember", document)));
	}

	public void update() {
		CollectionRoom().updateOne(eq("_id", "23"), combine(set("name", "23")));
		System.out.println("successful");
	}

	public void delete(String id) {
		CollectionRoom().deleteMany(eq("_id", id));
	}

	public void insertPeopleRoom(String idUser, String idRoom) {
		ArrayList<String> document = new ArrayList<String>();
		document = (ArrayList<String>) CollectionRoom().find().iterator().next().get("listMember");
		document.add(idUser);
		CollectionRoom().updateOne(eq("id", idRoom), combine(set("listMember", document)));
	}

	public ArrayList<String> getListNameGr() {
		Document filterDoc = new Document();
		MongoCursor<Document> document = CollectionRoom().find(filterDoc).iterator();
		ArrayList<String> listData = new ArrayList<String>();
		try {
			while (document.hasNext()) {
				Document doc = document.next();
				listData.add((String) doc.get("name").toString());
			}
		} finally {
			document.close();
		}
		return listData;
	}

	public ArrayList<String> getListIdGr() {
		Document filterDoc = new Document();
		MongoCursor<Document> document = CollectionRoom().find(filterDoc).iterator();
		ArrayList<String> listData = new ArrayList<String>();
		try {
			while (document.hasNext()) {
				Document doc = document.next();
				listData.add((String) doc.get("id").toString());
			}
		} finally {
			document.close();
		}
		return listData;
	}

	public ArrayList<String> getListNameToGr(String idGr) {
		Document filterDoc = new Document();
		filterDoc.append("id", idGr);
		MongoCursor<Document> document = CollectionRoom().find(filterDoc).iterator();
		ArrayList<String> listData = new ArrayList<String>();
		try {
			while (document.hasNext()) {
				Document doc = document.next();
				listData.add((String) doc.get("name").toString());
			}
		} finally {
			document.close();
		}
		return listData;
	}

	public ArrayList<String> getListIdMemberRoom(String idGr) {
		Document filterDoc = new Document();
		filterDoc.append("id", idGr);
		MongoCursor<Document> document = CollectionRoom().find(filterDoc).iterator();
		ArrayList<String> listData = new ArrayList<String>();
		try {
			while (document.hasNext()) {
				Document doc = document.next();
				listData.add((String) doc.get("listAdmins").toString());
				listData.add((String) doc.get("listMember").toString());
			}
		} finally {
			document.close();
		}
		return listData;
	}

	public void updateMember(String idRoom, ArrayList<String> listNewMember) {
		CollectionRoom().updateOne(eq("id", idRoom), combine(set("listMember", listNewMember)));
		System.out.println("successful");
	}

	public void updateMemberIdInGr(String idRoom, String idMember) {
		CollectionRoom().updateOne(eq("id", idRoom), combine(Updates.addToSet("listMember", idMember)));
		System.out.println("successful");
	}

	public void updateAdmin(String idRoom, ArrayList<String> listNewAdmin) {
		CollectionRoom().updateOne(eq("id", idRoom), combine(set("listAdmins", listNewAdmin)));
		System.out.println("successful");
	}

	public void changeNameGr(String idRoom, String newNameGr) {
		CollectionRoom().updateOne(eq("id", idRoom), combine(set("name", newNameGr)));
		System.out.println("successful");
	}
}
