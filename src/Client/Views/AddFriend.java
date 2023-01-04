package Client.Views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import Client.Controller;
import Entity.Packet;

public class AddFriend extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField searchName;
	private ImageIcon iconTitle = new ImageIcon(HomeScreen.class.getResource("/Image/iconmini.jpg"));
	private JTextField selectView;
	private JTextField statusView;
	private JList list;
	private DefaultListModel dmodel = new DefaultListModel();
	private ArrayList<String> listID = new ArrayList<String>();

	private String idSelected = "";
	private JButton rejectBtn;
	private JButton acceptBtn;
	private JButton cancelInviteBtn;
	private JButton cancelFrBtn;
	private JButton addFrBtn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddFriend frame = new AddFriend();
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
	public AddFriend() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2- getSize().width/2, dim.height/2 - getSize().height/2);
		Image icon = iconTitle.getImage();
		setIconImage(icon);
		setTitle("Find Friend");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				setVisible(false);
			}
		});
		setBounds(100, 100, 450, 305);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		addFrBtn = new JButton("Thêm bạn");
		addFrBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().sendTextMessage(
						new Packet("addFriend", Controller.getInstance().getID() + ", " + idSelected, "").toString());
				Controller.getInstance().sendTextMessage(
						new Packet("statusFriend", Controller.getInstance().getID() + ", " + idSelected, "")
								.toString());
			}
		});
		addFrBtn.setFont(new Font("Times New Roman", Font.BOLD, 15));
		addFrBtn.setBounds(316, 230, 108, 25);
		contentPane.add(addFrBtn);

		JLabel lblNewLabel = new JLabel("Nhập tên cần tìm");
		lblNewLabel.setBounds(10, 20, 131, 25);
		contentPane.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));

		searchName = new JTextField();
		searchName.setBounds(125, 20, 179, 24);
		contentPane.add(searchName);
		searchName.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		searchName.setColumns(10);

		JButton btnTmKim = new JButton("Tìm kiếm");
		btnTmKim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().sendTextMessage(new Packet("filterList", searchName.getText(), "").toString());
			}
		});
		btnTmKim.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnTmKim.setBounds(316, 20, 108, 25);
		contentPane.add(btnTmKim);

		list = new JList();
		list.setBounds(10, 56, 414, 96);
		contentPane.add(list);
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selected = list.getSelectedIndex();

				idSelected = listID.get(selected);
				selectView.setText(list.getSelectedValue().toString());
				selectView.setEditable(false);

				Controller.getInstance().sendTextMessage(
						new Packet("statusFriend", Controller.getInstance().getID() + ", " + idSelected, "")
								.toString());
			}
		});

		JLabel lblNewLabel_1 = new JLabel("Trạng thái");
		lblNewLabel_1.setBounds(10, 194, 63, 14);
		contentPane.add(lblNewLabel_1);

		selectView = new JTextField();
		selectView.setBounds(10, 163, 236, 20);
		contentPane.add(selectView);
		selectView.setColumns(10);

		statusView = new JTextField();
		statusView.setBounds(83, 191, 163, 20);
		contentPane.add(statusView);
		statusView.setColumns(10);

		cancelFrBtn = new JButton("Hủy kết bạn");
		cancelFrBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().sendTextMessage(
						new Packet("unFriend", Controller.getInstance().getID() + ", " + idSelected, "").toString());
				Controller.getInstance().sendTextMessage(
						new Packet("statusFriend", Controller.getInstance().getID() + ", " + idSelected, "")
								.toString());
				Controller.getInstance().sendTextMessage(new Packet("showListFriendChat",
						Controller.getInstance().getID(), Controller.getInstance().getID()).toString());
				Controller.getInstance().sendTextMessage(new Packet("listFriendOnline",
						Controller.getInstance().getID(), Controller.getInstance().getID()).toString());

			}
		});
		cancelFrBtn.setFont(new Font("Times New Roman", Font.BOLD, 15));
		cancelFrBtn.setBounds(279, 230, 145, 25);
		contentPane.add(cancelFrBtn);

		rejectBtn = new JButton("Từ chối");
		rejectBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().sendTextMessage(
						new Packet("rejectRequestFriend", Controller.getInstance().getID() + ", " + idSelected, "")
								.toString());
				Controller.getInstance().sendTextMessage(
						new Packet("statusFriend", Controller.getInstance().getID() + ", " + idSelected, "")
								.toString());
			}
		});
		rejectBtn.setFont(new Font("Times New Roman", Font.BOLD, 15));
		rejectBtn.setBounds(316, 194, 108, 25);
		contentPane.add(rejectBtn);

		acceptBtn = new JButton("Chấp nhận");
		acceptBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().sendTextMessage(
						new Packet("acceptRequestFriend", Controller.getInstance().getID() + ", " + idSelected, "")
								.toString());
				Controller.getInstance().sendTextMessage(
						new Packet("statusFriend", Controller.getInstance().getID() + ", " + idSelected, "")
								.toString());
				Controller.getInstance().sendTextMessage(new Packet("showListFriendChat",
						Controller.getInstance().getID(), Controller.getInstance().getID()).toString());
				Controller.getInstance().sendTextMessage(new Packet("listFriendOnline",
						Controller.getInstance().getID(), Controller.getInstance().getID()).toString());
			}
		});
		acceptBtn.setFont(new Font("Times New Roman", Font.BOLD, 15));
		acceptBtn.setBounds(316, 230, 108, 25);
		contentPane.add(acceptBtn);

		cancelInviteBtn = new JButton("Hủy lời mời");
		cancelInviteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().sendTextMessage(
						new Packet("rejectRequestFriend", Controller.getInstance().getID() + ", " + idSelected, "")
								.toString());
				Controller.getInstance().sendTextMessage(
						new Packet("statusFriend", Controller.getInstance().getID() + ", " + idSelected, "")
								.toString());
			}
		});
		cancelInviteBtn.setFont(new Font("Times New Roman", Font.BOLD, 15));
		cancelInviteBtn.setBounds(279, 230, 145, 25);
		contentPane.add(cancelInviteBtn);

		rejectBtn.setVisible(false);
		acceptBtn.setVisible(false);
		cancelInviteBtn.setVisible(false);
		cancelFrBtn.setVisible(false);
		addFrBtn.setVisible(false);
	}

	public void showListFriend(List<String> listUser) {
		if (listUser.size() > 1) {
			dmodel.clear();
			listID.clear();
			for (int i = 0; i < listUser.size(); i = i + 8) {
				if (!listUser.get(i + 7).equals(Controller.getInstance().getID())) {
					dmodel.addElement(listUser.get(i + 1));
					listID.add(listUser.get(i + 7));
				}
			}
			list.setModel(dmodel);
		} else {
			JOptionPane.showMessageDialog(this, "Không tìm thấy bạn bè!", "Warning", JOptionPane.WARNING_MESSAGE);
			return;
		}
	}

	public void handleStatusFriend(String status) {
		if (status.equals("Bạn bè")) {
			rejectBtn.setVisible(false);
			acceptBtn.setVisible(false);
			cancelInviteBtn.setVisible(false);
			cancelFrBtn.setVisible(true);
			addFrBtn.setVisible(false);
		} else if (status.equals("Yêu cầu kết bạn")) {
			rejectBtn.setVisible(true);
			acceptBtn.setVisible(true);
			cancelInviteBtn.setVisible(false);
			cancelFrBtn.setVisible(false);
			addFrBtn.setVisible(false);
		} else if (status.equals("Đã gửi lời mời")) {
			rejectBtn.setVisible(false);
			acceptBtn.setVisible(false);
			cancelInviteBtn.setVisible(true);
			cancelFrBtn.setVisible(false);
			addFrBtn.setVisible(false);
		} else {
			rejectBtn.setVisible(false);
			acceptBtn.setVisible(false);
			cancelInviteBtn.setVisible(false);
			cancelFrBtn.setVisible(false);
			addFrBtn.setVisible(true);
		}

		statusView.setText(status);
		statusView.setEditable(false);
	}

	public void reloadStatus() {
		Controller.getInstance().sendTextMessage(
				new Packet("statusFriend", Controller.getInstance().getID() + ", " + idSelected, "").toString());
	}
}
