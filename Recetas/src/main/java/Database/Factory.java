package Database;

import java.sql.*;


public class Factory {
	
	private Connection con;
	
	public Factory(){
		try{
			con = DriverManager.getConnection("jdbc:mysql://localhost/recetasWicket", "aplicacion", "recetas");
		}
		catch (Exception ex){
			//TODO: Hacer algo con la excepcion
		}
	}
	
	public Boolean loguearUsuario(String user, String password){
		Boolean resp = false;
		try
		{
			CallableStatement cmd = con.prepareCall("{call SP_Login(?,?,?)}");
			
			cmd.setString("pUsuario", user);
			cmd.setString("pPassword", password);
			cmd.registerOutParameter("bResp", Types.BOOLEAN);
			resp = cmd.execute();
		
		}
		catch(SQLException ex){
			
		}
		return resp;
	}
}
