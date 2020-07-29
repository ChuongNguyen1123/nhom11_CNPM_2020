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

import database.ConnectionDB;

public class ViewListNameTGB extends JFrame {
	private static final long serialVersionUID = 1L;

	private JPanel p;

	private JTable table;

	private DefaultTableModel model;

	private JScrollPane scrollPane;

	private JButton btXoa, btQuayLai;

	private ConnectionDB connectionDB;

	public ViewListNameTGB() throws ClassNotFoundException, SQLException {
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

//		truy xuất xuống DB để lấy dữ liệu từ table config
		connectionDB = new ConnectionDB();
		Connection con = connectionDB.getConnection();
		String sql = "SELECT config.Name_TGB FROM config";
		PreparedStatement pre = con.prepareStatement(sql);
		ResultSet rs = pre.executeQuery();
		int row = 0;
		while (rs.next()) {
			// add row, số row sẽ tương đương với số dòng của table config
			model.addRow(new Object[0]);
			// set dữ liệu cho cột 0
			model.setValueAt(rs.getString("Name_TGB"), row, 0);
			// set dữ liệu cho cột 1
			model.setValueAt(false, row, 1);
			row++;
		}

		// add 2 button
		p.add(btXoa = new JButton("Xoa"));
		p.add(btQuayLai = new JButton("Quay Lai"));
		btXoa.setBounds(100, 500, 100, 30);
		btQuayLai.setBounds(400, 500, 100, 30);
		btXoa.setBackground(Color.LIGHT_GRAY);
		btQuayLai.setBackground(Color.LIGHT_GRAY);

		// Action xóa
		btXoa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					connectionDB = new ConnectionDB();
					Connection connect = connectionDB.getConnection();

//					Khởi tạo mảng list chứa các dòng đã chọn 
					List<Integer> list = new ArrayList<Integer>();
// 					Duyệt các dòng của table, add vào mảng list những dòng có dấu check ở cột 1
					for (int i = 0; i < table.getRowCount(); i++) {
						boolean check = Boolean.valueOf(table.getValueAt(i, 1).toString());
						if (check) {
							list.add(i);
						}
					}

//					Duyệt mảng list để lấy ra tên tgb dựa vào vị trí của dòng check
					for (int i = 0; i < list.size(); i++) {
						String nameTGB = model.getValueAt(list.get(i), 0).toString();
//						Xóa dòng của table
						model.removeRow(list.get(i));
//						xóa tên TGB dưới table config
						String sqlDelete = "DELETE FROM config WHERE Name_TGB = '" + nameTGB + "'";
						PreparedStatement pre = connect.prepareStatement(sqlDelete);
						pre.executeUpdate();

						String sqlXoaBang = " DROP TABLE " + nameTGB ;
						PreparedStatement preparedStatement = connect.prepareStatement(sqlXoaBang);
						preparedStatement.executeUpdate();
// 						xét lại mảng list sao khi xóa
						
						for (int j = i + 1; j < list.size(); j++) {
							list.set(j, list.get(j) - 1);
						}
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		// Action quay lại
		btQuayLai.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
//				gọi class mainview
				new MainView();
//				Đóng giao diện mở TGB
				closed();
			}
		});

		setSize(600, 600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setLocationRelativeTo(null);
	}

//	Phương thức đóng giao diện ViewListNameTGB
	public void closed() {
		dispose();
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		new ViewListNameTGB();
	}
}
