package Client.Views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JList;
import java.awt.GridLayout;
import javax.swing.border.TitledBorder;
import javax.swing.AbstractListModel;

public class GroupChatList extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GroupChatList frame = new GroupChatList();
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
	public GroupChatList() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 842, 483);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pnControl = new JPanel();
		pnControl.setBackground(Color.WHITE);
		pnControl.setBounds(10, 10, 808, 92);
		contentPane.add(pnControl);
		pnControl.setLayout(null);
		
		JLabel lbArrange = new JLabel("Sắp xếp theo:");
		lbArrange.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbArrange.setBounds(11, 55, 99, 13);
		pnControl.add(lbArrange);
		
		JButton btnSortName = new JButton("Tên");
		btnSortName.setForeground(new Color(1, 128, 254));
		btnSortName.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSortName.setBounds(120, 52, 75, 21);
		pnControl.add(btnSortName);
		
		JButton btnSortDate = new JButton("Ngày tạo");
		btnSortDate.setForeground(new Color(1, 128, 254));
		btnSortDate.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSortDate.setBounds(230, 52, 99, 21);
		pnControl.add(btnSortDate);
		
		JLabel lblDanhSchNhm = new JLabel("Danh sách nhóm chat");
		lblDanhSchNhm.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDanhSchNhm.setBounds(336, 10, 150, 13);
		pnControl.add(lblDanhSchNhm);
		
		JButton btnBack = new JButton("Trở về");
		btnBack.setForeground(new Color(1, 128, 254));
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnBack.setBounds(0, 0, 87, 21);
		pnControl.add(btnBack);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 112, 808, 324);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel pnGroupName = new JPanel();
		pnGroupName.setBorder(new TitledBorder(null, "T\u00EAn nh\u00F3m chat", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(pnGroupName);
		pnGroupName.setLayout(null);
		
		JList listGroupChat = new JList();
		listGroupChat.setFont(new Font("Tahoma", Font.PLAIN, 13));
		listGroupChat.setModel(new AbstractListModel() {
			String[] values = new String[] {"Nhóm 1", "Nhóm 2", "Nhóm 3", "Nhóm 4"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		listGroupChat.setBounds(10, 21, 249, 293);
		pnGroupName.add(listGroupChat);
		
		JPanel pnMemberList = new JPanel();
		pnMemberList.setBorder(new TitledBorder(null, "Danh s\u00E1ch th\u00E0nh vi\u00EAn", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(pnMemberList);
		pnMemberList.setLayout(null);
		
		JList listMember = new JList();
		listMember.setModel(new AbstractListModel() {
			String[] values = new String[] {"Chính ", "Khôi", "Khương", "Tấn", "Vinh"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		listMember.setFont(new Font("Tahoma", Font.PLAIN, 13));
		listMember.setBounds(10, 21, 249, 293);
		pnMemberList.add(listMember);
		
		JPanel pnAdminList = new JPanel();
		pnAdminList.setBorder(new TitledBorder(null, "Danh s\u00E1ch admin", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(pnAdminList);
		pnAdminList.setLayout(null);
		
		JList listAdmin = new JList();
		listAdmin.setModel(new AbstractListModel() {
			String[] values = new String[] {"Khôi", "Vinh"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		listAdmin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		listAdmin.setBounds(10, 21, 249, 293);
		pnAdminList.add(listAdmin);
	}
}
