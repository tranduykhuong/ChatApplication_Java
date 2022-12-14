package Server.Controller;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.bson.Document;

import com.mongodb.client.MongoCursor;

import Server.Models.AccountModel;

public class AccountController extends AccountModel {
	public String formatDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		
		return   formatter.format(date);
	}
	
	public void create(String id, String fullName, String userName, String password, String dob, boolean gender,
			String address, String email) {

		ArrayList<String> listFriend = new ArrayList<String>();
		ArrayList<String> listRequestAddFriend = new ArrayList<String>();
		ArrayList<String> listRoom = new ArrayList<String>();
		ArrayList<String> listMessage = new ArrayList<String>();
		ArrayList<String> historyLogin = new ArrayList<String>();
		// boy -> gender :0
		// girl -> gender : 1

		Document document = new Document("id", id)
				.append("fullName", fullName)
				.append("userName", userName)
				.append("password", password)
				.append("dob", dob)
				.append("gender", gender)
				.append("address", address)
				.append("email", email)
				.append("listFriend", listFriend)
				.append("listRequestAddFriend", listRequestAddFriend)
				.append("listRoom", listRoom)
				.append("listMessage", listMessage)
				.append("createTime", formatDate())
				.append("active", 1)
				.append("historyLogin", historyLogin);
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
		document = (ArrayList<String>) CollectionAccount().find(eq("id", idUser)).iterator().next().get("listRoom");
		document.add(idRoom);
		
		CollectionAccount().updateOne(eq("id", idUser), combine(set("listRoom", document)));
	}
	
	public void deletePeopleRoom( String idRoom, String idDelMember) {
		ArrayList<String> document = new ArrayList<String>();
		document = (ArrayList<String>) CollectionAccount().find(eq("id", idDelMember)).iterator().next().get("listRoom");
		
		for(int i=0;i<document.size();i++)
		{
			if(document.get(i).equals(idRoom))
			{
				document.remove(i);
				break;
			}
		}
		
		CollectionAccount().updateOne(eq("id", idDelMember), combine(set("listRoom", document)));
	}
	public void updateListRequestAddFriend(String idUserSentRequest, String idUserRequest) {
		ArrayList<String> document = new ArrayList<String>();
		document = (ArrayList<String>) CollectionAccount()
				.find(eq("id", idUserSentRequest))
				.iterator().next().get("listRequestAddFriend");
		document.add(idUserRequest);
		
		CollectionAccount().updateOne(eq("id", idUserSentRequest), combine(set("listRequestAddFriend", document)));
	}
	
	public void deleteFriendListRequest( String idUser, String idRejectUser) {
		ArrayList<String> document = new ArrayList<String>();
		document = (ArrayList<String>) CollectionAccount()
				.find(eq("id", idUser))
				.iterator().next().get("listRequestAddFriend");
		for(int i=0;i<document.size();i++)
		{
			if(document.get(i).equals(idRejectUser))
			{
				document.remove(i);
				break;
			}
		}
		
		CollectionAccount().updateOne(eq("id", idUser), combine(set("listRequestAddFriend", document)));
		System.out.println("Success");
	}
	
	public void addListFriend( String idUser, String idNewFriend) {
		ArrayList<String> document = new ArrayList<String>();
		document = (ArrayList<String>) CollectionAccount()
				.find(eq("id", idUser))
				.iterator().next().get("listFriend");
		document.add(idNewFriend);
		
		CollectionAccount().updateOne(eq("id", idUser), combine(set("listFriend", document)));
		System.out.println("Success");
	}
	
	public void deleteFriendListFriend( String idUser, String idFriendDelete ) {
		ArrayList<String> document = new ArrayList<String>();
		document = (ArrayList<String>) CollectionAccount()
				.find(eq("id", idUser))
				.iterator().next().get("listFriend");
		
		for(int i=0;i<document.size();i++)
		{
			if(document.get(i).equals(idFriendDelete))
			{
				document.remove(i);
				break;
			}
		}
		CollectionAccount().updateOne(eq("id", idUser), combine(set("listFriend", document)));
		System.out.println("Success");
	}
	
	public void update(String idUser, String fullName, String userName, String password, 
			 String dob, boolean gender, String address, String email ) 
	{
		
		CollectionAccount()
		.updateOne(eq("id", idUser), 
				combine(
						set("fullName", fullName),
						set("password", password),
						set("dob", dob),
						set("gender", gender),
						set("address", address),
						set("email", email)
						));
		System.out.println("successful");
	}

	public void delete(String id) {
		CollectionAccount().deleteMany(eq("_id", id));
	}
	
	public void updateStatusUser( String idUser ) {
		Object document = new ArrayList<String>();
		document = CollectionAccount()
				.find(eq("id", idUser))
				.iterator().next().get("active");
		
		document = 1 - (int)document;
		
		CollectionAccount().updateOne(eq("id", idUser), combine(set("active", document )));
	
	}
	
	public void updateHistoryLogin( String idUser) {
		ArrayList<String> document = new ArrayList<String>();
		document = (ArrayList<String>) CollectionAccount()
				.find(eq("id", idUser))
				.iterator().next().get("historyLogin");
		
		document.add(formatDate());
		
		CollectionAccount().updateOne(eq("id", idUser), combine(set("historyLogin", document)));
	
	}

	
}
