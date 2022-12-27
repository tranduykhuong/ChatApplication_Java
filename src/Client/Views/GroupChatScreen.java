package Client.Views;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import Client.Controller;
import Entity.Packet;

public class GroupChatScreen extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JList<String> list;
	private JButton btnNewButton_1;
	private JButton btnNewButton_1_1;
	private JPanel panel;

	private String id;

	private DefaultListModel dmodel = new DefaultListModel();
	private String thisIdGr = "";
	private String isAdmin = "";
	private String listIDMember = "";
	private String listIdAdmin = "";
	private String listNameMember = "";
	private String nameGr = "";
	private String newNameGr = "";
	private String notifyUpdateAdmin = "";
	private String notifyChangeNameGr = "";
	private String notifyDeleteMember = "";
	private String notifyAddMember = "";
	private String fullnameshowListMember = "";
	private String idshowListMember = "";
	private String username = "";
	private String getNameGr = "";
	private ArrayList<String> newNameGroup = new ArrayList<String>();
	private ArrayList<String> packetIdMemberBecomeAdmin = new ArrayList<String>();
	private ArrayList<String> idMemberBecomeAdmin = new ArrayList<String>();
	private ArrayList<String> idMemberDeleteRoom = new ArrayList<String>();
	private ArrayList<String> packetIdMemberDeleteRoom = new ArrayList<String>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GroupChatScreen frame = new GroupChatScreen();
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
	public GroupChatScreen() {
		setTitle("Nhóm anh em");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 602, 321);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Danh s\u00E1ch th\u00E0nh vi\u00EAn", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel.setBounds(10, 59, 416, 214);
		contentPane.add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 47, 396, 157);
		panel.add(panel_1);
		panel_1.setLayout(new GridLayout(1, 0, 0, 0));

		list = new JList<String>();
		list.setModel(dmodel);
		list.setFont(new Font("Times New Roman", Font.BOLD, 13));
		panel_1.add(list);

		JLabel lblNewLabel = new JLabel("Tên Nhóm");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel.setBounds(34, 26, 56, 13);
		contentPane.add(lblNewLabel);

		textField = new JTextField();
		lblNewLabel.setLabelFor(textField);
		textField.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		textField.setBounds(100, 21, 226, 22);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton btnOk = new JButton("Cancel");
		btnOk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				destroyGroupChatScreen();
			}
		});
		btnOk.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btnOk.setBounds(444, 232, 123, 32);
		contentPane.add(btnOk);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Thao t\u00E1c", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(436, 62, 142, 144);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		btnNewButton_1 = new JButton("Admin Change");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (isAdmin.equals("1")) {
					clickedUpdateAdmin();
				}
			}
		});
		btnNewButton_1.setBounds(10, 24, 123, 32);
		panel_2.add(btnNewButton_1);
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 13));

		JButton btnNewButton_1_1_1 = new JButton("Add Member");
		btnNewButton_1_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AddMemberScreen addMember = new AddMemberScreen();
				addMember.setIdRoom(thisIdGr);
				addMember.setIdSender(id);
				addMember.setNameGr(nameGr);
				addMember.setVisible(true);
			}
		});
		btnNewButton_1_1_1.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btnNewButton_1_1_1.setBounds(10, 64, 123, 32);
		panel_2.add(btnNewButton_1_1_1);

		btnNewButton_1_1 = new JButton("Delete Member");
		btnNewButton_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (isAdmin.equals("1")) {
					clickedDeleteMember();
				}
			}
		});
		btnNewButton_1_1.setBounds(10, 106, 123, 32);
		panel_2.add(btnNewButton_1_1);
		btnNewButton_1_1.setFont(new Font("Times New Roman", Font.BOLD, 13));

		JButton btnNewButton = new JButton("Rename");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				newNameGroup.clear();
				newNameGroup.add(thisIdGr);
				newNameGr = textField.getText();
				if (newNameGr.length() == 0) {
					JOptionPane.showMessageDialog(panel, "Vui lòng nhập tên nhóm mới", "Error",
							JOptionPane.INFORMATION_MESSAGE);
				} else if (newNameGr.equals(nameGr)) {
					JOptionPane.showMessageDialog(panel, "Tên nhóm trùng với tên nhóm hiện tại", "Error",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					int dialogResult = JOptionPane.showConfirmDialog(null,
							"Bạn đã chắc chắn muốn đổi tên nhóm thành '" + newNameGr + "' không?", "Notify",
							JOptionPane.YES_NO_OPTION);
					if (dialogResult == JOptionPane.YES_OPTION) {
						getNameGr = newNameGr;
						newNameGroup.add(newNameGr);
						Controller.getInstance()
								.sendTextMessage(new Packet("changeNameGroup", newNameGroup.toString(), "").toString());
					}
				}
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btnNewButton.setBounds(336, 22, 85, 21);
		contentPane.add(btnNewButton);
	}

	public void controlShowListMember(ArrayList<String> showListNameRoom) {
		Controller.getInstance()
				.sendTextMessage(new Packet("showListMemberRoom", showListNameRoom.toString(), id).toString());
	}

	public String getNameGr() {
		return getNameGr;
	}

	public void setId(String _id) {
		id = _id;
	}

	public void setThisIdGr(String idGrSelected) {
		thisIdGr = idGrSelected;
	}

	public void setIsAmin(String _isAdmin) {
		isAdmin = _isAdmin;
	}

	public void setListIdMember(String _listIdMember) {
		listIDMember = _listIdMember;
	}

	public void setListIdAdmin(String _listIdAdmin) {
		listIdAdmin = _listIdAdmin;
	}

	public void setListNameMember(String _listNameMember) {
		listNameMember = _listNameMember;
	}

	public void setNameGroup(String _nameGr) {
		nameGr = _nameGr;
	}

	public void destroyName() {
		nameGr = "";
		getNameGr = "";
		newNameGr = "";
		newNameGroup.clear();

		textField.setText("");
	}

	public void destroyGroupChatScreen() {
		destroyAdd();
		destroyAdmin();
		destroyDelete();
		destroyName();
	}

	public void destroyAdmin() {
		packetIdMemberBecomeAdmin.clear();
		idMemberBecomeAdmin.clear();
	}

	public void destroyDelete() {
		idMemberDeleteRoom.clear();
		packetIdMemberDeleteRoom.clear();

		int sizeDmodel = dmodel.size();
		if (sizeDmodel != 0) {
			while (sizeDmodel != 0) {
				dmodel.removeElementAt(sizeDmodel - 1);
				sizeDmodel -= 1;
			}
		}
	}

	public void destroyAdd() {
		fullnameshowListMember = "";
		idshowListMember = "";
		username = "";
	}

	private void clickedDeleteMember() {
		packetIdMemberDeleteRoom.clear();
		packetIdMemberDeleteRoom.add(nameGr + "`");
		packetIdMemberDeleteRoom.add(thisIdGr);
		String selected = list.getSelectedValue().toString();
		String listIdAdminMember = listIdAdmin + ", " + listIDMember;
		boolean flag = true;

		for (int i = 0; i < listNameMember.split(", ").length; i++) {
			if (selected.equals(listNameMember.split(", ")[i])) {
				idMemberDeleteRoom.add(listIdAdminMember.split(", ")[i]);

				if (listIdAdminMember.split(", ")[i].equals(id)) {
					JOptionPane.showMessageDialog(panel, "Bạn không thể chọn chính bạn", "Error",
							JOptionPane.INFORMATION_MESSAGE);
					flag = false;
				}
			}
		}

		if (flag) {
			int dialogResult = JOptionPane.showConfirmDialog(null,
					"Bạn chắc chắn muốn xóa " + selected + " khỏi nhóm không?", "Notify", JOptionPane.YES_NO_OPTION);
			if (dialogResult == JOptionPane.YES_OPTION) {
				packetIdMemberDeleteRoom.add(idMemberDeleteRoom.toString());

				Controller.getInstance().sendTextMessage(
						new Packet("removeMember", packetIdMemberDeleteRoom.toString(), "").toString());
			}
		}

		idMemberDeleteRoom.clear();
	}

	private void clickedUpdateAdmin() {
		packetIdMemberBecomeAdmin.clear();
		packetIdMemberBecomeAdmin.add(nameGr + "`");
		packetIdMemberBecomeAdmin.add(thisIdGr);
		String selected = list.getSelectedValue().toString();
		String listIdAdminMember = listIdAdmin + ", " + listIDMember;

		boolean flag = true;

		for (int i = 0; i < listNameMember.split(", ").length; i++) {
			if (selected.equals(listNameMember.split(", ")[i])) {
				idMemberBecomeAdmin.add(listIdAdminMember.split(", ")[i]);
				if (listIdAdminMember.split(", ")[i].equals(id)) {
					JOptionPane.showMessageDialog(panel, "Bạn không thể chọn chính bạn", "Error",
							JOptionPane.INFORMATION_MESSAGE);
					flag = false;
				}
			}
		}

		if (flag) {
			int dialogResult = JOptionPane.showConfirmDialog(null,
					"Bạn chắc chắn muốn " + selected + " trở thành admin không?", "Notify", JOptionPane.YES_NO_OPTION);
			if (dialogResult == JOptionPane.YES_OPTION) {
				packetIdMemberBecomeAdmin.add(idMemberBecomeAdmin.toString());
				Controller.getInstance().sendTextMessage(
						new Packet("administator", packetIdMemberBecomeAdmin.toString(), "").toString());
			}
		}

		idMemberBecomeAdmin.clear();
	}

	public void showInfoGroupChat() {
		if (isAdmin.equals("0")) {
			btnNewButton_1.setEnabled(false);
			btnNewButton_1_1.setEnabled(false);
		} else {
			btnNewButton_1.setEnabled(true);
			btnNewButton_1_1.setEnabled(true);
		}

		for (int i = 0; i < listNameMember.split(", ").length; i++) {
			dmodel.addElement(listNameMember.split(", ")[i]);
		}
	}

	public void checkMessage(String data) {
		JOptionPane.showMessageDialog(panel, data, "Notify", JOptionPane.INFORMATION_MESSAGE);
	}

	public void refeshDataForm() {
		Controller.getInstance().sendTextMessage(new Packet("showListGr", id, "").toString());
	}
}
