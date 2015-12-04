package Database;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import ObjetosDB.Consulta;

public class FactoryEstSegunDificultad extends StrategyEstadisticas implements Serializable{

	private static final long serialVersionUID = -7096808652615386082L;
	private Factory fab;
	
	public FactoryEstSegunDificultad(){
		fab = new Factory();
	}
	
	private Consulta armarConsulta(ResultSet consultasRs) throws SQLException{
		Consulta actual;
		actual = new Consulta(consultasRs.getInt("idDificultad"), consultasRs.getString("descripcion"), consultasRs.getInt("cantidad"));
		return actual;
	}
	public ArrayList<ArrayList<Consulta>> ObtenerConsulta(int dias){
		ArrayList<Consulta> list = new ArrayList<Consulta>();
		
		CallableStatement cmd;
		ResultSet rs = null;
		
		try{
			cmd = fab.getConnection().prepareCall("{call SP_consultasSegunNievelDeDificultad(?)}");
			cmd.setInt(1, dias);
			rs = cmd.executeQuery();
			
			while(rs.next()){
				list.add(armarConsulta(rs));
			}
			
		}
		catch (SQLException ex){
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
		todasConsultas.add(list);
		return todasConsultas;
	}
}