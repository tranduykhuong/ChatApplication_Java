package Client.Views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import com.toedter.calendar.JDateChooser;
import javax.swing.border.TitledBorder;
import java.awt.GridLayout;
import javax.swing.JList;
import java.awt.SystemColor;
import javax.swing.AbstractListModel;

public class User extends JFrame {

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
					User frame = new User();
					frame.setVisible(true);
					frame.setTitle("User");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public User() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 725);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 58, 516, 620);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(28, 10, 455, 271);
		panel.add(panel_2);
		
		JLabel lblNewLabel_1 = new JLabel("Tên người dùng:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(30, 29, 106, 13);
		panel_2.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField.setColumns(10);
		textField.setBounds(157, 24, 253, 22);
		panel_2.add(textField);
		
		JLabel lblNewLabel_1_1 = new JLabel("Họ tên:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_1.setBounds(30, 71, 106, 13);
		panel_2.add(lblNewLabel_1_1);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField_1.setColumns(10);
		textField_1.setBounds(157, 66, 253, 22);
		panel_2.add(textField_1);
		
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
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField_2.setColumns(10);
		textField_2.setBounds(157, 108, 253, 22);
		panel_2.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField_3.setColumns(10);
		textField_3.setBounds(157, 234, 253, 22);
		panel_2.add(textField_3);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Nam");
		rdbtnNewRadioButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		rdbtnNewRadioButton.setBounds(157, 193, 103, 21);
		panel_2.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnN = new JRadioButton("Nữ");
		rdbtnN.setFont(new Font("Tahoma", Font.BOLD, 13));
		rdbtnN.setBounds(307, 193, 103, 21);
		panel_2.add(rdbtnN);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(157, 155, 253, 19);
		panel_2.add(dateChooser);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(10, 291, 496, 281);
		panel.add(panel_3);
		panel_3.setLayout(new GridLayout(1, 0, 0, 0));
		
		JList list_1 = new JList();
		list_1.setModel(new AbstractListModel() {
			String[] values = new String[] {"Vinh đẹp trai"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		list_1.setBorder(new TitledBorder(null, "Danh s\u00E1ch b\u1EA1n b\u00E8", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		list_1.setBackground(new Color(236, 251, 255));
		panel_3.add(list_1);
		
		JList list = new JList();
		list.setFont(new Font("Tahoma", Font.PLAIN, 13));
		list.setBorder(new TitledBorder(null, "L\u1ECBch s\u1EED \u0111\u0103ng nh\u1EADp", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		list.setBackground(new Color(236, 251, 255));
		panel_3.add(list);
		
		JButton btnCpNhtThng = new JButton("Chỉnh sửa thông tin");
		btnCpNhtThng.setBounds(197, 585, 165, 25);
		panel.add(btnCpNhtThng);
		btnCpNhtThng.setHorizontalAlignment(SwingConstants.LEFT);
		btnCpNhtThng.setForeground(new Color(1, 128, 254));
		btnCpNhtThng.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JButton btniMtKhu = new JButton("Đổi mật khẩu");
		btniMtKhu.setBounds(372, 585, 134, 25);
		panel.add(btniMtKhu);
		btniMtKhu.setHorizontalAlignment(SwingConstants.LEFT);
		btniMtKhu.setForeground(new Color(1, 128, 254));
		btniMtKhu.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 10, 516, 45);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnBack = new JButton("Trở về");
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
}
