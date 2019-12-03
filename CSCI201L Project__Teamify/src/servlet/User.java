
package servlet;

import java.util.ArrayList;
import java.util.List;

public class User {
	
	public String name = null;
	public String description = null;
	public String userType = null;
	public String email = null;
	public List<String> projects = new ArrayList<String>();
	public List<String> tags = new ArrayList<String>();
	
	public User() { }
	
	public User(String name, String description) {
		this.name = name;
		this.description = description;
	}
	
}
