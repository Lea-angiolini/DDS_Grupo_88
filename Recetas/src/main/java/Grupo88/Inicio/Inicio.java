package Grupo88.Inicio;

import java.util.List;

import master.MasterPage;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.CompoundPropertyModel;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import Database.HibernateUtil;
import Grupo88.Componentes.ListaDeRecetas;
import ObjetosDB.Ingredientes;
import ObjetosDB.Receta_Ingrediente;

public class Inicio extends MasterPage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FrmInicio frmInicio;
	
	@SuppressWarnings("unchecked")
	public Inicio(){
		super();
		//getMenuPanel().setVisible(false);
		
		
		add(frmInicio = new FrmInicio("FrmInicio"));
	}
	
	public class FrmInicio extends Form {

		public FrmInicio(String id) {
			super(id);			
			setDefaultModel(new CompoundPropertyModel(this));
			
			add(new ListaDeRecetas("recetasHome", getUsuarioActual().cargarHome(),getUsuarioActual()));
		}
		
		@Override
		protected void onSubmit() {
			// Va a conectarse con BD y comprobar las validaciones
			super.onSubmit();
		}
	}
	
		 
}
