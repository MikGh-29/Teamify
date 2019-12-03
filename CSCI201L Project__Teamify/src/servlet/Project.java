package servlet;

import java.util.List;

public class Project {
	
	public String name;
	public String description;
	protected String url;
	protected List<String> users = null;
	
	public Project(String n, String d, String url) {
		name = n;
		description = d;
		this.url = url;
	}
	
	public void setUser(List<String> u) {
		users = u;
	}
	
	public String getName() { return name; }
	public String getDescription() { return description; }
	public List<String> getUser() { return users; }
	public String getUrl() { return url; }
	
}
