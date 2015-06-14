package Database;

import java.sql.*;

import javax.swing.text.TableView;

import ObjetosDB.Usuario;


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
	
	public Usuario cargarUsuario(String nombreUsuario){
		ResultSet rs = null;
		Usuario user = new Usuario();
		try {
			CallableStatement cmd = con.prepareCall("{call SP_CargarUsuario(?,?)}");
			
			cmd.setString("pUsuario", nombreUsuario);
			cmd.registerOutParameter("usuarioACargar", Types.REF_CURSOR);
			rs = cmd.executeQuery();
			if(rs.next()){
				user.setAltura(rs.getInt("altura"));
				user.setNombre(rs.getString("nombre"));
				user.setApellido(rs.getString("apellido"));
				user.setComplexion(rs.getString("complexion"));
				user.setRutina(rs.getString("rutina"));
				user.setSexo(rs.getString("sexo").charAt(0));
			}
			
		}
		catch(SQLException ex){
			
		}
		return user;
		
	}
}
