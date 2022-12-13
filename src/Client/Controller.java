package Client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Client.Views.User;
import java.text.Collator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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

	private Controller() {
		client = new TCP_Client();
		running = false;
	}

	public static void setTis(Controller sgt) {
		Controller.sgt = sgt;
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

				if (msg == null || msg.equals("Server closed!")) {
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
					ClientApp.MNUserList.showInfor(myList);
					break;
				}
				case "showAll": {
					data = pk.getData();
					String replace1 = data.substring(1, data.lastIndexOf("]"));
					List<String> myList = new ArrayList<String>(Arrays.asList(replace1.split(", ")));
					System.out.println("List packet display:" + myList);
					ClientApp.MNUserList.showInfor(myList);
					break;
				}
				case "orderName":{
					data = pk.getData();
					String replace1 = data.substring(1, data.lastIndexOf("]"));
					List<String> myList = new ArrayList<String>(Arrays.asList(replace1.split(", ")));
					ClientApp.MNUserList.showInfor(myList);
					break;
				}
				case "orderCreateDate":{
					data = pk.getData();
					String replace1 = data.substring(1, data.lastIndexOf("]"));
					List<String> myList = new ArrayList<String>(Arrays.asList(replace1.split(", ")));
					ClientApp.MNUserList.showInfor(myList);
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
//					System.out.println(str);

					String[] userList = str.split("\\], ");
					userList[userList.length - 1] = userList[userList.length - 1].substring(0,
							userList[userList.length - 1].length() - 1);
					for (String user : userList) {
						ArrayList<String> userInfo = new ArrayList<String>();
						String[] temp = user.split(", \\[");
						userInfo.add(temp[0].split(", ")[0]);
						userInfo.add(temp[0].split(", ")[1]);
						userInfo.add(temp[1]);
						historyLogin.add(userInfo);
					}
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
						SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
						String startDate = tmpHistoryLogin.get(i1).get(2);
						String endDate = tmpHistoryLogin.get(i1 + 1).get(2);
						System.out.println(tmpHistoryLogin.get(i1));
						try {
							if (sdf.parse(startDate).before(sdf.parse(endDate))) {
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
//					System.out.println(historyLoginArrayList);
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
//						System.out.println(infoGroup[i]);
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
//					Collections.sort(nameGroupSorted);
					Collections.sort(nameGroupSorted, Collator.getInstance());
					System.out.println("After sorted: " + nameGroupSorted);
					
					for(int i = 0; i < nameGroupSorted.size(); i++) {
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
					for(int i = 0; i < infoGroupDate.length; i++) {
						infoCreatedGroupSorted.add(infoGroupDate[i] + ", " + infoGroupDate[++i]);
					}
					
					for(int i = 0; i < infoCreatedGroupSorted.size();i++) {
						List<String> myList = new ArrayList<String>(Arrays.asList(infoCreatedGroupSorted.get(i).split(", ")));
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
					
					for(int i = 0; i < listCreateDateGroup.size(); i++) {
						finalSortGroupList.add(listCreateDateGroup.get(i).get(0));
					}
					groupChatLists.showGroupChatListSortedByName(finalSortGroupList);
					groupChatLists.setVisible(true);
					break;
				}
				case "signUp": {
					break;
				}
				case "logIn": {
					break;
				}
				case "forgotPassword": {
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
				case "createGroup": {
					break;
				}
				case "changeNameGroup": {
					break;
				}
				case "administator": {
					break;
				}
				case "removeMember": {
					break;
				}
				case "chatGroup": {
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
		client.sendString("1\t" + username + "\t" + password);
		return false;
	}

	public boolean register(String username, String password) {
		client.sendString("0\t" + username + "\t" + password);
		System.out.println("send");
		return client.readString().equals("1");
	}

}
