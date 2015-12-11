package Grupo88.Inicio;

import java.util.ArrayList;

import master.ErrorPage;
import master.MasterPage;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.CompoundPropertyModel;

import Database.DAORecetas;
import Grupo88.Componentes.ListaDeRecetas;
import ObjetosDB.Receta;


public class Inicio extends MasterPage {

	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private FrmInicio frmInicio;
//	private DAORecetas daoRecetas;
	private Inicio pagina;
	
	public Inicio(){
		super();
		pagina = this;
		add(frmInicio = new FrmInicio("FrmInicio"));
	}
	
	public class FrmInicio extends Form<Object> {

		private static final long serialVersionUID = 6839760649395717223L;

		public FrmInicio(String id) {
			super(id);			
			setDefaultModel(new CompoundPropertyModel<FrmInicio>(this));
//			daoRecetas = new DAORecetas(getSessionBD());
			ArrayList<Receta> recetas;
			try {
				recetas = new ArrayList<Receta>(getUsuarioActual().cargarHome(getSessionBD()));
			} catch (Exception e) {
				setResponsePage(ErrorPage.ErrorRandom());
				return;
			}
			
			add(new ListaDeRecetas("recetasHome", recetas ,getUsuarioActual(),pagina));
		}
		
		@Override
		protected void onSubmit() {
			// Va a conectarse con BD y comprobar las validaciones
			super.onSubmit();
		}
	}
	
		 
}
