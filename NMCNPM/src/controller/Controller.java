package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

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
		// importFromTGB action
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
