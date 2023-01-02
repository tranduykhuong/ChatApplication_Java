package Server.Controller;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

import Server.Models.AccountModel;
import Server.Models.ClientSocket;

public class AccountController extends AccountModel {
	Logger logger1 = LoggerFactory.getLogger("org.mongodb.driver");

	public String formatDate() {

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();

		return formatter.format(date);
	}

	public void create(String id, String fullName, String userName, String password, String dob, boolean gender,
			String address, String email) {

		ArrayList<String> listFriend = new ArrayList<String>();
		ArrayList<String> listRequestAddFriend = new ArrayList<String>();
		ArrayList<String> listRoom = new ArrayList<String>();
		ArrayList<String> historyLogin = new ArrayList<String>();
		// boy -> gender :0
		// girl -> gender : 1

		Document document = new Document("id", id).append("fullName", fullName).append("userName", userName)
				.append("password", password).append("dob", dob).append("gender", gender).append("address", address)
				.append("email", email).append("listFriend", listFriend)
				.append("listRequestAddFriend", listRequestAddFriend).append("listRoom", listRoom)
				.append("createTime", formatDate()).append("active", 1).append("historyLogin", historyLogin)
				.append("role", "user");
		CollectionAccount().insertOne(document);
		System.out.println("successful");
	}

	public void createAccount(String id, String userName, String fullName, String password, String dob, boolean gender,
			String address, String email, String role) {
		logger1.atLevel(org.slf4j.event.Level.ERROR);
		ArrayList<String> listFriend = new ArrayList<String>();
		ArrayList<String> listRoom = new ArrayList<String>();
		ArrayList<String> listMessage = new ArrayList<String>();
		ArrayList<String> historyLogin = new ArrayList<String>();
		// boy -> gender :0
		// girl -> gender : 1
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();

		Document document = new Document("id", id).append("userName", userName).append("fullName", fullName)
				.append("password", password).append("dob", dob).append("gender", gender).append("address", address)
				.append("email", email).append("listFriend", listFriend).append("listRoom", listRoom)
				.append("listMessage", listMessage).append("createTime", formatter.format(date)).append("active", 1)
				.append("historyLogin", historyLogin).append("role", role);
		CollectionAccount().insertOne(document);
		System.out.println("successful");
	}

	public void updateProfile(String id, String fullName, String address, String dob, boolean gender) {
		logger1.atLevel(org.slf4j.event.Level.ERROR);
		CollectionAccount().updateOne(eq("id", id),
				combine(set("fullName", fullName), set("dob", dob), set("address", address), set("gender", gender)));
	}

	public ArrayList<String> read() {
		logger1.atLevel(org.slf4j.event.Level.ERROR);
		MongoCursor<Document> document = CollectionAccount().find().iterator();
		ArrayList<String> listData = new ArrayList<String>();
		try {
			while (document.hasNext()) {
				Document doc = document.next();
				listData.add((String) doc.get("userName"));
				listData.add((String) doc.get("fullName"));
				listData.add((String) doc.get("address"));
				listData.add((String) doc.get("dob"));
				listData.add((String) doc.get("gender").toString());
				listData.add((String) doc.get("email"));
			}
		} finally {
			document.close();
		}

		return listData;
	}

	public ArrayList<String> listHistoryLogin() {
		logger1.atLevel(org.slf4j.event.Level.ERROR);
		MongoCursor<Document> documentCursor = CollectionAccount().find().iterator();
		ArrayList<String> dataArrayList = new ArrayList<>();
		try {
			while (documentCursor.hasNext()) {
				Document nextDocument = documentCursor.next();
				if (!"[]".equals(nextDocument.get("historyLogin").toString())) {
					dataArrayList.add((String) nextDocument.get("userName"));
					dataArrayList.add((String) nextDocument.get("fullName"));
					dataArrayList.add((String) nextDocument.get("historyLogin").toString());
				}
			}
		} finally {
			documentCursor.close();
		}
		return dataArrayList;
	}

	public ArrayList<String> listMemberGroupChat(String id) {
		Document idMember = new Document();
		idMember.append("id", id);
		MongoCursor<Document> documentCursor = CollectionAccount().find(idMember).iterator();
		ArrayList<String> dataMemberArrayList = new ArrayList<>();
		try {
			while (documentCursor.hasNext()) {
				Document document = documentCursor.next();
				dataMemberArrayList.add((String) document.get("userName"));
			}
		} finally {
			documentCursor.close();
		}
		return dataMemberArrayList;
	}

	public ArrayList<String> listAdminGroupChat(String id) {
		Document idMember = new Document();
		idMember.append("id", id);
		MongoCursor<Document> documentCursor = CollectionAccount().find(idMember).iterator();
		ArrayList<String> dataAdminArrayList = new ArrayList<>();
		try {
			while (documentCursor.hasNext()) {
				Document document = (Document) documentCursor.next();
				dataAdminArrayList.add((String) document.get("userName"));
			}
		} finally {
			documentCursor.close();
		}
		return dataAdminArrayList;
	}

	public String checkAccountExist(String userName) {
		MongoCursor<Document> document = CollectionAccount().find(eq("userName", userName)).iterator();
		try {
			while (document.hasNext()) {
				Document doc = document.next();
				return (String) doc.get("password");
			}
		} finally {
			document.close();
		}
		return "";
	}

	public ArrayList<String> findByUsername(String userName) {
		logger1.atLevel(org.slf4j.event.Level.ERROR);
		MongoCursor<Document> document = CollectionAccount().find(eq("userName", userName)).iterator();
		ArrayList<String> listData = new ArrayList<String>();
		try {
			while (document.hasNext()) {
				Document doc = document.next();
				listData.add((String) doc.get("userName"));
				listData.add((String) doc.get("fullName"));
				listData.add((String) doc.get("address"));
				listData.add((String) doc.get("dob"));
				listData.add((String) doc.get("gender").toString());
				listData.add((String) doc.get("email"));
				listData.add((String) doc.get("password"));
				listData.add((String) doc.get("role"));
				listData.add((String) doc.get("id"));
			}
		} finally {
			document.close();
		}
		return listData;
	}

	public ArrayList<String> FilterByName(int status) {
		logger1.atLevel(org.slf4j.event.Level.ERROR);
		List<Document> document = CollectionAccount().find()
				.sort(new BasicDBObject("userName".substring("userName".lastIndexOf("|") + 1), status))
				.into(new ArrayList<>());
		ArrayList<String> listData = new ArrayList<String>();
		try {
			for (Document e : document) {
				listData.add((String) e.get("userName"));
				listData.add((String) e.get("fullName"));
				listData.add((String) e.get("address"));
				listData.add((String) e.get("dob"));
				listData.add((String) e.get("gender").toString());
				listData.add((String) e.get("email"));
			}
		} finally {
		}
		return listData;
	}

	public ArrayList<String> FilterByDate(int status) {
		logger1.atLevel(org.slf4j.event.Level.ERROR);
		List<Document> document = CollectionAccount().find().sort(new BasicDBObject("createTime", status))
				.into(new ArrayList<>());
		ArrayList<String> listData = new ArrayList<String>();
		try {
			for (Document e : document) {
				listData.add((String) e.get("userName"));
				listData.add((String) e.get("fullName"));
				listData.add((String) e.get("address"));
				listData.add((String) e.get("dob"));
				listData.add((String) e.get("gender").toString());
				listData.add((String) e.get("email"));
			}
		} finally {
		}
		return listData;
	}

	public ArrayList<String> SearchByName(String userName) {
		logger1.atLevel(org.slf4j.event.Level.ERROR);
		MongoCursor<Document> document = CollectionAccount()
				.find(Filters.or(Filters.regex("fullName", userName), Filters.regex("userName", userName))).iterator();
		ArrayList<String> listData = new ArrayList<String>();
		try {
			while (document.hasNext()) {
				Document doc = document.next();
				listData.add((String) doc.get("userName"));
				listData.add((String) doc.get("fullName"));
				listData.add((String) doc.get("address"));
				listData.add((String) doc.get("dob"));
				listData.add((String) doc.get("gender").toString());
				listData.add((String) doc.get("email"));
				listData.add((String) doc.get("role"));
				listData.add((String) doc.get("id"));
			}
		} finally {
			document.close();
		}
		System.out.print(listData);
		return listData;
	}

	public ArrayList<String> SearchByID(String id) {
		logger1.atLevel(org.slf4j.event.Level.ERROR);
		Document filterDoc = new Document();
		filterDoc.append("id", id);
		MongoCursor<Document> document = CollectionAccount().find(filterDoc).iterator();
		ArrayList<String> listData = new ArrayList<String>();
		try {
			while (document.hasNext()) {
				Document doc = document.next();
				listData.add((String) doc.get("fullName"));
			}
		} finally {
			document.close();
		}
		return listData;
	}

	public ArrayList<String> FindID(String userName, String fullName, String address, String email) {
		logger1.atLevel(org.slf4j.event.Level.ERROR);
		Document filterDoc = new Document();
		filterDoc.append("userName", userName).append("fullName", fullName).append("address", address).append("email",
				email);
		MongoCursor<Document> document = CollectionAccount().find(filterDoc).iterator();
		ArrayList<String> listData = new ArrayList<String>();
		try {
			while (document.hasNext()) {
				Document doc = document.next();
				listData.add((String) doc.get("id"));
				listData.add((String) doc.get("active").toString());
				listData.add((String) doc.get("role"));
				listData.add((String) doc.get("userName"));
				listData.add((String) doc.get("fullName"));
				listData.add((String) doc.get("address"));
				listData.add((String) doc.get("dob"));
				listData.add((String) doc.get("gender").toString());
				listData.add((String) doc.get("email"));
				listData.add((String) doc.get("listFriend").toString());
				listData.add((String) doc.get("historyLogin").toString());
			}
		} finally {
			document.close();
		}
		return listData;
	}

	public void addPeopleRoom(String idRoom, String idUser) {
		ArrayList<String> document = new ArrayList<String>();
		document = (ArrayList<String>) CollectionAccount().find(eq("id", idUser)).iterator().next().get("listRoom");
		document.add(idRoom);

		CollectionAccount().updateOne(eq("id", idUser), combine(set("listRoom", document)));
	}

	public void deletePeopleRoom(String idRoom, String idDelMember) {
		ArrayList<String> document = new ArrayList<String>();
		document = (ArrayList<String>) CollectionAccount().find(eq("id", idDelMember)).iterator().next()
				.get("listRoom");

		for (int i = 0; i < document.size(); i++) {
			if (document.get(i).equals(idRoom)) {
				document.remove(i);
				break;
			}
		}

		CollectionAccount().updateOne(eq("id", idDelMember), combine(set("listRoom", document)));
	}

	public ArrayList<String> getListFriend(String idUser) {
		ArrayList<String> document = new ArrayList<String>();
		document = (ArrayList<String>) CollectionAccount().find(eq("id", idUser)).iterator().next().get("listFriend");

		return document;
	}

	public void updateListRequestAddFriend(String idUserSentRequest, String idUserRequest) {
		ArrayList<String> document = new ArrayList<String>();
		document = (ArrayList<String>) CollectionAccount().find(eq("id", idUserSentRequest)).iterator().next()
				.get("listRequestAddFriend");
		document.add(idUserRequest);

		CollectionAccount().updateOne(eq("id", idUserSentRequest), combine(set("listRequestAddFriend", document)));
	}

	public ArrayList<String> getListRequestAddFriend(String idUser) {
		ArrayList<String> document = new ArrayList<String>();
		ArrayList<String> result = new ArrayList<String>();
		document = (ArrayList<String>) CollectionAccount().find(eq("id", idUser)).iterator().next()
				.get("listRequestAddFriend");

		for (String e : document) {
			String name = SearchByID(e).get(0);
			result.add(e);
			result.add(name);
		}

		return result;
	}

	public void deleteFriendListRequest(String idUser, String idRejectUser) {
		ArrayList<String> document = new ArrayList<String>();
		document = (ArrayList<String>) CollectionAccount().find(eq("id", idUser)).iterator().next()
				.get("listRequestAddFriend");
		for (int i = 0; i < document.size(); i++) {
			if (document.get(i).equals(idRejectUser)) {
				document.remove(i);
				break;
			}
		}

		CollectionAccount().updateOne(eq("id", idUser), combine(set("listRequestAddFriend", document)));
		System.out.println("Success");
	}

	public boolean checkFriend(String idA, String idB) {
		ArrayList<String> document = new ArrayList<String>();
		document = (ArrayList<String>) CollectionAccount().find(eq("id", idA)).iterator().next().get("listFriend");

		for (int i = 0; i < document.size(); i++) {
			if (document.get(i).equals(idB)) {
				return true;
			}
		}
		return false;
	}

	public boolean checkInviteAddF(String idA, String idB) {
		ArrayList<String> document = new ArrayList<String>();
		document = (ArrayList<String>) CollectionAccount().find(eq("id", idA)).iterator().next()
				.get("listRequestAddFriend");

		for (int i = 0; i < document.size(); i++) {
			if (document.get(i).equals(idB)) {
				return true;
			}
		}
		return false;
	}

	public void addListFriend(String idUser, String idNewFriend) {
		ArrayList<String> document = new ArrayList<String>();
		document = (ArrayList<String>) CollectionAccount().find(eq("id", idUser)).iterator().next().get("listFriend");
		document.add(idNewFriend);

		CollectionAccount().updateOne(eq("id", idUser), combine(set("listFriend", document)));
		System.out.println("Success");
	}

	public void deleteFriendListFriend(String idUser, String idFriendDelete) {
		ArrayList<String> document = new ArrayList<String>();
		document = (ArrayList<String>) CollectionAccount().find(eq("id", idUser)).iterator().next().get("listFriend");

		for (int i = 0; i < document.size(); i++) {
			if (document.get(i).equals(idFriendDelete)) {
				document.remove(i);
				break;
			}
		}
		CollectionAccount().updateOne(eq("id", idUser), combine(set("listFriend", document)));
		System.out.println("Success");
	}

	public void update(String idUser, String fullName, String userName, String password, String dob, boolean gender,
			String address, String email) {
		logger1.atLevel(org.slf4j.event.Level.ERROR);
		CollectionAccount().updateOne(eq("id", idUser), combine(set("fullName", fullName), set("password", password),
				set("dob", dob), set("gender", gender), set("address", address), set("email", email)));
		System.out.println("successful");
	}

	public void blockAccount(String id, int active) {
		logger1.atLevel(org.slf4j.event.Level.ERROR);
		CollectionAccount().updateOne(eq("id", id), combine(set("active", active)));
	}

	public void updateAccount(String id, String userName, String fullName, String dob, boolean gender, String address,
			String email, String role) {
		logger1.atLevel(org.slf4j.event.Level.ERROR);
		CollectionAccount().updateOne(eq("id", id),
				combine(set("userName", userName), set("fullName", fullName), set("dob", dob), set("address", address),
						set("gender", gender), set("email", email), set("role", role)));
	}

	public void delete(String id) {
		CollectionAccount().deleteMany(eq("_id", id));
	}

	public void removeAccount(String id) {
		logger1.atLevel(org.slf4j.event.Level.ERROR);
		CollectionAccount().deleteMany(eq("id", id));
	}

	@SuppressWarnings("unchecked")
	public ArrayList<String> getListFriendOnline(String idUser) {
		ArrayList<String> documentListFriend = new ArrayList<String>();
		documentListFriend = (ArrayList<String>) CollectionAccount().find(eq("id", idUser)).iterator().next()
				.get("listFriend");

		ArrayList<String> listFriendsOnline = new ArrayList<>();
		ArrayList<String> dataName = new ArrayList<String>();
		ArrayList<String> data = new ArrayList<String>();

		List<ClientSocket> clientOnline = ClientConnected.getInstance().getClientConnected();
		for (ClientSocket e : clientOnline) {
			if (e.getID() != null) {
				if (documentListFriend.contains(e.getID())) {
					listFriendsOnline.add(e.getID());
					ArrayList<String> dataString = new ArrayList<String>();
					dataString = getFullnameToById(e.getID());
					dataName.add(dataString.get(0));
				}
			}
		}

		data.add(dataName.toString());
		data.add(listFriendsOnline.toString());

		return data;
	}

	public String updatePassword(String userName, String password) {
		CollectionAccount().updateOne(eq("userName", userName), combine(set("password", password)));
		ArrayList<String> result = SearchByName(userName);
		if (result.size() != 0) {
			return result.get(5);
		}
		return "";
	}

	public void updateHistoryLogin(String idUser) {
		ArrayList<String> document = new ArrayList<String>();
		document = (ArrayList<String>) CollectionAccount().find(eq("id", idUser)).iterator().next().get("historyLogin");

		document.add(formatDate());

		CollectionAccount().updateOne(eq("id", idUser), combine(set("historyLogin", document)));

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
