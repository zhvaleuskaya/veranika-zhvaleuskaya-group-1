/**
 * @author Unic
 * "hw01_classLoading" project, Nov 4, 2014, 12:56:00 AM
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.multithreading.test;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import unic.mentoring.multithreading.Account;
import unic.mentoring.multithreading.Bank;
import unic.mentoring.multithreading.BankException;
import unic.mentoring.multithreading.Counter;
import unic.mentoring.multithreading.Currency;
import unic.mentoring.multithreading.Dao;
import unic.mentoring.multithreading.Service;
import unic.mentoring.multithreading.ServiceImpl;
import unic.mentoring.multithreading.UserClientBot;

public class ClientsTest
{
	private static final Logger LOG = Logger.getLogger(ClientsTest.class);
	private static final int THREADS_COUNT = 2;
	private static final int CONVESRIONS_COUNT = 10;
	private static final int TOTAL_CONVERSIONS_COUNT = THREADS_COUNT * CONVESRIONS_COUNT;
	
	private Bank bank;
	private Currency currencyUsd;
	private Currency currencyEur;
	private String accountId;
	private double delta = 0.0001;
	
	@Mock
	private Dao dao;
	
	@Before
	public void before() throws BankException
	{
		Service service = new ServiceImpl(dao);
		bank = new Bank(service);
		bank.addCurrency("eur", 0.01);
		bank.addCurrency("usd", 0.01);
		bank.addConversion("eur", "usd", 1.27363);
		bank.addConversion("usd", "eur", 0.78515);
		accountId = bank.addAccount("The torturing account");
		bank.addAccountCurrency(accountId, "eur", 100);
		bank.addAccountCurrency(accountId, "usd", 144);
		currencyUsd = bank.getCurrency("usd");
		currencyEur = bank.getCurrency("eur");
	}
	
	@Test
	public void testParallerConversions() throws BankException
	{
		Counter counter = new Counter();
		
		Thread[] threads = new Thread[THREADS_COUNT];
		for (int i = 0; i < threads.length; ++i)
			threads[i] = new Thread( new UserClientBot(bank, accountId, CONVESRIONS_COUNT, counter) );
		
		for (int i = 0; i < threads.length; ++i)
		{
			threads[i].start();
			
			// Use it for performing one by one conversions
			// try { threads[i].join(); } catch (InterruptedException e){}
		}
		
		while (counter.getCount() < TOTAL_CONVERSIONS_COUNT)
		{
			try
			{
				Thread.sleep(10);
			}
			catch (InterruptedException e){}
		}
		
		Account account = bank.getAccount(accountId);
		LOG.info("Conversion result for \"" + account.getId() + "\": " + currencyEur.getCode() + ":" + account.getAmount(currencyEur) + ", " +
				currencyUsd.getCode() + ":" + account.getAmount(currencyUsd) );
		
		Assert.assertEquals(115.8, account.getAmount(currencyEur), delta);
		Assert.assertEquals(124.0, account.getAmount(currencyUsd), delta);
	}
}