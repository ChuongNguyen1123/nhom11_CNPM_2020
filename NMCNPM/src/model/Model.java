package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import database.ConnectionDB;

public class Model {
	private Time timeType3;
	private TGB defaultTKB;
	private boolean alarmClockStatus;
	@SuppressWarnings("unused")
	private ConnectionDB cdb;

	public Model() throws ClassNotFoundException, SQLException {
		super();
		this.timeType3 = null;
		this.defaultTKB = null;
		this.alarmClockStatus = false;
		cdb = new ConnectionDB();

	}

	public Time getTimeType3() {
		return timeType3;
	}

	public void setTimeType3(Time timeType3) {
		this.timeType3 = timeType3;
	}

	public TGB getDefaultTGB() {
		return defaultTKB;
	}

	public void setDefaultTKB(TGB defaultTKB) {
		this.defaultTKB = defaultTKB;
	}

	public boolean isAlarmClockStatus() {
		return alarmClockStatus;
	}

	public void setAlarmClockStatus(boolean alarmClockStatus) {
		this.alarmClockStatus = alarmClockStatus;
	}

	public List<String> getListName() {
		return null;
	}

	public TGB getTKBFromName(String name) {
		return null;
	}

	public void editTKB() {

	}

	public void removeTKB(String name) {

	}

	public void exportToFile(String name, String path) {

	}

	public void importFromFilexlsx(File input) {

	}

	public boolean importFromFiletgb(File input) throws Exception {
		TGB tgb = getTGBFromtgb(input);
		return tgb.loadToDB();
	}

	private TGB getTGBFromtgb(File input) throws Exception {
		String name = "";
		TGB tgb = null; // tạo 1 tgb để lưu vào
		BufferedReader fileReader = new BufferedReader(new FileReader(input));
		String line = "";
		String[] split;
		while ((line = fileReader.readLine()) != null) {
			split = line.split("\t");

			if (split[0].equalsIgnoreCase("@Name")) {
				name = split[1];
			}
			if (split[0].equalsIgnoreCase("@Type")) {
				if (split[1].equalsIgnoreCase("type 1")) {
					tgb = new TGBType1(name);
				}
				if (split[1].equalsIgnoreCase("type 2")) {
					tgb = new TGBType2(name);
				}
				if (split[1].equalsIgnoreCase("type 3")) {
					tgb = new TGBType3(name);
				}
			}
			if (split[0].equalsIgnoreCase("@has Alarm clock")) {
				tgb.setHasAlarmClock((Boolean.parseBoolean(split[1])));
			}
			if (split[0].equalsIgnoreCase("@is Deafult display")) {
				tgb.setDefaultDisplay(Boolean.parseBoolean(split[1]));
			}
			if (split[0].equalsIgnoreCase("@Data")) {
				while ((line = fileReader.readLine()) != null) {
					tgb.addData(line);
				}
			}

		}
		System.out.println(tgb);
		fileReader.close();
		return tgb;
	}

	public List<String> getListNameTGB(){
		List<String> listName  = new ArrayList<String>();
		return listName;
	}
	public Time getTimeType3(String name) {
		return null;
	}

	public void runAlarmClock() {

	}

	public void showDefaultTKB() {

	}

	public static void main(String[] args) throws Exception {
		Model model = new Model();
//		File fileTestA = new File("resource/a.txt");
//		System.out.println(model.importFromFiletgb(fileTestA));
//		File fileTestB = new File("resource/b.txt");
//		model.importFromFiletgb(fileTestB);
		File fileTestC = new File("resource/c.txt");
		model.importFromFiletgb(fileTestC);
	}
}
