
import java.sql.*;

public class Connector {
	
	public Connection con;
	public PreparedStatement ps;
	public ResultSet rs;
	
	public Connector(String url) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.url = url;
			con = DriverManager.getConnection(this.url);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		ps = null;
		rs = null;
	}
	
	public String createUser(String username, String password) {
		String response = "Success";
		String cmd = "INSERT INTO Project.Users (Username, Password) VALUES (?, ?);";
		try {
			ps = con.prepareStatement(cmd);
			ps.setString(1, username);
			ps.setString(2, password);
			ps.executeUpdate();
		} catch(SQLException e) {
			response = "User already exists";
		} catch(SQLTimeoutException e) {
			System.out.println(e.getMessage());
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
			else temp = "Success";
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return response;
	}
	
	public List<String> findProject(String term) {
		String cmd = "SELECT * FROM Project.Projects WHERE LOWER(ProjectName)=LOWER(?);";
		List<String> projects = new ArrayList<String>();
		try {
			ps = con.prepareStatement();
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
			ps = con.prepareStatement();
			ps.setString(1, term);
			rs = ps.executeQuery();
			while(rs.next()) {
				projects.add(rs.getString("Username"));
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return users;
	}
	
	public static <T, E> List<T> getByTag(Class<T> type, Class<E> altType, List<String> tags) {
		String name = type == Project.class ? "ProjectName" : "Username";
		String targetName = name.equals("ProjectName") ? "Username" : "ProjectName";
		String table = name.equals("ProjectName") ? "Projects" : "Users";
		
		String cmd = "SELECT t.? FROM Tags t WHERE t.TagName=?;";
		ps = con.prepareStatement(cmd);
		Map<String, int> count = new HashMap<String, int>();
		List<T> target = new ArrayList<T>();
		for(String tag : tags) {
			ps.setString(1, name);
			ps.setString(2, tag);
			rs = ps.executeQuery();
			while(rs.next()) {
				String temp;
				int curr;
				try {
					count.put(temp, count.at(temp)+1);
				} catch(Exception e) {
					count.put(temp, 1);
		}	}	}
		Map<Integer, String> sorted = 
			count.entrySet().stream()
			.sorted(Entry.comparingByValue())
			.collect(Collectors.toMap(Entry::getKey, Entry::getValue,
            (e1, e2) -> e1, LinkedHashMap::new));
			
		cmd = "SELECT * FROM ? WHERE ?=?;";
		ps = con.prepareStatement();
		ps.setString(1, table);
		ps.setString(2, name);
		Iterator it = sorted.entrySet().iterator();
		while(it.hasNext()) {
			Map.Entry me = (Map.Entry)it.next();
			ps.setString(3, (String)it.getKey());
			rs = ps.executeQuery();
			target.add(T.getDeclaredConstructor(String.class, String.class).newInstance(rs.getString("ProjectName"), rs.getString("Description")));
		}
		
		cmd = "SELECT * FROM ?.ProjectMembers WHERE ?=?;";
		ps = con.prepareStatement();
		ps.setString(1, table);
		ps.setString(2, name);
		for(T p : target) {
			ps.setString(3, p.name);
			rs = ps.executeQuery();
			List<String> alt = new ArrayList<String>();
			while(rs.next()) alt.add(rs.getString(targetName));
			if(type == Project.class) p.setUsers(alt);
			else p.setProjects(alt);
		}
		return target;
	}
	
}
