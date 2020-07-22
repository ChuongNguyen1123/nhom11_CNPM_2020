package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
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

	public Controller(MainView view, Model model) {
		super();
		this.view = view;
		this.model = model;
		addAction();
	}

	private void importTGB(File file) throws Exception {
//		boolean isSuccess = model.importFromFiletgb(file);
		String mess = (model.importFromFiletgb(file)) ? "import success" : " error, can't import";
		JOptionPane.showMessageDialog(view, mess);

	}

	private void addAction() {

		importFromTGB();
		buttonViewListFileAction();
		export();
		addActionMenuBar();
		addActionDataType1();
		addActionDataType2();
		addActionDataType3();
		addActionChooseType();
	}

	private void addActionChooseType() {
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

	private void buttonViewListFileAction() {
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
	private void export() {
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

	private void addActionMenuBar() {
		view.getMiCreate().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				view.getChooseTypeTGB().setVisible(true);
			}
		});
	}

	// importFromTGB action
	private void importFromTGB() {

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
						importTGB(inputFile);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
	}

	private void addActionDataType1() {
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

	private void addActionDataType2() {
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

	private void addActionDataType3() {
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

}
