/**
 * @author Unic
 * "hw09" project, Dec 12, 2014, 12:17:42 AM
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.springmvc.core;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class DbUtil
{
	private static SessionFactory sessionFactory;
	
	public static SessionFactory getSessionFactory()
	{
		if (sessionFactory == null)
		{
			Configuration configuration = new Configuration().configure();
			StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
			serviceRegistryBuilder.applySettings(configuration.getProperties());
			ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		}
		
		return sessionFactory;
	}
	
	public static void closeSessionFactory()
	{
		if (sessionFactory != null)
		{
			sessionFactory.close();
			sessionFactory = null;
		}
	}
}