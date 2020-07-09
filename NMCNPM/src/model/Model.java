package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Time;
import java.util.List;

public class Model {
	private Time timeType3;
	private TGB defaultTKB;
	private boolean alarmClockStatus;

	public Model() {
		super();
		this.timeType3 = null;;
		this.defaultTKB = null;
		this.alarmClockStatus = false;

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

	public void importFromFilexlsx(String path) {

	}

	public void importFromFiletgb(File input) throws Exception {
		String name = "";
//		List<Data> data = null;
		TGB tgb = null; // tạo 1 tgb để lưu vào
		BufferedReader fileReader = new BufferedReader(new FileReader(input));
		String line = "";
		String[] split;
		while ((line = fileReader.readLine()) != null) {
			split = line.split("\t");

			if (split[0].equalsIgnoreCase("@Name")) {
//				tgb.setName(split[1]);
				name = split[1];
			}
			if (split[0].equalsIgnoreCase("@Type")) {
				if (split[1].equalsIgnoreCase("type 1")) {
					tgb = new TGBType1(name);
					while ((line = fileReader.readLine()) != null) {
						tgb.addData(line);
					}
					break;
				}
				if (split[1].equalsIgnoreCase("type 2")) {
					tgb = new TGBType2(name);
					while ((line = fileReader.readLine()) != null) {
						tgb.addData(line);
					}
					break;
				}
				if (split[1].equalsIgnoreCase("type 3")) {
					tgb = new TGBType3(name);
					while ((line = fileReader.readLine()) != null) {
						tgb.addData(line);
					}
					break;
				}
			}

		}
		System.out.println(tgb);
fileReader.close();
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
	File fileTest = new File("resource/a.txt");
	model.importFromFiletgb(fileTest);
}
}
