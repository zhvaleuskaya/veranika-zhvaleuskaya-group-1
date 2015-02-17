/**
 * @author Unic
 * "hw15" project, Feb 17, 2015, 7:54:53 PM
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.entpatterns;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

public class ComponentTransaction
{
	private static final Logger LOG = Logger.getLogger(ComponentTransaction.class);
	
	private String name;
	
	public ComponentTransaction(String name)
	{
		this.name = name;
	}
	
	public void startTransaction(Query query) throws TransactionException
	{
		System.out.println("[" + name + "] query: " + query.getQuery());
		BufferedReader br = new BufferedReader( new InputStreamReader(System.in) );
		System.out.print("[" + name + "] Decision ([s]uccess | fail): ");
		boolean success = true;
		String message = "Query failed.";
		
		try
		{
			String line = br.readLine();
			success = "s".equals(line);
		}
		catch (IOException e)
		{
			success = false;
			message = e.getMessage();
		}
		
		if (!success)
		{
			throw new TransactionException("Can't done transaction: " + message);
		}
	}
	
	public void commit()
	{
		LOG.info(name + ": commit");
	}
	
	public void abort()
	{
		LOG.info(name + ": abort");
	}
}