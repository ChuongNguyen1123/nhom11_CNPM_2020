package test;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/*w  w  w  . ja va  2  s  .  c om*/
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Main {
  public static void main(String[] args) {
    Object[][] rowData = { { "Hello", "World" } };
    Object[] columnNames = { "A", "B" };

    JTable table;
    DefaultTableModel model;

    model = new DefaultTableModel(rowData, columnNames);
    table = new JTable();
    table.setModel(model);
    JButton add = new JButton("Add");

    add.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        model.addRow(rowData[0]);
      }
    });
    JFrame f = new JFrame();
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Container c = f.getContentPane();
    c.setLayout(new BorderLayout());
    c.add(add, BorderLayout.SOUTH);
    c.add(new JScrollPane(table), BorderLayout.CENTER);

    f.pack();

    f.setLocationRelativeTo(null);
    f.setVisible(true);
  }
}