package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MainView extends JFrame {
	JMenu mFile, mOpen, mModify, mSetting;
	JMenuItem miCreate, miImportFromTGB, miImportFromXlsx, miExport, miOpen;
	ViewListFile viewListFile;
	ChooseTypeTGB chooseTypeTGB;


	public MainView() {
		viewListFile = new ViewListFile();
		chooseTypeTGB = new ChooseTypeTGB();

		gui();
		createMenu();
	}

	public ChooseTypeTGB getChooseTypeTGB() {
		return chooseTypeTGB;
	}

//	public void setTableDataType3(TableTGBType3 tableDataType3) {
//		this.tableDataType3 = tableDataType3;
//	}
//
//	public TableTGBType3 getTableDataType3() {
//		return tableDataType3;
//	}
//
//	public TableTGBType2 getTableDataType2() {
//		return tableDataType2;
//	}
//
//	public TableTGBType1 getTableDataType1() {
//		return tableDataType1;
//	}

	private void gui() {
		setTitle("Main View");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(800, 500));
		pack();
		setLocationRelativeTo(null);
		setVisible(true);

	}

	public ViewListFile getViewListFile() {
		return viewListFile;
	}

	public JMenu getmFile() {
		return mFile;
	}

	public JMenu getmOpen() {
		return mOpen;
	}

	public JMenu getmModify() {
		return mModify;
	}

	public JMenu getmSetting() {
		return mSetting;
	}

	public JMenuItem getMiImportFromTGB() {
		return miImportFromTGB;
	}

	public JMenuItem getMiImportFromXlsx() {
		return miImportFromXlsx;
	}

	public JMenuItem getMiExport() {
		return miExport;
	}

	private void createMenu() {
		JMenuBar menuBar = new JMenuBar();
		// create menu
		mFile = new JMenu("File");
		mOpen = new JMenu("Open");
		mModify = new JMenu("Modify");
		mSetting = new JMenu("Setting");
		// create menuItem
		miCreate = new JMenuItem("Create New");
		miImportFromTGB = new JMenuItem("ImportFromTGB");
		miImportFromXlsx = new JMenuItem("Iport From XLSX");
		miExport = new JMenuItem("Export");
		
		miOpen = new JMenuItem("Open list TGB");
		miOpen.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					new ViewListNameTGB();
					closed();
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
		// add menu item, menu
		mFile.add(miCreate);
		mFile.add(miImportFromTGB);
		mFile.add(miImportFromXlsx);
		mFile.add(miExport);
		
		mOpen.add(miOpen);

		menuBar.add(mFile);
		menuBar.add(mOpen);
		menuBar.add(mModify);
		menuBar.add(mSetting);
		// add menu bar
		this.setJMenuBar(menuBar);

	}

	public JMenuItem getMiCreate() {
		return miCreate;
	}
	
	public void closed() {
		dispose();
	}

//	public static void main(String[] args) {
//		MainView mv = new MainView();
//	}
}
