package Client.Views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class LoginScreen extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_1;
	private JCheckBox chckbxNewCheckBox;
	private JButton btnNewButton;
	private JButton btnCancel;
	private JPasswordField passwordField;
	private JButton btnNewButton_1;

	public LoginScreen() {
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

		textField_1 = new JTextField();
		lblNewLabel_1.setLabelFor(textField_1);
		textField_1.setColumns(10);
		textField_1.setBounds(157, 73, 209, 25);
		contentPane.add(textField_1);

		chckbxNewCheckBox = new JCheckBox("Show Password");
		chckbxNewCheckBox.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		chckbxNewCheckBox.setBounds(157, 140, 130, 21);
		contentPane.add(chckbxNewCheckBox);

		btnNewButton = new JButton("LOG IN");
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btnNewButton.setBounds(327, 220, 99, 34);
		contentPane.add(btnNewButton);

		btnCancel = new JButton("BACK");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new HomeScreen().setVisible(true);
				setVisible(false);
			}
		});
		btnCancel.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btnCancel.setBounds(225, 220, 99, 34);
		contentPane.add(btnCancel);

		passwordField = new JPasswordField();
		passwordField.setBounds(157, 108, 209, 25);
		contentPane.add(passwordField);

		btnNewButton_1 = new JButton("Forger Password");
		btnNewButton_1.setForeground(new Color(0, 0, 160));
		btnNewButton_1.setBackground(new Color(192, 192, 192));
		btnNewButton_1.setFont(new Font("Times New Roman", Font.ITALIC, 14));
		btnNewButton_1.setBounds(277, 174, 149, 21);
		contentPane.add(btnNewButton_1);
	}

}
