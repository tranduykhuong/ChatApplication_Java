package Server.Controller;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
//import java.util.Collections;
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

		Document document = new Document("id", idRoom)
				.append("name", name)
				.append("listMember", listMembers)
				.append("listAdmins", listAdmins)
				.append("listMessage", listMessage)
				.append("createTime", formatter.format(date))
				.append("listMessage", listMessage);
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

	@SuppressWarnings("unchecked")
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

	@SuppressWarnings("unchecked")
	public void deletePeopleRoom(String idRoom, String idDelMember) {
		ArrayList<String> document = new ArrayList<String>();
		document = (ArrayList<String>) CollectionRoom()
				.find(eq("id", idRoom))
				.iterator().next().get("listMember");
		for (int i = 0; i < document.size(); i++) {
			if (document.get(i).equals(idDelMember)) {
				document.remove(i);
				break;
			}
		}

		CollectionRoom().updateOne(eq("id", idRoom), combine(set("listMember", document)));
		System.out.println("Success");
	}

	public ArrayList<String> listGroupChat() {
		MongoCursor<Document> documentCursor = CollectionRoom().find().iterator();
		ArrayList<String> dataGroupArrayList = new ArrayList<>();
		try {
			while (documentCursor.hasNext()) {
				Document nextDocument = documentCursor.next();
				dataGroupArrayList.add((String) nextDocument.get("name"));
				dataGroupArrayList.add((String) nextDocument.get("id").toString());
			}
		} finally {
			documentCursor.close();
		}
		return dataGroupArrayList;
	}

	public ArrayList<String> sortByGroupName() {
		MongoCursor<Document> documentCursor = CollectionRoom().find().iterator();
		ArrayList<String> dataGroupArrayList = new ArrayList<>();
		try {
			while (documentCursor.hasNext()) {
				Document nextDocument = documentCursor.next();
				dataGroupArrayList.add((String) nextDocument.get("name"));
			}
		} finally {
			documentCursor.close();
		}
		return dataGroupArrayList;
	}

	public ArrayList<String> sortByCreateDateGroup() {
		MongoCursor<Document> documentCursor = CollectionRoom().find().iterator();
		ArrayList<String> dataGroupArrayList = new ArrayList<>();
		try {
			while (documentCursor.hasNext()) {
				Document nextDocument = documentCursor.next();
				dataGroupArrayList.add((String) nextDocument.get("name"));
				dataGroupArrayList.add((String) nextDocument.getString("createTime"));
			}
		} finally {
			documentCursor.close();
		}
		return dataGroupArrayList;
	}

	public ArrayList<String> listMember(String groupName) {
		Document group = new Document();
		group.append("name", groupName);
		MongoCursor<Document> documentCursor = CollectionRoom().find(group).iterator();
		ArrayList<String> dataIdMemberList = new ArrayList<>();
		try {
			while (documentCursor.hasNext()) {
				Document nexDocument = documentCursor.next();
				dataIdMemberList.add(nexDocument.get("listMember").toString());
			}
		} finally {
			documentCursor.close();
		}
		return dataIdMemberList;
	}

	public ArrayList<String> listAdmin(String groupN) {
		Document groups = new Document();
		groups.append("name", groupN);
		MongoCursor<Document> documentCursor = CollectionRoom().find(groups).iterator();
		ArrayList<String> dataIdAdminList = new ArrayList<>();
		try {
			while (documentCursor.hasNext()) {
				Document nextDocument = documentCursor.next();
				dataIdAdminList.add(nextDocument.get("listAdmins").toString());
			}
		} finally {
			documentCursor.close();
		}
		return dataIdAdminList;
	}

	@SuppressWarnings("unchecked")
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
