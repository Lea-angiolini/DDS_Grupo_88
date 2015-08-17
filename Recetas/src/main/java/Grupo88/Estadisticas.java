package Grupo88;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import master.MasterPage;
import master.RegisteredPage;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.AbstractItem;
import org.apache.wicket.markup.repeater.RepeatingView;

import Database.Browser;
import Grupo88.Login.FrmLogin;

public class Estadisticas extends RegisteredPage{
	//private static final long serialVersionUID = 1L;
	//private Usuario user = ((SesionUsuario)getSession()).getUsuario().getObject();
	private FrmEstadisticas frmEstadisticas;
	
	public Estadisticas(){
		super();
		//getMenuPanel().setVisible(false);
		
		add(frmEstadisticas = new FrmEstadisticas("frmEstadisticas"));

	}
	
	public class FrmEstadisticas extends Form{
		public FrmEstadisticas(String id) {
			super(id);
			
			ObjetosDB.Estadisticas est = Browser.obtenerEstadisticas();			
			
			add(generarLista("ultSem", est.getTopRecetasHombreSemana()));
			add(generarLista("ultMes", est.getTopRecetasHombreMes()));
			
	}
	}
	
	private RepeatingView generarLista(String id, ArrayList<String> listaRecetas){
		
		RepeatingView lista = new RepeatingView(id);
		
		for (String receta : listaRecetas){
			AbstractItem item = new AbstractItem(lista.newChildId());
			
			item.add(new Label("nombreReceta",receta));
			lista.add(item);
		}
		return lista;
	}
}
