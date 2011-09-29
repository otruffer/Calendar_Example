package controllers;

import play.libs.*;
import play.*;
import play.mvc.*;

import java.text.SimpleDateFormat;
import java.util.*;

import models.*;
import models.Calendar;

public	 class Application extends Controller {

    public static void index() {
    	List<User> users = Database.getUsers();
        render(users);
    }
    
    public static void showCalendars(String username){
    	User user = Database.getUserByName(username);
    	List<Calendar> calendars = user.getCalendars();
    	render(user, calendars);
    }
    
    public static void showEvents(String username, String calendarname, String message){
    	User user = Database.getUserByName(username);
    	Calendar calendar = user.getCalendarByName(calendarname);
    	List<Event> events = calendar.getEventsAfter(Database.getUserByName(username), new Date());
    	render(user, calendar, events, message);
    }
    
    public static void addEvent(String userName, String calendarName, String eventName, String eventStart, String eventEnd){
    	SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm");
    	User user = Database.getUserByName(userName);
    	Calendar calendar = user.getCalendarByName(calendarName);
    	String message = null;
    	try{
    		Date startDate = df.parse(eventStart);
    		Date endDate = df.parse(eventEnd);
        	calendar.addEvent(eventName, startDate, endDate, true);
    	}catch(Exception e){
    		message = "invalid Date Format";
    	}
		showEvents(user.getName(), calendar.getName(), message);

    }

}