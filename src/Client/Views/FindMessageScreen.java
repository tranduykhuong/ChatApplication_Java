package Client.Views;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Client.Controller;
import Entity.Packet;

public class FindMessageScreen extends JFrame {
	private static final long serialVersionUID = 1L;
	private ImageIcon iconTitle = new ImageIcon(HomeScreen.class.getResource("/Image/iconmini.jpg"));
	private JPanel contentPane;
	private JTextField viewFullname;
	private JTextField searchTF;
	private JButton searchBtn;
	private JList list;

	private String id = "";
	private String name = "";
	private String type = "";

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
		Image icon = iconTitle.getImage();
		setIconImage(icon);
		setTitle("Find Message");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				setVisible(false);
			}
		});
		setBounds(100, 100, 426, 339);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		viewFullname = new JTextField();
		viewFullname.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		viewFullname.setBounds(137, 25, 168, 26);
		contentPane.add(viewFullname);
		viewFullname.setColumns(10);

		JLabel lblNewLabel = new JLabel("Search In");
		lblNewLabel.setBounds(21, 25, 65, 22);
		contentPane.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));

		JLabel lblMessageFind = new JLabel("Message Search");
		lblMessageFind.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblMessageFind.setBounds(21, 61, 106, 22);
		contentPane.add(lblMessageFind);

		searchTF = new JTextField();
		searchTF.addCaretListener(e -> {
			if (searchTF.getText().length() > 0) {
				searchBtn.setEnabled(true);
			} else {
				searchBtn.setEnabled(false);
			}
		});

		searchTF.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		searchTF.setColumns(10);
		searchTF.setBounds(137, 57, 251, 26);
		contentPane.add(searchTF);

		searchBtn = new JButton("SEARCH");
		searchBtn.setEnabled(false);
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (searchTF.getText().length() > 0) {
					if (type.equals("FRIEND")) {
						Controller.getInstance()
								.sendTextMessage(new Packet("searchMessageOfFriendChat",
										Controller.getInstance().getID() + ", " + id + ", " + searchTF.getText(), "")
										.toString());
					} else if (type.equals("ROOM")) {
						Controller.getInstance()
								.sendTextMessage(new Packet("searchMessageOfRoomChat",
										Controller.getInstance().getID() + ", " + id + ", " + searchTF.getText(), "")
										.toString());
					} else if (type.equals("ALL")) {
						Controller.getInstance().sendTextMessage(new Packet("searchMessageAll",
								Controller.getInstance().getID() + ", " + searchTF.getText(), "").toString());
					}
				}
			}
		});
		searchBtn.setFont(new Font("Times New Roman", Font.BOLD, 13));
		searchBtn.setBounds(285, 103, 103, 22);
		contentPane.add(searchBtn);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 149, 367, 140);
		contentPane.add(scrollPane);

		list = new JList();
		list.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
		scrollPane.setViewportView(list);
	}

	public void setData(String id, String name, String type) {
		this.id = id;
		this.name = name;
		this.type = type;
		viewFullname.setText(name);
		viewFullname.setEditable(false);
		searchTF.setText("");
		list.setModel(new DefaultListModel<String>());
	}

	public void showList(String data) {
		String[] listData = data.substring(1, data.length() - 1).split(", ");

		DefaultListModel<String> dmodel = new DefaultListModel<String>();
		for (int i = 0; i < listData.length - 2; i += 3) {
			dmodel.addElement("[" + listData[i + 2] + "] " + listData[i] + ": " + listData[i + 1]);
		}
		list.setModel(dmodel);
	}
}
