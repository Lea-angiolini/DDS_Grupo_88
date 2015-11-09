package Grupo88.Inicio;

import master.MasterPage;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.CompoundPropertyModel;

import Grupo88.Componentes.ListaDeRecetas;


public class Inicio extends MasterPage {

	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private FrmInicio frmInicio;
	
	public Inicio(){
		super();
		add(frmInicio = new FrmInicio("FrmInicio"));
	}
	
	public class FrmInicio extends Form<Object> {

		private static final long serialVersionUID = 6839760649395717223L;

		public FrmInicio(String id) {
			super(id);			
			setDefaultModel(new CompoundPropertyModel<FrmInicio>(this));
			
			add(new ListaDeRecetas("recetasHome", getUsuarioActual().cargarHome(),getUsuarioActual()));
		}
		
		@Override
		protected void onSubmit() {
			// Va a conectarse con BD y comprobar las validaciones
			super.onSubmit();
		}
	}
	
		 
}
