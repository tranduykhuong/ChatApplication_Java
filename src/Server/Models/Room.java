package Server.Models;
import Server.Controller.*;
import org.bson.Document;

import com.mongodb.client.MongoCursor;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;
import java.text.SimpleDateFormat;  
import java.util.Date;  

public class Room  extends RoomController{
	 public void create( String name,String idRoom, String idUser) {
		    
	     ArrayList<String> listMember = new ArrayList<String>();
	     ArrayList<String> listAdmins = new ArrayList<String>();
	     listAdmins.add(idUser);
	     ArrayList<String> listMessage = new ArrayList<String>();
	     
	     SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
	     Date date = new Date(); 
	     
		 Document document = new Document("id",idRoom)
	        		.append("listMember",listMember)
	        		.append("listAdmins",listAdmins)
	        		.append("listMessage",listMessage)
	        		.append("createTime", formatter.format(date))
	        		.append("listMessage",listMessage);
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
	    
	    public void addPeopleRoom(String idUser, String idRoom)
	    {
	    	ArrayList<String> document = new ArrayList<String>();
	    	document= (ArrayList<String>) CollectionRoom().find().iterator().next().get("listMember");
	    	document.add(idUser);
	    	CollectionRoom().updateOne(eq("id",idRoom), combine(set("listMember",document)));

	    }
	    public void update() {
	    	CollectionRoom().updateOne(
	                eq("_id", "23"),
	                combine(set("name", "23"))
	                );
	        System.out.println("successful");
	    }

	    public void delete(String id) {
	    	CollectionRoom().deleteMany(eq("_id", id));
	    }
}
