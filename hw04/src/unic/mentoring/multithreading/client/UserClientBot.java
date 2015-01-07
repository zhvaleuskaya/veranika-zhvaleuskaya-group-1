/**
 * @author Unic
 * "hw01_classLoading" project, Oct 30, 2014, 2:31:52 AM
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.multithreading.client;

import org.apache.log4j.Logger;

import unic.mentoring.multithreading.core.Bank;
import unic.mentoring.multithreading.core.Client;
import unic.mentoring.multithreading.exception.BankException;
import unic.mentoring.multithreading.util.Counter;

public class UserClientBot extends Client
{
	private static final Logger LOG = Logger.getLogger(UserClientBot.class);
	
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
	public Long call()
	{
		LOG.debug("Thread " + this + " started.");
		
		long time = System.currentTimeMillis();
		
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
		
		return System.currentTimeMillis() - time;
	}
}