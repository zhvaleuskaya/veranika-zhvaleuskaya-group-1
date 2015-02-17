/**
 * @author Unic
 * "hw15" project, Feb 17, 2015, 8:32:15 PM
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.entpatterns;

import java.util.ArrayList;
import java.util.List;

public class CompositeTransactionLogic
{
	private List<TransactionWrapper> wrappers;
	private Coordinator coordinator;
	
	public CompositeTransactionLogic()
	{
		this.coordinator = new Coordinator();
		this.wrappers = new ArrayList<TransactionWrapper>();
	}
	
	public Status startTransaction(Query query)
	{
		for (TransactionWrapper wrapper : wrappers)
		{
			coordinator.register(wrapper);
		}
		
		return coordinator.startTransaction(query);
	}
	
	public void commit()
	{
		coordinator.commit();
	}
	
	public void abort()
	{
		coordinator.abort();
	}
	
	public void addTransactionWrapper(TransactionWrapper wrapper)
	{
		wrappers.add(wrapper);
	}
	
	public static void main(String[] args)
	{
		CompositeTransactionLogic compositeTransactionLogic = new CompositeTransactionLogic();
		compositeTransactionLogic.addTransactionWrapper( new SimpleTransactionWrapper( new ComponentTransaction("FirstCT") ) );
		compositeTransactionLogic.addTransactionWrapper( new SimpleTransactionWrapper( new ComponentTransaction("SecondCT") ) );
		Query query = new Query("SELECT * FROM {user}");
		
		Status status = compositeTransactionLogic.startTransaction(query);
		
		if (status == Status.Success)
		{
			compositeTransactionLogic.commit();
		}
		else
		{
			compositeTransactionLogic.abort();
		}
	}
}