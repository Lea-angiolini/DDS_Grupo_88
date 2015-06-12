package Database;

import org.apache.log4j.chainsaw.Main;

import Database.Factory;

public class Browser {
	
	public static void main(String[] args) {
		if (Login("Emiliano", "13051990"))
			System.out.println("Logueaste");
		else
			System.out.println("Gil");
	}
	
	public static Boolean Login(String username, String password){
		Factory f = new Factory();
		return f.loguearUsuario(username, password);		
	}
}
