package test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

public class ViewListFile0 extends JFrame {
// List<String>  listNameTGB ;
	JScrollPane scrollPane;
	JPanel panelRB;
	JPanel panelOption;
	List<JRadioButton> radioBTs;
	JButton nextBt, cancelBt;
	JFileChooser chooser;

	public ViewListFile0() {
		panelRB = new JPanel();
		panelOption = new JPanel();
		scrollPane = new JScrollPane();
		radioBTs = new LinkedList<JRadioButton>();
		chooser = new JFileChooser();
		nextBt = new JButton("Next");
		cancelBt = new JButton("Cancel");

		setLayout(new BorderLayout());
		setTitle("List File");
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setPreferredSize(new Dimension(500, 300));
		pack();
		setLocationRelativeTo(null);

	}

	public void genrateListName(List<String> listNameTGB) {
		panelRB.setLayout(new BoxLayout(panelRB, BoxLayout.Y_AXIS));
		panelRB.setPreferredSize(new Dimension(500, 200));
		ButtonGroup bgroup = new ButtonGroup();
		// tạo radio button tương ứng với name tgb, nhét chung 1 group
		for (int i = 0; i < listNameTGB.size(); i++) {
			JRadioButton radioBT = new JRadioButton(listNameTGB.get(i));
			radioBTs.add(radioBT);
			bgroup.add(radioBT);
		}
		// add các radiobutton đó vào scrollpane
		for (JRadioButton rb : radioBTs) {
			panelRB.add(rb);
		}
		scrollPane.setViewportView(panelRB);
		add(scrollPane, BorderLayout.CENTER);
		// add optionPanel
		panelOption.add(nextBt);
		panelOption.add(cancelBt);
		add(scrollPane, BorderLayout.NORTH);

//		JPanel t = new JPanel();
//		t.setPreferredSize(new Dimension(400, 750));
//		t.setBackground(Color.GREEN);
//		scrollPane = new JScrollPane(panel1);
//		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
//		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//		panel2.add(scrollPane);
//		JFileChooser fileDialog = new JFileChooser();
//		JButton showFileDialogButton = new JButton("Open File");
//		showFileDialogButton.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				int returnVal = fileDialog.showSaveDialog(panel2);
//				if (returnVal == JFileChooser.APPROVE_OPTION) {
////		               java.io.File file = fileDialog.getSelectedFile();
//					int a = fileDialog.showSaveDialog(panel2);
////		                java.io.File fileToSave = fileDialog.getSelectedFile();
//					setVisible();
//				}
//			}
//		});
//		panel2.add(showFileDialogButton);
//		add(panel2);
	}

	void setVisible() {
		setVisible(false);
	}

	public static void main(String[] args) {
		ViewListFile0 vlf = new ViewListFile0();
		List<String> list = new LinkedList<String>();
		list.add("name1");
		list.add("name2");
		list.add("name3");
		list.add("name4");
		list.add("name5");
		list.add("name6");
		list.add("name7");
		list.add("name8");
		list.add("name9");
		list.add("name10");
		list.add("name1");
		list.add("name2");
		list.add("name3");
		list.add("name4");
		list.add("name5");
		list.add("name6");
		list.add("name7");
		list.add("name8");
		list.add("name9");
		list.add("name10");
		list.add("name1");
		list.add("name2");
		list.add("name3");
		list.add("name4");
		list.add("name5");
		list.add("name6");
		list.add("name7");
		list.add("name8");
		list.add("name9");
		list.add("name10");
		list.add("name1");
		list.add("name2");
		list.add("name3");
		list.add("name4");
		list.add("name5");
		list.add("name6");
		list.add("name7");
		list.add("name8");
		list.add("name9");
		list.add("name10");
		vlf.genrateListName(list);
	}
}
