package Grupo88.Componentes;

import objetosWicket.SesionUsuario;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.hibernate.Session;

import Grupo88.BuscarReceta.BuscarReceta;
import Grupo88.Estadisticas.Estadisticas;
import Grupo88.Estadisticas.NegocioEstadistica;
import Grupo88.GestionarGrupos.CrearNuevoGrupo;
import Grupo88.GestionarGrupos.GestionarGrupos;
import Grupo88.GestionarPerfil.CambioPass;
import Grupo88.GestionarPerfil.GestionarPerfil;
import Grupo88.MisRecetas.MisRecetas;
import Grupo88.Reportes.ReporteNuevasRecetas;
import Grupo88.Reportes.ReporteSegunCalorias;
import Grupo88.Reportes.ReportesConsultadas;
import ObjetosDB.Usuario;

public class PanelLinks extends Panel {

	private static final long serialVersionUID = -6539107446596515L;
	private SesionUsuario sesion;
	//private Session sessionDB;
	//private Usuario user;
	
	public PanelLinks(String id) {
		super(id);
		
		sesion = (SesionUsuario)getSession();
		//sessionDB = sesion.getSessionDB();
		//user = sesion.getUsuario();
		add(new FrmLinks("FrmLinks"));
		
	}

	public class FrmLinks extends Form {
		
		public FrmLinks(String id) {
			super(id);
			
			add(new Link("recetas"){
				
				public void onClick() {
					setResponsePage(BuscarReceta.class);
				}
			});
			
			add(new Link("perfil"){
				
				public void onClick() {
					setResponsePage(GestionarPerfil.class);
				}
			});
			
			add(new Link("cambiarPass"){
				
				public void onClick() {
					setResponsePage(CambioPass.class);
				}
			});
			
			add(new Link("buscarGrupos"){
				
				public void onClick() {
					setResponsePage(GestionarGrupos.class);
				}
			});
			
			add(new Link("CrearNuevoGrupo"){
				
				public void onClick() {
					setResponsePage(CrearNuevoGrupo.class);
				}
			});

			add(new Link("misRecetas"){
				
				public void onClick() {
					setResponsePage(MisRecetas.class);
				}
			});
			
			add(new Link("estadisticas"){
				
				public void onClick() {
					setResponsePage(new Estadisticas(NegocioEstadistica.porSexo(sesion)));
				}
			});

			add(new Link("consultasDificultad"){
				
				public void onClick() {
					setResponsePage(new Estadisticas(NegocioEstadistica.porDificultad(sesion)));
				}
			});
			
			add(new Link("topRecetasConsultadas"){
				
				public void onClick() {
					setResponsePage(new Estadisticas(NegocioEstadistica.recetasMasconsultadas(sesion)));
				}
			});
			
			add(new Link("recetasConsultadas"){
				
				public void onClick() {
					setResponsePage(new ReportesConsultadas());
				}
			});
			
			add(new Link("recetasNuevas"){
				
				public void onClick() {
					setResponsePage(new ReporteNuevasRecetas());
				}
			});
			
			add(new Link("recetasCalorias"){
				
				public void onClick() {
					setResponsePage(new ReporteSegunCalorias());
				}
			});
		}
	}	
}