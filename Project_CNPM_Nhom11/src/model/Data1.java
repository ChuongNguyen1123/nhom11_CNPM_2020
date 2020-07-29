package model;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import database.ConnectionDB;

public class Data1 extends Data {
	private String dayOfWeek;
	private String nameSubject1;
	private String nameSubject2;
	private String nameSubject3;
	private String nameSubject4;
	private String nameSubject5;
	private boolean isMorning;

	public Data1(String dayOfWeek, String nameSubject1, String nameSubject2, String nameSubject3, String nameSubject4,
			String nameSubject5, boolean isMorning) {
		super();
		this.dayOfWeek = dayOfWeek;
		this.nameSubject1 = nameSubject1;
		this.nameSubject2 = nameSubject2;
		this.nameSubject3 = nameSubject3;
		this.nameSubject4 = nameSubject4;
		this.nameSubject5 = nameSubject5;
		this.isMorning = isMorning;
	}

	public String getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public String getNameSubject1() {
		return nameSubject1;
	}

	public void setNameSubject1(String nameSubject1) {
		this.nameSubject1 = nameSubject1;
	}

	public String getNameSubject2() {
		return nameSubject2;
	}

	public void setNameSubject2(String nameSubject2) {
		this.nameSubject2 = nameSubject2;
	}

	public String getNameSubject3() {
		return nameSubject3;
	}

	public void setNameSubject3(String nameSubject3) {
		this.nameSubject3 = nameSubject3;
	}

	public String getNameSubject4() {
		return nameSubject4;
	}

	public void setNameSubject4(String nameSubject4) {
		this.nameSubject4 = nameSubject4;
	}

	public String getNameSubject5() {
		return nameSubject5;
	}

	public void setNameSubject5(String nameSubject5) {
		this.nameSubject5 = nameSubject5;
	}

	public boolean isMorning() {
		return isMorning;
	}

	public void setMorning(boolean isMorning) {
		this.isMorning = isMorning;
	}

	@Override
	void insertData(String name) throws SQLException {
		String preSQL = ("INSERT INTO " + name + " VALUES (?,?,?,?,?,?,?)");
			PreparedStatement pre = ConnectionDB.connection.prepareStatement(preSQL);
			pre.setString(1, dayOfWeek);
			pre.setString(2, nameSubject1);
			pre.setString(3, nameSubject2);
			pre.setString(4, nameSubject3);
			pre.setString(5, nameSubject4);
			pre.setString(6, nameSubject5);
			pre.setString(7, isMorning+"");
			pre.execute();
		
	}

	@Override
	public String toString() {
		return dayOfWeek + "\t" + nameSubject1 + "\t" + nameSubject2 + "\t" + nameSubject3 + "\t" + nameSubject4
				+ "\t" + nameSubject5 +"\t" + isMorning;
	}

	public static void main(String args[]) { // parseBoolean returns a boolean primitive value
		String value = "true";
		boolean b = Boolean.parseBoolean(value);
		System.out.println(b);
		// valueOf returns a Boolean object
		String data = "false";
		boolean c = Boolean.valueOf(data);
		System.out.println(c);
		boolean f = Boolean.valueOf(value);
		System.out.println(f);
		value = "No";
		b = Boolean.parseBoolean(value);
		System.out.println(b);
		// null String will return false
		System.out.println(Boolean.parseBoolean(null));
		System.out.println(Boolean.valueOf(null));
		// any value other than true (Case-insensitive) will // return false
		System.out.println(Boolean.parseBoolean("YES"));
		System.out.println(Boolean.valueOf("Y"));
	}
}
