package model;

public class Data1 {
	private String dayOfWeek;
	private String nameSubject1;
	private String nameSubject2;
	private String nameSubject3;
	private String nameSubject4;
	private String nameSubject5;
	private boolean isMorning;

	public Data1(String dayOfWeek, String nameSubject1, String nameSubject2, String nameSubject3, String nameSubject4,
			String nameSubject5, boolean isMorning) {
		super();
		this.dayOfWeek = dayOfWeek;
		this.nameSubject1 = nameSubject1;
		this.nameSubject2 = nameSubject2;
		this.nameSubject3 = nameSubject3;
		this.nameSubject4 = nameSubject4;
		this.nameSubject5 = nameSubject5;
		this.isMorning = isMorning;
	}

	public String getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public String getNameSubject1() {
		return nameSubject1;
	}

	public void setNameSubject1(String nameSubject1) {
		this.nameSubject1 = nameSubject1;
	}

	public String getNameSubject2() {
		return nameSubject2;
	}

	public void setNameSubject2(String nameSubject2) {
		this.nameSubject2 = nameSubject2;
	}

	public String getNameSubject3() {
		return nameSubject3;
	}

	public void setNameSubject3(String nameSubject3) {
		this.nameSubject3 = nameSubject3;
	}

	public String getNameSubject4() {
		return nameSubject4;
	}

	public void setNameSubject4(String nameSubject4) {
		this.nameSubject4 = nameSubject4;
	}

	public String getNameSubject5() {
		return nameSubject5;
	}

	public void setNameSubject5(String nameSubject5) {
		this.nameSubject5 = nameSubject5;
	}

	public boolean isMorning() {
		return isMorning;
	}

	public void setMorning(boolean isMorning) {
		this.isMorning = isMorning;
	}

	@Override
	public String toString() {
		return dayOfWeek + " , " + nameSubject1 + " , " + nameSubject2 + " , " + nameSubject3 + " , " + nameSubject4
				+ " , " + nameSubject5 + ", " + isMorning;
	}

}
