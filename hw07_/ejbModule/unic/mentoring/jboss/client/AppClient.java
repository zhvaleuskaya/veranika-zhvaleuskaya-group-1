/**
 * @author Unic
 * "hw07_" project, Dec 2, 2014, 1:24:51 AM
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.jboss.client;

import javax.naming.Context;
import javax.naming.NamingException;

import unic.mentoring.jboss.beans.TestBean;
import unic.mentoring.jboss.beans.TestBeanRemote;
import unic.mentoring.jboss.util.ClientUtil;

public class AppClient
{
	public static void main(String[] args)
	{
		TestBeanRemote bean = doLookup();
		System.out.println( bean.hello("test") ); // 4. Call business logic
	}

	private static TestBeanRemote doLookup()
	{
		Context context = null;
		TestBeanRemote bean = null;
		try
		{
			// 1. Obtaining Context
			context = ClientUtil.getInitialContext();
			// 2. Generate JNDI Lookup name
			String lookupName = getLookupName();
			// 3. Lookup and cast
			System.out.println(lookupName);
			bean = (TestBeanRemote) context.lookup(lookupName);
		}
		catch (NamingException e)
		{
			e.printStackTrace();
		}
		return bean;
	}

	private static String getLookupName()
	{
		/*
		 * The app name is the EAR name of the deployed EJB without .ear suffix.
		 * Since we haven't deployed the application as a .ear, the app name for us
		 * will be an empty string
		 */
		String appName = "";

		/*
		 * The module name is the JAR name of the deployed EJB without the .jar
		 * suffix.
		 */
		String moduleName = "hw07_";

		/*
		 * AS7 allows each deployment to have an (optional) distinct name. This can
		 * be an empty string if distinct name is not specified.
		 */
		String distinctName = "";

		// The EJB bean implementation class name
		String beanName = TestBean.class.getSimpleName();

		// Fully qualified remote interface name
		final String interfaceName = TestBeanRemote.class.getName();

		// Create a look up string name
		String name = "ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + interfaceName;

		return name;
	}
}