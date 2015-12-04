package Database;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import ObjetosDB.Ingredientes;

public class FactoryIngredientes implements Serializable{
	
	private static final long serialVersionUID = 7501263341252581344L;
	private Factory fab;
	private String tableName = "ingredientes ";
	private String principalSelect = "select * from "+tableName;
	
	public FactoryIngredientes(){
		fab = new Factory();
	}
	
	public Ingredientes contruir(ResultSet rs) throws SQLException{
		/*Ingredientes ingrediente = new Ingredientes();
		ingrediente.setIdIngrediente(rs.getInt("idIngrediente"));
		
		*/
		return new Ingredientes(
				rs.getInt("idIngrediente"),
				rs.getString("nombre"), 
				rs.getInt("caloriasPorcion"),
				rs.getInt("tipoIngrediente"));
		
	}
	public ArrayList<Ingredientes> getIngredientes(){
		return generarIngredientes("");
	}
	
	public ArrayList<Ingredientes> getIngredinetesIn(String select){
		return generarIngredientes("where IdIngrediente in("+select+")");
	}
	public ArrayList<Ingredientes> generarIngredientes(String condicion){
		
		ArrayList<Ingredientes> ingredientes = new ArrayList<Ingredientes>();
		ResultSet rs = null;
			try
			{
				CallableStatement cmd;
				cmd = fab.getConnection().prepareCall(principalSelect+condicion); 
				rs = cmd.executeQuery();
				
				while(rs.next()){
					ingredientes.add(contruir(rs));
				}			
			}
			catch(SQLException ex){
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}
		
		return ingredientes;
	}
}