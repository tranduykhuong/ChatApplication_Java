package Server.Controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCrypt;

import Entity.Packet;
import Server.Models.ClientSocket;
import Server.Models.Email;

public class InterfaceAPI {
	private AccountController accountApi = new AccountController();
	private RoomController roomApi = new RoomController();
	private MessageController messageApi = new MessageController();

	private String formatDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();

		return formatter.format(date);
	}

	public String createAccount(String fullName, String userName, String password, String name, String dob,
			boolean gender, String address, String email, String role) {
		String id = UUID.randomUUID().toString();
		accountApi.createAccount(id, userName, password, name, dob, gender, address, email, role);

		return id;
	}

	public void updateAccount(String idUser, String fullName, String userName, String password, String name, String dob,
			boolean gender, String address, String email) {

		accountApi.update(idUser, fullName, userName, password, dob, gender, address, email);
	}

	public ArrayList<String> login(String userName, String password) {
		ArrayList<String> pwCheck = accountApi.findByUsername(userName);
		if (pwCheck.size() == 0)
			return pwCheck;

		if (accountApi.isAccountBlock(userName)) {
			ArrayList<String> result = new ArrayList<String>();
			result.add("Block");
			return result;
		}

		boolean matched = BCrypt.checkpw(password, pwCheck.get(pwCheck.size() - 3));

		if (matched) {
			accountApi.updateHistoryLogin(pwCheck.get(pwCheck.size() - 1));
			return pwCheck;
		}
		return new ArrayList<String>();
	}

	public String signup(String username, String email, String password) {
		ArrayList<String> exist = accountApi.SearchByName(username);
		if (exist.size() != 0) {
			return "Username had an account!";
		}
		String generatedSecuredPasswordHash = BCrypt.hashpw(password, BCrypt.gensalt(12));
		String id = UUID.randomUUID().toString();
		accountApi.create(id, "", username, generatedSecuredPasswordHash, "", false, "", email);
		return accountApi.SearchByName(username).toString();
	}

	public boolean forgotPassword(String username) {
		String newPW = "";
		for (int i = 0; i < 8; i++) {
			newPW += (int) (Math.random() * 10);
		}
		System.out.println(newPW);
		String generatedSecuredPasswordHash = BCrypt.hashpw(newPW, BCrypt.gensalt(12));
		String email = accountApi.updatePassword(username, generatedSecuredPasswordHash);
		if (email.length() != 0) {
			new Email("tranduykhuongit@gmail.com", email).sendEmail("RESET PASSWORD",
					"<div>" + "<h3>Reset password successfully!</h3>" + "<p>New password: <strong>" + newPW
							+ "</strong></p>" + "<p>Please use this password to login in the system...</p>" + "</div>");
			return true;
		} else {
			return false;
		}
	}

	public ArrayList<String> updateUser(String id, String userName, String fullName, String dob, String gender,
			String address, String email, String role) {
		boolean mainGD;
		String mainRole;
		ArrayList<String> res = new ArrayList<String>();
		if (gender.equals("false")) {
			mainGD = false;
		} else {
			mainGD = true;
		}

		if (role.equals("false")) {
			mainRole = "admin";
		} else {
			mainRole = "user";
		}

		accountApi.updateAccount(id, userName, fullName, dob, mainGD, address, email, mainRole);
		res.add(id);
		return res;
	}

	public String updateProfile(String id, String fullName, String address, String dob, String gender) {
		boolean mainGD;
		if (gender.equals("Nam")) {
			mainGD = false;
		} else {
			mainGD = true;
		}

		accountApi.updateProfile(id, fullName, address, dob, mainGD);
		return fullName;
	}

	//
	public void createRoom(String name, String idUser, ArrayList<String> listMembers) {
		String idRoom = UUID.randomUUID().toString();
		roomApi.create(name, idRoom, idUser, listMembers);
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

	public ArrayList<String> seeListFriend(String idUser) {
		return accountApi.getListFriend(idUser);
	}

	public String getStatusFriend(String idA, String idB) {
		if (accountApi.checkFriend(idA, idB)) {
			return "B???n b??";
		} else if (accountApi.checkInviteAddF(idA, idB)) {
			return "Y??u c???u k???t b???n";
		} else if (accountApi.checkInviteAddF(idB, idA)) {
			return "???? g???i l???i m???i";
		} else {
			return "Ch??a k???t b???n";
		}
	}

	/// idUserRequest : id ng?????i g???i
	// idUserSentRequest : id ng?????i nh???n
	public void addFriend(String idUserRequest, String idUserSentRequest) {
		accountApi.updateListRequestAddFriend(idUserSentRequest, idUserRequest);

		String data = accountApi.getFullnameToById(idUserRequest).get(0) + ", " + idUserRequest;
		ClientConnected.getInstance().sendNotify(idUserSentRequest, "addFriend", data, "");
	}

	public ArrayList<String> getListRequestAddFriend(String idUser) {
		return accountApi.getListRequestAddFriend(idUser);
	}

	public void acceptFriend(String idUser, String idNewFriend) {
		accountApi.deleteFriendListRequest(idUser, idNewFriend);
		accountApi.addListFriend(idUser, idNewFriend);
		accountApi.addListFriend(idNewFriend, idUser);

		String data = accountApi.getFullnameToById(idUser).get(0);
		ClientConnected.getInstance().sendNotify(idNewFriend, "acceptRequestFriend", data, "");
	}

	public void rejectFriend(String idUser, String idFriendReject) {
		accountApi.deleteFriendListRequest(idUser, idFriendReject);
		accountApi.deleteFriendListRequest(idFriendReject, idUser);
	}

	//
	public void deleteFriend(String idUser, String idFriendDel) {
		accountApi.deleteFriendListFriend(idUser, idFriendDel);
		accountApi.deleteFriendListFriend(idFriendDel, idUser);

		ClientConnected.getInstance().sendNotify(idFriendDel, "unFriend", "", "");
	}

	///
	public void sendMessageU2U(String idSender, String nameSender, String idReceiver, String message) {

		if (!messageApi.checkExistChatU2U(idSender, idReceiver))
			messageApi.createChatU2U(idSender, idReceiver);

		messageApi.chatU2U(idSender, nameSender, idReceiver, message);
	}

	public void sendMessageRoom(String idSender, String nameSender, String idRoom, String message) {
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<String> listMember = new ArrayList<String>();

		if (!messageApi.checkExistChatRoom(idRoom))
			messageApi.createChatRoom(idRoom);

		list = roomApi.getListIdMemberRoom(idRoom);
		String[] tmp1 = list.get(0).substring(1, list.get(0).length() - 1).split(", ");
		String[] tmp2 = list.get(1).substring(1, list.get(1).length() - 1).split(", ");

		for (String e : tmp1) {
			listMember.add(e);
		}
		for (String e : tmp2) {
			listMember.add(e);
		}

		messageApi.updateChatRoom(idSender, nameSender, idRoom, message, listMember);
	}

	public void removeMessageU2U(String idSender, String idReceiver) {
		messageApi.removeMessageU2U(idSender, idReceiver);
	}

	public void removeMessageRoom(String idSender, String idRoom) {
		messageApi.removeMessageRoom(idSender, idRoom);
	}

	public void userSeenMessage(String idUser1, String idUser2) {
		List<ClientSocket> clientOnline = ClientConnected.getInstance().getClientConnected();
		for (ClientSocket e : clientOnline) {
			if (e.getID() != null && e.getID().equals(idUser2)) {
				e.sendString(new Packet("userSeenMessage", idUser1, "").toString());
			}
		}
	}

	public String showMessageU2U(String idUser1, String idUser2) {
		if (!messageApi.checkExistChatU2U(idUser1, idUser2)) {
			messageApi.createChatU2U(idUser1, idUser2);
			return "0";
		}
		return messageApi.showHistoryMessageU2U(idUser1, idUser2);
	}

	public String showMessageRoom(String idRoom, String idUser) {
		if (!messageApi.checkExistChatRoom(idRoom)) {
			messageApi.createChatRoom(idRoom);
			return "0";
		}
		return messageApi.showHistoryMessageRoom(idRoom, idUser);
	}

	public String getWaitingMessage(String idUser) {
		ArrayList<String> listId = new ArrayList<String>();
		ArrayList<String> listName = new ArrayList<String>();
		listId = messageApi.getWaitingMessage(idUser);

		for (String e : listId) {
			String name = accountApi.getFullnameToById(e).get(0);
			listName.add(name);
		}

		return listId + "`" + listName;
	}

	public ArrayList<String> getListFriendOnline(String idUser) {
		return accountApi.getListFriendOnline(idUser);
	}

	public void saveTimeLogin(String idUser) {
		accountApi.updateHistoryLogin(idUser);
	}

	public ArrayList<String> findMessage(String idSender, String idReceiver, String keyWord, String idRoom) {
		ArrayList<String> listResultAfterFilter = messageApi.findMessageSigle(idSender, idReceiver, keyWord, idRoom);

		return listResultAfterFilter;
	}

	public ArrayList<String> findMessageAll(String idSender, String keyWord) {
		ArrayList<String> listResultAfterFilter = messageApi.findMessageAll(idSender, keyWord);

		return listResultAfterFilter;
	}

	public void addAccountSc(String id, String userName, String fullName, String password, String address, String dob,
			String gd, String email, String role) {
		boolean mainGD;
		if (gd.equals("false")) {
			mainGD = false;
		} else {
			mainGD = true;
		}
		accountApi.createAccount(id, userName, fullName, password, dob, mainGD, address, email, role);
	}

	public void blockAccount(String userName, String fullName, String address, String email, String active) {
		int status;
		if (active.equals("0")) {
			status = 0;
		} else {
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

		ArrayList<String> listFR = accountApi.getListFriendAndRoom(id);
		if (listFR.get(0).length() > 2) {
			String[] listF = listFR.get(0).substring(1, listFR.get(0).length() - 1).split(", ");
			System.out.println(listF);
			for (String idUser : listF)
				accountApi.deleteFriendListFriend(idUser, id);
		}
		if (listFR.get(1).length() > 2) {
			String[] listR = listFR.get(1).substring(1, listFR.get(0).length() - 1).split(", ");
			for (String idRoom : listR) {
				roomApi.deletePeopleRoom(idRoom, id);
				roomApi.deleteAdminRoom(idRoom, id);
			}
		}

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
		String listid = res.get(9);
		String listfriend = listid.substring(1, listid.length() - 1);
		List<String> ListFriend = new ArrayList<String>(Arrays.asList(listfriend.split(", ")));
		for (int i = 0; i < ListFriend.size(); i++) {
			finalRes.add(accountApi.SearchByID(ListFriend.get(i)).toString().substring(1,
					accountApi.SearchByID(ListFriend.get(i)).toString().length() - 1));
		}

		res.add(finalRes.toString());

		return res;
	}

	public ArrayList<String> searchListFriend(String id) {
		ArrayList<String> resListIdFriend = new ArrayList<String>();
		ArrayList<String> resIdName = new ArrayList<String>();
		ArrayList<String> dataName = new ArrayList<String>();
		ArrayList<String> data = new ArrayList<String>();

		resListIdFriend = accountApi.searchListFriend(id);

		if (resListIdFriend.get(0).equals("[]")) {
			data.add("Ch??a c?? b???n b??.");

			return data;
		} else {
			for (int i = 0; i < resListIdFriend.size(); i++) {
				String temp = resListIdFriend.get(i).replace("[", "").replace("]", "");

				int size = temp.split(", ").length;
				for (int j = 0; j < size; j++) {
					resIdName.add(temp.split(", ")[j]);
				}
			}

			for (int i = 0; i < resIdName.size(); i++) {
				ArrayList<String> dataString = new ArrayList<String>();
				dataString = accountApi.getFullnameAndRoleById(resIdName.get(i));

				if (dataString.get(1).equals("user")) {
					dataName.add(dataString.get(0));
				} else {
					resIdName.remove(i);
					i--;
				}
			}

			data.add(dataName.toString());
			data.add(resIdName.toString());

			return data;
		}
	}

	public void addMemberListRoom(String idRoom, String IdUser, String listIdNewMember) {
		accountApi.addMemberRoom(idRoom, IdUser);
		int size = listIdNewMember.split(", ").length;
		for (int i = 0; i < size; i++) {
			accountApi.addMemberRoom(idRoom, listIdNewMember.split(", ")[i]);
		}
	}

	public ArrayList<String> createGroup(String data, String idSender) {
		String nameGr = data.split("], ")[0].replace("[", "");
		String listId = data.split("], ")[1].replace("[", "").replace("]", "");

		ArrayList<String> listDataNameGr = new ArrayList<String>();
		ArrayList<String> listIdGr = new ArrayList<String>();
		ArrayList<String> createNewGr = new ArrayList<String>();

		listDataNameGr = roomApi.getListNameGr();
		boolean flag = true;
		for (int i = 0; i < listDataNameGr.size(); i++) {
			if (nameGr.equals(listDataNameGr.get(i))) {
				flag = false;
				break;
			}
		}
		if (flag) {
			String id = UUID.randomUUID().toString();
			ArrayList<String> Ids = new ArrayList<String>();
			for (int i = 0; i < listId.split(", ").length; i++) {
				Ids.add(listId.split(", ")[i]);
			}

			roomApi.create(nameGr, id, idSender, Ids);
			listIdGr = roomApi.getListIdMemberRoom(id);
			addMemberListRoom(id, idSender, listId);
			createNewGr.add("T???o th??nh c??ng");
			createNewGr.add(Ids.toString());
		} else {
			createNewGr.add("Nh??m ???? t???n t???i!!");
		}

		return createNewGr;
	}

	public ArrayList<String> returnListNameGr(String id) {
		ArrayList<String> resListIdFriend = new ArrayList<String>();
		ArrayList<String> resListNameGr = new ArrayList<String>();
		ArrayList<String> data = new ArrayList<String>();

		resListIdFriend = accountApi.getListIdGrToId(id);

		String listIdGr = resListIdFriend.get(0).replace("[", "").replace("]", "");

		for (int i = 0; i < listIdGr.split(", ").length; i++) {
			resListNameGr.add(roomApi.getListNameToGr(listIdGr.split(", ")[i]).toString());
		}

		String listNameGr = resListNameGr.toString().replace("[", "").replace("]", "");

		data.add("[" + listIdGr + "]");
		data.add("[" + listNameGr + "]");

		return data;
	}

	public ArrayList<String> listIdMemGr(String data, String idSender) {
		ArrayList<String> listIdMemAdRoom = new ArrayList<String>();
		ArrayList<String> listNameRoom = new ArrayList<String>();
		ArrayList<String> listIdMemberRoomAndNameRoom = new ArrayList<String>();
		String checkAdmin = "0";

		String nameGr = data.replace("[", "").replace("]", "").split(", ")[0];
		String idGr = data.replace("[", "").replace("]", "").split(", ")[1];

		listIdMemAdRoom = roomApi.getListIdMemberRoom(idGr);
		String listIdAdminRoom = (listIdMemAdRoom.get(0));
		String listIdMemberRoom = (listIdMemAdRoom.get(1));

		for (int i = 0; i < listIdAdminRoom.split(", ").length; i++) {
			listNameRoom.add(accountApi
					.getFullnameToById(listIdAdminRoom.split(", ")[i].replace("[", "").replace("]", "")).toString());
			if (idSender.equals(listIdAdminRoom.split(", ")[i].replace("[", "").replace("]", ""))) {
				checkAdmin = "1";
				break;
			}
		}

		for (int i = 0; i < listIdMemberRoom.split(", ").length; i++) {
			listNameRoom.add(accountApi
					.getFullnameToById(listIdMemberRoom.split(", ")[i].replace("[", "").replace("]", "")).toString());
		}

		listIdMemberRoomAndNameRoom.add(listNameRoom.toString());
		listIdMemberRoomAndNameRoom.add(listIdMemAdRoom.toString());
		listIdMemberRoomAndNameRoom.add("[[" + idGr + "]]");
		listIdMemberRoomAndNameRoom.add("[[" + checkAdmin + "]]");
		listIdMemberRoomAndNameRoom.add(nameGr);

		return listIdMemberRoomAndNameRoom;
	}

	public ArrayList<String> changeNameGr(String data, String idSender) {
		ArrayList<String> message = new ArrayList<String>();
		ArrayList<String> listNameGr = new ArrayList<String>();

		String idRoom = data.split(", ")[0].replace("[", "").replace("]", "");
		String newName = data.split(", ")[1].replace("[", "").replace("]", "");

		listNameGr = roomApi.getListNameGr();

		boolean flag = true;
		for (int i = 0; i < listNameGr.size(); i++) {
			if (newName.equals(listNameGr.get(i))) {
				flag = false;
				break;
			}
		}
		if (flag) {
			message.add("Thay ?????i th??nh c??ng");
			roomApi.changeNameGr(idRoom, newName);

			ArrayList<String> list = new ArrayList<String>();
			ArrayList<String> listMember = new ArrayList<String>();
			list = roomApi.getListIdMemberRoom(idRoom);
			String[] tmp1 = list.get(0).substring(1, list.get(0).length() - 1).split(", ");
			String[] tmp2 = list.get(1).substring(1, list.get(1).length() - 1).split(", ");

			for (String e : tmp1) {
				listMember.add(e);
			}
			for (String e : tmp2) {
				listMember.add(e);
			}

			for (String member : listMember) {
				List<ClientSocket> clientOnline = ClientConnected.getInstance().getClientConnected();
				for (ClientSocket e : clientOnline) {
					if (e.getID() != null && e.getID().equals(member) && !e.getID().equals(idSender)) {
						e.sendString(new Packet("changeNameRoom", newName, idSender).toString());
					}
				}
			}
		} else {
			message.add("Nh??m ???? t???n t???i!!");
		}

		return message;
	}

	public ArrayList<String> updateAdmin(String data) {
		ArrayList<String> listAdminFromDTS = new ArrayList<String>();
		ArrayList<String> listNewMember = new ArrayList<String>();
		ArrayList<String> listNewAdmin = new ArrayList<String>();
		ArrayList<String> message = new ArrayList<String>();

		String roomId = data.split(", ")[0].replace("[", "");
		String idUserUpdateAdmin = data.split(", ")[1].replace("[", "").replace("]", "");
		boolean checkNotAdmin = true;

		listAdminFromDTS = roomApi.getListIdMemberRoom(roomId);
		String listAdmins = listAdminFromDTS.get(0).replace("[", "").replace("]", "");
		String listMembers = listAdminFromDTS.get(1).replace("[", "").replace("]", "");

		int sizeAdmin = listAdmins.split(", ").length;
		for (int i = 0; i < sizeAdmin; i++) {
			if (idUserUpdateAdmin.equals(listAdmins.split(", ")[i])) {
				checkNotAdmin = false;
			}
		}

		if (checkNotAdmin) {
			int sizeMember = listMembers.split(", ").length;
			for (int i = 0; i < sizeMember; i++) {
				if (idUserUpdateAdmin.equals(listMembers.split(", ")[i]))
					continue;
				listNewMember.add(listMembers.split(", ")[i]);
			}

			roomApi.updateMember(roomId, listNewMember);

			for (int i = 0; i < sizeAdmin; i++) {
				listNewAdmin.add(listAdmins.split(", ")[i]);
			}
			listNewAdmin.add(idUserUpdateAdmin);

			roomApi.updateAdmin(roomId, listNewAdmin);

			message.add("Update th??nh c??ng");
		} else {
			message.add("??ang l?? admin");
		}

		return message;
	}

	public ArrayList<String> removeMember(String data) {
		ArrayList<String> listAdminFromDTS = new ArrayList<String>();
		ArrayList<String> listNewMember = new ArrayList<String>();
		ArrayList<String> listNewAdmin = new ArrayList<String>();
		ArrayList<String> listRoombyId = new ArrayList<String>();
		ArrayList<String> newListRoombyId = new ArrayList<String>();
		ArrayList<String> resNewListRoombyId = new ArrayList<String>();
		ArrayList<String> message = new ArrayList<String>();

		String roomId = data.split(", ")[0].replace("[", "");
		String idUserDelete = data.split(", ")[1].replace("[", "").replace("]", "");
		boolean checkExist = false;

		listAdminFromDTS = roomApi.getListIdMemberRoom(roomId);
		String listAdmins = listAdminFromDTS.get(0).replace("[", "").replace("]", "");
		String listMembers = listAdminFromDTS.get(1).replace("[", "").replace("]", "");

		int sizeAdmin = listAdmins.split(", ").length;
		for (int i = 0; i < sizeAdmin; i++) {
			if (idUserDelete.equals(listAdmins.split(", ")[i]))
				continue;
			listNewAdmin.add(listAdmins.split(", ")[i]);
		}

		int sizeMember = listMembers.split(", ").length;
		for (int i = 0; i < sizeMember; i++) {
			if (idUserDelete.equals(listMembers.split(", ")[i]))
				continue;
			listNewMember.add(listMembers.split(", ")[i]);
		}

		listRoombyId = accountApi.getListIdGrToId(idUserDelete);

		for (int i = 0; i < listRoombyId.get(0).split(", ").length; i++) {
			if (roomId.equals(listRoombyId.get(0).split(", ")[i].replace("[", "").replace("]", ""))) {
				checkExist = true;
				continue;
			}
			newListRoombyId.add(listRoombyId.get(0).split(", ")[i].replace("[", "").replace("]", ""));
		}

		if (newListRoombyId.size() == 0) {
			resNewListRoombyId = newListRoombyId;
		} else {
			for (int i = 0; i < newListRoombyId.get(0).split(", ").length; i++) {
				resNewListRoombyId.add(newListRoombyId.get(0).split(", ")[i].replace("[", "").replace("]", ""));
			}
		}
		if (checkExist) {
			roomApi.updateAdmin(roomId, listNewAdmin);
			roomApi.updateMember(roomId, listNewMember);
			accountApi.updateListRoom(idUserDelete, resNewListRoombyId);
			message.add("X??a th??nh c??ng");
		} else {
			message.add("X??a kh??ng th??nh c??ng");
		}

		return message;
	}

	public ArrayList<String> addMemberGroup(String data, String idSender) {
		ArrayList<String> dataMessage = new ArrayList<String>();
		ArrayList<String> fullnameId = new ArrayList<String>();
		ArrayList<String> listRoomById = new ArrayList<String>();
		ArrayList<String> listUsername = new ArrayList<String>();

		String idRoom = data.split(", ")[0].replace("[", "").replace("]", "");
		String userName = data.split(", ")[1].replace("[", "").replace("]", "");

		fullnameId = accountApi.getFullnameIdToByUsername(userName);
		listUsername = accountApi.getGetListUsername();

		if (fullnameId.size() == 0) {
			dataMessage.add("Kh??ng c?? th??ng tin b???n b?? n??y");

			return dataMessage;
		} else {
			for (int i = 0; i < listUsername.size(); i++) {
				if (userName.equals(listUsername.get(i))) {
					String fullName = fullnameId.get(0);
					String id = fullnameId.get(1);
					boolean checkExist = false;

					listRoomById = accountApi.getListIdGrToId(id);

					for (int j = 0; j < listRoomById.get(0).split(", ").length; j++) {
						if (idRoom.equals(listRoomById.get(0).split(", ")[j].replace("[", "").replace("]", ""))) {
							checkExist = true;
							break;
						}
					}

					if (checkExist) {
						dataMessage.add("Th??nh vi??n n??y ???? c?? trong nh??m");
					} else {
						accountApi.updateMemberIdInListRoom(id, idRoom);
						roomApi.updateMemberIdInGr(idRoom, id);
						dataMessage.add("Th??m th??nh c??ng");
						dataMessage.add(fullName);
						dataMessage.add(id);
					}
					break;
				}
			}
		}

		return dataMessage;
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
		for (int i = 0; i < idMember.length; i++) {
			ArrayList<String> findName = new ArrayList<>();

			findName = accountApi.listAdminGroupChat(idMember[i]);
			listMemberInGroup.add(!findName.isEmpty() ? findName.get(0) : " ");
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
		for (int i = 0; i < idAdmin.length; i++) {
			ArrayList<String> findName = new ArrayList<>();
			findName = accountApi.listAdminGroupChat(idAdmin[i]);
			listAdminInGroup.add(!findName.isEmpty() ? findName.get(0) : " ");
		}
		return listAdminInGroup;
	}
}
