package Grupo88.Reportes;

import java.util.List;

import javax.swing.JOptionPane;

import master.RegisteredPage;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.model.PropertyModel;

import Database.DAOReportes;
import ObjetosDB.Confirmacion;
import ObjetosDB.Historial;

	public class ReporteSegunCalorias  extends RegisteredPage{
		

		private static final long serialVersionUID = 1L;
		private NegocioReportes negocio;

		public ReporteSegunCalorias() {
			add(new FormReporte("frmReportes"));
			negocio = new NegocioReportes(getSessionUser());
		}
		
		private class FormReporte extends Form<Object>{

			private static final long serialVersionUID = 1L;
			
			private NumberTextField<Integer> desde;
			private NumberTextField<Integer> hasta;
			private Integer calMin;
			private Integer calMax;
			public FormReporte(String id) {
				super(id);
				desde = new NumberTextField<Integer>("desde", new PropertyModel<Integer>(this, "calMin"));
				hasta = new NumberTextField<Integer>("hasta", new PropertyModel<Integer>(this, "calMax"));
				
				add(desde);
				add(hasta);
				add(new EmptyPanel("AreaRecetas"));
			
			}
			
			@Override
			protected void onSubmit() {
				super.onSubmit();
				List<Confirmacion> listaHistorial = negocio.recetasConfirmadas(calMin.toString(), calMax.toString());
				addOrReplace(new PanelListaConfirmaciones("AreaRecetas",listaHistorial));
			}
		}
	}
