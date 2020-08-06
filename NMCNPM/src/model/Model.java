package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.table.TableModel;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import database.ConnectionDB;

public class Model {
	private Time timeType3;
	private TGB defaultTKB;
	private boolean alarmClockStatus;
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
	// lấy danh sách tên các tgb từ data base
	public List<String> getListNameTGB() throws SQLException {
		List<String> listName = new ArrayList<String>();
		String sqlConfig = "Select * from config ";
		ResultSet rs = ConnectionDB.connection.createStatement().executeQuery(sqlConfig);
		while (rs.next()) {
			listName.add(rs.getString("Name_TGB"));
		}

		return listName;
	}
	// lấy 1 tgb bằng name từ datab base
	public TGB getDataTGB(String name) throws SQLException {
		String sqlConfig = "Select * from config where Name_TGB = ?";
		PreparedStatement prs = ConnectionDB.connection.prepareStatement(sqlConfig);
		prs.setString(1, name);
		ResultSet rsConfig = prs.executeQuery();
		rsConfig.next();
//		System.out.println(rsConfig.getString(1));
		String type = rsConfig.getString("Type_TGB");
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
			// xoÃ¡ dáº¥u \t cuá»‘i
			data = data.substring(0, data.length() - 1);
			tgb.addData(data);
		}
//		System.out.println(tgb);
		return tgb;
	}

	public ResultSet getTableTGB(String nameTGB) throws ClassNotFoundException, SQLException {
		Connection connectTableTGB = cdb.getConnection();
		String sqlTableTGB = "SELECT * FROM config c, " + nameTGB + " WHERE c.Name_TGB =  '" + nameTGB + "'";
		PreparedStatement preTableTGB = connectTableTGB.prepareStatement(sqlTableTGB);
		ResultSet rsTableTGB = preTableTGB.executeQuery();
		return rsTableTGB;
	}

	public void editTKB() {

	}

	public void removeTKB(String nameTGB) throws ClassNotFoundException, SQLException {
		Connection connectionRemove = cdb.getConnection();
		String sqlRemove = "DELETE FROM config WHERE Name_TGB = '" + nameTGB + "'";
		PreparedStatement preRemove = connectionRemove.prepareStatement(sqlRemove);
		preRemove.executeUpdate();

		String sqlXoaBang = " DROP TABLE " + nameTGB;
		PreparedStatement preXB = connectionRemove.prepareStatement(sqlXoaBang);
		preXB.executeUpdate();
		connectionRemove.close();
	}
// Bươc 9 của export
	public boolean export(String filePath, String nameTGB) {

		try {

			TGB tgb;
			tgb = getDataTGB(nameTGB);
			tgb.export(filePath);
		} catch (SQLException | IOException e) {
			return false;
		}
		return true;

	}

	// Bước 7 của import
	public boolean importFromFilexlsx(File input) throws Exception {
		TGBType2 tgb = getTGBFromXLSX(input);

		return tgb.loadToDB();

	}
	// lấy 1 tgb từ file xlsx ( mặc định loại 2 )
	private TGBType2 getTGBFromXLSX(File input) {
		ArrayList<String> nameSubjects = new ArrayList<String>();
		ArrayList<String> dayOfWeeks = new ArrayList<String>();
		ArrayList<String> startTimes = new ArrayList<String>();
		ArrayList<String> addressRooms = new ArrayList<String>();
		TGBType2 tgb = new TGBType2("");
		try {
			// import tá»« file xlsx lÃ  loáº¡i 2.
			FileInputStream fileStream = new FileInputStream(input);

//			HSSFWorkbook wb = new HSSFFWorkbook(fileStream);
			HSSFWorkbook wb = new HSSFWorkbook(fileStream);

//			HSSFFSheet sheet = wb.getSheetAt(0);
			HSSFSheet sheet = wb.getSheetAt(0);
			Iterator<Row> iterator = sheet.iterator();
			// cell name at J7 ( mssv)
			CellReference cellReference = new CellReference("I7");
			Cell cellName = sheet.getRow(cellReference.getRow()).getCell(cellReference.getCol());
			tgb.setName("SV_" + cellName.getStringCellValue());

			while (iterator.hasNext()) {
				Row nextRow = iterator.next();
				// cháº¡y tá»« dÃ²ng 14
				if (nextRow.getRowNum() >= 14) {

					// Cá»™t G lÃ  cá»™t chá»©a tÃªn MÃ´n Há»�c
					CellReference cellRNameSubject = new CellReference("G" + nextRow.getRowNum());
					Cell cellNameSubject = sheet.getRow(cellRNameSubject.getRow()).getCell(cellRNameSubject.getCol());
					// háº¿t thÃ¬ bá»� , dá»«ng Ä‘á»�c
					if (cellNameSubject.getStringCellValue() == "") {
						break;
					}
					nameSubjects.add(cellNameSubject.getStringCellValue());
					// Cá»™t AA lÃ  cá»™t chá»©a thá»© ( DOW )
					CellReference cellRDOWSubject = new CellReference("AA" + nextRow.getRowNum());
					Cell cellDOWSubject = sheet.getRow(cellRDOWSubject.getRow()).getCell(cellRDOWSubject.getCol());
					dayOfWeeks.add(cellDOWSubject.getStringCellValue());
					// Cá»™t AC lÃ  cá»™t chá»©a tiáº¿t báº¯t Ä‘áº§u
					CellReference cellRStartTime = new CellReference("AC" + nextRow.getRowNum());
					Cell cellStartTime = sheet.getRow(cellRStartTime.getRow()).getCell(cellRStartTime.getCol());
					startTimes.add(cellStartTime.getStringCellValue());
					// Cá»™t AI lÃ  cá»™t chá»©a tÃªn PhÃ²ng
					CellReference cellRRoom = new CellReference("AI" + nextRow.getRowNum());
					Cell cellRoom = sheet.getRow(cellRRoom.getRow()).getCell(cellRRoom.getCol());
					addressRooms.add(cellRoom.getStringCellValue());
				}

			}

			wb.close();
			fileStream.close();
//			for (int i = 0; i < nameSubjects.size(); i++) {
//				System.out.println(dayOfWeeks.get(i) + "\t" + nameSubjects.get(i) + " \t" + addressRooms.get(i) + "\t"
//						+ startTimes.get(i) + "\t");
//			}

			// chuyá»ƒn Ä‘á»•i thá»©
			for (int i = 0; i < dayOfWeeks.size(); i++) {
				try {
					dayOfWeeks.set(i, (Integer.parseInt(dayOfWeeks.get(i)) - 1) + "");
				} catch (Exception e) {
					dayOfWeeks.set(i, "7");
				}
			}
			ArrayList<String> nameSubjectsC = (ArrayList<String>) nameSubjects.clone();
			ArrayList<String> dayOfWeeksC = (ArrayList<String>) dayOfWeeks.clone();
			ArrayList<String> startTimesC = (ArrayList<String>) startTimes.clone();
			ArrayList<String> addressRoomsC = (ArrayList<String>) addressRooms.clone();
			ArrayList<SupportTGBXLS> li = new ArrayList<SupportTGBXLS>();
			for (int i = 0; i < dayOfWeeks.size(); i++) {
				SupportTGBXLS sp = new SupportTGBXLS(dayOfWeeksC.get(0), nameSubjectsC.get(0), addressRoomsC.get(0),
						startTimesC.get(0));
				li.add(sp);
				nameSubjectsC.remove(0);
				dayOfWeeksC.remove(0);
				startTimesC.remove(0);
				addressRoomsC.remove(0);
			}
			ArrayList<SupportTGBXLS> li2 = (ArrayList<SupportTGBXLS>) li.clone();
			ArrayList<Data2> listData2 = new ArrayList<Data2>();
			int step = li2.size();
			for (int i = 0; i < step; i++) {
				if (i + 1 < step - 1) { // náº¿u cÃ²n trong táº§m thÃ¬ cá»© gá»™p 2 cÃ¡i sÃ¡t nhau thÃ nh 1
					Data2 data = li.get(i).megeData(li.get(i + 1));
					// mege thÃ nh cÃ´ng
					if (data != null) {
						listData2.add(data);
						i++; // nháº£y thÃªm 1 bÆ°á»›c
						continue;
					}
					// mege tháº¥t báº¡i ( data == null)
					data = li.get(i).covertData();
					listData2.add(data);
					continue;

				}
				listData2.add(li.get(i).covertData());

			}
//			for (Data2 sp : listData2) {
//				System.out.println(sp);
//
//			}

			tgb.setListData(listData2);
			System.out.println(tgb);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tgb;
	}

	// lá»›p há»— trá»£ Ä‘á»�c tá»« xls
	private class SupportTGBXLS {
		String dayOfWeek;
		String subject;
		String room;
		String startTime;

		public SupportTGBXLS(String dayOfWeek, String subject, String room, String startTime) {
			super();
			this.dayOfWeek = dayOfWeek;
			this.subject = subject;
			this.room = room;
			this.startTime = startTime;
		}

		@Override
		public boolean equals(Object obj) {

			if (obj instanceof SupportTGBXLS) {
				SupportTGBXLS another = (SupportTGBXLS) obj;
				return Integer.parseInt(startTime) == Integer.parseInt(another.startTime);
			}
			return false;
		}

		public Data2 megeData(SupportTGBXLS sp2) {
			boolean isMorning = (startTime.equalsIgnoreCase("1") || startTime.equalsIgnoreCase("4"));
			// 2 cÃ¡i cÃ¹ng ngÃ y -> phÃ¢n biá»‡t thá»© tá»±
			if (dayOfWeek.equalsIgnoreCase(sp2.dayOfWeek)) {
				if (Integer.parseInt(startTime) < Integer.parseInt(sp2.startTime)) {
					return new Data2(dayOfWeek, subject, room, sp2.subject, sp2.room, isMorning);
				} else {
					return new Data2(dayOfWeek, sp2.subject, sp2.room, subject, room, isMorning);

				}
			} else {// hai cÃ¡i khÃ´ng cÃ¹ng ngÃ y -> láº¥y cÃ¡i Ä‘áº§u
				return null;
			}

		}

		public Data2 covertData() {
			boolean isMorning = (startTime.equalsIgnoreCase("1") || startTime.equalsIgnoreCase("4"));
			if (startTime.equalsIgnoreCase("1") || startTime.equalsIgnoreCase("7")) {

				return new Data2(dayOfWeek, subject, room, "", "", isMorning);
			} else {
				return new Data2(dayOfWeek, "", "", subject, room, isMorning);
			}
		}

		@Override
		public String toString() {
			return dayOfWeek + "\t" + subject + "\t" + room + "\t" + startTime;
		}

	}

	// Bước 7 của import
	public boolean importFromFiletgb(File input) throws Exception {
		TGB tgb = getTGBFromFiletgb(input);
		return tgb.loadToDB();
	}
	
	private TGB getTGBFromFiletgb(File input) throws Exception {
		String name = "";
		TGB tgb = null; // táº¡o 1 tgb Ä‘á»ƒ lÆ°u vÃ o
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

	public TGB getDataModel(TableModel def1, TableModel def2) {
		for (int i = 0; i < 5; i++) {
			System.out.println();
			for (int j = 0; j < 7; j++) {
				System.out.print(def1.getValueAt(i, j) + "\t");
			}
		}
		System.out.println("===========");
		for (int i = 0; i < 5; i++) {
			System.out.println();
			for (int j = 0; j < 7; j++) {
				System.out.print(def2.getValueAt(i, j) + "\t");
			}
		}
		return null;
	}

	public static void main(String[] args) throws Exception {
		File f = new File("resource/report3.xls");
		Model model = new Model();
		System.out.println(f.exists());
		model.importFromFilexlsx(f);

	}
}
