package model;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import database.ConnectionDB;

public class Data2 extends Data {
	private String dayOfWeek;
	private String nameSubject1;
	private String room1;
	private String nameSubject2;
	private String room2;
	boolean isMorning;

	public Data2(String dayOfWeek, String nameSubject1, String room1, String nameSubject2, String room2,
			boolean isMorning) {
		super();
		this.dayOfWeek = dayOfWeek;
		this.nameSubject1 = nameSubject1;
		this.room1 = room1;
		this.nameSubject2 = nameSubject2;
		this.room2 = room2;
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

	public String getRoom1() {
		return room1;
	}

	public void setRoom1(String room1) {
		this.room1 = room1;
	}

	public String getNameSubject2() {
		return nameSubject2;
	}

	public void setNameSubject2(String nameSubject2) {
		this.nameSubject2 = nameSubject2;
	}

	public String getRoom2() {
		return room2;
	}

	public void setRoom2(String room2) {
		this.room2 = room2;
	}

	public boolean isMorning() {
		return isMorning;
	}

	public void setMorning(boolean isMorning) {
		this.isMorning = isMorning;
	}

	@Override
	void insertData(String name) throws SQLException {
		String preSQL = ("INSERT INTO " + name + " VALUES (?,?,?,?,?,?)");
		PreparedStatement pre = ConnectionDB.connection.prepareStatement(preSQL);
		pre.setString(1, dayOfWeek);
		pre.setString(2, nameSubject1);
		pre.setString(3, room1);
		pre.setString(4, nameSubject2);
		pre.setString(5, room2);
		pre.setString(6, isMorning + "");
		pre.execute();

	}

	@Override
	public String toString() {
		return dayOfWeek + " , " + nameSubject1 + " ," + room1 + " , " + nameSubject2 + " , " + room2 + " , "
				+ isMorning;
	}

}
