package Client.Views;

import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
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

		Controller.getInstance().startListen();
		new HomeScreen().setVisible(true);
		setVisible(false);
	}

	private void btnDisconnectActionPerformed(ActionEvent e) {
		Controller.getInstance().sendTextMessage("Disconnect");
		Controller.getInstance().disconnect();
	}

	/**
	 * Create the frame.
	 */
	public ConnectionScreen() {
		setTitle("Connection");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 356, 172);
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
		lblPort.setBounds(10, 62, 45, 13);
		contentPane.add(lblPort);

		portField = new JTextField();
		portField.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblPort.setLabelFor(portField);
		portField.setColumns(10);
		portField.setBounds(76, 57, 214, 26);
		contentPane.add(portField);

		JButton btnConnect = new JButton("Connect");
		btnConnect.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnConnect.setBounds(197, 93, 93, 30);
		contentPane.add(btnConnect);

		JButton btnDisconnect = new JButton("Disconnect");
		btnDisconnect.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnDisconnect.setBounds(76, 93, 111, 30);
		contentPane.add(btnDisconnect);

		// LOGISTIC
		btnConnect.addActionListener(e -> btnConnectActionPerformed(e));
		btnDisconnect.addActionListener(e -> btnDisconnectActionPerformed(e));
	}
}
