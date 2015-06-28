package Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

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
	
			//JOptionPane.showMessageDialog(null, ex, "Error al conectar"+ex.getMessage(), JOptionPane.ERROR_MESSAGE);
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
			
			//JOptionPane.showMessageDialog(null, ex.getMessage());
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
				//user.setComplexion(rs.getString("complexion"));
				//user.setRutina(rs.getString("rutina"));
				user.setSexo(rs.getString("sexo").charAt(0));
			}
			
		}
		catch(SQLException ex){
			//JOptionPane.showMessageDialog(null, ex.getMessage());
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
		
		ResultSet rs = null;
		itemBuscador items = new itemBuscador();
		try
		{
			
			CallableStatement cmd = con.prepareCall("select * from Grupo88.dificultad");
			
			rs = cmd.executeQuery();
			
			while (rs.next()){
				
			items.setDificultad(rs.getString("descripcion"));
		
			}
		}
		catch(SQLException ex){
			//JOptionPane.showMessageDialog(null, ex.getMessage());	
		}
		
		try
		{
			
			CallableStatement cmd = con.prepareCall("select * from Grupo88.ingredientes order by nombre");
			
			rs = cmd.executeQuery();
			
			while (rs.next()){
				
			items.setIngredientesPrincipal(rs.getString("nombre"));
		
			}
		}
		catch(SQLException ex){
			//JOptionPane.showMessageDialog(null, ex.getMessage());	
		}
		
		try
		{
			
			CallableStatement cmd = con.prepareCall("select * from Grupo88.temporadas");
			
			rs = cmd.executeQuery();
			
			while (rs.next()){
				
			items.setTemporada(rs.getString("nombreTemporada"));
		
			}
		}
		catch(SQLException ex){
			//JOptionPane.showMessageDialog(null, ex.getMessage());	
		}
		
		try
		{
			
			CallableStatement cmd = con.prepareCall("select * from grupo88.grupoalim");
			
			rs = cmd.executeQuery();
			
			while (rs.next()){
				
			items.setGrupoAlimenticio(rs.getString("descripcion"));
		
			}
		}
		catch(SQLException ex){
			//JOptionPane.showMessageDialog(null, ex.getMessage());	
		}
		
		return items;
		
	
	}
	
	
	public Recetas cargarRecetasBuscadas(itemsABuscar queBuscar){
		
		ResultSet rs = null;
		Recetas recetas = new Recetas();
		
		try
		{
			
			CallableStatement cmd = con.prepareCall("{call SP_BuscarRecetas(?,?,?,?,?,?,?)}");
			
			cmd.setString(1, queBuscar.getDificultad());
			cmd.setString(2, queBuscar.getTemporada());
			cmd.setString(3, queBuscar.getIngredientePrincipal());
			cmd.setString(4, queBuscar.getGrupoAlimenticio());
			cmd.setInt(5, queBuscar.getCalificacion());
			cmd.setInt(6, queBuscar.getCaloriasMax());
			cmd.setInt(7, queBuscar.getCaloriasMin());
			
			rs = cmd.executeQuery();
			
			while (rs.next()){
				
				recetas.agregarNuevaReceta(rs.getString("nombre"), 
						   rs.getString("creador"), 
						   rs.getString("descripcion"));
		
			}
		}
		catch(SQLException ex){
			//JOptionPane.showMessageDialog(null, ex.getMessage());	
		}
		
		return recetas;
	}
	
	public List<Complexiones> listaComplexiones(){
		
		ResultSet rs = null;
		List<Complexiones> complexiones = new ArrayList<Complexiones>();
		
		try
		{
			
			CallableStatement cmd = con.prepareCall("select * from grupo88.complexion");
					
			rs = cmd.executeQuery();
			
			while (rs.next()){
				
				complexiones.add(new Complexiones(rs.getInt("idComplexion"),rs.getString("complexion")));
		
			}
		}
		catch(SQLException ex){
			//JOptionPane.showMessageDialog(null, ex.getMessage());	
		}
		
		
		return complexiones;
	}
	
	public ArrayList<CondicionesPreexistentes> listaCondPreexistentes(){
		
		ResultSet rs = null;
		ArrayList<CondicionesPreexistentes> condPreex = new ArrayList<CondicionesPreexistentes>();
		
		try
		{
			
			CallableStatement cmd = con.prepareCall("select * from grupo88.condiciones");
					
			rs = cmd.executeQuery();
			
			while (rs.next()){
				
				condPreex.add(new CondicionesPreexistentes(rs.getInt("idCondicion"), rs.getString("condicion")));
		
			}
		}
		catch(SQLException ex){
			//JOptionPane.showMessageDialog(null, ex.getMessage());	
		}
		
		
		return condPreex;
	}
	
	public ArrayList<Dietas> listaDietas(){
		
		ResultSet rs = null;
		ArrayList<Dietas> dietas = new ArrayList<Dietas>();
		
		try
		{
			
			CallableStatement cmd = con.prepareCall("select * from grupo88.dietas");
					
			rs = cmd.executeQuery();
			
			while (rs.next()){
				
				dietas.add(new Dietas(rs.getInt("idDieta"),rs.getString("tipoDieta")));
		
			}
		}
		catch(SQLException ex){
			//JOptionPane.showMessageDialog(null, ex.getMessage());	
		}
		
		
		return dietas;
	}

	public ArrayList<Rutinas> listaRutinas(){
	
		ResultSet rs = null;
		ArrayList<Rutinas> rutinas = new ArrayList<Rutinas>();
		
		try
		{
			
			CallableStatement cmd = con.prepareCall("select * from grupo88.rutinas");
					
			rs = cmd.executeQuery();
			
			while (rs.next()){
				
				rutinas.add(new Rutinas(rs.getInt("idRutina"),rs.getString("rutina")));
		
			}
		}
		catch(SQLException ex){
			//JOptionPane.showMessageDialog(null, ex.getMessage());	
		}
		
		
		return rutinas;
	}
	
	public ArrayList<String> listaTemporadas(){
		
		ResultSet rs = null;
		ArrayList<String> temporadas = new ArrayList<String>();
		
		try
		{
			
			CallableStatement cmd = con.prepareCall("select nombreTemporada from grupo88.Temporadas");
					
			rs = cmd.executeQuery();
			
			while (rs.next()){
				
				temporadas.add(rs.getString("nombreTemporada"));
		
			}
		}
		catch(SQLException ex){
			//JOptionPane.showMessageDialog(null, ex.getMessage());	
		}
		
		
		return temporadas;
	}
	
	public ArrayList<String> listaGruposAlim(){
		
		ResultSet rs = null;
		ArrayList<String> gruposAlim = new ArrayList<String>();
		
		//Eliminar cuando este la tabla
		gruposAlim.add("Leche y derivados");
		gruposAlim.add("Carnes, huevo y pescado");
		gruposAlim.add("Grasas, aceite, manteca");
		gruposAlim.add("Frutas");
		gruposAlim.add("Verduras y hortalizas");
		
		try
		{
			
			CallableStatement cmd = con.prepareCall("select descripcion from grupo88.GrupoAlim");
					
			rs = cmd.executeQuery();
			
			while (rs.next()){
				
				gruposAlim.add(rs.getString("descripcion"));
		
			}
		}
		catch(SQLException ex){
			//JOptionPane.showMessageDialog(null, ex.getMessage());	
		}
		
		
		return gruposAlim;
	}
	
	public ArrayList<String> listaIngredientes(){
		
		ResultSet rs = null;
		ArrayList<String> ingredientes = new ArrayList<String>();
		
		try
		{
			
			CallableStatement cmd = con.prepareCall("select nombre from Grupo88.ingredientes order by nombre");
					
			rs = cmd.executeQuery();
			
			while (rs.next()){
				
				ingredientes.add(rs.getString("nombre"));
		
			}
		}
		catch(SQLException ex){
			//JOptionPane.showMessageDialog(null, ex.getMessage());	
		}
		
		
		return ingredientes;
	}
	
	public ArrayList<String> listaDificultades(){
		
		ResultSet rs = null;
		ArrayList<String> dificultades = new ArrayList<String>();
		
		try
		{
		
			CallableStatement cmd = con.prepareCall("select descripcion from Grupo88.dificultad");
					
			rs = cmd.executeQuery();
			
			while (rs.next()){
				
				dificultades.add(rs.getString("descripcion"));
		
			}
		}
		catch(SQLException ex){
			//JOptionPane.showMessageDialog(null, ex.getMessage());	
		}
		
		
		return dificultades;
	}
	
	
	public String registrarUsuario(Usuario nvoUsuario){
		
		
		try
		{
			
			CallableStatement cmd = con.prepareCall("{call SP_RegistrarUsuario(?,?,?,?,?,?,?,?,?,?,?,?)}");
			
			cmd.setString(1, nvoUsuario.getUsername());
			cmd.setString(2, nvoUsuario.getPassword());
			cmd.setString(3, nvoUsuario.getEmail());
			cmd.setString(4, nvoUsuario.getNombre());
			cmd.setString(5, nvoUsuario.getApellido());
			cmd.setString(6, nvoUsuario.getFechaNacimiento());
			cmd.setString(7, String.valueOf(nvoUsuario.getSexo()));
			cmd.setInt(8, nvoUsuario.getAltura());
			cmd.setInt(9, (nvoUsuario.getComplexion()).getIdComplexion());
			cmd.setInt(10, (nvoUsuario.getDieta()).getIdDietas());
			cmd.setInt(11, (nvoUsuario.getRutina()).getIdRutina());
			cmd.registerOutParameter(12, Types.VARCHAR);
			
			cmd.executeQuery();
			
			
			CallableStatement cmdCondPreex = con.prepareCall("{call SP_RegistrarCondPreexUsuario(?,?)}");
			cmdCondPreex.setString(1, nvoUsuario.getUsername());
				
			for (CondicionesPreexistentes condPreex : nvoUsuario.getCondiciones()) 
			{	
				cmdCondPreex.setInt(2, condPreex.getIdCondPreex());
				cmdCondPreex.executeQuery();
			}	
			
			return cmd.getString(12);
		
		}
		catch(SQLException ex){
			return ex.getMessage();
		}
	}
}
