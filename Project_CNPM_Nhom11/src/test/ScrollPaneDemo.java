package test;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JButton;

public class ScrollPaneDemo extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private final JButton btnAdd = new JButton("add");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScrollPaneDemo frame = new ScrollPaneDemo();
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
	public ScrollPaneDemo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 3));

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane);

		JLabel lblHeader = new JLabel("Header");
		scrollPane.setColumnHeaderView(lblHeader);
		String[] columName = { "Thứ 2" };
		Object[][] data = { { "GYM" }, { "9:30" }, { "9:30" }, { "9:30" } };
		DefaultTableModel defTable = new DefaultTableModel(data, columName);
		table = new JTable();
		table.setModel(defTable);
		scrollPane.setViewportView(table);

		JScrollPane scrollPane_1 = new JScrollPane();
		contentPane.add(scrollPane_1);
		scrollPane_1.setColumnHeaderView(btnAdd);

		JScrollPane scrollPane_2 = new JScrollPane();
		contentPane.add(scrollPane_2);
		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String[] a = { "a" };
				defTable.addRow(a);

			}
		});
	}

}
