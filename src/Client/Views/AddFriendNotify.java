package Client.Views;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Client.Controller;
import Entity.Packet;

public class AddFriendNotify extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;

	private String fullname;
	private String id;

	public AddFriendNotify() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2- getSize().width/2, dim.height/2 - getSize().height/2);
		setTitle("Notify");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				setVisible(false);
			}
		});
		setBounds(100, 100, 347, 120);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Lời mời kết bạn từ");
		lblNewLabel.setBounds(10, 11, 152, 14);
		contentPane.add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(134, 8, 186, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("Chấp nhận");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().sendTextMessage(
						new Packet("acceptRequestFriend", Controller.getInstance().getID() + ", " + id, "").toString());
				Controller.getInstance().sendTextMessage(
						new Packet("listRequestAddFriend", Controller.getInstance().getID(), id).toString());
				Controller.getInstance().sendTextMessage(
						new Packet("showListFriendChat", Controller.getInstance().getID(), id).toString());
				Controller.getInstance().sendTextMessage(
						new Packet("listFriendOnline", Controller.getInstance().getID(), id).toString());

				setVisible(false);
			}
		});
		btnNewButton.setBounds(217, 47, 103, 23);
		contentPane.add(btnNewButton);

		JButton btnTChi = new JButton("Từ chối");
		btnTChi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().sendTextMessage(
						new Packet("rejectRequestFriend", Controller.getInstance().getID() + ", " + id, "").toString());
				setVisible(false);
				Controller.getInstance().sendTextMessage(new Packet("listRequestAddFriend", id, id).toString());
			}
		});
		btnTChi.setBounds(106, 47, 103, 23);
		contentPane.add(btnTChi);
	}

	public void show(String fullname, String id) {
		this.fullname = fullname;
		this.id = id;

		textField.setText(this.fullname);
		textField.setEditable(false);
		this.setVisible(true);
	}
}
