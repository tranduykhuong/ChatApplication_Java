package Client.Views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JProgressBar;

public class LoadingScreen extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel ldinglabel;
	private ImageIcon iconLg = new ImageIcon(HomeScreen.class.getResource("/Image/AChat.png"));
	private ImageIcon bgimg = new ImageIcon(HomeScreen.class.getResource("/Image/bgld.png"));

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoadingScreen frame = new LoadingScreen();
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
	public LoadingScreen() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2- getSize().width/2, dim.height/2 - getSize().height/2);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 530, 365);
		contentPane = new JPanel();
		contentPane.setBackground(Color.RED);
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		ldinglabel = new JLabel("Loading...");
		ldinglabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		ldinglabel.setBounds(208, 322, 133, 19);
		contentPane.add(ldinglabel);

		JLabel lblNewLabel_2 = new JLabel("Java Project");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2.setBounds(41, 191, 181, 28);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_1 = new JLabel("New label");
		Image imgLogo = iconLg.getImage();
		lblNewLabel_1.setIcon(new ImageIcon(imgLogo));
		lblNewLabel_1.setBounds(0, 81, 267, 142);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel = new JLabel("backgroundImg");
		Image imgbg = bgimg.getImage();
		lblNewLabel.setIcon(new ImageIcon(imgbg));
		lblNewLabel.setBounds(0, 0, 538, 370);
		contentPane.add(lblNewLabel);

		setUndecorated(true);

	}
}
