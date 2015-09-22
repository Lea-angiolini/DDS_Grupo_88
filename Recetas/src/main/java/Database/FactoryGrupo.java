package Database;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.apache.wicket.markup.html.pages.ExceptionErrorPage;

import ObjetosDB.Grupo;
import ObjetosDB.Usuario;

public class FactoryGrupo {
	
	private Factory fab;
	private String tableName = "grupos ";
	private String principalSelect = "select * from "+tableName;
	private Usuario user;
	
	public FactoryGrupo(Usuario user){
		fab = new Factory();
		this.user = user;
	}
	
	public Grupo contruir(ResultSet rs) throws SQLException{
		
		return new Grupo(rs.getInt("idGrupo"), 
						 rs.getString("nombreGrupo"),
						 rs.getString("creador"), 
						 rs.getString("detalle"));
		
	}
	public ArrayList<Grupo> getGrupos(){
		return generarGrupos("");
	}
	private boolean grupoAdmite(int grupo, String user){
		ResultSet rs = null;
		try
		{
			CallableStatement cmd;
			cmd = fab.getConnection().prepareCall("select * from relusuariogrupo "+ 
												  "where nombreUsuario = '"+user+"' and "+
												  "idGrupo = "+grupo); 
			rs = cmd.executeQuery();
			
			return rs.next();		
		}
		catch(SQLException ex){
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
		return false;
	}
	
	public Grupo getGrupoCompleto(int id){
		
		if(grupoAdmite(id, user.getUsername()))	
			return generarGrupos("where idGrupo = "+id).get(0);
		return null;

	}
	public ArrayList<Grupo> getGruposIn(String select){
		return generarGrupos("where IdGrupo in("+select+")");
	}
	public ArrayList<Grupo> generarGrupos(String condicion){
		
		ArrayList<Grupo> grupos = new ArrayList<Grupo>();
		ResultSet rs = null;
			try
			{
				CallableStatement cmd;
				cmd = fab.getConnection().prepareCall(principalSelect+condicion); 
				rs = cmd.executeQuery();
				
				while(rs.next()){
					grupos.add(contruir(rs));
				}			
			}
			catch(SQLException ex){
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}
		
		return grupos;
	}
}