package Server.Controller;

import java.util.ArrayList;
import java.util.UUID;

public class InterfaceAPI {
	private AccountController accountApi = new AccountController();
	private RoomController roomApi = new RoomController();
	private MessageController messageApi = new MessageController();
	
	public String createAccount(String fullName, String userName, String password, 
			String name, String dob, boolean gender, String address, String email) {
		String id = UUID.randomUUID().toString();
		accountApi.create(id, fullName,userName, password, dob, gender, address, email);

		return id;
	}

	public void updateAccount(String idUser, String fullName, String userName, String password, 
			String name, String dob, boolean gender, String address, String email) {
		
		accountApi.update( idUser, fullName, userName,password, 
				  dob, gender, address, email ) ;
	}

	//
	public void createRoom(String name, String idUser) {
		String idRoom = UUID.randomUUID().toString();
		roomApi.create(name, idRoom, idUser);
		accountApi.addPeopleRoom(idRoom, idUser);
	}

	public void addMemberRoom(String idRoom, String idNewMember) {
		roomApi.addPeopleRoom(idNewMember, idRoom);
		accountApi.addPeopleRoom(idRoom, idNewMember);
	}

	public void deleteMemberRoom(String idRoom, String idDelMember) {
		roomApi.deletePeopleRoom(idRoom, idDelMember);
		accountApi.deletePeopleRoom(idRoom, idDelMember);
	}

	/// idUserRequest : id người gửi
	// idUserSentRequest : id người nhận
	public void addFriend(String idUserRequest, String idUserSentRequest) {
		accountApi.updateListRequestAddFriend( idUserSentRequest, idUserRequest);
	}

	public void acceptFriend(String idUser, String idNewFriend) {
		accountApi.deleteFriendListRequest( idUser, idNewFriend );
		accountApi.addListFriend(idUser, idNewFriend);
		accountApi.addListFriend(idNewFriend, idUser);
	}

	public void rejectFriend(String idUser, String idFriendReject) {
		accountApi.deleteFriendListRequest( idUser, idFriendReject );
	}

	//
	public void deleteFriend(String idUser, String idFriendDel) {
		accountApi.deleteFriendListFriend( idUser, idFriendDel );
		accountApi.deleteFriendListFriend( idFriendDel, idUser );
	}

	///
	public void sendMessageU2U( String idSender, String nameSender,  String idReceiver, String message) {
		
		if(messageApi.checkExistChatU2U(idSender, idReceiver) )
			messageApi.updateChatU2U(idSender, nameSender, idReceiver, message);
		else 
			messageApi.createChatU2U(idSender, idReceiver);	
	}
	
	public void setActive( String idUser ) {
		accountApi.updateStatusUser(idUser);
	}
	public void saveTimeLogin(String idUser) {
		accountApi.updateHistoryLogin(idUser);
	}


	public ArrayList<String> findMessage( String idSender, String idReceiver, String keyWord) {
		ArrayList<String> listResultAfterFilter = messageApi.findMessage( idSender,  idReceiver,  keyWord);
		
		for( String stringValue : listResultAfterFilter)
		{
			System.out.print(stringValue);
		}
		return listResultAfterFilter;
	}
}
