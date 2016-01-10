package Grupo88;

import master.MasterPage;
import objetosWicket.SesionUsuario;

import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;

import Grupo88.Inicio.Inicio;

/**
 * Application object for your web application.
 * If you want to run this application without deploying, run the Start class.
 * 
 * @see Grupo88.Start#main(String[])
 */
public class WicketApplication extends WebApplication
{
	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends WebPage> getHomePage()
	{
		return Inicio.class;
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init()
	{
		super.init();
		
		mountPage("/pageMount", MasterPage.class);
		// add your configuration here
	}
	
	@Override
	public Session newSession(Request request, Response response) {
		return new SesionUsuario(request);
	}
	
	@Override
	public RuntimeConfigurationType getConfigurationType() {
		return RuntimeConfigurationType.DEPLOYMENT;
	}
}
