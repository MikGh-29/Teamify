
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
	
	public String verifyUser(String username, String password) {
		String response = null;
		String cmd = "SELECT * FROM Project.Users WHERE Username=?;";
		ps = con.prepareStatement(cmd);
		ps.setString(1, username);
		rs = ps.executeQuery();
		String temp = null;
		if(rs.next()) temp = rs.getString("Password");
		if(temp == null) response = "No user has been found";
		else if(!temp.equals(password)) response = "Incorrect password";
		else temp = "Success";
		return response;
	}
	
	public static List<T> getByTag(Class<T> type, List<String> tags) {
		
		if(type.isInstance(new Project())） {
			
		}
		String cmd = "SELECT t.? FROM Tags t WHERE t.?=?;";
		ps = con.prepareStatement(cmd);
		Map<String, int> count = new HashMap<String, int>();
		List<T> target = new ArrayList<T>();
		for(String tag : tags) {
			ps.setString(1, tag);
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
		cmd = "SELECT * FROM Projects WHERE ProjectName=?;";
		ps = con.prepareStatement();
		Iterator it = sorted.entrySet().iterator();
		while(it.hasNext()) {
			Map.Entry me = (Map.Entry)it.next();
			ps.setString(1, (String)it.getKey());
			rs = ps.executeQuery();
			projects.add(new Project(rs.getString("ProjectName"), rs.getString("Description")));
		}
		cmd = "SELECT * FROM Project.ProjectMembers WHERE ProjectName=?;";
		ps = con.prepareStatement();
		for(Project p : projects) {
			ps.setString(1, p.name);
			rs = ps.executeQuery();
			List<String> users = new ArrayList<String>();
			while(rs.next()) user.add(rs.getString("Username"));
			p.setUsers(users);
		}
		return projects;
	}
	
	public List<User> getUserByTag(List<String> tags) {
		
	}
	
}