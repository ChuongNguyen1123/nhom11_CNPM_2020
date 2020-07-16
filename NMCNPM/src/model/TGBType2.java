package model;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TGBType2 extends TGB {
	private List<Data2> listData;

	public TGBType2(String name) {
		super(name);
		this.name = name;
		setListData(new ArrayList<Data2>());
	}

	@Override
	void editTKB(TGB tbkEdited) {
		// TODO Auto-generated method stub

	}

	public List<Data2> getListData() {
		return listData;
	}

	public void setListData(List<Data2> listData) {
		this.listData = listData;
	}

	@Override
	void addData(String lineData) {
		String[] split = lineData.split("\t");
		listData.add(new Data2(split[0], split[1], split[2], split[3], split[4], Boolean.parseBoolean(split[5])));

	}

	@Override
	public String toString() {
		StringBuffer result = new StringBuffer();
		result.append("name " + name + "\n");
		result.append("has alarm clock " + hasAlarmClock + "\n");
		result.append("is Default display " + isDefaultDisplay + "\n");
		for (Data2 d : listData) {
			result.append(d + "\n");
		}
		result.append("\n");
		return result.toString();
	}

	@Override
	protected void insertData() throws SQLException {
		for (Data2 data2 : listData) {
			data2.insertData(name);
		}

	}

	@SuppressWarnings("resource")
	@Override
	protected void export(String filePath) throws IOException {
		// tạo file
		new FileOutputStream(filePath);
		// đọc file
		BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
		writer.write("@name" + "\t" + name + "\n");
		writer.write("@type" + "\t" + "type 2" + "\n");
		writer.write("@has Alarm clock" + "\t" + hasAlarmClock + "\n");
		writer.write("@is Deafult display" + "\t" + isDefaultDisplay + "\n");
		writer.write("@data" + "\n");
		for (Data2 d : listData) {
			writer.write(d.toString() + "\n");
		}
		writer.flush();
		writer.close();
	}

}
