package Database;

import java.io.ObjectInputStream.GetField;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import org.apache.log4j.chainsaw.Main;

import Database.Factory;
import ObjetosDB.*;

public class Browser {
	
	public static Boolean Login(String username, String password) {
		Factory f = new Factory();
		return f.loguearUsuario(username, password);
	}
	
	public static Usuario cargarUsuario(String username){
		Factory f = new Factory();
		Usuario user = f.cargarDatosBasicosDe(username);
		//user.setGrupos(f.cargarGruposDe(user.getUsername()));
		//user.setDietas(f.cargarDietas(user.getUsername()));
		//user.setCondiciones(f.cargarCondiciones(user.getUsername()));
		return user;
	}
	
	public static Recetas cargarRecetasPopulares(){
		Factory f = new Factory();
		Recetas recetas = f.cargarRecetasPopulares();
		return recetas;
	}
	
	public static Recetas cargarRecetasUsuario(String usuario){
		Factory f = new Factory();
		Recetas recetas = f.cargarRecetasUsuario(usuario);
		return recetas;
	}
	
	public static itemBuscador cargarItemBuscador(){
		Factory f = new Factory();
		itemBuscador item = f.cargarItemBuscador();
		return item;
	
	}
	
	public static Recetas cargarRecetasBuscadas(itemsABuscar queBuscar){
		Factory f = new Factory();
		Recetas recetas = f.cargarRecetasBuscadas(queBuscar);
		return recetas;
	}
}
