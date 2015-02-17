/**
 * @author Unic
 * "hw15" project, Feb 17, 2015, 7:54:48 PM
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.entpatterns;

public class SimpleTransactionWrapper implements TransactionWrapper
{
	public static int STATUS_CLEAR = 0;
	public static int STATUS_SUCCESS = 1;
	public static int STATUS_FAIL = 2;
	
	private ComponentTransaction transaction;
	private Status status = null;
	
	public SimpleTransactionWrapper(ComponentTransaction transaction)
	{
		this.transaction = transaction;
	}
	
	@Override
    public void startTransaction(final Query query)
	{
		Runnable runnable = new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
					transaction.startTransaction(query);
					status = Status.Success;
				}
				catch (TransactionException e)
				{
					status = Status.Fail;
				}
			}
		};
		
		new Thread(runnable).start();
	}

	@Override
	public void commit()
	{
		transaction.commit();
		status = null;
	}

	@Override
	public void abort()
	{
		transaction.abort();
		status = null;
	}

	@Override
	public void synchronize()
	{
		while (status == null)
		{
			try
			{
				Thread.sleep(100);
			}
			catch (InterruptedException e)
			{
				break;
			}
		}
	}
	
	@Override
	public Status getStatus()
	{
		return status;
	}
}