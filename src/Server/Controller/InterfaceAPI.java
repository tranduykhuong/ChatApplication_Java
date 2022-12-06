package Server.Controller;

import java.util.UUID;

public class InterfaceAPI {
	private AccountController accountApi = new AccountController();
	private RoomController roomApi = new RoomController();

	public String createAccount(String userName, String password, String name, String dob, boolean gender,
			String address, String email) {
		String id = UUID.randomUUID().toString();
		accountApi.create(id, userName, password, name, dob, gender, address, email);

		return id;
	}

	public void updateAccount() {

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

	}

	///
	public void addFriend(String idUser, String idFriendAdd) {

	}

	public void acceptFriend(String idUser, String idNewFriend) {

	}

	public void rejectFriend(String idUser, String idFriendReject) {

	}

	//
	public void deleteFriend(String idUser, String idFriendDel) {

	}

	///
	public void saveTimeLogin(String idUser) {

	}

	///
	public void updateMessageFriend(String idUser, String idOtherUser, String idSender, String newMessage) {

	}

	public void createNewMessageFriend(String idUser, String idOtherUser, String idSender, String newMessage) {

	}

	public void findMessage() {

	}
}
