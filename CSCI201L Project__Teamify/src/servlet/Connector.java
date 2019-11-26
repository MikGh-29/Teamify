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
		String cmd = "INSERT INTO Project.Users (Username, Password, Email, UserType, Description) VALUES (?, ?);";
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
		String targetName = name.equals("ProjectName") ? "Username" : "ProjectName";
		String table = name.equals("ProjectName") ? "Projects" : "Users";
		String cmd = "SELECT t.? FROM Tags t WHERE t.TagName=?;";
		Map<String, Integer> count = null;
		try {
			ps = con.prepareStatement(cmd);
			count = new HashMap<String, Integer>();
			for(String tag : tags) {
				ps.setString(1, name);
				ps.setString(2, tag);
				rs = ps.executeQuery();
				while(rs.next()) {
					String temp = rs.getString(name);
					int curr;
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
		String cmd = "SELECT * FROM ? WHERE ?=?;";
		try {
			ps = con.prepareStatement(cmd);
			ps.setString(1, "Projects");
			ps.setString(2, "ProjectName");
			Iterator<Entry<String, Integer>> it = sorted.entrySet().iterator();
			while(it.hasNext()) {
				Map.Entry<String, Integer> me = (Map.Entry<String, Integer>)it.next();
				ps.setString(3, (String)me.getKey());
				rs = ps.executeQuery();
				target.add(new User(rs.getString("ProjectName"), rs.getString("Description")));
			}
			
			cmd = "SELECT * FROM ?.ProjectMembers WHERE ?=?;";
			ps = con.prepareStatement(cmd);
			ps.setString(1, "Projects");
			ps.setString(2, "ProjectName");
			for(User u : target) {
				ps.setString(3, u.name);
				rs = ps.executeQuery();
				List<String> alt = new ArrayList<String>();
				while(rs.next()) alt.add(rs.getString("Username"));
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
		String cmd = "SELECT * FROM ? WHERE ?=?;";
		try {
			ps = con.prepareStatement(cmd);
			ps.setString(1, "Users");
			ps.setString(2, "Username");
			Iterator<Entry<String, Integer>> it = sorted.entrySet().iterator();
			while(it.hasNext()) {
				Map.Entry<String, Integer> me = (Map.Entry<String, Integer>)it.next();
				ps.setString(3, (String)me.getKey());
				rs = ps.executeQuery();
				target.add(new Project(rs.getString("Username"), rs.getString("Description")));
			}
			
			cmd = "SELECT * FROM ?.ProjectMembers WHERE ?=?;";
			ps = con.prepareStatement(cmd);
			ps.setString(1, "Projects");
			ps.setString(2, "ProjectName");
			for(Project p : target) {
				ps.setString(3, p.name);
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
