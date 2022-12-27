package Client;

import java.text.Collator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.swing.JOptionPane;

import Client.Views.AddGroupScreen;
import Client.Views.ChatApplicationScreen;
import Client.Views.ForgotPWScreen;
import Client.Views.GroupChatList;
import Client.Views.GroupChatScreen;
import Client.Views.HomeScreen;
import Client.Views.LoginList;
import Client.Views.LoginScreen;
import Client.Views.ManageUsersList;
import Client.Views.RegisterScreen;
import Client.Views.User;
import javax.xml.crypto.Data;

import Client.Views.GroupChatList;
import Client.Views.LoginList;
import Entity.Packet;

public class Controller {
	private static Controller sgt;
	private TCP_Client client;
	private String hostName;
	private int port;
	private Boolean running;
	private User userframe;

	private String data;
	private GroupChatList groupChatLists = new GroupChatList();

	private AddGroupScreen addGrScreen = new AddGroupScreen();
	private ChatApplicationScreen chatAppScreen = new ChatApplicationScreen();
	private GroupChatScreen grChat = new GroupChatScreen();
	private ManageUsersList MNUserList = new ManageUsersList();

	private LoginScreen loginScreen = new LoginScreen();
	private RegisterScreen registerScreen = new RegisterScreen();
	private HomeScreen homeScreen = new HomeScreen();
	private ForgotPWScreen forgotScreen = new ForgotPWScreen();

	private String username;
	private String id;
	private String fullname;

	private Controller() {
		client = new TCP_Client();
		running = false;
	}

	public static Controller getInstance() {
		if (sgt == null) {
			sgt = new Controller();
		}
		return sgt;
	}

	public boolean connect(String hostname, int port) {
		this.hostName = hostname;
		this.port = port;
		if (client.ConnectToServer(hostname, port)) {
			running = true;
			return running;
		}
		return false;
	}

	public void startListen() {
		new Thread(() -> {
			while (true) {
				if (running == false)
					break;
				var msg = client.readString();
				System.out.println("Client receive: " + msg);

				if (msg == null)
					break;

				if (msg.equals("Server closed!")) {
					ClientApp.connectionScreen.handleDisconnect();
					ClientApp.connectionScreen.setVisible(true);
					disconnect();
					break;
				}

				Packet pk = new Packet(msg);
				String header = pk.getHeader();

				System.out.println("Header client receive: " + header);
				switch (header) {
					case "filterList": {
						data = pk.getData();
						String replace1 = data.substring(1, data.lastIndexOf("]"));
						List<String> myList = new ArrayList<String>(Arrays.asList(replace1.split(", ")));
						MNUserList.showInfor(myList);
						break;
					}
					case "showAll": {
						data = pk.getData();
						String replace1 = data.substring(1, data.lastIndexOf("]"));
						List<String> myList = new ArrayList<String>(Arrays.asList(replace1.split(", ")));
						System.out.println("List packet display:" + myList);
						MNUserList.showInfor(myList);
						break;
					}
					case "orderName": {
						data = pk.getData();
						String replace1 = data.substring(1, data.lastIndexOf("]"));
						List<String> myList = new ArrayList<String>(Arrays.asList(replace1.split(", ")));
						MNUserList.showInfor(myList);
						break;
					}
					case "orderCreateDate": {
						data = pk.getData();
						String replace1 = data.substring(1, data.lastIndexOf("]"));
						List<String> myList = new ArrayList<String>(Arrays.asList(replace1.split(", ")));
						MNUserList.showInfor(myList);
						break;
					}
					case "showDetail": {
						data = pk.getData();
						String replace1 = data.substring(1, data.lastIndexOf("]"));
						List<String> myList = new ArrayList<String>(Arrays.asList(replace1.split(", ")));

						String id = myList.get(0);
						String active = myList.get(1);
						String usname = myList.get(2);
						String fname = myList.get(3);
						String addr = myList.get(4);
						String dob = myList.get(5);
						String gender = myList.get(6);
						String email = myList.get(7);

						String tmpKo = data.substring(1, data.lastIndexOf("]"));
						String tmp = tmpKo.substring(tmpKo.indexOf("[", tmpKo.indexOf("[")) + 1, tmpKo.lastIndexOf("]"));
						String tmp1 = tmp.substring(tmp.indexOf("[") + 1, tmp.length());
						String listFriend = tmp1.substring(tmp1.indexOf("[") + 1, tmp1.length());

						String historyLg = tmp1.substring(0, tmp1.indexOf("]"));

						userframe = new User(id, usname, fname, addr, dob, gender, email);
						userframe.setVisible(true);
						userframe.showInformation(active, usname, fname, addr, dob, gender, email, listFriend, historyLg);
					}
					case "addAccount": {
						data = pk.getData();
						break;
					}
					case "updateAccount": {
						data = pk.getData();
						break;
					}
					case "removeAccount": {
						data = pk.getData();
						break;
					}
					case "lockAccount": {
						break;
					}
					case "historyLogin": {
						break;
					}
					case "listFriend": {
						break;
					}
					case "listLoginTime": {
						String dataString = pk.getData();
						ArrayList<ArrayList<String>> historyLogin = new ArrayList<>();
						ArrayList<List<String>> tmpHistoryLogin = new ArrayList<List<String>>();
						ArrayList<String> historyLoginArrayList = new ArrayList<String>();
						String str = dataString.substring(1, dataString.length() - 1);
						// System.out.println("Vinh" + str);
						String[] userList = str.split("\\], ");
						userList[userList.length - 1] = userList[userList.length - 1].substring(0,
								userList[userList.length - 1].length() - 1);
						for (String user : userList) {
							ArrayList<String> userInfo = new ArrayList<String>();
							String[] temp = user.split(", \\[");
							userInfo.add(temp[0].split(", ").length >= 1 ? temp[0].split(", ")[0] : "");
							userInfo.add(temp[0].split(", ").length > 1 ? temp[0].split(", ")[1] : "");
							userInfo.add(temp[1]);
							historyLogin.add(userInfo);
						}
						System.out.println(historyLogin);
						for (int i = 0; i < historyLogin.size(); i++) {
							String usname = historyLogin.get(i).get(0);
							String fname = historyLogin.get(i).get(1);

							ArrayList<String> listLogin = new ArrayList<>();
							ArrayList<String> finals = new ArrayList<>();

							for (int j = 2; j < historyLogin.get(i).size(); j++) {

								listLogin.add(historyLogin.get(i).get(j));
								String history = historyLogin.get(i).get(j);
								List<String> tmpLogin = new ArrayList<>(Arrays.asList(history.split(", ")));

								for (int l = 0; l < tmpLogin.size(); l++) {
									ArrayList<String> Detail = new ArrayList<>();
									Detail.add(usname);
									Detail.add(fname);
									Detail.add(tmpLogin.get(l));
									finals.add(Detail.toString().substring(1, (Detail.toString().length() - 1)));

									List<String> tmpKo = new ArrayList<>(Arrays.asList(finals.get(l).split(", ")));
									tmpHistoryLogin.add(tmpKo);
								}
							}
						}
						for (int i1 = 0; i1 < tmpHistoryLogin.size() - 1; i1++) {
							SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
							String startDate = tmpHistoryLogin.get(i1).get(2);
							String endDate = tmpHistoryLogin.get(i1 + 1).get(2);

							try {
								if (sdf.parse(startDate).after(sdf.parse(endDate))) {
									Collections.swap(tmpHistoryLogin, i1, i1 + 1);
								}
							} catch (ParseException e1) {
								e1.printStackTrace();
							}
						}

						for (int i = 0; i < tmpHistoryLogin.size(); i++) {
							for (int j = 0; j < 3; j++) {
								historyLoginArrayList.add(tmpHistoryLogin.get(i).get(j));
							}
						}
						System.out.println(historyLoginArrayList);
						LoginList historyLoginList = new LoginList();
						historyLoginList.showHistoryLoginList(historyLoginArrayList);
						historyLoginList.setVisible(true);
						break;
					}
					case "listGroupChat": {
						String dataString = pk.getData();
						String str = dataString.substring(1, dataString.length() - 1);
						ArrayList<String> nameGroup = new ArrayList<>();
						String[] infoGroup = str.split(", ");

						for (int i = 0; i < infoGroup.length; i++) {
							// System.out.println(infoGroup[i]);
							nameGroup.add(infoGroup[i]);
						}

						groupChatLists.showGroupChatList(nameGroup);
						groupChatLists.setVisible(true);
						break;
					}
					case "showMemberList": {
						String data = pk.getData();
						String str = data.substring(1, data.length() - 1);
						ArrayList<String> nameMemberList = new ArrayList<>();
						String[] infoMember = str.split(", ");

						for (int i = 0; i < infoMember.length; i++) {
							nameMemberList.add(infoMember[i]);
						}
						groupChatLists.showMemberList(nameMemberList);
						groupChatLists.setVisible(true);
						break;
					}
					case "showAdminList": {
						String data = pk.getData();
						String str = data.substring(1, data.length() - 1);
						ArrayList<String> nameAdminList = new ArrayList<>();
						String[] infoAdmin = str.split(", ");

						for (int i = 0; i < infoAdmin.length; i++) {
							nameAdminList.add(infoAdmin[i]);
						}
						groupChatLists.showAdminList(nameAdminList);
						groupChatLists.setVisible(true);
						break;
					}
					case "sortByGroupName": {
						String dataString = pk.getData();
						String str = dataString.substring(1, dataString.length() - 1);
						System.out.println("Vinh: " + str);
						ArrayList<String> nameGroupSorted = new ArrayList<>();
						String[] infoGroup = str.split(", ");

						for (int i = 0; i < infoGroup.length; i++) {
							nameGroupSorted.add(infoGroup[i]);
						}
						System.out.println("Before sorted: " + nameGroupSorted);
						// Collections.sort(nameGroupSorted);
						Collections.sort(nameGroupSorted, Collator.getInstance());
						System.out.println("After sorted: " + nameGroupSorted);

						for (int i = 0; i < nameGroupSorted.size(); i++) {
							System.out.println(nameGroupSorted.get(i));
						}
						groupChatLists.showGroupChatListSortedByName(nameGroupSorted);
						groupChatLists.setVisible(true);
						break;
					}
					case "sortByCreateDate": {
						String dataString = pk.getData();
						String str = dataString.substring(1, dataString.length() - 1);
						ArrayList<String> infoCreatedGroupSorted = new ArrayList<>();
						ArrayList<String> finalSortGroupList = new ArrayList<>();
						String[] infoGroupDate = str.split(", ");
						ArrayList<List<String>> listCreateDateGroup = new ArrayList<>();
						for (int i = 0; i < infoGroupDate.length; i++) {
							infoCreatedGroupSorted.add(infoGroupDate[i] + ", " + infoGroupDate[++i]);
						}

						for (int i = 0; i < infoCreatedGroupSorted.size(); i++) {
							List<String> myList = new ArrayList<String>(
									Arrays.asList(infoCreatedGroupSorted.get(i).split(", ")));
							listCreateDateGroup.add(myList);
						}

						for (int i1 = 0; i1 < listCreateDateGroup.size() - 1; i1++) {
							SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
							String startDate = listCreateDateGroup.get(i1).get(1);
							String endDate = listCreateDateGroup.get(i1 + 1).get(1);
							try {
								if (!sdf.parse(startDate).before(sdf.parse(endDate))) {
									Collections.swap(listCreateDateGroup, i1, i1 + 1);
								}
							} catch (ParseException e1) {
								e1.printStackTrace();
							}
						}

						for (int i = 0; i < listCreateDateGroup.size(); i++) {
							finalSortGroupList.add(listCreateDateGroup.get(i).get(0));
						}
						groupChatLists.showGroupChatListSortedByCreateDate(finalSortGroupList);
						groupChatLists.setVisible(true);
						break;
					}
					case "signUp": {
						String data = pk.getData();
						if (data.equals("Username had an account!")) {
							registerScreen.showMessage(data, "Warning", JOptionPane.WARNING_MESSAGE);
						} else {
							String[] dataArr = data.substring(1, data.length() - 1).split(", ");
							this.username = dataArr[0];
							this.id = dataArr[dataArr.length - 1];
							registerScreen.showMessage("Register successfully!", "Success",
									JOptionPane.INFORMATION_MESSAGE);
							registerScreen.setVisible(false);
							chatAppScreen.setVisible(true);
						}
						break;
					}
					case "logIn": {
						System.out.println(pk.getData());
						String data = pk.getData();
						if (data.equals("Username or password is wrong!")) {
							loginScreen.showMessage(data, "Warning", JOptionPane.WARNING_MESSAGE);
						} else {
							String[] dataArr = data.substring(1, data.length() - 1).split(", ");
							this.username = dataArr[0];
							this.fullname = dataArr[1];
							this.id = dataArr[dataArr.length - 1];
							loginScreen.showMessage("Login successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
							loginScreen.setVisible(false);
							if (dataArr[dataArr.length - 2].equals("user"))
								loginScreen.controllShowListGroup(this.id);
							// chatAppScreen.setVisible(true);
							else {
								MNUserList.setVisible(true);
								MNUserList.run();
							}
						}
						break;
					}
					case "forgotPassword": {
						String data = pk.getData();
						if (data.equals("success")) {
							forgotScreen.showMessage("New password sent to your email!", "Success",
									JOptionPane.INFORMATION_MESSAGE);
							forgotScreen.setVisible(false);
							loginScreen.setVisible(true);
						} else {
							forgotScreen.showMessage("Send password to your email was failed! Please again!", "Error",
									JOptionPane.ERROR_MESSAGE);
						}
						break;
					}
					case "addFriend": {
						break;
					}
					case "unFriend": {
						break;
					}
					case "listFriendOnline": {
						break;
					}
					case "chatWithFriendOnline": {
						break;
					}
					case "chatWithFriendOffline": {
						break;
					}
					case "viewChatHistory": {
						break;
					}
					case "romoveChatHistory": {
						break;
					}
					case "searchStringChatSingle": {
						break;
					}
					case "searchStringChatMultiple": {
						break;
					}
					case "showListGr": {
						data = pk.getData();
						String id = pk.getAuthor();

						chatAppScreen.setId(id);
						chatAppScreen.destroyChatAppForm();
						chatAppScreen.setVisible(true);
						chatAppScreen.showListNameGr(data);
						break;
					}
					case "showListFriend": {
						data = pk.getData();

						addGrScreen.setID(this.id);
						addGrScreen.destroyAddGroupScreen();
						String listIdAndName = data.substring(1, data.length() - 1);
						if (listIdAndName.split("], ").length > 1) {
							addGrScreen.setVisible(true);
							addGrScreen.showlistFriend(listIdAndName);
						} else {
							addGrScreen.checkMessage(listIdAndName);
						}
						break;
					}
					case "createGroup": {
						data = pk.getData();

						String notifyCreateGroup = data.replace("[", "").replace("]", "");
						if (notifyCreateGroup.equals("Tạo thành công")) {
							addGrScreen.checkMessage(notifyCreateGroup);
							addGrScreen.setVisible(false);
							addGrScreen.destroyAddGroupScreen();
							// chatAppScreen.destroyChatAppForm();
							chatAppScreen.refeshDataForm();
						} else {
							addGrScreen.checkMessage(notifyCreateGroup);
						}

						break;
					}
					case "showListMemberRoom": {
						data = pk.getData();

						String res = data.substring(data.indexOf("[") + 1, data.length() - 1);
						String listNameMember = res.split("]], ")[0].replace("[", "").replace("]", "");
						String listIDMemberAdmin = res.split("]], ")[1];
						String idGrSelected = res.split("]], ")[2].replace("[", "").replace("]", "");
						String isAdmin = res.split("]], ")[3].replace("[", "").replace("]", "");
						String nameGr = res.split("]], ")[4];

						String listIdAdmin = listIDMemberAdmin.split("], ")[0].replace("[", "").replace("]", "");
						String listIdMember = listIDMemberAdmin.split("], ")[1].replace("[", "").replace("]", "");

						grChat.destroyGroupChatScreen();
						grChat.setId(this.id);
						grChat.setListNameMember(listNameMember);
						grChat.setListIdAdmin(listIdAdmin);
						grChat.setListIdMember(listIdMember);
						grChat.setThisIdGr(idGrSelected);
						grChat.setIsAmin(isAdmin);
						grChat.setNameGroup(nameGr);
						grChat.setVisible(true);
						grChat.setTitle(nameGr);
						grChat.showInfoGroupChat();
						break;
					}
					case "changeNameGroup": {
						data = pk.getData();

						String notifyChangeNameGr = data.replace("[", "").replace("]", "");
						if (notifyChangeNameGr.equals("Thay đổi thành công")) {
							grChat.checkMessage(notifyChangeNameGr);
							grChat.setTitle(grChat.getNameGr());
							grChat.destroyName();
							chatAppScreen.destroyChatAppForm();
							chatAppScreen.refeshDataForm();
						} else {
							grChat.checkMessage(notifyChangeNameGr);
						}

						break;
					}
					case "administator": {
						data = pk.getData();

						String notifyUpdateAdmin = data.replace("[", "").replace("]", "");
						if (notifyUpdateAdmin.equals("Update thành công")) {
							grChat.checkMessage(notifyUpdateAdmin);
							grChat.destroyAdmin();
						} else {
							grChat.checkMessage(notifyUpdateAdmin);
							grChat.destroyAdmin();
						}

						break;
					}
					case "removeMember": {
						data = pk.getData();

						// System.out.println(data);

						String notifyDeleteMember = data.replace("[", "").replace("]", "");
						ArrayList<String> listGr = new ArrayList<String>();
						ArrayList<String> currGr = new ArrayList<String>();
						listGr = chatAppScreen.getDataControlListMemberRoom();
						currGr.add(listGr.get(listGr.size() - 1));

						if (notifyDeleteMember.equals("Xóa thành công")) {
							grChat.checkMessage(notifyDeleteMember);
							System.out.println(currGr);
							grChat.controlShowListMember(currGr);
						} else {
							grChat.checkMessage(notifyDeleteMember);
						}

						break;
					}
					case "addMemberGroup": {
						data = pk.getData();

						if (data.split(", ").length > 1) {
							String notifyAddMember = data.split(", ")[0].replace("[", "").replace("]", "");
							String fullnameshowListMember = data.split(", ")[1].replace("[", "").replace("]", "");
							String idshowListMember = data.split(", ")[2].replace("[", "").replace("]", "");
							ArrayList<String> listGr = new ArrayList<String>();
							ArrayList<String> currGr = new ArrayList<String>();
							listGr = chatAppScreen.getDataControlListMemberRoom();
							currGr.add(listGr.get(listGr.size() - 1));

							if (notifyAddMember.equals("Thêm thành công")) {
								grChat.checkMessage(notifyAddMember);
								grChat.controlShowListMember(currGr);
							}
						} else {
							String notifyAddMember = data.replace("[", "").replace("]", "");
							if (notifyAddMember.equals("Không có thông tin bạn bè này")) {
								grChat.checkMessage(notifyAddMember);
							} else {
								grChat.checkMessage(notifyAddMember);
							}
						}

						break;
					}
					case "sendNotifyCreateGr": {
						data = pk.getData();

						chatAppScreen.sendMessage(data);
						chatAppScreen.refeshDataForm();

						break;
					}
					case "sendNotifyAddGr": {
						data = pk.getData();

						grChat.checkMessage(data);

						break;
					}
					case "sendNotifyDeleteGr": {
						data = pk.getData();

						grChat.checkMessage(data);

						break;
					}
					case "sendNotifyAdminGr": {
						data = pk.getData();

						grChat.checkMessage(data);

						break;
					}
					case "chatGroup": {
						break;
					}
					case "resetPassword": {
						break;
					}
					default: {
						break;
					}
				}
			}
		}).start();
	}

	public void sendTextMessage(String content) {
		client.sendString(content);
	}

	public void reconnect() {
		client.DisconnectToServer();
		client.ConnectToServer(hostName, port);
	}

	public void disconnect() {
		running = false;
		client.DisconnectToServer();
	}

	public boolean login(String username, String password) {
		client.sendString(new Packet("logIn", username + '`' + password, "").toString());
		return true;
	}

	public boolean register(String username, String email, String password) {
		client.sendString(new Packet("signUp", username + '`' + email + "`" + password, "").toString());
		return true;
	}

	public boolean forgotPassword(String username) {
		client.sendString(new Packet("forgotPassword", username, "").toString());
		return true;
	}

	public void resetPassword(String username) {
		client.sendString(new Packet("resetPassword", username, "").toString());
	}

	public void logout() {
		username = null;
		id = null;
		fullname = null;
		homeScreen.setVisible(true);
	}

	public void handleScreen(String screen, boolean status) {
		switch (screen) {
			case "registerScreen": {
				registerScreen.setVisible(status);
				break;
			}
			case "homeScreen": {
				homeScreen.setVisible(status);
				break;
			}
			case "loginScreen": {
				loginScreen.setVisible(status);
				break;
			}
			case "manageScreen": {
				MNUserList.setVisible(status);
				break;
			}
			case "forgotScreen": {
				forgotScreen.setVisible(status);
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + screen);
		}

	}
}
