/**
 * @author Unic
 * "hw07_" project, Dec 2, 2014, 1:22:42 AM
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.jboss.util;

import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ClientUtil
{
	private static final String PKG_INTERFACES = "org.jboss.ejb.client.naming";
	
	private static Context initialContext;

	public static Context getInitialContext() throws NamingException
	{
		if (initialContext == null)
		{
			Properties properties = new Properties();
			properties.put(Context.URL_PKG_PREFIXES, PKG_INTERFACES);
			initialContext = new InitialContext(properties);
		}
		
		return initialContext;
	}
}