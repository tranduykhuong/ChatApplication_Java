package Client.Views;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import Client.Controller;
import Entity.Packet;

public class ChatApplicationScreen extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JButton findOneMsgBtn;
	JButton deleteHisBtn;
	JButton sendBtn;
	JTextPane listMessageTp;
	JList<String> list;
	JList<String> listFriendOnline;
	JList<String> listGroup;
	JList<String> listFriend;
	JList<String> listMsgWaiting;
	JList<String> listReqFriend;
	private String listIDChat = "";
	private String listNameChat = "";
	private String listIDOnline = "";
	private String listNameOnline = "";
	private ArrayList<String> listIDWaiting = new ArrayList<>();
	private ArrayList<String> listNameWaiting = new ArrayList<>();
	private ArrayList<String> listIDWaiting2 = new ArrayList<>();
	private ArrayList<String> listNameWaiting2 = new ArrayList<>();
	private ArrayList<String> listReqestAddFriend = new ArrayList<>();

	private String idChat = "";
	private String nameChat = "o";
	private boolean roomFlag = false;
	private boolean newMsg = false;
	private boolean sentHistoryRoom = true;

	private String id;
	private String fullname;

	private String listNameGr = "";
	private String listIdGr = "";

	private DefaultListModel<String> dmodel = new DefaultListModel<String>();
	private DefaultListModel<String> friendModel = new DefaultListModel<String>();
	private DefaultListModel<String> friendOnlineModel = new DefaultListModel<String>();
	private DefaultListModel<String> msgWaitingModel = new DefaultListModel<String>();
	private DefaultListModel<String> reqAddFriendModel = new DefaultListModel<String>();
	private ArrayList<String> showListNameRoom = new ArrayList<String>();
	private ArrayList<String> getshowListNameRoom = new ArrayList<String>();
	private ImageIcon iconTitle = new ImageIcon(HomeScreen.class.getResource("/Image/iconmini.jpg"));

	public String formatDate(String time) {
		if (time.length() > 0) {
			return time.substring(0, 5) + time.substring(10);
		} else {
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM HH:mm:ss");
			Date date = new Date();

			return formatter.format(date);
		}
	}

	private void formatMessage(JTextPane pane, String text, Color color, int align, Color bgColor) {
		StyledDocument doc = listMessageTp.getStyledDocument();

		Style style = pane.addStyle("Color Style", null);
		StyleConstants.setForeground(style, color);
		StyleConstants.setBackground(style, bgColor);

		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, align);
//		StyleConstants có nhiều hàm set lắm á, tìm hiểu trang trí cho đẹp, t chưa border radius được

		try {
			doc.insertString(doc.getLength(), text, style);
			doc.setParagraphAttributes(doc.getLength() - 1, doc.getLength(), center, false);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}

	private void scrollToBottom() {
		listMessageTp.selectAll();
		int y = listMessageTp.getSelectionEnd();
		listMessageTp.select(y, 0);
	}

	public void addTextMessage(String sender, String content, String time, String seen, String type) {
		SwingUtilities.invokeLater(() -> {
			if (sender.equals(fullname)) {
				if (!type.equals("ROOM")) {
					formatMessage(listMessageTp, seen, Color.green, StyleConstants.ALIGN_RIGHT, Color.white);
				}
				formatMessage(listMessageTp, content, Color.BLACK, StyleConstants.ALIGN_RIGHT, Color.blue);
				formatMessage(listMessageTp, " [" + time + "]\n", Color.LIGHT_GRAY, StyleConstants.ALIGN_RIGHT,
						Color.white);
			} else {
				formatMessage(listMessageTp, "[" + time + "]", Color.LIGHT_GRAY, StyleConstants.ALIGN_LEFT, Color.gray);
				if (type.equals("ROOM")) {
					formatMessage(listMessageTp, "[" + sender + "]", Color.BLUE, StyleConstants.ALIGN_LEFT, Color.cyan);
				}
				formatMessage(listMessageTp, " " + content + "\n", Color.BLACK, StyleConstants.ALIGN_LEFT, Color.green);
			}
			scrollToBottom();
		});
	}

	public ChatApplicationScreen() {
		Image icon = iconTitle.getImage();
		setIconImage(icon);
		setTitle("Chat Application");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				String[] options = { "Yes", "No" };
				int result = JOptionPane.showOptionDialog(null, "Are you sure you want to LOGOUT?", "Confirmation",
						JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
				if (result == 0) {
					Controller.getInstance().handleScreen("homeScreen", true);
					setVisible(false);
				}
			}
		});
		setBounds(100, 100, 917, 498);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		list = new JList<String>();
		list.setBounds(5, 5, 113, 62);

		list.setFont(new Font("Times New Roman", Font.BOLD, 12));
		list.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"User information", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		contentPane.add(list);

		JPanel panel = new JPanel();
		panel.setBounds(123, 5, 623, 356);
		panel.setBorder(new MatteBorder(2, 2, 3, 2, (Color) new Color(128, 128, 128)));
		contentPane.add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0 };
		gbl_panel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "Message", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_4.setBackground(Color.WHITE);
		panel_4.setLayout(null);
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.fill = GridBagConstraints.BOTH;
		gbc_panel_4.gridx = 0;
		gbc_panel_4.gridy = 0;
		panel.add(panel_4, gbc_panel_4);

		JPanel panel_8 = new JPanel();
		panel_8.setBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(192, 192, 192)));
		panel_8.setBounds(6, 51, 448, 292);
		panel_4.add(panel_8);
		panel_8.setLayout(new GridLayout(1, 0, 0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panel_8.add(scrollPane);

		listMessageTp = new JTextPane();
		scrollPane.setViewportView(listMessageTp);

		JPanel panel_7 = new JPanel();
		panel_7.setBounds(5, 20, 144, 35);
		panel_4.add(panel_7);
		panel_7.setLayout(new GridLayout(1, 0, 0, 0));

		JPanel panel_6 = new JPanel();
		panel_7.add(panel_6);
		panel_6.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_6.setBackground(Color.LIGHT_GRAY);
		panel_6.setLayout(null);

		JLabel lbNameChat = new JLabel("");
		lbNameChat.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lbNameChat.setBounds(10, 6, 124, 22);
		panel_6.add(lbNameChat);

		JPanel panel_9 = new JPanel();
		panel_9.setBorder(new TitledBorder(null, "Thao t\u00E1c ng\u01B0\u1EDDi d\u00F9ng", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel_9.setBackground(Color.WHITE);
		panel_9.setBounds(456, 11, 159, 334);
		panel_4.add(panel_9);
		panel_9.setLayout(null);

		deleteHisBtn = new JButton("Xóa lịch sử");
		deleteHisBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (roomFlag) {
					Controller.getInstance().sendTextMessage(
							new Packet("removeChatWithRoomHistory", id + ", " + idChat, id).toString());
					Controller.getInstance()
							.sendTextMessage(new Packet("viewChatHistoryRoom", id + ", " + idChat, id).toString());
				} else {
					Controller.getInstance().sendTextMessage(
							new Packet("removeChatWithFriendHistory", id + ", " + idChat, id).toString());
					Controller.getInstance().sendTextMessage(
							new Packet("viewChatHistoryWithFriend", id + ", " + idChat, id).toString());
				}
			}
		});
		deleteHisBtn.setFont(new Font("Times New Roman", Font.BOLD, 15));
		deleteHisBtn.setBounds(10, 30, 139, 27);
		deleteHisBtn.setEnabled(false);
		panel_9.add(deleteHisBtn);

		findOneMsgBtn = new JButton("Find Message");
		findOneMsgBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (roomFlag) {
					Controller.getInstance().setDataFindMsgScreen(idChat, nameChat, "ROOM");
				} else {
					Controller.getInstance().setDataFindMsgScreen(idChat, nameChat, "FRIEND");
				}
				Controller.getInstance().handleScreen("findMsgScreen", true);
			}
		});
		findOneMsgBtn.setFont(new Font("Times New Roman", Font.BOLD, 15));
		findOneMsgBtn.setBounds(10, 68, 139, 27);
		findOneMsgBtn.setEnabled(false);
		panel_9.add(findOneMsgBtn);

		JButton findAllMsgBtn = new JButton("Find All");
		findAllMsgBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().setDataFindMsgScreen("", "ALL", "ALL");
				Controller.getInstance().handleScreen("findMsgScreen", true);
			}
		});
		findAllMsgBtn.setFont(new Font("Times New Roman", Font.BOLD, 15));
		findAllMsgBtn.setBounds(10, 106, 139, 27);
		panel_9.add(findAllMsgBtn);

		JButton btnNewButton_6_1_1_1_1 = new JButton("LOG OUT");
		btnNewButton_6_1_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().logout();
				setVisible(false);
			}
		});
		btnNewButton_6_1_1_1_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnNewButton_6_1_1_1_1.setBounds(10, 291, 139, 27);
		panel_9.add(btnNewButton_6_1_1_1_1);

		listFriendOnline = new JList<String>();
		listFriendOnline.setBounds(5, 102, 113, 99);
		listFriendOnline.setFont(new Font("Times New Roman", Font.BOLD, 13));
		listFriendOnline.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"B\u1EA1n b\u00E8 Online", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		// contentPane.add(list_1);
		JScrollPane scroll1 = new JScrollPane(listFriendOnline);
		scroll1.setBounds(5, 72, 113, 130);
		contentPane.add(scroll1);

		listFriend = new JList<String>();
		listFriend.setBounds(751, 4, 145, 146);
		listFriend.setFont(new Font("Times New Roman", Font.BOLD, 13));
		listFriend.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Danh s\u00E1ch b\u1EA1n", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		contentPane.add(listFriend);

		listMsgWaiting = new JList<String>();
		listMsgWaiting.setFont(new Font("Times New Roman", Font.BOLD, 13));
		listMsgWaiting.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Tin nh\u1EAFn ch\u1EDD", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		listMsgWaiting.setBounds(751, 158, 145, 130);
		contentPane.add(listMsgWaiting);

		listFriendOnline.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int idx = listFriendOnline.getSelectedIndex();
				nameChat = listFriendOnline.getSelectedValue();
				idChat = listIDOnline.split(", ")[idx];

				lbNameChat.setText(nameChat);
				roomFlag = false;
				findOneMsgBtn.setEnabled(true);
				deleteHisBtn.setEnabled(true);
				sendBtn.setEnabled(true);

				Controller.getInstance()
						.sendTextMessage(new Packet("viewChatHistoryWithFriend", id + ", " + idChat, id).toString());
			}
		});

		listFriend.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int idx = listFriend.getSelectedIndex();
				nameChat = listFriend.getSelectedValue();
				idChat = listIDChat.split(", ")[idx];

				lbNameChat.setText(nameChat);
				roomFlag = false;
				findOneMsgBtn.setEnabled(true);
				deleteHisBtn.setEnabled(true);
				sendBtn.setEnabled(true);

				if (listIDWaiting2.contains(idChat)) {
					listIDWaiting2.remove(idChat);
					listNameWaiting2.remove(nameChat);
					Controller.getInstance()
							.sendTextMessage(new Packet("userSeenMessage", id + ", " + idChat, "").toString());
				}

				Controller.getInstance()
						.sendTextMessage(new Packet("viewChatHistoryWithFriend", id + ", " + idChat, id).toString());
			}
		});

		listMsgWaiting.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int idx = listMsgWaiting.getSelectedIndex();
				nameChat = listMsgWaiting.getSelectedValue();

				if (idx < listIDWaiting.size()) {
					idChat = listIDWaiting.get(idx);
				} else {
					idx = idx - listIDWaiting.size();
					idChat = listIDWaiting2.get(idx);

					listIDWaiting2.remove(idx);
					listNameWaiting2.remove(idx);
					Controller.getInstance()
							.sendTextMessage(new Packet("userSeenMessage", id + ", " + idChat, "").toString());
				}

				lbNameChat.setText(nameChat);
				roomFlag = false;
				findOneMsgBtn.setEnabled(true);
				deleteHisBtn.setEnabled(true);
				sendBtn.setEnabled(true);

				Controller.getInstance()
						.sendTextMessage(new Packet("viewChatHistoryWithFriend", id + ", " + idChat, id).toString());
			}
		});

		listGroup = new JList<String>();
		listGroup.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int idx = listGroup.getSelectedIndex();
				nameChat = listGroup.getSelectedValue();
				idChat = listIdGr.split(", ")[idx];

				lbNameChat.setText(nameChat);
				roomFlag = true;
				findOneMsgBtn.setEnabled(true);
				deleteHisBtn.setEnabled(true);
				sendBtn.setEnabled(true);

				if (sentHistoryRoom) {
					Controller.getInstance()
							.sendTextMessage(new Packet("viewChatHistoryRoom", id + ", " + idChat, id).toString());
					sentHistoryRoom = false;
				}
			}
		});
		listGroup.setBounds(5, 206, 113, 155);
		listGroup.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String selected = listGroup.getSelectedValue().toString();

				for (int i = 0; i < listNameGr.split(", ").length; i++) {
					if (selected.equals(listNameGr.split(", ")[i])) {
						showListNameRoom.add(selected);
						showListNameRoom.add(listIdGr.split(", ")[i]);
					}
				}
				getshowListNameRoom.add(showListNameRoom.toString());

				Controller.getInstance()
						.sendTextMessage(new Packet("showListMemberRoom", showListNameRoom.toString(), id).toString());

				showListNameRoom.clear();
			}
		});
		listGroup.setFont(new Font("Times New Roman", Font.BOLD, 13));
		listGroup.setBorder(
				new TitledBorder(null, "Nh\u00F3m", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		JScrollPane scroll2 = new JScrollPane(listGroup);
		scroll2.setBounds(5, 206, 113, 155);
		contentPane.add(scroll2);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(5, 366, 113, 38);
		contentPane.add(panel_2);
		panel_2.setLayout(new GridLayout(1, 0, 0, 0));

		JButton btnNewButton = new JButton("Tạo Nhóm");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().sendTextMessage(new Packet("showListFriend", id, "").toString());
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 13));
		panel_2.add(btnNewButton);

		JPanel panel_5 = new JPanel();
		panel_5.setBounds(123, 415, 515, 38);
		contentPane.add(panel_5);
		panel_5.setLayout(new GridLayout(1, 0, 0, 0));

		JScrollPane scrollPane_1 = new JScrollPane();
		panel_5.add(scrollPane_1);

		JTextArea msgInput = new JTextArea();
		msgInput.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!roomFlag && newMsg) {
					Controller.getInstance()
							.sendTextMessage(new Packet("userSeenMessage", id + ", " + idChat, "").toString());
					newMsg = false;
				}
			}
		});
		scrollPane_1.setViewportView(msgInput);
		msgInput.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		msgInput.setLineWrap(true);
		msgInput.setWrapStyleWord(true);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(644, 415, 76, 38);
		contentPane.add(panel_3);
		panel_3.setLayout(new GridLayout(1, 0, 0, 0));

		sendBtn = new JButton("GỬI");
		sendBtn.setEnabled(false);
		panel_3.add(sendBtn);
		sendBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String msg = msgInput.getText();
				if (msg.length() > 0 && idChat.length() > 0) {
					if (roomFlag) {
						Controller.getInstance().sendTextMessage(
								new Packet("chatGroup", id + ", " + fullname + ", " + idChat + ", " + msg, id)
										.toString());
						addTextMessage(fullname, msg, formatDate(""), "Đã nhận", "ROOM");
					} else {
						Controller.getInstance().sendTextMessage(
								new Packet("chatU2U", id + ", " + fullname + ", " + idChat + ", " + msg, id)
										.toString());
						addTextMessage(fullname, msg, formatDate(""), "Đã nhận", "U2U");
					}
				}
				msgInput.setText("");
			}
		});
		sendBtn.setFont(new Font("Times New Roman", Font.BOLD, 13));

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(727, 415, 169, 38);
		contentPane.add(panel_1);
		panel_1.setLayout(new GridLayout(1, 0, 0, 0));

		JButton btnNewButton_2 = new JButton("Tìm kiếm bạn bè");
		panel_1.add(btnNewButton_2);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().handleScreen("addFriendScreen", true);
			}
		});
		btnNewButton_2.setFont(new Font("Times New Roman", Font.BOLD, 13));

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(751, 293, 145, 111);
		contentPane.add(scrollPane_2);

		listReqFriend = new JList<String>();
		listReqFriend.setFont(new Font("Times New Roman", Font.BOLD, 13));
		listReqFriend.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"L\u1EDDi m\u1EDDi k\u1EBFt b\u1EA1n", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		listReqFriend.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int idx = listReqFriend.getSelectedIndex();
				String name = listReqFriend.getSelectedValue();
				String id = listReqestAddFriend.get(idx * 2);

				new AddFriendNotify().show(name, id);
			}
		});
		scrollPane_2.setViewportView(listReqFriend);
	}

	public void run() {
		Controller.getInstance().sendTextMessage(new Packet("showListGr", id, id).toString());
		Controller.getInstance().sendTextMessage(new Packet("showListFriendChat", id, id).toString());
		Controller.getInstance().sendTextMessage(new Packet("listFriendOnline", id, id).toString());
		Controller.getInstance().sendTextMessage(new Packet("getWaitingMessage", id, id).toString());
		Controller.getInstance().sendTextMessage(new Packet("listRequestAddFriend", id, id).toString());
		this.setVisible(true);
	}

	public ArrayList<String> getDataControlListMemberRoom() {
		return getshowListNameRoom;
	}

	public void setId(String _id) {
		this.id = _id;
	}

	public void refeshDataForm() {
		Controller.getInstance().sendTextMessage(new Packet("showListGr", id, id).toString());
	}

	public void showListNameGr(String _data) {
		String listIdAndName = _data.substring(1, _data.length() - 1);
		listIdGr = listIdAndName.split("], ")[0].replace("[", "");
		listNameGr = listIdAndName.split("], ")[1].replace("]", "").replace("[", "");

		for (int i = 0; i < listNameGr.split(", ").length; i++) {
			dmodel.addElement(listNameGr.split(", ")[i]);
		}

		listGroup.setModel(dmodel);
	}

	public void destroyListNameRoom() {
		getshowListNameRoom.clear();
	}

	public void destroyChatAppForm() {
		listNameGr = "";
		listIdGr = "";

		int sizeDmodel = dmodel.size();
		if (sizeDmodel != 0) {
			while (sizeDmodel != 0) {
				dmodel.removeElementAt(sizeDmodel - 1);
				sizeDmodel -= 1;
			}
		}

		listGroup.setModel(dmodel);
		showListNameRoom.clear();
		getshowListNameRoom.clear();
	}

	public void showMessage(String notify) {
		JOptionPane.showMessageDialog(contentPane, notify, "Notify", JOptionPane.INFORMATION_MESSAGE);
	}

	public void showlistFriend(String listIdAndName) {
		friendModel.clear();
		if (listIdAndName.split("], ").length > 1) {
			listNameChat = listIdAndName.split("], ")[0].replace("[", "");
			listIDChat = listIdAndName.split("], ")[1].replace("]", "").replace("[", "");

			for (int i = 0; i < listNameChat.split(", ").length; i++) {
				friendModel.addElement(listNameChat.split(", ")[i]);
			}
		}
		listFriend.setModel(friendModel);
	}

	public void showlistFriendOnline(String listIdAndName) {
		friendOnlineModel.clear();
		if (listIdAndName.split("], ").length > 1) {
			listNameOnline = listIdAndName.split("], ")[0].replace("[", "");
			listIDOnline = listIdAndName.split("], ")[1].replace("]", "").replace("[", "");

			for (int i = 0; i < listNameOnline.split(", ").length; i++) {
				friendOnlineModel.addElement(listNameOnline.split(", ")[i]);
			}
		}
		listFriendOnline.setModel(friendOnlineModel);
	}

	public void showUserInfo(String fullname, String id) {
		DefaultListModel<String> infoModel = new DefaultListModel<String>();
		infoModel.addElement(fullname);
		this.fullname = fullname;
		this.id = id;
		list.setModel(infoModel);
	}

	public void renderMessage(String data, String type) {
		String idSender = data.split(", ")[0];
		String nameSender = data.split(", ")[1];
		String msg = data.split(", ")[2];
		String time = data.split(", ")[3];

		if (type.equals("U2U")) {
			if (idSender.equals(idChat)) {
				addTextMessage(nameSender, msg, formatDate(time), "Đã xem", "U2U");
				newMsg = true;
			} else if (!listIDWaiting.contains(idSender) && !listIDWaiting2.contains(idSender)) {
				listIDWaiting2.add(idSender);
				listNameWaiting2.add(nameSender);

				msgWaitingModel.addElement(nameSender);
				listMsgWaiting.setModel(msgWaitingModel);
			}
		} else {
			if (data.split(", ")[4].equals(idChat)) {
				addTextMessage(nameSender, msg, formatDate(time), "Đã xem", "ROOM");
			}
		}
	}

	public void viewChatHistory(String data, String type) {
		listMessageTp.setText("");
		if (data.length() > 2) {
			String[] result = data.split("`");
			String[] listData = result[0].substring(1, result[0].length() - 1).split("Document");

			int seenNumber = Integer.parseInt(result[1]);
			for (int i = 1; i < listData.length; i++) {
				String[] itemData = listData[i].split(", ");

				String nameSender = itemData[2].split("=")[1];
				String msg = itemData[3].split("=")[1];
				String time = itemData[4].split("=")[1].substring(0, itemData[4].split("=")[1].length() - 2);
				if (i - 1 < seenNumber) {
					addTextMessage(nameSender, msg, formatDate(time), "Đã xem", type);
				} else {
					addTextMessage(nameSender, msg, formatDate(time), "Đã nhận", type);
				}
			}
			sentHistoryRoom = true;
		}
	}

	public void userSeenMsg(String idSeen) {
		if (idSeen.equals(this.idChat)) {
			Controller.getInstance()
					.sendTextMessage(new Packet("viewChatHistoryWithFriend", id + ", " + idChat, id).toString());
		}
	}

	public void showWaitingList(String data) {
		String[] listData = data.split("`");
		String[] listID = listData[0].substring(1, listData[0].length() - 1).split(", ");
		String[] listName = listData[1].substring(1, listData[1].length() - 1).split(", ");

		listIDWaiting.clear();
		listNameWaiting.clear();
		msgWaitingModel.clear();

		for (int i = 0; i < listID.length; i++) {
			listIDWaiting.add(listID[i]);
			listNameWaiting.add(listName[i]);
			msgWaitingModel.addElement(listName[i]);
		}
		for (String e : listNameWaiting2) {
			msgWaitingModel.addElement(e);
		}
		listMsgWaiting.setModel(msgWaitingModel);
	}

	public void showRequestAddFriend(String data) {
		String[] listData = data.substring(1, data.length() - 1).split(", ");

		listReqestAddFriend.clear();
		reqAddFriendModel.clear();

		for (int i = 0; i < listData.length - 1; i += 2) {
			listReqestAddFriend.add(listData[i]);
			listReqestAddFriend.add(listData[i + 1]);
			reqAddFriendModel.addElement(listData[i + 1]);
		}
		listReqFriend.setModel(reqAddFriendModel);
	}
}
