package Client.Views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Client.Controller;

public class ForgotPWScreen extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField usernameField;
	private ImageIcon iconTitle = new ImageIcon(HomeScreen.class.getResource("/Image/iconmini.jpg"));

	private boolean handleResetPW() {
		if (usernameField.getText().length() == 0) {
			JOptionPane.showMessageDialog(this, "Missing connection config", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		Controller.getInstance().forgotPassword(usernameField.getText());

		return true;
	}

	public void showMessage(String content, String heading, int opt) {
		JOptionPane.showMessageDialog(this, content, heading, opt);
	}

	public ForgotPWScreen() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2- getSize().width/2, dim.height/2 - getSize().height/2);
		Image icon = iconTitle.getImage();    
		setIconImage(icon);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 224);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("FORGOT PASSWORD");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setForeground(new Color(0, 0, 160));
		lblNewLabel.setBounds(134, 11, 168, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Enter username");
		lblNewLabel_1.setBounds(49, 47, 105, 14);
		contentPane.add(lblNewLabel_1);

		usernameField = new JTextField();
		usernameField.setBounds(49, 73, 329, 28);
		contentPane.add(usernameField);
		usernameField.setColumns(10);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().handleScreen("loginScreen", true);
				setVisible(false);
			}
		});
		btnCancel.setBounds(136, 135, 89, 23);
		contentPane.add(btnCancel);

		JButton btnResetPassword = new JButton("Reset password");
		btnResetPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleResetPW();
			}
		});
		btnResetPassword.setBounds(235, 135, 143, 23);
		contentPane.add(btnResetPassword);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				setVisible(false);
				Controller.getInstance().handleScreen("loginScreen", true);
			}
		});
	}
}
