package Client.Views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.CompoundBorder;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Client.Controller;
import Entity.Packet;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ManageUsersList extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel cpContain;
	private JTextField usernameField;
	private JTable tableUsersList;
	private Boolean statusFilterName = false;
	private Boolean statusFilterDate = false;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageUsersList frame = new ManageUsersList();
					frame.setVisible(true);
					frame.setTitle("Quản lí danh sách người dùng");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ManageUsersList() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1002, 555);
		cpContain = new JPanel();
		cpContain.setBackground(SystemColor.menu);
		cpContain.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(cpContain);
		cpContain.setLayout(null);

		JPanel pnInforUsers = new JPanel();
		pnInforUsers.setBackground(Color.WHITE);
		pnInforUsers.setBorder(new CompoundBorder(new EmptyBorder(1, 1, 1, 1), null));
		pnInforUsers.setBounds(10, 99, 968, 410);
		cpContain.add(pnInforUsers);
		pnInforUsers.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 0, 948, 400);
		pnInforUsers.add(panel);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 948, 390);
		panel.add(scrollPane);

		tableUsersList = new JTable();
		scrollPane.setViewportView(tableUsersList);
		String[] tableString = new String[] { "Tên Đăng Nhập", "Họ Tên", "Địa Chỉ", "Ngày Sinh", "Giới Tính", "Email" };
		tableUsersList.setModel(new DefaultTableModel(new Object[][] {
				{ "HTVinh", "Huynh Tan Vinh", "135 Tran Hung Dao, Q1, TP Ho Chi Minh", "01/01/1111", "Nam",
						"htvinh201@gmail.com" },
				{ "TAKhoi", "Tran Anh Khoi", "135 Tran Hung Dao, Q1, TP Ho Chi Minh", "01/01/1111", "Nam",
						"takhoi@gmail.com" },
				{ "TAKhoi", "Tran Anh Khoi", "135 Tran Hung Dao, Q1, TP Ho Chi Minh", "01/01/1111", "Nam",
						"takhoi@gmail.com" }, },
				tableString) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			Class[] columnTypes = new Class[] { String.class, Object.class, Object.class, Object.class, Object.class,
					Object.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		ListSelectionModel listSelectionModel = tableUsersList.getSelectionModel();
		listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listSelectionModel.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				int[] rows = tableUsersList.getSelectedRows();
				int[] cols = tableUsersList.getSelectedColumns();
				String codeString = String.valueOf(tableUsersList.getValueAt(rows[0], 0));
			}
		});

		tableUsersList.getColumnModel().getColumn(0).setPreferredWidth(114);
		tableUsersList.getColumnModel().getColumn(1).setPreferredWidth(143);
		tableUsersList.getColumnModel().getColumn(2).setPreferredWidth(196);
		tableUsersList.getColumnModel().getColumn(3).setPreferredWidth(77);
		tableUsersList.getColumnModel().getColumn(5).setPreferredWidth(152);
		tableUsersList.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tableUsersList.setDefaultEditor(Object.class, null);

		JPanel pnControl = new JPanel();
		pnControl.setBackground(Color.WHITE);
		pnControl.setBounds(10, 10, 968, 82);
		cpContain.add(pnControl);
		pnControl.setLayout(null);

		JButton btnAddUser = new JButton("Thêm người dùng");
		btnAddUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddUser AddUserFrame = new AddUser();
				AddUserFrame.setVisible(true);
			}
		});
		btnAddUser.setForeground(new Color(1, 128, 254));
		btnAddUser.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnAddUser.setBounds(589, 48, 150, 21);
		pnControl.add(btnAddUser);

		JLabel lbFilter = new JLabel("Lọc theo tên/tên đăng nhập:");
		lbFilter.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbFilter.setBounds(31, 20, 200, 13);
		pnControl.add(lbFilter);

		usernameField = new JTextField();
		usernameField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		usernameField.setColumns(10);
		usernameField.setBounds(229, 15, 212, 22);
		pnControl.add(usernameField);

		JLabel lbArrange = new JLabel("Sắp xếp theo:");
		lbArrange.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbArrange.setBounds(31, 52, 99, 13);
		pnControl.add(lbArrange);

		JButton btnSortName = new JButton("Tên");
		btnSortName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				statusFilterName = !statusFilterName;
				if (statusFilterName == true) {
					Controller.getInstance().sendTextMessage(new Packet("orderName", "1", "").toString());
				} else {
					Controller.getInstance().sendTextMessage(new Packet("orderName", "-1", "").toString());
				}
			}
		});
		btnSortName.setForeground(new Color(1, 128, 254));
		btnSortName.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSortName.setBounds(179, 48, 75, 21);
		pnControl.add(btnSortName);

		JButton btnSortDate = new JButton("Ngày tạo");
		btnSortDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				statusFilterDate = !statusFilterDate;
				if (statusFilterDate == true) {
					Controller.getInstance().sendTextMessage(new Packet("orderCreateDate", "1", "").toString());
				} else {
					Controller.getInstance().sendTextMessage(new Packet("orderCreateDate", "-1", "").toString());
				}
			}
		});
		btnSortDate.setForeground(new Color(1, 128, 254));
		btnSortDate.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSortDate.setBounds(266, 48, 99, 21);
		pnControl.add(btnSortDate);

		JButton btnBack = new JButton("Trở về");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				setVisible(false);
			}
		});
		btnBack.setHorizontalAlignment(SwingConstants.LEFT);
		btnBack.setForeground(new Color(1, 128, 254));
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnBack.setBounds(0, 0, 78, 19);
		pnControl.add(btnBack);

		JButton btnXa = new JButton("Xóa");
		btnXa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			
			}
		});
		btnXa.setForeground(new Color(1, 128, 254));
		btnXa.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnXa.setBounds(771, 48, 68, 21);
		pnControl.add(btnXa);

		JButton btnSa_1 = new JButton("Khóa");
		btnSa_1.setForeground(new Color(1, 128, 254));
		btnSa_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSa_1.setBounds(867, 47, 75, 21);
		pnControl.add(btnSa_1);

		JButton btnXemChiTit = new JButton("Quản lí đăng nhập");
		btnXemChiTit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().sendTextMessage(new Packet("listLoginTime", "", "").toString());
				setVisible(false);
			}
		});
		btnXemChiTit.setForeground(new Color(1, 128, 254));
		btnXemChiTit.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnXemChiTit.setBounds(589, 17, 171, 21);
		pnControl.add(btnXemChiTit);

		JButton btnLc = new JButton("Lọc");
		btnLc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance()
						.sendTextMessage(new Packet("filterList", usernameField.getText(), "").toString());
			}
		});
		btnLc.setForeground(new Color(1, 128, 254));
		btnLc.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnLc.setBounds(451, 16, 68, 21);
		pnControl.add(btnLc);

		JButton btnDanhSchNhm = new JButton("Quản lí nhóm chat");
		btnDanhSchNhm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().sendTextMessage(new Packet("listGroupChat", "", "").toString());
				setVisible(false);
			}
		});
		btnDanhSchNhm.setForeground(new Color(1, 128, 254));
		btnDanhSchNhm.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnDanhSchNhm.setBounds(771, 17, 171, 21);
		pnControl.add(btnDanhSchNhm);

		// String str = "hello, 13/12/2022 01:54:32, alo, 13/12/2022 01:54:49, � nhon,
		// 13/12/2022 01:55:07, 9h, 13/12/2022 10:34:13]";
		// ArrayList<String> infoCreatedGroupSorted = new ArrayList<>();
		// String[] infoGroupDate = str.split(", ");
		// ArrayList<List<String>> listCreateDateGroup = new ArrayList<>();
		// for(int i = 0; i < infoGroupDate.length; i++) {
		// infoCreatedGroupSorted.add(infoGroupDate[i] + ", " + infoGroupDate[++i]);
		// }
		//
		// for(int i = 0; i < infoCreatedGroupSorted.size();i++) {
		// List<String> myList = new
		// ArrayList<String>(Arrays.asList(infoCreatedGroupSorted.get(i).split(", ")));
		// listCreateDateGroup.add(myList);
		// }
		//
		// for (int i1 = 0; i1 < listCreateDateGroup.size() - 1; i1++) {
		// SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		// String startDate = listCreateDateGroup.get(i1).get(1);
		// String endDate = listCreateDateGroup.get(i1 + 1).get(1);
		//// System.out.println(listCreateDateGroup.get(i1));
		// try {
		// if (sdf.parse(startDate).after(sdf.parse(endDate))) {
		// Collections.swap(listCreateDateGroup, i1, i1 + 1);
		// }
		// } catch (ParseException e1) {
		// e1.printStackTrace();
		// }
		// }
		//
		// ArrayList<String> finalSortGroupList = new ArrayList<>();
		// for(int i = 0; i < listCreateDateGroup.size(); i++) {
		// finalSortGroupList.add(listCreateDateGroup.get(i).get(0));
		// System.out.println(listCreateDateGroup.get(i));
		// }
		// for (int i = 0; i < finalSortGroupList.size(); i++) {
		// System.out.println(finalSortGroupList.get(i));
		// }
	}

	public void run() {
		Controller.getInstance().sendTextMessage(new Packet("showAll", "", "").toString());
	}

	public void showInfor(List<String> listUser) {
		DefaultTableModel tableModel;
		tableUsersList.getModel();
		tableModel = (DefaultTableModel) tableUsersList.getModel();
		tableModel.setRowCount(0);
		for (int i = 0; i < listUser.size(); i = i + 6) {
			String genderTmp = (listUser.get(i + 4).equals("true")) ? "Nữ" : "Nam";
			tableModel.addRow(new Object[] { listUser.get(i), listUser.get(i + 1), listUser.get(i + 2),
					listUser.get(i + 3), genderTmp, listUser.get(i + 5) });
		}
	}
}
