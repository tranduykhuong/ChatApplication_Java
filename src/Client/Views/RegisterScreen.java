package Client.Views;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import Client.Controller;

public class RegisterScreen extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField userNameField;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JTextField emailField;
	private JButton btnCancel;
	private JPasswordField passwordField;
	private JPasswordField confirmField;
	private ImageIcon iconTitle = new ImageIcon(HomeScreen.class.getResource("/Image/iconmini.jpg"));

	static boolean isValidEmail(String email) {
		String regex = "^(.+)@(\\S+)$";
		return email.matches(regex);
	}

	private boolean handleRegister() {
		String password = String.valueOf(passwordField.getPassword());
		String passwordConfirm = String.valueOf(confirmField.getPassword());
		if (userNameField.getText().length() == 0 || password.length() == 0 || emailField.getText().length() == 0
				|| passwordConfirm.length() == 0) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ các dữ liệu!", "Error",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}

		if (!password.equals(passwordConfirm)) {
			JOptionPane.showMessageDialog(this, "Confirm password phải giống password!", "Warn",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}

		if (!isValidEmail(emailField.getText())) {
			JOptionPane.showMessageDialog(this, "Email không hợp lệ!", "Warn", JOptionPane.WARNING_MESSAGE);
			return false;
		}

		Controller.getInstance().register(userNameField.getText(), emailField.getText(), password);
		return true;
	}

	public void showMessage(String content, String heading, int opt) {
		JOptionPane.showMessageDialog(this, content, heading, opt);
	}

	public RegisterScreen() {
		Image icon = iconTitle.getImage();
		setIconImage(icon);
		setTitle("Register");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				setVisible(false);
				Controller.getInstance().handleScreen("homeScreen", true);
			}
		});
		setBounds(100, 100, 450, 341);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("REGISTER");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel.setBounds(195, 10, 117, 36);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("UserName");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_1.setBounds(59, 57, 77, 25);
		contentPane.add(lblNewLabel_1);

		userNameField = new JTextField();
		lblNewLabel_1.setLabelFor(userNameField);
		userNameField.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		userNameField.setBounds(182, 56, 186, 29);
		contentPane.add(userNameField);
		userNameField.setColumns(10);

		lblNewLabel_2 = new JLabel("Email");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_2.setBounds(59, 96, 77, 25);
		contentPane.add(lblNewLabel_2);

		lblNewLabel_3 = new JLabel("Password");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_3.setBounds(59, 138, 77, 25);
		contentPane.add(lblNewLabel_3);

		lblNewLabel_4 = new JLabel("Again Password");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_4.setBounds(59, 180, 112, 25);
		contentPane.add(lblNewLabel_4);

		emailField = new JTextField();
		lblNewLabel_2.setLabelFor(emailField);
		emailField.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		emailField.setColumns(10);
		emailField.setBounds(182, 95, 186, 29);
		contentPane.add(emailField);

		JCheckBox showCheckBox = new JCheckBox("Show password");
		showCheckBox.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		showCheckBox.setBounds(182, 217, 130, 21);
		contentPane.add(showCheckBox);
		showCheckBox.addActionListener(ae -> {
			JCheckBox c = (JCheckBox) ae.getSource();
			passwordField.setEchoChar(c.isSelected() ? '\u0000' : (Character) UIManager.get("PasswordField.echoChar"));
			confirmField.setEchoChar(c.isSelected() ? '\u0000' : (Character) UIManager.get("PasswordField.echoChar"));
		});

		JButton btnRegister = new JButton("REGISTER");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleRegister();
			}
		});
		btnRegister.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnRegister.setBounds(309, 262, 117, 32);
		contentPane.add(btnRegister);

		btnCancel = new JButton("BACK");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().handleScreen("homeScreen", true);
				setVisible(false);
			}
		});
		btnCancel.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnCancel.setBounds(182, 262, 117, 32);
		contentPane.add(btnCancel);

		passwordField = new JPasswordField();
		lblNewLabel_3.setLabelFor(passwordField);
		passwordField.setBounds(182, 137, 186, 29);
		contentPane.add(passwordField);

		confirmField = new JPasswordField();
		lblNewLabel_4.setLabelFor(confirmField);
		confirmField.setBounds(182, 176, 186, 29);
		contentPane.add(confirmField);
	}
}
