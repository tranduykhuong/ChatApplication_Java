package Server.Controller;

import java.awt.image.RescaleOp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class InterfaceAPI {
	private AccountController accountApi = new AccountController();
	private RoomController roomApi = new RoomController();
	private MessageController messageApi = new MessageController();
	
	public String createAccount(String fullName, String userName, String password, 
			String name, String dob, boolean gender, String address, String email) {
		String id = UUID.randomUUID().toString();
		accountApi.create(id, fullName,userName, password, dob, gender, address, email);
		accountApi.createAccount(id, userName, password, name, dob, gender, address, email);

		return id;
	}

	public void updateAccount(String idUser, String fullName, String userName, String password, 
			String name, String dob, boolean gender, String address, String email) {
		
		accountApi.update( idUser, fullName, userName,password, 
				  dob, gender, address, email ) ;
	}

	public ArrayList<String> updateUser(String id, String userName, String fullName, String dob, String gender, String address, String email) {
		boolean mainGD;
		ArrayList<String> res = new ArrayList<String>();
		if (gender.equals("false"))
		{
			mainGD = false;
		}
		else
		{
			mainGD = true;
		}
		accountApi.updateAccount(id, userName, fullName, dob, mainGD, address, email);
		res.add(id); res.add(userName); res.add(fullName); res.add(dob); res.add(gender); res.add(address); res.add(email);
		return res;
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
	public void addAccountSc(String id, String userName, String fullName, String password, String address, String dob, String gd, String email) {
		boolean mainGD;
		if (gd.equals("false"))
		{
			mainGD = false;
		}
		else
		{
			mainGD = true;
		}
		accountApi.createAccount(id, userName, fullName, password, dob, mainGD, address, email);
	}
	
	public void blockAccount(String userName, String fullName, String address, String email, String active) {
		int status;
		if(active.equals("0")) {
			status = 0;
		}
		else {
			status = 1;
		}
		ArrayList<String> res = new ArrayList<String>();
		res = accountApi.FindID(userName, fullName, address, email);
		String id = res.get(0);
		accountApi.blockAccount(id, status);
	}
	
	public void deleteAccount(String userName, String fullName, String address, String email) {
		ArrayList<String> res = new ArrayList<String>();
		res = accountApi.FindID(userName, fullName, address, email);
		String id = res.get(0);
		accountApi.removeAccount(id);
	}
	
	public ArrayList<String> Filter(String key, String data, int status) {
		ArrayList<String> res = new ArrayList<String>();
		switch (key) {
		case "searchByName":
			res = accountApi.SearchByName(data);
			break;
			
		case "orderbyName":
			res = accountApi.FilterByName(status);
			break;
			
		case "orderbyCreateDay":
			res = accountApi.FilterByDate(status);
			break;
			
		case "showAllInf":
			res = accountApi.read();
			break;

		default:
			break;
		}
		return res;
	}
	
	public ArrayList<String> showDetailAccount(String us, String fn, String addr, String em) {
		ArrayList<String> res = new ArrayList<String>();
		ArrayList<String> finalRes = new ArrayList<String>();
		res = accountApi.FindID(us, fn, addr, em);
		String listid = res.get(8);
		String listfriend = listid.substring(1, listid.length() - 1);
		List<String> ListFriend = new ArrayList<String>(Arrays.asList(listfriend.split(", ")));
		for(int i = 0; i <ListFriend.size();i++) {
			finalRes.add(accountApi.SearchByID(ListFriend.get(i)).toString().substring(1, accountApi.SearchByID(ListFriend.get(i)).toString().length()-1 ));
		}
		
		res.add(finalRes.toString());
		
		return res;
	}
	
}
