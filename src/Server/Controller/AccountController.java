package Server.Controller;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Iterator;
import java.util.List;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;

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
				.append("createTime", formatDate())
				.append("active", 1)
				.append("historyLogin", historyLogin);
		CollectionAccount().insertOne(document);
		System.out.println("successful");
	}

	public void createAccount(String id, String userName, String fullName, String password, String dob, boolean gender,
			String address, String email) {

		ArrayList<String> listFriend = new ArrayList<String>();
		ArrayList<String> listRoom = new ArrayList<String>();
		ArrayList<String> listMessage = new ArrayList<String>();
		ArrayList<String> historyLogin = new ArrayList<String>();
		// boy -> gender :0
		// girl -> gender : 1
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();

		Document document = new Document("id", id).append("userName", userName).append("fullName", fullName).append("password", password)
				.append("dob", dob).append("gender", gender).append("address", address).append("email", email)
				.append("listFriend", listFriend).append("listRoom", listRoom).append("listMessage", listMessage)
				.append("createTime", formatter.format(date)).append("active", 1).append("historyLogin", historyLogin);
		CollectionAccount().insertOne(document);
		System.out.println("successful");
	}
	
	public ArrayList<String> read() {
		MongoCursor<Document> document = CollectionAccount().find().iterator();
		ArrayList<String> listData = new ArrayList<String>();
        try {
         while (document.hasNext()) {
        	 Document doc= document.next();
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
	
	public ArrayList<String> FilterByName(int status) {
		List<Document> document = CollectionAccount().find().sort(new BasicDBObject("userName".substring("userName".lastIndexOf("|") + 1), status)).into(new ArrayList<>());
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
     } finally {}
      return listData;
	}
	
	public ArrayList<String> FilterByDate(int status) {
		List<Document> document = CollectionAccount().find().sort(new BasicDBObject("createTime", status)).into(new ArrayList<>());
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
     } finally {}
      return listData;
	}
	
	public ArrayList<String> SearchByName(String userName) {
   	 	MongoCursor<Document> document = CollectionAccount().find(Filters.or(Filters.regex("fullName", userName),Filters.regex("userName", userName))).iterator();
   	 	ArrayList<String> listData = new ArrayList<String>();
	        try {
            while (document.hasNext()) {
           	 Document doc= document.next();
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
	        System.out.print(listData);
	      return listData;
	}
	
	public ArrayList<String> SearchByID(String id) {
		Document filterDoc = new Document();
    	filterDoc.append("id", id);
   	 	MongoCursor<Document> document = CollectionAccount().find(filterDoc).iterator();
   	 	ArrayList<String> listData = new ArrayList<String>();
	        try {
            while (document.hasNext()) {
           	 Document doc= document.next();
       	    listData.add((String) doc.get("fullName"));
            }
        } finally {
        	document.close();
        }
	      return listData;
	}
	
	public ArrayList<String> FindID(String userName, String fullName, String address, String email) {
		Document filterDoc = new Document();
    	filterDoc.append("userName", userName).append("fullName", fullName).append("address", address).append("email", email);
   	 	MongoCursor<Document> document = CollectionAccount().find(filterDoc).iterator();
   	 	ArrayList<String> listData = new ArrayList<String>();
	        try {
            while (document.hasNext()) 
            {
           	 Document doc= document.next();
           	 listData.add((String) doc.get("id"));
           	 listData.add((String) doc.get("active").toString());
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
	
	public ArrayList<String> listHistoryLogin() {
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
	
	public void blockAccount(String id, int active) {
		CollectionAccount().updateOne(
                eq("id", id),
                combine(set("active", active))
                );
    }
	
	public void updateAccount(String id, String userName, String fullName, String dob, boolean gender, String address, String email) {
		CollectionAccount().updateOne(
                eq("id", id),
                combine(set("userName", userName), set("fullName", fullName), set("dob", dob), set("address", address), set("gender", gender), set("email", email))
                );
    }

	public void delete(String id) {
		CollectionAccount().deleteMany(eq("_id", id));
	}
	
	public void removeAccount(String id) {
		CollectionAccount().deleteMany(eq("id", id));
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
