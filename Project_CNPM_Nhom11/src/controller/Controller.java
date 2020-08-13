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
	String nameTGB, type;
	public Controller(MainView view, Model model) {
		super();
		this.view = view;
		this.model = model;
		addAction();
	}
	/*
	 * Thực hiện gọi Phuowng thức import from file tgb của model, quăng thông báo 
	 */
	private void importFromFileTGB(File file) throws Exception {
		String mess = (model.importFromFiletgb(file)) ? "import success" : " error, can't import";
		JOptionPane.showMessageDialog(view, mess);

	}

	/*
	 * Thực hiện gọi Phuowng thức import from file xls của model, quăng thông báo 
	 */
	private void importFromFileXlS(File inputFile) throws Exception {
		String mess = (model.importFromFilexlsx(inputFile)) ? "import success" : " error, can't import";
		JOptionPane.showMessageDialog(view, mess);

	}

	private void addAction() {

		actionImportFromFileTGB();
		actionImportFromFileXLS();
		actionButtonViewListFile();
		actionExport();
		actionCreate();
		actionDataType1();
		actionDataType2();
		actionDataType3();
		actionChooseType();
		addActionMenuBar();
		addActionBtnVLNTGB();
		addActionViewTableTGB();
	}
	// add hành động của choose type tgb
	// Bước 5 của create new.hiển thị mẫu tương ứng 
	private void actionChooseType() {
		ChooseTypeTGB choosed = view.getChooseTypeTGB();
		choosed.getBtnNewButton_1().addActionListener(new ActionListener() {
			// hiển thị mẫu tgb 1
			@Override
			public void actionPerformed(ActionEvent e) {
				choosed.setVisible(false);
				choosed.getTableDataType1().cleanData();
				choosed.getTableDataType1().setVisible(true);

			}
		});
		choosed.getBtnNewButton_2().addActionListener(new ActionListener() {
			// hiển thị mẫu tgb 2
			@Override
			public void actionPerformed(ActionEvent e) {
				choosed.setVisible(false);
				choosed.getTableDataType2().cleanData();
				choosed.getTableDataType2().setVisible(true);

			}
		});
		choosed.getBtnNewButton_3().addActionListener(new ActionListener() {
			// hiển thị mẫu tgb 3
			@Override
			public void actionPerformed(ActionEvent e) {
				choosed.setVisible(false);
				choosed.getTableDataType3().cleanData();
				choosed.getTableDataType3().setVisible(true);

			}
		});

	}
	//  Bước 4.1 export , add hành động cho các button của view list file
	private void actionButtonViewListFile() {
//		view.getViewListFile().setVisible(false);
		view.getViewListFile().getBtnExport().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					// lấy tên tgb được chọn
					String nameTGB = (view.getViewListFile().getList().getSelectedValue());
					// Bước 5 gọi và show JChosserFile
					JFileChooser chooser = new JFileChooser();
					chooser.showSaveDialog(view);
					String filePath = chooser.getSelectedFile().toString();
					// Thực hiện export
					boolean isSuccess = model.export(filePath, nameTGB);
					if (isSuccess) {
						JOptionPane.showMessageDialog(view, "Export success");
					} else {
						JOptionPane.showMessageDialog(view, "Export err");
					}

					view.getViewListFile().setVisible(false);
				} catch (Exception e1) {
					e1.printStackTrace();
					view.getViewListFile().setVisible(false);
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

	// Bước 3 export hiển thị bảng view list file 
	private void actionExport() {
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
	// Bước 3 của import - hiển thị JFileChooser, 
	private void actionImportFromFileTGB() {

		view.getMiImportFromTGB().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooserFile = new JFileChooser();
				chooserFile.setApproveButtonText("Import");
				int open = chooserFile.showOpenDialog(view);
				File inputFile = chooserFile.getSelectedFile();
				if (open == JFileChooser.APPROVE_OPTION) {
					try {
						// Bước 5 của import, tiến hành import
						importFromFileTGB(inputFile);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
	}
	// Bước 4 của import
	private void actionImportFromFileXLS() {
		view.getMiImportFromXlsx().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooserFile = new JFileChooser();
				chooserFile.setApproveButtonText("Import");
				int open = chooserFile.showOpenDialog(view);
				File inputFile = chooserFile.getSelectedFile();
				if (open == JFileChooser.APPROVE_OPTION) {
					try {
						importFromFileXlS(inputFile);
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
				/*
				 * Table đầu tiên của type là buổi sáng, cần phải chuyển về dạng thích hợp để truyền vào Phương thức addData của tgb
				 */
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
				/*
				 * Table thứ 2 là buổi chiều, cũng chuyển đổi lại để add vào tgb bằng phương thức addData
				 */
				TableModel d1 = tableDataType1.getTable_1().getModel();
				int columCount1 = d1.getColumnCount();
				int rowCount1 = d1.getRowCount();

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
				// 7.1 Load tgb vào db, thả thông báo
				String mess = (tgb.loadToDB()) ? "create success" : " error, can't create";
				JOptionPane.showMessageDialog(view, mess);
				// tắt bảng tgb mẫu( Model Type 1)
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
				String mess = (tgb.loadToDB()) ? "create success" : " error, can't create";
				JOptionPane.showMessageDialog(view, mess);
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
				// 7.1 Load to db
				String mess = (tgb.loadToDB()) ? "create success" : " error, can't create";
				// Bước 8 của create : xuất thông báo
				JOptionPane.showMessageDialog(view, mess);
				tableDataType3.setVisible(false);
			}
		});
	}
	
	
//		******** Chuong ********************
	private void addActionMenuBar() {
//		click openTGB chuyen sang giao dien hien danh sach name tgb duoi DB
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
						while (rsTableTGB.next()) { 	//	duyet tung dong cua table duoi DB
							if (rsTableTGB.getString("Type_TGB").equals("Type 1")) {	 //	So sanh voi dang 1
								type = rsTableTGB.getString("Type_TGB");
								if (rsTableTGB.getString("IsMorning").equals("false")) {	//	Kiem tra buoi sang hay chieu
									String caHoc = "Ca " + caChieu;
									String thu2 = rsTableTGB.getString("NameSubject1");
									String thu3 = rsTableTGB.getString("NameSubject2");
									String thu4 = rsTableTGB.getString("NameSubject3");
									String thu5 = rsTableTGB.getString("NameSubject4");
									String thu6 = rsTableTGB.getString("NameSubject5");
									String thu7 = " ";
									String cn = " ";
									view.getViewListNameTGB().getViewTableTGB().setValueRowTable(rowChieu, caHoc, thu2, thu3, thu4, thu5, thu6, thu7, cn);
									rowChieu++;
									caChieu++;
								} else { 		//	day la buoi sang, thi doi rowsang vaf caSang
									String caHoc = "Ca " + caSang;
									String thu2 = rsTableTGB.getString("NameSubject1");
									String thu3 = rsTableTGB.getString("NameSubject2");
									String thu4 = rsTableTGB.getString("NameSubject3");
									String thu5 = rsTableTGB.getString("NameSubject4");
									String thu6 = rsTableTGB.getString("NameSubject5");
									String thu7 = " ";
									String cn = " ";
									view.getViewListNameTGB().getViewTableTGB().setValueRowTable(rowSang, caHoc, thu2, thu3, thu4, thu5, thu6, thu7, cn);
									rowSang++;
									caSang++;
								}
								
							} else if (rsTableTGB.getString("Type_TGB").equals("Type 2")) {  //	day la dang 2
								type = rsTableTGB.getString("Type_TGB");
								if (rsTableTGB.getString("IsMorning").equals("false")) {
									String caHoc = "Ca " + caChieu;
									String thu2 = rsTableTGB.getString("NameSubject1") + " - " + " PH: " + rsTableTGB.getString("RoomSubject1");
									String thu3 = rsTableTGB.getString("NameSubject2") +  " - " + " PH: " + rsTableTGB.getString("RoomSubject2");
									String thu4 = rsTableTGB.getString("NameSubject3") +  " - " + " PH: " + rsTableTGB.getString("RoomSubject3");
									String thu5 = rsTableTGB.getString("NameSubject4") +  " - " + " PH: " + rsTableTGB.getString("RoomSubject4");
									String thu6 = rsTableTGB.getString("NameSubject5") +  " - " + " PH: " + rsTableTGB.getString("RoomSubject5");
									String thu7 = rsTableTGB.getString("NameSubject6") +  " - " + " PH: " + rsTableTGB.getString("RoomSubject6");
									String cn = rsTableTGB.getString("NameSubject7") +  " - " + " PH: " + rsTableTGB.getString("RoomSubject7");
									view.getViewListNameTGB().getViewTableTGB().setValueRowTable(rowChieu, caHoc, thu2, thu3, thu4, thu5, thu6, thu7, cn);
									rowChieu++;
									caChieu++;
									
								} else { 		
									String caHoc = "Ca " + caSang;
									String thu2 = rsTableTGB.getString("NameSubject1") +  " - " + " PH: " + rsTableTGB.getString("RoomSubject1");
									String thu3 = rsTableTGB.getString("NameSubject2") +  " - " + " PH: " + rsTableTGB.getString("RoomSubject2");
									String thu4 = rsTableTGB.getString("NameSubject3") +  " - " + " PH: " + rsTableTGB.getString("RoomSubject3");
									String thu5 = rsTableTGB.getString("NameSubject4") +  " - " + " PH: " + rsTableTGB.getString("RoomSubject4");
									String thu6 = rsTableTGB.getString("NameSubject5") +  " - " + " PH: " + rsTableTGB.getString("RoomSubject5");
									String thu7 = rsTableTGB.getString("NameSubject6") +  " - " + " PH: " + rsTableTGB.getString("RoomSubject6");
									String cn = rsTableTGB.getString("NameSubject7") +  " - " + " PH: " + rsTableTGB.getString("RoomSubject7");
									view.getViewListNameTGB().getViewTableTGB().setValueRowTable(rowSang, caHoc, thu2, thu3, thu4, thu5, thu6, thu7, cn);
									rowSang++;
									caSang++;
								}
							} else if (rsTableTGB.getString("Type_TGB").equals("Type 3")) { 	 //	day la dang 3
								type = rsTableTGB.getString("Type_TGB");
								String thu2 = "";
								String thu3 = "";
								String thu4 = "";
								String thu5 = "";
								String thu6 = "";
								String thu7 = "";
								String cn = "";
								String caHoc = String.valueOf(caSang);
								String ngay = rsTableTGB.getString("DayOfWeek");
								if(ngay.equals("1")) {
								thu2 = rsTableTGB.getString("NameSubject") +  " - " + " TG: " + rsTableTGB.getString("TimeSubject");
								}else if (ngay.equals("2")) {
								thu3 = rsTableTGB.getString("NameSubject") +  " - " + " TG: " + rsTableTGB.getString("TimeSubject");
								}else if (ngay.equals("3")) {
								thu4 = rsTableTGB.getString("NameSubject") +  " - " + " TG: " + rsTableTGB.getString("TimeSubject");
								}else if (ngay.equals("4")) {
								thu5 = rsTableTGB.getString("NameSubject") +  " - " + " TG: " + rsTableTGB.getString("TimeSubject");
								}else if (ngay.equals("5")) {
								thu6 = rsTableTGB.getString("NameSubject") +  " - " + " TG: " + rsTableTGB.getString("TimeSubject");
								}else if (ngay.equals("6")) {
								thu7 = rsTableTGB.getString("NameSubject") +  " - " + " TG: " + rsTableTGB.getString("TimeSubject");
								}else if (ngay.equals("7")) {
								cn = rsTableTGB.getString("NameSubject") +  " - " + " TG: " + rsTableTGB.getString("TimeSubject");
								}
								view.getViewListNameTGB().getViewTableTGB().setValueRowTable(rowSang, caHoc, thu2, thu3, thu4, thu5, thu6, thu7, cn);
								caSang++;
								rowSang++;

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
					PreparedStatement preEdit = model.editTKB(type, nameTGB); //	Phuong thuc chua cac cau query update cho 3 kieu tableTGB
//					duyet tung dong cua table
					for (int i = 0; i < view.getViewListNameTGB().getViewTableTGB().getTable().getRowCount(); i++) {
						if (type.equals("Type 1")) { 	//		kiem tra trong nameTGB co so 1 la kieu 1
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
						} else if (type.equals("Type 2")) {  //		kieu table thu 2
							preEdit.setString(1, view.getViewListNameTGB().getViewTableTGB().getTable()
									.getValueAt(i, 0).toString());
//				Vi kieu 2 co ca mon hoc va phong hoc nen tao mang de cat ra va dua xuong DB
							String[] slipThu2 = view.getViewListNameTGB().getViewTableTGB().getTable()
									.getValueAt(i, 1).toString().split("-");
							
							preEdit.setString(2, slipThu2[0]);
							preEdit.setString(3, slipThu2[1]);
							
							String[] slipThu3 = view.getViewListNameTGB().getViewTableTGB().getTable()
									.getValueAt(i, 2).toString().split("-");
							
							preEdit.setString(4, slipThu3[0]);
							preEdit.setString(5, slipThu3[1]);
							String[] slipThu4 = view.getViewListNameTGB().getViewTableTGB().getTable()
									.getValueAt(i, 3).toString().split("-");
							
							preEdit.setString(6, slipThu4[0]);
							preEdit.setString(7, slipThu4[1]);
							String[] slipThu5 = view.getViewListNameTGB().getViewTableTGB().getTable()
									.getValueAt(i, 4).toString().split("-");
							
							preEdit.setString(8, slipThu5[0]);
							preEdit.setString(9, slipThu5[1]);
							String[] slipThu6 = view.getViewListNameTGB().getViewTableTGB().getTable()
									.getValueAt(i, 5).toString().split("-");
							
							preEdit.setString(10, slipThu6[0]);
							preEdit.setString(11, slipThu6[1]);
							String[] slipThu7 = view.getViewListNameTGB().getViewTableTGB().getTable()
									.getValueAt(i, 6).toString().split("-");
							
							preEdit.setString(12, slipThu7[0]);
							preEdit.setString(13, slipThu7[1]);
							String[] slipCN = view.getViewListNameTGB().getViewTableTGB().getTable()
									.getValueAt(i, 7).toString().split("-");
							
							preEdit.setString(14, slipCN[0]);
							preEdit.setString(15, slipCN[1]);
							
							if (i > 3) {  //	Tuong tu check sang hay chieu
								preEdit.setString(16, "false");
							} else {
								preEdit.setString(16, "true");
							}
							
						} else if (type.equals("Type 3")) {  //		kieu TGB thu 3
							preEdit.setString(1, view.getViewListNameTGB().getViewTableTGB().getTable()
									.getValueAt(i, 0).toString());
//				Vi kieu 2 co ca mon hoc va phong hoc nen tao mang de cat ra va dua xuong DB
							String[] slipThu2 = view.getViewListNameTGB().getViewTableTGB().getTable()
									.getValueAt(i, 1).toString().split("-");
							
							preEdit.setString(2, slipThu2[0]);
							preEdit.setString(3, slipThu2[1]);
							
							String[] slipThu3 = view.getViewListNameTGB().getViewTableTGB().getTable()
									.getValueAt(i, 2).toString().split("-");
							
							preEdit.setString(4, slipThu3[0]);
							preEdit.setString(5, slipThu3[1]);
							String[] slipThu4 = view.getViewListNameTGB().getViewTableTGB().getTable()
									.getValueAt(i, 3).toString().split("-");
							
							preEdit.setString(6, slipThu4[0]);
							preEdit.setString(7, slipThu4[1]);
							String[] slipThu5 = view.getViewListNameTGB().getViewTableTGB().getTable()
									.getValueAt(i, 4).toString().split("-");
							
							preEdit.setString(8, slipThu5[0]);
							preEdit.setString(9, slipThu5[1]);
							String[] slipThu6 = view.getViewListNameTGB().getViewTableTGB().getTable()
									.getValueAt(i, 5).toString().split("-");
							
							preEdit.setString(10, slipThu6[0]);
							preEdit.setString(11, slipThu6[1]);
							String[] slipThu7 = view.getViewListNameTGB().getViewTableTGB().getTable()
									.getValueAt(i, 6).toString().split("-");
							
							preEdit.setString(12, slipThu7[0]);
							preEdit.setString(13, slipThu7[1]);
							String[] slipCN = view.getViewListNameTGB().getViewTableTGB().getTable()
									.getValueAt(i, 7).toString().split("-");
							
							preEdit.setString(14, slipCN[0]);
							preEdit.setString(15, slipCN[1]);
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
