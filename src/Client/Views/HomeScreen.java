package Client.Views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Client.ClientApp;

public class HomeScreen extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public HomeScreen() {
		setTitle("Home");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 469, 326);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 102));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("REGISTER");
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 10));
		btnNewButton.setBounds(153, 189, 149, 30);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("QUIT");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				ClientApp.connectionScreen.setVisible(true);
			}
		});
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 10));
		btnNewButton_1.setBounds(153, 229, 149, 30);
		contentPane.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("LOG IN");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new LoginScreen().setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton_2.setFont(new Font("Times New Roman", Font.BOLD, 10));
		btnNewButton_2.setBounds(153, 149, 149, 30);
		contentPane.add(btnNewButton_2);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\ACER\\Downloads\\AppChat.png"));
		lblNewLabel.setBounds(65, -33, 326, 252);
		contentPane.add(lblNewLabel);

		JButton btnNewButton_3 = new JButton("Dashboard");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				Controller.getInstance().sendTextMessage(new Packet("logIn", "Test ne", "DK").toString());
				new ChatApplicationScreen().setVisible(true);
			}
		});
		btnNewButton_3.setBounds(322, 192, 101, 23);
		contentPane.add(btnNewButton_3);

		JButton btnNewButton_4 = new JButton("New button");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClientApp.MNUserList.run();
				ClientApp.MNUserList.setVisible(true);
			}
		});
		btnNewButton_4.setBounds(10, 193, 85, 21);
		contentPane.add(btnNewButton_4);
	}
}
