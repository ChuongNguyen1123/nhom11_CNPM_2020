package tgb;

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

public class ViewListModelTGB extends JFrame {                     //NGUYEN 
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JPanel panel_1;
	private JButton btnOk;
	private JButton btnCancel;
	ViewDatamodelTGB viewDatamodelTGB;
	
	public void setBtnOk(JButton btnOk) {
		this.btnOk = btnOk;
	}

	public void setBtnCancel(JButton btnCancel) {
		this.btnCancel = btnCancel;
	}


	public ViewListModelTGB() {
		setLayout(new BorderLayout());
		setTitle("List ModelTGB");
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
		btnOk = new JButton("Ok");
		btnCancel = new JButton("Cancel");
		panel.add(btnOk);

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
	
	}



	public void showDatamodelTGB() {
		setVisible(true);
	}

	

	public JButton getBtnOkt() {
		return btnOk;
	}

	public JButton getBtnCancel() {
		return btnCancel;
	}


	public static void main(String[] args) {
		
	
		
		
		
		

	}
//		String[] aList = (String[]) listS.toArray();
//		System.out.println(aList);
//		DefaultListModel<String> defListModel = new DefaultListModel<String>();
//		defListModel.add 
}
