package Server.Controller;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;

import Server.Models.MessageModel;

public class MessageController extends MessageModel{
	
	
	public String idRoomChatU2U( String idSender, String idReceiver) {
		return  idSender.compareTo(idReceiver)>0?
						idSender+idReceiver:idReceiver+idSender;
	}
	public String formatDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		
		return   formatter.format(date);
	}
	
	///////////////
	public boolean checkExistChatU2U(String idSender, String idReceiver) {
		String idRoomChat = 
				idSender.compareTo(idReceiver)>0?
						idSender+idReceiver:idReceiver+idSender;


		FindIterable<Document> documents = CollectionMessage().find(eq("id", idRoomChat));

		MongoCursor<Document> cursor = documents.iterator();
		if (cursor.hasNext()) {
			return true;
		}
		return false;
		//return document==null ? false : true;
	}
	
	public boolean checkExistChatRoom() {
		return true;
	}
	
	// create the chat room of  user and other user
	public void createChatU2U(String idSender, String idReceiver) {
		ArrayList<Document> listMessage = new ArrayList<Document>();
		ArrayList<Document> listUserHiddenMessage = new ArrayList<Document>();
		ArrayList<Document> listUserDeleteMessage = new ArrayList<Document>();
	
		
//		Document itemMessage = new Document("id", 1)
//				.append("idSender", idSender)
//				.append("nameSender", nameSender)
//				.append("message", firstMessage)
//				.append("date", formatter.format(date));
//		listMessage.add(itemMessage);
		
		// idRoomChat = StringGreater + StringLessx
		
		String idRoomChat = idRoomChatU2U(idSender, idReceiver);
		
		System.out.println("Id Room chat : " + idRoomChat);
		Document document = new Document("id", idRoomChat)
							.append("listUserHiddenMessage", listUserHiddenMessage)
							.append("listUserDeleteMessage", listUserDeleteMessage)
							.append("listMessage", listMessage)
							.append("date", this.formatDate());
			
		CollectionMessage().insertOne(document);
		System.out.println("successful");
	}
	// create the chat room of  users
	public void createChatRoom(String idRoom) {
		
		ArrayList<Document> listMessage = new ArrayList<Document>();
		ArrayList<Document> listUserHiddenMessage = new ArrayList<Document>();
		ArrayList<Document> listUserDeleteMessage = new ArrayList<Document>();
		
		
		Document document = new Document("id", idRoom)
				.append("listUserHiddenMessage", listUserHiddenMessage)
				.append("listUserDeleteMessage", listUserDeleteMessage)
				.append("itemMessage", listMessage)
				.append("date",  this.formatDate());
		CollectionMessage().insertOne(document);
	}
	
	public void updateChatU2U(String idSender, String nameSender, String idReceiver, String message) {
		
		
		ArrayList<Document> document = new ArrayList<Document>();
		Document itemMessage = new Document("id", 1)
		.append("idSender", idSender)
		.append("nameSender", nameSender)
		.append("message", message)
		.append("date", this.formatDate());
	
		
		String idRoomChat = idRoomChatU2U(idSender, idReceiver);
				
		document = (ArrayList<Document>) CollectionMessage()
				.find(eq("id", idRoomChat))
				.iterator().next().get("listMessage");
		document.add(itemMessage);
		
		CollectionMessage().updateOne(eq("id", idRoomChat), combine(set("listMessage", document)));
		System.out.println("Success");
	}
	public void updateChatRoom() {
		
	}
	public void updateListHidden() {
		
	}
	
	public ArrayList<String> findMessage(String idSender, String idReceiver, String keyword)
	{
		ArrayList<Document> document = new ArrayList<Document>();
		String idRoomChat = idRoomChatU2U(idSender,idReceiver);
		document = (ArrayList<Document>) CollectionMessage()
				.find(eq("id", idRoomChat))
				.iterator().next().get("listMessage");
		
		ArrayList<String> listResultFilter = new ArrayList<String>();
		
		for(int i=0;i<document.size();i++)
		{
			if(((String) document.get(i).get("message")).contains(keyword))
				listResultFilter.add(((String) document.get(i).get("message")));
		}
		
		System.out.println("Success");
		return listResultFilter;
	}
}
