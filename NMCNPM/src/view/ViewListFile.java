package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

public class ViewListFile extends JFrame {
// List<String>  listNameTGB ;
	JScrollPane scrollPane ;
	JPanel panel1;
	JPanel panel2;
	List<JLabel> names ;
	List<JRadioButton> radioBTs;
	JFileChooser chooser;
	

	public ViewListFile() {
		panel1 = new JPanel();
		panel2 = new JPanel();
		scrollPane = new JScrollPane();
		names = new LinkedList<JLabel>();
		radioBTs = new LinkedList<JRadioButton>();
		chooser = new JFileChooser();
		
		setTitle("List File");
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setPreferredSize(new Dimension(500, 300));
		pack();
		setLocationRelativeTo(null);
		
	}
	public void genrateListName(List<String> listNameTKB ) {
	panel1.setLayout(new GridLayout());
//	panel.setLayout(n);
//		int size = listNameTKB.size();
		ButtonGroup bg = new ButtonGroup();
		for (int i = 0; i < listNameTKB.size(); i++) {
			
			JPanel sup =new JPanel(new FlowLayout());
			JLabel name = new JLabel(listNameTKB.get(i));
			JRadioButton radioBT = new JRadioButton();
			bg.add(radioBT);;
			sup.add(name);
			sup.add(radioBT);
			
			panel1.add(sup);
		}
//		scrollPane.add(panel1);
		JPanel t = new JPanel();
		 t.setPreferredSize(new Dimension(400, 750));
		 t.setBackground(Color.GREEN);
//		 scrollPane.add(t);
		 scrollPane = new JScrollPane(panel1);
		 scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
		 scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  
		panel2.add(scrollPane);
//		panel2.add(t);
//		add(scrollPane);
		JFileChooser  fileDialog = new JFileChooser();
		 JButton showFileDialogButton = new JButton("Open File");
		 showFileDialogButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				 int returnVal = fileDialog.showOpenDialog(panel2);
		            if (returnVal == JFileChooser.APPROVE_OPTION) {
//		               java.io.File file = fileDialog.getSelectedFile();
		            	int a = fileDialog.showSaveDialog(panel2);
		                java.io.File fileToSave = fileDialog.getSelectedFile();
		                setVisible();
		            }				
			}
		});
		 panel2.add(showFileDialogButton);
		add(panel2);
	}
	void setVisible() {
		setVisible(false);
	}
	public static void main(String[] args) {
		ViewListFile vlf = new ViewListFile();
		List<String> list = new LinkedList<String>();
		list.add("ú à");
		list.add("Quả 2");
		list.add("Quả s");
		list.add("Quản 3");
		vlf.genrateListName(list);
	}
}
