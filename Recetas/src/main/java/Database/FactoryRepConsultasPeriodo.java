package Database;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import ObjetosDB.Consulta;
import ObjetosDB.Usuario;

public class FactoryRepConsultasPeriodo extends StrategyReportes{
	
	private Factory fab;
	
	public FactoryRepConsultasPeriodo() {
		// TODO Auto-generated constructor stub
		fab = new Factory();
	}
	
	private String generarConsulta(String user, String fechaini, String fechafin){
		return
		"select count(*) as cantidad, rec.* from historicoconsultas his "+
		"join recetas rec "+
		"on rec.idReceta = his.idReceta "+
		"where username = '"+user+"' and "+
		"fecha >= '"+fechaini+"' and "+
		"fecha <= '"+fechafin+"' "+
		"group by his.idReceta "+
		"order by count(*) desc; ";
	}
	
	private Consulta cargarConsulta(ResultSet rs) throws SQLException{
		return new Consulta(rs.getInt("idReceta"), rs.getString("nombre"), rs.getInt("cantidad"));
		
	}
	
	
	@Override
	public ArrayList<Consulta> obtenerReporte(Usuario user, String inicio, String fin) {
		// TODO Auto-generated method stub
		ArrayList<Consulta> consultas = new ArrayList<Consulta>();
		CallableStatement cmd;
		ResultSet rs = null;
		
		try{
			cmd = fab.getConnection().prepareCall(generarConsulta(user.getUsername(),inicio,fin));
			rs = cmd.executeQuery();
			
			while(rs.next()){
				consultas.add(cargarConsulta(rs));
			}
		}
		catch(SQLException ex){
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	
		return consultas;
	}
	

}
