package test;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ImageDemo extends JFrame {

	private JPanel contentPane;


	/**
	 * Create the frame.
	 */
	public ImageDemo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		ImageIcon imgThisImg = new ImageIcon("resource/model1.png");
//		lblNewLabel.setIcon(imgThisImg);
		
		JButton btnSelect = new JButton();
		setSize(new Dimension(300,150));
		btnSelect.setIcon(imgThisImg);
		contentPane.add(btnSelect, BorderLayout.SOUTH);
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ImageDemo frame = new ImageDemo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
