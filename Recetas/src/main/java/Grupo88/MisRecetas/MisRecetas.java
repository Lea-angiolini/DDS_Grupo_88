package Grupo88.MisRecetas;

import java.util.ArrayList;

import master.RegisteredPage;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.Link;

import Grupo88.AgregarReceta.AgregarReceta;
import Grupo88.Componentes.ListaDeRecetas;
import Grupo88.Inicio.Inicio;
import ObjetosDB.Receta;

public class MisRecetas extends RegisteredPage {

	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private FrmMisRecetas frmMisRecetas;
	private MisRecetas pagina;
	private NegocioMisRecetas negocio;
	
	public MisRecetas(){
		super();
		negocio = new NegocioMisRecetas(getSessionUser());
		pagina = this;
		add(frmMisRecetas = new FrmMisRecetas("frmMisRecetas"));
	
	}
	
	private class FrmMisRecetas extends Form<Object>{

		private static final long serialVersionUID = 1L;

		public FrmMisRecetas(String id) {
			super(id);			

			add(new ListaDeRecetas("listaMisRecetas", new ArrayList<Receta>(negocio.recetasParaUsuarioActual()) , getUsuarioActual(), pagina));
			
			add(new Link<Object>("cancelar"){
 
				private static final long serialVersionUID = 1L;

				public void onClick(){
					setResponsePage(Inicio.class);
				}
			});
			
			add(new Link<Object>("nvaReceta"){
 
				private static final long serialVersionUID = 1L;

				public void onClick(){
				setResponsePage(AgregarReceta.class);
				}
			});	
	}
}
}

