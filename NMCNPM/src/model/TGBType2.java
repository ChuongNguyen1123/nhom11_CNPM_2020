package model;

import java.util.ArrayList;
import java.util.LinkedList;
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
		listData.add(new Data2(split[0], split[1], split[2], split[3], split[4], Boolean.parseBoolean(split[6])));

	}
	@Override
	public String toString() {
		StringBuffer result = new StringBuffer();
		result.append(name + "\n");
		for (Data2 d : listData) {
			result.append(d + "\n" );
		}
		result.append("\n");
		return result.toString();
	}
}
