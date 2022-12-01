package Client.Views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;

public class ConnectionScreen extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblPort;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	// public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	// ConnectionScreen frame = new ConnectionScreen();
	// frame.setVisible(true);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// });
	// }

	/**
	 * Create the frame.
	 */
	public ConnectionScreen() {
		setTitle("Server Connect");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 356, 172);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("IP");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel.setBounds(10, 26, 45, 13);
		contentPane.add(lblNewLabel);

		textField = new JTextField();
		textField.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblNewLabel.setLabelFor(textField);
		textField.setBounds(76, 21, 214, 26);
		contentPane.add(textField);
		textField.setColumns(10);

		lblPort = new JLabel("Port");
		lblPort.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblPort.setBounds(10, 62, 45, 13);
		contentPane.add(lblPort);

		textField_1 = new JTextField();
		textField_1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblPort.setLabelFor(textField_1);
		textField_1.setColumns(10);
		textField_1.setBounds(76, 57, 214, 26);
		contentPane.add(textField_1);

		JButton btnNewButton = new JButton("Connect");
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnNewButton.setBounds(197, 93, 93, 30);
		contentPane.add(btnNewButton);

		JButton btnDisconnect = new JButton("Disconnect");
		btnDisconnect.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnDisconnect.setBounds(76, 93, 111, 30);
		contentPane.add(btnDisconnect);
	}
}
