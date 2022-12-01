package Client.Views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class ChangePasswordScreen extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField passwordField;
	private JLabel lblNewPassword;
	private JLabel lblAgainNewPassword;
	private JPasswordField passwordField_1;
	private JPasswordField passwordField_2;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChangePasswordScreen frame = new ChangePasswordScreen();
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
	public ChangePasswordScreen() {
		setTitle("Change Password");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 380, 188);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Current Password");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel.setBounds(28, 26, 130, 17);
		contentPane.add(lblNewLabel);

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		passwordField.setBounds(173, 23, 163, 22);
		contentPane.add(passwordField);

		lblNewPassword = new JLabel("New Password");
		lblNewPassword.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewPassword.setBounds(28, 58, 130, 17);
		contentPane.add(lblNewPassword);

		lblAgainNewPassword = new JLabel("Again New Password");
		lblAgainNewPassword.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblAgainNewPassword.setBounds(28, 90, 130, 17);
		contentPane.add(lblAgainNewPassword);

		passwordField_1 = new JPasswordField();
		passwordField_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		passwordField_1.setBounds(173, 55, 163, 22);
		contentPane.add(passwordField_1);

		passwordField_2 = new JPasswordField();
		passwordField_2.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		passwordField_2.setBounds(173, 87, 163, 22);
		contentPane.add(passwordField_2);

		btnNewButton = new JButton("Change");
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnNewButton.setBounds(251, 119, 85, 21);
		contentPane.add(btnNewButton);
	}
}
