package Server.Views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Panel;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.JList;
import java.awt.FlowLayout;
import javax.swing.AbstractListModel;
import javax.swing.border.TitledBorder;

public class SeverChat extends JFrame {

	private JPanel ipAddress;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SeverChat frame = new SeverChat();
					frame.setVisible(true);
					frame.setTitle("Trang chủ Server");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SeverChat() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 711, 600);
		ipAddress = new JPanel();
		ipAddress.setBackground(Color.WHITE);
		ipAddress.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(ipAddress);
		ipAddress.setLayout(null);
		
		JPanel pnInforStatusList = new JPanel();
		pnInforStatusList.setBounds(10, 90, 677, 465);
		ipAddress.add(pnInforStatusList);
		pnInforStatusList.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 29, 657, 426);
		pnInforStatusList.add(panel_1);
		panel_1.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Server Status", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBackground(Color.WHITE);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		JList list = new JList();
		list.setBounds(10, 22, 308, 394);
		panel_2.add(list);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "Client Status", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_4.setBackground(Color.WHITE);
		panel_1.add(panel_4);
		panel_4.setLayout(null);
		
		JList list_1 = new JList();
		list_1.setBounds(10, 22, 308, 394);
		panel_4.add(list_1);
		
		JPanel pnControlServer = new JPanel();
		pnControlServer.setBounds(10, 5, 677, 75);
		ipAddress.add(pnControlServer);
		pnControlServer.setLayout(null);
		
		JButton btnRunServer = new JButton("Run");
		btnRunServer.setForeground(new Color(38, 154, 217));
		btnRunServer.setBounds(434, 24, 70, 25);
		btnRunServer.setFont(new Font("Tahoma", Font.BOLD, 13));
		pnControlServer.add(btnRunServer);
		
		JButton btnStopServer = new JButton("Stop");
		btnStopServer.setForeground(new Color(38, 154, 217));
		btnStopServer.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnStopServer.setBounds(545, 24, 70, 25);
		pnControlServer.add(btnStopServer);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.BOLD, 13));
		textField.setColumns(10);
		textField.setBounds(292, 21, 105, 30);
		pnControlServer.add(textField);
		
		JLabel lbIPAddress = new JLabel("IP address:");
		lbIPAddress.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbIPAddress.setBackground(new Color(0, 128, 255));
		lbIPAddress.setBounds(10, 24, 76, 25);
		pnControlServer.add(lbIPAddress);
		
		JLabel lbPort = new JLabel("Port:");
		lbPort.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbPort.setBounds(247, 24, 45, 25);
		pnControlServer.add(lbPort);
	}
}
