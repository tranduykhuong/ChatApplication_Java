package Client.Views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.bson.Document;

import Client.Controller;
import Entity.Packet;
import Server.Controller.AccountController;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.bson.Document;

import Client.Controller;
import Entity.Packet;
import Server.Controller.AccountController;

public class LoginList extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private AccountController account = new AccountController();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AccountController b = new AccountController();
					b.listHistoryLogin();
					LoginList frame = new LoginList();
					frame.setVisible(true);
					frame.setTitle("Login List");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginList() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 740, 547);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 73, 706, 427);
		contentPane.add(panel);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 686, 407);
		panel.add(scrollPane);

		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 13));
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object[][] { { null, null, null },

		}, new String[] { "Thời Gian", "Tên Đăng Nhập", "Họ Tên", }));

		JButton btnBack = new JButton("Trở về");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ManageUsersList().setVisible(true);
				setVisible(false);
			}
		});

		
		btnBack.setForeground(new Color(1, 128, 254));
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnBack.setBounds(10, 10, 89, 21);
		contentPane.add(btnBack);
		JLabel lbLoginList = new JLabel("Danh sách đăng nhập");
		lbLoginList.setForeground(new Color(31, 128, 224));
		lbLoginList.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbLoginList.setBounds(296, 9, 158, 20);
		contentPane.add(lbLoginList);
	}

	public static String removeFirstandLast(String str) {
		str = str.substring(1, str.length() - 1);
		return str;
	}

	public void showHistoryLoginList(List<String> listUserLogin) {
		List<String> sortedList = new ArrayList<>();
		DefaultTableModel tableModel;
		table.getModel();
		tableModel = (DefaultTableModel) table.getModel();
		tableModel.setRowCount(0);
		for (int i = 0; i < listUserLogin.size(); i = i + 3) {
			tableModel
					.addRow(new Object[] { listUserLogin.get(i + 2), listUserLogin.get(i), listUserLogin.get(i + 1) });
		}
	}
}
