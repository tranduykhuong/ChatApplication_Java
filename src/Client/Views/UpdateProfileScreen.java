package Client.Views;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class UpdateProfileScreen extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateProfileScreen frame = new UpdateProfileScreen();
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
	public UpdateProfileScreen() {
		setTitle("Update Profile");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 241);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(SystemColor.controlShadow));
		panel.setBounds(10, 10, 416, 184);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Họ tên");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel.setBounds(20, 26, 107, 13);
		panel.add(lblNewLabel);

		textField = new JTextField();
		lblNewLabel.setLabelFor(textField);
		textField.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textField.setBounds(20, 43, 172, 21);
		panel.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Tên đăng nhập");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_1.setBounds(46, 49, 107, 13);
		panel.add(lblNewLabel_1);

		JLabel lblHTn = new JLabel("Ngày sinh");
		lblHTn.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblHTn.setBounds(20, 85, 107, 13);
		panel.add(lblHTn);

		textField_1 = new JTextField();
		lblHTn.setLabelFor(textField_1);
		textField_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textField_1.setColumns(10);
		textField_1.setBounds(20, 101, 172, 21);
		panel.add(textField_1);

		JLabel lblaCh = new JLabel("Địa chỉ");
		lblaCh.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblaCh.setBounds(214, 26, 107, 13);
		panel.add(lblaCh);

		textField_2 = new JTextField();
		lblaCh.setLabelFor(textField_2);
		textField_2.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textField_2.setColumns(10);
		textField_2.setBounds(214, 43, 172, 21);
		panel.add(textField_2);

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "Nam", "Nữ" }));
		comboBox.setBounds(214, 101, 172, 21);
		panel.add(comboBox);

		JLabel lblGiiTnh = new JLabel("Giới tính");
		lblGiiTnh.setLabelFor(comboBox);
		lblGiiTnh.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblGiiTnh.setBounds(214, 85, 107, 13);
		panel.add(lblGiiTnh);

		JButton btnNewButton = new JButton("UPDATE");
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 10));
		btnNewButton.setBounds(166, 140, 85, 29);
		panel.add(btnNewButton);
	}
}
