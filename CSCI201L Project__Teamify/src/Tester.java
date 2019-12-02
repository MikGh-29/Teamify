import servlet.*;

public class Tester {
	
	static Connector con = new Connector(null);
	
	public static void main(String[] args) {
		String str = con.createUser("WinnieXi", "winniexi", "winniexi@gmail.com", "Organizer", "WinnieDictator");
		System.out.println(str);
	}
	
}

