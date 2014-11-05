package unic.mentoring.gc;

import org.apache.log4j.Logger;

/**
 * @author Unic
 * "hw01_classLoading" project, 2014.
 * GPL v3: http://gnu.org/licenses
 */
public class Run extends Thread
{
	private static Logger LOG = Logger.getLogger(Run.class);
	
	public static void main(String[] args)
	{
		LOG.debug("Hello, garbage collector!");
		new Run().start();
	}
	
	@Override
	public void run()
	{
		Object o;
		
		for (;;)
		{
			for (int i = 0; i < 10000; ++i)
			{
				o = new Object();
				o.toString();
				o = null;
			}
			
			try
			{
				Thread.sleep(10);
			}
			catch (InterruptedException e){}
		}
	}
}