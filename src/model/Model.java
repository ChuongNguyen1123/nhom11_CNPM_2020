package model;

import java.sql.Time;
import java.util.List;

public class Model {
	private Time timeType3;
	private TKB defaultTKB;
	private boolean alarmClockStatus;

	public Model(Time timeType3, TKB defaultTKB, boolean alarmClockStatus) {
		super();
		this.timeType3 = timeType3;
		this.defaultTKB = defaultTKB;
		this.alarmClockStatus = alarmClockStatus;
	}

	public Time getTimeType3() {
		return timeType3;
	}

	public void setTimeType3(Time timeType3) {
		this.timeType3 = timeType3;
	}

	public TKB getDefaultTKB() {
		return defaultTKB;
	}

	public void setDefaultTKB(TKB defaultTKB) {
		this.defaultTKB = defaultTKB;
	}

	public boolean isAlarmClockStatus() {
		return alarmClockStatus;
	}

	public void setAlarmClockStatus(boolean alarmClockStatus) {
		this.alarmClockStatus = alarmClockStatus;
	}

	public List<String> getListName() {
		return null;
	}

	public TKB getTKBFromName(String name) {
		return null;
	}

	public void editTKB() {

	}

	public void removeTKB(String name) {

	}

	public void exportToFile(String name, String path) {

	}

	public void importFromFilexlsx(String path) {

	}

	public void importFromFiletkb(String path) {

	}

	public Time getTimeType3(String name) {
		return null;
	}

	public void runAlarmClock() {

	}

	public void showDefaultTKB() {

	}

}
