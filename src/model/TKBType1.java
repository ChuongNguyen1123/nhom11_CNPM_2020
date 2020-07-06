package model;

import java.util.LinkedList;
import java.util.List;

// là mẫu cho học sinh cấp 1-2-3
public class TKBType1 extends TKB {
	private List<Data1> listData;

	public TKBType1(String name) {
		super(name);
		listData = new LinkedList<Data1>();
	}

	@Override
	void editTKB(TKB tbkEdited) {

	}

	public List<Data1> getListData() {
		return listData;
	}

	public void setListData(List<Data1> listData) {
		this.listData = listData;
	}

}
