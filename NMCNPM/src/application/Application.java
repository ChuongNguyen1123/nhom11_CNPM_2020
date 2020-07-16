package application;

import java.sql.SQLException;

import controller.Controller;
import model.Model;
import view.MainView;

public class Application {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		MainView view = new MainView();
		Model model = new Model();
		Controller controller = new Controller(view, model);
	}
}
