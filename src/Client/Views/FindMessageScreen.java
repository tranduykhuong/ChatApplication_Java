package Client.Views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class FindMessageScreen extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FindMessageScreen frame = new FindMessageScreen();
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
	public FindMessageScreen() {
		setTitle("Find Message");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 430, 339);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JList<String> list = new JList<String>();
		list.setBorder(new LineBorder(new Color(0, 0, 0)));
		list.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		list.setBounds(21, 146, 367, 141);
		contentPane.add(list);
		list.setModel(new AbstractListModel<String>() {
			private static final long serialVersionUID = 1L;
			String[] values = new String[] { "Khôi to Khương - 12:34 /Em an com chua" };

			public int getSize() {
				return values.length;
			}

			public String getElementAt(int index) {
				return values[index];
			}
		});

		textField = new JTextField();
		textField.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textField.setBounds(137, 25, 168, 26);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel = new JLabel("Search In");
		lblNewLabel.setBounds(21, 25, 65, 22);
		contentPane.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));

		JLabel lblMessageFind = new JLabel("Message Search");
		lblMessageFind.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblMessageFind.setBounds(21, 61, 106, 22);
		contentPane.add(lblMessageFind);

		textField_1 = new JTextField();
		textField_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textField_1.setColumns(10);
		textField_1.setBounds(137, 57, 251, 26);
		contentPane.add(textField_1);

		JButton btnNewButton = new JButton("SEARCH");
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btnNewButton.setBounds(285, 104, 103, 21);
		contentPane.add(btnNewButton);
	}
}
