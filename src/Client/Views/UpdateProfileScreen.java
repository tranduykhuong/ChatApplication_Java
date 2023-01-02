package Client.Views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.toedter.calendar.JDateChooser;

import Client.Controller;
import Entity.Packet;

public class UpdateProfileScreen extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nameF;
	private JTextField addressF;
	private JDateChooser dateChooser;
	private ImageIcon iconTitle = new ImageIcon(HomeScreen.class.getResource("/Image/iconmini.jpg"));

	String id = "";
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					UpdateProfileScreen frame = new UpdateProfileScreen();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public UpdateProfileScreen() {
		Image icon = iconTitle.getImage();
		setIconImage(icon);
		setTitle("Update Profile");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 445, 243);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(SystemColor.controlShadow));
		panel.setBounds(10, 10, 409, 186);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Họ tên");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel.setBounds(20, 42, 107, 13);
		panel.add(lblNewLabel);

		nameF = new JTextField();
		lblNewLabel.setLabelFor(nameF);
		nameF.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		nameF.setBounds(20, 59, 172, 21);
		panel.add(nameF);
		nameF.setColumns(10);

		JLabel lblHTn = new JLabel("Ngày sinh");
		lblHTn.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblHTn.setBounds(20, 91, 107, 13);
		panel.add(lblHTn);

		dateChooser = new JDateChooser();
		lblHTn.setLabelFor(dateChooser);
		dateChooser.setBackground(new Color(255, 255, 255));
		dateChooser.setBounds(20, 111, 172, 21);
		dateChooser.setDate(new Date());
		panel.add(dateChooser);

		JLabel lblaCh = new JLabel("Địa chỉ");
		lblaCh.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblaCh.setBounds(214, 42, 107, 13);
		panel.add(lblaCh);

		addressF = new JTextField();
		lblaCh.setLabelFor(addressF);
		addressF.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		addressF.setColumns(10);
		addressF.setBounds(214, 59, 172, 21);
		panel.add(addressF);

		JComboBox<String> genderDrop = new JComboBox<String>();
		genderDrop.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		genderDrop.setModel(new DefaultComboBoxModel<String>(new String[] { "Nam", "Nữ" }));
		genderDrop.setBounds(214, 111, 172, 21);
		panel.add(genderDrop);

		JLabel lblGiiTnh = new JLabel("Giới tính");
		lblGiiTnh.setLabelFor(genderDrop);
		lblGiiTnh.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblGiiTnh.setBounds(214, 91, 107, 13);
		panel.add(lblGiiTnh);

		JButton updateBtn = new JButton("UPDATE");
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				String dob = formatter.format(dateChooser.getDate());
				String fullname = nameF.getText();
				String address = addressF.getText();
				String gender = genderDrop.getSelectedItem().toString();
				if (fullname.length() > 0) {
					Controller.getInstance().sendTextMessage(new Packet("updateProfile",
							id + "`" + fullname + "`" + address + "`" + dob + "`" + gender, id).toString());
				} else {
					showMessage("Vui lòng cập nhật Fullname", "Warning", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		updateBtn.setFont(new Font("Times New Roman", Font.BOLD, 10));
		updateBtn.setBounds(163, 143, 85, 29);
		panel.add(updateBtn);

		JLabel lblCpNhtThng = new JLabel("Cập nhật thông tin");
		lblCpNhtThng.setForeground(new Color(0, 0, 160));
		lblCpNhtThng.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblCpNhtThng.setBounds(146, 11, 143, 20);
		panel.add(lblCpNhtThng);
	}

	public void showMessage(String content, String heading, int opt) {
		JOptionPane.showMessageDialog(this, content, heading, opt);
	}

	public void setData(String id) {
		this.id = id;
	}
}
