package model;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

// là mẫu cho học sinh cấp 1-2-3
public class TGBType1 extends TGB {
	private List<Data1> listData;

	public TGBType1(String name) {
		super(name);
		listData = new LinkedList<Data1>();
		hasAlarmClock = false;
	}

	@Override
	void editTKB(TGB tbkEdited) {

	}

	public List<Data1> getListData() {
		return listData;
	}

	public void setListData(List<Data1> listData) {
		this.listData = listData;
	}

	@Override
	public void addData(String lineData) {
		System.out.println(lineData);
		String[] split = lineData.split("\t");
		listData.add(
				new Data1(split[0], split[1], split[2], split[3], split[4], split[5], Boolean.parseBoolean(split[6])));
	}

	@Override
	public String toString() {
		StringBuffer result = new StringBuffer();
		result.append("name " + name + "\n");
		result.append("has alarm clock " + hasAlarmClock + "\n");
		result.append("is Default display " + isDefaultDisplay + "\n");
		for (Data1 d : listData) {
			result.append(d + "\n");
		}
		result.append("\n");
		return result.toString();
	}

	@Override
	protected void insertData() throws SQLException {
		for (Data1 data1 : listData) {
			data1.insertData(name);
		}

	}

	@SuppressWarnings("resource")
	@Override
	public  void export(String filePath) throws IOException {
		// tạo file
		FileOutputStream f = new FileOutputStream(filePath);
		// đọc file
		BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
		writer.write("@name" + "\t" + name + "\n");
		writer.write("@type" + "\t" + "type 1" + "\n");
		writer.write("@has Alarm clock" + "\t" + hasAlarmClock + "\n");
		writer.write("@is Deafult display" + "\t" + isDefaultDisplay + "\n");
		writer.write("@data" + "\n");
		for (Data1 d : listData) {
			writer.write(d.toString() + "\n");
		}
		writer.flush();
		writer.close();
		f.close();
	}
}
