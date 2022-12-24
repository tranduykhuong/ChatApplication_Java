package Client.Views;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.UUID;

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

import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.UUID;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddUser extends JFrame {
	private static final long serialVersionUID = 1L;
	private static final String EMAIL_PATTERN = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
	private JPanel contentPane;
	private JTextField userFd;
	private JTextField fullnameFd;
	private JTextField addrFd;
	private JRadioButton maleFd;
	private JRadioButton femaleFd;
	private JDateChooser dateChooser;
	private JTextField emailFd;
	private JRadioButton rdbtnUser;
	private JRadioButton rdbtnAdmin;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddUser frame = new AddUser();
					frame.setVisible(true);
					frame.setTitle("Add user");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AddUser() {
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 471, 463);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(30, 113, 225));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 55, 437, 365);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Tên người dùng:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(30, 29, 106, 13);
		panel.add(lblNewLabel_1);

		userFd = new JTextField();
		userFd.setFont(new Font("Tahoma", Font.PLAIN, 13));
		userFd.setBounds(147, 24, 253, 22);
		panel.add(userFd);
		userFd.setColumns(10);

		JLabel lblNewLabel_1_1 = new JLabel("Họ tên:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_1.setBounds(30, 71, 106, 13);
		panel.add(lblNewLabel_1_1);

		fullnameFd = new JTextField();
		fullnameFd.setFont(new Font("Tahoma", Font.PLAIN, 13));
		fullnameFd.setColumns(10);
		fullnameFd.setBounds(147, 66, 253, 22);
		panel.add(fullnameFd);

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

		addrFd = new JTextField();
		addrFd.setFont(new Font("Tahoma", Font.PLAIN, 13));
		addrFd.setColumns(10);
		addrFd.setBounds(147, 108, 253, 22);
		panel.add(addrFd);

		emailFd = new JTextField();
		emailFd.setFont(new Font("Tahoma", Font.PLAIN, 13));
		emailFd.setColumns(10);
		emailFd.setBounds(147, 234, 253, 22);
		panel.add(emailFd);

		maleFd = new JRadioButton("Nam");
		maleFd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				maleFd.setSelected(true);
				femaleFd.setSelected(false);
			}
		});
		maleFd.setFont(new Font("Tahoma", Font.BOLD, 13));
		maleFd.setBounds(148, 194, 103, 21);
		panel.add(maleFd);

		femaleFd = new JRadioButton("Nữ");
		femaleFd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				femaleFd.setSelected(true);
				maleFd.setSelected(false);
			}
		});
		femaleFd.setFont(new Font("Tahoma", Font.BOLD, 13));
		femaleFd.setBounds(297, 194, 103, 21);
		panel.add(femaleFd);

		dateChooser = new JDateChooser();
		dateChooser.setBounds(147, 155, 253, 19);
		panel.add(dateChooser);

		JButton btnThmNgiDng = new JButton("Thêm người dùng");
		btnThmNgiDng.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getCreateInfor();
			}
		});
		btnThmNgiDng.setForeground(new Color(1, 128, 254));
		btnThmNgiDng.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnThmNgiDng.setBounds(147, 320, 155, 21);
		panel.add(btnThmNgiDng);

		JLabel lblNewLabel_1_1_4_1 = new JLabel("Role:");
		lblNewLabel_1_1_4_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_1_4_1.setBounds(30, 275, 106, 13);
		panel.add(lblNewLabel_1_1_4_1);

		rdbtnUser = new JRadioButton("User");
		rdbtnUser.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rdbtnUser.setSelected(true);
				rdbtnAdmin.setSelected(false);
			}
		});
		rdbtnUser.setSelected(true);
		rdbtnUser.setFont(new Font("Tahoma", Font.BOLD, 13));
		rdbtnUser.setBounds(147, 271, 103, 21);
		panel.add(rdbtnUser);

		rdbtnAdmin = new JRadioButton("Admin");
		rdbtnAdmin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rdbtnAdmin.setSelected(true);
				rdbtnUser.setSelected(false);
			}
		});
		rdbtnAdmin.setFont(new Font("Tahoma", Font.BOLD, 13));
		rdbtnAdmin.setBounds(297, 271, 103, 21);
		panel.add(rdbtnAdmin);

		JLabel lblNewLabel = new JLabel("Thêm người dùng");
		lblNewLabel.setForeground(new Color(30, 113, 225));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(180, 10, 114, 21);
		contentPane.add(lblNewLabel);

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
	}

	public void getCreateInfor() {
		boolean gender;
		String role;

		if (userFd.getText().equals("") || fullnameFd.getText().equals("") || addrFd.getText().equals("") || emailFd.getText().equals("")
				|| !emailFd.getText().matches(EMAIL_PATTERN) || dateChooser.getDate() == null) {
			JOptionPane.showMessageDialog(this, "Field invalid!", "Warning", JOptionPane.WARNING_MESSAGE);
			return;
		}

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String strDate = formatter.format(dateChooser.getDate());

		if (maleFd.isSelected()) {
			gender = false;
		} else {
			gender = true;
		}
		
		if (rdbtnAdmin.isSelected()) {
			role = "admin";
		} else {
			role = "user";
		}

		String id = UUID.randomUUID().toString();
		String password = UUID.randomUUID().toString();

		Controller.getInstance()
				.sendTextMessage(new Packet("addAccount",
						id + ", " + userFd.getText() + ", " + fullnameFd.getText() + ", " + password + ", "
								+ addrFd.getText() + ", " + strDate + ", " + gender + ", " + emailFd.getText() + ", " + role,
						"").toString());
		JOptionPane.showMessageDialog(this, "Thêm thành công");
		Controller.getInstance().sendTextMessage(new Packet("showAll", "", "").toString());
		setVisible(false);
	}
}
