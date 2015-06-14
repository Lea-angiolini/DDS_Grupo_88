package Database;

import java.io.ObjectInputStream.GetField;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.apache.log4j.chainsaw.Main;

import Database.Factory;
import ObjetosDB.Usuario;

public class Browser {
	
	public static Boolean Login(String username, String password){
		Factory f = new Factory();
		return f.loguearUsuario(username, password);		
	}
	
	public static Usuario cargarUsuario(String username){
		Factory f = new Factory();
		return f.cargarUsuario(username);
		
	}
}
