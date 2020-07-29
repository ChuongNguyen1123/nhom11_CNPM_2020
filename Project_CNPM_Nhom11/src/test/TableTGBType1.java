package test;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import java.awt.Color;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class TableTGBType1 extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTable table_1;
	private JMenuBar menuBar;
	private JMenu advance;
	private JMenu mColor;
	private JMenu mDefault;
	private JMenu mAlarm;
	private JRadioButtonMenuItem blackColor, blueColor;
	private JCheckBoxMenuItem defOn, alarmOn;
	private JButton btnSave;

	/**
	 * Create the frame.
	 */
	public TableTGBType1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));

		
		showNewTGB();
		addMenu();
		setResizable(false);
		setSize(600, 350);
		setVisible(true);
		setTitle("Model Type 1");
	}

	private void addMenu() {

		//
		mColor = new JMenu("Color");
		mAlarm = new JMenu("Alarm Clock");
		mDefault = new JMenu("Default Display");
		//
		menuBar = new JMenuBar();
		advance = new JMenu("Advance");
		// S
		advance.add(mColor);
		advance.add(mAlarm);
		advance.add(mDefault);
		//
		ButtonGroup g1 = new ButtonGroup();
		blackColor = new JRadioButtonMenuItem("balck");
		blackColor.setSelected(true);
		g1.add(blackColor);
		blueColor = new JRadioButtonMenuItem("blue");
		g1.add(blueColor);
		mColor.add(blackColor);
		mColor.add(blueColor);

		//
		alarmOn = new JCheckBoxMenuItem("Turn On");
		alarmOn.setSelected(false);
		mAlarm.add(alarmOn);
		//
		defOn = new JCheckBoxMenuItem("Is Default");
		defOn.setSelected(false);
		mDefault.add(defOn);
		//
		menuBar.add(advance);
		setJMenuBar(menuBar);

		btnSave = new JButton("Save");
		menuBar.add(btnSave);

	}

	private void showNewTGB() {
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane);

		String[] columName = { "Thứ 2", "Thứ 3", "Thứ 4", "Thứ 5", "Thứ 6", "Thứ 7", "Chủ Nhật" };
		DefaultTableModel defTable = new DefaultTableModel(new Object[5][7], columName);
		table = new JTable(defTable);
		table.setBorder(new LineBorder(new Color(0, 0, 0), 2));
//		table.setEnabled(false); // chỉ được xem
		table.setCellSelectionEnabled(false); // chỉ được chọn 1 ô
		table.getTableHeader().setReorderingAllowed(false);
		scrollPane.setViewportView(table);

		JLabel lblSng = new JLabel("SÁNG");
		lblSng.setBackground(Color.CYAN);
		scrollPane.setRowHeaderView(lblSng);
//
		JScrollPane scrollPane_1 = new JScrollPane();
		contentPane.add(scrollPane_1);
		DefaultTableModel defTable1 = new DefaultTableModel(new Object[5][7], columName);
		table_1 = new JTable(defTable1);
//		table_1 = new JTable(new Object[4][6], new String[6]);
		table_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		table_1.setCellSelectionEnabled(false); // chỉ được chọn 1 ô
		table_1.getTableHeader().setReorderingAllowed(false);
		scrollPane_1.setViewportView(table_1);

		JLabel lblChiu = new JLabel("CHIỀU");
		scrollPane_1.setRowHeaderView(lblChiu);

	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
					TableTGBType1 frame = new TableTGBType1();
//					frame.setVisible(true);
//					frame.table.getModel().setValueAt("asss", rowIndex, columnIndex);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
	}

}
