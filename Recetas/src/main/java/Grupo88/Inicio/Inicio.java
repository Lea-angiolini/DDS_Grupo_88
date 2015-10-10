package Grupo88.Inicio;

import master.MasterPage;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.CompoundPropertyModel;


import Grupo88.Componentes.ListaDeRecetas;


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
