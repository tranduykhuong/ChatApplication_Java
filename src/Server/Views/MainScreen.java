package Server.Views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import Client.Views.HomeScreen;
import Server.TCP_Server;

public class MainScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel ipAddress;
	private JTextField portField;
	private TCP_Server server = null;
	private JTextField ipField;
	private ImageIcon iconTitle = new ImageIcon(HomeScreen.class.getResource("/Image/iconmini.jpg"));

	JButton btnRunServer;
	JButton btnStopServer;
	JCheckBox autoCheckbox;

	JList<String> clientStatusPane;
	JList<String> serverStatusPane;
	DefaultListModel<String> model;

	private boolean checkValidation() {
		if (portField.getText().length() == 0 || ipField.getText().length() == 0) {
			JOptionPane.showMessageDialog(this, "Missing connection config", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		try {
			String PATTERN = "^((0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)\\.){3}(0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)$";
			if (!ipField.getText().matches(PATTERN)) {
				JOptionPane.showMessageDialog(this, "IP address is not valid", "Error", JOptionPane.ERROR_MESSAGE);
				return false;
			}

			var port = Integer.valueOf(portField.getText());
			if (port < 0 || port > 65536) {
				JOptionPane.showMessageDialog(this, "Port is not valid", "Error", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Port is not valid", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;
	}

	private void btnRunActionPerformed(ActionEvent e) {
		if (!checkValidation()) {
			return;
		}

		var port = Integer.valueOf(portField.getText());

		if (!server.openSocket(ipField.getText(), port)) {
			JOptionPane.showMessageDialog(this, "Can not open Server", "Warning", JOptionPane.WARNING_MESSAGE);
			return;
		}

		DefaultListModel<String> model = new DefaultListModel<String>();
		model.addElement("Server is running...");
		serverStatusPane.setModel(model);

		btnStopServer.setEnabled(true);
		btnRunServer.setEnabled(false);
		ipField.setEditable(false);
		portField.setEditable(false);
		autoCheckbox.setEnabled(false);
	}

	public void reConnect() {
		server.closeSocket();
		var port = Integer.valueOf(portField.getText());
		if (!server.openSocket(ipField.getText(), port)) {
			JOptionPane.showMessageDialog(this, "Can not open Server", "Warning", JOptionPane.WARNING_MESSAGE);
			return;
		}
		JOptionPane.showMessageDialog(this, "Reconnect Server!", "Warning", JOptionPane.WARNING_MESSAGE);
		DefaultListModel<String> model = new DefaultListModel<String>();
		model.addElement("Server is running...");
		serverStatusPane.setModel(model);
	}

	private void btnStopActionPerformed(ActionEvent e) {
		DefaultListModel<String> modelServer = new DefaultListModel<String>();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		server.closeSocket();
		modelServer.addElement("Server stopped...!");
		serverStatusPane.setModel(modelServer);
		model.clear();
		clientStatusPane.setModel(model);

		btnRunServer.setEnabled(true);
		btnStopServer.setEnabled(false);
		ipField.setEditable(true);
		portField.setEditable(true);
		autoCheckbox.setEnabled(true);
	}

	public void addClientStatus(String status) {
		model.add(0, status);

		clientStatusPane.setModel(model);
	}

	/**
	 * Create the frame.
	 */
	public MainScreen() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2- getSize().width/2, dim.height/2 - getSize().height/2);
		Image icon = iconTitle.getImage();    
		setIconImage(icon); 
		setResizable(false);
		setTitle("SERVER");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				String[] options = { "Yes", "No" };
				int result = JOptionPane.showOptionDialog(null, "Are you sure you want to exit?", "Confirmation",
						JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
				
				System.out.println(options[0]);
				System.out.println(result);
				
				if (result == 0) {
					server.closeSocket();
				}
				else {
					return;
				}
			}
		});
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

		serverStatusPane = new JList();
		serverStatusPane.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		serverStatusPane.setBounds(10, 22, 308, 394);
		panel_2.add(serverStatusPane);

		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "Client Status", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_4.setBackground(Color.WHITE);
		panel_1.add(panel_4);
		panel_4.setLayout(null);

		clientStatusPane = new JList();
		clientStatusPane.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		clientStatusPane.setBounds(10, 22, 308, 394);
		panel_4.add(clientStatusPane);
		model = new DefaultListModel<String>();

		JPanel pnControlServer = new JPanel();
		pnControlServer.setBounds(10, 5, 677, 75);
		ipAddress.add(pnControlServer);
		pnControlServer.setLayout(null);

		btnRunServer = new JButton("Run");
		btnRunServer.setForeground(new Color(38, 154, 217));
		btnRunServer.setBounds(434, 24, 70, 25);
		btnRunServer.setFont(new Font("Tahoma", Font.BOLD, 13));
		pnControlServer.add(btnRunServer);
		btnRunServer.setEnabled(true);

		btnStopServer = new JButton("Stop");
		btnStopServer.setForeground(new Color(38, 154, 217));
		btnStopServer.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnStopServer.setBounds(545, 24, 70, 25);
		pnControlServer.add(btnStopServer);
		btnStopServer.setEnabled(false);

		portField = new JTextField();
		portField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		portField.setColumns(10);
		portField.setBounds(284, 8, 105, 30);
		pnControlServer.add(portField);

		JLabel lbIPAddress = new JLabel("IP address");
		lbIPAddress.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbIPAddress.setBackground(new Color(0, 128, 255));
		lbIPAddress.setBounds(10, 11, 76, 25);
		pnControlServer.add(lbIPAddress);

		JLabel lbPort = new JLabel("Port");
		lbPort.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbPort.setBounds(247, 11, 45, 25);
		pnControlServer.add(lbPort);

		ipField = new JTextField();
		ipField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		ipField.setColumns(10);
		ipField.setBounds(85, 8, 141, 30);
		pnControlServer.add(ipField);

		autoCheckbox = new JCheckBox("Auto");
		autoCheckbox.setBounds(10, 43, 97, 23);
		pnControlServer.add(autoCheckbox);

		autoCheckbox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (autoCheckbox.isSelected()) {
					ipField.setText("127.0.0.1");
					portField.setText("8888");
					ipField.setEditable(false);
					portField.setEditable(false);
				} else {
					ipField.setText("");
					portField.setText("");
					ipField.setEditable(true);
					portField.setEditable(true);
				}
			}
		});

		setVisible(true);

		// Logistic
		server = new TCP_Server();
		btnRunServer.addActionListener(e -> btnRunActionPerformed(e));
		btnStopServer.addActionListener(e -> btnStopActionPerformed(e));

	}
}
