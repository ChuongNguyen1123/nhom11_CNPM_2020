package view;

import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;

public class MainView extends JFrame{
ViewListFile viewLF ;
void showviewLF() {
viewLF = new ViewListFile();
	List<String> list = new LinkedList<String>();
	list.add("ú à");
	list.add("Quả 2");
	list.add("Quả s");
	list.add("Quản 3");
	viewLF.genrateListName(list);
}
public static void main(String[] args) {
MainView mv = new MainView();
mv.showviewLF();
}
}
