package view;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MainView extends JFrame {
	JMenu mFile, mOpen, mModify, mSetting;
	JMenuItem miImportFromTGB, miImportFromXlsx, miExport;
	ViewListFile viewListFile;

	public MainView() {
		gui();
		createMenu();
	}

	private void gui() {
		setTitle("Main View");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(800, 500));
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		viewListFile = new ViewListFile();

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
		miImportFromTGB = new JMenuItem("ImportFromTGB");
		miImportFromXlsx = new JMenuItem("Iport From XLSX");
		miExport = new JMenuItem("Export");
		// add menu item, menu
		mFile.add(miImportFromTGB);
		mFile.add(miImportFromXlsx);
		mFile.add(miExport);

		menuBar.add(mFile);
		menuBar.add(mOpen);
		menuBar.add(mModify);
		menuBar.add(mSetting);
		// add menu bar
		this.setJMenuBar(menuBar);

	}

	public static void main(String[] args) {
		MainView mv = new MainView();
	}
}
