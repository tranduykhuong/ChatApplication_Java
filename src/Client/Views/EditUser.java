package Client.Views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import Client.Controller;
import Entity.Packet;

public class EditUser extends JFrame {
	private static final long serialVersionUID = 1L;
	private static final String EMAIL_PATTERN = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
	private JPanel contentPane;
	private JTextField nameFd;
	private JTextField fnameFd;
	private JTextField addFd;
	private JTextField emailFd;
	private JRadioButton namFd;
	private JRadioButton nuFd;
	private JDateChooser dateFd;
	private JRadioButton rdbtnCustomer;
	private JRadioButton rdbtnAdmin;
	private boolean flagGender;
	private boolean flagRole;
	private ImageIcon iconTitle = new ImageIcon(HomeScreen.class.getResource("/Image/iconmini.jpg"));

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditUser frame = new EditUser("", "", "", true, null, "", "", true);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public EditUser(String id, String userName, String fullName, boolean gender, java.util.Date date, String address,
			String email, boolean role) {
		Image icon = iconTitle.getImage();
		setIconImage(icon);
		setTitle("Update Account");
		setBounds(100, 100, 471, 463);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblCpNhtNgi = new JLabel("Cập nhật người dùng");
		lblCpNhtNgi.setBounds(164, 10, 140, 16);
		lblCpNhtNgi.setForeground(new Color(30, 113, 225));
		lblCpNhtNgi.setFont(new Font("Tahoma", Font.BOLD, 13));
		contentPane.add(lblCpNhtNgi);

		JButton btnBack = new JButton("Trở về");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnBack.setForeground(new Color(1, 128, 254));
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnBack.setBounds(10, 5, 89, 21);
		contentPane.add(btnBack);
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 51, 437, 365);
		contentPane.add(panel);
		JLabel lblNewLabel_1 = new JLabel("Tên người dùng:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(30, 29, 106, 13);
		panel.add(lblNewLabel_1);

		nameFd = new JTextField();
		nameFd.setFont(new Font("Tahoma", Font.PLAIN, 13));
		nameFd.setColumns(10);
		nameFd.setBounds(147, 24, 253, 22);
		panel.add(nameFd);

		JLabel lblNewLabel_1_1 = new JLabel("Họ tên:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_1.setBounds(30, 71, 106, 13);
		panel.add(lblNewLabel_1_1);

		fnameFd = new JTextField();
		fnameFd.setFont(new Font("Tahoma", Font.PLAIN, 13));
		fnameFd.setColumns(10);
		fnameFd.setBounds(147, 66, 253, 22);
		panel.add(fnameFd);

		JLabel lblNewLabel_1_1_1 = new JLabel("Địa chỉ:");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_1_1.setBounds(30, 113, 106, 13);
		panel.add(lblNewLabel_1_1_1);
		JLabel lblNewLabel_1_1_2 = new JLabel("Ngày sinh:");
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_1_2.setBounds(30, 155, 106, 13);
		panel.add(lblNewLabel_1_1_2);
		JLabel lblNewLabel_1_1_3 = new JLabel("Giới tính:");
		lblNewLabel_1_1_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_1_3.setBounds(30, 197, 106, 13);
		panel.add(lblNewLabel_1_1_3);
		JLabel lblNewLabel_1_1_4 = new JLabel("Email:");
		lblNewLabel_1_1_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_1_4.setBounds(30, 239, 106, 13);
		panel.add(lblNewLabel_1_1_4);

		addFd = new JTextField();
		addFd.setFont(new Font("Tahoma", Font.PLAIN, 13));
		addFd.setColumns(10);
		addFd.setBounds(147, 108, 253, 22);
		panel.add(addFd);

		emailFd = new JTextField();
		emailFd.setFont(new Font("Tahoma", Font.PLAIN, 13));
		emailFd.setColumns(10);
		emailFd.setBounds(147, 234, 253, 22);
		panel.add(emailFd);

		namFd = new JRadioButton("Nam");
		namFd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				nuFd.setSelected(false);
				namFd.setSelected(true);
				flagGender = false;
			}
		});
		namFd.setFont(new Font("Tahoma", Font.BOLD, 13));
		namFd.setBounds(148, 194, 103, 21);
		panel.add(namFd);

		nuFd = new JRadioButton("Nữ");
		nuFd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				namFd.setSelected(false);
				nuFd.setSelected(true);
				flagGender = true;
			}
		});
		nuFd.setFont(new Font("Tahoma", Font.BOLD, 13));
		nuFd.setBounds(297, 194, 103, 21);
		panel.add(nuFd);

		dateFd = new JDateChooser();
		dateFd.setBounds(147, 155, 253, 19);
		panel.add(dateFd);

		JButton btnLuThayi = new JButton("Lưu thay đổi");
		btnLuThayi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateInfor(id);
			}
		});
		btnLuThayi.setForeground(new Color(1, 128, 254));
		btnLuThayi.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnLuThayi.setBounds(147, 320, 155, 21);
		panel.add(btnLuThayi);

		JLabel lblNewLabel_1_1_4_1 = new JLabel("Role:");
		lblNewLabel_1_1_4_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_1_4_1.setBounds(30, 280, 106, 13);
		panel.add(lblNewLabel_1_1_4_1);

		rdbtnCustomer = new JRadioButton("User");
		rdbtnCustomer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rdbtnAdmin.setSelected(false);
				rdbtnCustomer.setSelected(true);
				flagRole = true;
			}
		});
		rdbtnCustomer.setFont(new Font("Tahoma", Font.BOLD, 13));
		rdbtnCustomer.setBounds(147, 275, 103, 21);
		panel.add(rdbtnCustomer);

		rdbtnAdmin = new JRadioButton("Admin");
		rdbtnAdmin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rdbtnCustomer.setSelected(false);
				rdbtnAdmin.setSelected(true);
				flagRole = false;
			}
		});
		rdbtnAdmin.setFont(new Font("Tahoma", Font.BOLD, 13));
		rdbtnAdmin.setBounds(297, 275, 103, 21);
		panel.add(rdbtnAdmin);

		showCurrentInfor(userName, fullName, gender, date, address, email, role);
	}

	public void showCurrentInfor(String userName, String fullName, boolean gender, java.util.Date date, String address,
			String email, boolean role) {

		nameFd.setText(userName);
		fnameFd.setText(fullName);
		addFd.setText(address);
		dateFd.setDate(date);
		emailFd.setText(email);

		if (gender == false) {
			namFd.setSelected(true);
			nuFd.setSelected(false);
			flagGender = false;
		} else {
			namFd.setSelected(false);
			nuFd.setSelected(true);
			flagGender = true;
		}

		if (role == false) {
			rdbtnAdmin.setSelected(true);
			rdbtnCustomer.setSelected(false);
			rdbtnAdmin.setEnabled(false);
			rdbtnCustomer.setEnabled(false);
			flagRole = false;
		} else {
			rdbtnAdmin.setSelected(false);
			rdbtnCustomer.setSelected(true);
			rdbtnAdmin.setEnabled(true);
			rdbtnCustomer.setEnabled(true);
			flagRole = true;
		}

	}

	public void UpdateInfor(String id) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String strDate = formatter.format(dateFd.getDate());

		int response = JOptionPane.showConfirmDialog(this, "Are you sure to update this account?", "Update Account",
				JOptionPane.YES_NO_OPTION);
		if (response == JOptionPane.YES_OPTION) {

			if (nameFd.getText().equals("") || fnameFd.getText().equals("") || addFd.getText().equals("")
					|| emailFd.getText().equals("") || !emailFd.getText().matches(EMAIL_PATTERN)) {
				JOptionPane.showMessageDialog(this, "Field invalid!", "Warning", JOptionPane.WARNING_MESSAGE);
				return;
			}

			Controller.getInstance().handleScreen("loadingScreen", true);

			Controller.getInstance()
					.sendTextMessage(new Packet("updateAccount",
							id + ", " + nameFd.getText() + ", " + fnameFd.getText() + ", " + strDate + ", " + flagGender
									+ ", " + addFd.getText() + ", " + emailFd.getText() + ", " + flagRole,
							"").toString());

			Controller.getInstance().sendTextMessage(new Packet("showDetail",
					nameFd.getText() + ", " + fnameFd.getText() + ", " + addFd.getText() + ", " + emailFd.getText(), "")
					.toString());

			Controller.getInstance().handleScreen("loadingScreen", true);

			Controller.getInstance().sendTextMessage(new Packet("showAll", "", "").toString());
			setVisible(false);
		}
	}
}
