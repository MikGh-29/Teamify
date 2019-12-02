package test;

import servlet.Connector;

public class test {

	static Connector con = new Connector(null);
	
	 public static void main(String[] args) {
	  String str = con.createUser("WinnieXi", "winniexi", "winniexi@gmail.com", "Organizer", "WinnieDictator");
	  System.out.println(str);
	  
	  str = con.verifyUser("WinnieXi", "winniexi");
	  System.out.println(str);
	  
	  str = con.createProject("test", "My first project fot testing purpose", "1");
	 }

}
