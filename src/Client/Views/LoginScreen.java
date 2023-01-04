package Client.Views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
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

public class LoginScreen extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField userNameField;
	private JCheckBox showCheckBox;
	private JButton btnLogin;
	private JButton btnCancel;
	private JPasswordField passwordField;
	private JButton btnForgot;
	private ImageIcon iconTitle = new ImageIcon(HomeScreen.class.getResource("/Image/iconmini.jpg"));

	private boolean handleClickLogin() {
		if (userNameField.getText().length() == 0 || String.valueOf(passwordField.getPassword()).length() == 0) {
			JOptionPane.showMessageDialog(this, "Missing connection config", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		Controller.getInstance().login(userNameField.getText(), String.valueOf(passwordField.getPassword()));
		Controller.getInstance().handleScreen("loadingScreen", true);

		return true;
	}

	public void showMessage(String content, String heading, int opt) {
		JOptionPane.showMessageDialog(this, content, heading, opt);
	}

	public LoginScreen() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2- getSize().width/2, dim.height/2 - getSize().height/2);
		Image icon = iconTitle.getImage();
		setIconImage(icon);
		setTitle("Login");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				setVisible(false);
				Controller.getInstance().handleScreen("homeScreen", true);
			}
		});
		setBounds(100, 100, 450, 301);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("LOG IN");
		lblNewLabel.setForeground(new Color(0, 0, 160));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 23));
		lblNewLabel.setBounds(178, 0, 99, 40);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_1.setBounds(36, 75, 82, 16);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_2.setBounds(36, 112, 78, 16);
		contentPane.add(lblNewLabel_2);

		userNameField = new JTextField();
		lblNewLabel_1.setLabelFor(userNameField);
		userNameField.setColumns(10);
		userNameField.setBounds(157, 73, 209, 25);
		contentPane.add(userNameField);

		showCheckBox = new JCheckBox("Show Password");
		showCheckBox.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		showCheckBox.setBounds(157, 140, 130, 21);
		contentPane.add(showCheckBox);
		showCheckBox.addActionListener(ae -> {
			JCheckBox c = (JCheckBox) ae.getSource();
			passwordField.setEchoChar(c.isSelected() ? '\u0000' : (Character) UIManager.get("PasswordField.echoChar"));
		});

		btnLogin = new JButton("LOG IN");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleClickLogin();
			}
		});
		btnLogin.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btnLogin.setBounds(309, 220, 99, 34);
		contentPane.add(btnLogin);

		btnCancel = new JButton("BACK");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().handleScreen("homeScreen", true);
				setVisible(false);
			}
		});
		btnCancel.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btnCancel.setBounds(200, 220, 99, 34);
		contentPane.add(btnCancel);

		passwordField = new JPasswordField();
		passwordField.setBounds(157, 108, 209, 25);
		contentPane.add(passwordField);

		btnForgot = new JButton("Forger Password");
		btnForgot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().handleScreen("forgotScreen", true);
				setVisible(false);
			}
		});
		btnForgot.setForeground(new Color(0, 0, 160));
		btnForgot.setBackground(new Color(192, 192, 192));
		btnForgot.setFont(new Font("Times New Roman", Font.ITALIC, 14));
		btnForgot.setBounds(259, 179, 149, 21);
		contentPane.add(btnForgot);
	}

}
