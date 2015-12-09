package Grupo88.Reportes;

import java.util.List;

import javax.swing.JOptionPane;

import master.RegisteredPage;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.PropertyModel;

import Database.DAOReportes;
import Database.DAOReportesPeriodo;
import ObjetosDB.Historial;
import ObjetosDB.Receta;

public class Reportes extends RegisteredPage{
	

	private static final long serialVersionUID = 1L;
	private DAOReportes daoReportes;

	public Reportes(DAOReportes daoReportes) {
		add(new FormReporte("frmReportes"));
		this.daoReportes = daoReportes;
	}
	
	
	private class FormReporte extends Form<Object>{

		private static final long serialVersionUID = 1L;
		
		private TextField<String> pickerDesde;
		private TextField<String> pickerHasta;
		private String fechaDesde;
		private String fechaHasta;
		public FormReporte(String id) {
			super(id);
			pickerDesde = new TextField<String>("desde", new PropertyModel<String>(this, "fechaDesde"));
			pickerHasta = new TextField<String>("hasta", new PropertyModel<String>(this, "fechaHasta"));
			
			add(pickerDesde);
			add(pickerHasta);
			add(new EmptyPanel("AreaRecetas"));
		
		}
		
		@Override
		protected void onSubmit() {
			super.onSubmit();
			List<Historial> listaHistorial;
			try {
				listaHistorial = daoReportes.recetasmasconsultadas(fechaDesde, fechaHasta);
				addOrReplace(new PanelListaRecetas("AreaRecetas", listaHistorial));
			} catch (Exception e) {
				e.printStackTrace();
				info("error");
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
			
			
		}
	}
}
