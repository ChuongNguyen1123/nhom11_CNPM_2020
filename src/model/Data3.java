package model;

public class Data3 {
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

}
