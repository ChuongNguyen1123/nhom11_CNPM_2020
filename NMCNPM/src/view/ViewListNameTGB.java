package view;

import java.awt.Color;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.Controller;
import database.ConnectionDB;
import model.Model;

public class ViewListNameTGB extends JFrame {
	private static final long serialVersionUID = 1L;

	private JPanel p;

	private JTable table;

	private DefaultTableModel model;

	private JScrollPane scrollPane;

	private JButton btXoa, btQuayLai, btXem;

	private ConnectionDB connectionDB;
	
	ViewTableTGB viewTableTGB;

	public ViewListNameTGB() throws ClassNotFoundException, SQLException {
		
		viewTableTGB = new ViewTableTGB();
		viewTableTGB.closed();
		
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		// tạo panel
		add(p = new JPanel());
		p.setLayout(null);
		p.setBackground(Color.WHITE);

		// tạo scroll panel để chứ table
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 560, 400);
		p.add(scrollPane);

		// tạo table và hiển thị nó lên scroll panel
		table = new JTable();
		scrollPane.setViewportView(table);

		// tạo model cho table
		model = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			// khởi tạo các kiểu dữ liệu cho các cột - ở đây mình tạo 2 cột 0-1 , cột 0 là
			// kiểu String và cột 1 là boolean (checkbox)
			public java.lang.Class<?> getColumnClass(int columnIndex) {
				switch (columnIndex) {
//				Cột 0
				case 0:
					return String.class;
//					Cột 1
				case 1:
					return Boolean.class;

				default:
					return String.class;
				}
			};
		};

		// set model cho table
		table.setModel(model);

		// add 2 cột cho model
		model.addColumn("Danh sách thời gian biểu");
		model.addColumn("");


		// add 3 button
		p.add(btXoa = new JButton("Delete"));
		p.add(btQuayLai = new JButton("Quay lai"));
		p.add(btXem = new JButton("Xem"));
		btXoa.setBounds(100, 500, 100, 30);
		btQuayLai.setBounds(400, 500, 100, 30);
		btXem.setBounds(250, 500, 100, 30);
		btXoa.setBackground(Color.LIGHT_GRAY);
		btQuayLai.setBackground(Color.LIGHT_GRAY);
		btXem.setBackground(Color.LIGHT_GRAY);

		
		// Action quay lại
		btQuayLai.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
//				gọi class mainview
				try {
					MainView view = new MainView();
					Model model;
					model = new Model();
					Controller controller = new Controller(view, model);
//				Đóng giao diện mở TGB
					closed();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(248);
		setTitle("Danh sách TGB");
		setSize(600, 600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	public ViewTableTGB getViewTableTGB() {
		return viewTableTGB;
	}

	public JTable getTable() {
		return table;
	}

	public JButton getBtXoa() {
		return btXoa;
	}

	public DefaultTableModel getModel() {
		return model;
	}

	public void setBtXoa(JButton btXoa) {
		this.btXoa = btXoa;
	}

	public JButton getBtQuayLai() {
		return btQuayLai;
	}

	public void setBtQuayLai(JButton btQuayLai) {
		this.btQuayLai = btQuayLai;
	}
	
	
public JButton getBtXem() {
		return btXem;
	}


	public void setBtXem(JButton btXem) {
		this.btXem = btXem;
	}


	//	Phương thức đóng giao diện ViewListNameTGB
	public void closed() {
		dispose();
	}
	

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		new ViewListNameTGB();
	}
}
