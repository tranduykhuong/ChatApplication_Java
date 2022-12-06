package Client.Views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import com.toedter.calendar.JDateChooser;

public class EditUser extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditUser frame = new EditUser();
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
	public EditUser() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField.setColumns(10);
		textField.setBounds(147, 24, 253, 22);
		panel.add(textField);
		
		JLabel lblNewLabel_1_1 = new JLabel("Họ tên:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_1.setBounds(30, 71, 106, 13);
		panel.add(lblNewLabel_1_1);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField_1.setColumns(10);
		textField_1.setBounds(147, 66, 253, 22);
		panel.add(textField_1);
		
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
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField_2.setColumns(10);
		textField_2.setBounds(147, 108, 253, 22);
		panel.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField_3.setColumns(10);
		textField_3.setBounds(147, 234, 253, 22);
		panel.add(textField_3);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Nam");
		rdbtnNewRadioButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		rdbtnNewRadioButton.setBounds(148, 194, 103, 21);
		panel.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnN = new JRadioButton("Nữ");
		rdbtnN.setFont(new Font("Tahoma", Font.BOLD, 13));
		rdbtnN.setBounds(297, 194, 103, 21);
		panel.add(rdbtnN);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(147, 155, 253, 19);
		panel.add(dateChooser);
		
		JButton btnLuThayi = new JButton("Lưu thay đổi");
		btnLuThayi.setForeground(new Color(1, 128, 254));
		btnLuThayi.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnLuThayi.setBounds(147, 320, 155, 21);
		panel.add(btnLuThayi);
	}
}
