/**
 * @author Unic
 * "hw15" project, Feb 17, 2015, 8:35:59 PM
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.entpatterns;

import java.util.ArrayList;
import java.util.List;

public class Coordinator
{
	private List<TransactionWrapper> registeredWrappers;
	
	public Coordinator()
	{
		this.registeredWrappers = new ArrayList<TransactionWrapper>();
	}
	
	public Status startTransaction(Query query)
	{
		for (TransactionWrapper wrapper : registeredWrappers)
		{
			wrapper.startTransaction(query);
		}
		
		for (TransactionWrapper wrapper : registeredWrappers)
		{
			wrapper.synchronize();
		}
		
		for (TransactionWrapper wrapper : registeredWrappers)
		{
			if (wrapper.getStatus() == Status.Fail)
			{
				return Status.Fail;
			}
		}
		
		return Status.Success;
	}
	
	public void commit()
	{
		for (TransactionWrapper wrapper : registeredWrappers)
		{
			wrapper.commit();
		}
		
		registeredWrappers.clear();
	}
	
	public void abort()
	{
		for (TransactionWrapper wrapper : registeredWrappers)
		{
			wrapper.abort();
		}
		
		registeredWrappers.clear();
	}
	
	public void register(TransactionWrapper wrapper)
	{
		registeredWrappers.add(wrapper);
	}
}