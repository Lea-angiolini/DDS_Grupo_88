package Database;

import java.sql.*;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.text.TableView;

import ObjetosDB.*;


public class Factory {
	
	static private Connection con;
	
	public Factory(){
		try {
		    Class.forName("com.mysql.jdbc.Driver").newInstance();
		    //JOptionPane.showMessageDialog(null,"Registro exitoso");
	
		} catch (Exception ex) {
	
			//JOptionPane.showMessageDialog(null, e, "Error en el registro "+e.getMessage(), JOptionPane.ERROR_MESSAGE);
			ex.printStackTrace();
		}
		
		con = null;
		//...
	
		try {
	
		    con = DriverManager.getConnection(
		            "jdbc:mysql://localhost/grupo88?"
		            + "user=llevaYtrae&password=gil&noAccessToProcedureBodies=true");
		    
		    if (con == null)
		    	throw new SQLException("No se pudo abrir la conexion.");
		    //JOptionPane.showMessageDialog(null,"conectado exitoso");
		    
		} catch (SQLException ex) {
	
			JOptionPane.showMessageDialog(null, ex, "Error al conectar"+ex.getMessage(), JOptionPane.ERROR_MESSAGE);
			//ex.printStackTrace();
		}
		
	}
	
	public Boolean loguearUsuario(String user, String password) {
		Boolean resp = false;
		try
		{
			CallableStatement cmd;
			cmd = con.prepareCall("{call SP_Login(?,?,?)}");
			
			cmd.setString(1, user); // MySQL es re puto y quiere los numeros
			
			cmd.setString(2, password); // MySQL es re puto y quiere los numeros
			
			cmd.registerOutParameter(3, Types.BOOLEAN); // MySQL es re puto y quiere los numeros
			cmd.execute();
			
			resp = cmd.getBoolean(3);
			
			
		}
		catch(Exception ex){
			
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
		return resp;
	}
	
	public Usuario cargarDatosBasicosDe(String nombreUsuario){
		ResultSet rs = null;
		Usuario user = new Usuario();
		try {
			CallableStatement cmd = con.prepareCall("{call SP_CargarUsuario(?)}");
			
			cmd.setString(1, nombreUsuario);
			//cmd.registerOutParameter(2, Types.REF_CURSOR);
			rs = cmd.executeQuery();
			if(rs.next()){
				user.setAltura(rs.getInt("altura"));
				user.setNombre(rs.getString("nombre"));
				user.setApellido(rs.getString("apellido"));
				user.setComplexion(rs.getString("complexion"));
				user.setRutina(rs.getString("rutina"));
				user.setSexo(rs.getString("sexo").charAt(0));
			}
			JOptionPane.showMessageDialog(null, ""+user.getAltura()+" "+user.getNombre());
			
		}
		catch(SQLException ex){
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
		return user;
		
	}
	
	public Recetas cargarRecetasPopulares()
	{
		ResultSet rs = null;
		Recetas recetas = new Recetas();
		try
		{
			
			CallableStatement cmd = con.prepareCall("{call SP_RecetasPopulares()}");
			
			rs = cmd.executeQuery();
			
			
			while (rs.next()){
				
				recetas.agregarNuevaReceta(rs.getString("nombre"), 
										   rs.getString("creador"), 
										   rs.getString("descripcion"));
				
				
		}
		}
		catch(SQLException ex){
			
		}
		
		return recetas;
	}
	
	public Recetas cargarRecetasUsuario(String usuario)
	{
		ResultSet rs = null;
		Recetas recetas = new Recetas();
		try
		{
			
			CallableStatement cmd = con.prepareCall("{call SP_RecetasUsuario(?)}");
			cmd.setString(1, usuario);
			
			rs = cmd.executeQuery();
			
			
			while (rs.next()){
				
				recetas.agregarNuevaReceta(rs.getString("nombre"), 
										   rs.getString("creador"), 
										   rs.getString("descripcion"));
				
				
		}
		}
		catch(SQLException ex){
			
		}
		
		return recetas;
	}
	
	
	public itemBuscador cargarItemBuscador(){
		
		ResultSet rsDificultad = null;
		itemBuscador items = new itemBuscador();
		try
		{
			
			CallableStatement cmd = con.prepareCall("select * from Grupo88.dificultad");
			
			rsDificultad = cmd.executeQuery();
			
			while (rsDificultad.next()){
				
			items.setDificultad(rsDificultad.getString("descripcion"));
		
			}
		}
		catch(SQLException ex){
			JOptionPane.showMessageDialog(null, ex.getMessage());	
		}
		
		try
		{
			
			CallableStatement cmd = con.prepareCall("select * from Grupo88.ingredientes");
			
			rsDificultad = cmd.executeQuery();
			
			while (rsDificultad.next()){
				
			items.setIngredientesPrincipal(rsDificultad.getString("nombre"));
		
			}
		}
		catch(SQLException ex){
			JOptionPane.showMessageDialog(null, ex.getMessage());	
		}
		
		try
		{
			
			CallableStatement cmd = con.prepareCall("select * from Grupo88.temporadas");
			
			rsDificultad = cmd.executeQuery();
			
			while (rsDificultad.next()){
				
			items.setTemporada(rsDificultad.getString("nombreTemporada"));
		
			}
		}
		catch(SQLException ex){
			JOptionPane.showMessageDialog(null, ex.getMessage());	
		}
		
		return items;
		
	
	}
}
