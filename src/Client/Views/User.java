package Client.Views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JDateChooser;

import Client.Controller;
import Entity.Packet;

public class User extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField usernameFd;
	private JTextField fullnameFd;
	private JTextField addrFd;
	private JDateChooser dateChooser;
	private JRadioButton maleTick;
	private JRadioButton femaleTick;
	private JTextField emailFd;
	private JRadioButton btncus;
	private JRadioButton btnadm;
	private boolean flagGender;
	private boolean flagRole;
	private JButton btnKho;
	private JList<String> listFriend;
	private JList<String> historyLogin;
	private ImageIcon iconTitle = new ImageIcon(HomeScreen.class.getResource("/Image/iconmini.jpg"));

	String username;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					User frame = new User("", "", "", "", "", "", "");
					frame.setVisible(true);
					frame.setTitle("User");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void handleResetPassword() {
		Controller.getInstance().resetPassword(username);
		JOptionPane.showMessageDialog(this, "New password sent to this account's email!", "Success",
				JOptionPane.INFORMATION_MESSAGE);
	}

	public User(String id, String username, String fullname, String addr, String dob, String gender, String email) {
		this.username = username;
		Image icon = iconTitle.getImage();
		setIconImage(icon);
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 749);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 65, 516, 647);
		contentPane.add(panel);
		panel.setLayout(null);

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(28, 10, 455, 300);
		panel.add(panel_2);

		JLabel lblNewLabel_1 = new JLabel("Tên người dùng:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(30, 29, 106, 13);
		panel_2.add(lblNewLabel_1);

		usernameFd = new JTextField();
		usernameFd.setEditable(false);
		usernameFd.setFont(new Font("Tahoma", Font.PLAIN, 13));
		usernameFd.setColumns(10);
		usernameFd.setBounds(157, 24, 253, 22);
		panel_2.add(usernameFd);

		JLabel lblNewLabel_1_1 = new JLabel("Họ tên:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_1.setBounds(30, 71, 106, 13);
		panel_2.add(lblNewLabel_1_1);

		fullnameFd = new JTextField();
		fullnameFd.setEditable(false);
		fullnameFd.setFont(new Font("Tahoma", Font.PLAIN, 13));
		fullnameFd.setColumns(10);
		fullnameFd.setBounds(157, 66, 253, 22);
		panel_2.add(fullnameFd);

		JLabel lblNewLabel_1_1_1 = new JLabel("Địa chỉ:");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_1_1.setBounds(30, 113, 106, 13);
		panel_2.add(lblNewLabel_1_1_1);
		JLabel lblNewLabel_1_1_2 = new JLabel("Ngày sinh:");
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_1_2.setBounds(30, 155, 106, 13);
		panel_2.add(lblNewLabel_1_1_2);

		JLabel lblNewLabel_1_1_3 = new JLabel("Giới tính:");
		lblNewLabel_1_1_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_1_3.setBounds(30, 197, 106, 13);
		panel_2.add(lblNewLabel_1_1_3);
		JLabel lblNewLabel_1_1_4 = new JLabel("Email:");
		lblNewLabel_1_1_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_1_4.setBounds(30, 239, 106, 13);
		panel_2.add(lblNewLabel_1_1_4);

		addrFd = new JTextField();
		addrFd.setEditable(false);
		addrFd.setFont(new Font("Tahoma", Font.PLAIN, 13));
		addrFd.setColumns(10);
		addrFd.setBounds(157, 108, 253, 22);
		panel_2.add(addrFd);

		emailFd = new JTextField();
		emailFd.setEditable(false);
		emailFd.setFont(new Font("Tahoma", Font.PLAIN, 13));
		emailFd.setColumns(10);
		emailFd.setBounds(157, 234, 253, 22);
		panel_2.add(emailFd);

		maleTick = new JRadioButton("Nam");
		maleTick.setEnabled(false);
		maleTick.setFont(new Font("Tahoma", Font.BOLD, 13));
		maleTick.setBounds(157, 193, 103, 21);
		panel_2.add(maleTick);

		femaleTick = new JRadioButton("Nữ");
		femaleTick.setEnabled(false);
		femaleTick.setFont(new Font("Tahoma", Font.BOLD, 13));
		femaleTick.setBounds(307, 193, 103, 21);
		panel_2.add(femaleTick);

		dateChooser = new JDateChooser();
		dateChooser.setEnabled(false);
		dateChooser.setBackground(new Color(255, 255, 255));
		dateChooser.setBounds(157, 155, 253, 19);
		panel_2.add(dateChooser);

		JLabel lblNewLabel_1_1_3_1 = new JLabel("Role:");
		lblNewLabel_1_1_3_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_1_3_1.setBounds(30, 277, 106, 13);
		panel_2.add(lblNewLabel_1_1_3_1);

		btncus = new JRadioButton("User");
		btncus.setFont(new Font("Tahoma", Font.BOLD, 13));
		btncus.setEnabled(false);
		btncus.setBounds(157, 274, 103, 21);
		panel_2.add(btncus);

		btnadm = new JRadioButton("Admin");
		btnadm.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnadm.setEnabled(false);
		btnadm.setBounds(307, 274, 103, 21);
		panel_2.add(btnadm);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(10, 320, 496, 281);
		panel.add(panel_3);
		panel_3.setLayout(new GridLayout(0, 2, 0, 0));

		listFriend = new JList<String>();
		listFriend.setFont(new Font("Tahoma", Font.PLAIN, 14));
		listFriend.setBorder(new TitledBorder(null, "Danh s\u00E1ch b\u1EA1n b\u00E8", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		listFriend.setBackground(new Color(236, 251, 255));
		JScrollPane scrollPane1 = new JScrollPane(listFriend);
		panel_3.add(scrollPane1);

		historyLogin = new JList<String>();
		historyLogin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		historyLogin.setBorder(new TitledBorder(null, "L\u1ECBch s\u1EED \u0111\u0103ng nh\u1EADp",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		historyLogin.setBackground(new Color(236, 251, 255));
		JScrollPane scrollPane = new JScrollPane(historyLogin);
		panel_3.add(scrollPane);

		JButton btnCpNhtThng = new JButton("Chỉnh sửa thông tin");
		btnCpNhtThng.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditUser UpdateFrame = new EditUser(id, usernameFd.getText(), fullnameFd.getText(), flagGender,
						dateChooser.getDate(), addrFd.getText(), emailFd.getText(), flagRole);
				UpdateFrame.setVisible(true);
			}
		});
		btnCpNhtThng.setBounds(204, 612, 165, 25);
		panel.add(btnCpNhtThng);
		btnCpNhtThng.setHorizontalAlignment(SwingConstants.LEFT);
		btnCpNhtThng.setForeground(new Color(1, 128, 254));
		btnCpNhtThng.setFont(new Font("Tahoma", Font.BOLD, 13));

		JButton btniMtKhu = new JButton("Đổi mật khẩu");
		btniMtKhu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleResetPassword();
			}
		});
		btniMtKhu.setBounds(372, 612, 134, 25);
		panel.add(btniMtKhu);
		btniMtKhu.setForeground(new Color(1, 128, 254));
		btniMtKhu.setFont(new Font("Tahoma", Font.BOLD, 13));

		JButton btnXa = new JButton("Xóa");
		btnXa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeleteAccount();
			}
		});
		btnXa.setForeground(new Color(1, 128, 254));
		btnXa.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnXa.setBounds(113, 612, 87, 25);
		panel.add(btnXa);

		btnKho = new JButton("Khóa");
		btnKho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BlockAccount();
			}
		});
		btnKho.setForeground(new Color(1, 128, 254));
		btnKho.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnKho.setBounds(10, 612, 98, 25);
		panel.add(btnKho);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 10, 516, 45);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JButton btnBack = new JButton("Trở về");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnBack.setBounds(0, 0, 75, 25);
		btnBack.setHorizontalAlignment(SwingConstants.LEFT);
		btnBack.setForeground(new Color(1, 128, 254));
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel_1.add(btnBack);
		JLabel lblChiTitNgi = new JLabel("Chi tiết người dùng");
		lblChiTitNgi.setBounds(219, 4, 125, 16);
		lblChiTitNgi.setForeground(new Color(1, 128, 254));
		lblChiTitNgi.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel_1.add(lblChiTitNgi);
	}

	public void BlockAccount() {
		if (btnKho.getText() == "Khóa") {
			int response = JOptionPane.showConfirmDialog(this, "Are you sure to block this account?", "Block Account",
					JOptionPane.YES_NO_OPTION);
			if (response == JOptionPane.YES_OPTION) {
				String activeSt = "0";
				Controller.getInstance()
						.sendTextMessage(new Packet("lockAccount", usernameFd.getText() + ", " + fullnameFd.getText()
								+ ", " + addrFd.getText() + ", " + emailFd.getText() + ", " + activeSt, "").toString());
				JOptionPane.showMessageDialog(this, "Khóa thành công");
				setVisible(false);
			}
		} else {
			int response = JOptionPane.showConfirmDialog(this, "Are you sure to unblock this account?",
					"Unblock Account", JOptionPane.YES_NO_OPTION);
			if (response == JOptionPane.YES_OPTION) {
				String activeSt = "1";
				Controller.getInstance()
						.sendTextMessage(new Packet("lockAccount", usernameFd.getText() + ", " + fullnameFd.getText()
								+ ", " + addrFd.getText() + ", " + emailFd.getText() + ", " + activeSt, "").toString());
				JOptionPane.showMessageDialog(this, "Mở khóa thành công");
				setVisible(false);
			}
		}

	}

	public void DeleteAccount() {
		int response = JOptionPane.showConfirmDialog(this, "Are you sure to delete this account?", "Delete Account",
				JOptionPane.YES_NO_OPTION);
		if (response == JOptionPane.YES_OPTION) {
			Controller.getInstance().sendTextMessage(new Packet("removeAccount", usernameFd.getText() + ", "
					+ fullnameFd.getText() + ", " + addrFd.getText() + ", " + emailFd.getText(), "").toString());
			JOptionPane.showMessageDialog(this, "Xóa thành công");
			Controller.getInstance().sendTextMessage(new Packet("showAll", "", "").toString());
			setVisible(false);
		}
	}

	public void showInformation(String active, String username, String fullname, String addr, String dob, String gender,
			String email, String listfriend, String listLogin, String role) {
		System.out.print("Infor" + active + "/" + username + fullname + addr + dob + gender + email + role);
		usernameFd.setText(username);
		fullnameFd.setText(fullname);
		addrFd.setText(addr);
		emailFd.setText(email);

		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		try {
			java.util.Date date = formatter.parse(dob);
			dateChooser.setDateFormatString("dd/MM/YYYY");
			dateChooser.setDate(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (gender.equals("false")) {
			maleTick.setSelected(true);
			femaleTick.setSelected(false);
			flagGender = false;
		} else {
			maleTick.setSelected(false);
			femaleTick.setSelected(true);
			flagGender = true;
		}

		if (role.equals("admin")) {
			btnadm.setSelected(true);
			btncus.setSelected(false);
			flagRole = false;
		} else {
			btnadm.setSelected(false);
			btncus.setSelected(true);
			flagRole = true;
		}

		if (active.equals("1")) {
			btnKho.setText("Khóa");
		} else {
			btnKho.setText("Mở khóa");
		}

		// LIST
		// List Friend
		System.out.print("Nhom nhan: " + listfriend);
		List<String> ListFriend = new ArrayList<String>(Arrays.asList(listfriend.split(", ")));
		DefaultListModel<String> dlm = new DefaultListModel<String>();
		for (int i = 0; i < ListFriend.size(); i++) {
			dlm.addElement(ListFriend.get(i));
		}

		listFriend.setModel(dlm);

		// History login
		System.out.print("Lich su nhan: " + listLogin);
		List<String> HistoryLG = new ArrayList<String>(Arrays.asList(listLogin.split(", ")));
		DefaultListModel<String> dlm1 = new DefaultListModel<String>();
		for (int i = 0; i < HistoryLG.size(); i++) {
			dlm1.addElement(HistoryLG.get(i));
		}

		historyLogin.setModel(dlm1);
	}
}
