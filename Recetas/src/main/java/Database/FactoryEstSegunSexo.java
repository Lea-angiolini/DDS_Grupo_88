package Database;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import ObjetosDB.Consulta;

public class FactoryEstSegunSexo extends StrategyEstadisticas {
	
	private Factory fab;
	
	public FactoryEstSegunSexo(){
		fab = new Factory();
	}
	
	private Consulta armarConsulta(ResultSet consultasRs) throws SQLException{
		Consulta actual;
		actual = new Consulta(consultasRs.getInt("idReceta"), consultasRs.getString("nombre"), consultasRs.getInt("cantidad"));
		actual.setSexo(consultasRs.getString("sexo"));
		return actual;
	}
	public ArrayList<ArrayList<Consulta>> ObtenerConsulta(int dias){
		ResultSet rs = null;
		ArrayList<Consulta> consultas;
		CallableStatement cmd;
		
		try{
			//Se obtienen top Consultas
			cmd = fab.getConnection().prepareCall("{Call SP_TOPRecetas(?,?)}");
			cmd.setString(1, "M");
			cmd.setInt(2, dias);
			rs = cmd.executeQuery();
			
			consultas = new ArrayList<Consulta>();
			todasConsultas.add(consultas);
			while (rs.next()){
				consultas.add(armarConsulta(rs));	
			}
			cmd.setString(1, "F");
			rs = cmd.executeQuery();
			consultas = new ArrayList<Consulta>();
			todasConsultas.add(consultas);
			
			while (rs.next()){
				consultas.add(armarConsulta(rs));
			}
			
			cmd.close();
		}
		catch(SQLException ex){
			
		}
		return todasConsultas;
	}
}
