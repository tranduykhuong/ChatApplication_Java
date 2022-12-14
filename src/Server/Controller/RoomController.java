package Server.Controller;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.bson.Document;

import com.mongodb.client.MongoCursor;

import Server.Models.RoomModel;

public class RoomController extends RoomModel {
	public void create(String name, String idRoom, String idUser) {

		ArrayList<String> listMember = new ArrayList<String>();
		ArrayList<String> listAdmins = new ArrayList<String>();
		listAdmins.add(idUser);
		ArrayList<String> listMessage = new ArrayList<String>();

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();

		Document document = new Document("id", idRoom)
				.append("name", name)
				.append("listMember", listMember)
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

	public void addPeopleRoom(String idUser, String idRoom) {
		ArrayList<String> document = new ArrayList<String>();
		document = (ArrayList<String>) CollectionRoom().find().iterator().next().get("listMember");
		document.add(idUser);
		CollectionRoom().updateOne(eq("id", idRoom), combine(set("listMember", document)));

	}

	public void update() {
		CollectionRoom().updateOne(eq("_id", "23"), combine(set("name", "23")));
		System.out.println("successful");
	}

	public void deletePeopleRoom(String idRoom, String idDelMember) {
		ArrayList<String> document = new ArrayList<String>();
		document = (ArrayList<String>) CollectionRoom()
				.find(eq("id", idRoom))
				.iterator().next().get("listMember");
		for(int i=0;i<document.size();i++)
		{
			if(document.get(i).equals(idDelMember))
			{
				document.remove(i);
				break;
			}
		}
		
		CollectionRoom().updateOne(eq("id", idRoom), combine(set("listMember", document)));
		System.out.println("Success");
	}
}
