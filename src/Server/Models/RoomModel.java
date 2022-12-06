package Server.Models;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class RoomModel {
	public MongoCollection<Document> CollectionRoom() {
		String uri = "mongodb+srv://hongtan19:0163712766cc@cluster0.hm5s93m.mongodb.net/?retryWrites=true&w=majority";
		MongoClientURI mongoClientURI = new MongoClientURI(uri);
		MongoClient mongoClient = new MongoClient(mongoClientURI);

		MongoDatabase mongoDatabase = mongoClient.getDatabase("Database");

		MongoCollection<Document> collection = mongoDatabase.getCollection("Room");
		return collection;
	}
}
