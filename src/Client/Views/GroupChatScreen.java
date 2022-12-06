package Client.Views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.border.TitledBorder;

public class GroupChatScreen extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GroupChatScreen frame = new GroupChatScreen();
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
	public GroupChatScreen() {
		setTitle("Nhóm anh em");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 602, 321);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Danh s\u00E1ch th\u00E0nh vi\u00EAn", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel.setBounds(10, 59, 416, 214);
		contentPane.add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 47, 396, 157);
		panel.add(panel_1);
		panel_1.setLayout(new GridLayout(1, 0, 0, 0));

		JList<String> list = new JList<String>();
		list.setModel(new AbstractListModel<String>() {
			private static final long serialVersionUID = 1L;
			String[] values = new String[] { "Anh Khoi", "Huu Chinh", "Duy Khuong" };

			public int getSize() {
				return values.length;
			}

			public String getElementAt(int index) {
				return values[index];
			}
		});
		list.setFont(new Font("Times New Roman", Font.BOLD, 13));
		panel_1.add(list);

		JLabel lblNewLabel = new JLabel("Tên Nhóm");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel.setBounds(34, 26, 56, 13);
		contentPane.add(lblNewLabel);

		textField = new JTextField();
		lblNewLabel.setLabelFor(textField);
		textField.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		textField.setBounds(100, 21, 226, 22);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("Rename");
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btnNewButton.setBounds(336, 21, 88, 22);
		contentPane.add(btnNewButton);

		JButton btnOk = new JButton("Confirm");
		btnOk.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btnOk.setBounds(444, 232, 123, 32);
		contentPane.add(btnOk);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Thao t\u00E1c", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(436, 62, 142, 144);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		JButton btnNewButton_1 = new JButton("Admin Change");
		btnNewButton_1.setBounds(10, 24, 123, 32);
		panel_2.add(btnNewButton_1);
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 13));

		JButton btnNewButton_1_1_1 = new JButton("Add Member");
		btnNewButton_1_1_1.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btnNewButton_1_1_1.setBounds(10, 64, 123, 32);
		panel_2.add(btnNewButton_1_1_1);

		JButton btnNewButton_1_1 = new JButton("Delete Member");
		btnNewButton_1_1.setBounds(10, 106, 123, 32);
		panel_2.add(btnNewButton_1_1);
		btnNewButton_1_1.setFont(new Font("Times New Roman", Font.BOLD, 13));
	}
}
