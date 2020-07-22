package view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import model.TGBType1;
import javax.swing.JTextField;

public class TableTGBType2 extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTable table_1;
	private JMenuBar menuBar;
	private JMenu advance;
	private JMenu mColor;
	private JMenu mDefault;
	private JRadioButtonMenuItem blackColor, blueColor;
	private JCheckBoxMenuItem defOn;
	private JButton btnSave;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JTextField txtNameTgb;
	private boolean isDefault;

	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}

	public boolean isDefault() {
		return isDefault;
	}

	/**
	 * Create the frame.
	 */
	public TableTGBType2() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));

		showNewTGB();
		addMenu();
		adSelfdAction();
		contentPane.add(scrollPane);
		contentPane.add(scrollPane_1);
//		setResizable(false);
		setSize(600, 300);
		setVisible(false);
		setTitle("Model Type 2");
	}

	private void addMenu() {

		//
		mColor = new JMenu("Color");
//		mAlarm = new JMenu("Alarm Clock");
		mDefault = new JMenu("Default Display");
		//
		menuBar = new JMenuBar();
		advance = new JMenu("Advance");
		// S
		advance.add(mColor);
//		advance.add(mAlarm);
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
//		alarmOn = new JCheckBoxMenuItem("Turn On");
//		alarmOn.setSelected(false);
//		mAlarm.add(alarmOn);
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

	public JTextField getTxtNameTgb() {
		return txtNameTgb;
	}

	public void showNewTGB() {
		scrollPane = new JScrollPane();
//		contentPane.add(scrollPane);

		String[] columName = { "Thứ 2", "Thứ 3", "Thứ 4", "Thứ 5", "Thứ 6", "Thứ 7", "Chủ Nhật" };
		Object[][] data = { { "", "", "", "", "", " ", "" }, { "", "", "", "", "", " ", "" },
				{ "", "", "", "", "", " ", "" }, { "", "", "", "", "", " ", "" } };
		DefaultTableModel defTable = new DefaultTableModel(data.clone(), columName.clone());
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
		scrollPane_1 = new JScrollPane();
		DefaultTableModel defTable1 = new DefaultTableModel(data.clone(), columName.clone());
		table_1 = new JTable(defTable1);
//		table_1 = new JTable(new Object[4][6], new String[6]);
		table_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		table_1.setCellSelectionEnabled(false); // chỉ được chọn 1 ô
		table_1.getTableHeader().setReorderingAllowed(false);
		scrollPane_1.setViewportView(table_1);

		JLabel lblChiu = new JLabel("CHIỀU");
		scrollPane_1.setRowHeaderView(lblChiu);

	}

	public JPanel getContentPane() {
		return contentPane;
	}

	public JTable getTable() {
		System.out.println(table.getModel().getValueAt(2, 2));
		return table;
	}

	public JTable getTable_1() {
		return table_1;
	}

	public JMenu getAdvance() {
		return advance;
	}

	public JMenu getmColor() {
		return mColor;
	}

	public JMenu getmDefault() {
		return mDefault;
	}

	public JRadioButtonMenuItem getBlackColor() {
		return blackColor;
	}

	public JRadioButtonMenuItem getBlueColor() {
		return blueColor;
	}

	public JCheckBoxMenuItem getDefOn() {
		return defOn;
	}

//	public JCheckBoxMenuItem getAlarmOn() {
//		return alarmOn;
//	}

	public JButton getBtnSave() {
		return btnSave;
	}

	public void cleanData() {
		// vì mình dùng 5 dòng mặc định hiển thị nên không thể clear bằng cách set
		// rowcount =0 ( khác với loại thứ 3)
		if (null != table.getCellEditor()) {
			// there is an edit in progress
			table.getCellEditor().stopCellEditing();
		}
		for (int i = 0; i < table.getRowCount(); i++) {
			for (int j = 0; j < table.getColumnCount(); j++) {
				table.setValueAt("", i, j);
			}
		}
		// table 2
		if (null != table_1.getCellEditor()) {
			// there is an edit in progress
			table_1.getCellEditor().stopCellEditing();
		}

		for (int i = 0; i < table_1.getRowCount(); i++) {
			for (int j = 0; j < table_1.getColumnCount(); j++) {
				table_1.setValueAt("", i, j);
			}
		}
	}

	private void adSelfdAction() {
		defOn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				isDefault = !isDefault;
				System.out.println(isDefault);
			}
		});

	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
		TableTGBType2 frame = new TableTGBType2();
		frame.setVisible(true);
//					frame.table.getModel().setValueAt("asss", rowIndex, columnIndex);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
	}

}
