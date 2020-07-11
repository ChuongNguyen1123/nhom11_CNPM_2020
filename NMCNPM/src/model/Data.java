package model;

import java.sql.SQLException;

public abstract class Data {
	abstract void insertData(String name) throws SQLException;
}
