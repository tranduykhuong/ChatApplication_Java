package Client;

import javax.naming.ldap.ManageReferralControl;

public class Controller {
	private static Controller tis;
    private  TCP_Client client;
    private String hostName;
    private int port;
    private int downloadPort;
//    private IMessageReceiverListener listener;

    
	private  Controller() {
        client = new TCP_Client();
    }
	
//	public static Controller getTis() {
//		return tis;
//	}
	
	public static void setTis(Controller tis) {
		Controller.tis = tis;
	}
	
	public static Controller getInstance() {
        if (tis == null) {
            tis = new Controller();
        }
        return tis;
    }
	
	public boolean connect(String hostname, int port) {
        this.hostName = hostname;
        this.port = port;
        return client.ConnectToServer(hostname, port); //Create "connect" function in TCP_Client
    }
	
	public void startListen() {
        new Thread(() -> {
            while (true) {
//                if (listener == null)
//                    break;
                var msg = client.readString(); //Create "readString" function in TCP_Client
                if (msg == null)
                    break;
                // var message = new Message(msg); // Create "Message" class in Client package
//                listener.process(message);
            }
        }).start();
    }
	
	public void sendTextMessage(String content) {
        // var textmessage = new TextMessage(content);  //entity.message.TextMessage;
        // var message = new Message(MessageType.TEXT, textmessage, ""); //entity.MessageType
        // client.sendString(message.toString()); 
    }
	
	public void reconnect() {
        client.disconnect(); //Create "disconnect" function in TCP_Client
        client.ConnectToServer(hostName, port);
    }
	
	public boolean login(String username, String password) {
        client.sendString("1\t" + username + "\t" + password);
        if (client.readString().equals("1")) {
            downloadPort = Integer.valueOf((String) client.readString());
            return true;
        }
        return false;
    }
	
	public boolean register(String username, String password) {
        client.sendString("0\t" + username + "\t" + password);
        return client.readString().equals("1");
    }
//	public IMessageReceiverListener getListener() {
//        return listener;
//    }
//	public void setListener(IMessageReceiverListener listener) {
//        this.listener = listener;
//    }
	
	

}
