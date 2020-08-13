package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import controller.Controller;
import database.ConnectionDB;
import model.Model;

public class ViewTableTGB extends JFrame {
	private static final long serialVersionUID = 1L;

	private JPanel p;

	private JTable table;

	private DefaultTableModel model;

	private JScrollPane scrollPane;

	private JButton btUpdate, btQuayLai;
	
	private JLabel lblSang, lblChieu;


	public ViewTableTGB() throws ClassNotFoundException, SQLException {
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		// tạo panel
		add(p = new JPanel());
		p.setLayout(null);
		p.setBackground(SystemColor.controlHighlight);

		// tạo scroll panel để chứ table
		scrollPane = new JScrollPane();
		scrollPane.setBounds(80, 10, 600, 151);
		p.add(scrollPane);

		// tạo table và hiển thị nó lên scroll panel
		table = new JTable();
		scrollPane.setViewportView(table);

		// tạo model cho table
		model = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			// khởi tạo các kiểu dữ liệu cho các cột 
			public java.lang.Class<?> getColumnClass(int columnIndex) {
				switch (columnIndex) {
//				Cột 0
				case 0:
					return String.class;
//					Cột 1
				case 1:
					return String.class;
				case 2:
					return String.class;
				case 3:
					return String.class;
				case 4:
					return String.class;
				case 5:
					return String.class;
				case 6:
					return String.class;
				case 7:
					return String.class;

				default:
					return String.class;
				}
			};
		};

		// set model cho table
		table.setModel(model);

		// add title cua cac cot
		model.addColumn("Ca học/Thời gian");
		model.addColumn("Thứ 2");
		model.addColumn("Thứ 3");
		model.addColumn("Thứ 4");
		model.addColumn("Thứ 5");
		model.addColumn("Thứ 6");
		model.addColumn("Thứ 7");
		model.addColumn("Chủ nhật");
		
		
		
		
		for (int i = 0; i < 8; i++) {
//			 add row, số row sẽ co 8 row danh so tu 0 den 7
			model.addRow(new Object[0]);
			model.setValueAt("", i, 0);
			model.setValueAt("", i, 1);
			model.setValueAt("", i, 2);
			model.setValueAt("", i, 3);
			model.setValueAt("", i, 4);
			model.setValueAt("", i, 5);
			model.setValueAt("", i, 6);
			model.setValueAt("", i, 7);
		}

		// add 2 button
		p.add(btUpdate = new JButton("Update"));
		p.add(btQuayLai = new JButton("Quay lại"));
		btUpdate.setBounds(200, 170, 100, 30);
		btQuayLai.setBounds(500, 170, 100, 30);
		btUpdate.setBackground(Color.LIGHT_GRAY);
		btQuayLai.setBackground(Color.LIGHT_GRAY);
		btUpdate.setFont(new Font("Arial", Font.PLAIN, 14));
		btQuayLai.setFont(new Font("Arial", Font.PLAIN, 14));
		

		lblSang = new JLabel("Sáng");
		lblSang.setHorizontalAlignment(SwingConstants.CENTER);
		lblSang.setForeground(SystemColor.desktop);
		lblSang.setFont(new Font("Arial", Font.PLAIN, 18));
		lblSang.setBounds(10, 35, 68, 36);
		p.add(lblSang);

		lblChieu = new JLabel("Chiều");
		lblChieu.setHorizontalAlignment(SwingConstants.CENTER);
		lblChieu.setForeground(SystemColor.desktop);
		lblChieu.setFont(new Font("Arial", Font.PLAIN, 18));
		lblChieu.setBounds(10, 105, 68, 36);
		p.add(lblChieu);

		
		table.getColumnModel().getColumn(0).setPreferredWidth(120);
//		setTitle("Thời gian biểu");
		setSize(700, 250);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

	}
	
	public void setValueRowTable(int row, String caHoc, String thu2, String thu3, String thu4, String thu5, String thu6, String thu7, String cn) {
		model.addRow(new Object[0]);
		model.setValueAt(caHoc, row, 0);
		model.setValueAt(thu2, row, 1);
		model.setValueAt(thu3, row, 2);
		model.setValueAt(thu4, row, 3);
		model.setValueAt(thu5, row, 4);
		model.setValueAt(thu6, row, 5);
		model.setValueAt(thu7, row, 6);
		model.setValueAt(cn, row, 7);
	}

	public JTable getTable() {
		return table;
	}

	public JButton getBtUpdate() {
		return btUpdate;
	}

	public DefaultTableModel getModel() {
		return model;
	}

	public JButton getBtQuayLai() {
		return btQuayLai;
	}

	public void setBtQuayLai(JButton btQuayLai) {
		this.btQuayLai = btQuayLai;
	}
	
	

	public JPanel getP() {
		return p;
	}

	public void setP(JPanel p) {
		this.p = p;
	}

	public JLabel getLblSang() {
		return lblSang;
	}

	public JLabel getLblChieu() {
		return lblChieu;
	}
	
	

	// Phương thức đóng giao diện ViewListNameTGB
	public void closed() {
		dispose();
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		new ViewTableTGB();
	}
}
