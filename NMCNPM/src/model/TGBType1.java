package model;

import java.util.LinkedList;
import java.util.List;

// là mẫu cho học sinh cấp 1-2-3
public class TGBType1 extends TGB {
	private List<Data1> listData;

	public TGBType1(String name) {
		super(name);
		listData = new LinkedList<Data1>();
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
	void addData(String lineData) {
		String[] split = lineData.split("\t");
		listData.add(new Data1(split[0], split[1], split[2], split[3], split[4], split[5], Boolean.parseBoolean(split[6])));
	}
@Override
public String toString() {
	StringBuffer result = new StringBuffer();
	result.append(name + "\n");
	for (Data1 d : listData) {
		result.append(d + "\n" );
	}
	result.append("\n");
	return result.toString();
}
}
