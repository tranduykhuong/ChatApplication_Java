package Server.Controller;

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

	public ArrayList<String> searchListFriend(String id) {
		ArrayList<String> resListIdFriend = new ArrayList<String>();
		ArrayList<String> resIdName = new ArrayList<String>();
		ArrayList<String> dataName = new ArrayList<String>();
		ArrayList<String> data = new ArrayList<String>();

		resListIdFriend = accountApi.searchListFriend(id);

		for (int i = 0; i < resListIdFriend.size(); i++) {
			String temp = resListIdFriend.get(i).replace("[", "").replace("]", "");

			int size = temp.split(", ").length;
			for (int j = 0; j < size; j++) {
				resIdName.add(temp.split(", ")[j]);
			}
		}

		for (int i = 0; i < resIdName.size(); i++) {
			ArrayList<String> dataString = new ArrayList<String>();
			dataString = accountApi.getFullnameToById(resIdName.get(i));
			dataName.add(dataString.get(0));
		}

		data.add(dataName.toString());
		data.add(resIdName.toString());

		return data;
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
		listDataNameGr = roomApi.getListNameGr();

		ArrayList<String> createNewGr = new ArrayList<String>();
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
			createNewGr.add("Tạo thành công");
		} else {
			createNewGr.add("Nhóm đã tồn tại!!");
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
//		ArrayList<String> listIdAdminRoom = new ArrayList<String>();
//		ArrayList<String> listIdMemberRoom = new ArrayList<String>();
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
		listIdMemberRoomAndNameRoom.add("[[" + idSender + "]]");
		listIdMemberRoomAndNameRoom.add(nameGr);

		return listIdMemberRoomAndNameRoom;
	}

	public ArrayList<String> changeNameGr(String data) {
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
			message.add("Thay đổi thành công");
		} else {
			message.add("Nhóm đã tồn tại!!");
		}

		roomApi.changeNameGr(idRoom, newName);

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

			message.add("Update thành công");
		} else {
			message.add("Đang là admin");
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

		for (int i = 0; i < newListRoombyId.get(0).split(", ").length; i++) {
			resNewListRoombyId.add(newListRoombyId.get(0).split(", ")[i].replace("[", "").replace("]", ""));
		}
		if (checkExist) {
			roomApi.updateAdmin(roomId, listNewAdmin);
			roomApi.updateMember(roomId, listNewMember);
			accountApi.updateListRoom(idUserDelete, resNewListRoombyId);
			message.add("Xóa thành công");
		} else {
			message.add("Xóa không thành công");
		}

		return message;
	}

	public ArrayList<String> addMemberGroup(String data) {
		ArrayList<String> dataMessage = new ArrayList<String>();
		ArrayList<String> fullnameId = new ArrayList<String>();
		ArrayList<String> listRoomById = new ArrayList<String>();
		ArrayList<String> listUsername = new ArrayList<String>();

		String idRoom = data.split(", ")[0].replace("[", "").replace("]", "");
		String userName = data.split(", ")[1].replace("[", "").replace("]", "");

		fullnameId = accountApi.getFullnameIdToByUsername(userName);
		listUsername = accountApi.getGetListUsername();

		if (fullnameId.size() == 0) {
			dataMessage.add("Không có thông tin bạn bè này");

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
						dataMessage.add("Thành viên này đã có trong nhóm");
					} else {
						accountApi.updateMemberIdInListRoom(id, idRoom);
						roomApi.updateMemberIdInGr(idRoom, id);
						dataMessage.add("Thêm thành công");
						dataMessage.add(fullName);
						dataMessage.add(id);
					}

					break;
				}
			}
		}

		return dataMessage;
	}
}
