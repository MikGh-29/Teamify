package servlet;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class Connector {
	
	public Connection con;
	public PreparedStatement ps;
	public ResultSet rs;
	
	public Connector(String url) {
		if(url == null || url.trim().length() == 0) {
			url = "jdbc:mysql://google/Project?cloudSqlInstance=white-inscriber-255423:us-central1:sql-db-1"
					+ "&socketFactory=com.google.cloud.sql.mysql.SocketFactory&useSSL=false&user=scarlett"
					+ "&password=password";
		}
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		ps = null;
		rs = null;
	}
	
	public String createUser(String username, String password, String email, String type, String desc) {
		String response = "Success";
		String cmd = "INSERT INTO Project.Users (Username, Password, Email, UserType, Description) VALUES (?, ?, ?, ?, ?);";
		try {
			ps = con.prepareStatement(cmd);
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setString(3, email);
			ps.setString(4, type);
			ps.setString(5, desc);
			ps.executeUpdate();
		} catch(SQLException e) {
			response = "User already exists";
		}
		return response;
	}
	
	public String verifyUser(String username, String password) {
		String response = null;
		String cmd = "SELECT * FROM Project.Users WHERE Username=?;";
		try {
			ps = con.prepareStatement(cmd);
			ps.setString(1, username);
			rs = ps.executeQuery();
			String temp = null;
			if(rs.next()) temp = rs.getString("Password");
			if(temp == null) response = "No user has been found";
			else if(!temp.equals(password)) response = "Incorrect password";
			else response = "Success";
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return response;
	}
	
	public String createProject(String name, String description, String url, String[] tags) {
		String response = "Success";
		String cmd = "INSERT INTO Project.Projects (ProjectName, Description, Image) VALUES (?, ?, ?);";
		try {
			ps = con.prepareStatement(cmd);
			ps.setString(1, name);
			ps.setString(2, description);
			ps.setString(3, url);
			ps.executeUpdate();
		} catch(SQLException e) {
			response = e.getMessage();
		}
		cmd = "INSERT INTO Project.Tags (TagName, ProjectName) VALUES (?, ?);";
		try {
			ps = con.prepareStatement(cmd);
			ps.setString(2, name);
			for(String tag : tags) {
				ps.setString(1, tag);
				ps.executeUpdate();
			}
		} catch(Exception e) {
			response = e.getMessage();
		}
		return response;
	}
	
	public String addUserToProject(String username, String projectName) {
		String response = "Success";
		String cmd = "INSERT INTO Project.ProjectMembers (ProjectName, Username) VALUES (?, ?);";
		try {
			ps = con.prepareStatement(cmd);
			ps.setString(1, projectName);
			ps.setString(2, username);
			ps.executeUpdate();
		} catch(Exception e) {
			response = e.getMessage();
		}
		return response;
	}
	
	public List<String> getUserData(String username) {
		String response = "Success";
		String cmd = "SELECT * FROM Users WHERE Username=?;";
		try {
			ps = con.prepareStatement(cmd);
			ps.setString(1, username);
			rs = ps.executeQuery();
			while(rs.next()) {
				
			}
		}
	}
	
	public String editUserTag(String username, List<String> newTags, List<String> oldTags) {
		String response = "Success";
		String cmd = "INSERT INTO Project.Tags (TagName, Username) VALUES (?, ?);";
		try {
			ps = con.prepareStatement(cmd);
			ps.setString(2, username);
			if(newTags != null) {
				for(String tag : newTags) {
					ps.setString(1, tag);
					ps.executeUpdate();
				}
			}
			cmd = "REMOVE FROM Project.Tags WHERE TagName=? AND Username=?;";
			ps = con.prepareStatement(cmd);
			ps.setString(2, username);
			if(oldTags != null) {
				for(String tag : oldTags) {
					ps.setString(1, tag);
					ps.executeUpdate();
				}
			}
		} catch(Exception e) {
			response = e.getMessage();
		}
		return response;
	}
	
	public List<String> findProject(String term) {
		String cmd = "SELECT * FROM Project.Projects WHERE LOWER(ProjectName)=LOWER(?);";
		List<String> projects = new ArrayList<String>();
		try {
			ps = con.prepareStatement(cmd);
			ps.setString(1, term);
			rs = ps.executeQuery();
			while(rs.next()) {
				projects.add(rs.getString("ProjectName"));
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return projects;
	}
	
	public List<String> findUser(String term) {
		String cmd = "SELECT * FROM Project.Users WHERE LOWER(Username)=LOWER(?);";
		List<String> users = new ArrayList<String>();
		try {
			ps = con.prepareStatement(cmd);
			ps.setString(1, term);
			rs = ps.executeQuery();
			while(rs.next()) {
				users.add(rs.getString("Username"));
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return users;
	}
	
	public <T> Map<String, Integer> getByTag(Class<T> type, List<String> tags) {
		String name = type == Project.class ? "ProjectName" : "Username";
		String cmd = "SELECT t." + name + " FROM Tags t WHERE t.TagName=?;";
		Map<String, Integer> count = null;
		try {
			ps = con.prepareStatement(cmd);
			count = new HashMap<String, Integer>();
			for(String tag : tags) {
				ps.setString(1, tag);
				rs = ps.executeQuery();
				while(rs.next()) {
					String temp = rs.getString(name);
					try {
						count.put(temp, count.get(temp)+1); 
					} catch(Exception e) {
						count.put(temp, 1);
			}	}	}
		} catch(Exception e) {
			e.printStackTrace();
		}
		Map<String, Integer> sorted = 
			(HashMap<String, Integer>)count.entrySet().stream()
			.sorted(Entry.comparingByValue())
			.collect(Collectors.toMap(Entry::getKey, Entry::getValue,
            (e1, e2) -> e1, HashMap::new));
		return sorted;
	}
	
	public List<User> getUserByTag(List<String> tags) {
		List<User> target = new ArrayList<User>();
		Map<String, Integer> sorted = getByTag(Project.class, tags);
		String cmd = "SELECT * FROM Projects WHERE ProjectName=?;";
		try {
			ps = con.prepareStatement(cmd);
			Iterator<Entry<String, Integer>> it = sorted.entrySet().iterator();
			while(it.hasNext()) {
				Map.Entry<String, Integer> me = (Map.Entry<String, Integer>)it.next();
				ps.setString(1, (String)me.getKey());
				rs = ps.executeQuery();
				target.add(new User(rs.getString("ProjectName"), rs.getString("Description")));
			}
			
			cmd = "SELECT * FROM Projects.ProjectMembers WHERE Username=?;";
			ps = con.prepareStatement(cmd);
			for(User u : target) {
				ps.setString(1, u.name);
				rs = ps.executeQuery();
				List<String> alt = new ArrayList<String>();
				while(rs.next()) alt.add(rs.getString("ProjectName"));
				u.setProject(alt);
			}
		} catch(Exception e) {	
			e.printStackTrace();
		}
		return target;
	}
	
	public List<Project> getProjectByTag(List<String> tags) {
		List<Project> target = new ArrayList<Project>();
		Map<String, Integer> sorted = getByTag(Project.class, tags);
		String cmd = "SELECT * FROM Users WHERE Username=?;";
		try {
			ps = con.prepareStatement(cmd);
			Iterator<Entry<String, Integer>> it = sorted.entrySet().iterator();
			while(it.hasNext()) {
				Map.Entry<String, Integer> me = (Map.Entry<String, Integer>)it.next();
				ps.setString(3, (String)me.getKey());
				rs = ps.executeQuery();
				target.add(new Project(rs.getString("Username"), rs.getString("Description")));
			}
			
			cmd = "SELECT * FROM Projects.ProjectMembers WHERE ProjectName=?;";
			ps = con.prepareStatement(cmd);
			for(Project p : target) {
				ps.setString(1, p.name);
				rs = ps.executeQuery();
				List<String> alt = new ArrayList<String>();
				while(rs.next()) alt.add(rs.getString("Username"));
				p.setUser(alt);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return target;
	}
	
}
