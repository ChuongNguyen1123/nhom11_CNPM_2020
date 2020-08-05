package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class ChooseTypeTGB extends JFrame {

	private JPanel contentPane;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private JButton cancleBt;
	private TableTGBType1 tableDataType1;
	private TableTGBType2 tableDataType2;
	private TableTGBType3 tableDataType3;

	/**
	 * Create the frame.
	 */
	public ChooseTypeTGB() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setSize(new Dimension(400, 300));
		setResizable(false);
		tableDataType1 = new TableTGBType1();
		tableDataType2 = new TableTGBType2();
		tableDataType3 = new TableTGBType3();
		addModels();
		addAction();

	}

	public JPanel getContentPane() {
		return contentPane;
	}

	public JButton getBtnNewButton_1() {
		return btnNewButton_1;
	}

	public JButton getBtnNewButton_2() {
		return btnNewButton_2;
	}

	public JButton getBtnNewButton_3() {
		return btnNewButton_3;
	}

	public JButton getCancleBt() {
		return cancleBt;
	}

	private void addAction() {
		cancleBt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);

			}
		});
	}

	public TableTGBType3 getTableDataType3() {
		return tableDataType3;
	}

	public void setTableDataType3(TableTGBType3 tableDataType3) {
		this.tableDataType3 = tableDataType3;
	}

	public TableTGBType1 getTableDataType1() {
		return tableDataType1;
	}

	public TableTGBType2 getTableDataType2() {
		return tableDataType2;
	}

	private void addModels() {
		try {
			JScrollPane scrollPane = new JScrollPane();
			contentPane.add(scrollPane, BorderLayout.CENTER);
			JPanel mainPanel = new JPanel();
			scrollPane.setViewportView(mainPanel);
//		panel.setLayout(new GridLayout(3, 1));
			mainPanel.setLayout(new GridLayout(3, 1, 0, 20));
			// cancle
			JPanel southPane = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			contentPane.add(southPane, BorderLayout.SOUTH);
			cancleBt = new JButton("Cancle");
			southPane.add(cancleBt);
			// type1
			JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
			btnNewButton_1 = new JButton("");
//		ImageIcon img1 = new ImageIcon("resource/model1.png");
			Image img1;

			img1 = ImageIO.read(getClass().getResource("/model1.png"));

			btnNewButton_1.setPreferredSize(new Dimension(300, 150));
			btnNewButton_1.setIcon(new ImageIcon(img1));
			panel1.add(btnNewButton_1);
			JLabel lblType1 = new JLabel("Type 1");
			panel1.add(lblType1);
			mainPanel.add(panel1);
			// type 2
			JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
			btnNewButton_2 = new JButton("");
			Image img2 = ImageIO.read(getClass().getResource("/model2.png"));
			btnNewButton_2.setPreferredSize(new Dimension(300, 150));
			btnNewButton_2.setIcon(new ImageIcon(img2));
			panel2.add(btnNewButton_2);
			JLabel lblType2 = new JLabel("Type 2");
			panel2.add(lblType2);
			mainPanel.add(panel2);
			// type 3
			JPanel panel3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
			btnNewButton_3 = new JButton("");
			Image img3 = ImageIO.read(getClass().getResource("/model3.png"));
			btnNewButton_3.setPreferredSize(new Dimension(300, 150));
			btnNewButton_3.setIcon(new ImageIcon(img3));
			panel3.add(btnNewButton_3);
			mainPanel.add(panel3);
			JLabel lblType3 = new JLabel("Type 3");
			panel3.add(lblType3);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChooseTypeTGB frame = new ChooseTypeTGB();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
