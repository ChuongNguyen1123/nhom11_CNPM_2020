package test;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class JChooserSaveDemo extends JFrame {

	private JPanel contentPane;
	JButton saveBt;

	public JChooserSaveDemo() {

		setLayout(new BorderLayout());
		setTitle("List File");
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setPreferredSize(new Dimension(500, 300));
		pack();
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		//
		saveBt = new JButton("save");
		saveBt.addActionListener(new ActionListener() {
			JFileChooser chooser = new JFileChooser();

			@Override
			public void actionPerformed(ActionEvent e) {
				chooser.showSaveDialog(contentPane);
				String path = chooser.getCurrentDirectory().toString();
				System.out.println(path);
				String fileName = chooser.getSelectedFile().getName();
				System.out.println(fileName);

			}
		});
		contentPane.add(saveBt);
	}

	public static void main(String[] args) {
		JChooserSaveDemo jcsd = new JChooserSaveDemo();
	}
}
