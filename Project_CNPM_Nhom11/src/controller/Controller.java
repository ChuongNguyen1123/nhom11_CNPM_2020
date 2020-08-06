package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import model.Model;
import model.TGBType1;
import model.TGBType2;
import model.TGBType3;
import view.ChooseTypeTGB;
import view.MainView;
import view.TableTGBType1;
import view.TableTGBType2;
import view.TableTGBType3;

public class Controller {
	private MainView view;
	private Model model;
	String nameTGB;
	public Controller(MainView view, Model model) {
		super();
		this.view = view;
		this.model = model;
		addAction();
	}
	// Bước 6 của import
	private void importFromFileTGB(File file) throws Exception {
//		boolean isSuccess = model.importFromFiletgb(file);
		String mess = (model.importFromFiletgb(file)) ? "import success" : " error, can't import";
		JOptionPane.showMessageDialog(view, mess);

	}

	// Bước 6 của import
	private void importFromFileXlSX(File inputFile) throws Exception {
//		boolean isSuccess = model.importFromFiletgb(file);
		String mess = (model.importFromFilexlsx(inputFile)) ? "import success" : " error, can't import";
		JOptionPane.showMessageDialog(view, mess);

	}

	private void addAction() {

		actionImportFromFileTGB();
		actionImportFromFileXLSX();
		actionButtonViewListFile();
		actionExport();
		actionCreate();
		actionDataType1();
		actionDataType2();
		actionDataType3();
		actionChooseType();
		addActionBtnVLNTGB();
		addActionViewTableTGB();
		addActionMenuBar();
	}
	// bước 6 creat new 
	private void actionChooseType() {
		ChooseTypeTGB choosed = view.getChooseTypeTGB();
		choosed.getBtnNewButton_1().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				choosed.setVisible(false);
				choosed.getTableDataType1().cleanData();
				choosed.getTableDataType1().setVisible(true);

			}
		});
		choosed.getBtnNewButton_2().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				choosed.setVisible(false);
				choosed.getTableDataType2().cleanData();
				choosed.getTableDataType2().setVisible(true);

			}
		});
		choosed.getBtnNewButton_3().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				choosed.setVisible(false);
				choosed.getTableDataType3().cleanData();
				choosed.getTableDataType3().setVisible(true);

			}
		});

	}
	// Bước 7 của export
	private void actionButtonViewListFile() {
		view.getViewListFile().getBtnExport().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//
				try {

					//
					String selectName = (view.getViewListFile().getList().getSelectedValue());
					JFileChooser chooser = new JFileChooser();

					chooser.showSaveDialog(view);
					String filePath = chooser.getSelectedFile().toString();

					boolean isSuccess = model.export(filePath, selectName);
					if (isSuccess) {
						JOptionPane.showMessageDialog(view, "Export success");
					} else {
						JOptionPane.showMessageDialog(view, "Export err");
					}

					view.getViewListFile().setVisible(false);
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});
		view.getViewListFile().getBtnCancel().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				view.getViewListFile().setVisible(false);

			}
		});

	}

	// action menuItem export
	private void actionExport() {
		// muốn export ra file tbk
		view.getMiExport().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				view.getViewListFile().setVisible(true);
				try {
					view.getViewListFile().setValuesList(model.getListNameTGB());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});

	}
	// Bước 4 creat new 
	private void actionCreate() {
		view.getMiCreate().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				view.getChooseTypeTGB().setVisible(true);
			}
		});
	}

	// importFromTGB action
	// Bước 4 của import
	private void actionImportFromFileTGB() {

		view.getMiImportFromTGB().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooserFile = new JFileChooser();
				int open = chooserFile.showOpenDialog(view);
				File inputFile = chooserFile.getSelectedFile();
				System.out.println(inputFile.getName());
				if (open == JFileChooser.APPROVE_OPTION) {
//					try {
					try {
						importFromFileTGB(inputFile);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
	}
	// Bước 4 của import
	private void actionImportFromFileXLSX() {
		view.getMiImportFromXlsx().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooserFile = new JFileChooser();
				int open = chooserFile.showOpenDialog(view);
				File inputFile = chooserFile.getSelectedFile();
				if (open == JFileChooser.APPROVE_OPTION) {
//					try {
					try {
						importFromFileXlSX(inputFile);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}

		});

	}
	// Bước 7 của creat new
	private void actionDataType1() {
		TableTGBType1 tableDataType1 = view.getChooseTypeTGB().getTableDataType1();
		tableDataType1.getBtnSave().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				TGBType1 tgb = new TGBType1(tableDataType1.getTxtNameTgb().getText());
				TableModel d = tableDataType1.getTable().getModel();
				int columCount = d.getColumnCount();
				int rowCount = d.getRowCount();
				tgb.setDefaultDisplay(tableDataType1.isDefault());
				String lineData = ""; // dòng dữ liệu chuẩn bị cho add data
				for (int i = 0; i < columCount; i++) {
					lineData += i + 1 + "\t"; // ngày trong tuần bắt đầu từ ngày 1 : thứ 2
					for (int j = 0; j < rowCount; j++) {
						lineData += d.getValueAt(j, i) + "\t";
					}
					lineData += true; // table đầu tiên is Morrning = true
					tgb.addData(lineData); // add dòng data vào table
					System.out.println(lineData);
					lineData = ""; // clean line data

				}
				TableModel d1 = tableDataType1.getTable_1().getModel();
				int columCount1 = d1.getColumnCount();
				int rowCount1 = d1.getRowCount();
//				TGBType1 tgb1 = new TGBType1(view.getTableDataType1().getName());
				tgb.setDefaultDisplay(tableDataType1.isDefault());
				String lineData1 = ""; // dòng dữ liệu chuẩn bị cho add data
				for (int i = 0; i < columCount1; i++) {
					lineData1 += i + 1 + "\t"; // ngày trong tuần bắt đầu từ ngày 1 : thứ 2
					for (int j = 0; j < rowCount1; j++) {
						lineData1 += d1.getValueAt(j, i) + "\t";
					}
					lineData1 += false; // table1 đầu tiên is Morrning = false
					tgb.addData(lineData1); // add dòng data vào table1
					System.out.println(lineData1);
					lineData1 = ""; // clean line data

				}
				try {
					String mess = (tgb.loadToDB()) ? "create success" : " error, can't create";
					JOptionPane.showMessageDialog(view, mess);

				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(view, " error, can't create");
					e1.printStackTrace();
				}
				tableDataType1.setVisible(false);
			}
		});
	}
	// Bước 7 của creat new
	private void actionDataType2() {
		TableTGBType2 tableDataType2 = view.getChooseTypeTGB().getTableDataType2();
		tableDataType2.getBtnSave().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				TGBType2 tgb = new TGBType2(tableDataType2.getTxtNameTgb().getText());
				TableModel d = tableDataType2.getTable().getModel();
				int columCount = d.getColumnCount();
				int rowCount = d.getRowCount();
				tgb.setDefaultDisplay(tableDataType2.isDefault());
				String lineData = ""; // dòng dữ liệu chuẩn bị cho add data
				for (int i = 0; i < columCount; i++) {
					lineData += i + 1 + "\t"; // ngày trong tuần bắt đầu từ ngày 1 : thứ 2
					for (int j = 0; j < rowCount; j++) {
						lineData += d.getValueAt(j, i) + "\t";
					}
					lineData += true; // table đầu tiên is Morrning = true
					tgb.addData(lineData); // add dòng data vào table
					System.out.println(lineData);
					lineData = ""; // clean line data

				}
				TableModel d1 = tableDataType2.getTable_1().getModel();
				int columCount1 = d1.getColumnCount();
				int rowCount1 = d1.getRowCount();
//				TGBType1 tgb1 = new TGBType1(view.getTableDataType1().getName());
				tgb.setDefaultDisplay(tableDataType2.isDefault());
				String lineData1 = ""; // dòng dữ liệu chuẩn bị cho add data
				for (int i = 0; i < columCount1; i++) {
					lineData1 += i + 1 + "\t"; // ngày trong tuần bắt đầu từ ngày 1 : thứ 2
					for (int j = 0; j < rowCount1; j++) {
						lineData1 += d1.getValueAt(j, i) + "\t";
					}
					lineData1 += false; // table1 đầu tiên is Morrning = false
					tgb.addData(lineData1); // add dòng data vào table1
					System.out.println(lineData1);
					lineData1 = ""; // clean line data

				}
				try {
					String mess = (tgb.loadToDB()) ? "create success" : " error, can't create";
					JOptionPane.showMessageDialog(view, mess);

				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(view, " error, can't create");
					e1.printStackTrace();
				}
				tableDataType2.setVisible(false);
			}
		});
	}
	// Bước 7 của creat new
	private void actionDataType3() {
		TableTGBType3 tableDataType3 = view.getChooseTypeTGB().getTableDataType3();
		tableDataType3.getBtnSave().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				TGBType3 tgb = new TGBType3(tableDataType3.getTxtNameTgb().getText());
				List<JTable> listTable = tableDataType3.getListTable();
				int count = 0;
				String lineData = "";
				for (JTable table : listTable) {
//					System.out.println(" colum thứ " + count++);
					// tránh trường hợp cell đang bị edit thì không lấy được dữ liệu ( giống với vấn
					// về bên clean data)
					if (table.getCellEditor() != null) {
						table.getCellEditor().stopCellEditing();
					}
					for (int i = 0; i < table.getColumnCount(); i++) {
						System.out.println(" colum thứ " + count++);
						lineData += i + 1 + "\t";
						for (int j = 0; j < table.getRowCount(); j++) {
							lineData += table.getValueAt(j, i) + "\t";
							if (j % 2 != 0) {
//								System.out.println("line data: " + lineData); // chổ này là add vào tgb
								tgb.addData(lineData);
								lineData = i + 1 + "\t"; // next data in same colum
							}
						}
					}
				}

				try {
					String mess = (tgb.loadToDB()) ? "create success" : " error, can't create";
					JOptionPane.showMessageDialog(view, mess);

				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(view, " error, can't create");
					e1.printStackTrace();
				}
				tableDataType3.setVisible(false);
			}
		});
	}
	
//		******** Chuong ********************
	private void addActionMenuBar() {
//		click menubar openTGB chuyen sang giao dien hien danh sach name tgb duoi DB
		view.getMiOpen().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
//					lay list name duoi DB nho lop model goi phuong thuc getListNameTGB
//					Sau do dua vao tung hang trong table tren giao dien
					List<String> listNameTGB = model.getListNameTGB();
					for (int i = 0; i < listNameTGB.size(); i++) {
						// add row, số row sẽ tương đương với số dòng của table config
						view.getViewListNameTGB().getModel().addRow(new Object[0]);
						// set dữ liệu cho cột 0
						view.getViewListNameTGB().getModel().setValueAt(listNameTGB.get(i), i, 0);
						// set dữ liệu cho cột 1
						view.getViewListNameTGB().getModel().setValueAt(false, i, 1);
//					chuyen giao dien
						view.getViewListNameTGB().setVisible(true);
						view.closed();
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
	}
//		**********************************************
	
	
//	Phuong thuc xu li xu kien cac nut button trong ViewListNameTGB
	public void addActionBtnVLNTGB() {
//		button xoa
		view.getViewListNameTGB().getBtXoa().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
//					Khởi tạo mảng list chứa các dòng đã chọn 
					List<Integer> list = new ArrayList<Integer>();
// 					Duyệt các dòng của table, add vào mảng list những dòng có dấu check ở cột 1
					for (int i = 0; i < view.getViewListNameTGB().getTable().getRowCount(); i++) {
						boolean check = Boolean
								.valueOf(view.getViewListNameTGB().getTable().getValueAt(i, 1).toString());
						if (check) {
							list.add(i);
						}
					}

//					Duyệt mảng list để lấy ra tên tgb dựa vào vị trí của dòng check
					for (int i = 0; i < list.size(); i++) {
						String nameTGB = view.getViewListNameTGB().getModel().getValueAt(list.get(i), 0).toString();
//						Xóa dòng của table
						view.getViewListNameTGB().getModel().removeRow(list.get(i));
//						xóa tên va bang TGB dưới DB, goi lop remove ben model
						model.removeTKB(nameTGB);
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
		
//		Action btn quay lai trang main view
		view.getViewListNameTGB().getBtQuayLai().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Controller controll = new Controller(new MainView(), model);
					view.getViewListNameTGB().closed();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

//		action btn xem 1 table tgb
		view.getViewListNameTGB().getBtXem().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				List<Integer> listCheck = new ArrayList<Integer>(); //		list chua cac name table duoc chon
				for (int i = 0; i < view.getViewListNameTGB().getTable().getRowCount(); i++) {
//					duyet cac row cua table xem nhung nam nao duoc chon
					boolean check = Boolean.valueOf(view.getViewListNameTGB().getTable().getValueAt(i, 1).toString());
					if (check) {
						listCheck.add(i);
					}
				}
//				hien thong bao khong chon table nao ma chon xem
				if (listCheck.size() > 1) {
					JOptionPane.showMessageDialog(view.getViewListNameTGB(), "Chi duoc xem 1 table!");
				} else if (listCheck.size() < 1) {  	//		hien thong bao khong chon nhieu hon 1 table
					JOptionPane.showMessageDialog(view.getViewListNameTGB(),
							"Hay check vao o ben canh ten table muon xem!");
				} else if (listCheck.size() == 1) {		//	khi chon 1 name table se chuyen sang giao dien moi
//					lay ra ten table TGB vua chon
					nameTGB = view.getViewListNameTGB().getModel().getValueAt(listCheck.get(0), 0).toString();
					try {
						ResultSet rsTableTGB = model.getTableTGB(nameTGB); 		//	lay ra cac dong cua table duoi DB
						int rowSang = 0;
						int rowChieu = 4;
						int caSang = 1;
						int caChieu = 1;
						int rowType3 = 0;
						while (rsTableTGB.next()) { 	//	duyet tung dong cua table duoi DB
							if (rsTableTGB.getString("Type_TGB").equals("Type 1")) {	 //	So sanh voi dang 1
								if (rsTableTGB.getString("IsMorning").equals("false")) {	//	Kiem tra buoi sang hay chieu
//									set du lieu cho o tai row 4 column 0;
									view.getViewListNameTGB().getViewTableTGB().getModel().setValueAt("Ca " + caChieu,rowChieu, 0);
//									set du lieu cho o dau tien tai row 4 column 1;
									view.getViewListNameTGB().getViewTableTGB().getModel()
											.setValueAt(rsTableTGB.getString("NameSubject1"), rowChieu, 1);
//									set du lieu cho tai row 4 column 2;
									view.getViewListNameTGB().getViewTableTGB().getModel()
											.setValueAt(rsTableTGB.getString("NameSubject2"), rowChieu, 2);
									view.getViewListNameTGB().getViewTableTGB().getModel()
											.setValueAt(rsTableTGB.getString("NameSubject3"), rowChieu, 3);
									view.getViewListNameTGB().getViewTableTGB().getModel()
											.setValueAt(rsTableTGB.getString("NameSubject4"), rowChieu, 4);
									view.getViewListNameTGB().getViewTableTGB().getModel()
											.setValueAt(rsTableTGB.getString("NameSubject5"), rowChieu, 5);
									view.getViewListNameTGB().getViewTableTGB().getModel().setValueAt("", rowChieu,
											6);
									view.getViewListNameTGB().getViewTableTGB().getModel().setValueAt("", rowChieu,
											7);
//									rowChieu tang len 1 thanh row 5, cachieu thanh 2
									rowChieu++;
									caChieu++;
								} else { 		//	day la buoi sang, thi doi rowsang vaf caSang
									view.getViewListNameTGB().getViewTableTGB().getModel().setValueAt("Ca " + caSang,
											rowSang, 0);
									view.getViewListNameTGB().getViewTableTGB().getModel()
											.setValueAt(rsTableTGB.getString("NameSubject1"), rowSang, 1);
									view.getViewListNameTGB().getViewTableTGB().getModel()
											.setValueAt(rsTableTGB.getString("NameSubject2"), rowSang, 2);
									view.getViewListNameTGB().getViewTableTGB().getModel()
											.setValueAt(rsTableTGB.getString("NameSubject3"), rowSang, 3);
									view.getViewListNameTGB().getViewTableTGB().getModel()
											.setValueAt(rsTableTGB.getString("NameSubject4"), rowSang, 4);
									view.getViewListNameTGB().getViewTableTGB().getModel()
											.setValueAt(rsTableTGB.getString("NameSubject5"), rowSang, 5);
									view.getViewListNameTGB().getViewTableTGB().getModel().setValueAt("", rowSang,
											6);
									view.getViewListNameTGB().getViewTableTGB().getModel().setValueAt("", rowSang,
											7);
									rowSang++;
									caSang++;
								}
								
							} else if (rsTableTGB.getString("Type_TGB").equals("Type 2")) {  //	day la dang 2
								if (rsTableTGB.getString("IsMorning").equals("false")) {
									view.getViewListNameTGB().getViewTableTGB().getModel().setValueAt("Ca " + caChieu,
											rowChieu, 0);
									view.getViewListNameTGB().getViewTableTGB().getModel()
											.setValueAt(rsTableTGB.getString("NameSubject1") + " --> "
													+ rsTableTGB.getString("RoomSubject1"), rowChieu, 1);
									view.getViewListNameTGB().getViewTableTGB().getModel()
											.setValueAt(rsTableTGB.getString("NameSubject2") + " --> "
													+ rsTableTGB.getString("RoomSubject2"), rowChieu, 2);
									view.getViewListNameTGB().getViewTableTGB().getModel().setValueAt("", rowChieu,
											3);
									view.getViewListNameTGB().getViewTableTGB().getModel().setValueAt("", rowChieu,
											4);
									view.getViewListNameTGB().getViewTableTGB().getModel().setValueAt("", rowChieu,
											5);
									view.getViewListNameTGB().getViewTableTGB().getModel().setValueAt("", rowChieu,
											6);
									view.getViewListNameTGB().getViewTableTGB().getModel().setValueAt("", rowChieu,
											7);
									rowChieu++;
									caChieu++;
									
								} else { 		
									view.getViewListNameTGB().getViewTableTGB().getModel().setValueAt("Ca " + caSang,
											rowSang, 0);
									view.getViewListNameTGB().getViewTableTGB().getModel()
											.setValueAt(rsTableTGB.getString("NameSubject1") + " --> "
													+ rsTableTGB.getString("RoomSubject1"), rowSang, 1);
									view.getViewListNameTGB().getViewTableTGB().getModel()
											.setValueAt(rsTableTGB.getString("NameSubject2") + " --> "
													+ rsTableTGB.getString("RoomSubject2"), rowSang, 2);
									view.getViewListNameTGB().getViewTableTGB().getModel().setValueAt("", rowSang,
											3);
									view.getViewListNameTGB().getViewTableTGB().getModel().setValueAt("", rowSang,
											4);
									view.getViewListNameTGB().getViewTableTGB().getModel().setValueAt("", rowSang,
											5);
									view.getViewListNameTGB().getViewTableTGB().getModel().setValueAt("", rowSang,
											6);
									view.getViewListNameTGB().getViewTableTGB().getModel().setValueAt("", rowSang,
											7);
									rowSang++;
									caSang++;
								}
							} else if (rsTableTGB.getString("Type_TGB").equals("Type 3")) { 	 //	day la dang 3
								view.getViewListNameTGB().getViewTableTGB().getP()
										.remove(view.getViewListNameTGB().getViewTableTGB().getLblChieu());
								view.getViewListNameTGB().getViewTableTGB().getP()
										.remove(view.getViewListNameTGB().getViewTableTGB().getLblSang());
								view.getViewListNameTGB().getViewTableTGB().getModel()
										.setValueAt(rsTableTGB.getString("TimeSubject"), rowType3, 0);
								view.getViewListNameTGB().getViewTableTGB().getModel()
										.setValueAt(rsTableTGB.getString("NameSubject"), rowType3, 1);
								view.getViewListNameTGB().getViewTableTGB().getModel().setValueAt("", rowType3, 2);
								view.getViewListNameTGB().getViewTableTGB().getModel().setValueAt("", rowType3, 3);
								view.getViewListNameTGB().getViewTableTGB().getModel().setValueAt("", rowType3, 4);
								view.getViewListNameTGB().getViewTableTGB().getModel().setValueAt("", rowType3, 5);
								view.getViewListNameTGB().getViewTableTGB().getModel().setValueAt("", rowType3, 6);
								view.getViewListNameTGB().getViewTableTGB().getModel().setValueAt("", rowType3, 7);
								rowType3++;

							}

						}
						
//						ViewTableTGB hien, an ViewListNameTGB
						view.getViewListNameTGB().getViewTableTGB().setVisible(true);
						view.getViewListNameTGB().getViewTableTGB().setTitle(nameTGB);  //	set ten co JFrame ViewTableTGB
						view.getViewListNameTGB().closed();
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

				
			}

		});

	}
	
//	Phuong thuc action 2 nut button trong lop ViewTableTGB
	public void addActionViewTableTGB() {
//		button Update lai table tgb
		view.getViewListNameTGB().getViewTableTGB().getBtUpdate().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					PreparedStatement preEdit = model.editTKB(nameTGB); //	Phuong thuc chua cac cau query update cho 3 kieu tableTGB
//					duyet tung dong cua table
					for (int i = 0; i < view.getViewListNameTGB().getViewTableTGB().getTable().getRowCount(); i++) {
						if (nameTGB.contains("1")) { 	//		kiem tra trong nameTGB co so 1 la kieu 1
//							Vi mot so mau tao san cua nhom se quy dinh cu the ten tung loai table
//							Set lai du lieu cho cac o duoi DB
							preEdit.setString(1, view.getViewListNameTGB().getViewTableTGB().getModel().getValueAt(i, 0).toString());
							preEdit.setString(2, view.getViewListNameTGB().getViewTableTGB().getModel()
									.getValueAt(i, 1).toString());
							preEdit.setString(3, view.getViewListNameTGB().getViewTableTGB().getModel()
									.getValueAt(i, 2).toString());
							preEdit.setString(4, view.getViewListNameTGB().getViewTableTGB().getModel()
									.getValueAt(i, 3).toString());
							preEdit.setString(5, view.getViewListNameTGB().getViewTableTGB().getModel()
									.getValueAt(i, 4).toString());
							preEdit.setString(6, view.getViewListNameTGB().getViewTableTGB().getModel()
									.getValueAt(i, 5).toString());
							if (i > 3) { 	//	Cho nay check xem buoi sang hay chieu, buoi sang 4 row danh tu o den 3
								preEdit.setString(7, "false");
							} else {
								preEdit.setString(7, "true");
							}
						} else if (nameTGB.contains("2")) {  //		kieu table thu 2
							preEdit.setString(1, view.getViewListNameTGB().getViewTableTGB().getTable()
									.getValueAt(i, 0).toString());
//				Vi kieu 2 co ca mon hoc va phong hoc nen tao mang de cat ra va dua xuong DB
							String[] slipThu2 = view.getViewListNameTGB().getViewTableTGB().getTable()
									.getValueAt(i, 1).toString().split("-->");
							
							preEdit.setString(2, slipThu2[0]);
							preEdit.setString(3, slipThu2[1]);
							
							String[] slipThu3 = view.getViewListNameTGB().getViewTableTGB().getTable()
									.getValueAt(i, 2).toString().split("-->");
							
							preEdit.setString(4, slipThu3[0]);
							preEdit.setString(5, slipThu3[1]);
							
							if (i > 3) {  //	Tuong tu check sang hay chieu
								preEdit.setString(6, "false");
							} else {
								preEdit.setString(6, "true");
							}
							
						} else if (nameTGB.contains("3")) {  //		kieu TGB thu 3
							preEdit.setString(1, String.valueOf(i + 1)); //	 Duoi DB la kieu varchar nen phai chuyen index i ve String
							preEdit.setString(2, view.getViewListNameTGB().getViewTableTGB().getTable()
									.getValueAt(i, 1).toString());
							preEdit.setString(3, view.getViewListNameTGB().getViewTableTGB().getTable()
									.getValueAt(i, 0).toString());
						}
					}
					preEdit.executeUpdate();
					JOptionPane.showMessageDialog(view.getViewListNameTGB().getViewTableTGB(),
							"Update thanh cong!");
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
//		Action cua btn quay lai 
		view.getViewListNameTGB().getViewTableTGB().getBtQuayLai().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
//			Minh cho no quay lai main view luon vi neu nhu co update se cho no load lai luon
					new Controller(new MainView(), model);
					view.getViewListNameTGB().getViewTableTGB().closed();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
	}

}
