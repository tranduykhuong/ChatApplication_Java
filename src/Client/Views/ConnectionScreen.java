package Client.Views;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Client.Controller;

public class ConnectionScreen extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField ipField;
	private JLabel lblPort;
	private JTextField portField;
	JCheckBox autoCheckBox;
	JButton btnConnect;
	JButton btnDisconnect;
	private ImageIcon iconTitle = new ImageIcon(HomeScreen.class.getResource("/Image/iconmini.jpg"));

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

	private void btnConnectActionPerformed(ActionEvent e) {
		if (!checkValidation()) {
			return;
		}
		var port = Integer.valueOf(portField.getText());

		if (!Controller.getInstance().connect(ipField.getText(), port)) {
			JOptionPane.showMessageDialog(this, "Can not connect to Server", "Warning", JOptionPane.WARNING_MESSAGE);
			return;
		}
		JOptionPane.showMessageDialog(this, "Connect successfully!", "Connection", JOptionPane.INFORMATION_MESSAGE);

		btnDisconnect.setEnabled(true);
		btnConnect.setEnabled(false);
		ipField.setEditable(false);
		portField.setEditable(false);
		autoCheckBox.setEnabled(false);

		Controller.getInstance().startListen();
		Controller.getInstance().handleScreen("homeScreen", true);
		setVisible(false);
	}

	private void btnDisconnectActionPerformed(ActionEvent e) {
		btnDisconnect.setEnabled(false);
		btnConnect.setEnabled(true);
		ipField.setEditable(true);
		portField.setEditable(true);
		autoCheckBox.setEnabled(true);
		Controller.getInstance().sendTextMessage("Disconnect");
		Controller.getInstance().disconnect();
	}

	public void handleDisconnect() {
		btnDisconnect.setEnabled(false);
		btnConnect.setEnabled(true);
		ipField.setEditable(true);
		portField.setEditable(true);
		autoCheckBox.setEnabled(true);
		Controller.getInstance().sendTextMessage("Disconnect");
		Controller.getInstance().disconnect();
	}

	/**
	 * Create the frame.
	 */
	public ConnectionScreen() {
		Image icon = iconTitle.getImage();    
		setIconImage(icon); 
		setTitle("Connection");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				String[] options = { "Yes", "No" };
				int result = JOptionPane.showOptionDialog(null, "Are you sure you want to exit?", "Confirmation",
						JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
				if (result == 0) {
					setVisible(false);
				}
			}
		});
		setBounds(100, 100, 324, 204);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("IP");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel.setBounds(10, 26, 45, 13);
		contentPane.add(lblNewLabel);

		ipField = new JTextField();
		ipField.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblNewLabel.setLabelFor(ipField);
		ipField.setBounds(76, 21, 214, 26);
		contentPane.add(ipField);
		ipField.setColumns(10);

		lblPort = new JLabel("Port");
		lblPort.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblPort.setBounds(10, 64, 45, 13);
		contentPane.add(lblPort);

		portField = new JTextField();
		portField.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblPort.setLabelFor(portField);
		portField.setColumns(10);
		portField.setBounds(76, 57, 214, 26);
		contentPane.add(portField);

		btnConnect = new JButton("Connect");
		btnConnect.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnConnect.setBounds(197, 124, 93, 30);
		contentPane.add(btnConnect);

		btnDisconnect = new JButton("Disconnect");
		btnDisconnect.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnDisconnect.setBounds(76, 124, 111, 30);
		contentPane.add(btnDisconnect);
		btnDisconnect.setEnabled(false);

		autoCheckBox = new JCheckBox("Auto");
		autoCheckBox.setBounds(76, 90, 97, 23);
		contentPane.add(autoCheckBox);
		autoCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (autoCheckBox.isSelected()) {
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

		// LOGISTIC
		btnConnect.addActionListener(e -> btnConnectActionPerformed(e));
		btnDisconnect.addActionListener(e -> btnDisconnectActionPerformed(e));
	}
}
