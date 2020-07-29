package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

public class ViewListFile extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	DefaultListModel<String> defListModel;
	JList<String> jList;
	JPanel panel_1;
	private JButton btnExport;
	private JButton btnCancel;
	public void setBtnExport(JButton btnExport) {
		this.btnExport = btnExport;
	}

	public void setBtnCancel(JButton btnCancel) {
		this.btnCancel = btnCancel;
	}


	public ViewListFile() {
		setLayout(new BorderLayout());
		setTitle("List File");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(300, 150));
		pack();
		setVisible(false);
		setLocationRelativeTo(null);
//		setResizable(false);
		//
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		addScrollList();
		addButtons();
	}

	private void addButtons() {
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		btnExport = new JButton("Export");
		btnCancel = new JButton("Cancel");
		panel.add(btnExport);

		panel.add(btnCancel);
	}

	private void addScrollList() {
		// add Scrollpane
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		panel_1 = new JPanel();
		panel_1.setBackground(Color.DARK_GRAY);
//		panel_1.set
		scrollPane.setViewportView(panel_1);
		// add jlist
		defListModel = new DefaultListModel<String>();
		jList = new JList<String>(defListModel);
		jList.setBackground(Color.DARK_GRAY);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		panel_1.add(jList);
	}

	public void setValuesList(List<String> list) {
		defListModel.clear();
		for (String st : list) {
			defListModel.addElement(st);
		}
	}

//	public void show() {
//		setVisible(true);
//	}

	public JList<String> getList() {
		return jList;
	}

	public JButton getBtnExport() {
		return btnExport;
	}

	public JButton getBtnCancel() {
		return btnCancel;
	}


	public static void main(String[] args) {
		List<String> listS = new LinkedList<String>();
		listS.add("name1");
		listS.add("name2");
		listS.add("name3");
		listS.add("name4");
		listS.add("name5");
		listS.add("name6");
		listS.add("name7");
		//
		List<String> listS2 = new LinkedList<String>();
		listS2.add("name11");
		listS2.add("name12");
		listS2.add("name13");
		listS2.add("name14");
		listS2.add("name15");
		listS2.add("name16");
		listS2.add("name17");
		ViewListFile frame = new ViewListFile();
		frame.setValuesList(listS);
		frame.setValuesList(listS2);

	}
//		String[] aList = (String[]) listS.toArray();
//		System.out.println(aList);
//		DefaultListModel<String> defListModel = new DefaultListModel<String>();
//		defListModel.add
}