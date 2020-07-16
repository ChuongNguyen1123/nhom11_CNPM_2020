package model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import database.ConnectionDB;

public abstract class TGB {
	protected String name;
	protected boolean hasAlarmClock;
	protected boolean isDefaultDisplay;

	public TGB(String name) {
		this.name = name;
		this.hasAlarmClock = false;
		this.isDefaultDisplay = false;
	}

	public TGB(String name, List<Data> data) {
		super();
		this.name = name;
	}

	abstract void addData(String lineData);

	abstract void editTKB(TGB tbkEdited);

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isHasAlarmClock() {
		return hasAlarmClock;
	}

	public void setHasAlarmClock(boolean hasAlarmClock) {
		this.hasAlarmClock = hasAlarmClock;
	}

	public boolean isDefaultDisplay() {
		return isDefaultDisplay;
	}

	public void setDefaultDisplay(boolean isDefaultDisplay) {
		this.isDefaultDisplay = isDefaultDisplay;
	}

	protected boolean loadToDB() throws SQLException {
		Connection conn = ConnectionDB.connection;
		Statement state = conn.createStatement();
		// kiểm tra xem có tkb nào trong db trùng tên vs nó chưa. nếu có thì trả về
		// fales
		String sqlCheck = "SELECT * FROM config WHERE Name_TGB = N'" + name + "'";
		ResultSet rs = state.executeQuery(sqlCheck);
		// nếu trùng tên trong csdl thì return false, không load nữa --> import bị ngắt
		if (rs.next()) {
			System.out.println("có bảng như thế tồn tại, không import");
			System.out.println("tên bảng trùng :" + rs.getString(1));
			return false;
		}
		System.out.println("có thể tạo mới , import");
		// không trùng tên thì tạo bảng tkb mới theo tên tkb này, cập nhật cái config
		if (this instanceof TGBType1) {
			ResultSet rsGetSQl = state.executeQuery("SELECT * FROM DEFINE WHERE Type_TGB = 1");
			rsGetSQl.next();
			String sqlCreate = "CREATE TABLE " + name + rsGetSQl.getString("SQL_Create");
			state.execute(sqlCreate);
			insertData();
			String preSQL = "INSERT INTO config values ( ?,?,?,?)";
			PreparedStatement preS = conn.prepareStatement(preSQL);
			preS.setString(1, name);
			preS.setString(2, "Type 1");
			preS.setString(3, hasAlarmClock + "");
			preS.setString(4, isDefaultDisplay + "");
			preS.execute();
			return true;
		}
		if (this instanceof TGBType2) {
			ResultSet rsGetSQl = state.executeQuery("SELECT * FROM DEFINE WHERE Type_TGB = 2");
			rsGetSQl.next();
			String sqlCreate = "CREATE TABLE " + name + rsGetSQl.getString("SQL_Create");
			state.execute(sqlCreate);
			insertData();
			String preSQL = "INSERT INTO config values ( ?,?,?,?)";
			PreparedStatement preS = conn.prepareStatement(preSQL);
			preS.setString(1, name);
			preS.setString(2, "Type 2");
			preS.setString(3, hasAlarmClock + "");
			preS.setString(4, isDefaultDisplay + "");
			preS.execute();
			return true;
		}
		if (this instanceof TGBType3) {
			ResultSet rsGetSQl = state.executeQuery("SELECT * FROM DEFINE WHERE Type_TGB = 3");
			rsGetSQl.next();
			String sqlCreate = "CREATE TABLE " + name + rsGetSQl.getString("SQL_Create");
			state.execute(sqlCreate);
			insertData();
			String preSQL = "INSERT INTO config values ( ?,?,?,?)";
			PreparedStatement preS = conn.prepareStatement(preSQL);
			preS.setString(1, name);
			preS.setString(2, "Type 3");
			preS.setString(3, hasAlarmClock + "");
			preS.setString(4, isDefaultDisplay + "");
			preS.execute();
			return true;
		}
		return true;
	}

	protected abstract void insertData() throws SQLException;

	protected abstract void export(String filePath) throws IOException;

}
