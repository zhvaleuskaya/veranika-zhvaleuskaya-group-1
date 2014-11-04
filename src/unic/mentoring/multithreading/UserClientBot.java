/**
 * @author Unic
 * "hw01_classLoading" project, Oct 30, 2014, 2:31:52 AM
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.multithreading;

public class UserClientBot extends Client
{
	private String accountId;
	private int iterations;
	private Counter counter;
	
	public UserClientBot(Bank bank, String accountId, int iterations, Counter counter)
	{
		super(bank);
		
		this.accountId = accountId;
		this.iterations = iterations;
		this.counter = counter;
	}

	@Override
	public void run()
	{
		try
		{
			for (int i = 0; i < iterations; ++i)
			{
				bank.convert(accountId, "usd", "eur", 1);
				counter.inc();
			}
		}
		catch (BankException e)
		{
			e.printStackTrace();
		}
	}
}