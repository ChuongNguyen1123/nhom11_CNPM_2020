package model;

import java.util.List;

public abstract class TGB {
	protected String name;

	public TGB(String name) {
		this.name = name;
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

}
