package Server.Controller;

import java.awt.image.RescaleOp;
import java.util.ArrayList;
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
	
	public ArrayList<String> showHistoryLogin() {
		ArrayList<String> res = new ArrayList<String>();
		res = accountApi.listHistoryLogin();
		return res;
	}
	
	public ArrayList<String> showGroupChatList() {
		ArrayList<String> res = new ArrayList<>();
		res = roomApi.listGroupChat();
		return res;
	}
	
	public ArrayList<String> showGroupChatListToSort() {
		ArrayList<String> res = new ArrayList<>();
		res = roomApi.sortByGroupName();
		return res;
	} 
	
	public ArrayList<String> showGroupChatListByCreateDate() {
		ArrayList<String> res = new ArrayList<>();
		res = roomApi.sortByCreateDateGroup();
		return res;
	} 
	public ArrayList<String> showMemberGroup(String groupName) {
		ArrayList<String> res = new ArrayList<>();
		ArrayList<String> listMemberInGroup = new ArrayList<>();
		res = roomApi.listMember(groupName);
		String idMemberString = res.toString();

		String str = idMemberString.substring(2, idMemberString.length() - 2);

		String[] idMember = str.split(", ");
		for(int i = 0; i < idMember.length; i++) {
			ArrayList<String> findName = new ArrayList<>();
//			System.out.println("Member " + idMember[i]);
			findName = accountApi.listAdminGroupChat(idMember[i]);
			listMemberInGroup.add(findName.get(0));
		}
		return listMemberInGroup;
	}
	
	public ArrayList<String> showAdminGroup(String groupName) {
		ArrayList<String> res = new ArrayList<>();
		ArrayList<String> listAdminInGroup = new ArrayList<>();
		res = roomApi.listAdmin(groupName);
		String idAdminString = res.toString();
		String str = idAdminString.substring(2, idAdminString.length() - 2);
		String[] idAdmin = str.split(", ");
		for(int i = 0; i < idAdmin.length; i++) {
			ArrayList<String> findName = new ArrayList<>();
			findName = accountApi.listAdminGroupChat(idAdmin[i]);
			listAdminInGroup.add(findName.get(0));
		}
		return listAdminInGroup;
	}
}
