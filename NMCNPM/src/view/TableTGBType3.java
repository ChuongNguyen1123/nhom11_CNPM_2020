package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class TableTGBType3 extends JFrame {
	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu advance;
	private JMenu mColor;
	private JMenu mDefault;
	private JMenu mAlarm;
	private JRadioButtonMenuItem blackColor, blueColor;
	private JCheckBoxMenuItem defOn, alarmOn;
	private JButton btnSave;
	private JTextField txtNameTgb;
	private boolean isDefault;
//	private JScrollPane scrollPane;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JScrollPane scrollPane_2;
	private JScrollPane scrollPane_3;
	private JScrollPane scrollPane_4;
	private JScrollPane scrollPane_5;
	private JScrollPane scrollPane_6;
	private JPanel panel;
	private DefaultTableModel defTable;
	private JTable table;
	private JButton btnNewButton;
	private JPanel panel_1;
	private DefaultTableModel defTable1;
	private JTable table_1;
	private JButton btnNewButton_1;
	private JPanel panel_2;
	private DefaultTableModel defTable2;
	private JTable table_2;
	private JButton btnNewButton_2;
	private JPanel panel_3;
	private DefaultTableModel defTable3;
	private JTable table_3;
	private JButton btnNewButton_3;
	private JPanel panel_4;
	private DefaultTableModel defTable4;
	private JTable table_4;
	private JButton btnNewButton_4;
	private JPanel panel_5;
	private DefaultTableModel defTable5;
	private JTable table_5;
	private JButton btnNewButton_5;
	private JPanel panel_6;
	private DefaultTableModel defTable6;
	private JTable table_6;
	private JButton btnNewButton_6;
	private JLabel lblTh2;
	private JLabel lblTh3;
	private JLabel lblTh4;
	private JLabel lblTh5;
	private JLabel lblTh6;
	private JLabel lblTh7;
	private JLabel lblCN;
	private List<DefaultTableModel> listDefModel; // dùng để đóng gói các dữ liệu cho dễ sử lý sau này
	private List<JTable> listTable; // chủ yếu dùng cho việc clear

	public List<JTable> getListTable() {
		return listTable;
	}

	/**
	 * Create the frame.
	 */
	public TableTGBType3() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 7));
		setSize(new Dimension(650, 300));
		setVisible(false);
		initTable();

		listDefModel = new ArrayList<>();
		listDefModel.add(defTable);
		listDefModel.add(defTable1);
		listDefModel.add(defTable2);
		listDefModel.add(defTable3);
		listDefModel.add(defTable4);
		listDefModel.add(defTable5);
		listDefModel.add(defTable6);
		//
		listTable = new ArrayList<JTable>();
		listTable.add(table);
		listTable.add(table_1);
		listTable.add(table_2);
		listTable.add(table_3);
		listTable.add(table_4);
		listTable.add(table_5);
		listTable.add(table_6);

		addMenu();
		addRow();
		addSelfdAction();
		setTitle(" TGB Type 3");
	}

	private void initTable() {

		//
		String[] columName = { "" };
		Object[][] data = { { "Action:" }, { "Time:" } };
		//
		// thứ 2
		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		contentPane.add(scrollPane);

		panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(new BorderLayout(0, 0));

		defTable = new DefaultTableModel(data.clone(), columName.clone());
		table = new JTable();
		table.setModel(defTable);
		panel.add(table, BorderLayout.CENTER);

		btnNewButton = new JButton("New Action");
		panel.add(btnNewButton, BorderLayout.SOUTH);

		lblTh2 = new JLabel("Thứ 2");
		scrollPane.setColumnHeaderView(lblTh2);

		// thứ 3
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		contentPane.add(scrollPane_1);

		panel_1 = new JPanel();
		scrollPane_1.setViewportView(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		defTable1 = new DefaultTableModel(data.clone(), columName.clone());
		table_1 = new JTable();
		table_1.setModel(defTable1);
		panel_1.add(table_1, BorderLayout.CENTER);

		btnNewButton_1 = new JButton("New Action");
		panel_1.add(btnNewButton_1, BorderLayout.SOUTH);

		lblTh3 = new JLabel("Thứ 3");
		scrollPane_1.setColumnHeaderView(lblTh3);

		// thứ 4
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		contentPane.add(scrollPane_2);

		panel_2 = new JPanel();
		scrollPane_2.setViewportView(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));

		defTable2 = new DefaultTableModel(data.clone(), columName.clone());
		table_2 = new JTable();
		table_2.setModel(defTable2);
		panel_2.add(table_2, BorderLayout.CENTER);

		btnNewButton_2 = new JButton("New Action");
		panel_2.add(btnNewButton_2, BorderLayout.SOUTH);

		lblTh4 = new JLabel("Thứ 4");
		scrollPane_2.setColumnHeaderView(lblTh4);

		// thứ 5
		scrollPane_3 = new JScrollPane();
		scrollPane_3.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		contentPane.add(scrollPane_3);

		panel_3 = new JPanel();
		scrollPane_3.setViewportView(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));

		defTable3 = new DefaultTableModel(data.clone(), columName.clone());
		table_3 = new JTable();
		table_3.setModel(defTable3);
		panel_3.add(table_3, BorderLayout.CENTER);

		btnNewButton_3 = new JButton("New Action");
		panel_3.add(btnNewButton_3, BorderLayout.SOUTH);

		lblTh5 = new JLabel("Thứ 5");
		scrollPane_3.setColumnHeaderView(lblTh5);

		// thứ 6
		scrollPane_4 = new JScrollPane();
		scrollPane_4.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		contentPane.add(scrollPane_4);

		panel_4 = new JPanel();
		scrollPane_4.setViewportView(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));

		btnNewButton_4 = new JButton("New Action");
		panel_4.add(btnNewButton_4, BorderLayout.SOUTH);

		defTable4 = new DefaultTableModel(data.clone(), columName.clone());
		table_4 = new JTable();
		table_4.setModel(defTable4);
		panel_4.add(table_4, BorderLayout.CENTER);

		lblTh6 = new JLabel("Thứ 6");
		scrollPane_4.setColumnHeaderView(lblTh6);

		// thứ 7
		scrollPane_5 = new JScrollPane();
		scrollPane_5.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		contentPane.add(scrollPane_5);

		panel_5 = new JPanel();
		scrollPane_5.setViewportView(panel_5);
		panel_5.setLayout(new BorderLayout(0, 0));

		defTable5 = new DefaultTableModel(data.clone(), columName.clone());
		table_5 = new JTable();
		table_5.setModel(defTable5);
		panel_5.add(table_5, BorderLayout.CENTER);

		btnNewButton_5 = new JButton("New Action");
		panel_5.add(btnNewButton_5, BorderLayout.SOUTH);

		lblTh7 = new JLabel("Thứ 7");
		scrollPane_5.setColumnHeaderView(lblTh7);

		// chủ nhật
		scrollPane_6 = new JScrollPane();
		scrollPane_6.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		contentPane.add(scrollPane_6);

		panel_6 = new JPanel();
		scrollPane_6.setViewportView(panel_6);
		panel_6.setLayout(new BorderLayout(0, 0));

		defTable6 = new DefaultTableModel(data.clone(), columName.clone());
		table_6 = new JTable();
		table_6.setModel(defTable6);
		panel_6.add(table_6, BorderLayout.CENTER);

		btnNewButton_6 = new JButton("New Action");
		panel_6.add(btnNewButton_6, BorderLayout.SOUTH);

		lblCN = new JLabel("Chủ Nhật");
		scrollPane_6.setColumnHeaderView(lblCN);
	}

	public JMenu getmColor() {
		return mColor;
	}

	public void setmColor(JMenu mColor) {
		this.mColor = mColor;
	}

	public JMenu getmDefault() {
		return mDefault;
	}

	public JMenu getmAlarm() {
		return mAlarm;
	}

	public JButton getBtnSave() {
		return btnSave;
	}

	public JTextField getTxtNameTgb() {
		return txtNameTgb;
	}

	public List<DefaultTableModel> getListDefModel() {
		return listDefModel;
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

		txtNameTgb = new JTextField();
		txtNameTgb.setText("name_tgb");
		menuBar.add(txtNameTgb);
		txtNameTgb.setColumns(10);

		btnSave = new JButton("Save");
		menuBar.add(btnSave);

	}

	private void addRow() {
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String[] a = { "Action:" };
				String[] t = { "Time:" };
				defTable.addRow(a);
				defTable.addRow(t);
			}
		});
		btnNewButton_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String[] a = { "Action:" };
				String[] t = { "Time:" };
				defTable1.addRow(a);
				defTable1.addRow(t);
			}
		});
		btnNewButton_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String[] a = { "Action:" };
				String[] t = { "Time:" };
				defTable2.addRow(a);
				defTable2.addRow(t);
			}
		});
		btnNewButton_3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String[] a = { "Action:" };
				String[] t = { "Time:" };
				defTable3.addRow(a);
				defTable3.addRow(t);
			}
		});
		btnNewButton_4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String[] a = { "Action:" };
				String[] t = { "Time:" };
				defTable4.addRow(a);
				defTable4.addRow(t);
			}
		});
		btnNewButton_5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String[] a = { "Action:" };
				String[] t = { "Time:" };
				defTable5.addRow(a);
				defTable5.addRow(t);
			}
		});
		btnNewButton_6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String[] a = { "Action:" };
				String[] t = { "Time:" };
				defTable6.addRow(a);
				defTable6.addRow(t);
			}
		});
	}

	private void addSelfdAction() {
		defOn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				isDefault = !isDefault;
				System.out.println(isDefault);
			}
		});

	}

	public void cleanData() {
		for (JTable t : listTable) {
//			for (int i = 0; i < defM.getRowCount(); i++) {
//				for (int j = 0; j < defM.getColumnCount(); j++) {
//					defM.setValueAt("", i, j);
//				}
//			}
			if (null != t.getCellEditor()) {
				// there is an edit in progress
				t.getCellEditor().stopCellEditing();
			}

			((DefaultTableModel) t.getModel()).setRowCount(0);
		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TableTGBType3 frame = new TableTGBType3();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
