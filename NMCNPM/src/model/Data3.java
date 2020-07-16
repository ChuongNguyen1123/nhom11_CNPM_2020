package model;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import database.ConnectionDB;

public class Data3 extends Data {
	private String dayOfWeek;
	private String nameAction;
	private String timeAction;

	public Data3(String dayOfWeek, String nameAction, String timeAction) {
		super();
		this.dayOfWeek = dayOfWeek;
		this.nameAction = nameAction;
		this.timeAction = timeAction;
	}

	public String getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public String getNameAction() {
		return nameAction;
	}

	public void setNameAction(String nameAction) {
		this.nameAction = nameAction;
	}

	public String getTimeAction() {
		return timeAction;
	}

	public void setTimeAction(String timeAction) {
		this.timeAction = timeAction;
	}

	@Override
	void insertData(String name) throws SQLException {
		String preSQL = ("INSERT INTO " + name + " VALUES (?,?,?)");
		PreparedStatement pre = ConnectionDB.connection.prepareStatement(preSQL);
		pre.setString(1, dayOfWeek);
		pre.setString(2, nameAction);
		pre.setString(3, timeAction);
		pre.execute();


	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return dayOfWeek + "\t" + nameAction + "\t" + timeAction;
	}
}
