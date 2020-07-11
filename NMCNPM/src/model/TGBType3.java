package model;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class TGBType3 extends TGB {
	private List<Data3> listData;

	public TGBType3(String name) {
		super(name);
		this.listData = new LinkedList<Data3>();
	}

	@Override
	void editTKB(TGB tbkEdited) {
		// TODO Auto-generated method stub

	}

	@Override
	void addData(String lineData) {
		String[] split = lineData.split("\t");
		listData.add(new Data3(split[0], split[1], split[2]));

	}


	@Override
	public String toString() {
		StringBuffer result = new StringBuffer();
		result.append("name " + name + "\n");
		result.append("has alarm clock " + hasAlarmClock + "\n");
		result.append("is Default display " + isDefaultDisplay + "\n");
		for (Data3 d : listData) {
			result.append(d + "\n");
		}
		result.append("\n");
		return result.toString();
	}

	@Override
	protected void insertData() throws SQLException {
		for (Data3 data3 : listData) {
			data3.insertData(name);
		}

	}
}
