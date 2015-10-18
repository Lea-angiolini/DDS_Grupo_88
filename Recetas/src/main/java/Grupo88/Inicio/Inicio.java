package Grupo88.Inicio;

import master.MasterPage;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.CompoundPropertyModel;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import Grupo88.Componentes.ListaDeRecetas;
import ObjetosDB.Ingredientes;
import ObjetosDB.Receta;


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
		/*
		SessionFactory sessionFactory;
		 
		  Configuration configuration = new Configuration();
		 configuration.configure();
		 ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
		 sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		
		
		Session se = sessionFactory.openSession();
		Receta q = (Receta) se.createQuery("from Receta").list().get(0);
		*/
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
