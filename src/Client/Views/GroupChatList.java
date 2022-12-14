package Client.Views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import Client.Controller;
import Entity.Packet;

public class GroupChatList extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JList<String> listGroupChat;
	private JList<String> listMember;
	private JList<String> listAdmin;
	private String selectedString;

	private ImageIcon iconTitle = new ImageIcon(HomeScreen.class.getResource("/Image/iconmini.jpg"));

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GroupChatList frame = new GroupChatList();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GroupChatList() {
		Image icon = iconTitle.getImage();
		setIconImage(icon);
		setBounds(100, 100, 842, 483);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		JPanel pnControl = new JPanel();
		pnControl.setBackground(Color.WHITE);
		pnControl.setBounds(10, 10, 808, 92);
		contentPane.add(pnControl);
		pnControl.setLayout(null);
		JLabel lbArrange = new JLabel("Sắp xếp theo:");
		lbArrange.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbArrange.setBounds(11, 55, 99, 13);
		pnControl.add(lbArrange);

		JButton btnSortName = new JButton("Tên");
		btnSortName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().sendTextMessage(new Packet("sortByGroupName", "", "").toString());
				Controller.getInstance().handleScreen("loadingScreen", true);
			}
		});
		btnSortName.setForeground(new Color(1, 128, 254));
		btnSortName.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSortName.setBounds(120, 52, 75, 21);
		pnControl.add(btnSortName);

		JButton btnSortDate = new JButton("Ngày tạo");
		btnSortDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().sendTextMessage(new Packet("sortByCreateDate", "", "").toString());
				Controller.getInstance().handleScreen("loadingScreen", true);
			}
		});
		btnSortDate.setForeground(new Color(1, 128, 254));
		btnSortDate.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSortDate.setBounds(230, 52, 99, 21);
		pnControl.add(btnSortDate);
		JLabel lblDanhSchNhm = new JLabel("Danh sách nhóm chat");
		lblDanhSchNhm.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDanhSchNhm.setBounds(336, 10, 150, 13);
		pnControl.add(lblDanhSchNhm);

		JButton btnBack = new JButton("Trở về");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnBack.setForeground(new Color(1, 128, 254));
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnBack.setBounds(0, 0, 87, 21);
		pnControl.add(btnBack);
		JPanel panel = new JPanel();
		panel.setBounds(10, 112, 808, 324);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(1, 0, 0, 0));

		JPanel pnGroupName = new JPanel();
		pnGroupName.setBorder(
				new TitledBorder(null, "T\u00EAn nh\u00F3m chat", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(pnGroupName);
		pnGroupName.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 21, 249, 292);
		pnGroupName.add(scrollPane);

		listGroupChat = new JList<String>();
		scrollPane.setViewportView(listGroupChat);
		listGroupChat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectedString = listGroupChat.getSelectedValue().toString();
				Controller.getInstance().sendTextMessage(new Packet("showMemberList", selectedString, "").toString());
				Controller.getInstance().handleScreen("loadingScreen", true);
				Controller.getInstance().sendTextMessage(new Packet("showAdminList", selectedString, "").toString());
			}
		});
		listGroupChat.setFont(new Font("Tahoma", Font.PLAIN, 13));

		JPanel pnMemberList = new JPanel();
		pnMemberList.setBorder(new TitledBorder(null, "Danh s\u00E1ch th\u00E0nh vi\u00EAn", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel.add(pnMemberList);
		pnMemberList.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 21, 249, 292);
		pnMemberList.add(scrollPane_1);
		listMember = new JList<String>();
		scrollPane_1.setViewportView(listMember);
		listMember.setFont(new Font("Tahoma", Font.PLAIN, 13));

		JPanel pnAdminList = new JPanel();
		pnAdminList.setBorder(
				new TitledBorder(null, "Danh s\u00E1ch admin", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(pnAdminList);
		pnAdminList.setLayout(null);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 21, 249, 292);
		pnAdminList.add(scrollPane_2);
		listAdmin = new JList<String>();
		scrollPane_2.setViewportView(listAdmin);
		listAdmin.setFont(new Font("Tahoma", Font.PLAIN, 13));
	}

	public void showGroupChatList(List<String> groupChatList) {
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		listGroupChat.setModel(listModel);
		for (int i = 0; i < groupChatList.size(); i = i + 2) {
			listModel.addElement(groupChatList.get(i)); // + " " + groupChatList.get(i + 1)
		}
		System.out.println(groupChatList);
	}

	public void showGroupChatListSortedByName(List<String> groupChatList) {
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		listGroupChat.setModel(listModel);
		for (int i = 0; i < groupChatList.size(); i++) {
			listModel.addElement(groupChatList.get(i));
		}
	}

	public void showGroupChatListSortedByCreateDate(List<String> groupChatList) {
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		listGroupChat.setModel(listModel);
		if (!groupChatList.isEmpty()) {
			for (int i = groupChatList.size() - 1; i >= 0; i--) {
				listModel.addElement(groupChatList.get(i));
			}
		}
	}

	public void showMemberList(List<String> userList) {
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		listMember.setModel(listModel);
		for (int i = 0; i < userList.size(); i++) {
			listModel.addElement(userList.get(i));
		}
	}

	public void showAdminList(List<String> adminList) {
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		listAdmin.setModel(listModel);
		for (int i = 0; i < adminList.size(); i++) {
			listModel.addElement(adminList.get(i)); // + " " + groupChatList.get(i + 1)
		}
	}
}
