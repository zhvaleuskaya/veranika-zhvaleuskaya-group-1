/**
 * @author Unic
 * "hw01_classLoading" project, 2014.
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.classloading.core;

import java.io.IOException;

import org.apache.log4j.Logger;

public class Run
{
	private static final Logger LOG = Logger.getLogger(Run.class);
	private static final String DEFAULT_DIRECTORY = "ext";
	
	public static void main(String[] args)
	{
		String directory = args.length >= 1 ? args[0] : DEFAULT_DIRECTORY;
		
		try
		{
			DirectoryClassLoader dcl = new DirectoryClassLoader();
			dcl.registerDirectory(directory, true);
			dcl.processEvents();
		}
		catch (IOException e)
		{
			LOG.error( e.getMessage() );
		}
	}
}