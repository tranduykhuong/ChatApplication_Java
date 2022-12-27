package Client.Views;

import java.awt.Color;
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
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import Client.Controller;
import Entity.Packet;

public class AddGroupScreen extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	JPanel panel;
	private JList<String> list;
	private JList<String> list_1;

	private String id;

	private String listID = "";
	private String listName = "";
	private String selectedAdd = "";
	private String selectedRemove = "";
	private String nameGroup = "";
	private String notifyCreateGroup = "";
	private DefaultListModel dmodel = new DefaultListModel();
	private DefaultListModel dmodelSelected = new DefaultListModel();
	private ArrayList<String> createRoomForID = new ArrayList<String>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddGroupScreen frame = new AddGroupScreen();
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
	public AddGroupScreen() {
		setTitle("Create New Group");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 354);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Ch\u1ECDn User \u0111\u1EC3 th\u00EAm v\u00E0o nh\u00F3m",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		panel.setBounds(10, 62, 416, 209);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(1, 0, 0, 0));

		list = new JList<String>();
		list.setModel(dmodel);
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectedAdd = list.getSelectedValue().toString();
				int index = list.getSelectedIndex();

				dmodelSelected.addElement(selectedAdd);
				dmodel.removeElementAt(index);

				for (int i = 0; i < listName.split(", ").length; i++) {
					if (selectedAdd.equals(listName.split(", ")[i])) {
						createRoomForID.add(listID.split(", ")[i]);
						selectedAdd = "";
					}
					if (selectedRemove.equals(listName.split(", ")[i])) {
						for (int j = 0; j < createRoomForID.size(); j++) {
							if (listID.split(", ")[i].equals(createRoomForID.get(j))) {
								createRoomForID.remove(j);
								selectedRemove = "";
							}
						}
					}
				}
			}
		});
		list.setFont(new Font("Times New Roman", Font.BOLD, 13));
		panel.add(list);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Danh s\u00E1ch th\u00E0nh vi\u00EAn", TitledBorder.CENTER, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		panel.add(panel_1);
		panel_1.setLayout(new GridLayout(1, 0, 0, 0));

		list_1 = new JList<String>();
		list_1.setModel(dmodelSelected);
		list_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectedRemove = list_1.getSelectedValue().toString();
				int index = list_1.getSelectedIndex();

				dmodel.addElement(selectedRemove);
				dmodelSelected.removeElementAt(index);

				for (int i = 0; i < listName.split(", ").length; i++) {
					if (selectedAdd.equals(listName.split(", ")[i])) {
						createRoomForID.add(listID.split(", ")[i]);
						selectedAdd = "";
					}
					if (selectedRemove.equals(listName.split(", ")[i])) {
						for (int j = 0; j < createRoomForID.size(); j++) {
							if (listID.split(", ")[i].equals(createRoomForID.get(j))) {
								createRoomForID.remove(j);
								selectedRemove = "";
							}
						}
					}
				}
			}
		});
		list_1.setFont(new Font("Times New Roman", Font.BOLD, 13));
		panel_1.add(list_1);

		JLabel lblNewLabel = new JLabel("Tên Nhóm");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel.setBounds(23, 21, 91, 13);
		contentPane.add(lblNewLabel);

		textField = new JTextField();
		lblNewLabel.setLabelFor(textField);
		textField.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		textField.setBounds(124, 17, 288, 21);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("Tạo");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				nameGroup = textField.getText();
				if (nameGroup.length() == 0 || createRoomForID.size() < 2) {
					JOptionPane.showMessageDialog(panel, "Vui lòng nhập tên nhóm or chọn từ hai người bạn của bạn",
							"Error", JOptionPane.INFORMATION_MESSAGE);
				} else {
					ArrayList<String> concatGrNameListId = new ArrayList<String>();
					concatGrNameListId.add("[" + nameGroup + "]");
					concatGrNameListId.add(createRoomForID.toString());

					Controller.getInstance()
							.sendTextMessage(new Packet("createGroup", concatGrNameListId.toString(), id).toString());
				}
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btnNewButton.setBounds(335, 281, 91, 30);
		contentPane.add(btnNewButton);
	}

	public void destroyAddGroupScreen() {
		listID = "";
		listName = "";
		selectedAdd = "";
		selectedRemove = "";
		nameGroup = "";
		notifyCreateGroup = "";

		int sizeDmodelSelected = dmodelSelected.size();
		while (sizeDmodelSelected != 0) {
			dmodelSelected.removeElementAt(sizeDmodelSelected - 1);
			sizeDmodelSelected -= 1;
		}
		int sizeDmodel = dmodel.size();
		if (sizeDmodel != 0) {
			while (sizeDmodel != 0) {
				dmodel.removeElementAt(sizeDmodel - 1);
				sizeDmodel -= 1;
			}
		}

		createRoomForID.clear();
		textField.setText("");
	}

	public void setID(String _id) {
		id = _id;
	}

	public void showlistFriend(String listIdAndName) {
//		String listIdAndName = data.substring(1, data.length() - 1);

		listName = listIdAndName.split("], ")[0].replace("[", "");
		listID = listIdAndName.split("], ")[1].replace("]", "").replace("[", "");

		for (int i = 0; i < listName.split(", ").length; i++) {
			dmodel.addElement(listName.split(", ")[i]);
		}
	}

	public void checkMessage(String notify) {
		JOptionPane.showMessageDialog(panel, notify, "Notify", JOptionPane.INFORMATION_MESSAGE);
	}
}
