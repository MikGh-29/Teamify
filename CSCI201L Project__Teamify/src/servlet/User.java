
package servlet;

import java.util.List;

public class User {
	
	protected String name;
	protected String description;
	protected List<String> projects;
	
	public User(String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	public void setProject(List<String> projects) {
		this.projects = projects;
	}
	
	public String getName() { return name; }
	public String getDescription() { return description; }
	public List<String> getProjects() { return projects; }
	
}