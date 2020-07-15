package test;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Color;

public class ViewListFileauto extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	DefaultListModel<String> defListModel;
	JList<String> jList;
	JPanel panel_1;
	private final JButton btnExport = new JButton("Export");
	private final JButton btnCancel = new JButton("Cancel");

	/**
	 * Create the frame.
	 */
	public ViewListFileauto() {
		setBackground(Color.DARK_GRAY);
//		setTitle("ListFile");
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 450, 300);
//		setVisible(true);
//		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		contentPane.setLayout(new BorderLayout(0, 0));
//		setContentPane(contentPane);
		getContentPane().setLayout(new BorderLayout());
		setTitle("List File");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(500, 300));
		pack();
		setLocationRelativeTo(null);
		//
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		addScrollList();
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

		panel.add(btnExport);

		panel.add(btnCancel);
	}

	private void addScrollList() {
		// add Scrollpane
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		panel_1 = new JPanel();
		panel_1.setBackground(Color.DARK_GRAY);
		scrollPane.setViewportView(panel_1);
		// add jlist
		defListModel = new DefaultListModel<String>();
		jList = new JList<String>();
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		panel_1.add(jList);
	}

//	public void show() {
//	setVisible(true);
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

	public void showJChooser(List<String> listName) {

	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		List<String> listS = new LinkedList<String>();
		listS.add("name1");
		listS.add("name2");
		listS.add("name3");
		listS.add("name4");
		listS.add("name5");
		listS.add("name6");
		listS.add("name7");
		listS.add("name8");
		listS.add("name9");
		listS.add("name10");
		listS.add("name1");
		listS.add("name2");
		listS.add("name3");
		listS.add("name4");
		listS.add("name5");
		listS.add("name6");
		listS.add("name7");
		listS.add("name8");
		listS.add("name9");
		listS.add("name10");
		listS.add("name1");
		listS.add("name2");
		listS.add("name3");
		listS.add("name4");
		listS.add("name5");
		listS.add("name6");
		listS.add("name7");
		listS.add("name8");
		listS.add("name9");
		listS.add("name10");
		listS.add("name1");
		listS.add("name2");
		listS.add("name3");
		listS.add("name4");
		listS.add("name5");
		listS.add("name6");
		listS.add("name7");
		listS.add("name8");
		listS.add("name9");
		listS.add("name10");
		ViewListFileauto frame = new ViewListFileauto();
//		String[] aList = (String[]) listS.toArray();
//		System.out.println(aList);
//		DefaultListModel<String> defListModel = new DefaultListModel<String>();
//		defListModel.add
	}

}
