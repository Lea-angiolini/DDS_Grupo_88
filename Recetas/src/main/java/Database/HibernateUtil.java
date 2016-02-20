package Database;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.context.internal.ThreadLocalSessionContext;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;



public class HibernateUtil implements Serializable{
	
	private static final long serialVersionUID = 6164875537581831612L;
	private static SessionFactory sessionFactory = null;
	  
	       public static synchronized void buildSessionFactory() {
	           if (sessionFactory == null) {
	               Configuration configuration = new Configuration();
	               configuration.configure();
	               configuration.setProperty("hibernate.current_session_context_class", "thread");
	               ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
	               sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	               
	           }
	       }
	  
	       public static Session openSessionAndBindToThread() {
	           Session session = sessionFactory.openSession();
	           ThreadLocalSessionContext.bind(session);
	           return session;
	       }
	  
	  
	       public static SessionFactory getSessionFactory() {
	           if (sessionFactory==null)  {
	               buildSessionFactory();
	           }
	           return sessionFactory;
	       }
	  
	       public static void closeSessionAndUnbindFromThread() {
	           Session session = ThreadLocalSessionContext.unbind(sessionFactory);
	           if (session!=null) {
	               session.close();
	           }
	       }
	  
	       public static void closeSessionFactory() {
	           if ((sessionFactory!=null) && (sessionFactory.isClosed()==false)) {
	               sessionFactory.close();
	           }
	       }

}
