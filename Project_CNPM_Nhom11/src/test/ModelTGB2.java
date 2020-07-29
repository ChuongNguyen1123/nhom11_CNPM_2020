package test;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

public class ModelTGB2 extends JFrame {

	private JPanel contentPane;
	private JTable table1;
	private JTable table2;
	private JScrollPane scrollPane1;
	private JScrollPane scrollPane2;
	private JButton printBt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModelTGB2 frame = new ModelTGB2();
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
	public ModelTGB2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		addTable();
		addBTAction();
		contentPane.add(scrollPane1, BorderLayout.CENTER);
	}

	private void addTable() {
		String[] columName = { "Thứ 2", "Thứ 3", "Thứ 4", "Thứ 5", "Thứ 6", "Thứ 7", "Chủ Nhật" };
		Object[][] data = { { "gj", "Lý", "Hóa", "", "Sử", "Công Dân", "Địa" },
				{ "Toán", "Lý", "Hóa", "", "Sử", "Công Dân", "Địa" },
				{ "Toán", "Lý", "Hóa", "Anh Văn", "Sử", "Thể Dục", "Địa" },
				{ "Sinh", "Lý", "Hóa", "", "Sử", "", "Địa" }, 
				{ "", "Lý", "Hóa", "", "Sử", "Công Nghệ", "Địa" },
				{ "Hóa", "Lý", "Hóa", "", "Sử", "Công Dân", "Địa" } };
		table1 = new JTable(data, columName);
		scrollPane1 = new JScrollPane(table1);
		table1.setFillsViewportHeight(true);
		table1.getTableHeader().setReorderingAllowed(false);
		System.out.println(table1.getColumnName(2));
		
	//
		String[] columName2 = { "Thứ 2", "Thứ 3", "Thứ 4", "Thứ 5", "Thứ 6", "Thứ 7", "Chủ Nhật" };
		Object[][] data2 = { { "gj", "Lý", "Hóa", "", "Sử", "Công Dân", "Địa" },
				{ "Toán", "Lý", "Hóa", "", "Sử", "Công Dân", "Địa" },
				{ "Toán", "Lý", "Hóa", "Anh Văn", "Sử", "Thể Dục", "Địa" },
				{ "Sinh", "Lý", "Hóa", "", "Sử", "", "Địa" }, 
				{ "", "Lý", "Hóa", "", "Sử", "Công Nghệ", "Địa" },
				{ "Hóa", "Lý", "Hóa", "", "Sử", "Công Dân", "Địa" } };
		table2 = new JTable(data2, columName2);
		scrollPane2 = new JScrollPane(table2);
		table2.setFillsViewportHeight(true);
		table2.getTableHeader().setReorderingAllowed(false);
//		System.out.println(table.getValueAt(2, 2));
	}

	private void addBTAction() {
		printBt = new JButton("print");
		printBt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				List<String> listVL = new ArrayList<String>();
				for (int i = 0; i <= 5; i++) {
					for (int j = 0; j < 7; j++) {
						listVL.add((String) table1.getValueAt(i, j));
					}
					
				}
			for (String s : listVL) {
				System.out.println(s);
			}
			}
		});
		contentPane.add(printBt, BorderLayout.EAST);
	}

}
