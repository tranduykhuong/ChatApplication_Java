package Client.Views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Client.Controller;
import Entity.Packet;

public class ManageUsersList extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel cpContain;
	private JTextField usernameField;
	private JTable tableUsersList;
	private Boolean statusFilterName = false;
	private Boolean statusFilterDate = false;
	private ImageIcon iconTitle = new ImageIcon(HomeScreen.class.getResource("/Image/iconmini.jpg"));

	public ManageUsersList() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2- getSize().width/2, dim.height/2 - getSize().height/2);
		Image icon = iconTitle.getImage();    
		setIconImage(icon); 
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
		setTitle("Qu???n l?? danh s??ch ng?????i d??ng");
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
		tableUsersList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tableUsersList.getSelectedRow();
				String username = (String) tableUsersList.getValueAt(row, 0);
				String fullname = (String) tableUsersList.getValueAt(row, 1);
				String address = (String) tableUsersList.getValueAt(row, 2);
				String dob = (String) tableUsersList.getValueAt(row, 3);
				String gender = (String) tableUsersList.getValueAt(row, 4);
				String email = (String) tableUsersList.getValueAt(row, 5);
				
				Controller.getInstance().handleScreen("loadingScreen", true);

				Controller.getInstance().sendTextMessage(
						new Packet("showDetail", username + ", " + fullname + ", " + address + ", " + email, "")
								.toString());
				
			}
		});
		scrollPane.setViewportView(tableUsersList);
		String[] tableString = new String[] { "T??n ????ng Nh???p", "H??? T??n", "?????a Ch???", "Ng??y Sinh", "Gi???i T??nh", "Email" };

		tableUsersList.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null, null, null, null }, { null, null, null, null, null, null },
						{ null, null, null, null, null, null }, { null, null, null, null, null, null },
						{ null, null, null, null, null, null }, { null, null, null, null, null, null },
						{ null, null, null, null, null, null }, { null, null, null, null, null, null },
						{ null, null, null, null, null, null }, { null, null, null, null, null, null },
						{ null, null, null, null, null, null }, { null, null, null, null, null, null },
						{ null, null, null, null, null, null }, { null, null, null, null, null, null },
						{ null, null, null, null, null, null }, { null, null, null, null, null, null },
						{ null, null, null, null, null, null }, { null, null, null, null, null, null },
						{ null, null, null, null, null, null }, { null, null, null, null, null, null },
						{ null, null, null, null, null, null }, { null, null, null, null, null, null },
						{ null, null, null, null, null, null }, { null, null, null, null, null, null },
						{ null, null, null, null, null, null }, { null, null, null, null, null, null },
						{ null, null, null, null, null, null }, { null, null, null, null, null, null },
						{ null, null, null, null, null, null }, { null, null, null, null, null, null },
						{ null, null, null, null, null, null }, { null, null, null, null, null, null },
						{ null, null, null, null, null, null }, { null, null, null, null, null, null },
						{ null, null, null, null, null, null }, { null, null, null, null, null, null },
						{ null, null, null, null, null, null }, { null, null, null, null, null, null },
						{ null, null, null, null, null, null }, { null, null, null, null, null, null },
						{ null, null, null, null, null, null }, { null, null, null, null, null, null },
						{ null, null, null, null, null, null }, { null, null, null, null, null, null },
						{ null, null, null, null, null, null }, { null, null, null, null, null, null },
						{ null, null, null, null, null, null }, { null, null, null, null, null, null },
						{ null, null, null, null, null, null }, { null, null, null, null, null, null }, },
				new String[] { "T\u00EAn \u0110\u0103ng Nh\u1EADp", "H\u1ECD T\u00EAn", "\u0110\u1ECBa Ch\u1EC9",
						"Ng\u00E0y Sinh", "Gi\u1EDBi T\u00EDnh", "Email" }));
		tableUsersList.getColumnModel().getColumn(0).setPreferredWidth(114);
		tableUsersList.getColumnModel().getColumn(1).setPreferredWidth(143);
		tableUsersList.getColumnModel().getColumn(2).setPreferredWidth(196);
		tableUsersList.getColumnModel().getColumn(3).setPreferredWidth(77);
		tableUsersList.getColumnModel().getColumn(5).setPreferredWidth(152);
		ListSelectionModel listSelectionModel = tableUsersList.getSelectionModel();
		listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableUsersList.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tableUsersList.setDefaultEditor(Object.class, null);

		JPanel pnControl = new JPanel();
		pnControl.setBackground(Color.WHITE);
		pnControl.setBounds(10, 10, 968, 82);
		cpContain.add(pnControl);
		pnControl.setLayout(null);

		JButton btnAddUser = new JButton("Th??m ng?????i d??ng");
		btnAddUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddUser AddUserFrame = new AddUser();
				Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
				AddUserFrame.setLocation(dim.width/2- AddUserFrame.getSize().width/2, dim.height/2 - AddUserFrame.getSize().height/2);
				AddUserFrame.setVisible(true);
			}
		});
		btnAddUser.setForeground(new Color(1, 128, 254));
		btnAddUser.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnAddUser.setBounds(769, 48, 171, 21);
		pnControl.add(btnAddUser);
		JLabel lbFilter = new JLabel("L???c theo t??n/t??n ????ng nh???p:");
		lbFilter.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbFilter.setBounds(31, 20, 200, 13);
		pnControl.add(lbFilter);

		usernameField = new JTextField();
		usernameField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		usernameField.setColumns(10);
		usernameField.setBounds(229, 15, 212, 22);
		pnControl.add(usernameField);

		JLabel lbArrange = new JLabel("S???p x???p theo:");
		lbArrange.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbArrange.setBounds(31, 52, 99, 13);
		pnControl.add(lbArrange);

		JButton btnSortName = new JButton("T??n");
		btnSortName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				statusFilterName = !statusFilterName;
				if (statusFilterName == true) {
					Controller.getInstance().handleScreen("loadingScreen", true);
					Controller.getInstance().sendTextMessage(new Packet("orderName", "1", "").toString());
				} else {
					Controller.getInstance().handleScreen("loadingScreen", true);
					Controller.getInstance().sendTextMessage(new Packet("orderName", "-1", "").toString());
				}
			}
		});
		btnSortName.setForeground(new Color(1, 128, 254));
		btnSortName.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSortName.setBounds(179, 48, 75, 21);
		pnControl.add(btnSortName);

		JButton btnSortDate = new JButton("Ng??y t???o");
		btnSortDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				statusFilterDate = !statusFilterDate;
				if (statusFilterDate == true) {
					Controller.getInstance().handleScreen("loadingScreen", true);
					Controller.getInstance().sendTextMessage(new Packet("orderCreateDate", "1", "").toString());
				} else {
					Controller.getInstance().handleScreen("loadingScreen", true);
					Controller.getInstance().sendTextMessage(new Packet("orderCreateDate", "-1", "").toString());
				}
			}
		});
		btnSortDate.setForeground(new Color(1, 128, 254));
		btnSortDate.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSortDate.setBounds(266, 48, 99, 21);
		pnControl.add(btnSortDate);

		JButton btnBack = new JButton("Log out");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().logout();
				setVisible(false);
			}
		});
		btnBack.setHorizontalAlignment(SwingConstants.LEFT);
		btnBack.setForeground(new Color(1, 128, 254));
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnBack.setBounds(880, 0, 88, 19);
		pnControl.add(btnBack);

		JButton btnXemChiTit = new JButton("Qu???n l?? ????ng nh???p");
		btnXemChiTit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().sendTextMessage(new Packet("listLoginTime", "", "").toString());
				Controller.getInstance().handleScreen("loadingScreen", true);
			}
		});
		btnXemChiTit.setForeground(new Color(1, 128, 254));
		btnXemChiTit.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnXemChiTit.setBounds(589, 17, 171, 21);
		pnControl.add(btnXemChiTit);

		JButton btnLc = new JButton("L???c");
		btnLc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().handleScreen("loadingScreen", true);
				Controller.getInstance()
						.sendTextMessage(new Packet("filterList", usernameField.getText(), "").toString());
			}
		});
		btnLc.setForeground(new Color(1, 128, 254));
		btnLc.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnLc.setBounds(451, 16, 68, 21);
		pnControl.add(btnLc);

		JButton btnDanhSchNhm = new JButton("Qu???n l?? nh??m chat");
		btnDanhSchNhm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().sendTextMessage(new Packet("listGroupChat", "", "").toString());
				Controller.getInstance().handleScreen("loadingScreen", true);
			}
		});
		btnDanhSchNhm.setForeground(new Color(1, 128, 254));
		btnDanhSchNhm.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnDanhSchNhm.setBounds(589, 48, 171, 21);
		pnControl.add(btnDanhSchNhm);

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
			System.out.println(listUser.get(i));
			String genderTmp = (listUser.get(i + 4).equals("true")) ? "N???" : "Nam";
			tableModel.addRow(new Object[] { listUser.get(i), listUser.get(i + 1), listUser.get(i + 2),
					listUser.get(i + 3), genderTmp, listUser.get(i + 5) });
		}
	}
	
	public void showInforWithRole(List<String> listUser) {
		if(listUser.size() > 1)
		{
		DefaultTableModel tableModel;
		tableUsersList.getModel();
		tableModel = (DefaultTableModel) tableUsersList.getModel();
		tableModel.setRowCount(0);
		for (int i = 0; i < listUser.size(); i = i + 8) {
			System.out.println(listUser.get(i));
			String genderTmp = (listUser.get(i + 4).equals("true")) ? "N???" : "Nam";
			tableModel.addRow(new Object[] { listUser.get(i), listUser.get(i + 1), listUser.get(i + 2),
					listUser.get(i + 3), genderTmp, listUser.get(i + 5) });
		}
	}
		else
		{
			DefaultTableModel tableModel;
			tableUsersList.getModel();
			tableModel = (DefaultTableModel) tableUsersList.getModel();
			tableModel.setRowCount(0);
			JOptionPane.showMessageDialog(this, "Kh??ng c?? d??? li???u!", "Warning", JOptionPane.WARNING_MESSAGE);
			return;
		}
	}
}
