package tgb;

import java.awt.Choice;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ItemListener;

import java.util.ArrayList;


import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;





public class ViewDatamodelTGB extends JFrame { //nguyen 
	//cái  này chắc sai bét nhè rồi 
	  
	   ;
	    ArrayList<Contact> contactsList;

	    public ViewDatamodelTGB() {

	        setDefaultCloseOperation(EXIT_ON_CLOSE);

	JLabel label = new JLabel("Select a teacher:");
    container.add(label);

    dayOfWeek = new dayOfWeek();
    dayOfWeek.beginInit();
    dayOfWeek.setCurrentView(CalendarView.Timetable);
    dayOfWeek.setTheme(ThemeType.Light);
    

   

    dayOfWeek.getTimetableSettings().getCellStyle().setBorderBottomColor(new Color(169, 169, 169));
    dayOfWeek.getTimetableSettings().getCellStyle().setBorderBottomWidth(1);
    dayOfWeek.getTimetableSettings().getCellStyle().setBorderLeftColor(new Color(169, 169, 169));
    dayOfWeek.getTimetableSettings().getCellStyle().setBorderLeftWidth(1);
    dayOfWeek.getTimetableSettings().getCellStyle().setBorderRightColor(new Color(169, 169, 169));
    dayOfWeek.getTimetableSettings().getCellStyle().setBorderRightWidth(1);
    dayOfWeek.getTimetableSettings().getCellStyle().setBorderTopColor(new Color(169, 169, 169));
    dayOfWeek.getTimetableSettings().getCellStyle().setBorderTopWidth(1);
    dayOfWeek.getTimetableSettings().getCellStyle().setHeaderTextShadowOffset(0);
    dayOfWeek.getTimetableSettings().getCellStyle().setHeaderTextShadowStyle(ShadowStyle.None);
    dayOfWeek.getTimetableSettings().getDates().clear();

    for (int i = 0; i < 7; i++)
    	dayOfWeek.getTimetableSettings().getDates().add(DateTime.today().addDays(i - 1));

    dayOfWeek.getTimetableSettings().setItemOffset(30);
    dayOfWeek.getTimetableSettings().setShowItemSpans(false);
    dayOfWeek.getTimetableSettings().setSnapInterval(Duration.fromMinutes(1));
    dayOfWeek.getTimetableSettings().setVisibleColumns(7);
    dayOfWeek.endInit();

    springLayout.putConstraint(SpringLayout.EAST, dayOfWeek, 0, SpringLayout.EAST, container);
    springLayout.putConstraint(SpringLayout.NORTH, dayOfWeek, 0, SpringLayout.NORTH, container);
    springLayout.putConstraint(SpringLayout.WEST, dayOfWeek, 0, SpringLayout.WEST, container);
    springLayout.putConstraint(SpringLayout.SOUTH, dayOfWeek, -35, SpringLayout.NORTH, guitarBox);

    springLayout.putConstraint(SpringLayout.WEST, teachers, 5, SpringLayout.EAST, label);
    springLayout.putConstraint(SpringLayout.SOUTH, teachers, -5, SpringLayout.NORTH, guitarBox);

    


   

    
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
