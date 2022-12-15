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

	private DefaultListModel dmodel = new DefaultListModel();
	private String thisIdGr = "";
	private String isAdmin = "";
	private String listIDMember = "";
	private String listIdAdmin = "";
	private String listNameMember = "";
	private String nameGr = "";
	private String newNameGr = "";
	private String idThisUser = "";
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
				addMember.setVisible(true);
//				username = addMember.getUserName();
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
				getNameGr = newNameGr;
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
		String id = "63b533f8-1e88-4a22-9069-51d9507f94ed";
		Controller.getInstance()
				.sendTextMessage(new Packet("showListMemberRoom", showListNameRoom.toString(), id).toString());
	}

	public String getNameGr() {
		return getNameGr;
	}

	public void destroyGroupChatScreen() {
		thisIdGr = "";
		isAdmin = "";
		listIDMember = "";
		listIdAdmin = "";
		listNameMember = "";
		nameGr = "";
		newNameGr = "";
		idThisUser = "";
		notifyUpdateAdmin = "";
		notifyChangeNameGr = "";
		notifyDeleteMember = "";
		notifyAddMember = "";
		fullnameshowListMember = "";
		idshowListMember = "";
		username = "";
		getNameGr = "";
		newNameGroup.clear();
		packetIdMemberBecomeAdmin.clear();
		idMemberBecomeAdmin.clear();
		idMemberDeleteRoom.clear();
		packetIdMemberDeleteRoom.clear();

		int sizeDmodel = dmodel.size();
		if (sizeDmodel != 0) {
			while (sizeDmodel != 0) {
				dmodel.removeElementAt(sizeDmodel - 1);
				sizeDmodel -= 1;
			}
		}

		textField.setText("");
	}

	private void clickedDeleteMember() {
		packetIdMemberDeleteRoom.clear();
		packetIdMemberDeleteRoom.add(thisIdGr);
		String selected = list.getSelectedValue().toString();
		String listIdAdminMember = listIdAdmin + ", " + listIDMember;
		boolean flag = true;

		for (int i = 0; i < listNameMember.split(", ").length; i++) {
			if (selected.equals(listNameMember.split(", ")[i])) {
				idMemberDeleteRoom.add(listIdAdminMember.split(", ")[i]);

				if (listIdAdminMember.split(", ")[i].equals(idThisUser)) {
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
		packetIdMemberBecomeAdmin.add(thisIdGr);
		String selected = list.getSelectedValue().toString();
		String listIdAdminMember = listIdAdmin + ", " + listIDMember;

		boolean flag = true;

		for (int i = 0; i < listNameMember.split(", ").length; i++) {
			if (selected.equals(listNameMember.split(", ")[i])) {
				idMemberBecomeAdmin.add(listIdAdminMember.split(", ")[i]);
				if (listIdAdminMember.split(", ")[i].equals(idThisUser)) {
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

	public void showInfoGroupChat(String _listNameMember, String idGrSelected, String _isAdmin, String _listIdMember,
			String _nameGr, String _listIdAdmin, String _idUser) {
		thisIdGr = idGrSelected;
		isAdmin = _isAdmin;
		listIDMember = _listIdMember;
		listNameMember = _listNameMember;
		listIdAdmin = _listIdAdmin;
		nameGr = _nameGr;
		idThisUser = _idUser;

		if (isAdmin.equals("0")) {
			btnNewButton_1.setEnabled(false);
			btnNewButton_1_1.setEnabled(false);
		}
		for (int i = 0; i < listNameMember.split(", ").length; i++) {
			dmodel.addElement(listNameMember.split(", ")[i]);
		}
	}

	public void checkChangeNameGr(String data, ArrayList<String> refeshData) {
		notifyChangeNameGr = data.replace("[", "").replace("]", "");
		if (notifyChangeNameGr.equals("Thay đổi thành công")) {
			JOptionPane.showMessageDialog(panel, notifyChangeNameGr, "Notify", JOptionPane.INFORMATION_MESSAGE);
			destroyGroupChatScreen();
			controlShowListMember(refeshData);
			this.setTitle(getNameGr);
		} else {
			JOptionPane.showMessageDialog(panel, notifyChangeNameGr, "Notify", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public void checkUpdateAdmin(String data) {
		notifyUpdateAdmin = data.replace("[", "").replace("]", "");
		if (notifyUpdateAdmin.equals("Update thành công")) {
			JOptionPane.showMessageDialog(panel, notifyUpdateAdmin, "Notify", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(panel, notifyUpdateAdmin, "Notify", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public void checkAddMember(String data, ArrayList<String> refeshData) {
		if (data.split(", ").length > 1) {
			notifyAddMember = data.split(", ")[0].replace("[", "").replace("]", "");
			fullnameshowListMember = data.split(", ")[1].replace("[", "").replace("]", "");
			idshowListMember = data.split(", ")[2].replace("[", "").replace("]", "");

			if (notifyAddMember.equals("Thêm thành công")) {
				JOptionPane.showMessageDialog(panel, notifyAddMember, "Notify", JOptionPane.INFORMATION_MESSAGE);
				destroyGroupChatScreen();
				controlShowListMember(refeshData);
			} else if (notifyAddMember.equals("Không có thông tin bạn bè này")) {
				JOptionPane.showMessageDialog(panel, notifyAddMember, "Notify", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(panel, notifyAddMember, "Notify", JOptionPane.INFORMATION_MESSAGE);
			}
		} else {
			notifyAddMember = data.replace("[", "").replace("]", "");
			if (notifyAddMember.equals("Thêm thành công")) {
				JOptionPane.showMessageDialog(panel, notifyAddMember, "Notify", JOptionPane.INFORMATION_MESSAGE);
			} else if (notifyAddMember.equals("Không có thông tin bạn bè này")) {
				JOptionPane.showMessageDialog(panel, notifyAddMember, "Notify", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(panel, notifyAddMember, "Notify", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

	public void checkDeleteMember(String data, ArrayList<String> refeshData) {
		notifyDeleteMember = data.replace("[", "").replace("]", "");
		if (notifyDeleteMember.equals("Xóa thành công")) {
			JOptionPane.showMessageDialog(panel, notifyDeleteMember, "Notify", JOptionPane.INFORMATION_MESSAGE);
			destroyGroupChatScreen();
			controlShowListMember(refeshData);
		} else {
			JOptionPane.showMessageDialog(panel, notifyDeleteMember, "Notify", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public void refeshDataForm() {
		String id = "63b533f8-1e88-4a22-9069-51d9507f94ed";
		Controller.getInstance().sendTextMessage(new Packet("showListGr", id, "").toString());
	}
}
