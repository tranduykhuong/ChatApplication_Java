package Client.Views;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import Client.Controller;
import Entity.Packet;

public class AddMemberScreen extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private ImageIcon iconTitle = new ImageIcon(HomeScreen.class.getResource("/Image/iconmini.jpg"));

	private String userName = "";
	private String idRoom = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddMemberScreen frame = new AddMemberScreen();
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
	public AddMemberScreen() {
		Image icon = iconTitle.getImage();    
		setIconImage(icon); 
		setTitle("Add member");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 395, 140);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Nh\u1EADp t\u00EAn b\u1EA1n mu\u1ED1n th\u00EAm", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel.setBounds(10, 10, 358, 59);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 22, 72, 17);
		panel.add(lblNewLabel);

		textField = new JTextField();
		lblNewLabel.setLabelFor(textField);
		textField.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textField.setBounds(95, 20, 201, 20);
		panel.add(textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("Add");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				userName = textField.getText();
				if (userName.length() == 0) {
					JOptionPane.showMessageDialog(panel, "Vui lòng nhập username bạn bè muốn thêm vào nhóm", "Error",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					ArrayList<String> dataUsername = new ArrayList<String>();
					dataUsername.add(idRoom);
					dataUsername.add(userName);
					controllerAddMember(dataUsername);
					setVisible(false);
				}
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnNewButton.setBounds(283, 72, 85, 21);
		contentPane.add(btnNewButton);
	}

	public void setIdRoom(String _idRoom) {
		idRoom = _idRoom;
	}

	public void controllerAddMember(ArrayList<String> data) {
		Controller.getInstance().sendTextMessage(new Packet("addMemberGroup", data.toString(), "").toString());
	}
}
