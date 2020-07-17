package tgb;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;



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

	public List<String> getListNameTGB() throws SQLException {
		List<String> listName = new ArrayList<String>();
		String sqlConfig = "Select * from config ";
		ResultSet rs = ConnectionDB.connection.createStatement().executeQuery(sqlConfig);
		while (rs.next()) {
			listName.add(rs.getNString("Name_TGB"));
//			System.out.println(rs.getNString("Name_TGB"));
		}

		return listName;
	}
	public List<String> getListModelTGB() throws SQLException {
		List<String> listModelTGB = new ArrayList<String>();
		String sqlConfig = "Select * from config ";
		ResultSet rs = ConnectionDB.connection.createStatement().executeQuery(sqlConfig);
		while (rs.next()) {
			listModelTGB.add(rs.getNString("Model of TGB"));
//			System.out.println(rs.getNString("Model of TGB"));
		}

		return listModelTGB;
	}

	public TGB getDataTGB(String name) throws SQLException {
		String sqlConfig = "Select * from config where Name_TGB = ?";
		PreparedStatement prs = ConnectionDB.connection.prepareStatement(sqlConfig);
		prs.setString(1, name);
		ResultSet rsConfig = prs.executeQuery();
		rsConfig.next();
//		System.out.println(rsConfig.getNString(1));
		String type = rsConfig.getNString("Type_TGB");
		TGB tgb = null;
		if (type.contentEquals("Type 1")) {
			tgb = new TGBType1(name);
		}
		if (type.contentEquals("Type 2")) {
			tgb = new TGBType2(name);
		}
		if (type.contentEquals("Type 3")) {
			tgb = new TGBType3(name);
		}

		tgb.setDefaultDisplay(rsConfig.getBoolean("Is_Default_Display"));
		tgb.setHasAlarmClock(rsConfig.getBoolean("Has_Alarm_Clock"));
		ResultSet rsTGB = ConnectionDB.connection.createStatement().executeQuery("Select *  From " + name);
		while (rsTGB.next()) {
			ResultSetMetaData rsmd = rsTGB.getMetaData();
			int columnCount = rsmd.getColumnCount();
			String data = "";
			for (int i = 1; i <= columnCount; i++) {
				data += rsTGB.getString(i) + "\t";
			}
			// xoá dấu \t cuối
			data = data.substring(0, data.length() - 1);
			tgb.addData(data);
		}
//		System.out.println(tgb);
		return tgb;
	}
	public TGB getDatamodelTGB(String name) throws SQLException {
		String sqlConfig = "Select * from config where Model of TGB = ?";
		PreparedStatement prs = ConnectionDB.connection.prepareStatement(sqlConfig);
		prs.setString(1, name);
		ResultSet rsConfig = prs.executeQuery();
		rsConfig.next();
//		System.out.println(rsConfig.getNString(1));
		String type = rsConfig.getNString("Type_TGB");
		TGB tgb = null;
		if (type.contentEquals("Type 1")) {
			tgb = new TGBType1(name);
		}
		if (type.contentEquals("Type 2")) {
			tgb = new TGBType2(name);
		}
		if (type.contentEquals("Type 3")) {
			tgb = new TGBType3(name);
		}

		tgb.setDefaultDisplay(rsConfig.getBoolean("Is_Default_Display"));
		tgb.setHasAlarmClock(rsConfig.getBoolean("Has_Alarm_Clock"));
		ResultSet rsTGB = ConnectionDB.connection.createStatement().executeQuery("Select *  From " + name);
		while (rsTGB.next()) {
			ResultSetMetaData rsmd = rsTGB.getMetaData();
			int columnCount = rsmd.getColumnCount();
			String data = "";
			for (int i = 1; i <= columnCount; i++) {
				data += rsTGB.getString(i) + "\t";
			}
			// xoá dấu \t cuối
			data = data.substring(0, data.length() - 1);
			tgb.addData(data);
		}
//		System.out.println(tgb);
		return tgb;
	}
	public void addinfo() {//nguyen 
		String name = "";
		TGB tgb = null;
		
	}
	public void notes() {//nguyen 

	}
	
	public void editTKB() {

	}

	public void removeTKB(String name) {

	}

	public void export(String filePath, String nameTGB) throws Exception {
//		FileOutputStream fo = new FileOutputStream(filePath);
//		BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
//		writer.write("á àlkanlmamfamlfa");
		TGB tgb = getDataTGB(nameTGB);
		tgb.export(filePath);

////		FileOutputStream writer = new FileOutputStream(new FileOutputStream("aa", true));
////		out.w
////		BufferedWriter writer = new BufferedWriter(
////				new OutputStreamWriter(new FileOutputStream(new File(path)), charset));
//		writer.write("a a a ");
//		writer.write("b b b  ");
//		writer.flush();
//		writer.close();
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

	public Time getTimeType3(String name) {
		return null;
	}

	public void runAlarmClock() {

	}

	public void showDefaultTKB() {

	}

	public static void main(String[] args) throws Exception {
//		Model model = new Model();
//		File fileTestA = new File("resource/a.txt");
//		System.out.println(model.importFromFiletgb(fileTestA));
//		model.getTKBFromName("tkb1");
//		File fileTestB = new File("resource/b.txt");
//		model.importFromFiletgb(fileTestB);
//		File fileTestC = new File("resource/c.txt");
//		model.importFromFiletgb(fileTestC);
//		model.export("demofile.txt", "G:\\");
//		List<String> lsn = model.getListName();
//		for (String s : lsn) {
//			System.out.println(s);
//			
//		}

	}
}
