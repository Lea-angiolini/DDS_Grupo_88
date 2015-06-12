package Grupo88;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

//import javax.swing.JOptionPane;

public abstract class ConexionDB {
	
	static private Connection con;
	
	public static void conectarDB(){
	
		try {
		    Class.forName("com.mysql.jdbc.Driver").newInstance();
		    //JOptionPane.showMessageDialog(null,"Registro exitoso");
	
		} catch (Exception e) {
	
			//JOptionPane.showMessageDialog(null, e, "Error en el registro "+e.getMessage(), JOptionPane.ERROR_MESSAGE);
	
		}
		
		con = null;
		//...
	
		try {
	
		    con = DriverManager.getConnection(
		            "jdbc:mysql://localhost/grupo88?"
		            + "user=root&password=admin");
		    
		    //JOptionPane.showMessageDialog(null,"conectado exitoso");
	
		} catch (SQLException ex) {
	
			//JOptionPane.showMessageDialog(null, ex, "Error al conectar"+ex.getMessage(), JOptionPane.ERROR_MESSAGE);
	
		}
	}
	
	public static void callProcedure(){
		
		
	}
}
