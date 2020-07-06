package model;

public abstract class TKB {
protected String name;
abstract void editTKB(TKB tbkEdited);
public TKB(String name) {
	super();
	this.name = name;
}

}
