package Client.Views;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import Client.Controller;
import Entity.Packet;

public class ChatApplicationScreen extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JList<String> list_2;

	private String listNameGr = "";
	private String listIdGr = "";
	private DefaultListModel dmodel = new DefaultListModel();
	private ArrayList<String> showListNameRoom = new ArrayList<String>();
	private ArrayList<String> getshowListNameRoom = new ArrayList<String>();

	public ChatApplicationScreen() {
		setTitle("Chat Application");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				String[] options = { "Yes", "No" };
				int result = JOptionPane.showOptionDialog(null, "Are you sure you want to LOGOUT?", "Confirmation",
						JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
				if (result == 0) {
					Controller.getInstance().handleScreen("homeScreen", true);
					setVisible(false);
				}
			}
		});
		setBounds(100, 100, 917, 446);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0, 0, 4, 66, 0, 0, 0, 0, 0, 0, 0, 0, 0, 30, 24, 73, 0 };
		gbl_contentPane.rowHeights = new int[] { 85, 2, 0, 0, 0, 0, 0, 70, 22, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 1.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JList<String> list = new JList<String>();
		list.setModel(new AbstractListModel<String>() {
			private static final long serialVersionUID = 1L;
			String[] values = new String[] { "IP: 123", "Port: 999", "Số user online: 1" };

			public int getSize() {
				return values.length;
			}

			public String getElementAt(int index) {
				return values[index];
			}
		});

		list.setFont(new Font("Times New Roman", Font.BOLD, 12));
		list.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Server",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.gridwidth = 4;
		gbc_list.insets = new Insets(0, 0, 5, 5);
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 0;
		gbc_list.gridy = 0;
		contentPane.add(list, gbc_list);

		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(2, 2, 3, 2, (Color) new Color(128, 128, 128)));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridheight = 8;
		gbc_panel.gridwidth = 13;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 4;
		gbc_panel.gridy = 0;
		contentPane.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0 };
		gbl_panel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "Message", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_4.setBackground(Color.WHITE);
		panel_4.setLayout(null);
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.fill = GridBagConstraints.BOTH;
		gbc_panel_4.gridx = 0;
		gbc_panel_4.gridy = 0;
		panel.add(panel_4, gbc_panel_4);

		JPanel panel_8 = new JPanel();
		panel_8.setBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(192, 192, 192)));
		panel_8.setBounds(5, 51, 353, 280);
		panel_4.add(panel_8);
		panel_8.setLayout(new GridLayout(1, 0, 0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panel_8.add(scrollPane);

		JTextArea textArea_1 = new JTextArea();
		textArea_1.setWrapStyleWord(true);
		textArea_1.setLineWrap(true);
		textArea_1.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		scrollPane.setViewportView(textArea_1);

		JPanel panel_7 = new JPanel();
		panel_7.setBounds(5, 20, 117, 35);
		panel_4.add(panel_7);
		panel_7.setLayout(new GridLayout(1, 0, 0, 0));

		JPanel panel_6 = new JPanel();
		panel_7.add(panel_6);
		panel_6.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_6.setBackground(Color.LIGHT_GRAY);
		panel_6.setLayout(null);

		JLabel lblNewLabel = new JLabel("Khôi");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel.setBounds(10, 10, 63, 22);
		panel_6.add(lblNewLabel);

		JButton btnNewButton_4 = new JButton(UIManager.getIcon("InternalFrame.closeIcon"));
		btnNewButton_4.setBounds(65, 12, 31, 18);
		panel_6.add(btnNewButton_4);

		JPanel panel_9 = new JPanel();
		panel_9.setBorder(new TitledBorder(null, "Thao t\u00E1c ng\u01B0\u1EDDi d\u00F9ng", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel_9.setBackground(Color.WHITE);
		panel_9.setBounds(365, 10, 159, 321);
		panel_4.add(panel_9);
		panel_9.setLayout(null);

		JButton btnNewButton_6_1 = new JButton("Delete CH");
		btnNewButton_6_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnNewButton_6_1.setBounds(10, 79, 139, 27);
		panel_9.add(btnNewButton_6_1);

		JButton btnNewButton_6_1_1 = new JButton("Find Message");
		btnNewButton_6_1_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnNewButton_6_1_1.setBounds(10, 120, 139, 27);
		panel_9.add(btnNewButton_6_1_1);

		JButton btnNewButton_6_1_1_1 = new JButton("Find All");
		btnNewButton_6_1_1_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnNewButton_6_1_1_1.setBounds(10, 161, 139, 27);
		panel_9.add(btnNewButton_6_1_1_1);

		JButton btnNewButton_6_1_1_1_1 = new JButton("LOG OUT");
		btnNewButton_6_1_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().logout();
				setVisible(false);
			}
		});
		btnNewButton_6_1_1_1_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnNewButton_6_1_1_1_1.setBounds(10, 284, 139, 27);
		panel_9.add(btnNewButton_6_1_1_1_1);

		JList<String> list_3 = new JList<String>();
		list_3.setFont(new Font("Times New Roman", Font.BOLD, 13));
		list_3.setModel(new AbstractListModel<String>() {
			private static final long serialVersionUID = 1L;
			String[] values = new String[] { "Long", "Lộc", "Chú lùn", "Tấn ", "Duy Khương Trần" };

			public int getSize() {
				return values.length;
			}

			public String getElementAt(int index) {
				return values[index];
			}
		});
		list_3.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Danh s\u00E1ch b\u1EA1n", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GridBagConstraints gbc_list_3 = new GridBagConstraints();
		gbc_list_3.insets = new Insets(0, 0, 5, 0);
		gbc_list_3.gridheight = 8;
		gbc_list_3.fill = GridBagConstraints.BOTH;
		gbc_list_3.gridx = 17;
		gbc_list_3.gridy = 0;
		contentPane.add(list_3, gbc_list_3);

		JList<String> list_1 = new JList<String>();
		list_1.setFont(new Font("Times New Roman", Font.BOLD, 13));
		list_1.setModel(new AbstractListModel<String>() {
			private static final long serialVersionUID = 1L;
			String[] values = new String[] { "Khôi ", "Long", "Chính" };

			public int getSize() {
				return values.length;
			}

			public String getElementAt(int index) {
				return values[index];
			}
		});
		list_1.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"B\u1EA1n b\u00E8 Online", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GridBagConstraints gbc_list_1 = new GridBagConstraints();
		gbc_list_1.gridheight = 3;
		gbc_list_1.gridwidth = 4;
		gbc_list_1.insets = new Insets(0, 0, 5, 5);
		gbc_list_1.fill = GridBagConstraints.BOTH;
		gbc_list_1.gridx = 0;
		gbc_list_1.gridy = 1;
		contentPane.add(list_1, gbc_list_1);

		list_2 = new JList<String>();
		list_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String selected = list_2.getSelectedValue().toString();

				for (int i = 0; i < listNameGr.split(", ").length; i++) {
					if (selected.equals(listNameGr.split(", ")[i])) {
						showListNameRoom.add(selected);
						showListNameRoom.add(listIdGr.split(", ")[i]);
					}
				}
				getshowListNameRoom.add(showListNameRoom.toString());

				String id = "63b533f8-1e88-4a22-9069-51d9507f94ed";
				Controller.getInstance()
						.sendTextMessage(new Packet("showListMemberRoom", showListNameRoom.toString(), id).toString());

				showListNameRoom.clear();
			}
		});
		list_2.setModel(dmodel);
		list_2.setFont(new Font("Times New Roman", Font.BOLD, 13));
		list_2.setBorder(
				new TitledBorder(null, "Nh\u00F3m", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		GridBagConstraints gbc_list_2 = new GridBagConstraints();
		gbc_list_2.gridwidth = 4;
		gbc_list_2.gridheight = 4;
		gbc_list_2.insets = new Insets(0, 0, 5, 5);
		gbc_list_2.fill = GridBagConstraints.BOTH;
		gbc_list_2.gridx = 0;
		gbc_list_2.gridy = 4;
		contentPane.add(list_2, gbc_list_2);

		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.gridwidth = 4;
		gbc_panel_2.insets = new Insets(0, 0, 0, 5);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 8;
		contentPane.add(panel_2, gbc_panel_2);
		panel_2.setLayout(new GridLayout(1, 0, 0, 0));

		JButton btnNewButton = new JButton("Tạo Nhóm");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = "63b533f8-1e88-4a22-9069-51d9507f94ed";

				Controller.getInstance().sendTextMessage(new Packet("showListFriend", id, "").toString());
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 13));
		panel_2.add(btnNewButton);

		JPanel panel_5 = new JPanel();
		GridBagConstraints gbc_panel_5 = new GridBagConstraints();
		gbc_panel_5.gridwidth = 11;
		gbc_panel_5.insets = new Insets(0, 0, 0, 5);
		gbc_panel_5.fill = GridBagConstraints.BOTH;
		gbc_panel_5.gridx = 4;
		gbc_panel_5.gridy = 8;
		contentPane.add(panel_5, gbc_panel_5);
		panel_5.setLayout(new GridLayout(1, 0, 0, 0));

		JScrollPane scrollPane_1 = new JScrollPane();
		panel_5.add(scrollPane_1);

		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		scrollPane_1.setViewportView(textArea);

		JPanel panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.gridwidth = 2;
		gbc_panel_3.insets = new Insets(0, 0, 0, 5);
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 15;
		gbc_panel_3.gridy = 8;
		contentPane.add(panel_3, gbc_panel_3);
		panel_3.setLayout(new GridLayout(1, 0, 0, 0));

		JButton btnNewButton_1 = new JButton("GỬI");
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 13));
		panel_3.add(btnNewButton_1);

		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 17;
		gbc_panel_1.gridy = 8;
		contentPane.add(panel_1, gbc_panel_1);
		panel_1.setLayout(new GridLayout(1, 0, 0, 0));

		JButton btnNewButton_3 = new JButton("Unfriend");
		btnNewButton_3.setFont(new Font("Times New Roman", Font.BOLD, 13));
		panel_1.add(btnNewButton_3);

		JButton btnNewButton_2 = new JButton("Add Friend");
		btnNewButton_2.setFont(new Font("Times New Roman", Font.BOLD, 13));
		panel_1.add(btnNewButton_2);
	}

	public ArrayList<String> getDataControlListMemberRoom() {
		return getshowListNameRoom;
	}

	public void refeshDataForm() {
		String id = "63b533f8-1e88-4a22-9069-51d9507f94ed";
		Controller.getInstance().sendTextMessage(new Packet("showListGr", id, "").toString());
	}

	public void showListNameGr(String _data) {
		String listIdAndName = _data.substring(1, _data.length() - 1);
		listIdGr = listIdAndName.split("], ")[0].replace("[", "");
		listNameGr = listIdAndName.split("], ")[1].replace("]", "").replace("[", "");

		for (int i = 0; i < listNameGr.split(", ").length; i++) {
			dmodel.addElement(listNameGr.split(", ")[i]);
		}
	}

	public void destroyChatAppForm() {
		listNameGr = "";
		listIdGr = "";

		int sizeDmodel = dmodel.size();
		if (sizeDmodel != 0) {
			while (sizeDmodel != 0) {
				dmodel.removeElementAt(sizeDmodel - 1);
				sizeDmodel -= 1;
			}
		}

		showListNameRoom.clear();
		getshowListNameRoom.clear();
	}
}
