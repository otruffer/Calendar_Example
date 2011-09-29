package models;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Database {
	private static Map<String, User> users =  new HashMap<String, User>();
	
	public static void initialize(){
		users = new HashMap<String, User>();
	}
	
	public static User getUserByName(String name){
		return users.get(name);
	}
	
	public static List<User> getUsers(){
		return new LinkedList<User>(users.values());
	}

	public static void addUser(User user) {
		if(!users.containsKey(user.getName()))
			users.put(user.getName(), user);
	}
}
