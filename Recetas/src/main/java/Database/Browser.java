package Database;

import java.io.ObjectInputStream.GetField;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.chainsaw.Main;

import Database.Factory;
import ObjetosDB.*;

@Deprecated
public class Browser {
	
	public static Boolean Login(String username, String password) {
		Factory f = new Factory();
		return f.loguearUsuario(username, password);
	}
	
	public static Usuario cargarUsuario(String username){
		Factory f = new Factory();
		//Usuario user = f.cargarDatosBasicosDe(username);
		//user.setGrupos(f.cargarGruposDe(user.getUsername()));
		//user.setDietas(f.cargarDietas(user.getUsername()));
		//user.setCondiciones(f.cargarCondiciones(user.getUsername()));
		return null;
	}
	
	public static ArrayList<Recetaborrar> cargarRecetasPopulares(){
		Factory f = new Factory();
		ArrayList<Recetaborrar> recetas = f.cargarRecetasPopulares();
		return recetas;
	}
	
	/*public static ArrayList<Receta> cargarRecetasUsuario(String usuario){
		Factory f = new Factory();
		ArrayList<Receta> recetas = f.cargarRecetasUsuario(usuario);
		return recetas;
	}*/
	
	public static ArrayList<Receta> cargarRecetasBuscadas(itemsABuscar queBuscar){
		Factory f = new Factory();
		ArrayList<Receta> recetas = f.cargarRecetasBuscadas(queBuscar);
		return recetas;
	}
	
	public static List<Complexiones> listaComplexiones(){
		Factory f = new Factory();
		return  f.listaComplexiones();
	}
	
	public static List<PreferenciasAlimenticias> listaPreferenciasAlimenticias(){
		Factory f = new Factory();
		return  f.listaPreferenciasAlimenticias();
	}
	
	public static ArrayList<CondicionesPreexistentes> listaCondPreexistentes(){
		Factory f = new Factory();
		return f.listaCondPreexistentes();
	}
	
	public static ArrayList<Dietas> listaDietas(){
		Factory f = new Factory();
		return f.listaDietas();
	}
	
	public static ArrayList<Rutinas> listaRutinas(){
		Factory f = new Factory();
		return f.listaRutinas();
	}
	
	public static ArrayList<Temporadas> listaTemporadas(){
		Factory f = new Factory();
		return f.listaTemporadas();
	}
	
	public static ArrayList<GruposAlimenticios> listaGruposAlim(){
		Factory f = new Factory();
		return f.listaGruposAlim();
	}
	
	public static ArrayList<Ingredientes> listaIngredientes(){
		Factory f = new Factory();
		return f.listaIngredientes();
	}
	
	public static ArrayList<Condimentos> listaCondimentos(){
		Factory f = new Factory();
		return f.listaCondimentos();	
	}
	
	public static ArrayList<Dificultades> listaDificultades(){
		Factory f = new Factory();
		return f.listaDificultades();
	}
	
	public static String registrarUsuario(Usuario nvoUsuario){
		Factory f = new Factory();
		return f.registrarUsuario(nvoUsuario);
	}
	
	public static String modificarPerfil(Usuario user){
		Factory f = new Factory();
		return f.modificarPerfil(user);
	}
	
	/*public static Receta cargarReceta(int idReceta, Usuario user){
		Factory f = new Factory();
		return f.cargarReceta(idReceta, user);
	}*/
	
	public static boolean agregarAHistorial(int idReceta ,Usuario user){
		Factory f = new Factory();
		return f.agregarAHistorial(idReceta, user);	
	}
	
	public static boolean calUltimaConfirmacion(int idReceta,Usuario user, int calificacion){
		Factory f = new Factory();
		return f.calUltimaConfirmacion(idReceta, user, calificacion);
	}

	
	public static boolean entrarGrupo (String username, int idGrupo){
		Factory f = new Factory();
		return f.entrarGrupo(username, idGrupo);
	}
	
	public static boolean salirGrupo (String username, int idGrupo){
		Factory f = new Factory();
		return f.salirGrupo(username, idGrupo);
	}
	

	
	public static boolean grupoTieneReceta(int idGrupo ,int idReceta){
		Factory f = new Factory();
		return f.grupoTieneReceta(idGrupo,idReceta);
	}
	
	public static boolean agregarRecetaGrupo(int idGrupo, int idReceta){
		Factory f = new Factory();
		return f.agregarRecetaGrupo(idGrupo, idReceta);
	}
	
	public static boolean agregarHistConsultas(int idReceta, String username){
		Factory f = new Factory();
		return f.agregarHistConsultas(idReceta, username);
	}
	
	/*public static boolean agregarReceta(Receta receta){
		Factory f = new Factory();
		return f.agregarReceta(receta);	
	}*/
	
	/*public static Estadisticas obtenerEstadisticas(){
		Factory f = new Factory();
		return f.obtenerEstadisticas();
	}*/
		
	public static ArrayList<Receta> obtenerRecetasGrupo(int idGrupo){
		Factory f = new Factory();
		return f.obtenerRecetasGrupo(idGrupo);
	}
	
	public static ArrayList<Usuario> obtenerUsuariosGrupo(Grupo grupo){
		Factory f = new Factory();
		return f.obtenerUsuariosGrupo(grupo);
	}
	
	public static ArrayList<Receta> cargarHomeRecetas(Usuario user){
		Factory f = new Factory();
		return f.cargarHomeRecetas(user);
	}
	
	/*public static boolean agregarIngredientesyCondimentos(Receta receta){
		Factory f = new Factory();
		return f.agregarIngredientesyCondimentos(receta);
	}*/
	
	public static ArrayList<Consulta> obtenerConsultasSegunDificultad(int dias){
		Factory f = new Factory();
		return f.obtenerConsultasSegunDificultad(dias);
	}
	
	public static ArrayList<Consulta> obtenerTopRecetasMasConsultadas(int dias){
		Factory f = new Factory();
		return f.obtenerTopRecetasMasConsultadas(dias);
	}
}
