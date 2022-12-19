package Client.Views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JPasswordField;

public class RegisterScreen extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JTextField textField_1;
	private JButton btnCancel;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterScreen frame = new RegisterScreen();
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
	public RegisterScreen() {
		setTitle("Register");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

		textField = new JTextField();
		lblNewLabel_1.setLabelFor(textField);
		textField.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		textField.setBounds(182, 56, 186, 29);
		contentPane.add(textField);
		textField.setColumns(10);

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

		textField_1 = new JTextField();
		lblNewLabel_2.setLabelFor(textField_1);
		textField_1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		textField_1.setColumns(10);
		textField_1.setBounds(182, 95, 186, 29);
		contentPane.add(textField_1);

		JCheckBox chckbxNewCheckBox = new JCheckBox("Show password");
		chckbxNewCheckBox.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		chckbxNewCheckBox.setBounds(182, 217, 130, 21);
		contentPane.add(chckbxNewCheckBox);

		JButton btnNewButton = new JButton("REGISTER");
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnNewButton.setBounds(309, 262, 117, 32);
		contentPane.add(btnNewButton);

		btnCancel = new JButton("BACK");
		btnCancel.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnCancel.setBounds(182, 262, 117, 32);
		contentPane.add(btnCancel);

		passwordField = new JPasswordField();
		lblNewLabel_3.setLabelFor(passwordField);
		passwordField.setBounds(182, 137, 186, 29);
		contentPane.add(passwordField);

		passwordField_1 = new JPasswordField();
		lblNewLabel_4.setLabelFor(passwordField_1);
		passwordField_1.setBounds(182, 176, 186, 29);
		contentPane.add(passwordField_1);
	}
}
