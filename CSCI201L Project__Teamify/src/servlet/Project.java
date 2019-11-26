package servlet;

import java.util.List;

public class Project {
	
	protected String name;
	protected String description;
	protected List<String> users = null;
	
	public Project(String n, String d) {
		name = n;
		description = d;
	}
	
	public void setUser(List<String> u) {
		users = u;
	}
	
	public String getName() { return name; }
	public String getDescription() { return description; }
	public List<String> getUser() { return users; }
	
}
