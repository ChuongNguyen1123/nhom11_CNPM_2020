package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Model;
import view.MainView;

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
		actionViewListFile();
		export();
	}

	private void buttonViewListFileAction() {
		view.getViewListFile().getBtnExport().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//
				try {
//					view.getViewListFile().setValuesList(model.getListName());

					//
					String selectName = (view.getViewListFile().getList().getSelectedValue());
					JFileChooser chooser = new JFileChooser();

					chooser.showSaveDialog(view);
					String filePath = chooser.getSelectedFile().toString();
//					System.out.println(filePath);
					//

					model.export(filePath, selectName);
					view.getViewListFile().setVisible(false);
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});
		view.getViewListFile().getBtnCancel().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
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
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

	}

	public void actionViewListFile() {
		view.getViewListFile().getList().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
//				System.out.println(view.getViewListFile().getList().getSelectedValue());

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
					try {
						importTGB(inputFile);
					} catch (Exception e1) {
						System.out.println(" có lỗi" + e.toString());
					}
				} else {
					System.out.println("bạn nhấn cancle");
				}
			}
		});
	}
}
